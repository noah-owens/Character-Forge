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

import Character.Forge.Behavior.IOManager;
import Character.Forge.Data.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

/**
 * Test file for IOManager.java
 * <p>
 * @version v0.3.1
 * @author Noah Owens
 */
@Log4j2
public class IOManagerTest {
    private final String filePath = "src/main/resources/temp-files/io-manager-test.json";
    private IOManager<Race> raceIOManager;
    private Race halfling;
    private Gson gson;

    private BufferedWriter writer;
    private BufferedReader reader;

    @BeforeEach
    public void setUp() {
        File file = new File(filePath);
        raceIOManager = new IOManager<>(new TypeToken<ArrayList<Race>>() {});
        gson = new Gson();

        ArrayList<Stat> statChanges = new ArrayList<>();
            statChanges.add(new Stat("DEX", 2, 0));
            statChanges.add(new Stat("CHA", 1, 0));
        ArrayList<CharFeature> features = new ArrayList<>();
            features.add(new CharFeature(0, "Short", null));
            features.add(new CharFeature(0, "The one where you re-roll 1s", null));

        halfling = new Race("Lightfoot Halfling", statChanges, features);

        try {
            writer = new BufferedWriter(new FileWriter(filePath));

            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputReader);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @AfterEach
    public void reset() {
        try {
            writer.write("");
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("writeJson() overwrites data in a file with a serialized ArrayList<T>")
    public void testWriteJson() {
        try {
            writer.write("Junk Data 091980r08109-8098wqeroijafwen%%^#%! absolute garbage");
            writer.flush();

            ArrayList<Race> racesCollection = new ArrayList<>();
            racesCollection.add(halfling);

            raceIOManager.jsonWrite(racesCollection, filePath);

            String line = reader.readLine();
            assert ("[".equals(line));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("readJson() returns a list identical in type and content to a list written into the file")
    public void testReadJson() {
        try {
            ArrayList<Race> racesCollection = new ArrayList<>();
            racesCollection.add(halfling);
            String gsonString = gson.toJson(racesCollection);

            writer.write(gsonString);
            writer.flush();

            ArrayList<Race> deserializedRacesCollection = raceIOManager.jsonRead(filePath);

            // Please excuse the awful method chaining, I'm just so so happy this finally works
            assert (racesCollection.get(0).getName().equals(deserializedRacesCollection.get(0).getName()));
            assert (racesCollection.get(0).getFeatures().get(0).getLevel() == deserializedRacesCollection.get(0).getFeatures().get(0).getLevel());
            assert (racesCollection.get(0).getStatChanges().get(0).getValue() == deserializedRacesCollection.get(0).getStatChanges().get(0).getValue());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("appendFile() writes correctly to the end of the list")
    public void testAppendFile() {
        ArrayList<Race> racesCollection = new ArrayList<>();
        racesCollection.add(halfling);

        raceIOManager.jsonWrite(racesCollection, filePath);

        ArrayList<Stat> orcStatChanges = new ArrayList<>();
            orcStatChanges.add(new Stat("STR", 2, 0));
            orcStatChanges.add(new Stat("CON", 1, 0));
        ArrayList<CharFeature> orcFeatures = new ArrayList<>();
            orcFeatures.add(new CharFeature(0, "Tall", null));

        ArrayList<Race> newRaces = new ArrayList<>();
        newRaces.add(new Race("Orc", orcStatChanges, orcFeatures));

        raceIOManager.appendFile(newRaces, filePath);

        ArrayList<Race> appendedRacesCollection = raceIOManager.jsonRead(filePath);

        assert (appendedRacesCollection.size() == 2);
    }
}
