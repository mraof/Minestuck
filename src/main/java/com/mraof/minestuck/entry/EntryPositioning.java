package com.mraof.minestuck.entry;

import com.mraof.minestuck.MinestuckConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface EntryPositioning
{
	BlockPos getTeleportOffset();
	
	// The provided consumer should return true if it succeeds and should continue,
	// or false if it failed and should stop iterating
	boolean forEachBlockTry(Predicate<BlockPos> consumer);
	
	boolean forEachXZTry(Predicate<BlockPos> consumer);
	
	default void forEachBlock(Consumer<BlockPos> consumer)
	{
		forEachBlockTry(pos -> {consumer.accept(pos); return true;});
	}
	
	default void forEachXZ(Consumer<BlockPos> consumer)
	{
		forEachXZTry(pos -> {consumer.accept(pos); return true;});
	}
	
	List<Entity> getOtherEntitiesToTeleport(ServerPlayerEntity playerToExclude, ServerWorld originWorld);
	
	int updateBlocksPostEntry(ServerWorld world, int index);
	
	void writeToNBTPostEntry(CompoundNBT nbt);
	
	static EntryPositioning readNBTPostEntry(CompoundNBT nbt)
	{
		byte type = nbt.getByte("entryType");
		BlockPos center = new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
		int range = nbt.getInt("entrySize");
		return new Default(center, range);
	}
	
	class Default implements EntryPositioning
	{
		private final int artifactRange;
		private final BlockPos center, destCenter;
		private final int topY;
		
		Default(BlockPos center, ServerWorld world)
		{
			artifactRange = MinestuckConfig.SERVER.artifactRange.get();
			this.center = center;
			
			topY = MinestuckConfig.SERVER.adaptEntryBlockHeight.get() ? getTopHeight(world) : center.getY() + artifactRange;
			destCenter = new BlockPos(0, 127 - (topY - center.getY()), 0);
		}
		
		private Default(BlockPos destCenter, int artifactRange)
		{
			this.artifactRange = artifactRange;
			this.destCenter = destCenter;
			//These aren't needed post-entry, so the value doesn't matter
			center = BlockPos.ZERO;
			topY = 0;
		}
		
		/**
		 * Gives the Y-value of the highest non-air block within artifact range of the coordinates provided in the given world.
		 */
		private int getTopHeight(ServerWorld world)
		{
			AtomicInteger maxY = new AtomicInteger(center.getY());
			forEachXZ(pos ->
			{
				int height = (int) Math.sqrt(artifactRange * artifactRange - (((pos.getX() - center.getX()) * (pos.getX() - center.getX())
						+ (pos.getZ() - center.getZ()) * (pos.getZ() - center.getZ())) / 2F));
				for(int blockY = Math.min(255, center.getY() + height); blockY > maxY.get(); blockY--)
				{
					((BlockPos.Mutable) pos).setY(blockY);
					if(!world.isAirBlock(pos))
					{
						maxY.set(blockY);
						break;
					}
				}
			});
			
			return maxY.get();
		}
		
		@Override
		public BlockPos getTeleportOffset()
		{
			return destCenter.subtract(center);
		}
		
		@Override
		public boolean forEachBlockTry(Predicate<BlockPos> consumer)
		{
			return forEachXZTry(pos ->
			{
				int height = (int) Math.sqrt(artifactRange * artifactRange - (((pos.getX() - center.getX()) * (pos.getX() - center.getX())
						+ (pos.getZ() - center.getZ()) * (pos.getZ() - center.getZ())) / 2F));
				int maxY = Math.min(topY, center.getY() + height);
				for(int blockY = Math.max(0, center.getY() - height); blockY <= maxY; blockY++)
				{
					((BlockPos.Mutable) pos).setY(blockY);
					if(!consumer.test(pos))
						return false;
				}
				return true;
			});
		}
		
		@Override
		public boolean forEachXZTry(Predicate<BlockPos> consumer)
		{
			BlockPos.Mutable pos = new BlockPos.Mutable(center);
			for(int blockX = center.getX() - artifactRange; blockX <= center.getX() + artifactRange; blockX++)
			{
				pos.setX(blockX);
				int zWidth = (int) Math.sqrt(artifactRange * artifactRange - (blockX - center.getX()) * (blockX - center.getX()));
				for(int blockZ = center.getZ() - zWidth; blockZ <= center.getZ() + zWidth; blockZ++)
				{
					pos.setZ(blockZ);
					if(!consumer.test(pos))
						return false;
				}
			}
			return true;
		}
		
		@Override
		public List<Entity> getOtherEntitiesToTeleport(ServerPlayerEntity playerToExclude, ServerWorld originWorld)
		{
			return originWorld.getEntitiesInAABBexcluding(playerToExclude, getBoundingBoxForEntities(),
					EntityPredicates.NOT_SPECTATING.and(this::isEntityInRange));
		}
		
		private boolean isEntityInRange(Entity entity)
		{
			return center.distanceSq(entity.getPositionVec(), true) <= artifactRange*artifactRange;
		}
		
		private AxisAlignedBB getBoundingBoxForEntities()
		{
			return new AxisAlignedBB(center.getX() + 0.5 - artifactRange, center.getY() + 0.5 - artifactRange,
					center.getZ() + 0.5 - artifactRange, center.getX() + 0.5 + artifactRange,
					center.getY() + 0.5 + artifactRange, center.getZ() + 0.5 + artifactRange);
		}
		
		@Override
		public int updateBlocksPostEntry(ServerWorld world, int index)
		{
			long time = System.currentTimeMillis() + PostEntryTask.MIN_TIME;
			int i = 0;
			BlockPos.Mutable pos = new BlockPos.Mutable();
			for(int blockX = destCenter.getX() - artifactRange; blockX <= destCenter.getX() + artifactRange; blockX++)
			{
				int zWidth = (int) Math.sqrt(artifactRange * artifactRange - (blockX - destCenter.getX()) * (blockX - destCenter.getX()));
				for(int blockZ = destCenter.getZ() - zWidth; blockZ <= destCenter.getZ() + zWidth; blockZ++)
				{
					int height = (int) Math.sqrt(artifactRange * artifactRange - (((blockX - destCenter.getX()) * (blockX - destCenter.getX())
							+ (blockZ - destCenter.getZ()) * (blockZ - destCenter.getZ())) / 2F));
					if(blockX == destCenter.getX() - artifactRange || blockX == destCenter.getX() + artifactRange || blockZ == destCenter.getZ() - zWidth || blockZ == destCenter.getZ() + zWidth)
					{
						for(int blockY = destCenter.getY() - height; blockY <= Math.min(128, destCenter.getY() + height); blockY++)
							if(index <= i++)
								PostEntryTask.updateBlock(pos.setPos(blockX, blockY, blockZ), world, true);
					} else
					{
						if(index <= i++)
							PostEntryTask.updateBlock(pos.setPos(blockX, destCenter.getY() - height, blockZ), world, true);
						for(int blockY = destCenter.getY() - height + 1; blockY < Math.min(128, destCenter.getY() + height); blockY++)
							if(index <= i++)
								PostEntryTask.updateBlock(pos.setPos(blockX, blockY, blockZ), world, false);
						if(index <= i++)
							PostEntryTask.updateBlock(pos.setPos(blockX, Math.min(128, destCenter.getY() + height), blockZ), world, true);
					}
					if(time <= System.currentTimeMillis())
						return Math.max(i, index);
				}
			}
			return -1;
		}
		
		@Override
		public void writeToNBTPostEntry(CompoundNBT nbt)
		{
			nbt.putInt("x", destCenter.getX());
			nbt.putInt("y", destCenter.getY());
			nbt.putInt("z", destCenter.getZ());
			nbt.putInt("entrySize", artifactRange);
			nbt.putByte("entryType", (byte) 0);
		}
	}
}