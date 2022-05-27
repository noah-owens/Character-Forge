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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * BackgroundTest will be pretty much only testing getters and setters
 */
class BackgroundTest {
    private Background bkg;

    @BeforeEach
    void setUp() {
        ArrayList<String> featList = new ArrayList<>();
            featList.add("Angry at kids");

        ArrayList<String> profList = new ArrayList<>();

        ArrayList<String> poolList = new ArrayList<>();
            poolList.add("Nature");
            poolList.add("Survival");

        bkg = new Background("Hermit", featList, profList, poolList, 2, false);
    }

    @Test
    void getName() {
        assert (bkg.getName().equals("Hermit"));
    }

    @Test
    void getFeatures() {
        String firstFeature = bkg.getFeatures().get(0);
        assert (firstFeature.equals("Angry at kids"));
    }

    @Test
    void getProficiencies() {
        String firstProf = bkg.getProficiencies().get(0);
        String secondProf = bkg.getProficiencies().get(1);

        assert (bkg.getProficiencies().size() == 2);
        assert (firstProf.equals("Nature") || firstProf.equals("Survival"));
        assert (secondProf.equals("Nature") || secondProf.equals("Survival"));
    }

    @Test
    void isErrorFlag() {
        assert (!bkg.isErrorFlag());
    }

    @Test
    void setFlag() {
        bkg.setFlag(true);
        assert (bkg.isErrorFlag());
    }
}