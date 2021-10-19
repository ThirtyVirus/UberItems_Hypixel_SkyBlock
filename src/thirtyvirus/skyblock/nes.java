package thirtyvirus.skyblock;

import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import thirtyvirus.skyblock.commands.WandOops;
import thirtyvirus.skyblock.events.UberEvent;
import thirtyvirus.skyblock.helpers.utils;
import thirtyvirus.skyblock.items.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.UberMaterial;
import thirtyvirus.uber.helpers.*;

import java.util.*;

public class nes extends JavaPlugin {

    public void onEnable() {

        // enforce UberItems dependancy
        if (Bukkit.getPluginManager().getPlugin("UberItems") == null) {
            this.getLogger().severe("Not Enough SkyBlock requires UberItems! disabled because UberItems dependency not found");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // register commands, events, UberItems, and UberMaterials
        registerCommands();
        registerEvents();
        registerUberMaterials();
        registerUberItems();

        // post confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");
    }
    public void onDisable() {
        // posts exit message in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been disabled");
    }

    // register commands
    private void registerCommands() {
        getCommand("wandoops").setExecutor(new WandOops());
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new UberEvent(), this);
    }

    private void registerUberItems() {

        UberItems.putItem("stone_platform", new stone_platform(Material.EGG, "Stone Platform", UberRarity.COMMON,
                false, true, false,
                Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.EGG),
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.STONE_BRICKS, 8),
                        new ItemStack(Material.STONE_BRICKS, 8)), false, 1)));

        UberItems.putItem("magical_water_bucket", new magical_water_bucket(Material.WATER_BUCKET, "Magical Water Bucket", UberRarity.COMMON,
                false, false, false,
                Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.IRON_INGOT),
                        UberItems.getMaterial("enchanted_ice").makeItem(1),
                        new ItemStack(Material.IRON_INGOT),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.IRON_INGOT),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("plumbers_sponge", new plumbers_sponge(Material.SPONGE, "Plumber's Sponge", UberRarity.UNCOMMON,
                true, true, true,
                Collections.singletonList(new UberAbility("Drain", AbilityType.RIGHT_CLICK, "Instructions: 1. Place on water. 2. Drains other water. 3. Double-bill client. " + ChatColor.DARK_GRAY + "Thanks Plumber Joe!")),
                null));

        UberItems.putItem("grappling_hook", new grappling_hook(Material.FISHING_ROD, "Grappling Hook", UberRarity.UNCOMMON,
                false, false, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK),
                        UberItems.getMaterial("enchanted_string").makeItem(1),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_string").makeItem(1)), false, 1)));

        UberItems.putItem("aspect_of_the_end", new aspect_of_the_end(Material.DIAMOND_SWORD, "Aspect Of The End", UberRarity.RARE,
                false, false, false,
                Collections.singletonList(new UberAbility("Instant Transmission", AbilityType.RIGHT_CLICK, "Teleport " + ChatColor.GREEN + "8 blocks" + ChatColor.GRAY + " ahead of you and gain " + ChatColor.GREEN + "+50 " + ChatColor.WHITE + "✦ Speed" + ChatColor.GRAY + " for " + ChatColor.GREEN + "3 seconds")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_eye_of_ender").makeItem(16),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_eye_of_ender").makeItem(16),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_diamond").makeItem(1),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("zombie_sword", new zombie_sword(Material.IRON_SWORD, "Zombie Sword", UberRarity.RARE,
                false, false, false,
                Collections.singletonList(new UberAbility("Instant Heal", AbilityType.RIGHT_CLICK, "Heal for " +
                        ChatColor.RED + "1.5 hearts" + ChatColor.GRAY + " and heal players within " +
                        ChatColor.GREEN + "7" + ChatColor.GRAY + " blocks for " + ChatColor.RED + "0.5 hearts" + ChatColor.GRAY +
                        ". " + ChatColor.DARK_GRAY + "Charges: " + ChatColor.YELLOW + "4" + ChatColor.DARK_GRAY + " / " + ChatColor.GREEN + "15s")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("zombie_heart").makeItem(1),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("zombie_heart").makeItem(1),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("ornate_zombie_sword", new ornate_zombie_sword(Material.GOLDEN_SWORD, "Ornate Zombie Sword", UberRarity.EPIC,
                false, false, false,
                Collections.singletonList(new UberAbility("Instant Heal", AbilityType.RIGHT_CLICK, "Heal for " +
                        ChatColor.RED + "2 hearts" + ChatColor.GRAY + " and heal players within " +
                        ChatColor.GREEN + "7" + ChatColor.GRAY + " blocks for " + ChatColor.RED + "1 heart" + ChatColor.GRAY +
                        ". " + ChatColor.DARK_GRAY + "Charges: " + ChatColor.YELLOW + "5" + ChatColor.DARK_GRAY + " / " + ChatColor.GREEN + "15s")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_gold_block").makeItem(1),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("golden_powder").makeItem(1),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getItem("zombie_sword").makeItem(1),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("florid_zombie_sword", new florid_zombie_sword(Material.GOLDEN_SWORD, "Florid Zombie Sword", UberRarity.LEGENDARY,
                false, false, false,
                Collections.singletonList(new UberAbility("Instant Heal", AbilityType.RIGHT_CLICK, "Heal for " +
                        ChatColor.RED + "2.5 hearts" + ChatColor.GRAY + " and heal players within " +
                        ChatColor.GREEN + "8" + ChatColor.GRAY + " blocks for " + ChatColor.RED + "1.5 hearts" + ChatColor.GRAY +
                        ". " + ChatColor.DARK_GRAY + "Charges: " + ChatColor.YELLOW + "7" + ChatColor.DARK_GRAY + " / " + ChatColor.GREEN + "15s")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("healing_tissue").makeItem(12),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("healing_tissue").makeItem(12),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getItem("ornate_zombie_sword").makeItem(1),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("ember_rod", new ember_rod(Material.BLAZE_ROD, "Ember Rod", UberRarity.EPIC,
                false, false, false,
                Collections.singletonList(new UberAbility("Fire Blast!", AbilityType.RIGHT_CLICK, "Shoot 3 FireBalls in rapid succession in front of you!", 30)),
                null));

        UberItems.putItem("jungle_axe", new jungle_axe(Material.WOODEN_AXE, "Jungle Axe", UberRarity.UNCOMMON,
                false, false, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        UberItems.getMaterial("enchanted_jungle_wood").makeItem(1),
                        UberItems.getMaterial("enchanted_jungle_wood").makeItem(1),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_jungle_wood").makeItem(1),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("treecapitator", new treecapitator(Material.GOLDEN_AXE, "Treecapitator", UberRarity.EPIC,
                false, false, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getItem("jungle_axe").makeItem(1),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(64)), false, 1)));

        UberItems.putItem("stonk", new stonk(Material.GOLDEN_PICKAXE, "Stonk", UberRarity.EPIC,
                false, false, false,
                Collections.singletonList(new UberAbility("Mining Speed Boost", AbilityType.RIGHT_CLICK, "Grants " +
                        ChatColor.GREEN + "Efficiency 10" + ChatColor.GRAY + " for " + ChatColor.GREEN + "20s" + ChatColor.GRAY + ". " + ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + "120s")),
                null));

        UberItems.putItem("juju_shortbow", new juju_shortbow(Material.BOW, "Juju Shortbow", UberRarity.EPIC,
                false, false, false,
                Collections.singletonList(new UberAbility("Instantly Shoots!", AbilityType.RIGHT_CLICK, "Hits " + ChatColor.RED + "3" + " mobs on impact. /newline Can damage endermen.")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_eye_of_ender").makeItem(32),
                        UberItems.getMaterial("enchanted_string").makeItem(64),
                        UberItems.getMaterial("null_ovoid").makeItem(32),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_string").makeItem(64),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_quartz_block").makeItem(32),
                        UberItems.getMaterial("enchanted_string").makeItem(64)), false, 1)));

        UberItems.putItem("emerald_blade", new emerald_blade(Material.EMERALD, "Emerald Blade", UberRarity.EPIC,
                false, false, true, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_emerald_block").makeItem(1),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_emerald_block").makeItem(1),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("phantom_rod", new phantom_rod(Material.FISHING_ROD, "Phantom Rod", UberRarity.LEGENDARY,
                false, false, true,
                Collections.singletonList(new UberAbility("Phantom Impel", AbilityType.LEFT_CLICK, "Terrify hooked enemies pushing them away and dealing massive damage")), null));

        UberItems.putItem("builders_wand", new builders_wand(Material.BLAZE_ROD, "Builder's Wand", UberRarity.LEGENDARY,
                false, false, true,
                Collections.singletonList(new UberAbility("Contruction!", AbilityType.RIGHT_CLICK, "Right click the face of any block to extend all connected block faces. /newline " + ChatColor.DARK_GRAY + "(consumes blocks from your inventory)")),
                null));

        UberItems.putItem("runaans_bow", new runaans_bow(Material.BOW, "Runaan's Bow", UberRarity.LEGENDARY,
                false, false, true,
                Collections.singletonList(new UberAbility("Triple Shot", AbilityType.RIGHT_CLICK,
                        "Shoots 3 arrows at a time! The 2 /newline extra arrows deal " +
                                ChatColor.GREEN + "40%" + ChatColor.GRAY + " of the /newline damage and home to targets.")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_bone").makeItem(64),
                        UberItems.getMaterial("enchanted_string").makeItem(64),
                        UberItems.getMaterial("enchanted_bone").makeItem(64),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_string").makeItem(64),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_bone").makeItem(64),
                        UberItems.getMaterial("enchanted_string").makeItem(64)), false, 1)));

        UberItems.putItem("souls_rebound", new souls_rebound(Material.BOW, "Souls Rebound", UberRarity.EPIC,
                false, false, false,
                Collections.emptyList(), null));

        UberItems.putItem("aspect_of_the_dragons", new aspect_of_the_dragons(Material.DIAMOND_SWORD, "Aspect of the Dragons", UberRarity.LEGENDARY,
                false, false, false,
                Collections.singletonList(new UberAbility("Dragon Rage", AbilityType.RIGHT_CLICK, "All Monsters and Players /newline in front of you take " + ChatColor.GREEN + "4" + ChatColor.GRAY + " damage. Hit monsters take large knockback.")),
                null));

    }
    private void registerUberMaterials() {
        UberItems.putMaterial("enchanted_ice", new UberMaterial(Material.ICE, "Enchanted Ice",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.ICE)));

        UberItems.putMaterial("enchanted_rotten_flesh", new UberMaterial(Material.ROTTEN_FLESH, "Enchanted Rotten Flesh",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.ROTTEN_FLESH)));

        UberItems.putMaterial("healing_tissue", new UberMaterial(Material.ROTTEN_FLESH, "Healing Tissue",
                UberRarity.RARE, false, true, false, "", null));

        UberItems.putMaterial("enchanted_jungle_wood", new UberMaterial(Material.JUNGLE_LOG, "Enchanted Jungle Wood",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.JUNGLE_LOG)));

        UberItems.putMaterial("zombie_heart", new UberMaterial(Utilities.getSkull("http://textures.minecraft.net/texture/71d7c816fc8c636d7f50a93a0ba7aaeff06c96a561645e9eb1bef391655c531"),
                "Zombie's Heart", UberRarity.RARE, true, false, false, "",
                utils.gser(UberItems.getMaterial("enchanted_rotten_flesh").makeItem(32))));

        UberItems.putMaterial("enchanted_emerald", new UberMaterial(Material.EMERALD, "Enchanted Emerald", UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.EMERALD)));

        UberItems.putMaterial("enchanted_emerald_block", new UberMaterial(Material.EMERALD_BLOCK, "Enchanted Emerald Block",
                UberRarity.RARE, true, false, false, "",
                utils.gser(UberItems.getMaterial("enchanted_emerald").makeItem(32))));

        UberItems.putMaterial("enchanted_bone", new UberMaterial(Material.BONE, "Enchanted Bone",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.BONE)));

        UberItems.putMaterial("enchanted_string", new UberMaterial(Material.STRING, "Enchanted String",
                UberRarity.UNCOMMON, true, true, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STRING, 64),
                        new ItemStack(Material.STRING, 64),
                        new ItemStack(Material.STRING, 64),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR)), true, 1)));

        UberItems.putMaterial("enchanted_obsidian", new UberMaterial(Material.OBSIDIAN, "Enchanted Obsidian",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.OBSIDIAN)));

        UberItems.putMaterial("enchanted_quartz", new UberMaterial(Material.QUARTZ, "Enchanted Quartz",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.QUARTZ)));

        UberItems.putMaterial("enchanted_quartz_block", new UberMaterial(Material.QUARTZ_BLOCK, "Enchanted Quartz Block",
                UberRarity.RARE, true, true, false, "",
                utils.gser(UberItems.getMaterial("enchanted_quartz").makeItem(32))));

        UberItems.putMaterial("enchanted_gold", new UberMaterial(Material.GOLD_INGOT, "Enchanted Gold",
                UberRarity.UNCOMMON, true, true, false, "",
                utils.gser(Material.QUARTZ)));

        UberItems.putMaterial("enchanted_gold_block", new UberMaterial(Material.GOLD_BLOCK, "Enchanted Gold Block",
                UberRarity.RARE, true, true, false, "",
                utils.gser(UberItems.getMaterial("enchanted_gold").makeItem(32))));

        UberItems.putMaterial("enchanted_eye_of_ender", new UberMaterial(Material.ENDER_EYE, "Enchanted Eye of Ender",
                UberRarity.UNCOMMON, true, true, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.BLAZE_POWDER, 16),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.BLAZE_POWDER, 16),
                        UberItems.getMaterial("enchanted_ender_pearl").makeItem(16),
                        new ItemStack(Material.BLAZE_POWDER, 16),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.BLAZE_POWDER, 16),
                        new ItemStack(Material.AIR)), true, 1)));

        UberItems.putMaterial("null_sphere", new UberMaterial(Material.FIREWORK_STAR, "Null Sphere",
                UberRarity.UNCOMMON, true, true, false, "", null));

        UberItems.putMaterial("null_ovoid", new UberMaterial(Material.ENDERMAN_SPAWN_EGG, "Null Ovoid",
                UberRarity.RARE, true, true, false, "",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("null_sphere").makeItem(32),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("null_sphere").makeItem(32),
                        UberItems.getMaterial("enchanted_obsidian").makeItem(32),
                        UberItems.getMaterial("null_sphere").makeItem(32),
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("null_sphere").makeItem(32),
                        new ItemStack(Material.AIR)), true, 1)));

        UberItems.putMaterial("golden_powder", new UberMaterial(Material.GLOWSTONE_DUST, "Golden Powder",
                UberRarity.EPIC, true, true, false, "", null));
    }
}