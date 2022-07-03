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
 * CharClass objects represent classes and subclasses, with their associated proficiencies and features
 */
public class CharClass {
    private final CharClassName name;
    private final String spellcastingMod;
    private final int hitDie;
    private final ArrayList<String> features;
    private ArrayList<String> proficiencies;
    private final ArrayList<String> profPool;
    private final int profGranted;
    private boolean errorFlag;
    private CharClass subclass;

    private Rand r = Rand.getInstance();

    public CharClass(CharClassName name, String spellcastingMod, int hitDie, ArrayList<String> features, ArrayList<String> proficiencies, ArrayList<String> profPool, int profGranted, boolean errorFlag, CharClass subclass) {
        this.name = name;
        this.spellcastingMod = spellcastingMod;
        this.hitDie = hitDie;
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

    public CharClassName getName() {
        return name;
    }

    public String getSpellcastingMod() {
        return spellcastingMod;
    }

    public int getHitDie() {
        return hitDie;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public ArrayList<String> getProficiencies() {
        return proficiencies;
    }

    public ArrayList<String> getProfPool() {
        return profPool;
    }

    public int getProfGranted() {
        return profGranted;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public CharClass getSubclass() {
        return subclass;
    }

    /*---------------------------------|
    |         Setter Methods           |
    |---------------------------------*/

    public void setFlag(boolean b) {
        errorFlag = b;
    }
}
