package com.mraof.minestuck.entry;

import com.mraof.minestuck.util.MSNBTUtil;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a task for updating blocks copied over into the entry.
 * To reduce time, and still reduce lightning and "floating" liquids,
 * this was created to handle such tasks during the ticks right after entry instead of during entry.
 */
public class PostEntryTask
{
	private static final Logger LOGGER = LogManager.getLogger();
	/**
	 * The maximum amount of time (in milliseconds) to spend updating blocks,
	 * before leaving the rest for the next game tick.
	 */
	static final long MIN_TIME = 20;
	
	private final DimensionType dimension;
	private final EntryPositioning positioning;
	private int index;
	
	PostEntryTask(DimensionType dimension, EntryPositioning positioning)
	{
		this.dimension = dimension;
		this.positioning = positioning;
		this.index = 0;
	}
	
	public PostEntryTask(CompoundNBT nbt)
	{
		this(MSNBTUtil.tryReadDimensionType(nbt, "dimension"), EntryPositioning.readNBTPostEntry(nbt));
		this.index = nbt.getInt("index");
		if(dimension == null)
			LOGGER.warn("Unable to load dimension type by name {}!", nbt.getString("dimension"));
	}
	
	public CompoundNBT write()
	{
		CompoundNBT nbt = new CompoundNBT();
		MSNBTUtil.tryWriteDimensionType(nbt, "dimension", dimension);
		nbt.putInt("index", index);
		positioning.writeToNBTPostEntry(nbt);
		
		return nbt;
	}
	
	public boolean onTick(MinecraftServer server)
	{
		if(isDone())
			return false;
		
		ServerWorld world = dimension != null ? server.getWorld(dimension) : null;
		
		if(world == null)
		{
			LOGGER.error("Couldn't find world for dimension {} when performing post entry preparations! Cancelling task.", dimension);
			setDone();
			return true;
		}
		
		int preIndex = index;
		index = positioning.updateBlocksPostEntry(world, index);
		if(isDone())
			LOGGER.info("Completed entry block updates for dimension {}.", dimension.getRegistryName());
		else
			LOGGER.debug("Updated {} blocks this tick.", index - preIndex);
		
		return index != preIndex;
	}
	
	public boolean isDone()
	{
		return index == -1;
	}
	
	private void setDone()
	{
		index = -1;
	}
	
	static void updateBlock(BlockPos pos, ServerWorld world, boolean blockUpdate)
	{
		if(blockUpdate)
			world.notifyNeighborsOfStateChange(pos, world.getBlockState(pos).getBlock());
		world.getChunkProvider().getLightManager().checkBlock(pos);
		IChunk chunk = world.getChunk(pos);
		BlockState state = chunk.getBlockState(pos);
		int x = pos.getX() & 15, y = pos.getY(), z = pos.getZ() & 15;
		chunk.getHeightmap(Heightmap.Type.MOTION_BLOCKING).update(x, y, z, state);
		chunk.getHeightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).update(x, y, z, state);
		chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR).update(x, y, z, state);
		chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE).update(x, y, z, state);
	}
}