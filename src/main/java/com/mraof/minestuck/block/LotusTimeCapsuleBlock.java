package com.mraof.minestuck.block;

import com.mraof.minestuck.block.machine.MachineMultiblock;
import com.mraof.minestuck.block.machine.MultiMachineBlock;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.util.CustomVoxelShape;
import com.mraof.minestuck.util.MSSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;
import java.util.Random;

public class LotusTimeCapsuleBlock extends MultiMachineBlock
{
	protected final Map<Direction, VoxelShape> shape;
	protected final boolean recursive, corner;
	protected final BlockPos mainPos;
	
	public LotusTimeCapsuleBlock(MachineMultiblock machine, CustomVoxelShape shape, boolean recursive, boolean corner, BlockPos mainPos, Properties properties)
	{
		super(machine, properties);
		this.shape = shape.createRotatedShapes();
		this.recursive = recursive;
		this.corner = corner;
		this.mainPos = mainPos;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return shape.get(state.get(FACING));
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		ItemStack itemstack = player.getHeldItem(handIn);
		if(itemstack.isEmpty())
		{
			if(!worldIn.isRemote)
			{
				Direction direction = hit.getFace();
				Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
				worldIn.playSound((PlayerEntity) null, pos, MSSoundEvents.EVENT_ECHELADDER_INCREASE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				//worldIn.setBlockState(pos, MSBlocks.DORMANT_LOTUS_TIME_CAPSULE_BLOCK.getDefaultState(), 11);
				ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + 0.5D + (double) direction1.getXOffset() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction1.getZOffset() * 0.65D, new ItemStack(MSItems.SERVER_DISK, 1));
				itementity.setMotion(0.05D * (double) direction1.getXOffset() + worldIn.rand.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction1.getZOffset() + worldIn.rand.nextDouble() * 0.02D);
				worldIn.addEntity(itementity);
				
				
				//List<ItemStack> itemStack = this.generateLoot(lootTable, player);
				
				//ItemEntity itementity1 = new ItemEntity(worldIn, (double) pos.getX() + 0.5D + (double) direction1.getXOffset() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction1.getZOffset() * 0.65D, new ItemStack(MSItems.CLIENT_DISK, 1));
				//itementity1.setMotion(0.05D * (double) direction1.getXOffset() + worldIn.rand.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction1.getZOffset() + worldIn.rand.nextDouble() * 0.02D);
				//worldIn.addEntity(itementity1);
				//worldIn.setBlockState(pos, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getDefaultState(), 11);
				//worldIn.setBlockState(pos, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getDefaultState().with(LotusTimeCapsuleBlock.UNACTIVATED, false), 11);
			}
			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.FAIL;
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
	{
		if(state.getBlock() != newState.getBlock())
		{
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
	{
		super.randomTick(state, worldIn, pos, random);
		if (random.nextFloat() >= 0.98F) {
			//worldIn.setBlockState(pos, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getDefaultState().with(LotusTimeCapsuleBlock.UNACTIVATED, true), 11);
			worldIn.setBlockState(pos.up(1), MSBlocks.MINI_FROG_STATUE.getDefaultState());
		}
	}
}