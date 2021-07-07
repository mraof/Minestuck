package com.mraof.minestuck.world.gen.feature.structure;

import com.mraof.minestuck.block.DecorBlock;
import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.block.MSDirectionalBlock;
import com.mraof.minestuck.entity.MSEntityTypes;
import com.mraof.minestuck.entity.LotusFlowerEntity;
import com.mraof.minestuck.world.gen.feature.MSStructurePieces;
import com.mraof.minestuck.world.gen.feature.structure.blocks.StructureBlockUtil;
import com.mraof.minestuck.world.storage.loot.MSLootTables;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.state.properties.Half;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class FrogTemplePiece extends ScatteredStructurePiece
{
	private boolean createRan = false;
	private static final FrogTemplePiece.Selector HIEROGLYPHS = new FrogTemplePiece.Selector();
	
	public FrogTemplePiece(Random random, int minX, int minZ, float skyLight)
	{
		super(MSStructurePieces.FROG_TEMPLE, random, minX, 64, minZ, 140, 100, 140);
	}
	
	public FrogTemplePiece(TemplateManager templates, CompoundNBT nbt)
	{
		super(MSStructurePieces.FROG_TEMPLE, nbt);
	}
	
	@Override
	public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox boundingBoxIn, ChunkPos chunkPosIn)
	{
		if(!isInsideBounds(worldIn, boundingBoxIn, 0))
			return false;
		
		BlockState wallBlock = MSBlocks.GREEN_STONE_BRICKS.getDefaultState();
		BlockState columnBlock = MSBlocks.GREEN_STONE_COLUMN.getDefaultState().with(MSDirectionalBlock.FACING, Direction.UP);
		BlockState floorBlock = MSBlocks.POLISHED_GREEN_STONE.getDefaultState();
		BlockState stoneBlock = MSBlocks.GREEN_STONE.getDefaultState();
		
		//Debug.debugf("create function has run");
		for(int a = 0; a < 7; a++)
		{
			buildMainPlatform(wallBlock, worldIn, randomIn, boundingBoxIn, a);
		}
		
		buildStairsAndUnderneath(wallBlock, worldIn, boundingBoxIn);
		buildWallsAndFloors(floorBlock, worldIn, boundingBoxIn, randomIn);
		carveRooms(worldIn, boundingBoxIn);
		buildIndoorBlocks(columnBlock, worldIn, boundingBoxIn);
		generateLoot(worldIn, boundingBoxIn, randomIn, chunkPosIn);
		buildFrog(stoneBlock, worldIn, boundingBoxIn);
		//buildPillars(columnBlock, worldIn, boundingBoxIn, randomIn);
		
		return true;
	}
	
	private void generateLoot(IWorld worldIn, MutableBoundingBox boundingBoxIn, Random randomIn, ChunkPos chunkPos)
	{
		
		//TODO There is only a percent chance of the rotateYCCW applying correctly
		//setBlockState(worldIn, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getMainBlock().getDefaultState().with(LotusTimeCapsuleBlock.FACING, /*Direction.NORTH*/getCoordBaseMode()), 21 + 20, 49, 23 + 38 + 20, boundingBox);
		//modifiedDirection = modifiedDirection.rotateYCCW();
		//setBlockState(worldIn, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getMainBlock().getDefaultState().with(LotusTimeCapsuleBlock.FACING, getCoordBaseMode().rotateY()), 21 + 20, 49, 22 + 38 + 20, boundingBox); //east
		//modifiedDirection = modifiedDirection.rotateYCCW();
		//setBlockState(worldIn, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getMainBlock().getDefaultState().with(LotusTimeCapsuleBlock.FACING, getCoordBaseMode().getOpposite()), 20 + 20, 49, 22 + 38 + 20, boundingBox); //south
		//modifiedDirection = modifiedDirection.rotateYCCW();
		//setBlockState(worldIn, MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.getMainBlock().getDefaultState().with(LotusTimeCapsuleBlock.FACING, getCoordBaseMode().rotateYCCW()), 20 + 20, 49, 23 + 38 + 20, boundingBox); //west
		BlockPos lotusBlockPos = new BlockPos(this.getXWithOffset(20 + 20, 21 + 38 + 20), this.getYWithOffset(49), this.getZWithOffset(20 + 20, 21 + 38 + 20)); //TODO temples whose main entrance opens south(and presumedly west) generate one block closer to entrance;
		
		if(this.getRotation() == Rotation.NONE || this.getRotation() == Rotation.CLOCKWISE_180)
		{
			MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.placeWithRotation(worldIn, lotusBlockPos, getRotation());
		} else if(this.getRotation() == Rotation.CLOCKWISE_90 || this.getRotation() == Rotation.COUNTERCLOCKWISE_90)
		{
			lotusBlockPos = new BlockPos(this.getXWithOffset(20 + 20, 20 + 38 + 20), this.getYWithOffset(49), this.getZWithOffset(20 + 20, 20 + 38 + 20)); //TODO temples whose main entrance opens south(and presumedly west) generate one block closer to entrance
			MSBlocks.LOTUS_TIME_CAPSULE_BLOCK.placeWithRotation(worldIn, lotusBlockPos, getRotation());
		}
		
		/*BlockPos chestPos = new BlockPos(this.getXWithOffset(20 + 20, 12 + 38 + 20), this.getYWithOffset(17), this.getZWithOffset(20 + 20, 12 + 38 + 20));
		StructureBlockUtil.placeLootChest(chestPos, worldIn, boundingBoxIn, getCoordBaseMode(), ChestType.LEFT, MSLootTables.FROG_TEMPLE_CHEST, randomIn);
		chestPos = new BlockPos(this.getXWithOffset(19 + 20, 12 + 38 + 20), this.getYWithOffset(17), this.getZWithOffset(19 + 20, 12 + 38 + 20));
		StructureBlockUtil.placeLootChest(chestPos, worldIn, boundingBoxIn, getCoordBaseMode(), ChestType.RIGHT, MSLootTables.FROG_TEMPLE_CHEST, randomIn);
		*/
		BlockPos chestFarPos = new BlockPos(this.getXWithOffset(21 + 20, 12 + 38 + 20), this.getYWithOffset(21), this.getZWithOffset(21 + 20, 12 + 38 + 20));
		fillWithBlocks(worldIn, boundingBoxIn, 20 + 20, 20, 12 + 38 + 20, 21 + 20, 20, 12 + 38 + 20, MSBlocks.GREEN_STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, this.getCoordBaseMode().getOpposite()).with(StairsBlock.HALF, Half.TOP), MSBlocks.GREEN_STONE_BRICK_STAIRS.getDefaultState(), false);
		StructureBlockUtil.placeLootChest(chestFarPos, worldIn, boundingBoxIn, getCoordBaseMode(), ChestType.LEFT, MSLootTables.FROG_TEMPLE_CHEST, randomIn);
		chestFarPos = new BlockPos(this.getXWithOffset(20 + 20, 12 + 38 + 20), this.getYWithOffset(21), this.getZWithOffset(20 + 20, 12 + 38 + 20));
		StructureBlockUtil.placeLootChest(chestFarPos, worldIn, boundingBoxIn, getCoordBaseMode(), ChestType.RIGHT, MSLootTables.FROG_TEMPLE_CHEST, randomIn);
		
		BlockPos chestNearDoorPos = new BlockPos(this.getXWithOffset(11 + 20, 29 + 38 + 20), this.getYWithOffset(21), this.getZWithOffset(11 + 20, 29 + 38 + 20));
		fillWithBlocks(worldIn, boundingBoxIn, 10 + 20, 20, 29 + 38 + 20, 11 + 20, 20, 29 + 38 + 20, MSBlocks.GREEN_STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, this.getCoordBaseMode()).with(StairsBlock.HALF, Half.TOP), MSBlocks.GREEN_STONE_BRICK_STAIRS.getDefaultState(), false);
		StructureBlockUtil.placeLootChest(chestNearDoorPos, worldIn, boundingBoxIn, getCoordBaseMode().getOpposite(), ChestType.LEFT, MSLootTables.FROG_TEMPLE_CHEST, randomIn);
		chestNearDoorPos = new BlockPos(this.getXWithOffset(10 + 20, 29 + 38 + 20), this.getYWithOffset(21), this.getZWithOffset(10 + 20, 29 + 38 + 20));
		StructureBlockUtil.placeLootChest(chestNearDoorPos, worldIn, boundingBoxIn, getCoordBaseMode().getOpposite(), ChestType.RIGHT, MSLootTables.FROG_TEMPLE_CHEST, randomIn);
		
		//TODO Everything seems to generate several times over, first noticed with lotus flower entity, find better fix than below
		if(!createRan)
		{
			LotusFlowerEntity lotusFlowerEntity = MSEntityTypes.LOTUS_FLOWER.create(worldIn.getWorld());
			if(lotusFlowerEntity == null)
				throw new IllegalStateException("Unable to create a new lotus flower. Entity factory returned null!");
			lotusFlowerEntity.setLocationAndAngles(this.getXWithOffset(21 + 20, 21 + 38 + 20), this.getYWithOffset(50), this.getZWithOffset(21 + 20, 21 + 38 + 20), 0F, 0);
			//lotusFlowerEntity.setLocationAndAngles(lotusBlockPos.getX(), this.getYWithOffset(50), lotusBlockPos.getZ(), 0F, 0);
			worldIn.addEntity(lotusFlowerEntity);
			createRan = true;
		}
	}
	
	private void buildMainPlatform(BlockState block, IWorld world, Random rand, MutableBoundingBox boundingBox, int a)
	{
		fillWithBlocks(world, boundingBox, a * 2 + 20, 8 * a, a * 2 + 38 + 20, (int) (40 * (1F - a / 20F)) + 1 + 20, (8 * a) + 8, (int) (40 * (1F - a / 20F)) + 1 + 38 + 20, block, block, false);
	}
	
	private void buildStairsAndUnderneath(BlockState block, IWorld world, MutableBoundingBox boundingBox)
	{
		int pushUp = 0;
		for(int i = 0; i < 24; i++)
		{
			fillWithBlocks(world, boundingBox, 17 + 20, pushUp, i + 20 + 24, 24 + 20, pushUp, i + 20 + 24, MSBlocks.STEEP_GREEN_STONE_BRICK_STAIRS_BASE.getDefaultState().with(DecorBlock.FACING, this.getCoordBaseMode().getOpposite()), MSBlocks.STEEP_GREEN_STONE_BRICK_STAIRS_BASE.getDefaultState().with(DecorBlock.FACING, this.getCoordBaseMode().getOpposite()), false); //stairs base
			fillWithBlocks(world, boundingBox, 17 + 20, pushUp + 1, i + 20 + 24, 24 + 20, pushUp + 1, i + 20 + 24, MSBlocks.STEEP_GREEN_STONE_BRICK_STAIRS_TOP.getDefaultState().with(DecorBlock.FACING, this.getCoordBaseMode().getOpposite()), MSBlocks.STEEP_GREEN_STONE_BRICK_STAIRS_TOP.getDefaultState().with(DecorBlock.FACING, this.getCoordBaseMode().getOpposite()), false); //stairs top
			fillWithBlocks(world, boundingBox, 17 + 20, pushUp, i + 20 + 1 + 24, 24 + 20, pushUp, 50 + 20, MSBlocks.GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.GREEN_STONE_BRICKS.getDefaultState(), false); //stairs base fill in
			fillWithBlocks(world, boundingBox, 17 + 20, pushUp + 1, i + 20 + 1 + 24, 24 + 20, pushUp + 1, 50 + 20, MSBlocks.GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.GREEN_STONE_BRICKS.getDefaultState(), false); //stairs top fill in
			pushUp = pushUp + 2;
		}
		
		fillWithBlocks(world, boundingBox, 17 + 20, 48, 24 + 20 + 24, 24 + 20, 48, 24 + 20 + 24, MSBlocks.STEEP_GREEN_STONE_BRICK_STAIRS_BASE.getDefaultState().with(DecorBlock.FACING, this.getCoordBaseMode().getOpposite()), MSBlocks.STEEP_GREEN_STONE_BRICK_STAIRS_BASE.getDefaultState().with(DecorBlock.FACING, this.getCoordBaseMode().getOpposite()), false); //stairs base at top
		fillWithBlocks(world, boundingBox, 17 + 20, -10, 20 + 24, 24 + 20, 0, 24 + 20 + 24, MSBlocks.GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.GREEN_STONE_BRICKS.getDefaultState(), false); //underneath stairs
		
		fillWithBlocks(world, boundingBox, 20, -10, 38 + 20, 41 + 20, 0, 79 + 20, block, block, false); //underneath main platform
		for(int i = 0; i < 28; i++)
		{
			fillWithBlocks(world, boundingBox, i + 20, -i - 10, i + 38 + 20, 41 - i + 20, -i - 10, 79 - i + 20, block, block, false); //underneath main platform
		}
	}
	
	private void buildWallsAndFloors(BlockState block, IWorld world, MutableBoundingBox boundingBox, Random rand)
	{
		fillWithBlocks(world, boundingBox, 14 + 20, 48, 51 + 20, 27 + 20, 48, 65 + 20, block, block, false); //lotus room floor
		fillWithRandomizedBlocks(world, boundingBox, 13 + 20, 50, 51 + 20, 28 + 20, 54, 66 + 20, true, rand, HIEROGLYPHS); //lotus room hieroglyphs
		fillWithBlocks(world, boundingBox, 13 + 20, 49, 51 + 20, 28 + 20, 49, 66 + 20, MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState().with(MSDirectionalBlock.FACING, Direction.UP), MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState(), false); //lotus room trim floor
		fillWithBlocks(world, boundingBox, 13 + 20, 55, 51 + 20, 28 + 20, 55, 66 + 20, MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState().with(MSDirectionalBlock.FACING, Direction.DOWN), MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState(), false); //lotus room trim ceiling
		
		fillWithRandomizedBlocks(world, boundingBox, 6 + 20, 6, 44 + 20, 35 + 20, 15, 73 + 20, true, rand, HIEROGLYPHS); //lower room hieroglyphs
		fillWithBlocks(world, boundingBox, 6 + 20, 5, 44 + 20, 35 + 20, 5, 73 + 20, MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState().with(MSDirectionalBlock.FACING, Direction.UP), MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState(), false); //lotus room trim floor
		fillWithBlocks(world, boundingBox, 6 + 20, 16, 44 + 20, 35 + 20, 16, 73 + 20, MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState().with(MSDirectionalBlock.FACING, Direction.DOWN), MSBlocks.GREEN_STONE_BRICK_TRIM.getDefaultState(), false); //lotus room trim ceiling
	}
	
	private void buildIndoorBlocks(BlockState columnBlock, IWorld world, MutableBoundingBox boundingBox)
	{
		//lower room columns
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				fillWithBlocks(world, boundingBox, 10 + 20 + i * 10, 5, 48 + 20 + j * 10, 11 + 20 + i * 10, 23, 49 + 20 + j * 10, columnBlock.with(MSDirectionalBlock.FACING, Direction.UP), columnBlock.with(MSDirectionalBlock.FACING, Direction.UP), false);
				fillWithBlocks(world, boundingBox, 8 + 20 + i * 10, 16, 46 + 20 + j * 10, 13 + 20 + i * 10, 16, 51 + 20 + j * 10, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //platform around columns
			}
		}
		
		//lower room stepping stones
		fillWithBlocks(world, boundingBox, 24 + 20, 16, 68 + 20, 24 + 20, 16, 69 + 20, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //first step
		fillWithBlocks(world, boundingBox, 30 + 20, 16, 65 + 20, 31 + 20, 16, 65 + 20, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //second step
		fillWithBlocks(world, boundingBox, 27 + 20, 16, 58 + 20, 27 + 20, 16, 59 + 20, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //third step
		fillWithBlocks(world, boundingBox, 17 + 20, 16, 58 + 20, 17 + 20, 16, 59 + 20, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //fourth step
		fillWithBlocks(world, boundingBox, 10 + 20, 16, 55 + 20, 11 + 20, 16, 55 + 20, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //fifth step
		fillWithBlocks(world, boundingBox, 14 + 20, 16, 48 + 20, 14 + 20, 16, 49 + 20, MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), MSBlocks.CHISELED_GREEN_STONE_BRICKS.getDefaultState(), false); //sixth step
		
		//lower room ladder
		fillWithBlocks(world, boundingBox, 20 + 20, 5, 72 + 20, 20 + 20, 16, 72 + 20, Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, getCoordBaseMode().getOpposite()), Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, getCoordBaseMode().getOpposite()), false);
		fillWithBlocks(world, boundingBox, 21 + 20, 5, 72 + 20, 21 + 20, 16, 72 + 20, Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, getCoordBaseMode().getOpposite()), Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, getCoordBaseMode().getOpposite()), false);
	}
	
	private void carveRooms(IWorld world, MutableBoundingBox boundingBox)
	{
		fillWithAir(world, boundingBox, 14 + 20, 49, 52 + 20, 27 + 20, 55, 65 + 20); //lotus room
		fillWithAir(world, boundingBox, 19 + 20, 49, 49 + 20, 22 + 20, 52, 51 + 20); //lotus room entry
		fillWithAir(world, boundingBox, 7 + 20, 5, 45 + 20, 34 + 20, 23, 72 + 20); //lower room
		fillWithAir(world, boundingBox, 19 + 20, 17, 71 + 20, 22 + 20, 20, 79 + 20); //lower room entry
	}
	
	private void buildPillars(BlockState block, IWorld world, MutableBoundingBox boundingBox, Random random) //TODO pieces of pillars often fail to generate, which is why it is not in use currently
	{
		if(random.nextBoolean())
		{
			if(random.nextBoolean())
			{
				fillWithBlocks(world, boundingBox, 19 + 20, -20, 1, 22, 30, 4, block, block, false); //front/south pillar
				fillWithBlocks(world, boundingBox, 18 + 20, 31, 0, 23 + 20, 36, 5, MSBlocks.GREEN_STONE.getDefaultState(), MSBlocks.CRUXITE_BLOCK.getDefaultState(), false);
			} else
			{
				int randReduction = random.nextInt(5);
				fillWithBlocks(world, boundingBox, 18 + 20, -20, 1, 23, 30 - randReduction, 4, block, block, false); //front/south pillar
			}
		}
		if(random.nextBoolean())
		{
			if(random.nextBoolean())
			{
				fillWithBlocks(world, boundingBox, 1, -20, 57 + 20, 4, 30, 60 + 20, block, block, false); //west pillar
				fillWithBlocks(world, boundingBox, 0, 31, 56 + 20, 5, 36, 61 + 20, MSBlocks.GREEN_STONE.getDefaultState(), MSBlocks.URANIUM_BLOCK.getDefaultState(), false);
			} else
			{
				int randReduction = random.nextInt(5);
				fillWithBlocks(world, boundingBox, 1, -20, 57 + 20, 4, 30 - randReduction, 60 + 20, block, block, false); //west pillar
			}
		}
		if(random.nextBoolean())
		{
			if(random.nextBoolean())
			{
				fillWithBlocks(world, boundingBox, 18 + 20, -20, 103 + 20, 21 + 20, 30, 108 + 20, block, block, false); //north pillar
				fillWithBlocks(world, boundingBox, 17 + 20, 31, 102 + 20, 22 + 20, 36, 109 + 20, MSBlocks.GREEN_STONE.getDefaultState(), MSBlocks.CRUXITE_BLOCK.getDefaultState(), false);
			} else
			{
				int randReduction = random.nextInt(5);
				fillWithBlocks(world, boundingBox, 18 + 20, -20, 103 + 20, 21 + 20, 30 - randReduction, 108 + 20, block, block, false); //north pillar
			}
		}
		if(random.nextBoolean())
		{
			if(random.nextBoolean())
			{
				fillWithBlocks(world, boundingBox, 67 + 20, -20, 57 + 20, 70 + 20, 30, 60 + 20, block, block, false); //east pillar
				fillWithBlocks(world, boundingBox, 66 + 20, 31, 56 + 20, 71 + 20, 36, 61 + 20, MSBlocks.GREEN_STONE.getDefaultState(), MSBlocks.URANIUM_BLOCK.getDefaultState(), false);
			} else
			{
				int randReduction = random.nextInt(5);
				fillWithBlocks(world, boundingBox, 67 + 20, -20, 57 + 20, 70 + 20, 30 - randReduction, 60 + 20, block, block, false); //east pillar
			}
		}
	}
	
	private void buildFrog(BlockState block, IWorld world, MutableBoundingBox boundingBox)
	{
		fillWithBlocks(world, boundingBox, 23 + 20, 57, 14 + 38 + 20, 27 + 20, 58, 55 + 20, block, block, false); //right front leg
		fillWithBlocks(world, boundingBox, 14 + 20, 57, 14 + 38 + 20, 18 + 20, 58, 55 + 20, block, block, false); //left front leg
		fillWithBlocks(world, boundingBox, 23 + 20, 57, 23 + 38 + 20, 27 + 20, 61, 64 + 20, block, block, false); //right back leg
		fillWithBlocks(world, boundingBox, 14 + 20, 57, 23 + 38 + 20, 18 + 20, 61, 64 + 20, block, block, false); //left back leg
		fillWithBlocks(world, boundingBox, 16 + 20, 57, 16 + 38 + 20, 25 + 20, 65, 63 + 20, block, block, false); //body
		fillWithBlocks(world, boundingBox, 15 + 20, 62, 14 + 38 + 20, 26 + 20, 67, 59 + 20, block, block, false); //head
		fillWithBlocks(world, boundingBox, 23 + 20, 65, 15 + 38 + 20, 27 + 20, 68, 57 + 20, block, block, false); //right eye
		fillWithBlocks(world, boundingBox, 14 + 20, 65, 15 + 38 + 20, 18 + 20, 68, 57 + 20, block, block, false); //left eye
	}
	
	static class Selector extends StructurePiece.BlockSelector
	{
		private Selector()
		{
		}
		
		public void selectBlocks(Random rand, int x, int y, int z, boolean wall)
		{
			int randomBlock = rand.nextInt(14);
			if(randomBlock == 12 || randomBlock == 13)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_FROG.getDefaultState();
			} else if(randomBlock == 10 || randomBlock == 11)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_TURTLE.getDefaultState();
			} else if(randomBlock == 8 || randomBlock == 9)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_SKAIA.getDefaultState();
			} else if(randomBlock == 6 || randomBlock == 7)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_LOTUS.getDefaultState();
			} else if(randomBlock == 5)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_IGUANA_LEFT.getDefaultState();
			} else if(randomBlock == 4)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_IGUANA_RIGHT.getDefaultState();
			} else if(randomBlock == 3)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_NAK_LEFT.getDefaultState();
			} else if(randomBlock == 2)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_NAK_RIGHT.getDefaultState();
			} else if(randomBlock == 1)
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_SALAMANDER_LEFT.getDefaultState();
			} else
			{
				this.blockstate = MSBlocks.GREEN_STONE_BRICK_SALAMANDER_RIGHT.getDefaultState();
			}
		}
	}
}