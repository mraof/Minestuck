package com.mraof.minestuck.item.weapon;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class CustomBow extends BowItem
{
	private final Type type;
	
	public enum Type
	{
		FLAME, LONG, NO_GRAVITY, QUICK, RANDOM_SECOND
	}
	
	public CustomBow(Item.Properties builder, Type type)
	{
		super(builder);
		this.type = type;
		this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
			if(p_210310_2_ == null)
			{
				return 0.0F;
			} else
			{
				return !(p_210310_2_.getActiveItemStack().getItem() instanceof CustomBow) ? 0.0F : (float) (p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) / 20.0F;
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
			return p_210309_2_ != null && p_210309_2_.isHandActive() && p_210309_2_.getActiveItemStack() == p_210309_0_ ? 1.0F : 0.0F;
		});
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
	{
		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			boolean canShootFlag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);
			
			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || canShootFlag);
			if(i < 0) return;
			
			if(!itemstack.isEmpty() || canShootFlag)
			{
				if(itemstack.isEmpty())
				{
					itemstack = new ItemStack(Items.ARROW);
				}
				
				float f = getArrowVelocity(i);
				if(!((double) f < 0.1D))
				{
					boolean noPickupFlag = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
					if(!worldIn.isRemote)
					{
						ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
						AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
						abstractarrowentity = customeArrow(abstractarrowentity);
						if(this.type == Type.LONG) //TODO has trouble tracking real position
							f = f + 0.5F;
						abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, this.type == Type.LONG ? 0.5F : 1.0F);
						if(f == 1.0F)
						{
							abstractarrowentity.setIsCritical(true);
						}
						
						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						if(j > 0)
						{
							abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double) j * 0.5D + 0.5D);
						}
						
						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						if(k > 0)
						{
							abstractarrowentity.setKnockbackStrength(k);
						}
						
						if(this.type == Type.FLAME || EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
						{
							abstractarrowentity.setFire(100);
						}
						
						stack.damageItem(1, playerentity, (p_220009_1_) -> {
							p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
						});
						if(noPickupFlag || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
						{
							abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						}
						
						if(this.type == Type.NO_GRAVITY)
							abstractarrowentity.setNoGravity(true);
						
						if(this.type == Type.RANDOM_SECOND)
						{
							AbstractArrowEntity secondArrowEntity = arrowitem.createArrow(worldIn, itemstack, playerentity);
							secondArrowEntity.shoot(playerentity, playerentity.rotationPitch + random.nextFloat() - .5F, playerentity.rotationYaw + random.nextFloat() - .5F, 0.0F, f * 3.0F, 2.0F);
							secondArrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
							worldIn.addEntity(secondArrowEntity);
						}
						
						worldIn.addEntity(abstractarrowentity);
					}
					
					worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if(!noPickupFlag && !playerentity.abilities.isCreativeMode)
					{
						itemstack.shrink(1);
						if(itemstack.isEmpty())
						{
							playerentity.inventory.deleteStack(itemstack);
						}
					}
					
					playerentity.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}
}
