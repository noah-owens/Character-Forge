package Character.Forge;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Background class creates an object which represents the background field in a character sheet,
 * containing a name, features, and additional equipment
 * <p>
 * @version v0.0.0
 * @author Noah Owens
 */
public class Background {
    @Getter @Setter String name;
    @Getter @Setter ArrayList<CharFeature> features;

    /**
     * Background constructor creates a background object with a name, and features
     * <p>
     * @param name the name of the background, provides a hint for possible roleplaying starts
     * @param features the mechanical benefits of a given background
     */
    public Background(String name, ArrayList<CharFeature> features) {
        this.name = name;
        this.features = features;
    }
}