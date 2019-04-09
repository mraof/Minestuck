package com.mraof.minestuck.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

import com.mraof.minestuck.inventory.captchalouge.CaptchaDeckHandler;
import com.mraof.minestuck.inventory.specibus.StrifePortfolioHandler;
import com.mraof.minestuck.inventory.specibus.StrifeSpecibus;
import com.mraof.minestuck.item.MinestuckItems;
import com.mraof.minestuck.util.Debug;
import com.mraof.minestuck.util.IdentifierHandler;
import com.mraof.minestuck.util.KindAbstratusList;
import com.mraof.minestuck.util.MinestuckPlayerData;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;

public class SpecibusPacket extends MinestuckPacket
{

	public static final byte PORTFOLIO = 0;
	public static final byte SPECIBUS_ADD = 1;
	public static final byte SPECIBUS_REMOVE = 2;
	public static final byte DECK_ADD = 3;
	public static final byte DECK_REMOVE = 4;
	
	public int itemIndex;
	public byte type;
	public NBTTagCompound nbt;
	public ArrayList<StrifeSpecibus> portfolio;
	private int specibusId;
	
	@Override
	public MinestuckPacket generatePacket(Object... data) 
	{
		byte type = (Byte) data[0];
		this.data.writeByte(type);
		if(data.length > 1)
		{
			switch(type)
			{

			case DECK_REMOVE:
			{
				if(data.length > 2) this.data.writeInt((Integer)data[2]);
				else 				this.data.writeInt(0);
			}
			case SPECIBUS_REMOVE:
			{
				if(data.length > 1) this.data.writeDouble((Integer)data[1]);
				else 				this.data.writeDouble(0);
			}
			break;
				default:
				//case PORTFOLIO: case SPECIBUS_ADD:
				{
					try
					{
						ByteArrayOutputStream bytes = new ByteArrayOutputStream();
						CompressedStreamTools.writeCompressed((NBTTagCompound)data[1], bytes);
						this.data.writeBytes(bytes.toByteArray());
					}
					catch (IOException e)
					{
						e.printStackTrace();
						return null;
					}
				}
				break;
			}
		}
		return this;
	}

	@Override
	public MinestuckPacket consumePacket(ByteBuf data) 
	{
		this.type = data.readByte();
		if(data.readableBytes() > 0)
		{
			switch(type)
			{
			case DECK_REMOVE:
			{
				this.itemIndex = data.readInt();
			}
			case SPECIBUS_REMOVE:
			{
				this.specibusId = (int)data.readDouble();
			}
			break;
			default:
			{
				byte[] bytes = new byte[data.readableBytes()];
				data.readBytes(bytes);
				try
				{
					this.nbt = CompressedStreamTools.readCompressed(new ByteArrayInputStream(bytes));
				}
				catch(IOException e)
				{
					e.printStackTrace();
					return null;
				}
			}
			break;
			
			}
		}
		return this;
	}

	@Override
	public void execute(EntityPlayer player) 
	{
		if(player != null && player.world != null && !player.world.isRemote)
		{
			switch(type)
			{
			case PORTFOLIO:
			{
				System.out.println("portfolio");
				portfolio = StrifePortfolioHandler.createPortfolio(nbt);
				MinestuckPlayerData.onPacketRecived(this);
				MinestuckPlayerData.setStrifePortfolio(IdentifierHandler.encode(player), portfolio);
				MinestuckPlayerData.setClientPortfolio(portfolio);
			}
			break;
			case SPECIBUS_ADD:
			{
				System.out.println("add");
				ArrayList<StrifeSpecibus> portfolio = MinestuckPlayerData.getStrifePortfolio(IdentifierHandler.encode(player));
				StrifeSpecibus specibus = new StrifeSpecibus(nbt);
				MinestuckPlayerData.setClientPortfolio(portfolio);
				MinestuckPlayerData.getStrifePortfolio(IdentifierHandler.encode(player)).add(specibus);
			}
			break;
			case SPECIBUS_REMOVE:
				System.out.println("remove");
				StrifeSpecibus specibus = StrifePortfolioHandler.getSpecibus((EntityPlayerMP) player, specibusId);
				MinestuckPlayerData.removeStrifeSpecibus(IdentifierHandler.encode(player), specibusId);
				
				if(specibus != null)				
					StrifePortfolioHandler.spawnItem((EntityPlayerMP) player, StrifePortfolioHandler.createSpecibusItem(specibus));
			break;
			case DECK_ADD:
			{
				System.out.println("deck add");
				StrifePortfolioHandler.addItemToDeck((EntityPlayerMP)player);
			}
			break;
			case DECK_REMOVE:
				System.out.println("deck remove");
				StrifePortfolioHandler.retrieveItem((EntityPlayerMP) player, specibusId, itemIndex);
			break;
			}
		}
		else
		{
			switch(type)
			{
			case PORTFOLIO:
			{
				System.out.println("null portfolio");
				portfolio = StrifePortfolioHandler.createPortfolio(nbt);
				MinestuckPlayerData.setClientPortfolio(portfolio);
				MinestuckPlayerData.setStrifePortfolio(IdentifierHandler.encode(player), portfolio);
			}
			break;
			}
		}
	}

	@Override
	public EnumSet<Side> getSenderSide() 
	{
		return EnumSet.allOf(Side.class);
	}
	
}