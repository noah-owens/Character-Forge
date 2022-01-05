package Character.Forge;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * CharClass class creates an object which represents one of the main character species in the game.
 * At some future point it will become the parent of a "Subrace" inherited class.
 * <p>
 * @version v0.0.0
 * @author Noah Owens
 */
public class Race {
    @Getter @Setter String name;
    @Getter @Setter ArrayList<Stat> statChanges;
    @Getter @Setter ArrayList<CharFeature> features;

    /**
     * Race constructor builds an object with a name, stat changes, and list of features.
     * <p>
     * @param name what the species is called
     * @param statChanges the stats which are added to as a feature of being this race, and the quantity by which they are increased
     * @param features a list of the features which characters of this race gain
     */
    public Race(String name, ArrayList<Stat> statChanges, ArrayList<CharFeature> features) {
        this.name = name;
        this.statChanges = statChanges;
        this.features = features;
    }
}