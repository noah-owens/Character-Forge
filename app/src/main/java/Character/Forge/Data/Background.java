/**
 * MIT License
 *
 * Copyright (c) 2022 Noah Owens
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
