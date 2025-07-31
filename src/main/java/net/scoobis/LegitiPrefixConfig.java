package net.scoobis;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "legitiprefix")
public class LegitiPrefixConfig implements ConfigData {
    public boolean LegitiEnabled = true;
    public boolean LegitiPascalCase = true;
}