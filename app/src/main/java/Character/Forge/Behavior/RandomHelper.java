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
package Character.Forge.Behavior;

import java.util.Random;

/**
 * This class defines common uses for the Random library in this project
 * in order to minimize constructing similar code in multiple files.
 * <p>
 * @version 0.3.1
 * @author Noah Owens
 */
public class RandomHelper {
    Random r = new Random();

    /**
     * Non-parameterized constructor
     */
    public RandomHelper() {}

    /**
     * Simulate the roll of a die with n sides.
     * <p>
     * @param n the number of sides
     * @return an integer value between [1 & n] inclusive both sides
     * @throws IllegalArgumentException if passed a negative n
     */
    public int rollDie(int n) throws IllegalArgumentException {
        return r.nextInt(n) + 1;
    }

    /**
     * Create random number between [0,n) inclusive of zero only
     * @param n upper bound (non-inclusive)
     * @return a number between [0,n)
     */
    public int randNum(int n) {
        return r.nextInt(n);
    }
}
