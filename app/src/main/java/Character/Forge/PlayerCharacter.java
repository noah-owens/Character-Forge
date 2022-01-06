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

package Character.Forge;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

/**
 * PlayerCharacter class creates an object representative of the completed character sheet.
 * <p>
 * @version v0.0.0
 * @author Noah Owens
 */
public class PlayerCharacter {
    @Getter @Setter private String name;
    @Getter @Setter private int level;
    @Getter @Setter private CharClass charClass;
    @Getter @Setter private Race race;
    @Getter @Setter private ArrayList<Stat> stats;
    @Getter @Setter private Background background;
    @Getter @Setter private String alignment;

    public ArrayList<String> equipment;
    public ArrayList<String> spells;
    public ArrayList<CharFeature> features;

    /**
     * PlayerCharacter constructor contains an instance of an ArrayList named "equipment" for storing adventuring paraphernalia and likewise one for "spells"
     * <p>
     * @param name the character's given name
     * @param level an integer between 1 & 20 representative of the character's power
     * @param charClass the character's profession, expertise, or proclivity (warlock, fighter, cleric, etc.)
     * @param race the character's species (human, goblin, dwarf, etc.)
     * @param stats a list of seven numeric attributes (HP, STR, DEX, CON, INT, WIS, CHA) which represent a character's skillfulness in a certain area
     * @param background the character's sob story, which may include helpful talents or connections
     * @param alignment a certain... moral guiding light represented in two character pairs (LG, NE, CG, NN)
     */
    public PlayerCharacter(String name, int level, CharClass charClass, Race race, ArrayList<Stat> stats, Background background, String alignment) {
        this.name = name;
        this.level = level;
        this.charClass = charClass;
        this.race = race;
        this.stats = stats;
        this.background = background;
        this.alignment = alignment;

        equipment = new ArrayList<>();
        spells = new ArrayList<>();
    }
}