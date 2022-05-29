package Character.Forge.Data;

/**
 * Feature objects represent a character's extraordinary powers and abilities.
 * <p>
 * A feature object will contain the name of the feature, the level it becomes available at, and whether it
 * affects stats/proficiencies.
 * <p>
 * @version 0.5.0
 * @author Noah Owens
 */
public class Feature {
    private final String name;
    private final int level;
    private final boolean tweakNeeded;

    /**
     * Initializes a feature object. All fields final and not settable.
     * @param name The display name of the feature, which will be featured in the character sheet UI
     * @param level The level at which the feature becomes available to a character
     * @param tweakNeeded Flags the FeatureHandler object to reference what needs to happen to the character sheet.
     */
    public Feature(String name, int level, boolean tweakNeeded) {
        this.name = name;
        this.level = level;
        this.tweakNeeded = tweakNeeded;
    }

    /*---------------------------------|
    |         Getter Methods           |
    |---------------------------------*/

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public boolean isTweakNeeded() {
        return tweakNeeded;
    }
}
