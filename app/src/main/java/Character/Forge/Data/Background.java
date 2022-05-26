package Character.Forge.Data;

import Character.Forge.Util.Rand;

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
    private boolean errorFlag;

    private Rand r = Rand.getInstance();

    public Background(String name, ArrayList<String> features, ArrayList<String> proficiencies, ArrayList<String> profPool, int profGranted, boolean errorFlag) {
        this.name = name;
        this.features = features;
        this.proficiencies = proficiencies;
        this.profPool = profPool;
        this.profGranted = profGranted;
        this.errorFlag = errorFlag;

        proficiencies = generateProficiencies(profPool, profGranted);
    }

    private ArrayList<String> generateProficiencies(ArrayList<String> pool, int granted) {
        for (int i = 0; i < granted; i++) {
            String randProficiency = pool.remove(r.randInt(pool.size()));
            proficiencies.add(randProficiency);
        }

        return proficiencies;
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

    public boolean isErrorFlag() {
        return errorFlag;
    }

    /*---------------------------------|
    |         Setter Methods           |
    |---------------------------------*/

    public void setFlag(boolean b) {
        errorFlag = b;
    }
}
