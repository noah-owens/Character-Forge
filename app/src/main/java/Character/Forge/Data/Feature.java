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
package Character.Forge.Data;

/**
 * Feature objects represent a character's extraordinary powers and abilities.
 * <p>
 * A feature object will contain the name of the feature, the level it becomes available at, and whether it
 * affects stats/proficiencies.
 * <p>
 * @version 0.5.0
 * @author Noah Owens
 */
public class Feature {
    private final String name;
    private final int level;
    private final boolean tweakNeeded;

    /**
     * Initializes a feature object. All fields final and not settable.
     * @param name The display name of the feature, which will be featured in the character sheet UI
     * @param level The level at which the feature becomes available to a character
     * @param tweakNeeded Flags the FeatureHandler object to reference what needs to happen to the character sheet.
     */
    public Feature(String name, int level, boolean tweakNeeded) {
        this.name = name;
        this.level = level;
        this.tweakNeeded = tweakNeeded;
    }

    /*---------------------------------|
    |         Getter Methods           |
    |---------------------------------*/

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public boolean isTweakNeeded() {
        return tweakNeeded;
    }
}
