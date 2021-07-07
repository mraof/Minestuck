package com.mraof.minestuck.network;

import com.mraof.minestuck.entity.LotusFlowerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;

public class LotusFlowerPacket implements PlayToClientPacket
{
	private final int entityID;
	private final LotusFlowerEntity.Animation animation;
	
	public static LotusFlowerPacket createPacket(LotusFlowerEntity entity, LotusFlowerEntity.Animation animation)
	{
		return new LotusFlowerPacket(entity.getEntityId(), animation);
	}
	
	private LotusFlowerPacket(int entityID, LotusFlowerEntity.Animation animation)
	{
		this.entityID = entityID;
		this.animation = animation;
	}
	
	@Override
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(entityID);
		buffer.writeInt(animation.ordinal());
	}
	
	public static LotusFlowerPacket decode(PacketBuffer buffer)
	{
		int entityID = buffer.readInt(); //readInt spits out the values you gave to the PacketBuffer in encode in that order
		LotusFlowerEntity.Animation animation = LotusFlowerEntity.Animation.values()[buffer.readInt()];
		
		return new LotusFlowerPacket(entityID, animation);
	}
	
	@Override
	public void execute()
	{
		Entity entity = Minecraft.getInstance().world.getEntityByID(entityID);
		if(entity instanceof LotusFlowerEntity)
		{
			((LotusFlowerEntity) entity).setAnimationFromPacket(animation);
		}
	}
}
