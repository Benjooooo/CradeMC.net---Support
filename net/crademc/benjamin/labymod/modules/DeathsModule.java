package net.crademc.benjamin.labymod.modules;

import net.crademc.benjamin.labymod.CradeMod;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class DeathsModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Deaths";
    }

    @Override
    public String getDisplayValue() {
        return CradeMod.getInstance().getStats().getDeaths();
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.BONE);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Display Deaths";
    }

    @Override
    public String getDescription() {
        return "Zeigt deine aktuellen Tode an.";
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
