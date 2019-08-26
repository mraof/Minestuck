package com.mraof.minestuck.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;

import static com.mraof.minestuck.block.MinestuckBlocks.*;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraftforge.common.Tags.Blocks.*;

public class MinestuckBlockTagsProvider extends BlockTagsProvider
{
	public MinestuckBlockTagsProvider(DataGenerator generatorIn)
	{
		super(generatorIn);
	}
	
	@Override
	protected void registerTags()
	{
		getBuilder(PLANKS).add(GLOWING_PLANKS, FROST_PLANKS, RAINBOW_PLANKS, END_PLANKS, DEAD_PLANKS, TREATED_PLANKS, BLOOD_ASPECT_PLANKS, BREATH_ASPECT_PLANKS, DOOM_ASPECT_PLANKS, HEART_ASPECT_PLANKS, HOPE_ASPECT_PLANKS, LIFE_ASPECT_PLANKS, LIGHT_ASPECT_PLANKS, MIND_ASPECT_PLANKS, RAGE_ASPECT_PLANKS, SPACE_ASPECT_PLANKS, TIME_ASPECT_PLANKS, VOID_ASPECT_PLANKS);
		getBuilder(STONE_BRICKS).add(FLOWERY_MOSS_BRICKS);
		getBuilder(WOODEN_BUTTONS).add(WOODEN_EXPLOSIVE_BUTTON);
		getBuilder(BUTTONS).add(STONE_EXPLOSIVE_BUTTON);
		getBuilder(WOODEN_STAIRS).add(RAINBOW_PLANKS_STAIRS, END_PLANKS_STAIRS, DEAD_PLANKS_STAIRS, TREATED_PLANKS_STAIRS);
		getBuilder(WOODEN_SLABS).add(RAINBOW_PLANKS_SLAB, END_PLANKS_SLAB, DEAD_PLANKS_SLAB, TREATED_PLANKS_SLAB);
		getBuilder(SAPLINGS).add(RAINBOW_SAPLING, END_SAPLING, BLOOD_ASPECT_SAPLING, BREATH_ASPECT_SAPLING, DOOM_ASPECT_SAPLING, HEART_ASPECT_SAPLING, HOPE_ASPECT_SAPLING, LIFE_ASPECT_SAPLING, LIGHT_ASPECT_SAPLING, MIND_ASPECT_SAPLING, RAGE_ASPECT_SAPLING, SPACE_ASPECT_SAPLING, TIME_ASPECT_SAPLING, VOID_ASPECT_SAPLING);
		getBuilder(LOGS); //TODO Make our own log block tags
		getBuilder(ENDERMAN_HOLDABLE); //There could be a point to adding some of our blocks to this
		getBuilder(STAIRS).add(COARSE_STONE_STAIRS, SHADE_BRICK_STAIRS, FROST_BRICK_STAIRS, CAST_IRON_STAIRS, MYCELIUM_BRICK_STAIRS, CHALK_STAIRS, CHALK_BRICK_STAIRS, PINK_STONE_BRICK_STAIRS);
		getBuilder(SLABS).add(CHALK_SLAB, CHALK_BRICK_SLAB, PINK_STONE_BRICK_SLAB);
		getBuilder(LEAVES).add(FROST_LEAVES, RAINBOW_LEAVES, END_LEAVES, BLOOD_ASPECT_LEAVES, BREATH_ASPECT_LEAVES, DOOM_ASPECT_LEAVES, HEART_ASPECT_LEAVES, HOPE_ASPECT_LEAVES, LIFE_ASPECT_LEAVES, LIGHT_ASPECT_LEAVES, MIND_ASPECT_LEAVES, RAGE_ASPECT_LEAVES, SPACE_ASPECT_LEAVES, TIME_ASPECT_LEAVES, VOID_ASPECT_LEAVES);
		getBuilder(DIRT_LIKE).add(BLACK_CHESS_DIRT, WHITE_CHESS_DIRT, DARK_GRAY_CHESS_DIRT, LIGHT_GRAY_CHESS_DIRT, BLUE_DIRT, THOUGHT_DIRT);
		getBuilder(COBBLESTONE).add(FLOWERY_MOSS_STONE);
		getBuilder(ORES); //TODO Make our own ore block tags
		getBuilder(ORES_COAL).add(NETHERRACK_COAL_ORE, PINK_STONE_COAL_ORE);
		getBuilder(ORES_DIAMOND).add(PINK_STONE_DIAMOND_ORE);
		getBuilder(ORES_GOLD).add(SANDSTONE_GOLD_ORE, RED_SANDSTONE_GOLD_ORE, PINK_STONE_GOLD_ORE);
		getBuilder(ORES_IRON).add(END_STONE_IRON_ORE, SANDSTONE_IRON_ORE, RED_SANDSTONE_IRON_ORE);
		getBuilder(ORES_LAPIS).add(PINK_STONE_LAPIS_ORE);
		getBuilder(ORES_QUARTZ).add(STONE_QUARTZ_ORE);
		getBuilder(ORES_REDSTONE).add(END_STONE_REDSTONE_ORE);
		getBuilder(STONE).add(COARSE_STONE, BLACK_STONE, COARSE_END_STONE, PINK_STONE);
		getBuilder(STORAGE_BLOCKS); //TODO Make our own storage block tags
	}
	
	@Override
	public String getName()
	{
		return "Minestuck Block Tags";
	}
}