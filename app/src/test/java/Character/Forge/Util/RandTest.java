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
package Character.Forge.Util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandTest {
    Rand r;
    Rand r2;

    @Test
    void getInstance() {
        r = Rand.getInstance();
        r2 = Rand.getInstance();

        assert (r.equals(r2));
    }


    /*
    | Assert that across a large enough number of trials, every potential number
    | is accounted for without any being out of bounds
    */
    @Test
    void rollDie() {
        int[] numDist = {0, 0, 0, 0, 0, 0};
        int trials = 1000;
        int dieSize = 6;
        r = Rand.getInstance();

        for (int i = 0; i < trials; i++) {
            int result = r.rollDie(dieSize);

            assert (result > 0);
            assert (result < dieSize + 1);

            int newCount = numDist[result-1] + 1;
            numDist[result-1] = newCount;
        }

        for (int j : numDist) {
            assert (j > 0);
        }
    }

    /*
    | Assert that across a large enough number of trials, every potential number
    | is accounted for without any being out of bounds
    */
    @Test
    void randInt() {
        int[] numDist = {0, 0, 0, 0, 0, 0};
        int trials = 1000;
        int upperBound = 6;
        r = Rand.getInstance();

        for (int i = 0; i < trials; i++) {
            int result = r.randInt(upperBound);

            assert (result >= 0);
            assert (result < upperBound);

            int newCount = numDist[result] + 1;
            numDist[result] = newCount;
        }

        for (int j : numDist) {
            assert (j > 0);
        }
    }
}