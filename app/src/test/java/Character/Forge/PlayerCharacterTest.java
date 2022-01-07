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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test file for CharClass.java
 * <p>
 * @version v0.0.0
 * @author Noah Owens
 */
public class PlayerCharacterTest {
    PlayerCharacter lyle;
    PlayerCharacter bronan;
    CharClass bard;
    CharClass barbarian;
    Stat hitPoints;

    @BeforeEach
    public void setUp() {
        bard = new CharClass("Bard", 8, new ArrayList<>());
        barbarian = new CharClass("Barbarian", 12, new ArrayList<>());
        lyle = new PlayerCharacter("Lyle", 1, bard, null, new ArrayList<>(), null, "CG");
        bronan = new PlayerCharacter("Bronan", 15, barbarian, null, new ArrayList<>(), null, "CG");
        hitPoints = new Stat("HP", 0, 0);
    }

    /**
     * Test that rollDie() is rolling a number N for which 1<=N<=n
     */
    @Test
    @DisplayName("Vegas, baby!")
    public void testRollDie() {
        int i = 0;

        while(i < 100) {
            int d2 = lyle.rollDie(2);
            assert(d2 > 0 && d2 <= 2);

            int d8 = lyle.rollDie(8);
            assert(d8 > 0 && d8 <= 8);

            int d20 = lyle.rollDie(20);
            assert(d20 > 0 && d20 <= 20);
            i++;
        }
    }

    /**
     * Test generateHp() at several levels with different hit die to ensure it stays within legal bounds
     */
    @Test
    @DisplayName("Make sure that generateHp() results in legal values every time")
    public void testGenerateHp() {
        int hpAtLevelOne  = 0;
        int hpAtLevelThree = 0;

        hpAtLevelOne = lyle.generateHP();
        assert(hpAtLevelOne == 8);

        lyle.setLevel(3);
        hpAtLevelThree = lyle.generateHP();
        assert (10 <= hpAtLevelThree && hpAtLevelThree <= 24);

        int hpAtLevelFifteen = bronan.generateHP();
        assert (36 <= hpAtLevelFifteen && hpAtLevelFifteen <= 180);
    }
}
