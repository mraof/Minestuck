package com.mraof.minestuck.data.recipe;

import com.google.gson.JsonObject;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.item.crafting.MSRecipeTypes;
import com.mraof.minestuck.util.ExtraForgeTags;
import com.mraof.minestuck.util.MSTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class MinestuckRecipeProvider extends RecipeProvider
{
	public MinestuckRecipeProvider(DataGenerator generator)
	{
		super(generator);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> recipeBuilder)
	{
		ShapelessRecipeBuilder.shapelessRecipe(MSItems.RAW_CRUXITE, 9).addIngredient(MSBlocks.CRUXITE_BLOCK).addCriterion("has_raw_cruxite", hasItem(MSItems.RAW_CRUXITE)).addCriterion("has_cruxite_block", hasItem(MSBlocks.CRUXITE_BLOCK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_cruxite_from_block"));
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CRUXITE_BLOCK).key('#', MSItems.RAW_CRUXITE).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_raw_cruxite", hasItem(MSItems.RAW_CRUXITE)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSItems.RAW_URANIUM, 9).addIngredient(MSBlocks.URANIUM_BLOCK).addCriterion("has__raw_uranium", hasItem(MSItems.RAW_URANIUM)).addCriterion("has_uranium_block", hasItem(MSBlocks.URANIUM_BLOCK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_uranium_from_block"));
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.URANIUM_BLOCK).key('#', MSItems.RAW_URANIUM).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_raw_uranium", hasItem(MSItems.RAW_URANIUM)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(Items.SUGAR, 9).addIngredient(MSBlocks.SUGAR_CUBE).addCriterion("has_sugar", hasItem(Items.SUGAR)).addCriterion("has_sugar_cube", hasItem(MSBlocks.SUGAR_CUBE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "sugar_from_cube"));
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.SUGAR_CUBE).key('#', Items.SUGAR).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_sugar", hasItem(Items.SUGAR)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSItems.STRAWBERRY_CHUNK, 4).addIngredient(MSBlocks.STRAWBERRY).addCriterion("has_strawberry_chunks", hasItem( MSItems.STRAWBERRY_CHUNK)).addCriterion("has_strawberry_block", hasItem(MSBlocks.STRAWBERRY)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.STRAWBERRY).key('#', MSItems.STRAWBERRY_CHUNK).patternLine("##").patternLine("##").addCriterion("has_strawberry_chunks", hasItem(MSItems.STRAWBERRY_CHUNK)).build(recipeBuilder);
		
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.SMOOTH_SHADE_STONE, 4).key('#', MSBlocks.SHADE_STONE).patternLine("##").patternLine("##").addCriterion("has_shade_stone", hasItem(MSBlocks.SHADE_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.SHADE_BRICKS, 4).key('#', MSBlocks.SMOOTH_SHADE_STONE).patternLine("##").patternLine("##").addCriterion("has_smooth_shade_stone", hasItem(MSBlocks.SMOOTH_SHADE_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.FROST_BRICKS, 4).key('#', MSBlocks.FROST_TILE).patternLine("##").patternLine("##").addCriterion("has_frost_tile", hasItem(MSBlocks.FROST_TILE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.POLISHED_CHALK, 4).key('#', MSBlocks.CHALK).patternLine("##").patternLine("##").addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHALK_BRICKS, 4).key('#', MSBlocks.POLISHED_CHALK).patternLine("##").patternLine("##").addCriterion("has_polished_chalk", hasItem(MSBlocks.POLISHED_CHALK)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHISELED_CHALK_BRICKS, 4).key('#', MSBlocks.CHALK_BRICKS).patternLine("##").patternLine("##").addCriterion("has_chalk_bricks", hasItem(MSBlocks.CHALK_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.POLISHED_PINK_STONE, 4).key('#', MSBlocks.PINK_STONE).patternLine("##").patternLine("##").addCriterion("has_pink_stone", hasItem(MSBlocks.PINK_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.PINK_STONE_BRICKS, 4).key('#', MSBlocks.POLISHED_PINK_STONE).patternLine("##").patternLine("##").addCriterion("has_polished_pink_stone", hasItem(MSBlocks.POLISHED_PINK_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHISELED_PINK_STONE_BRICKS, 4).key('#', MSBlocks.PINK_STONE_BRICKS).patternLine("##").patternLine("##").addCriterion("has_pink_stone_bricks", hasItem(MSBlocks.PINK_STONE_BRICKS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.MOSSY_PINK_STONE_BRICKS).addIngredient(MSBlocks.PINK_STONE_BRICKS).addIngredient(Blocks.VINE).addCriterion("has_vine", this.hasItem(Blocks.VINE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.POLISHED_BROWN_STONE, 4).key('#', MSBlocks.BROWN_STONE).patternLine("##").patternLine("##").addCriterion("has_brown_stone", hasItem(MSBlocks.BROWN_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.BROWN_STONE_BRICKS, 4).key('#', MSBlocks.POLISHED_BROWN_STONE).patternLine("##").patternLine("##").addCriterion("has_polished_brown_stone", hasItem(MSBlocks.POLISHED_BROWN_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.BROWN_STONE_COLUMN, 3).key('#', MSBlocks.POLISHED_BROWN_STONE).patternLine("#").patternLine("#").patternLine("#").addCriterion("has_polished_brown_stone", hasItem(MSBlocks.POLISHED_BROWN_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.COARSE_STONE_STAIRS, 4).key('#', MSBlocks.COARSE_STONE).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_coarse_stone", hasItem(MSBlocks.COARSE_STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.SHADE_BRICK_STAIRS, 4).key('#', MSBlocks.SHADE_BRICKS).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_shade_bricks", hasItem(MSBlocks.SHADE_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.FROST_BRICK_STAIRS, 4).key('#', MSBlocks.FROST_BRICKS).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_frost_bricks", hasItem(MSBlocks.FROST_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CAST_IRON_STAIRS, 4).key('#', MSBlocks.CAST_IRON).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_cast_iron", hasItem(MSBlocks.CAST_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.MYCELIUM_BRICK_STAIRS, 4).key('#', MSBlocks.MYCELIUM_BRICKS).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_mycelium_bricks", hasItem(MSBlocks.MYCELIUM_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHALK_STAIRS, 4).key('#', MSBlocks.CHALK).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHALK_BRICK_STAIRS, 4).key('#', MSBlocks.CHALK_BRICKS).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_chalk_bricks", hasItem(MSBlocks.CHALK_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.PINK_STONE_BRICK_STAIRS, 4).key('#', MSBlocks.PINK_STONE_BRICKS).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_pink_stone_bricks", hasItem(MSBlocks.PINK_STONE_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.BROWN_STONE_BRICK_STAIRS, 4).key('#', MSBlocks.BROWN_STONE_BRICKS).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_brown_stone_bricks", hasItem(MSBlocks.BROWN_STONE_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHALK_SLAB, 6).key('#', MSBlocks.CHALK).patternLine("###").addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHALK_BRICK_SLAB, 6).key('#', MSBlocks.CHALK_BRICKS).patternLine("###").addCriterion("has_chalk_bricks", hasItem(MSBlocks.CHALK_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.PINK_STONE_BRICK_SLAB, 6).key('#', MSBlocks.PINK_STONE_BRICKS).patternLine("###").addCriterion("has_pink_stone_bricks", hasItem(MSBlocks.PINK_STONE_BRICKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.BROWN_STONE_BRICK_SLAB, 6).key('#', MSBlocks.BROWN_STONE_BRICKS).patternLine("###").addCriterion("has_brown_stone_bricks", hasItem(MSBlocks.BROWN_STONE_BRICKS)).build(recipeBuilder);
		
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.GLOWING_WOOD, 3).key('#', MSBlocks.GLOWING_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.GLOWING_LOGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.GLOWING_PLANKS, 4).addIngredient(MSTags.Items.GLOWING_LOGS).setGroup("planks").addCriterion("has_log", hasItem(MSTags.Items.GLOWING_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.FROST_WOOD, 3).key('#', MSBlocks.FROST_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.FROST_LOGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.FROST_PLANKS, 4).addIngredient(MSTags.Items.FROST_LOGS).setGroup("planks").addCriterion("has_log", hasItem(MSTags.Items.FROST_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.RAINBOW_WOOD, 3).key('#', MSBlocks.RAINBOW_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.RAINBOW_LOGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.RAINBOW_PLANKS, 4).addIngredient(MSTags.Items.RAINBOW_LOGS).setGroup("planks").addCriterion("has_log", hasItem(MSTags.Items.RAINBOW_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.END_WOOD, 3).key('#', MSBlocks.END_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.END_LOGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.END_PLANKS, 4).addIngredient(MSTags.Items.END_LOGS).setGroup("planks").addCriterion("has_log", hasItem(MSTags.Items.END_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.VINE_WOOD, 3).key('#', MSBlocks.VINE_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.VINE_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.FLOWERY_VINE_WOOD, 3).key('#', MSBlocks.FLOWERY_VINE_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.FLOWERY_VINE_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.DEAD_WOOD, 3).key('#', MSBlocks.DEAD_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.DEAD_LOGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.DEAD_PLANKS, 4).addIngredient(MSTags.Items.DEAD_LOGS).setGroup("planks").addCriterion("has_log", hasItem(MSTags.Items.DEAD_LOGS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.PETRIFIED_WOOD, 3).key('#', MSBlocks.PETRIFIED_LOG).patternLine("##").patternLine("##").setGroup("bark").addCriterion("has_log", hasItem(MSTags.Items.PETRIFIED_LOGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(Blocks.OAK_PLANKS).addIngredient(MSBlocks.TREATED_PLANKS).addIngredient(Items.WATER_BUCKET).setGroup("planks").addCriterion("has_log", hasItem(MSBlocks.TREATED_PLANKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "oak_planks_from_treated_planks"));
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.RAINBOW_PLANKS_STAIRS, 4).key('#', MSBlocks.RAINBOW_PLANKS).patternLine("#  ").patternLine("## ").patternLine("###").setGroup("wooden_stairs").addCriterion("has_planks", hasItem(MSBlocks.RAINBOW_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.END_PLANKS_STAIRS, 4).key('#', MSBlocks.END_PLANKS).patternLine("#  ").patternLine("## ").patternLine("###").setGroup("wooden_stairs").addCriterion("has_planks", hasItem(MSBlocks.END_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.DEAD_PLANKS_STAIRS, 4).key('#', MSBlocks.DEAD_PLANKS).patternLine("#  ").patternLine("## ").patternLine("###").setGroup("wooden_stairs").addCriterion("has_planks", hasItem(MSBlocks.DEAD_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.TREATED_PLANKS_STAIRS, 4).key('#', MSBlocks.TREATED_PLANKS).patternLine("#  ").patternLine("## ").patternLine("###").setGroup("wooden_stairs").addCriterion("has_planks", hasItem(MSBlocks.TREATED_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.RAINBOW_PLANKS_SLAB, 6).key('#', MSBlocks.RAINBOW_PLANKS).patternLine("###").setGroup("wooden_slab").addCriterion("has_planks", hasItem(MSBlocks.RAINBOW_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.END_PLANKS_SLAB, 6).key('#', MSBlocks.END_PLANKS).patternLine("###").setGroup("wooden_slab").addCriterion("has_planks", hasItem(MSBlocks.END_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.DEAD_PLANKS_SLAB, 6).key('#', MSBlocks.DEAD_PLANKS).patternLine("###").setGroup("wooden_slab").addCriterion("has_planks", hasItem(MSBlocks.DEAD_PLANKS)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.TREATED_PLANKS_SLAB, 6).key('#', MSBlocks.TREATED_PLANKS).patternLine("###").setGroup("wooden_slab").addCriterion("has_planks", hasItem(MSBlocks.TREATED_PLANKS)).build(recipeBuilder);
		
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.COMPUTER).key('e', MSItems.ENERGY_CORE).key('i', Tags.Items.INGOTS_IRON).patternLine("iii").patternLine("iei").patternLine("iii").addCriterion("has_energy_core", hasItem(MSItems.ENERGY_CORE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.URANIUM_COOKER).key('e', MSItems.ENERGY_CORE).key('i', Tags.Items.INGOTS_IRON).key('F', Blocks.FURNACE).patternLine("iii").patternLine("iFi").patternLine("iei").addCriterion("has_energy_core", hasItem(MSItems.ENERGY_CORE)).build(recipeBuilder);
		
		ShapelessRecipeBuilder.shapelessRecipe(Blocks.SPRUCE_PLANKS).addIngredient(MSBlocks.WOODEN_CACTUS).setGroup("planks").addCriterion("has_cactus", hasItem(MSBlocks.WOODEN_CACTUS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "spruce_planks_from_wooden_cactus"));
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.APPLE_CAKE).key('m', Items.MILK_BUCKET).key('a', Items.APPLE).key('w', Items.WHEAT).key('e', Items.EGG).patternLine("mmm").patternLine("aea").patternLine("www").addCriterion("has_egg", hasItem(Items.EGG)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHESSBOARD).key('W', Blocks.WHITE_TERRACOTTA).key('B', Blocks.BLACK_TERRACOTTA).patternLine("WBW").patternLine("BWB").patternLine("WBW").setGroup("chessboard").addCriterion("has_white_terracotta", hasItem(Blocks.WHITE_TERRACOTTA)).addCriterion("has_black_terracotta", hasItem(Blocks.BLACK_TERRACOTTA)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chessboard_white"));
		ShapedRecipeBuilder.shapedRecipe(MSBlocks.CHESSBOARD).key('W', Blocks.WHITE_TERRACOTTA).key('B', Blocks.BLACK_TERRACOTTA).patternLine("BWB").patternLine("WBW").patternLine("BWB").setGroup("chessboard").addCriterion("has_white_terracotta", hasItem(Blocks.WHITE_TERRACOTTA)).addCriterion("has_black_terracotta", hasItem(Blocks.BLACK_TERRACOTTA)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chessboard_black"));
		ShapedRecipeBuilder.shapedRecipe(MSItems.CLOTHES_IRON).key('s', Tags.Items.STONE).key('i', Tags.Items.INGOTS_IRON).key('r', Tags.Items.RODS_WOODEN).patternLine(" rr").patternLine("sss").patternLine("iii").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.HORN).key('w', Items.WHITE_WOOL).key('i', Tags.Items.INGOTS_IRON).key('r', Tags.Items.RODS_WOODEN).patternLine("  i").patternLine(" r ").patternLine("w  ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).addCriterion("has_white_wool", hasItem(Items.WHITE_WOOL)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSItems.BI_DYE, 2).addIngredient(Items.BLUE_DYE).addCriterion("has_blue_dye", hasItem(Items.BLUE_DYE)).addIngredient(Items.PINK_DYE).addCriterion("has_pink_dye", hasItem(Items.PINK_DYE)).addIngredient(Items.PURPLE_DYE).addCriterion("has_purple_dye", hasItem(Items.PURPLE_DYE)).build(recipeBuilder);
		
		ShapedRecipeBuilder.shapedRecipe(MSItems.CLAW_HAMMER).key('i', Tags.Items.INGOTS_IRON).key('s', Tags.Items.RODS_WOODEN).patternLine(" ii").patternLine("is ").patternLine(" s ").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.SLEDGE_HAMMER).key('i', Tags.Items.INGOTS_IRON).key('s', Tags.Items.RODS_WOODEN).key('S', Tags.Items.STONE).patternLine("iSi").patternLine(" s ").patternLine(" s ").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.KATANA).key('i', Tags.Items.INGOTS_IRON).key('s', Tags.Items.RODS_WOODEN).patternLine("  i").patternLine(" i ").patternLine("s  ").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.MACUAHUITL).key('P', ItemTags.PLANKS).key('s', Tags.Items.RODS_WOODEN).key('O', Items.OBSIDIAN).patternLine("OPO").patternLine("OPO").patternLine(" s ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.OBSIDIAN_AXE_KNIFE).key('s', Tags.Items.RODS_WOODEN).key('O', Items.OBSIDIAN).patternLine("  O").patternLine(" Os").patternLine("Os ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.SICKLE).key('i', Tags.Items.INGOTS_IRON).key('s', Tags.Items.RODS_WOODEN).patternLine("ii ").patternLine("  i").patternLine(" s ").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.DEUCE_CLUB).key('P', ItemTags.PLANKS).key('s', Tags.Items.RODS_WOODEN).patternLine("  P").patternLine(" s ").patternLine("s  ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.CANE).key('s', Tags.Items.RODS_WOODEN).patternLine("  s").patternLine(" s ").patternLine("s  ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.BEAR_POKING_STICK).key('s', Tags.Items.RODS_WOODEN).key('l', Tags.Items.LEATHER).patternLine("  s").patternLine(" s ").patternLine("l  ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.UMBRELLA).key('s', Tags.Items.RODS_WOODEN).key('w', Items.BLACK_WOOL).patternLine(" w ").patternLine("wsw").patternLine(" s ").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.FAN).key('s', Tags.Items.RODS_WOODEN).key('p', Items.PAPER).patternLine("  p").patternLine(" pp").patternLine("pps").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.IRON_CANE).key('i', Tags.Items.INGOTS_IRON).key('c', MSItems.CANE).patternLine("  i").patternLine(" c ").patternLine("i  ").addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.WOODEN_SPOON).key('s', Tags.Items.RODS_WOODEN).key('b', Items.BOWL).patternLine("b").patternLine("s").patternLine("s").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.FORK).key('S', Tags.Items.STONE).patternLine("S S").patternLine(" S ").patternLine(" S ").addCriterion("has_stone", hasItem(Tags.Items.STONE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.POINTY_STICK, 1).key('s', Tags.Items.RODS_WOODEN).patternLine("s").patternLine("s").patternLine("s").addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.KNITTING_NEEDLE, 2).key('i', Items.IRON_INGOT).key('n', Items.IRON_NUGGET).patternLine("  n").patternLine(" i ").patternLine("i  ").addCriterion("has_iron_ingot", hasItem(Items.IRON_INGOT)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.SHURIKEN, 2).key('i', Items.IRON_INGOT).key('n', Items.IRON_NUGGET).patternLine(" i ").patternLine("ini").patternLine(" i ").addCriterion("has_iron_ingot", hasItem(Items.IRON_INGOT)).build(recipeBuilder);
		
		ShapedRecipeBuilder.shapedRecipe(MSItems.ENERGY_CORE).key('u', ExtraForgeTags.Items.URANIUM_CHUNKS).key('c', MSItems.RAW_CRUXITE).patternLine("cuc").patternLine("ucu").patternLine("cuc").addCriterion("has_raw_uranium", hasItem(MSItems.RAW_URANIUM)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.TEMPLE_SCANNER).key('e', MSItems.ENERGY_CORE).key('c', MSItems.RAW_CRUXITE).key('i', Items.IRON_INGOT).key('r', Items.REDSTONE).patternLine(" i ").patternLine("iei").patternLine("crc").addCriterion("has_energy_core", hasItem(MSItems.ENERGY_CORE)).build(recipeBuilder);
		ShapedRecipeBuilder.shapedRecipe(MSItems.CAPTCHA_CARD).key('p', Items.PAPER).key('c', MSItems.RAW_CRUXITE).patternLine("ppp").patternLine("pcp").patternLine("ppp").addCriterion("has_raw_cruxite", hasItem(MSItems.RAW_CRUXITE)).build(recipeBuilder);
		//TODO custom ingredient type to disallow punched cards or cards with items to be used here
		NonMirroredRecipeBuilder.nonMirroredRecipe(MSItems.STACK_MODUS_CARD).key('a', Ingredient.fromItems(MSItems.CAPTCHA_CARD)).key('c', MSItems.RAW_CRUXITE).key('C', MSBlocks.CRUXITE_BLOCK).patternLine("Cac").addCriterion("has_card", hasItem(MSItems.CAPTCHA_CARD)).build(recipeBuilder);
		NonMirroredRecipeBuilder.nonMirroredRecipe(MSItems.QUEUE_MODUS_CARD).key('a', Ingredient.fromItems(MSItems.CAPTCHA_CARD)).key('c', MSItems.RAW_CRUXITE).key('C', MSBlocks.CRUXITE_BLOCK).patternLine("caC").addCriterion("has_card", hasItem(MSItems.CAPTCHA_CARD)).build(recipeBuilder);
		
		ShapelessRecipeBuilder.shapelessRecipe(MSItems.BUG_ON_A_STICK, 3).addIngredient(Ingredient.fromTag(Tags.Items.RODS_WOODEN), 3).addIngredient(MSItems.JAR_OF_BUGS).addCriterion("has_jag_of_bugs", hasItem(MSItems.JAR_OF_BUGS)).build(recipeBuilder);
		ShapelessRecipeBuilder.shapelessRecipe(MSItems.SALAD).addIngredient(Items.BOWL).addIngredient(ItemTags.LEAVES).addCriterion("has_bowl", hasItem(Items.BOWL)).build(recipeBuilder);
		
		ShapelessRecipeBuilder.shapelessRecipe(MSBlocks.TRANS_PORTALIZER).addIngredient(MSBlocks.TRANSPORTALIZER).addIngredient(Items.PINK_DYE).addIngredient(Items.LIGHT_BLUE_DYE).addCriterion("has_transportalizer", hasItem(MSBlocks.TRANSPORTALIZER)).build(recipeBuilder);
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE_BRICKS), MSBlocks.CRACKED_PINK_STONE_BRICKS, 0.1F, 200).addCriterion("has_pink_stone_bricks", hasItem(MSBlocks.PINK_STONE_BRICKS)).build(recipeBuilder);
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE_BRICKS), MSBlocks.CRACKED_BROWN_STONE_BRICKS, 0.1F, 200).addCriterion("has_brown_stone_bricks", hasItem(MSBlocks.BROWN_STONE_BRICKS)).build(recipeBuilder);
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(MSItems.CAKE_MIX), Blocks.CAKE, 0.0F, 200).addCriterion("has_cake_mix", hasItem(MSItems.CAKE_MIX)).build(recipeBuilder, new ResourceLocation (Minestuck.MOD_ID, "cake_from_mix"));
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.CRUXITE_ORES), MSItems.RAW_CRUXITE, 0.2F, 200).addCriterion("has_cruxite_ore", hasItem(MSTags.Items.CRUXITE_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_cruxite_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.CRUXITE_ORES), MSItems.RAW_CRUXITE, 0.2F, 100).addCriterion("has_cruxite_ore", hasItem(MSTags.Items.CRUXITE_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_cruxite_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.URANIUM_ORES), MSItems.RAW_URANIUM, 0.2F, 200).addCriterion("has_uranium_ore", hasItem(MSTags.Items.URANIUM_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_uranium_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.URANIUM_ORES), MSItems.RAW_URANIUM, 0.2F, 100).addCriterion("has_uranium_ore", hasItem(MSTags.Items.URANIUM_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_uranium_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.COAL_ORES), Items.COAL, 0.1F, 200).addCriterion("has_coal_ore", hasItem(MSTags.Items.COAL_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "coal_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.COAL_ORES), Items.COAL, 0.1F, 100).addCriterion("has_coal_ore", hasItem(MSTags.Items.COAL_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "coal_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.IRON_ORES), Items.IRON_INGOT, 0.7F, 200).addCriterion("has_iron_ore", hasItem(MSTags.Items.IRON_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "iron_ingot_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.IRON_ORES), Items.IRON_INGOT, 0.7F, 100).addCriterion("has_iron_ore", hasItem(MSTags.Items.IRON_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "iron_ingot_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.GOLD_ORES), Items.GOLD_INGOT, 1.0F, 200).addCriterion("has_gold_ore", hasItem(MSTags.Items.GOLD_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "gold_ingot_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.GOLD_ORES), Items.GOLD_INGOT, 1.0F, 100).addCriterion("has_gold_ore", hasItem(MSTags.Items.GOLD_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "gold_ingot_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.REDSTONE_ORES), Items.REDSTONE, 0.7F, 200).addCriterion("has_redstone_ore", hasItem(MSTags.Items.REDSTONE_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "redstone_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.REDSTONE_ORES), Items.REDSTONE, 0.7F, 100).addCriterion("has_redstone_ore", hasItem(MSTags.Items.REDSTONE_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "redstone_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.QUARTZ_ORES), Items.QUARTZ, 0.2F, 200).addCriterion("has_quartz_ore", hasItem(MSTags.Items.QUARTZ_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "quartz_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.QUARTZ_ORES), Items.QUARTZ, 0.2F, 100).addCriterion("has_quartz_ore", hasItem(MSTags.Items.QUARTZ_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "quartz_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.LAPIS_ORES), Items.LAPIS_LAZULI, 0.2F, 200).addCriterion("has_lapis_ore", hasItem(MSTags.Items.LAPIS_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "lapis_lazuli_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.LAPIS_ORES), Items.LAPIS_LAZULI, 0.2F, 100).addCriterion("has_lapis_ore", hasItem(MSTags.Items.LAPIS_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "lapis_lazuli_from_blasting"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MSTags.Items.DIAMOND_ORES), Items.DIAMOND, 1.0F, 200).addCriterion("has_diamond_ore", hasItem(MSTags.Items.DIAMOND_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "diamond_from_smelting"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(MSTags.Items.DIAMOND_ORES), Items.DIAMOND, 1.0F, 100).addCriterion("has_diamond_ore", hasItem(MSTags.Items.DIAMOND_ORES)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "diamond_from_blasting"));
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(MSBlocks.CRUXITE_DOWEL), MSItems.RAW_CRUXITE, 0.0F, 200).addCriterion("has_cruxite_dowel", hasItem(MSBlocks.CRUXITE_DOWEL)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "raw_cruxite_from_dowel"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(MSBlocks.GOLD_SEEDS), Items.GOLD_NUGGET, 0.1F, 200).addCriterion("has_gold_seeds", hasItem(MSBlocks.GOLD_SEEDS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "gold_nugget_from_seeds"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(MSBlocks.WOODEN_CACTUS), Items.CHARCOAL, 0.15F, 200).addCriterion("has_wooden_cactus", hasItem(MSBlocks.WOODEN_CACTUS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "charcoal_from_wooden_cactus"));
		
		cookingRecipesFor(recipeBuilder, Ingredient.fromItems(MSItems.BEEF_SWORD), MSItems.STEAK_SWORD, 0.5F, "has_beef_sword", hasItem(MSItems.BEEF_SWORD));
		
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.BEEF), MSItems.IRRADIATED_STEAK, 0.2F, 20, MSRecipeTypes.IRRADIATING).addCriterion("has_beef", hasItem(Items.BEEF)).build(skipAdvancement(recipeBuilder));
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(MSItems.BEEF_SWORD), MSItems.IRRADIATED_STEAK_SWORD, 0.35F, 20, MSRecipeTypes.IRRADIATING).addCriterion("has_beef_sword", hasItem(MSItems.BEEF_SWORD)).build(skipAdvancement(recipeBuilder));
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.STICK), MSItems.URANIUM_POWERED_STICK, 0.1F, 100, MSRecipeTypes.IRRADIATING).addCriterion("has_stick", hasItem(Items.STICK)).build(skipAdvancement(recipeBuilder));
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(Items.MUSHROOM_STEW), Items.SLIME_BALL, 0.1F, 20, MSRecipeTypes.IRRADIATING).addCriterion("has_mushroom_stew", hasItem(Items.MUSHROOM_STEW)).build(skipAdvancement(recipeBuilder), new ResourceLocation(Minestuck.MOD_ID, "slimeball_from_irradiating"));
		IrradiatingFallbackRecipeBuilder.fallback(IRecipeType.SMOKING).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "irradiate_smoking_fallback"));
		
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.COARSE_STONE), MSBlocks.COARSE_STONE_STAIRS).addCriterion("has_coarse_stone", hasItem(MSBlocks.COARSE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "coarse_stone_stairs_from_coarse_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.COARSE_STONE), MSBlocks.CHISELED_COARSE_STONE).addCriterion("has_coarse_stone", hasItem(MSBlocks.COARSE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chiseled_coarse_stone_from_coarse_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.SHADE_STONE), MSBlocks.SHADE_BRICKS).addCriterion("has_shade_stone", hasItem(MSBlocks.SHADE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "shade_bricks_from_shade_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.SHADE_STONE), MSBlocks.SMOOTH_SHADE_STONE).addCriterion("has_shade_stone", hasItem(MSBlocks.SHADE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "smooth_shade_stone_from_shade_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.SHADE_STONE), MSBlocks.SHADE_BRICK_STAIRS).addCriterion("has_shade_stone", hasItem(MSBlocks.SHADE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "shade_brick_stairs_from_shade_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.SMOOTH_SHADE_STONE), MSBlocks.SHADE_BRICKS).addCriterion("has_smooth_shade_stone", hasItem(MSBlocks.SMOOTH_SHADE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "shade_bricks_from_smooth_shade_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.SMOOTH_SHADE_STONE), MSBlocks.SHADE_BRICK_STAIRS).addCriterion("has_smooth_shade_stone", hasItem(MSBlocks.SMOOTH_SHADE_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "shade_brick_stairs_from_smooth_shade_stone_stonecutting"));
		//SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.SHADE_BRICKS), MSBlocks.SHADE_BRICK_STAIRS).addCriterion("has_shade_bricks", hasItem(MSBlocks.SHADE_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "shade_brick_stairs_from_shade_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.FROST_TILE), MSBlocks.FROST_BRICKS).addCriterion("has_frost_tile", hasItem(MSBlocks.FROST_TILE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "frost_bricks_from_frost_tile_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.FROST_TILE), MSBlocks.FROST_BRICK_STAIRS).addCriterion("has_frost_tile", hasItem(MSBlocks.FROST_TILE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "frost_brick_stairs_from_frost_tile_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.FROST_TILE), MSBlocks.CHISELED_FROST_BRICKS).addCriterion("has_frost_tile", hasItem(MSBlocks.FROST_TILE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chiseled_frost_bricks_from_frost_tile_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.FROST_BRICKS), MSBlocks.FROST_BRICK_STAIRS).addCriterion("has_frost_bricks", hasItem(MSBlocks.FROST_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "frost_brick_stairs_from_frost_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.FROST_BRICKS), MSBlocks.CHISELED_FROST_BRICKS).addCriterion("has_frost_bricks", hasItem(MSBlocks.FROST_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chiseled_frost_bricks_from_frost_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CAST_IRON), MSBlocks.CAST_IRON_STAIRS).addCriterion("has_cast_iron", hasItem(MSBlocks.CAST_IRON)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "cast_iron_stairs_from_cast_iron_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CAST_IRON), MSBlocks.CHISELED_CAST_IRON).addCriterion("has_cast_iron", hasItem(MSBlocks.CAST_IRON)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chiseled_cast_iron_from_cast_iron_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.MYCELIUM_BRICKS), MSBlocks.MYCELIUM_BRICK_STAIRS).addCriterion("has_mycelium_bricks", hasItem(MSBlocks.MYCELIUM_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "mycelium_brick_stairs_from_mycelium_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK), MSBlocks.CHALK_STAIRS).addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_stairs_from_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK), MSBlocks.CHALK_SLAB, 2).addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_slab_from_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK), MSBlocks.POLISHED_CHALK).addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "polished_chalk_from_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK), MSBlocks.CHALK_BRICKS).addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_bricks_from_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK), MSBlocks.CHALK_BRICK_STAIRS).addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_brick_stairs_from_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK), MSBlocks.CHALK_BRICK_SLAB, 2).addCriterion("has_chalk", hasItem(MSBlocks.CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_brick_slab_from_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_CHALK), MSBlocks.CHALK_BRICKS).addCriterion("has_polished_chalk", hasItem(MSBlocks.POLISHED_CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_bricks_from_polished_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_CHALK), MSBlocks.CHALK_BRICK_STAIRS).addCriterion("has_polished_chalk", hasItem(MSBlocks.POLISHED_CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_brick_stairs_from_polished_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_CHALK), MSBlocks.CHALK_BRICK_SLAB, 2).addCriterion("has_polished_chalk", hasItem(MSBlocks.POLISHED_CHALK)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_brick_slab_from_polished_chalk_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK_BRICKS), MSBlocks.CHALK_BRICK_STAIRS).addCriterion("has_chalk_bricks", hasItem(MSBlocks.CHALK_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_brick_stairs_from_chalk_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.CHALK_BRICKS), MSBlocks.CHALK_BRICK_SLAB, 2).addCriterion("has_chalk_bricks", hasItem(MSBlocks.CHALK_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "chalk_brick_slab_from_chalk_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE), MSBlocks.POLISHED_PINK_STONE).addCriterion("has_pink_stone", hasItem(MSBlocks.PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "polished_pink_stone_from_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE), MSBlocks.PINK_STONE_BRICKS).addCriterion("has_pink_stone", hasItem(MSBlocks.PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_bricks_from_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE), MSBlocks.PINK_STONE_BRICK_STAIRS).addCriterion("has_pink_stone", hasItem(MSBlocks.PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_brick_stairs_from_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE), MSBlocks.PINK_STONE_BRICK_SLAB).addCriterion("has_pink_stone", hasItem(MSBlocks.PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_brick_slab_from_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_PINK_STONE), MSBlocks.PINK_STONE_BRICKS).addCriterion("has_polished_pink_stone", hasItem(MSBlocks.POLISHED_PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_bricks_from_polished_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_PINK_STONE), MSBlocks.PINK_STONE_BRICK_STAIRS).addCriterion("has_polished_pink_stone", hasItem(MSBlocks.POLISHED_PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_brick_stairs_from_polished_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_PINK_STONE), MSBlocks.PINK_STONE_BRICK_SLAB, 2).addCriterion("has_polished_pink_stone", hasItem(MSBlocks.POLISHED_PINK_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_brick_slab_from_polished_pink_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE_BRICKS), MSBlocks.PINK_STONE_BRICK_STAIRS).addCriterion("has_pink_stone_bricks", hasItem(MSBlocks.PINK_STONE_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_brick_stairs_from_pink_stone_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.PINK_STONE_BRICKS), MSBlocks.PINK_STONE_BRICK_SLAB, 2).addCriterion("has_pink_stone_bricks", hasItem(MSBlocks.PINK_STONE_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "pink_stone_brick_slab_from_pink_stone_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE), MSBlocks.POLISHED_BROWN_STONE).addCriterion("has_brown_stone", hasItem(MSBlocks.BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "polished_brown_stone_from_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE), MSBlocks.BROWN_STONE_BRICKS).addCriterion("has_brown_stone", hasItem(MSBlocks.BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_bricks_from_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE), MSBlocks.BROWN_STONE_BRICK_STAIRS).addCriterion("has_brown_stone", hasItem(MSBlocks.BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_brick_stairs_from_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE), MSBlocks.BROWN_STONE_BRICK_SLAB, 2).addCriterion("has_brown_stone", hasItem(MSBlocks.BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_brick_slab_from_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_BROWN_STONE), MSBlocks.BROWN_STONE_BRICKS).addCriterion("has_polished_brown_stone", hasItem(MSBlocks.POLISHED_BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_bricks_from_polished_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_BROWN_STONE), MSBlocks.BROWN_STONE_BRICK_STAIRS).addCriterion("has_polished_brown_stone", hasItem(MSBlocks.POLISHED_BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_brick_stairs_from_polished_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.POLISHED_BROWN_STONE), MSBlocks.BROWN_STONE_BRICK_SLAB, 2).addCriterion("has_polished_brown_stone", hasItem(MSBlocks.POLISHED_BROWN_STONE)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_brick_slab_from_polished_brown_stone_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE_BRICKS), MSBlocks.BROWN_STONE_BRICK_STAIRS).addCriterion("has_brown_stone_bricks", hasItem(MSBlocks.BROWN_STONE_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_brick_stairs_from_brown_stone_bricks_stonecutting"));
		SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(MSBlocks.BROWN_STONE_BRICKS), MSBlocks.BROWN_STONE_BRICK_SLAB, 2).addCriterion("has_brown_stone_bricks", hasItem(MSBlocks.BROWN_STONE_BRICKS)).build(recipeBuilder, new ResourceLocation(Minestuck.MOD_ID, "brown_stone_brick_slab_from_brown_stone_bricks_stonecutting"));
	}
	
	private void cookingRecipesFor(Consumer<IFinishedRecipe> recipeBuilder, Ingredient input, IItemProvider result, float experience, String criterionName, InventoryChangeTrigger.Instance criterion)
	{
		ResourceLocation itemName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(result.asItem()), "Registry name for "+result+" was found to be null!");
		CookingRecipeBuilder.smeltingRecipe(input, result, experience, 200).addCriterion(criterionName, criterion).build(recipeBuilder);
		CookingRecipeBuilder.cookingRecipe(input, result, experience, 100, IRecipeSerializer.SMOKING).addCriterion(criterionName, criterion).build(recipeBuilder, new ResourceLocation(itemName.getNamespace(), itemName.getPath()+"_from_smoking"));
		CookingRecipeBuilder.cookingRecipe(input, result, experience, 600, IRecipeSerializer.CAMPFIRE_COOKING).addCriterion(criterionName, criterion).build(recipeBuilder, new ResourceLocation(itemName.getNamespace(), itemName.getPath()+"_from_campfire_cooking"));
	}
	
	//TODO check between mc versions if this is still needed
	//As of writing this, the categories used by the vanilla recipe books are hardcoded, and all recipes are put in these categories
	// Because of this, recipes of modded recipe types will when unlocked show up in a vanilla recipe category
	// As a temporary solution, this function helps to remove the recipe advancement, thus preventing the recipe from "unlocking"
	private Consumer<IFinishedRecipe> skipAdvancement(Consumer<IFinishedRecipe> recipeBuilder)
	{
		return recipe -> recipeBuilder.accept(new Wrapper(recipe));
	}
	
	private static class Wrapper implements IFinishedRecipe
	{
		IFinishedRecipe recipe;
		
		Wrapper(IFinishedRecipe recipe)
		{
			this.recipe = recipe;
		}
		
		@Override
		public void serialize(JsonObject json)
		{
			recipe.serialize(json);
		}
		
		@Override
		public ResourceLocation getID()
		{
			return recipe.getID();
		}
		
		@Override
		public IRecipeSerializer<?> getSerializer()
		{
			return recipe.getSerializer();
		}
		
		@Nullable
		@Override
		public JsonObject getAdvancementJson()
		{
			return null;
		}
		
		@Nullable
		@Override
		public ResourceLocation getAdvancementID()
		{
			return null;
		}
	}
	
	@Override
	public String getName()
	{
		return "Minestuck Recipes";
	}
}