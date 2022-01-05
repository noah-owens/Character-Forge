package Character.Forge;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * CharClass class creates an object which represents one of the main class types in the game.
 * At some future point it will become the parent of a "CharSubclass" inherited class.
 * <p>
 * @version v0.0.0
 * @author Noah Owens
 */
public class CharClass {
    @Getter @Setter String name;
    @Getter @Setter int hitDie;
    @Getter @Setter ArrayList<CharFeature> features;

    /**
     * CharClass constructor builds an object with a name, hit die, and list of features.
     * <p>
     * @param name the one word title of the character class
     * @param hitDie an integer (either 6, 8, 10, or 12) representing the size of the die a character of this class rolls for hitpoints and short rest healing
     * @param features a list of the features which characters of this class gain.
     */
    public CharClass(String name, int hitDie, ArrayList<CharFeature> features) {
        this.name = name;
        this.hitDie = hitDie;
        this.features = features;
    }
}