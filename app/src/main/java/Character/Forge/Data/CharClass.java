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

import Character.Forge.Behavior.RandomHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * CharClass class creates an object which represents one of the main class types in the game.
 * At some future point it will become the parent of a "CharSubclass" inherited class.
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class CharClass {
    @Getter String name;
    @Getter int hitDie;
    @Getter @Setter ArrayList<CharFeature> features;
    @Getter @Setter ArrayList<Equipment> equipment;


    /**
     * CharClass constructor builds an object with a name, hit die, and list of features.
     * <p>
     * @param name the one word title of the character class
     * @param hitDie an integer (either 6, 8, 10, or 12) representing the size of the die a character of this class rolls for hitpoints and short rest healing
     * @param features a list of the features which characters of this class gain.
     * @param equipment a list of equipment to be added to character's overall equipment list
     */
    public CharClass(String name, int hitDie, ArrayList<CharFeature> features, ArrayList<Equipment> equipment) {
        this.name = name;
        this.hitDie = hitDie;
        this.features = features;
        this.equipment = equipment;
    }

    /**
     * Assign equipment using PHB equipment quick select based on CharClass.name
     */
    public void quickSelectEquipment() {
        switch (name) {
            case "Artificer":
                break;
            case "Barbarian":
                break;
            case "Bard":
                break;
            case "Cleric":
                break;
            case "Druid":
                break;
            case "Fighter":
                break;
            case "Monk":
                break;
            case "Paladin":
                break;
            case "Ranger":
                break;
            case "Rogue":
                break;
            case "Sorcerer":
                break;
            case "Warlock":
                break;
            case "Wizard":
                break;
            default:
                equipment.add(new Equipment("Rubber Duck"));
                break;
        }
    }

    /**
     * Makes a choice between two Equipment objects
     * @param e1 choice 1
     * @param e2 choice 2
     * @return chosen equipment
     */
    private Equipment pickOneEquip(Equipment e1, Equipment e2) {
        Equipment pickedEquip;
        RandomHelper r = new RandomHelper();
        int randNum = r.rollDie(2);

        if (randNum == 1) {
            pickedEquip = e1;
        } else {
            pickedEquip = e2;
        }

        return pickedEquip;
    }
}