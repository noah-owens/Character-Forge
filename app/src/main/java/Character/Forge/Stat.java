/**
 * MIT License
 *
 * Copyright (c) 2022 Noah C Owens
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

/**
 * Stat class creates a specific [name, value] pair object to represent the 6 main stats
 * plus HP of a 5e D&D character
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class Stat {
    @Getter final private String id;
    @Getter @Setter private int value;
    @Getter @Setter private int bonus;

    /**
     * Stat constructor creates an object with an id that can be easily parsed for it's raw value and it's applied bonus for skill checks
     * <p>
     * @param id the 2-3 character representation of the stat (HP, STR, WIS, CHA, etc.)
     * @param value the raw number (between 3 & 20) value of the stat
     * @param bonus the number derived from the raw value which is applied to skill checks
     */
    public Stat(String id, int value, int bonus) {
        this.id = id;
        this.value = value;
        this.bonus = bonus;
    }

    /**
     * Returns a bonus using the Player's Handbook calculation for ability modifiers
     * <p>
     * @return stat bonus derived from stat object's value field
     */
    public int deriveBonus() {
        double b = Math.floor((value - 10.0) / 2.0);
        return (int) b;
    }
}