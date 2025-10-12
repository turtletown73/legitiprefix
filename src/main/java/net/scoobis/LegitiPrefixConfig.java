package net.scoobis;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "legitiprefix")
public class LegitiPrefixConfig implements ConfigData {
    @ConfigEntry.Category("General")
    public boolean LegitiEnabled = true;
    @ConfigEntry.Category("General")
    public boolean LegitiPascalCase = true;
    @ConfigEntry.Category("General")
    public String LegitiPrefix = "legiti";
    @ConfigEntry.Category("General")
    public String[] LegitiCustomBlacklist = new String[] {"have", "has", "had", "do", "does", "did", "can", "could", "will", "would", "shall", "should", "might", "must", "be", "am", "is", "are", "was", "were", "being", "there", "theres", "on"};
    @ConfigEntry.Category("General")
    public String[] LegitiCustomWhitelist = new String[] {};

    @ConfigEntry.Category("Types")
    public boolean LegitiForceAllTypes = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixNouns = true;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixAdjectives = true;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixVerbs = true;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixAdverbs = true;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixDeterminers = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixPronouns = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixPrepositions = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixParticles = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixConjunctions = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixModals = false;
    @ConfigEntry.Category("Types")
    public boolean LegitiPrefixMisc = false;
}