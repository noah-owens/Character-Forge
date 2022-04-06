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

import java.util.ArrayList;

/**
 * Background class creates an object which represents the background field in a character sheet,
 * containing a name, features, and additional equipment
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class Background {
    @Getter String name;
    @Getter ArrayList<CharFeature> features;
    @Getter ArrayList<String> equipment;

    /**
     * Background constructor creates a background object with a name, and features
     * <p>
     * @param name the name of the background, provides a hint for possible roleplaying starts
     * @param features the mechanical benefits of a given background
     * @param equipment
     */
    public Background(String name, ArrayList<CharFeature> features, ArrayList<String> equipment) {
        this.name = name;
        this.features = features;
        this.equipment = equipment;
    }
}