package com.mraof.minestuck.entity;

import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.network.LotusFlowerPacket;
import com.mraof.minestuck.network.MSPacketHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.Collections;

public class LotusFlowerEntity extends LivingEntity implements IAnimatable, IEntityAdditionalSpawnData
{
	private static final int RESTORATION_TIME = 10000; //500 seconds from animation start to flower restoration
	
	// Animation lengths
	private static final int OPENING_LENGTH = 120;	//6 sec open animation * 20 ticks/sec = 120
	private static final int OPEN_IDLE_LENGTH = 320;	//4 sec idle animation * 4 loops * 20 ticks/sec = 320
	private static final int VANISHING_LENGTH = 13;	//0.65 sec vanish animation * 20 ticks/sec = 13
	
	// Animation start times
	private static final int IDLE_TIME = -1;
	private static final int OPEN_START = 0;
	private static final int OPEN_IDLE_START = OPEN_START + OPENING_LENGTH;
	private static final int VANISH_START = OPEN_IDLE_START + OPEN_IDLE_LENGTH;
	private static final int ANIMATION_END = VANISH_START + VANISHING_LENGTH;
	
	private final AnimationFactory factory = new AnimationFactory(this);
	
	//Only used serverside. Used to track the flower state and the progression of the animation
	private int eventTimer = IDLE_TIME;
	
	public static final String REGROW = "minestuck.lotusflowerentity.regrow";
	
	// Specifies the current animation phase
	@Nonnull
	private Animation animation = Animation.IDLE;
	
	protected LotusFlowerEntity(EntityType<? extends LotusFlowerEntity> type, World worldIn)
	{
		super(type, worldIn);
		
		setInvulnerable(true);
	}
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
	{
		event.getController().setAnimation(new AnimationBuilder().addAnimation(animation.animationName, true));
		
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData data)
	{
		data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
	}
	
	@Override
	public AnimationFactory getFactory()
	{
		return this.factory;
	}
	
	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand)
	{
		if(isAlive() && !player.isSneaking() && animation == Animation.IDLE)
		{
			startLotusAnimation();
			return true;
		}
		if(isAlive() && animation == Animation.EMPTY)
		{
			ItemStack itemstack = player.getHeldItem(hand);
			
			if(player.getDistanceSq(this) < 9 && itemstack.getItem() == Items.BONE_MEAL && player.isCreative())
			{
				restoreFromBonemeal();
			} else if(world.isRemote)
			{
				player.sendMessage(new TranslationTextComponent(REGROW));
			}
			
			return true;
		} else
			return super.processInitialInteract(player, hand);
	}
	
	
	@Override
	public void livingTick()
	{
		super.livingTick();
		
		if(!world.isRemote)
		{
			if(animation != Animation.IDLE)
				setEventTimer(eventTimer + 1);
			
			if(eventTimer == OPEN_IDLE_START)
				spawnLoot();
			else if(eventTimer >= RESTORATION_TIME)
				setEventTimer(IDLE_TIME);
		}
	}
	
	private void startLotusAnimation()
	{
		if(!world.isRemote)
		{
			setEventTimer(OPEN_START);
			
			Vec3d posVec = getPositionVec();
			world.playSound(null, posVec.getX(), posVec.getY(), posVec.getZ(), SoundEvents.BLOCK_COMPOSTER_READY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
		}
	}
	
	private void restoreFromBonemeal()
	{
		if(!world.isRemote)
			setEventTimer(IDLE_TIME);
		
		Vec3d posVec = getPositionVec();
		for(int i = 0; i < 10; i++)
			this.world.addParticle(ParticleTypes.COMPOSTER, posVec.x, posVec.y + 0.5, posVec.z, 0.5 - rand.nextDouble(), 0.5 - rand.nextDouble(), 0.5 - rand.nextDouble());
	}
	
	private void setEventTimer(int time)
	{
		if(world.isRemote)
			throw new IllegalStateException("Shouldn't call setEventTimer client-side!");
		
		eventTimer = time;
		
		Animation newAnimation = animationFromEventTimer();
		if(newAnimation != animation)
			updateAndSendAnimation(newAnimation);
	}
	
	private Animation animationFromEventTimer()
	{
		if(eventTimer >= ANIMATION_END)
			return Animation.EMPTY;
		else if(eventTimer >= VANISH_START)
			return Animation.VANISH;
		else if(eventTimer >= OPEN_IDLE_START)
			return Animation.OPEN_IDLE;
		else if(eventTimer >= OPEN_START)
			return Animation.OPEN;
		else
			return Animation.IDLE;
	}
	
	protected void updateAndSendAnimation(Animation animation)
	{
		this.animation = animation;
		LotusFlowerPacket packet = LotusFlowerPacket.createPacket(this, animation);
		MSPacketHandler.sendToTracking(packet, this);
	}
	
	protected void spawnLoot()
	{
		World worldIn = this.world;
		Vec3d posVec = this.getPositionVec();
		
		ItemEntity unpoweredComputerItemEntity = new ItemEntity(worldIn, posVec.getX(), posVec.getY() + 1D, posVec.getZ(), new ItemStack(MSItems.COMPUTER_PARTS, 1));
		worldIn.addEntity(unpoweredComputerItemEntity);
		
		ItemEntity sburbCodeItemEntity = new ItemEntity(worldIn, posVec.getX(), posVec.getY() + 1D, posVec.getZ(), new ItemStack(MSItems.SBURB_CODE, 1));
		worldIn.addEntity(sburbCodeItemEntity);
		
		this.world.addParticle(ParticleTypes.FLASH, posVec.x, posVec.y + 0.5D, posVec.z, 0.0D, 0.0D, 0.0D);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		
		compound.putInt("EventTimer", eventTimer);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		
		if(compound.contains("EventTimer", Constants.NBT.TAG_ANY_NUMERIC))
		{
			eventTimer = compound.getInt("EventTimer");
			animation = animationFromEventTimer();
		}
	}
	
	@Override
	public void writeSpawnData(PacketBuffer buffer)
	{
		buffer.writeInt(animation.ordinal());
	}
	
	@Override
	public void readSpawnData(PacketBuffer additionalData)
	{
		animation = Animation.values()[additionalData.readInt()];
	}
	
	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	public void setAnimationFromPacket(Animation newAnimation)
	{
		if(world.isRemote)
		{
			animation = newAnimation;
			if(animation == Animation.IDLE)
				addRestoreEffects();
		}
	}
	
	protected void addRestoreEffects()
	{
		Vec3d posVec = this.getPositionVec();
		this.world.addParticle(ParticleTypes.FLASH, posVec.x, posVec.y + 0.5D, posVec.z, 0.0D, 0.0D, 0.0D);
		this.world.playSound(posVec.getX(), posVec.getY(), posVec.getZ(), SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.NEUTRAL, 1.0F, 1.0F, false);
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	@Override
	public void move(MoverType typeIn, Vec3d pos)
	{}
	
	@Override
	public Iterable<ItemStack> getArmorInventoryList()
	{
		return Collections.emptyList();
	}
	
	@Override
	public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn)
	{
		return ItemStack.EMPTY;
	}
	
	@Override
	public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack)
	{}
	
	@Override
	public HandSide getPrimaryHand()
	{
		return HandSide.RIGHT;
	}
	
	public enum Animation
	{
		IDLE("lotus.idle"),
		OPEN("lotus.open"),
		OPEN_IDLE("lotus.open.idle"),
		VANISH("lotus.vanish"),
		EMPTY("lotus.empty");
		
		private final String animationName;
		
		Animation(String animationName)
		{
			this.animationName = animationName;
		}
	}
}