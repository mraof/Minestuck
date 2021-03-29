package com.mraof.minestuck.tileentity;

import com.mraof.minestuck.block.LotusTimeCapsuleBlock;
import net.minecraft.block.CampfireBlock;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

/*
public class LotusTimeCapsuleTileEntity extends TileEntity
{
	int countdownTime = 2000;
	
	public LotusTimeCapsuleTileEntity()
	{
		super(MSTileEntityTypes.LOTUS_TIME_CAPSULE);
	}
	
	public void tick() {
		boolean opened = this.getBlockState().get(LotusTimeCapsuleBlock.UNACTIVATED);
		if (opened) {
			--countdownTime;
		} else {
			countdownTime = 2000;
		}
	}
}
*/
