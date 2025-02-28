package com.mills.test;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.projectiles.ProjectileSource;

public class EventHandler implements Listener {

    @org.bukkit.event.EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();

        if (e.getHand().equals(EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().equals(Main.onHoe())) {
                    player.launchProjectile(Egg.class, player.getLocation().getDirection());

                    if (e.getClickedBlock() != null) {
                        if (e.getClickedBlock().getType().equals(Material.GRASS_BLOCK) || e.getClickedBlock().getType().equals(Material.DIRT)) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onBreak(PlayerHarvestBlockEvent e) {

        if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().equals(Main.onHoe())) {

            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You can't break blocks with this item!");
        }
    }

    @org.bukkit.event.EventHandler
    public void onBreakBlock(BlockBreakEvent e) {

        if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().equals(Main.onHoe())) {

            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You can't break blocks with this item!");

        }

    }

    @org.bukkit.event.EventHandler
    public void onShoot(ProjectileHitEvent e) {

        if (e.getEntity() instanceof Egg) {

            ProjectileSource shooter = e.getEntity().getShooter();

            if (shooter instanceof Player) {
                Player player = (Player) shooter;

                if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().equals(Main.onHoe())) {

                    if (e.getHitEntity() instanceof LivingEntity) {
                        LivingEntity entityType = (LivingEntity) e.getHitEntity();

                        if (Main.mobs.contains(entityType.getType())) {

                            entityType.setHealth(0.0);

                        } else if (!Main.mobs.contains(entityType.getType())) {

                            entityType.damage(0);

                        }
                    }
                }
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onEgg(PlayerEggThrowEvent e) {

        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().equals(Main.onHoe())) {

            e.setHatching(false);

        }
    }
}
