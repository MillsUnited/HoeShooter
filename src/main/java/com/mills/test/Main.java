package com.mills.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin implements Listener {

    public static List<EntityType> mobs = List.of(EntityType.ZOMBIE, EntityType.SPIDER, EntityType.CREEPER, EntityType.SKELETON, EntityType.ENDERMAN);

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new EventHandler(), this);
        getCommand("hoe").setExecutor(new HoeCommand());

    }

    public static ItemStack onHoe() {

        ItemStack hoe = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta hoemeta = hoe.getItemMeta();
        hoemeta.setDisplayName(ChatColor.DARK_RED + "Mob Killer");
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (EntityType type : mobs) {
            lore.add(ChatColor.RED + "Kills " + type.name().substring(0, 1) + type.name().substring(1).toLowerCase() + "s");
        }
        hoemeta.setLore(lore);
        hoemeta.setUnbreakable(true);
        hoemeta.setEnchantmentGlintOverride(true);
        hoe.setItemMeta(hoemeta);
        return hoe;
    }
}
