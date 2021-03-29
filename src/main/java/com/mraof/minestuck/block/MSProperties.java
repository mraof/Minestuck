package com.mraof.minestuck.block;

import com.mraof.minestuck.block.machine.ComputerBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;

/**
 * Minestuck version of {@link net.minecraft.state.properties.BlockStateProperties}
 */
public class MSProperties
{
	public static final BooleanProperty MAIN = BooleanProperty.create("main");
	public static final BooleanProperty HAS_CARD = BooleanProperty.create("has_card");
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty BROKEN = BooleanProperty.create("broken");
	public static final BooleanProperty ALPHA = BooleanProperty.create("alpha");
	public static final BooleanProperty OMEGA = BooleanProperty.create("omega");
	public static final BooleanProperty RED = BooleanProperty.create("red");
	public static final BooleanProperty GREEN = BooleanProperty.create("green");
	public static final BooleanProperty BLUE = BooleanProperty.create("blue");
	public static final BooleanProperty UNACTIVATED = BooleanProperty.create("unactivated");
	
	public static final IntegerProperty COUNT_0_2 = IntegerProperty.create("count", 0, 2);
	
	public static final EnumProperty<EnumCassetteType> CASSETTE = EnumProperty.create("cassette", EnumCassetteType.class);
	public static final EnumProperty<EnumDowelType> DOWEL = EnumProperty.create("dowel", EnumDowelType.class, EnumDowelType.DOWEL, EnumDowelType.CARVED_DOWEL);
	public static final EnumProperty<EnumDowelType> DOWEL_OR_NONE = EnumProperty.create("dowel", EnumDowelType.class);
	public static final EnumProperty<CruxiteDowelBlock.Type> DOWEL_BLOCK = EnumProperty.create("dowel", CruxiteDowelBlock.Type.class);
	public static final EnumProperty<Direction.Axis> AXIS_2 = EnumProperty.create("axis_2", Direction.Axis.class);
	public static final EnumProperty<ComputerBlock.State> COMPUTER_STATE = EnumProperty.create("state", ComputerBlock.State.class);
}