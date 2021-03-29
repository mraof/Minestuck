package com.mraof.minestuck.data.loot_table;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.block.plant.TallEndGrassBlock;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.tileentity.ItemStackTileEntity;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

import static com.mraof.minestuck.block.MSBlocks.*;

public class MSBlockLootTables extends BlockLootTables
{
	private static final ILootCondition.IBuilder SILK_TOUCH_CONDITION = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
	private static final ILootCondition.IBuilder SHEAR_CONDITION = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
	private static final ILootCondition.IBuilder SILK_AND_SHEAR_CONDITION = SHEAR_CONDITION.alternative(SILK_TOUCH_CONDITION);
	private static final ILootCondition.IBuilder NO_SILK_OR_SHEAR_CONDITION = SILK_AND_SHEAR_CONDITION.inverted();
	private static final float[] SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
	
	@Override
	protected void addTables()
	{
		registerDropSelfLootTable(BLACK_CHESS_DIRT);
		registerDropSelfLootTable(WHITE_CHESS_DIRT);
		registerDropSelfLootTable(DARK_GRAY_CHESS_DIRT);
		registerDropSelfLootTable(LIGHT_GRAY_CHESS_DIRT);
		
		registerDropSelfLootTable(BLACK_CASTLE_BRICKS);
		registerDropSelfLootTable(WHITE_CASTLE_BRICKS);
		registerDropSelfLootTable(LIGHT_GRAY_CASTLE_BRICKS);
		registerDropSelfLootTable(DARK_GRAY_CASTLE_BRICKS);
		registerDropSelfLootTable(BLACK_CASTLE_BRICK_SMOOTH);
		registerDropSelfLootTable(WHITE_CASTLE_BRICK_SMOOTH);
		registerDropSelfLootTable(LIGHT_GRAY_CASTLE_BRICK_SMOOTH);
		registerDropSelfLootTable(DARK_GRAY_CASTLE_BRICK_SMOOTH);
		registerDropSelfLootTable(BLACK_CASTLE_BRICK_TRIM);
		registerDropSelfLootTable(WHITE_CASTLE_BRICK_TRIM);
		registerDropSelfLootTable(LIGHT_GRAY_CASTLE_BRICK_TRIM);
		registerDropSelfLootTable(DARK_GRAY_CASTLE_BRICK_TRIM);
		registerDropSelfLootTable(CHECKERED_STAINED_GLASS);
		registerDropSelfLootTable(BLACK_CROWN_STAINED_GLASS);
		registerDropSelfLootTable(BLACK_PAWN_STAINED_GLASS);
		registerDropSelfLootTable(WHITE_CROWN_STAINED_GLASS);
		registerDropSelfLootTable(WHITE_PAWN_STAINED_GLASS);
		
		registerLootTable(STONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(COBBLESTONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(SANDSTONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(RED_SANDSTONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(NETHERRACK_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(END_STONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(SHADE_STONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(PINK_STONE_CRUXITE_ORE, MSBlockLootTables::cruxiteOreDrop);
		registerLootTable(STONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(COBBLESTONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(SANDSTONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(RED_SANDSTONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(NETHERRACK_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(END_STONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(SHADE_STONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		registerLootTable(PINK_STONE_URANIUM_ORE, MSBlockLootTables::uraniumOreDrop);
		
		registerLootTable(NETHERRACK_COAL_ORE, MSBlockLootTables::coalOreDrop);
		registerLootTable(SHADE_STONE_COAL_ORE, MSBlockLootTables::coalOreDrop);
		registerLootTable(PINK_STONE_COAL_ORE, MSBlockLootTables::coalOreDrop);
		registerDropSelfLootTable(SANDSTONE_IRON_ORE);
		registerDropSelfLootTable(RED_SANDSTONE_IRON_ORE);
		registerDropSelfLootTable(END_STONE_IRON_ORE);
		registerDropSelfLootTable(SANDSTONE_GOLD_ORE);
		registerDropSelfLootTable(RED_SANDSTONE_GOLD_ORE);
		registerDropSelfLootTable(SHADE_STONE_GOLD_ORE);
		registerDropSelfLootTable(PINK_STONE_GOLD_ORE);
		registerLootTable(END_STONE_REDSTONE_ORE, MSBlockLootTables::redstoneOreDrop);
		registerLootTable(STONE_QUARTZ_ORE, MSBlockLootTables::quartzOreDrop);
		registerLootTable(PINK_STONE_LAPIS_ORE, MSBlockLootTables::lapisOreDrop);
		registerLootTable(PINK_STONE_DIAMOND_ORE, MSBlockLootTables::diamondOreDrop);
		
		registerDropSelfLootTable(CRUXITE_BLOCK);
		registerDropSelfLootTable(URANIUM_BLOCK);
		registerDropSelfLootTable(GENERIC_OBJECT);
		
		registerDropSelfLootTable(BLUE_DIRT);
		registerDropSelfLootTable(THOUGHT_DIRT);
		registerDropSelfLootTable(COARSE_STONE);
		registerDropSelfLootTable(CHISELED_COARSE_STONE);
		registerDropSelfLootTable(SHADE_STONE);
		registerDropSelfLootTable(SHADE_BRICKS);
		registerDropSelfLootTable(SMOOTH_SHADE_STONE);
		registerDropSelfLootTable(FROST_BRICKS);
		registerDropSelfLootTable(FROST_TILE);
		registerDropSelfLootTable(CHISELED_FROST_BRICKS);
		registerDropSelfLootTable(CAST_IRON);
		registerDropSelfLootTable(CHISELED_CAST_IRON);
		registerDropSelfLootTable(MYCELIUM_BRICKS);
		registerDropSelfLootTable(BLACK_STONE);
		registerDropSelfLootTable(FLOWERY_MOSSY_COBBLESTONE);
		registerDropSelfLootTable(FLOWERY_MOSSY_STONE_BRICKS);
		registerDropSelfLootTable(COARSE_END_STONE);
		registerLootTable(END_GRASS, MSBlockLootTables::endGrassDrop);
		registerDropSelfLootTable(CHALK);
		registerDropSelfLootTable(POLISHED_CHALK);
		registerDropSelfLootTable(CHALK_BRICKS);
		registerDropSelfLootTable(CHISELED_CHALK_BRICKS);
		registerDropSelfLootTable(PINK_STONE);
		registerDropSelfLootTable(POLISHED_PINK_STONE);
		registerDropSelfLootTable(PINK_STONE_BRICKS);
		registerDropSelfLootTable(CHISELED_PINK_STONE_BRICKS);
		registerDropSelfLootTable(CRACKED_PINK_STONE_BRICKS);
		registerDropSelfLootTable(MOSSY_PINK_STONE_BRICKS);
		registerDropSelfLootTable(BROWN_STONE);
		registerDropSelfLootTable(POLISHED_BROWN_STONE);
		registerDropSelfLootTable(BROWN_STONE_BRICKS);
		registerDropSelfLootTable(BROWN_STONE_COLUMN);
		registerDropSelfLootTable(CRACKED_BROWN_STONE_BRICKS);
		registerDropSelfLootTable(DENSE_CLOUD);
		registerDropSelfLootTable(BRIGHT_DENSE_CLOUD);
		registerDropSelfLootTable(SUGAR_CUBE);
		
		registerDropSelfLootTable(GLOWING_LOG);
		registerDropSelfLootTable(FROST_LOG);
		registerDropSelfLootTable(RAINBOW_LOG);
		registerDropSelfLootTable(END_LOG);
		registerDropSelfLootTable(VINE_LOG);
		registerDropSelfLootTable(FLOWERY_VINE_LOG);
		registerDropSelfLootTable(DEAD_LOG);
		registerDropSelfLootTable(PETRIFIED_LOG);
		registerDropSelfLootTable(GLOWING_WOOD);
		registerDropSelfLootTable(FROST_WOOD);
		registerDropSelfLootTable(RAINBOW_WOOD);
		registerDropSelfLootTable(END_WOOD);
		registerDropSelfLootTable(VINE_WOOD);
		registerDropSelfLootTable(FLOWERY_VINE_WOOD);
		registerDropSelfLootTable(DEAD_WOOD);
		registerDropSelfLootTable(PETRIFIED_WOOD);
		registerDropSelfLootTable(GLOWING_PLANKS);
		registerDropSelfLootTable(FROST_PLANKS);
		registerDropSelfLootTable(RAINBOW_PLANKS);
		registerDropSelfLootTable(END_PLANKS);
		registerDropSelfLootTable(DEAD_PLANKS);
		registerDropSelfLootTable(TREATED_PLANKS);
		registerLootTable(FROST_LEAVES, MSBlockLootTables::frostLeavesDrop);
		registerLootTable(RAINBOW_LEAVES, MSBlockLootTables::rainbowLeavesDrop);
		registerLootTable(END_LEAVES, MSBlockLootTables::endLeavesDrop);
		registerDropSelfLootTable(RAINBOW_SAPLING);
		registerDropSelfLootTable(END_SAPLING);
		
		registerDropSelfLootTable(BLOOD_ASPECT_LOG);
		registerDropSelfLootTable(BREATH_ASPECT_LOG);
		registerDropSelfLootTable(DOOM_ASPECT_LOG);
		registerDropSelfLootTable(HEART_ASPECT_LOG);
		registerDropSelfLootTable(HOPE_ASPECT_LOG);
		registerDropSelfLootTable(LIFE_ASPECT_LOG);
		registerDropSelfLootTable(LIGHT_ASPECT_LOG);
		registerDropSelfLootTable(MIND_ASPECT_LOG);
		registerDropSelfLootTable(RAGE_ASPECT_LOG);
		registerDropSelfLootTable(SPACE_ASPECT_LOG);
		registerDropSelfLootTable(TIME_ASPECT_LOG);
		registerDropSelfLootTable(VOID_ASPECT_LOG);
		registerDropSelfLootTable(BLOOD_ASPECT_PLANKS);
		registerDropSelfLootTable(BREATH_ASPECT_PLANKS);
		registerDropSelfLootTable(DOOM_ASPECT_PLANKS);
		registerDropSelfLootTable(HEART_ASPECT_PLANKS);
		registerDropSelfLootTable(HOPE_ASPECT_PLANKS);
		registerDropSelfLootTable(LIFE_ASPECT_PLANKS);
		registerDropSelfLootTable(LIGHT_ASPECT_PLANKS);
		registerDropSelfLootTable(MIND_ASPECT_PLANKS);
		registerDropSelfLootTable(RAGE_ASPECT_PLANKS);
		registerDropSelfLootTable(SPACE_ASPECT_PLANKS);
		registerDropSelfLootTable(TIME_ASPECT_PLANKS);
		registerDropSelfLootTable(VOID_ASPECT_PLANKS);
		registerLootTable(BLOOD_ASPECT_LEAVES, MSBlockLootTables::bloodAspectLeavesDrop);
		registerLootTable(BREATH_ASPECT_LEAVES, MSBlockLootTables::breathAspectLeavesDrop);
		registerLootTable(DOOM_ASPECT_LEAVES, MSBlockLootTables::doomAspectLeavesDrop);
		registerLootTable(HEART_ASPECT_LEAVES, MSBlockLootTables::heartAspectLeavesDrop);
		registerLootTable(HOPE_ASPECT_LEAVES, MSBlockLootTables::hopeAspectLeavesDrop);
		registerLootTable(LIFE_ASPECT_LEAVES, MSBlockLootTables::lifeAspectLeavesDrop);
		registerLootTable(LIGHT_ASPECT_LEAVES, MSBlockLootTables::lightAspectLeavesDrop);
		registerLootTable(MIND_ASPECT_LEAVES, MSBlockLootTables::mindAspectLeavesDrop);
		registerLootTable(RAGE_ASPECT_LEAVES, MSBlockLootTables::rageAspectLeavesDrop);
		registerLootTable(SPACE_ASPECT_LEAVES, MSBlockLootTables::spaceAspectLeavesDrop);
		registerLootTable(TIME_ASPECT_LEAVES, MSBlockLootTables::timeAspectLeavesDrop);
		registerLootTable(VOID_ASPECT_LEAVES, MSBlockLootTables::voidAspectLeavesDrop);
		registerDropSelfLootTable(BLOOD_ASPECT_SAPLING);
		registerDropSelfLootTable(BREATH_ASPECT_SAPLING);
		registerDropSelfLootTable(DOOM_ASPECT_SAPLING);
		registerDropSelfLootTable(HEART_ASPECT_SAPLING);
		registerDropSelfLootTable(HOPE_ASPECT_SAPLING);
		registerDropSelfLootTable(LIFE_ASPECT_SAPLING);
		registerDropSelfLootTable(LIGHT_ASPECT_SAPLING);
		registerDropSelfLootTable(MIND_ASPECT_SAPLING);
		registerDropSelfLootTable(RAGE_ASPECT_SAPLING);
		registerDropSelfLootTable(SPACE_ASPECT_SAPLING);
		registerDropSelfLootTable(TIME_ASPECT_SAPLING);
		registerDropSelfLootTable(VOID_ASPECT_SAPLING);
		
		registerDropSelfLootTable(GLOWING_MUSHROOM);
		registerLootTable(DESERT_BUSH, MSBlockLootTables::desertBushDrop);
		registerDropSelfLootTable(BLOOMING_CACTUS);
		registerDropSelfLootTable(PETRIFIED_GRASS);
		registerDropSelfLootTable(PETRIFIED_POPPY);
		registerDropSelfLootTable(STRAWBERRY);
		registerLootTable(ATTACHED_STRAWBERRY_STEM, func_218482_a());
		registerLootTable(STRAWBERRY_STEM, MSBlockLootTables::strawberryStemDrop);
		registerLootTable(TALL_END_GRASS, func_218482_a());
		
		registerDropSelfLootTable(GLOWY_GOOP);
		registerDropSelfLootTable(COAGULATED_BLOOD);
		registerLootTable(VEIN, func_218482_a());
		registerLootTable(VEIN_CORNER, func_218482_a());
		registerLootTable(INVERTED_VEIN_CORNER, func_218482_a());
		registerDropSelfLootTable(PIPE);
		registerDropSelfLootTable(PIPE_INTERSECTION);
		registerDropSelfLootTable(PARCEL_PYXIS);
		registerDropSelfLootTable(PYXIS_LID);
		
		registerDropSelfLootTable(COARSE_STONE_STAIRS);
		registerDropSelfLootTable(SHADE_BRICK_STAIRS);
		registerDropSelfLootTable(FROST_BRICK_STAIRS);
		registerDropSelfLootTable(CAST_IRON_STAIRS);
		registerDropSelfLootTable(MYCELIUM_BRICK_STAIRS);
		registerDropSelfLootTable(CHALK_STAIRS);
		registerDropSelfLootTable(CHALK_BRICK_STAIRS);
		registerDropSelfLootTable(PINK_STONE_BRICK_STAIRS);
		registerDropSelfLootTable(BROWN_STONE_BRICK_STAIRS);
		registerDropSelfLootTable(RAINBOW_PLANKS_STAIRS);
		registerDropSelfLootTable(END_PLANKS_STAIRS);
		registerDropSelfLootTable(DEAD_PLANKS_STAIRS);
		registerDropSelfLootTable(TREATED_PLANKS_STAIRS);
		registerDropSelfLootTable(CHALK_SLAB);
		registerDropSelfLootTable(CHALK_BRICK_SLAB);
		registerDropSelfLootTable(PINK_STONE_BRICK_SLAB);
		registerDropSelfLootTable(BROWN_STONE_BRICK_SLAB);
		registerDropSelfLootTable(RAINBOW_PLANKS_SLAB);
		registerDropSelfLootTable(END_PLANKS_SLAB);
		registerDropSelfLootTable(DEAD_PLANKS_SLAB);
		registerDropSelfLootTable(TREATED_PLANKS_SLAB);
		
		registerDropSelfLootTable(HOLOPAD);
		registerDropSelfLootTable(CRUXTRUDER_LID);
		registerLootTable(MINI_CRUXTRUDER, MSBlockLootTables::droppingWithColor);
		registerDropSelfLootTable(MINI_TOTEM_LATHE);
		registerDropSelfLootTable(MINI_ALCHEMITER);
		registerDropSelfLootTable(MINI_PUNCH_DESIGNIX);
		
		registerDropSelfLootTable(COMPUTER);
		registerDropSelfLootTable(LAPTOP);
		registerDropSelfLootTable(CROCKERTOP);
		registerDropSelfLootTable(HUBTOP);
		registerDropSelfLootTable(LUNCHTOP);
		registerDropSelfLootTable(OLD_COMPUTER);
		registerLootTable(TRANSPORTALIZER, MSBlockLootTables::droppingWithNameOnSilkTouch);
		registerLootTable(TRANS_PORTALIZER, MSBlockLootTables::droppingWithNameOnSilkTouch);
		registerDropSelfLootTable(SENDIFICATOR);
		registerDropSelfLootTable(GRIST_WIDGET);
		registerDropSelfLootTable(URANIUM_COOKER);
		
		registerLootTable(CRUXITE_DOWEL, MSBlockLootTables::droppingWithTEItem);
		
		registerDropSelfLootTable(GOLD_SEEDS);
		registerDropSelfLootTable(WOODEN_CACTUS);
		
		registerLootTable(APPLE_CAKE, func_218482_a());
		registerLootTable(BLUE_CAKE, func_218482_a());
		registerLootTable(COLD_CAKE, func_218482_a());
		registerLootTable(RED_CAKE, func_218482_a());
		registerLootTable(HOT_CAKE, func_218482_a());
		registerLootTable(REVERSE_CAKE, func_218482_a());
		registerLootTable(FUCHSIA_CAKE, func_218482_a());
		registerLootTable(NEGATIVE_CAKE, func_218482_a());
		
		registerDropSelfLootTable(PRIMED_TNT);
		registerDropSelfLootTable(UNSTABLE_TNT);
		registerDropSelfLootTable(INSTANT_TNT);
		registerDropSelfLootTable(WOODEN_EXPLOSIVE_BUTTON);
		registerDropSelfLootTable(STONE_EXPLOSIVE_BUTTON);
		
		registerDropSelfLootTable(BLENDER);
		registerDropSelfLootTable(CHESSBOARD);
		registerDropSelfLootTable(MINI_FROG_STATUE);
		registerDropSelfLootTable(MINI_WIZARD_STATUE);
		registerDropSelfLootTable(CASSETTE_PLAYER);
		//registerDropSelfLootTable(LOTUS_TIME_CAPSULE_BLOCK);
		registerDropSelfLootTable(GLOWYSTONE_DUST);
	}
	
	private static LootTable.Builder cruxiteOreDrop(Block block)
	{
		return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(MSItems.RAW_CRUXITE).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 5.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
	}
	private static LootTable.Builder uraniumOreDrop(Block block)
	{
		return droppingItemWithFortune(block, MSItems.RAW_URANIUM);
	}
	private static LootTable.Builder coalOreDrop(Block block)
	{
		return droppingItemWithFortune(block, Items.COAL);
	}
	private static LootTable.Builder redstoneOreDrop(Block block)
	{
		return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.REDSTONE).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
	}
	private static LootTable.Builder quartzOreDrop(Block block)
	{
		return droppingItemWithFortune(block, Items.QUARTZ);
	}
	private static LootTable.Builder lapisOreDrop(Block block)
	{
		return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.LAPIS_LAZULI).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
	}
	private static LootTable.Builder diamondOreDrop(Block block)
	{
		return droppingItemWithFortune(block, Items.DIAMOND);
	}
	private static LootTable.Builder endGrassDrop(Block block)
	{
		return droppingWithSilkTouch(block, Blocks.END_STONE);
	}
	private static LootTable.Builder frostLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, Blocks.AIR, SAPLING_CHANCES);
	}
	private static LootTable.Builder rainbowLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, RAINBOW_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder endLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, END_SAPLING, SAPLING_CHANCES).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(NO_SILK_OR_SHEAR_CONDITION).addEntry(withSurvivesExplosion(block, ItemLootEntry.builder(Items.CHORUS_FRUIT)).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
	}
	private static LootTable.Builder bloodAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, BLOOD_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder breathAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, BREATH_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder doomAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, DOOM_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder heartAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, HEART_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder hopeAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, HOPE_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder lifeAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, LIFE_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder lightAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, LIGHT_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder mindAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, MIND_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder rageAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, RAGE_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder spaceAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, SPACE_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder timeAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, TIME_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder voidAspectLeavesDrop(Block block)
	{
		return droppingWithChancesAndSticks(block, VOID_ASPECT_SAPLING, SAPLING_CHANCES);
	}
	private static LootTable.Builder desertBushDrop(Block block)
	{
		return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(MSItems.DESERT_FRUIT).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE))));
	}
	private static LootTable.Builder strawberryStemDrop(Block block)
	{
		return droppingByAge(block, MSItems.STRAWBERRY_CHUNK);
	}
	protected static LootTable.Builder droppingWithColor(Block block)
	{
		return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).replaceOperation("color", "color")))));
	}
	protected static LootTable.Builder droppingWithTEItem(Block block)
	{
		return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(DynamicLootEntry.func_216162_a(ItemStackTileEntity.ITEM_DYNAMIC))));
	}
	protected static LootTable.Builder droppingWithNameOnSilkTouch(Block block)
	{
		return dropping(block, SILK_TOUCH_CONDITION.inverted(), ItemLootEntry.builder(block).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)));
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks()
	{
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(Minestuck.MOD_ID)).collect(Collectors.toList());
	}
}
