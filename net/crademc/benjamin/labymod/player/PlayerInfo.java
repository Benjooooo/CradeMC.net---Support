package net.crademc.benjamin.labymod.player;

public class PlayerInfo {

    private String server;
    private String minigame;
    private String coins;
    private String group;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getMinigame() {
        return minigame;
    }

    public void setMinigame(String minigame) {
        this.minigame = minigame;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String rank) {
        this.group = rank;
    }
}
