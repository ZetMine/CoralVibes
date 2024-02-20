package net.zetmine.coralvibes;

import net.luckperms.api.LuckPerms;
import net.zetmine.coralvibes.commands.Broadcast;
import net.zetmine.coralvibes.commands.Hologram;
import net.zetmine.coralvibes.commands.Maintenance;
import net.zetmine.coralvibes.commands.Vanish;
import net.zetmine.coralvibes.listeners.*;
import net.zetmine.coralvibes.utils.ConfigManager;
import net.zetmine.coralvibes.utils.InventoryManager;
import net.zetmine.coralvibes.utils.ScoreboardUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


//FORMAT NOTE: configManager, lpApi

public class Main extends JavaPlugin {

    public LuckPerms lpApi;
    private static Main instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){

        instance = this;

        RegisteredServiceProvider<LuckPerms> lpProvider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (lpProvider != null) {
            lpApi = lpProvider.getProvider();
            getLogger().info("LuckPerms API loaded with success.");
        } else {
            getLogger().warning("LuckPerms API isn't found. Please make sure LuckPerms is downloaded.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        ConfigManager configManager =  new ConfigManager();
        configManager.loadConfig(this);

        Bukkit.getPluginManager().registerEvents(new InventoryManager(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDrop(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteraction(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(configManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSlotChange(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChat(lpApi), this);

        this.getCommand("broadcast").setExecutor(new Broadcast());
        this.getCommand("maintenance").setExecutor(new Maintenance(configManager));
        this.getCommand("hologram").setExecutor(new Hologram());
        this.getCommand("vanish").setExecutor(new Vanish());

        ScoreboardUpdater scoreboardUpdater = new ScoreboardUpdater();
        scoreboardUpdater.startUpdater();


    }
}
