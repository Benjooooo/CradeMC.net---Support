package net.crademc.benjamin.labymod.player;

public class PlayerInfo {

    private boolean online;
    private String server;
    private String minigame;
    private String coins;
    private String group;

    public PlayerInfo() {
        this.online = false;
        this.server = "Lobby";
        this.coins = "0";
        this.group = "Spieler";
    }

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

    public boolean isInLobby() {
        if(isOnline()) {
            if(getServer().startsWith("Lobby")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isPlaying() {
        if(isOnline()) {
            if(getServer().startsWith("Lobby") || getServer().startsWith("PrivateServer")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean isPlayingKnockOut() {
        if(isOnline()) {
            if(getServer().startsWith("KnockOut")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isPlayingGodWars() {
        if(isOnline()) {
            if(getServer().startsWith("GodWars")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isPlayingQuickSG() {
        if(isOnline()) {
            if(getServer().startsWith("QuickSG")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
