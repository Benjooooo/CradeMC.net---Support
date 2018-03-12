package net.crademc.benjamin.labymod;

import net.crademc.benjamin.labymod.modules.*;
import net.crademc.benjamin.labymod.player.Minigame;
import net.crademc.benjamin.labymod.player.PlayerInfo;
import net.crademc.benjamin.labymod.player.Stats;
import net.crademc.benjamin.labymod.server.CradeServer;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ServerData;

import java.util.List;

public class CradeMod extends LabyModAddon {

    private static CradeMod instance;
    public static ModuleCategory CATEGORY;
    private static String prefix;

    private CradeServer server;
    private PlayerInfo playerInfo;
    private Stats stats;
    private Minigame minigame;

    @Override
    public void onEnable() {
        instance = this;
        ModuleCategoryRegistry.loadCategory(CATEGORY = new ModuleCategory("CradeMC.net", true, new ControlElement.IconData("crademc/textures/icon.png")));
        prefix = "§8» §a§lCradeMod §8» ";
        this.playerInfo = new PlayerInfo();
        this.stats = new Stats();
        this.minigame = new Minigame();
        this.api.registerServerSupport(this, this.server = new CradeServer());
        this.getServer().getData();
        registerEvents();
        this.api.registerModule(new CoinsModule());
        this.api.registerModule(new RankModule());
        this.api.registerModule(new KillsModule());
        this.api.registerModule(new DeathsModule());
        this.api.registerModule(new MapModule());
        this.api.registerModule(new MapchangeModule());
        this.api.registerModule(new KitModule());
        this.api.registerModule(new KitchangeModule());

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void loadConfig() {
        getServer().enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
        /*
        getServer().displayCoins = !getConfig().has("displayCoins") || getConfig().get("displayCoins").getAsBoolean();
        getServer().displayRank = !getConfig().has("displayRank") || getConfig().get("displayRank").getAsBoolean();
        getServer().displayKills = !getConfig().has("displayKills") || getConfig().get("displayKills").getAsBoolean();
        getServer().displayDeaths = !getConfig().has("displayDeaths") || getConfig().get("displayDeaths").getAsBoolean();
        */
    }

    @Override
    protected void fillSettings(List<SettingsElement> settingsElements) {
        settingsElements.add(new HeaderElement("General"));
        settingsElements.add(new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getServer().enabled = accepted;
                getConfig().addProperty("enabled", accepted);
                saveConfig();
            }
        }, getServer().enabled));

        settingsElements.add(new HeaderElement("Anzeige Optionen bei den Ingame-Anzeigen > CradeMC.net"));

        /*
        settingsElements.add(new HeaderElement("Misc"));
        settingsElements.add(new BooleanElement("Display Coins", new ControlElement.IconData(Material.GOLD_NUGGET), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getServer().displayCoins = accepted;
                getConfig().addProperty("displayCoins", accepted);
                saveConfig();
            }
        }, getServer().displayCoins));
        settingsElements.add(new BooleanElement("Display Rank", new ControlElement.IconData(Material.ARROW), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getServer().displayRank = accepted;
                getConfig().addProperty("displayRank", accepted);
                saveConfig();
            }
        }, getServer().displayRank));
        settingsElements.add(new HeaderElement("Stats"));
        settingsElements.add(new BooleanElement("Display Kills", new ControlElement.IconData(Material.DIAMOND_SWORD), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getServer().displayKills = accepted;
                getConfig().addProperty("displayKills", accepted);
                saveConfig();
            }
        }, getServer().displayKills));
        settingsElements.add(new BooleanElement("Display Deaths", new ControlElement.IconData(Material.BONE), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getServer().displayDeaths = accepted;
                getConfig().addProperty("displayDeaths", accepted);
                saveConfig();
            }
        }, getServer().displayDeaths));
        */
    }

    public void registerEvents() {
        this.api.getEventManager().registerOnJoin(new Consumer<ServerData>() {
            @Override
            public void accept(ServerData serverData) {
                if(serverData.getIp().equalsIgnoreCase("crademc.net")) {
                    getPlayerInfo().setOnline(true);
                } else {
                    getPlayerInfo().setOnline(false);
                }
            }
        });
        this.api.getEventManager().registerOnQuit(new Consumer<ServerData>() {
            @Override
            public void accept(ServerData serverData) {
                getPlayerInfo().setOnline(false);
            }
        });
    }

    public static CradeMod getInstance() {
        return instance;
    }

    public CradeServer getServer() {
        return this.server;
    }

    public static String getPrefix() {
        return prefix;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public Stats getStats() {
        return stats;
    }

    public Minigame getMinigame() {
        return minigame;
    }
}
