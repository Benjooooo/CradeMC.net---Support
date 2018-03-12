package net.crademc.benjamin.labymod.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.crademc.benjamin.labymod.CradeMod;
import net.labymod.api.events.ServerMessageEvent;
import net.labymod.api.events.TabListEvent;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

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
            LabyMod.getInstance().displayMessageInChat(CradeMod.getPrefix() + "§7Herzlich willkommen, §a" + LabyMod.getInstance().getPlayerName() + "§7, auf §fCradeMC§7.");
        }
    }

    @Override
    public ChatDisplayAction handleChatMessage(String s, String s1) {
        return null;
    }

    @Override
    public void handlePluginMessage(String s, PacketBuffer packetBuffer) {

    }

    @Override
    public void handleTabInfoMessage(TabListEvent.Type type, String s, String s1) {

    }

    @Override
    public void addModuleLines(List<Server.DisplayLine> lines) {
        /*if(this.enabled) {
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
        }*/
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
                    if(CradeMod.getInstance().getPlayerInfo().isPlaying()) {
                        if(s.equalsIgnoreCase("stats")) {
                            if(object.has("kills")) {
                                CradeMod.getInstance().getStats().setKills(object.get("kills").getAsString());
                            }
                            if(object.has("deaths")) {
                                CradeMod.getInstance().getStats().setDeaths(object.get("deaths").getAsString());
                            }
                        }
                        if(s.equalsIgnoreCase("knockout")) {
                            if(object.has("map")) {
                                CradeMod.getInstance().getMinigame().setMap(object.get("map").getAsString());
                            }
                            if(object.has("mapcountdown")) {
                                CradeMod.getInstance().getMinigame().setMapcountdown(object.get("mapcountdown").getAsInt());
                            }
                            if(object.has("kit")) {
                                CradeMod.getInstance().getMinigame().setKit(object.get("kit").getAsString());
                            }
                            if(object.has("kitcountdown")) {
                                CradeMod.getInstance().getMinigame().setKitcountdown(object.get("kitcountdown").getAsInt());
                            }
                        }
                        if(s.equalsIgnoreCase("godwars")) {
                            if(object.has("map")) {
                                CradeMod.getInstance().getMinigame().setMap(object.get("map").getAsString());
                            }
                        }
                        if(s.equalsIgnoreCase("quicksg")) {
                            if(object.has("map")) {
                                CradeMod.getInstance().getMinigame().setMap(object.get("map").getAsString());
                            }
                        }
                    }
                }
            }
        });
    }

    public String getTimer(long ms) {
        long sec = ms / 1000;
        long min = sec / 60;
        sec = sec - (min * 60);
        return (min == 0 ? "00:" : (min < 10 ? "0" : "") + min + ":") + (sec == 0 ? "00" : (sec < 10 ? "0" : "") + sec);
    }

}
