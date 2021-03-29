package com.mraof.minestuck;

import com.mraof.minestuck.advancements.MSCriteriaTriggers;
import com.mraof.minestuck.client.ClientProxy;
import com.mraof.minestuck.computer.ProgramData;
import com.mraof.minestuck.computer.editmode.DeployList;
import com.mraof.minestuck.entity.MSEntityTypes;
import com.mraof.minestuck.entity.consort.ConsortDialogue;
import com.mraof.minestuck.entry.ComputerBlockProcess;
import com.mraof.minestuck.entry.EntryProcess;
import com.mraof.minestuck.entry.RSEntryBlockProcess;
import com.mraof.minestuck.entry.TransportalizerBlockProcess;
import com.mraof.minestuck.fluid.MSFluids;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.item.crafting.alchemy.GristTypes;
import com.mraof.minestuck.network.MSPacketHandler;
import com.mraof.minestuck.player.KindAbstratusList;
import com.mraof.minestuck.tileentity.MSTileEntityTypes;
import com.mraof.minestuck.world.biome.MSBiomes;
import com.mraof.minestuck.world.gen.MSSurfaceBuilders;
import com.mraof.minestuck.world.gen.feature.MSFillerBlockTypes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.WorldPersistenceHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.mraof.minestuck.Minestuck.MOD_ID;
import static com.mraof.minestuck.world.gen.OreGeneration.setupOverworldOreGeneration;
import static com.mraof.minestuck.world.gen.OverworldStructureGeneration.setupOverworldStructureGeneration;

@Mod(MOD_ID)
public class Minestuck
{
	public static final String MOD_NAME = "Minestuck";
	public static final String MOD_ID = "minestuck";
	
	public Minestuck()
	{
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MinestuckConfig.commonSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MinestuckConfig.clientSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MinestuckConfig.serverSpec);
		
		WorldPersistenceHooks.addHook(new MSWorldPersistenceHook());
		
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MSFluids.FLUIDS.register(eventBus);
		MSSurfaceBuilders.REGISTER.register(eventBus);
		MSBiomes.REGISTER.register(eventBus);
		MSTileEntityTypes.REGISTER.register(eventBus);
		GristTypes.GRIST_TYPES.register(eventBus);
	}
	
	/**
	 * Common setup, which happens in parallel with other mods.
	 * Only do thread-safe setup, such as internal setup of thread-safe registering.
	 */
	private void setup(final FMLCommonSetupEvent event)
	{
		DeferredWorkQueue.runLater(this::mainThreadSetup);
		
		//register channel handler
		MSPacketHandler.setupChannel();
		
		MSBiomes.init();
	}
	
	/**
	 * Handles any setup that is not thread-safe, and thus need to happen on the main thread.
	 * Typically meant for registering stuff.
	 */
	private void mainThreadSetup()
	{
		MSCriteriaTriggers.register();
		MSEntityTypes.registerPlacements();
		MSFillerBlockTypes.init();	//Not sure if this is thread safe, but better safe than sorry
		
		//register ore generation
		setupOverworldOreGeneration();
		
		//register structure generation
		setupOverworldStructureGeneration();
		
		ConsortDialogue.init();
		
		KindAbstratusList.registerTypes();
		DeployList.registerItems();
		
		ProgramData.registerProgram(0, new ItemStack(MSItems.CLIENT_DISK), ProgramData::onClientClosed);
		ProgramData.registerProgram(1, new ItemStack(MSItems.SERVER_DISK), ProgramData::onServerClosed);
		
		EntryProcess.addBlockProcessing(new ComputerBlockProcess());
		EntryProcess.addBlockProcessing(new TransportalizerBlockProcess());
		if(ModList.get().isLoaded("refinedstorage"))
			EntryProcess.addBlockProcessing(new RSEntryBlockProcess());
	}
	
	private void clientSetup(final FMLClientSetupEvent event)
	{
		ClientProxy.init();
	}
}