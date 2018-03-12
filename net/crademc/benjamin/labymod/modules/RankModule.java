package net.crademc.benjamin.labymod.modules;

import net.crademc.benjamin.labymod.CradeMod;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class RankModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Rang";
    }

    @Override
    public String getDisplayValue() {
        return CradeMod.getInstance().getPlayerInfo().getGroup();
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.EMERALD);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Display Rank";
    }

    @Override
    public String getDescription() {
        return "Zeigt deinen aktuellen Rang an.";
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
        return (CradeMod.getInstance().getServer().enabled) && (CradeMod.getInstance().getPlayerInfo().isOnline());
    }

}
