package net.crademc.benjamin.labymod.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.crademc.benjamin.labymod.CradeMod;
import net.labymod.api.LabyModAPI;
import net.labymod.api.LabyModAddon;
import net.labymod.api.events.ServerMessageEvent;
import net.labymod.api.events.TabListEvent;
import net.labymod.ingamegui.moduletypes.ColoredTextModule;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

import java.util.Collections;
import java.util.List;

public class CradeServer extends Server {

    public boolean enabled;
    public boolean displayCoins;
    public boolean displayRank;
    public boolean displayKills;
    public boolean displayDeaths;

    public CradeServer() {
        super("CradeMC", "crademc.net");
    }

    @Override
    public void onJoin(ServerData serverData) {
        if(CradeMod.getInstance().getServer().enabled) {
            LabyMod.getInstance().displayMessageInChat(CradeMod.getPrefix() + "§7Herzlich willkommen, §a" + LabyMod.getInstance().getPlayerName() + " §7, auf §fCradeMC§7.");
        }
    }

    @Override
    public ChatDisplayAction handleChatMessage(String s, String s1) throws Exception {
        return null;
    }

    @Override
    public void handlePluginMessage(String s, PacketBuffer packetBuffer) throws Exception {

    }

    @Override
    public void handleTabInfoMessage(TabListEvent.Type type, String s, String s1) throws Exception {

    }

    @Override
    public void addModuleLines(List<Server.DisplayLine> lines) {
        if(this.enabled) {
            if(this.displayCoins) {
                lines.add(new DisplayLine("Coins", Collections.singletonList(ColoredTextModule.Text.getText(CradeMod.getInstance().getPlayerInfo().getCoins()))));
            }
            if(this.displayRank) {
                lines.add(new DisplayLine("Rang", Collections.singletonList(ColoredTextModule.Text.getText(CradeMod.getInstance().getPlayerInfo().getGroup()))));
            }
            if(CradeMod.getInstance().getPlayerInfo().getServer().equalsIgnoreCase("Lobby") || CradeMod.getInstance().getPlayerInfo().getMinigame() == null) {
                if(lines.contains("Kills")) {
                    lines.remove("Kills");
                }
                if(lines.contains("Deaths")) {
                    lines.remove("Deaths");
                }
            } else {
                if(this.displayKills) {
                    lines.add(new DisplayLine("Kills", Collections.singletonList(ColoredTextModule.Text.getText(CradeMod.getInstance().getStats().getKills()))));
                }
                if(this.displayDeaths) {
                    lines.add(new DisplayLine("Deaths", Collections.singletonList(ColoredTextModule.Text.getText(CradeMod.getInstance().getStats().getDeaths()))));
                }
            }
        }
    }

    @Override
    protected void initConfig(JsonObject config) {}

    @Override
    public void fillSubSettings(List<SettingsElement> settingsElements) { }

    public void getData() {
        CradeMod.getInstance().getApi().getEventManager().register(new ServerMessageEvent() {
            @Override
            public void onServerMessage(String s, JsonElement jsonElement) {
                if(jsonElement.isJsonObject()) {
                    JsonObject object = jsonElement.getAsJsonObject();
                    if(s.equalsIgnoreCase("playerinfo")) {
                        if(object.has("server")) {
                            CradeMod.getInstance().getPlayerInfo().setServer(object.get("server").getAsString());
                        }
                        if(object.has("minigame")) {
                            CradeMod.getInstance().getPlayerInfo().setMinigame(object.get("minigame").getAsString());
                        }
                        if(object.has("group")) {
                            CradeMod.getInstance().getPlayerInfo().setGroup(object.get("group").getAsString());
                        }
                        if(object.has("coins")) {
                            CradeMod.getInstance().getPlayerInfo().setCoins(object.get("coins").getAsString());
                        }
                    }
                    if(!CradeMod.getInstance().getPlayerInfo().getServer().equalsIgnoreCase("Lobby")) {
                        if(s.equalsIgnoreCase("stats")) {
                            if(object.has("kills")) {
                                CradeMod.getInstance().getStats().setKills(object.get("kills").getAsString());
                            }
                            if(object.has("deaths")) {
                                CradeMod.getInstance().getStats().setDeaths(object.get("deaths").getAsString());
                            }
                        }
                    }
                }
            }
        });
    }

}
