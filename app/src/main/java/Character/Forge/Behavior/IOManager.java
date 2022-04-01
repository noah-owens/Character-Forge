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

import java.io.*;
import java.nio.Buffer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;

/**
 * This class will be capable of writing data-classes to json (using Gson library) and reading them
 * from json files stored at /resources/serialized-objects/[file].json
 * @version 0.2.1
 * @author Noah Owens
 */

@Log4j2
public class IOManager {
    Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .serializeNulls()
                    .setPrettyPrinting()
                    //  Uncomment setLenient if reading handwritten json becomes an issue.
                    //  Writing better json would be preferable to accepting worse json however.
                    // .setLenient()
                    .create();
    BufferedWriter writer;
    BufferedReader reader;


    /**
     * Non-parameterized constructor allows for accessing methods outside IOManager class
     */
    public IOManager() {}

    /**
     * Writes an object into json at specified location.
     * <p>
     * When specifying path in this project, the filepath passed to jsonWrite() should be in
     * src/main/resources/serialized-objects/[fileName].json format.
     * @param obj an object to be serialized
     * @param filePath destination file, typically src/main/resources/serialized-objects/[fileName].json
     * @param overwriteData true if file should be overwritten, false if data should be appended.
     */
    public void jsonWrite(Object obj, String filePath, boolean overwriteData) {
        String jsonObject = gson.toJson(obj);

        try {
            File file = new File(filePath);

            // create writer with append parameter set to the opposite of overwriteData,
            // so if overwriteData is true, append will be false.
            writer = new BufferedWriter(new FileWriter(file, !overwriteData));

            // Write object to specified file
            writer.write(jsonObject);
        } catch (IOException e) {
            logException(e);
        } finally {
            // shut it all down
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                logException(e);
            }
        }
    }

    /**
     * Check to see if the file has been used.
     * <p>
     * @param file object of type File
     * @return true if file is empty, false otherwise
     */
    private boolean fileIsEmpty(File file) {
        boolean empty = false;

        try {
            // create buffered file reader
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            logException(e);
        } finally {
            try {
            // close it up
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logException(e);
            }
        }

        return empty;
    }

    private void logException(Exception error) {
        log.error(error.getMessage());
    }
}
