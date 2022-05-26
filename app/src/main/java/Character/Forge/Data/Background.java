package Character.Forge.Data;

import java.util.ArrayList;

/**
 * Background Class creates an object which represents the Background of the character (PHB Ch4)
 * @version 0.5.0
 * @author Noah Owens
 */
public class Background {
    private String name;
    private ArrayList<String> features;
    private ArrayList<String> proficiencies;
    private ArrayList<String> profPool;
    private int profGranted;
    private boolean dupeProficiencyFlag;

    public Background(String name, ArrayList<String> features, ArrayList<String> proficiencies, ArrayList<String> profPool, int profGranted, boolean dupeProficiencyFlag) {
        this.name = name;
        this.features = features;
        this.proficiencies = proficiencies;
        this.profPool = profPool;
        this.profGranted = profGranted;
        this.dupeProficiencyFlag = dupeProficiencyFlag;

        proficiencies = generateProficiencies(profPool, profGranted);
    }

    private ArrayList<String> generateProficiencies(ArrayList<String> pool, int granted) {
        for (int i = 0; i < granted; i++) {

        }
    }

    /*---------------------------------|
    |         Getter Methods           |
    |---------------------------------*/

    public String getName() {
        return name;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public ArrayList<String> getProficiencies() {
        return proficiencies;
    }

    public boolean isDupeProficiencyFlag() {
        return dupeProficiencyFlag;
    }
}
