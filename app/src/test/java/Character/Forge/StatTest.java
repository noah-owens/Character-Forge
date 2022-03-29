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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test file for Stat.java
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class StatTest {
    Stat strength;
    Stat wisdom;

    /**
     * Creates a fresh instance of each stat so each test is dealing with a clean slate
     */
    @BeforeEach
    public void setUp() {
        strength = new Stat("STR", 14, 0);
        wisdom = new Stat("WIS", 7, 0);
    }

    /**
     * Tests the deriveBonus() method in Stat.java
     */
    @Test
    @DisplayName("Test that derive bonus calculation is handled correctly.")
    public void testDeriveBonus(){
        strength.setBonus(strength.deriveBonus());
        assert(strength.getBonus() == 2);

        strength.setValue(18);
        strength.setBonus(strength.deriveBonus());
        assert(strength.getBonus() == 4);

        wisdom.setBonus(wisdom.deriveBonus());
        assert(wisdom.getBonus() == -2);
    }
}
