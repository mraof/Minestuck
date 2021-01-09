package com.mraof.minestuck.entry;

import com.mraof.minestuck.MinestuckConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface EntryPositioning
{
	
	// The provided consumer should return true if it succeeds and should continue,
	// or false if it failed and should stop iterating
	boolean forEachBlockTry(Predicate<BlockPos> consumer);
	
	boolean forEachXZTry(Predicate<BlockPos> consumer);
	
	default void forEachBlock(Consumer<BlockPos> consumer)
	{
		forEachBlockTry(pos -> {consumer.accept(pos); return true;});
	}
	
	List<Entity> getOtherEntitiesToTeleport(ServerPlayerEntity playerToExclude, ServerWorld originWorld);
	
	class Default implements EntryPositioning
	{
		private final int artifactRange = MinestuckConfig.SERVER.artifactRange.get();
		private final BlockPos center;
		
		Default(BlockPos center)
		{
			this.center = center;
		}
		
		@Override
		public boolean forEachBlockTry(Predicate<BlockPos> consumer)
		{
			return forEachXZTry(pos ->
			{
				int height = (int) Math.sqrt(artifactRange * artifactRange - (((pos.getX() - center.getX()) * (pos.getX() - center.getX())
						+ (pos.getZ() - center.getZ()) * (pos.getZ() - center.getZ())) / 2F));
				for(int blockY = Math.max(0, center.getY() - height); blockY <= Math.min(255, center.getY() + height); blockY++)
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
	}
}