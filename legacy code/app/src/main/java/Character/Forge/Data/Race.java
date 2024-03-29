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
 * CharClass class creates an object which represents one of the main character species in the game.
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class Race {
    @Getter private final String name;
    @Getter @Setter private ArrayList<Stat> statChanges;
    @Getter @Setter private ArrayList<CharFeature> features;

    /**
     * Race constructor builds an object with a name, stat changes, and list of features.
     * <p>
     * @param name what the species is called
     * @param statChanges the stats which are added to as a feature of being this race, and the quantity by which they are increased
     *                    (an ArrayList length 6 with stats in Character Sheet order. For unadjusted stats value = 0)
     * @param features a list of the features which characters of this race gain
     */
    public Race(String name, ArrayList<Stat> statChanges, ArrayList<CharFeature> features) {
        this.name = name;
        this.statChanges = statChanges;
        this.features = features;
    }
}