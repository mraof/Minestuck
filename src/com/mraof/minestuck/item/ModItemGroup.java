package com.mraof.minestuck.item;

import com.mraof.minestuck.block.MinestuckBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class ModItemGroup extends ItemGroup
{
	public static final ItemGroup MAIN = new ModItemGroup("minestuck", () -> new ItemStack(MinestuckItems.disk));
	public static final ItemGroup LANDS = new ModItemGroup("minestuckLands", () -> new ItemStack(MinestuckBlocks.GLOWING_MUSHROOM));
	public static final ItemGroup WEAPONS = new ModItemGroup("minestuckWeapons", () -> new ItemStack(MinestuckItems.zillyhooHammer));
	
	private final Supplier<ItemStack> icon;
	
	public ModItemGroup(String label, Supplier<ItemStack> icon)
	{
		super(label);
		this.icon = icon;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public ItemStack createIcon()
	{
		return icon.get();
	}
}