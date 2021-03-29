package com.mraof.minestuck.world.gen.feature;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.world.gen.feature.structure.*;
import com.mraof.minestuck.world.gen.feature.structure.castle.*;
import com.mraof.minestuck.world.gen.feature.structure.village.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public final class MSStructurePieces
{
	public static IStructurePieceType FROG_TEMPLE;
	
	public static IStructurePieceType GATE_PILLAR;
	public static IStructurePieceType GATE_MUSHROOM;
	public static IStructurePieceType SMALL_RUIN;
	
	public static IStructurePieceType IMP_ENTRY, IMP_ENTRY_CORRIDOR;
	public static IStructurePieceType IMP_STRAIGHT_CORRIDOR, IMP_CROSS_CORRIDOR, IMP_TURN_CORRIDOR;
	public static IStructurePieceType IMP_RETURN_ROOM, IMP_ALT_RETURN_ROOM;
	public static IStructurePieceType IMP_SPAWNER_ROOM, IMP_SPAWNER_CORRIDOR, IMP_LARGE_SPAWNER_CORRIDOR;
	public static IStructurePieceType IMP_BOOKCASE_ROOM, IMP_OGRE_CORRIDOR;
	
	public static IStructurePieceType VILLAGE_PATH;
	public static IStructurePieceType MARKET_CENTER;
	public static IStructurePieceType ROCK_CENTER;
	public static IStructurePieceType CACTUS_PYRAMID_CENTER;
	public static IStructurePieceType MUSHROOM_TOWER_CENTER;
	public static IStructurePieceType TURTLE_WELL_CENTER;
	public static IStructurePieceType RADIO_TOWER_CENTER;
	public static IStructurePieceType PIPE_HOUSE_1, HIGH_PIPE_HOUSE_1, SMALL_TOWER_STORE;
	public static IStructurePieceType SHELL_HOUSE_1, TURTLE_MARKET_1, TURTLE_TEMPLE_1;
	public static IStructurePieceType SMALL_VILLAGE_TENT_1, LARGE_VILLAGE_TENT_1, SMALL_TENT_STORE;
	public static IStructurePieceType HIGH_NAK_HOUSING_1, HIGH_NAK_MARKET, HIGH_NAK_INN;
	
	public static IStructurePieceType SKAIA_CASTLE_START, SKAIA_CASTLE_SOLID, SKAIA_CASTLE_WALL;
	public static IStructurePieceType SKAIA_CASTLE_ROOM, SKAIA_CASTLE_LIBRARY, SKAIA_CASTLE_STAIRCASE;
	
	/**
	 * Should only be called by {@link com.mraof.minestuck.world.gen.feature.MSFeatures} on feature registry.
	 */
	static void init()
	{
		FROG_TEMPLE = register(FrogTemplePiece::new, Minestuck.MOD_ID+":frog_temple");
		
		GATE_PILLAR = register(GatePillarPiece::new, Minestuck.MOD_ID+":gate_pillar");
		GATE_MUSHROOM = register(GateMushroomPiece::new, Minestuck.MOD_ID+":gate_mushroom");
		SMALL_RUIN = register(SmallRuinPiece::new, Minestuck.MOD_ID+":small_ruin");
		
		IMP_ENTRY = register(ImpDungeonStart.EntryPiece::new, Minestuck.MOD_ID+":imp_entry");
		IMP_ENTRY_CORRIDOR = register(ImpDungeonPieces.EntryCorridor::new, Minestuck.MOD_ID+":imp_entry_corridor");
		IMP_STRAIGHT_CORRIDOR = register(ImpDungeonPieces.StraightCorridor::new, Minestuck.MOD_ID+":imp_straight_corridor");
		IMP_CROSS_CORRIDOR = register(ImpDungeonPieces.CrossCorridor::new, Minestuck.MOD_ID+":imp_cross_corridor");
		IMP_TURN_CORRIDOR = register(ImpDungeonPieces.TurnCorridor::new, Minestuck.MOD_ID+":imp_turn_corridor");
		IMP_RETURN_ROOM = register(ImpDungeonPieces.ReturnRoom::new, Minestuck.MOD_ID+":imp_return_room");
		IMP_ALT_RETURN_ROOM = register(ImpDungeonPieces.ReturnRoomAlt::new, Minestuck.MOD_ID+":imp_alt_return_room");
		IMP_SPAWNER_ROOM = register(ImpDungeonPieces.SpawnerRoom::new, Minestuck.MOD_ID+":imp_spawner_room");
		IMP_SPAWNER_CORRIDOR = register(ImpDungeonPieces.SpawnerCorridor::new, Minestuck.MOD_ID+":imp_spawner_corridor");
		IMP_LARGE_SPAWNER_CORRIDOR = register(ImpDungeonPieces.LargeSpawnerCorridor::new, Minestuck.MOD_ID+":imp_large_spawner_corridor");
		IMP_BOOKCASE_ROOM = register(ImpDungeonPieces.BookcaseRoom::new, Minestuck.MOD_ID+":imp_bookcase_room");
		IMP_OGRE_CORRIDOR = register(ImpDungeonPieces.OgreCorridor::new, Minestuck.MOD_ID+":imp_ogre_corridor");
		
		VILLAGE_PATH = register(ConsortVillagePieces.VillagePath::new, Minestuck.MOD_ID+":village_path");
		MARKET_CENTER = register(ConsortVillageCenter.VillageMarketCenter::new, Minestuck.MOD_ID+":market_center");
		ROCK_CENTER = register(ConsortVillageCenter.RockCenter::new, Minestuck.MOD_ID+":rock_center");
		CACTUS_PYRAMID_CENTER = register(ConsortVillageCenter.CactusPyramidCenter::new, Minestuck.MOD_ID+":cactus_pyramid_center");
		MUSHROOM_TOWER_CENTER = register(SalamanderVillagePieces.RuinedTowerMushroomCenter::new, Minestuck.MOD_ID+":mushroom_tower_center");
		TURTLE_WELL_CENTER = register(TurtleVillagePieces.TurtleWellCenter::new, Minestuck.MOD_ID+":turtle_well_center");
		RADIO_TOWER_CENTER = register(NakagatorVillagePieces.RadioTowerCenter::new, Minestuck.MOD_ID+":radio_tower_center");
		PIPE_HOUSE_1 = register(SalamanderVillagePieces.PipeHouse1::new, Minestuck.MOD_ID+":pipe_house_1");
		HIGH_PIPE_HOUSE_1 = register(SalamanderVillagePieces.HighPipeHouse1::new, Minestuck.MOD_ID+":high_pipe_house_1");
		SMALL_TOWER_STORE = register(SalamanderVillagePieces.SmallTowerStore::new, Minestuck.MOD_ID+":small_tower_store");
		SHELL_HOUSE_1 = register(TurtleVillagePieces.ShellHouse1::new, Minestuck.MOD_ID+":village_shell_house_1");
		TURTLE_MARKET_1 = register(TurtleVillagePieces.TurtleMarket1::new, Minestuck.MOD_ID+":turtle_market_1");
		TURTLE_TEMPLE_1 = register(TurtleVillagePieces.TurtleTemple1::new, Minestuck.MOD_ID+":turtle_temple_1");
		HIGH_NAK_HOUSING_1 = register(NakagatorVillagePieces.HighNakHousing1::new, Minestuck.MOD_ID+":high_nak_housing_1");
		HIGH_NAK_MARKET = register(NakagatorVillagePieces.HighNakMarket1::new, Minestuck.MOD_ID+":high_nak_market");
		HIGH_NAK_INN = register(NakagatorVillagePieces.HighNakInn1::new, Minestuck.MOD_ID+":high_nak_inn");
		SMALL_VILLAGE_TENT_1 = register(IguanaVillagePieces.SmallTent1::new, Minestuck.MOD_ID+":small_village_tent_1");
		LARGE_VILLAGE_TENT_1 = register(IguanaVillagePieces.LargeTent1::new, Minestuck.MOD_ID+":large_village_tent_1");
		SMALL_TENT_STORE = register(IguanaVillagePieces.SmallTentStore::new, Minestuck.MOD_ID+":small_tent_store");
		
		SKAIA_CASTLE_START = register(CastleStartPiece::new, Minestuck.MOD_ID+":skaia_castle_start");
		SKAIA_CASTLE_SOLID = register(CastleSolidPiece::new, Minestuck.MOD_ID+":skaia_castle_solid");
		SKAIA_CASTLE_WALL = register(CastleWallPiece::new, Minestuck.MOD_ID+":skaia_castle_wall");
		SKAIA_CASTLE_ROOM = register(CastleRoomPiece::new, Minestuck.MOD_ID+":skaia_castle_room");
		SKAIA_CASTLE_LIBRARY = register(CastleLibraryPiece::new, Minestuck.MOD_ID+":skaia_castle_library");
		SKAIA_CASTLE_STAIRCASE = register(CastleStaircasePiece::new, Minestuck.MOD_ID+":skaia_castle_staircase");
	}
	
	private static IStructurePieceType register(IStructurePieceType type, String name) {
		return Registry.register(Registry.STRUCTURE_PIECE, name, type);
	}
}