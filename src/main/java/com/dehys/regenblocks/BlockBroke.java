package com.dehys.regenblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BlockBroke implements Listener{


    private Plugin plugin;

    BlockBroke(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        World world = block.getWorld();
        Location location = block.getLocation();
        Material material = block.getType();
        UUID claim = plugin.getClaim(block).getUniqueId();

        if (e.getPlayer().hasPermission("regenblocks.break") && e.getPlayer().isSneaking()) return;

        for (UUID c : plugin.claims) {
            e.getPlayer().sendMessage(c.toString());
            if(c.equals(claim)){
                e.getPlayer().sendMessage(c.toString()+"-!");
                for (String m : plugin.recordedMaterials) {
                    e.getPlayer().sendMessage(m);
                    String materialName = m.split(":")[0];
                    long regenTime = Long.parseLong(m.split(":")[1]);

                    if(materialName.equalsIgnoreCase(block.getType().name())){
                        if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                            if(!(block.getDrops(e.getPlayer().getItemInHand()).isEmpty())) {
                                world.dropItemNaturally(location, new ItemStack(material, 1));
                            }
                        }
                        else {
                            for (ItemStack is : block.getDrops(e.getPlayer().getItemInHand())) {
                                world.dropItemNaturally(location, is);
                                e.getPlayer().sendMessage("DROP!");
                            }
                        }
                        new RegenBlock(world, claim, block, Plugin.currentTickTime()+regenTime);
//                        e.getPlayer().sendMessage(String.valueOf(block.getData()));
                        e.setCancelled(true);
                        block.setType(plugin.replacementBlock);
                        return;
                    }
                }
                return;
            }
        }
    }

}
