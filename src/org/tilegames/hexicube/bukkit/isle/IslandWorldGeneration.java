package org.tilegames.hexicube.bukkit.isle;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;

public final class IslandWorldGeneration extends JavaPlugin implements Listener
{
	public static int islandSpacing, islandStartY;
	public static double[] rarityModifiers;
	public static int[] islandChances;
	public static int islandTotalChance;
	public static double dungeonChance;
	
	public static boolean enabled;
	
	public static boolean spawnVerified = false;
	
	@Override
	public void onEnable()
	{
		enabled = true;
		
		//IslePopulator.interpretSchematic("1.1=5.0=3.3.3=0.0.0.0.0.0.-1.0.-1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.-1.0.-1");
		//IslePopulator.interpretSchematic("1.0=5.1=5.1.5=-1.-1.0.-1.-1.-1.0.0.0.-1.0.0.0.0.0.-1.0.0.0.-1.-1.-1.0.-1.-1");
		
		islandSpacing = getConfig().getInt("island.spacing", 8);
		getConfig().set("island.spacing", islandSpacing);
		islandStartY = getConfig().getInt("island.height", 150);
		getConfig().set("island.height", islandStartY);
		rarityModifiers = new double[10];
		rarityModifiers[0] = getConfig().getDouble("rarity.coalore", 1);
		getConfig().set("rarity.coalore", rarityModifiers[0]);
		rarityModifiers[1] = getConfig().getDouble("rarity.ironore", 1);
		getConfig().set("rarity.ironore", rarityModifiers[1]);
		rarityModifiers[2] = getConfig().getDouble("rarity.goldore", 1);
		getConfig().set("rarity.goldore", rarityModifiers[2]);
		rarityModifiers[3] = getConfig().getDouble("rarity.redstoneore", 1);
		getConfig().set("rarity.redstoneore", rarityModifiers[3]);
		rarityModifiers[4] = getConfig().getDouble("rarity.diamondore", 1);
		getConfig().set("rarity.diamondore", rarityModifiers[4]);
		rarityModifiers[5] = getConfig().getDouble("rarity.emeraldore", 1);
		getConfig().set("rarity.emeraldore", rarityModifiers[5]);
		rarityModifiers[6] = getConfig().getDouble("rarity.fluidpool", 1);
		getConfig().set("rarity.fluidpool", rarityModifiers[6]);
		rarityModifiers[7] = getConfig().getDouble("rarity.gravelpatch", 1);
		getConfig().set("rarity.gravelpatch", rarityModifiers[7]);
		rarityModifiers[8] = getConfig().getDouble("rarity.caves", 1);
		getConfig().set("rarity.caves", rarityModifiers[8]);
		rarityModifiers[9] = getConfig().getDouble("rarity.quartzore", 1);
		getConfig().set("rarity.quartzore", rarityModifiers[9]);
		
		dungeonChance = getConfig().getDouble("dungeonchance", 0.02);
		getConfig().set("dungeonchance", dungeonChance);
		
		islandChances = new int[9];
		islandChances[0] = getConfig().getInt("islandchance.plains", 4);
		getConfig().set("islandchance.plains", islandChances[0]);
		islandChances[1] = getConfig().getInt("islandchance.forest", 8);
		getConfig().set("islandchance.forest", islandChances[1]);
		islandChances[2] = getConfig().getInt("islandchance.taiga", 6);
		getConfig().set("islandchance.taiga", islandChances[2]);
		islandChances[3] = getConfig().getInt("islandchance.swamp", 3);
		getConfig().set("islandchance.swamp", islandChances[3]);
		islandChances[4] = getConfig().getInt("islandchance.jungle", 4);
		getConfig().set("islandchance.jungle", islandChances[4]);
		islandChances[5] = getConfig().getInt("islandchance.desert", 4);
		getConfig().set("islandchance.desert", islandChances[5]);
		islandChances[6] = getConfig().getInt("islandchance.nether", 1);
		getConfig().set("islandchance.nether", islandChances[6]);
		islandChances[7] = getConfig().getInt("islandchance.ender", 1);
		getConfig().set("islandchance.ender", islandChances[7]);
		islandChances[8] = getConfig().getInt("islandchance.mushroom", 1);
		getConfig().set("islandchance.mushroom", islandChances[8]);
		islandTotalChance = 0;
		for(int a = 0; a < islandChances.length; a++)
		{
			if(islandChances[a] < 0) islandChances[a] = 0;
			islandTotalChance += islandChances[a];
		}
		saveConfig();
	}
	
	@Override
	public void onDisable()
	{
		enabled = false;
	}
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		if(!enabled) getServer().getPluginManager().enablePlugin(this);
		return new ChunkGen();
	}
}