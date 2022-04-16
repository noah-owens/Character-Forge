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

import Character.Forge.Behavior.PlayerCharacter;
import Character.Forge.Data.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Test file for CharClass.java
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class PlayerCharacterTest {
    PlayerCharacter lyle;
    PlayerCharacter bronan;
    PlayerCharacter mannequin;
    CharClass bard;
    CharClass barbarian;
    Race human;
    Background salesperson;
    ArrayList<Stat> humanRaceBonuses;
    String[] barbSaves = {"STR", "CON"};

    @BeforeEach
    public void setUp() {
        bard = new CharClass("Bard", 8, new ArrayList<>(), barbSaves, new ArrayList<>(), 0);
        barbarian = new CharClass("Barbarian", 12, new ArrayList<>(), barbSaves, new ArrayList<>(), 0);
        lyle = new PlayerCharacter("Lyle", 1, bard, null, 0, new ArrayList<>(), null, "CG");
        bronan = new PlayerCharacter("Bronan", 15, barbarian, null, 0, new ArrayList<>(), null, "CG");
        humanRaceBonuses = new ArrayList<>();
            humanRaceBonuses.add(new Stat("STR", 1, 0));
            humanRaceBonuses.add(new Stat("DEX", 1, 0));
            humanRaceBonuses.add(new Stat("CON", 1, 0));
            humanRaceBonuses.add(new Stat("INT", 1, 0));
            humanRaceBonuses.add(new Stat("WIS", 1, 0));
            humanRaceBonuses.add(new Stat("CHA", 1, 0));
        human = new Race("Human", humanRaceBonuses, new ArrayList<CharFeature>());
        salesperson = new Background("Salesperson", new ArrayList<CharFeature>(), new ArrayList<>());
    }

    @Test
    @DisplayName("Nonparameterized constructor contains spell and equipment lists")
    public void testNonParameterizedConstructor(){
        //No access/update methods for spell and equipment lists yet
        mannequin = new PlayerCharacter();
    }

    @Test
    @DisplayName("All getters/setters access what they're supposed to")
    public void testGettersAndSetters() {
        mannequin = new PlayerCharacter();

        mannequin.setName("J.C. Penney");
        assert ("J.C. Penney".equals(mannequin.getName()));

        mannequin.setLevel(20);
        assert (20 == mannequin.getLevel());

        mannequin.setCharClass(barbarian);
        assert (barbarian.equals(mannequin.getCharClass()));

        mannequin.setRace(human);
        assert (human.equals(mannequin.getRace()));

        mannequin.setHp(1);
        assert (1 == mannequin.getHp());

        ArrayList<Stat> generatedStats = mannequin.createStatsAndBonuses();
        mannequin.setStats(generatedStats);
        assert (generatedStats.equals(mannequin.getStats()));

        mannequin.setBackground(salesperson);
        assert (salesperson.equals(mannequin.getBackground()));

        mannequin.setAlignment("N");
        assert ("N".equals(mannequin.getAlignment()));
    }

    /**
     * Test generateHp() at several levels with different hit die to ensure it stays within legal bounds
     */
    @Test
    @DisplayName("generateHp() results in legal values")
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
    @Test
    @DisplayName("Verify conAdjustHp() correctly increases player.hp")
    public void testConAdjustHp() {
        // stat generation requires non-null race field
        lyle.setRace(human);

        // generate stats for lyle [str, dex, con, int, wis, cha]
        //                                     ^ index 2
        ArrayList<Stat> lyleStatList = lyle.createStatsAndBonuses();
        lyle.setStats(lyleStatList);

        // hp initialized at 0 in this case
        int lyleHp = lyle.getHp();

        // constitution set to a normal value/bonus pair manually for purposes of this test
        lyle.getStats().get(2).setValue(16);

        int adjustedHp = lyle.conAdjustHp(lyleHp);

        assert(3 == adjustedHp);
    }

    /**
     * Test that everything is following the rules set by
     * https://chicken-dinner.com/5e/5e-point-buy.html - Rules as Written tab
     */
    @Test
    @DisplayName("createStatsAndBonuses() generates valid stats")
    public void testCreateStatsAndBonuses() {
        bronan.setRace(human);
        bronan.setStats(bronan.createStatsAndBonuses());

        assert (6 == bronan.getStats().size());

        // 8 < Stat.value <= 16 [racial bonus for human adds 1 to every stat value]
        // -1 <= Stat.bonus <= 3 [min value (9) has a derived bonus of -1 and max value (16) has a derived bonus of 3]
        for (Stat i : bronan.getStats()) {
            assert (8 < i.getValue());
            assert (16 >= i.getValue());
            assert (3 >= i.getBonus());
            assert (-1 <= i.getBonus());
        }
    }
}
