package com.mraof.minestuck.entry;

import com.mraof.minestuck.MinestuckConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public interface EntryPositioning
{
	
	List<Entity> getOtherEntitiesToTeleport(ServerPlayerEntity playerToExclude, ServerWorld originWorld);
	
	class Default implements EntryPositioning
	{
		private final int artifactRange = MinestuckConfig.SERVER.artifactRange.get();
		private final BlockPos center;
		
		public Default(BlockPos center)
		{
			this.center = center;
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