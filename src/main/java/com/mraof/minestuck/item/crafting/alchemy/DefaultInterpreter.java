package com.mraof.minestuck.item.crafting.alchemy;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Collections;
import java.util.List;

public class DefaultInterpreter implements RecipeInterpreter
{
	public static final DefaultInterpreter INSTANCE = new DefaultInterpreter();
	
	@ObjectHolder("minestuck:default")
	public static final InterpreterSerializer<DefaultInterpreter> SERIALIZER = null;
	
	//TODO interpreter (perhaps setting) that makes the interpreter not remove container cost for ingredient
	
	@Override
	public List<Item> getOutputItems(IRecipe<?> recipe)
	{
		ItemStack stack = recipe.getRecipeOutput();
		return stack.isEmpty() ? Collections.emptyList() : Collections.singletonList(stack.getItem());
	}
	
	@Override
	public GristSet generateCost(IRecipe<?> recipe, Item item, GristCostGenerator.IngredientLookup ingredientInterpreter)
	{
		if(recipe.isDynamic())
			return null;
		
		GristSet totalCost = new GristSet();
		for(Ingredient ingredient : recipe.getIngredients())
		{
			GristSet ingredientCost = ingredientInterpreter.lookup(ingredient, true);
			if(ingredientCost == null)
				return null;
			else totalCost.addGrist(ingredientCost);
		}
		
		totalCost.scale(1F/recipe.getRecipeOutput().getCount(), false);	//Do not round down because it's better to have something cost a little to much than it possibly costing nothing
		
		return totalCost;
	}
	
	@Override
	public InterpreterSerializer<?> getSerializer()
	{
		return SERIALIZER;
	}
	
	public static class Serializer extends InterpreterSerializer<DefaultInterpreter>
	{
		@Override
		public DefaultInterpreter read(JsonElement json)
		{
			return INSTANCE;
		}
		
		@Override
		public JsonElement write(DefaultInterpreter interpreter)
		{
			return JsonNull.INSTANCE;
		}
	}
}