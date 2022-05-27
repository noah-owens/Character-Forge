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

import java.util.concurrent.ThreadLocalRandom;


/**
 * Singleton Class Rand should hopefully help to keep pseudorandom numbers
 * generated across the program from being too close to each other.
 * <p>
 * @version 0.5.0
 * @author Noah Owens
 */
public class Rand {
    private static Rand randInstance = null;

    /**
     * Access singleton Rand object
     * <p>
     * @return Rand object
     */
    public static Rand getInstance() {
        if (randInstance == null) {
            randInstance = new Rand();
        }
        return randInstance;
    }

    /**
     * rollDie(int x) returns a number 'n' for which 1 <= n <= x
     * <p>
     * @param n the dice size being rolled
     * @return integer from [1,n]
     */
    public int rollDie(int n) {
        return ThreadLocalRandom.current().nextInt(1, n + 1);
    }

    /**
     * randInt(int x) returns a number 'n' for which 0 <= n < x
     * @param n upper bound
     * @return integer from [0,n)
     */
    public int randInt(int n) {
        return ThreadLocalRandom.current().nextInt(0, n);
    }
}
