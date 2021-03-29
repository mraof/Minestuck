package com.mraof.minestuck.world.gen.feature;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.world.gen.feature.structure.FrogTempleStructure;
import com.mraof.minestuck.world.gen.feature.structure.GateStructure;
import com.mraof.minestuck.world.gen.feature.structure.ImpDungeonStructure;
import com.mraof.minestuck.world.gen.feature.structure.SmallRuinStructure;
import com.mraof.minestuck.world.gen.feature.structure.castle.CastleStructure;
import com.mraof.minestuck.world.gen.feature.structure.village.ConsortVillageStructure;
import com.mraof.minestuck.world.gen.feature.tree.EndTreeFeature;
import com.mraof.minestuck.world.gen.feature.tree.LeaflessTreeFeature;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;

@ObjectHolder(Minestuck.MOD_ID)
@Mod.EventBusSubscriber(modid = Minestuck.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MSFeatures
{
	public static final GateStructure LAND_GATE = getNull();
	public static final Structure<NoFeatureConfig> FROG_TEMPLE = getNull();
	public static final Structure<NoFeatureConfig> SMALL_RUIN = getNull();
	public static final Structure<NoFeatureConfig> IMP_DUNGEON = getNull();
	public static final Structure<NoFeatureConfig> CONSORT_VILLAGE = getNull();
	public static final Structure<NoFeatureConfig> SKAIA_CASTLE = getNull();
	
	public static final Feature<TreeFeatureConfig> END_TREE = getNull();
	public static final Feature<BlockStateFeatureConfig> LEAFLESS_TREE = getNull();
	
	public static final Feature<NoFeatureConfig> RETURN_NODE = getNull();
	public static final Feature<NoFeatureConfig> FIRE_FIELD = getNull();
	public static final Feature<ProbabilityConfig> CAKE = getNull();
	public static final Feature<BlockStateFeatureConfig> PILLAR = getNull();
	public static final Feature<BlockStateFeatureConfig> LARGE_PILLAR = getNull();
	public static final Feature<BlockBlobConfig> BLOCK_BLOB = getNull();
	public static final Feature<RandomRockBlockBlobConfig> RANDOM_ROCK_BLOCK_BLOB = getNull();
	public static final Feature<SphereReplaceConfig> SURFACE_DISK = getNull();
	public static final Feature<SphereReplaceConfig> GRASSY_SURFACE_DISK = getNull();
	public static final Feature<NoFeatureConfig> OCEAN_RUNDOWN = getNull();
	public static final Feature<NoFeatureConfig> RABBIT_PLACEMENT = getNull();
	
	public static final Feature<NoFeatureConfig> SMALL_LIBRARY = getNull();
	public static final Feature<NoFeatureConfig> CAKE_PEDESTAL = getNull();
	public static final Feature<NoFeatureConfig> COG = getNull();
	public static final Feature<NoFeatureConfig> FLOOR_COG = getNull();
	public static final Feature<NoFeatureConfig> OASIS = getNull();
	public static final Feature<NoFeatureConfig> MESA = getNull();
	public static final Feature<NoFeatureConfig> ROCK_SPIKE = getNull();
	public static final Feature<NoFeatureConfig> PARCEL_PYXIS = getNull();
	public static final Feature<NoFeatureConfig> SURFACE_FOSSIL = getNull();
	public static final Feature<NoFeatureConfig> BUCKET = getNull();
	public static final Feature<NoFeatureConfig> BROKEN_SWORD = getNull();
	public static final Feature<NoFeatureConfig> TOWER = getNull();
	public static final Feature<BlockStateFeatureConfig> STONE_MOUND = getNull();
	
	@Nonnull
	@SuppressWarnings("ConstantConditions")
	private static <T> T getNull()
	{
		return null;
	}
	
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
		IForgeRegistry<Feature<?>> registry = event.getRegistry();
		
		registry.register(new GateStructure(NoFeatureConfig::deserialize).setRegistryName("land_gate"));
		registry.register(new FrogTempleStructure(NoFeatureConfig::deserialize).setRegistryName("frog_temple"));
		registry.register(new SmallRuinStructure(NoFeatureConfig::deserialize).setRegistryName("small_ruin"));
		registry.register(new ImpDungeonStructure(NoFeatureConfig::deserialize).setRegistryName("imp_dungeon"));
		registry.register(new ConsortVillageStructure(NoFeatureConfig::deserialize).setRegistryName("consort_village"));
		registry.register(new CastleStructure(NoFeatureConfig::deserialize).setRegistryName("skaia_castle"));
		
		registry.register(new EndTreeFeature(TreeFeatureConfig::deserializeFoliage).setRegistryName("end_tree"));
		registry.register(new LeaflessTreeFeature(BlockStateFeatureConfig::deserialize).setRegistryName("leafless_tree"));
		
		registry.register(new ReturnNodeFeature(NoFeatureConfig::deserialize).setRegistryName("return_node"));
		registry.register(new FireFieldFeature(NoFeatureConfig::deserialize).setRegistryName("fire_field"));
		registry.register(new CakeFeature(ProbabilityConfig::deserialize).setRegistryName("cake"));
		registry.register(new PillarFeature(BlockStateFeatureConfig::deserialize, false).setRegistryName("pillar"));
		registry.register(new PillarFeature(BlockStateFeatureConfig::deserialize, true).setRegistryName("large_pillar"));
		registry.register(new ConditionFreeBlobFeature(BlockBlobConfig::deserialize).setRegistryName("block_blob"));
		registry.register(new RandomRockConditionFreeBlobFeature(RandomRockBlockBlobConfig::deserialize).setRegistryName("random_rock_block_blob"));
		registry.register(new SurfaceDiskFeature(SphereReplaceConfig::deserialize, false).setRegistryName("surface_disk"));
		registry.register(new SurfaceDiskFeature(SphereReplaceConfig::deserialize, true).setRegistryName("grassy_surface_disk"));
		registry.register(new OceanRundownFeature(NoFeatureConfig::deserialize).setRegistryName("ocean_rundown"));
		registry.register(new RabbitPlacementFeature(NoFeatureConfig::deserialize).setRegistryName("rabbit_placement"));
		
		registry.register(new SmallLibraryFeature(NoFeatureConfig::deserialize).setRegistryName("small_library"));
		registry.register(new CakePedestalFeature(NoFeatureConfig::deserialize).setRegistryName("cake_pedestal"));
		registry.register(new CogFeature(NoFeatureConfig::deserialize).setRegistryName("cog"));
		registry.register(new FloorCogFeature(NoFeatureConfig::deserialize).setRegistryName("floor_cog"));
		registry.register(new OasisFeature(NoFeatureConfig::deserialize).setRegistryName("oasis"));
		registry.register(new MesaFeature(NoFeatureConfig::deserialize).setRegistryName("mesa"));
		registry.register(new RockSpikeFeature(NoFeatureConfig::deserialize).setRegistryName("rock_spike"));
		registry.register(new ParcelPyxisFeature(NoFeatureConfig::deserialize).setRegistryName("parcel_pyxis"));
		registry.register(new SurfaceFossilsFeature(NoFeatureConfig::deserialize).setRegistryName("surface_fossil"));
		registry.register(new BucketFeature(NoFeatureConfig::deserialize).setRegistryName("bucket"));
		registry.register(new BrokenSwordFeature(NoFeatureConfig::deserialize).setRegistryName("broken_sword"));
		registry.register(new TowerFeature(NoFeatureConfig::deserialize).setRegistryName("tower"));
		registry.register(new StoneMoundFeature(BlockStateFeatureConfig::deserialize).setRegistryName("stone_mound"));
		
		MSStructurePieces.init();
		MSStructureProcessorTypes.init();
	}
}