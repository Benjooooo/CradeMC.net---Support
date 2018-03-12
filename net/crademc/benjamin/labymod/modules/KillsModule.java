package net.crademc.benjamin.labymod.modules;

import net.crademc.benjamin.labymod.CradeMod;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class KillsModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Kills";
    }

    @Override
    public String getDisplayValue() {
        return CradeMod.getInstance().getStats().getKills();
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.DIAMOND_SWORD);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Display Kills";
    }

    @Override
    public String getDescription() {
        return "Zeigt deine aktuellen Kills an.";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return CradeMod.CATEGORY;
    }

    @Override
    public boolean isShown() {
        return (CradeMod.getInstance().getServer().enabled) && (CradeMod.getInstance().getPlayerInfo().isPlaying());
    }

}
