package com.mraof.minestuck.world.gen.feature.structure;

import com.mojang.datafixers.Dynamic;
import com.mraof.minestuck.Minestuck;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class FrogTempleStructure extends ScatteredStructure<NoFeatureConfig>
{
	public FrogTempleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
	{
		super(configFactory);
	}
	
	@Override
	protected int getSeedModifier()
	{
		return 41361201;
	}
	
	@Override
	public IStartFactory getStartFactory()
	{
		return Start::new;
	}
	
	@Override
	public String getStructureName()
	{
		return Minestuck.MOD_ID + ":frog_temple";
	}
	
	@Override
	public int getSize()
	{
		return 16;
	}
	
	@Override
	protected int getBiomeFeatureDistance(ChunkGenerator<?> generator)
	{
		return 128;
	}
	
	@Override
	protected int getBiomeFeatureSeparation(ChunkGenerator<?> generator)
	{
		return 92;
	}
	
	public static class Start extends StructureStart
	{
		private Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, boundingBox, reference, seed);
		}
		
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn)
		{
			FrogTemplePiece piece = new FrogTemplePiece(rand, chunkX * 16 + rand.nextInt(16), chunkZ * 16 + rand.nextInt(16), 0.5F);
			components.add(piece);
			recalculateStructureSize();
		}
	}
}