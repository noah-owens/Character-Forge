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
package Character.Forge.Behavior;

import Character.Forge.Data.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * PlayerCharacter class creates an object representative of the completed character sheet.
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class PlayerCharacter {
    @Getter @Setter private String name;
    @Getter @Setter private int level;
    @Getter @Setter private CharClass charClass;
    @Getter @Setter private Race race;
    @Getter @Setter private int hp;
    @Getter @Setter private ArrayList<Stat> stats;
    @Getter @Setter private Background background;
    @Getter @Setter private String alignment;

    private ArrayList<String> equipment;
    private ArrayList<Spell> spells;
    private ArrayList<String> proficiencies;

    private final RandomHelper r = new RandomHelper();

    /**
     * PlayerCharacter constructor instantiates an ArrayList named "equipment"
     * for storing adventuring paraphernalia and likewise one for "spells"
     * <p>
     * @param name the character's given name
     * @param level an integer between 1 and 20 representative of the character's power
     * @param charClass the character's profession, expertise, or proclivity (warlock, fighter, cleric, etc.)
     * @param race the character's species (human, goblin, dwarf, etc.)
     * @param hp an integer representing a character's health points.
     * @param stats a list of six numeric attributes (STR, DEX, CON, INT, WIS, CHA) which represent a character's skillfulness in a certain area
     * @param background the character's sob story, which may include helpful talents or connections
     * @param alignment a character's moral guiding light represented in two-letter pairs (LG, NE, CG, NN)
     */
    public PlayerCharacter(String name, int level, CharClass charClass, Race race, int hp, ArrayList<Stat> stats, Background background, String alignment) {
        this.name = name;
        this.level = level;
        this.charClass = charClass;
        this.race = race;
        this.hp = hp;
        this.stats = stats;
        this.background = background;
        this.alignment = alignment;

        equipment = new ArrayList<>();
        spells = new ArrayList<>();
        proficiencies = new ArrayList<>();
    }

    /**
     * Non-parameterized constructor
     */
    public PlayerCharacter() {
        equipment = new ArrayList<>();
        spells = new ArrayList<>();
        proficiencies = new ArrayList<>();
    }

    /**
     * Returns a randomized Hp total (without considering constitution bonus) considering character level and class hit die
     * <p>
     * @return an Hp total ready to be adjusted with constitution
     */
    public int generateHp() {
        int hp = charClass.getHitDie();

        if(level > 1) {
            for(int i = 1; i < level; i++) {
                int addedHitPoints = r.rollDie(charClass.getHitDie());
                if (addedHitPoints < charClass.getHitDie()/2) {
                    addedHitPoints = charClass.getHitDie()/2;
                }
                hp += addedHitPoints;
            }
        }
        return hp;
    }

    /**
     * Returns an integer value equal to the hp passed into it + level * constitution bonus
     * <p>
     * @param unadjustedHp an integer Hp total (typically the unedited output of generateHp())
     * @return an integer representing the final hp total of a character
     */
    public int conAdjustHp(int unadjustedHp) {
        int adjustedHp = 0;
        int intLevel = level;
        int conBonus = stats.get(2).getBonus();

        adjustedHp = unadjustedHp + (intLevel * conBonus);

        return adjustedHp;
    }

    /**
     * The only stat generation method you'll ever need! Not really, it just calls applyRacialStatChanges() on generateStats()
     * and derives the bonuses from there. The only public method available for stat creation.
     * <p>
     * @return An arraylist of Stat objects with size 6 which includes [str,dex,con,int,wis,cha] with populated value and bonus properties
     */
    public ArrayList<Stat> createStatsAndBonuses() {
        return applyRacialStatChanges(generateStats());
    }

    /**
     * Initializes the six main stats with a value of 8 and bonus of 0
     * <p>
     * @return a list of the six stats in character sheet order
     */
    private ArrayList<Stat> initStats() {
        ArrayList<Stat> statArrayList = new ArrayList<>();

        Stat strength = new Stat("STR", 8, 0);
        statArrayList.add(strength);

        Stat dexterity = new Stat("DEX", 8, 0);
        statArrayList.add(dexterity);

        Stat constitution = new Stat("CON", 8, 0);
        statArrayList.add(constitution);

        Stat intelligence = new Stat("INT", 8, 0);
        statArrayList.add(intelligence);

        Stat wisdom = new Stat("WIS", 8, 0);
        statArrayList.add(wisdom);

        Stat charisma = new Stat("CHA", 8, 0);
        statArrayList.add(charisma);

        return statArrayList;
    }

    /**
     * A costly way to implement a legal point buy system based on an algorithm I'd already written for this purpose which you can
     * find at: https://github.com/noah-owens/5eRandomizer/blob/main/5e-Randomizer/scripts/main.js
     * <p>
     * @return an ArrayList of stats which is ready for racial bonus to be applied
     */
    private ArrayList<Stat> generateStats() {
        ArrayList<Stat> initializedStats = initStats();
        int pointBuyLimit = 27;
        int pointBuyTracker = 0;
        int statLimit = 15;
        int intIndex;
        Stat statAtIndex;
        int valueOfStatAtIndex;

        while (pointBuyTracker < pointBuyLimit) {
            intIndex = r.randNum(6);
            statAtIndex = initializedStats.get(intIndex);
            valueOfStatAtIndex = statAtIndex.getValue();

            if (valueOfStatAtIndex < statLimit) {
                valueOfStatAtIndex += 1;
                statAtIndex.setValue(valueOfStatAtIndex);

                pointBuyTracker++;
            }
        }

        return initializedStats;
    }

    /**
     * Takes a character's stats, adds in stat points from the racial bonus arraylist, then spits out the completed stat arraylist
     * <p>
     * @param originalStatsArrayList stats after point-buy step
     * @return completed stats (only values, bonuses not yet derived)
     */
    private ArrayList<Stat> applyRacialStatChanges(ArrayList<Stat> originalStatsArrayList) {
        ArrayList<Stat> racialBonusesArrayList = this.race.getStatChanges();
        Stat originalStat;
        Stat raceBonusStat;
        int sumOfValues;

        for (int i = 0; i < originalStatsArrayList.size(); i++) {
            originalStat = originalStatsArrayList.get(i);
            raceBonusStat = racialBonusesArrayList.get(i);

            sumOfValues = originalStat.getValue() + raceBonusStat.getValue();

            originalStat.setValue(sumOfValues);
        }

        return originalStatsArrayList;
    }

    /**
     * Assign equipment using PHB equipment quick select based on CharClass.name
     */
    public void quickSelectEquipment() {
        switch (charClass.getName()) {
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
                equipment.add("Rubber Duck");
                break;
        }
    }

    /**
     * Makes a choice between two equipment String objects
     * @param e1 choice 1
     * @param e2 choice 2
     * @return chosen equipment
     */
    private String pickOneEquip(String e1, String e2) {
        String pickedEquip;

        RandomHelper r = new RandomHelper();
        int randNum = r.rollDie(2);

        if (randNum == 1) {
            pickedEquip = e1;
        } else {
            pickedEquip = e2;
        }

        return pickedEquip;
    }

    public void addSpell(Spell s) {
        spells.add(s);
    }

    public void addEquipment(String e) {
        equipment.add(e);
    }

    public void addProficiency(String p) { proficiencies.add(p); }
}
