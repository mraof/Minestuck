package com.mraof.minestuck.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TempleScannerItem extends Item
{
	public TempleScannerItem(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.UI_BUTTON_CLICK, SoundCategory.AMBIENT, 0.8F, 1.3F);
		
		ItemStack item = playerIn.getHeldItem(handIn);
		if(worldIn instanceof ServerWorld)
		{
			BlockPos structureBlockPos = ((ServerWorld) worldIn).getChunkProvider().getChunkGenerator().findNearestStructure(worldIn, "minestuck:frog_temple", new BlockPos(playerIn), 100, false);
			
			
			if(structureBlockPos != null)
			{
				BlockPos playerBlockPos = playerIn.getPosition();
				ITextComponent message = new TranslationTextComponent(getTranslationKey() + ".successMessage", (int)Math.sqrt(playerBlockPos.distanceSq(structureBlockPos.up(64))));
				message.getStyle().setColor(TextFormatting.AQUA);
				playerIn.sendMessage(message);
			} else
			{
				ITextComponent message = new TranslationTextComponent(getTranslationKey() + ".failMessage");
				message.getStyle().setColor(TextFormatting.RED);
				playerIn.sendMessage(message);
			}
		}
		
		return ActionResult.resultSuccess(item);
	}
}
