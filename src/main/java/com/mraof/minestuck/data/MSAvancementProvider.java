package com.mraof.minestuck.data;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.advancements.*;
import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.entity.MSEntityTypes;
import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.inventory.captchalogue.ModusType;
import com.mraof.minestuck.inventory.captchalogue.ModusTypes;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.util.ColorHandler;
import com.mraof.minestuck.util.Debug;
import com.mraof.minestuck.world.gen.feature.MSFeatures;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

public class MSAvancementProvider implements IDataProvider
{
	public static final String ROOT = "minestuck.root";
	public static final String CONNECT = "minestuck.connect";
	public static final String ENTRY = "minestuck.entry";
	public static final String ALCHEMY = "minestuck.alchemy";
	public static final String NEW_MODUS = "minestuck.new_modus";
	public static final String ALL_MODI = "minestuck.all_modi";
	public static final String GOLD_SEEDS = "minestuck.gold_seeds";
	public static final String FRENCH_FRY = "minestuck.french_fry";
	public static final String MELON_OVERLOAD = "minestuck.melon_overload";
	public static final String TREE_MODUS = "minestuck.tree_modus";
	public static final String KILL_OGRE = "minestuck.kill_ogre";
	public static final String RETURN_NODE = "minestuck.return_node";
	public static final String DUNGEON = "minestuck.dungeon";
	public static final String COMMUNE = "minestuck.commune";
	public static final String BUGS = "minestuck.bugs";
	public static final String SHADY_BUYER = "minestuck.shady_buyer";
	public static final String LONG_TIME_COMING = "minestuck.long_time_coming";
	
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator generator;
	
	public MSAvancementProvider(DataGenerator generator)
	{
		this.generator = generator;
	}
	
