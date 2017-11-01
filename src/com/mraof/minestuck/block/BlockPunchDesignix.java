package com.mraof.minestuck.block;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.client.gui.GuiHandler;
import com.mraof.minestuck.tileentity.TileEntityPunchDesignix;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockPunchDesignix extends BlockLargeMachine
{
	
	public static final PropertyEnum<EnumParts> PART = PropertyEnum.create("part", EnumParts.class);
	public static final PropertyDirection DIRECTION = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockPunchDesignix() {
		super(2,2,1);
		this.setUnlocalizedName("punch_designix");
		this.setDefaultState(this.blockState.getBaseState());
		
	} 
	//not sure how to do this.
	//@Override
	//public AxisAlignedBB getBoundingBox(IBlockState state,IBlockAccess source,BlockPos pos){
		
	//}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		BlockPos MasterPos = getMainPos(state, pos);
		TileEntity te = worldIn.getTileEntity(pos);
		if(!worldIn.isRemote && te != null && te instanceof TileEntityPunchDesignix && !((TileEntityPunchDesignix)te).destroyed)
		{
			playerIn.openGui(Minestuck.instance, GuiHandler.GuiId.MACHINE.ordinal(), worldIn, MasterPos.getX(), MasterPos.getY(), MasterPos.getZ());
		}
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPunchDesignix(this.getStateFromMeta(meta));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		EnumFacing facing = EnumFacing.getHorizontal(MathHelper.floor((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
		state = state.withProperty(DIRECTION, facing);
		if(!(worldIn.isRemote))
		{
			worldIn.setBlockState(pos, state.withProperty(PART, EnumParts.BOTTOM_LEFT));
			worldIn.setBlockState(pos.offset(facing.rotateYCCW()), state.withProperty(PART, EnumParts.BOTTOM_RIGHT));
			worldIn.setBlockState(pos.up(),state.withProperty(PART, EnumParts.TOP_LEFT));
			worldIn.setBlockState(pos.up().offset(facing.rotateYCCW()), state.withProperty(PART, EnumParts.TOP_RIGHT));
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
	
		BlockPos masterPos = getMainPos(state, pos);
		TileEntityPunchDesignix te = (TileEntityPunchDesignix) worldIn.getTileEntity(masterPos);
		if(te != null)
		{
			te.destroyed = true;
			InventoryHelper.dropInventoryItems(worldIn, pos, te);
		}
		
		super.breakBlock(worldIn, pos, state);
	}
	
	//Block state handling
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, PART, DIRECTION);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState defaultState = this.getDefaultState();
		EnumParts part = EnumParts.values()[meta % 4];
		EnumFacing facing = EnumFacing.getHorizontal(meta/4);
		
		return defaultState.withProperty(PART, part).withProperty(DIRECTION, facing);
	}
	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumParts part = state.getValue(PART);
		EnumFacing facing = state.getValue(DIRECTION);
		
		return part.ordinal() + facing.getHorizontalIndex()*4;
	}
	
	public BlockPos getMainPos(IBlockState state, BlockPos pos)
	{
		EnumFacing facing = state.getValue(DIRECTION);
		switch(state.getValue(PART))
		{
			case TOP_LEFT: return pos;
			case TOP_RIGHT: return pos.offset(facing.rotateY());
			case BOTTOM_LEFT: return pos.up();
			case BOTTOM_RIGHT: return pos.up().offset(facing.rotateY());
		}
		return pos;
	}
	
	public enum EnumParts implements IStringSerializable
	{
		BOTTOM_LEFT,
		BOTTOM_RIGHT,
		TOP_LEFT,
		TOP_RIGHT;
		
		@Override
		public String toString()
		{
			return this.getName();
		}
		
		@Override
		public String getName()
		{
			return this.name().toLowerCase();
		}
	}
}