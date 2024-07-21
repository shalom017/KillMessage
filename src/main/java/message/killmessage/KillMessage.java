package message.killmessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class KillMessage extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("KillPlugin включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("KillPlugin выключен!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer != null) {
            ItemStack weapon = killer.getInventory().getItemInMainHand();
            String weaponName = getWeaponName(weapon);

            String message = ChatColor.GRAY + "[" + ChatColor.WHITE + "K" + ChatColor.GRAY + "] " +
                    ChatColor.RED + killer.getName() +
                    ChatColor.GRAY + " убил " + ChatColor.RED + victim.getName() +
                    ChatColor.GRAY + " с помощью " + ChatColor.YELLOW + weaponName;

            event.setDeathMessage(null);
            getServer().broadcastMessage(message);
        }
    }

    private String getWeaponName(ItemStack item) {
        if (item == null || item.getType().isAir()) {
            return "Кулаков";
        }

        String itemName = item.getType().name().toLowerCase().replace("_", " ");
        return translateWeaponName(itemName);
    }

    private String translateWeaponName(String englishName) {
        switch (englishName) {
            // Мечи
            case "wooden sword": return "Деревянного меча";
            case "stone sword": return "Каменного меча";
            case "iron sword": return "Железного меча";
            case "golden sword": return "Золотого меча";
            case "diamond sword": return "Алмазного меча";
            case "netherite sword": return "Незеритового меча";

            // Топоры
            case "wooden axe": return "Деревянного топора";
            case "stone axe": return "Каменного топора";
            case "iron axe": return "Железного топора";
            case "golden axe": return "Золотого топора";
            case "diamond axe": return "Алмазного топора";
            case "netherite axe": return "Незеритового топора";

            // Кирки
            case "wooden pickaxe": return "Деревянной кирки";
            case "stone pickaxe": return "Каменной кирки";
            case "iron pickaxe": return "Железной кирки";
            case "golden pickaxe": return "Золотой кирки";
            case "diamond pickaxe": return "Алмазной кирки";
            case "netherite pickaxe": return "Незеритовой кирки";

            // Лопаты
            case "wooden shovel": return "Деревянной лопаты";
            case "stone shovel": return "Каменной лопаты";
            case "iron shovel": return "Железной лопаты";
            case "golden shovel": return "Золотой лопаты";
            case "diamond shovel": return "Алмазной лопаты";
            case "netherite shovel": return "Незеритовой лопаты";

            // Мотыги
            case "wooden hoe": return "Деревянной мотыги";
            case "stone hoe": return "Каменной мотыги";
            case "iron hoe": return "Железной мотыги";
            case "golden hoe": return "Золотой мотыги";
            case "diamond hoe": return "Алмазной мотыги";
            case "netherite hoe": return "Незеритовой мотыги";

            // Луки и арбалеты
            case "bow": return "Лука";
            case "crossbow": return "Арбалета";

            // Трезубец
            case "trident": return "Трезубца";

            // Зелья
            case "splash potion": return "Взрывного зелья";
            case "lingering potion": return "Туманного зелья";

            // Всякое гвно
            case "fishing rod": return "Удочки";
            case "flint and steel": return "Огнива";
            case "tnt": return "Динамита";
            case "lava bucket": return "Ведра лавы";

            default: return englishName.substring(0, 1).toUpperCase() + englishName.substring(1);
        }
    }
}