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

import lombok.Getter;
import lombok.Setter;

/**
 * CharFeature class creates an [integer, string] pair object which represents the level at which a feature is unlocked, and the title
 * of the feature in question.
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class CharFeature {
    @Getter private int level;
    @Getter private String title;
    @Getter private String subclass;

    /**
     * CharFeature constructor makes an object that associates the title of the feature with the level that it becomes available to a character.
     * <p>
     * @param level the level benchmark which must be met for this feature to be available
     * @param title the name of the feature
     * @param subclass the name of subclass it's available for (null for races, "all" for main class ability)
     */
    public CharFeature(int level, String title, String subclass) {
        this.level = level;
        this.title = title;
        this.subclass = subclass;
    }
}