	protected void buildAdvancements(Consumer<Advancement> advancementSaver)
	{
		Advancement root = Advancement.Builder.builder().withDisplay(MSItems.RAW_CRUXITE, new TranslationTextComponent(title(ROOT)), new TranslationTextComponent(desc(ROOT)), new ResourceLocation("minestuck:textures/gui/advancement_bg.png"), FrameType.TASK, false, false, false).withCriterion("raw_cruxite", InventoryChangeTrigger.Instance.forItems(MSItems.RAW_CRUXITE)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/root");
		Advancement connect = Advancement.Builder.builder().withParent(root).withDisplay(MSItems.CLIENT_DISK, new TranslationTextComponent(title(CONNECT)), new TranslationTextComponent(desc(CONNECT)), null, FrameType.TASK, true, true, false).withCriterion("connection", EventTrigger.Instance.sburbConnection()).register(advancementSaver, Minestuck.MOD_ID+":minestuck/connect");
		Advancement entry = Advancement.Builder.builder().withParent(connect).withDisplay(ColorHandler.setDefaultColor(new ItemStack(MSItems.CRUXITE_APPLE)), new TranslationTextComponent(title(ENTRY)), new TranslationTextComponent(desc(ENTRY)), null, FrameType.TASK, true, true, false).withCriterion("use_artifact", EventTrigger.Instance.cruxiteArtifact()).register(advancementSaver, Minestuck.MOD_ID+":minestuck/entry");
		Advancement alchemy = Advancement.Builder.builder().withParent(entry).withDisplay(MSItems.CAPTCHA_CARD, new TranslationTextComponent(title(ALCHEMY)), new TranslationTextComponent(desc(ALCHEMY)), null, FrameType.TASK, true, true, false).withCriterion("use_punch_designix", PunchDesignixTrigger.Instance.any()).register(advancementSaver, Minestuck.MOD_ID+":minestuck/alchemy");
		Advancement newModus = Advancement.Builder.builder().withParent(alchemy).withDisplay(MSItems.HASHMAP_MODUS_CARD, new TranslationTextComponent(title(NEW_MODUS)), new TranslationTextComponent(desc(NEW_MODUS)), null, FrameType.TASK, true, true, false).withCriterion("change_modus_type", ChangeModusTrigger.Instance.any()).register(advancementSaver, Minestuck.MOD_ID+":minestuck/new_modus");
		Advancement allModi = changeModusCriteria(Advancement.Builder.builder().withParent(newModus).withDisplay(MSItems.QUEUESTACK_MODUS_CARD, new TranslationTextComponent(title(ALL_MODI)), new TranslationTextComponent(desc(ALL_MODI)), null, FrameType.TASK, true, true, false).withRequirementsStrategy(IRequirementsStrategy.AND)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/all_modi");
		Advancement goldSeeds = Advancement.Builder.builder().withParent(alchemy).withDisplay(MSBlocks.GOLD_SEEDS, new TranslationTextComponent(title(GOLD_SEEDS)), new TranslationTextComponent(desc(GOLD_SEEDS)), null, FrameType.TASK, true, true, false).withCriterion("plant_gold_seeds", PlacedBlockTrigger.Instance.placedBlock(MSBlocks.GOLD_SEEDS)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/gold_seeds");
		Advancement frenchFry = Advancement.Builder.builder().withParent(alchemy).withDisplay(MSItems.FRENCH_FRY, new TranslationTextComponent(title(FRENCH_FRY)), new TranslationTextComponent(desc(FRENCH_FRY)), null, FrameType.TASK, true, true, false).withCriterion("has_french_fry", ConsumeItemTrigger.Instance.forItem(MSItems.FRENCH_FRY)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/french_fry");
		Advancement melonOverload = Advancement.Builder.builder().withParent(alchemy).withDisplay(MSItems.MELONSBANE, new TranslationTextComponent(title(MELON_OVERLOAD)), new TranslationTextComponent(desc(MELON_OVERLOAD)), null, FrameType.TASK, true, true, true).withCriterion("melon_overload", EventTrigger.Instance.melonOverload()).register(advancementSaver, Minestuck.MOD_ID+":minestuck/melon_overload");
		Advancement treeModus = Advancement.Builder.builder().withParent(newModus).withDisplay(MSItems.TREE_MODUS_CARD, new TranslationTextComponent(title(TREE_MODUS)), new TranslationTextComponent(desc(TREE_MODUS)), null, FrameType.TASK, true, true, false).withCriterion("tree_root", TreeModusRootTrigger.Instance.count(MinMaxBounds.IntBound.atLeast(16))).register(advancementSaver, Minestuck.MOD_ID+":minestuck/tree_modus");
		Advancement killOgre = Advancement.Builder.builder().withParent(entry).withDisplay(MSItems.POGO_HAMMER, new TranslationTextComponent(title(KILL_OGRE)), new TranslationTextComponent(desc(KILL_OGRE)), null, FrameType.TASK, true, true, false).withCriterion("kill_ogre", KilledTrigger.Instance.playerKilledEntity(EntityPredicate.Builder.create().type(MSEntityTypes.OGRE))).register(advancementSaver, Minestuck.MOD_ID+":minestuck/kill_ogre");
		Advancement returnNode = Advancement.Builder.builder().withParent(entry).withDisplay(Items.RED_BED, new TranslationTextComponent(title(RETURN_NODE)), new TranslationTextComponent(desc(RETURN_NODE)), null, FrameType.TASK, true, true, false).withCriterion("touch_return_node", EnterBlockTrigger.Instance.forBlock(MSBlocks.RETURN_NODE)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/return_node");
		Advancement dungeon = Advancement.Builder.builder().withParent(returnNode).withDisplay(MSBlocks.FROST_BRICKS, new TranslationTextComponent(title(DUNGEON)), new TranslationTextComponent(desc(DUNGEON)), null, FrameType.TASK, true, true, false).withCriterion("imp_dungeon", PositionTrigger.Instance.forLocation(LocationPredicate.forFeature(MSFeatures.IMP_DUNGEON))).register(advancementSaver, Minestuck.MOD_ID+":minestuck/dungeon");
		Advancement commune = Advancement.Builder.builder().withParent(entry).withDisplay(MSItems.STONE_SLAB, new TranslationTextComponent(title(COMMUNE)), new TranslationTextComponent(desc(COMMUNE)), null, FrameType.TASK, true, true, false).withRequirementsStrategy(IRequirementsStrategy.AND).withCriterion("talk_to_consort", ConsortTalkTrigger.Instance.any()).withCriterion("visit_village", PositionTrigger.Instance.forLocation(LocationPredicate.forFeature(MSFeatures.CONSORT_VILLAGE))).register(advancementSaver, Minestuck.MOD_ID+":minestuck/commune");
		Advancement bugs = consumeBugCriteria(Advancement.Builder.builder().withParent(commune).withDisplay(MSItems.CHOCOLATE_BEETLE, new TranslationTextComponent(title(BUGS)), new TranslationTextComponent(desc(BUGS)), null, FrameType.TASK, true, true, false).withRequirementsStrategy(IRequirementsStrategy.OR)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/bugs");
		Advancement shadyBuyer = Advancement.Builder.builder().withParent(commune).withDisplay(MSItems.ROCK_COOKIE, new TranslationTextComponent(title(SHADY_BUYER)), new TranslationTextComponent(desc(SHADY_BUYER)), null, FrameType.TASK, true, true, false).withCriterion("buy_item", ConsortItemTrigger.Instance.forType(EnumConsort.MerchantType.SHADY)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/shady_buyer");
		Advancement longTimeComing = Advancement.Builder.builder().withParent(root).withDisplay(MSItems.SERVER_DISK, new TranslationTextComponent(title(LONG_TIME_COMING)), new TranslationTextComponent(desc(LONG_TIME_COMING)), null, FrameType.TASK, true, true, false).withCriterion("click_capsule", InventoryChangeTrigger.Instance.forItems(MSItems.SERVER_DISK)).register(advancementSaver, Minestuck.MOD_ID+":minestuck/long_time_coming");
	}
	
	private static Advancement.Builder changeModusCriteria(Advancement.Builder builder)
	{
		for(ModusType<?> type : Arrays.asList(ModusTypes.STACK, ModusTypes.QUEUE, ModusTypes.QUEUE_STACK, ModusTypes.TREE, ModusTypes.HASH_MAP, ModusTypes.SET))
		{
			builder = builder.withCriterion(type.getRegistryName().getPath(), InventoryChangeTrigger.Instance.forItems(type.getItem()));
		}
		return builder;
	}
	
	private static Advancement.Builder consumeBugCriteria(Advancement.Builder builder)
	{
		for(IItemProvider item : Arrays.asList(MSItems.BUG_ON_A_STICK, MSItems.CHOCOLATE_BEETLE, MSItems.CONE_OF_FLIES, MSItems.GRASSHOPPER, MSItems.JAR_OF_BUGS))
		{
			builder = builder.withCriterion(item.asItem().getRegistryName().getPath(), ConsumeItemTrigger.Instance.forItem(item));
		}
		return builder;
	}
	
	private static String title(String name)
	{
		return "advancements."+name+".title";
	}
	private static String desc(String name)
	{
		return "advancements."+name+".description";
	}
	
	@Override
	public void act(DirectoryCache cache)
	{
		Path outputFolder = generator.getOutputFolder();
		Set<ResourceLocation> savedAdvancements = Sets.newHashSet();
		
		buildAdvancements(advancement -> save(cache, outputFolder, savedAdvancements, advancement));
	}
	
	private static void save(DirectoryCache cache, Path outputFolder, Set<ResourceLocation> savedAdvancements, Advancement advancement)
	{
		if(!savedAdvancements.add(advancement.getId()))
		{
			throw new IllegalStateException("Duplicate advancement " + advancement.getId());
		} else
		{
			Path jsonPath = outputFolder.resolve("data/" + advancement.getId().getNamespace() + "/advancements/" + advancement.getId().getPath() + ".json");
			
			try
			{
				IDataProvider.save(GSON, cache, advancement.copy().serialize(), jsonPath);
			} catch(IOException e)
			{
				Debug.logger.error("Couldn't save advancement {}", jsonPath, e);
			}
		}
	}
	
	@Override
	public String getName()
	{
		return "Minestuck Advancements";
	}
}