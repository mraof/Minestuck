package com.mraof.minestuck.world.gen;

import com.mraof.minestuck.world.biome.BiomeType;
import com.mraof.minestuck.world.gen.feature.MSFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.layer.BiomeLayer;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

public class OverworldStructureGeneration
{
	public static void setupOverworldStructureGeneration()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			if(BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD) /*&& biome != Biomes.OCEAN*/)
			{
				biome.addStructure(MSFeatures.FROG_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
				//biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, MSFeatures.FROG_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_PASSTHROUGH.configure(new ChanceConfig(70))));
				biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, MSFeatures.FROG_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
				//biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, MSFeatures.FROG_TEMPLE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.TOP_SOLID_HEIGHTMAP_RANGE.configure(new TopSolidRangeConfig(-50, -30))));
			}
		}
	}
}