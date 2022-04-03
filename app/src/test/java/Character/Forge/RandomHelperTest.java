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

import Character.Forge.Behavior.RandomHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test file for RandomHelper.java
 * <p>
 * @version v0.3.1
 * @author Noah Owens
 */
public class RandomHelperTest {
    RandomHelper r = new RandomHelper();

    /**
     * Test that rollDie() is rolling a number N for which 1<=N<=n
     */
    @Test
    @DisplayName("Vegas, baby! (rollDie(n) creates a random number N for which 1<=N<=n)")
    public void testRollDie() {
        int i = 0;

        while(i < 100) {
            int d2 = r.rollDie(2);
            assert (d2 > 0 && d2 <= 2);

            int d8 = r.rollDie(8);
            assert (d8 > 0 && d8 <= 8);

            int d20 = r.rollDie(20);
            assert (d20 > 0 && d20 <= 20);
            i++;
        }
    }

    /**
     * Test that randNum is creating an integer from 0 to n
     */
    @Test
    @DisplayName("randNum(n) creates a number N for which 0<=N<n")
    public void testRandNum() {
        int i = 0;
        int testNum;

        while (i < 100) {
            testNum = r.randNum(6);
            assert (0 <= testNum && testNum < 6);
            i++;
        }
    }

}
