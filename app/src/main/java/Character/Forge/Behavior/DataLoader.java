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

import Character.Forge.Data.*;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import java.util.ArrayList;

/**
 * Singleton DataLoader class makes an object responsible for loading json into usable lists when program is run.
 * <p>
 * @version 0.3.1
 * @author Noah Owens
 */
public class DataLoader {
    private static DataLoader dataLoaderInstance = null;
    private final IOManager<Background> backgroundIOManager= new IOManager<>(new TypeToken<ArrayList<Background>>() {});
    private final IOManager<CharClass> charClassIOManager = new IOManager<>(new TypeToken<ArrayList<CharClass>>() {});
    private final IOManager<CharFeature> charFeatureIOManager = new IOManager<>(new TypeToken<ArrayList<CharFeature>>() {});
    private final IOManager<Race> raceIOManager = new IOManager<>(new TypeToken<ArrayList<Race>>() {});
    private final IOManager<Spell> spellIOManager = new IOManager<>(new TypeToken<ArrayList<Spell>>() {});
    @Getter private ArrayList<Background> backgrounds;
    @Getter private ArrayList<CharClass> charClasses;
    @Getter private ArrayList<CharFeature> charFeatures;
    @Getter private ArrayList<Race> races;
    @Getter private ArrayList<Spell> spells;

    private DataLoader() {}

    /**
     * Access singleton DataLoader object
     * <p>
     * @return DataLoader
     */
    public static DataLoader getDataLoaderInstance() {
        if (dataLoaderInstance == null) {
            dataLoaderInstance = new DataLoader();
        }
        return dataLoaderInstance;
    }

//    /**
//     * serialize() is for constructing the json files that make the basis of the project's data.
//     * <p>
//     * To make edits/additions to the serialized objects stored in the program, uncomment it, create your
//     * new objects, and append/overwrite existing files using the relevant IOManager.
//     */
//    public void serialize() {
//
//    }

    /**
     * loadFromJson() is to be called alongside App.launch() as one of the first behaviors in the project.
     * <p>
     * It will convert the json stored in resources/serialized-objects into objects that are usable and traversable
     * by the program.
     */
    public void loadFromJson() {
        backgrounds = backgroundIOManager.jsonRead("src/main/resources/serialized-objects/background.json");
        charClasses = charClassIOManager.jsonRead("src/main/resources/serialized-objects/char-classes.json");
        charFeatures = charFeatureIOManager.jsonRead("src/main/resources/serialized-objects/char-feature.json");
        races = raceIOManager.jsonRead("src/main/resources/serialized-objects/race.json");
        spells = spellIOManager.jsonRead("src/main/resources/serialized-objects/spell.json");
    }
}
