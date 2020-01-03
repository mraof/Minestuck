package com.mraof.minestuck.item.crafting.alchemy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

import java.util.Objects;

/**
 * Container for a GristType + integer combination that might be useful when iterating through a GristSet.
 */
public class GristAmount
{
	private final GristType type;
	private final long amount;
	
	public GristAmount(GristType type, long amount)
	{
		this.type = type;
		this.amount = amount;
	}
	
	public GristType getType()
	{
		return type;
	}

	public long getAmount()
	{
		return amount;
	}
	
	/**
	 * @return a value estimate for this grist amount
	 */
	public double getValue()
	{
		return type.getValue()*amount;
	}
	
	@Override
	public String toString()
	{
		return "gristAmount:[type="+type.getRegistryName()+",amount="+amount+"]";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		GristAmount that = (GristAmount) o;
		return amount == that.amount &&
				type.equals(that.type);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(type, amount);
	}
	
	public void write(PacketBuffer buffer)
	{
		buffer.writeRegistryId(getType());
		buffer.writeLong(getAmount());
	}
	
	public static GristAmount read(PacketBuffer buffer)
	{
		GristType type = buffer.readRegistryIdSafe(GristType.class);
		long amount = buffer.readLong();
		return new GristAmount(type, amount);
	}
	
	private static String makeNBTPrefix(String prefix)
	{
		return prefix != null && !prefix.isEmpty() ? prefix + "_" : "";
	}
	
	public CompoundNBT write(CompoundNBT nbt, String keyPrefix)
	{
		keyPrefix = makeNBTPrefix(keyPrefix);
		getType().write(nbt, keyPrefix + "type");
		nbt.putLong(keyPrefix + "amount", getAmount());
		return nbt;
	}
	
	public static GristAmount read(CompoundNBT nbt, String keyPrefix)
	{
		keyPrefix = makeNBTPrefix(keyPrefix);
		GristType type = GristType.read(nbt, keyPrefix + "type");
		long amount = nbt.getLong(keyPrefix + "amount");
		return new GristAmount(type, amount);
	}
}