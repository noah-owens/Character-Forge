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

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Test file for Race.java
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class RaceTest {
    Race orc = new Race("Orc", new ArrayList<>(), new ArrayList<>());
    Race human = new Race("Human", new ArrayList<>(), new ArrayList<>());
    PlayerCharacter ron = new PlayerCharacter("Ron Stampler", 1, null, human, new ArrayList<>(), null, "NG");
    PlayerCharacter grond = new PlayerCharacter("Nightmare Ram Grond", 1, null, orc, new ArrayList<>(), null, "CE");
    ArrayList<Stat> orcChanges;
    ArrayList<Stat> humanChanges;

    @BeforeEach
    public void setUp() {
        orcChanges = new ArrayList<>();
        humanChanges = new ArrayList<>();

        orcChanges.add(new Stat("STR", 2, 0));
        orcChanges.add(new Stat("CON", 1, 0));

        humanChanges.add(new Stat("STR", 1, 0));
        humanChanges.add(new Stat("DEX", 1, 0));
        humanChanges.add(new Stat("CON", 1, 0));
        humanChanges.add(new Stat("INT", 1, 0));
        humanChanges.add(new Stat("WIS", 1, 0));
        humanChanges.add(new Stat("CHA", 1, 0));

        orc.setStatChanges(orcChanges);
        ArrayList<Stat> grondStats = grond.generateStats();
        grond.setStats(grondStats);

        human.setStatChanges(humanChanges);
        ArrayList<Stat> ronStats = ron.generateStats();
        ron.setStats(ronStats);
    }

    @Test
    @DisplayName("Adjust stats for hypothetical orc and hypothetical human")
    public void testApplyStatChanges() {
        ArrayList<Stat> finalGrondStats = null;

        finalGrondStats = orc.applyStatChanges(grond.getStats());
        assert (grond.getStats() != finalGrondStats);

        ArrayList<Stat> finalRonStats = human.applyStatChanges(ron.getStats());
        assert(ron.getStats() != finalRonStats);
    }
}
