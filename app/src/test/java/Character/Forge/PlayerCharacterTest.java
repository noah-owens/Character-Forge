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
import java.util.Objects;

/**
 * Test file for CharClass.java
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class PlayerCharacterTest {
    PlayerCharacter lyle;
    PlayerCharacter bronan;
    CharClass bard;
    CharClass barbarian;
    ArrayList<Stat> lyleStatList;

    @BeforeEach
    public void setUp() {
        bard = new CharClass("Bard", 8, new ArrayList<>());
        barbarian = new CharClass("Barbarian", 12, new ArrayList<>());
        lyle = new PlayerCharacter("Lyle", 1, bard, null, 0, new ArrayList<>(), null, "CG");
        bronan = new PlayerCharacter("Bronan", 15, barbarian, null, 0, new ArrayList<>(), null, "CG");
        lyleStatList = lyle.generateStats();
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
        int hpAtLevelOne;
        int hpAtLevelThree;

        hpAtLevelOne = lyle.generateHp();
        assert(hpAtLevelOne == 8);

        lyle.setLevel(3);
        hpAtLevelThree = lyle.generateHp();
        assert (10 <= hpAtLevelThree && hpAtLevelThree <= 24);

        int hpAtLevelFifteen = bronan.generateHp();
        assert (36 <= hpAtLevelFifteen && hpAtLevelFifteen <= 180);
    }

    /**
     * Test conAdjustHp()
     * <p>
     * Currently throwing an index out of bounds exception that isn't present in
     * testGenerateStats() despite basically doing the same thing?
     * Index 2 (Constitution) is out of bounds for length 0, which should be 5
     */
//    @Test
//    @DisplayName("Verify conAdjustHp() correctly increases player.hp")
//    public void testConAdjustHp() {
//
//        // generate stats for lyle [str, dex, con, int, wis, cha]
//        //                                     ^ index 2
//        ArrayList<Stat> lyleStatList = lyle.generateStats();
//
//        // hp initialized at 0 in this case
//        int lyleHp = lyle.getHp();
//
//        // constitution set to a normal value/bonus pair manually for purposes of this test
//        lyleStatList.get(2).setValue(16);
//        lyleStatList.get(2).setBonus(3);
//
//        int adjustedHp = lyle.conAdjustHp(lyleHp);
//
//        assert(3 == adjustedHp);
//    }

    /**
     * Tests that initStats() creates the six stats in character sheet order and sets their value to 8
     */
    @Test
    @DisplayName("initStats() is doing it's job")
    public void testInitStats() {
        String[] correctOrder = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
        ArrayList<Stat> statArrayList = lyle.initStats();

        for(int i = 0; i < correctOrder.length; i++) {
            String correctStat = correctOrder[i];
            Stat currentStat = statArrayList.get(i);

            assert(Objects.equals(correctStat, currentStat.getId()));
            assert(8 == currentStat.getValue());
        }
    }

    /**
     * Test that generateStats() is following the rules set by https://chicken-dinner.com/5e/5e-point-buy.html - Rules as Written tab
     * <p>
     * Currently generateStats() is returning all 8's. It's correctly initializing the stats, but not point buying at all.
     */
    @Test
    @DisplayName("Random point buy is following the rules")
    public void testGenerateStats() {
        ArrayList<Stat> testerStats = bronan.generateStats();

        for (Stat stat : testerStats) {
           int v = stat.getValue();
           assert(8 <= v && v <= 15);
        }
    }
}
