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

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Character.Forge.Data.Race;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;

/**
 * This class will be capable of writing data-classes to json (using Gson library) and reading them
 * from json files stored at src/main/resources/serialized-objects/[file].json
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
                    //  Writing better json would be preferable to accepting json w/
                    //  variable formatting however.
                    // .setLenient()
                    .create();
    BufferedWriter writer;
    BufferedReader reader;


    /**
     * Non-parameterized constructor allows for accessing methods outside IOManager class
     */
    public IOManager() {}

    /**
     * Writes a collection of objects into json at specified location. UNFINISHED
     * <p>
     * When specifying path in this project, the filepath passed to jsonWrite() should be in
     * src/main/resources/serialized-objects/[fileName].json format.
     * @param objList a collection of objects to be serialized
     * @param filePath destination file, typically src/main/resources/serialized-objects/[fileName].json
     * @param overwriteData true if file should be overwritten, false if data should be appended.
     */
    public void jsonWrite(ArrayList<Object> objList, String filePath, boolean overwriteData) {
        String jsonObjectList = gson.toJson(objList);

        try {
            File file = new File(filePath);
            writer = new BufferedWriter(new FileWriter(file));

            if (fileIsEmpty(file) || overwriteData) {
                // Write object to specified file
                writer.write(jsonObjectList);
            } else if (!fileIsEmpty(file)) { // && !overwriteData (is implied by earlier condition)
                writer.write(jsonObjectList);
            }

        } catch (IOException e) {
            logException(e);
        } finally {
            shutdownReaderWriter();
        }
    }

    /**
     * Deserialize ArrayList from file, use add method UNFINISHED
     * @param file file which needs a trailing close bracket replaced by a comma.
     */
    private void appendFile(ArrayList<Object> objList, File file) {
        if (fileIsEmpty(file)){
            log.error("appendFile() called on unoccupied file");
        } else {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputReader);


            } catch (IOException e) {
                logException(e);
            } finally {
                shutdownReaderWriter();
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

        if (file.length() == 0) {
            // check if a file is empty the easy way
            empty = true;
        } else {
            try {
                // check if a file only contains whitespace
                FileInputStream inputStream = new FileInputStream(file);
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputReader);

                // non IO variables
                String line;
                int letterCtr = 0;
                int spaceCtr = 0;

                // read first line and determine spaces vs words count
                if ((line = reader.readLine()) != null) {
                    for (char c : line.toCharArray()) {
                        if (c == ' ') {
                            spaceCtr++;
                        } else {
                            letterCtr++;
                        }
                    }
                }

                if (spaceCtr < 0 && letterCtr == 0) {
                    empty = true;
                }
            } catch (IOException e) {
                logException(e);
            } finally {
                shutdownReaderWriter();
            }
        }
        return empty;
    }

    /**
     * Utilize logj42 log.error() to log caught exceptions
     * <p>
     * @param exception caught exception
     */
    private void logException(Exception exception) {
        log.error(exception.getMessage());
    }

    /**
     * Close and return system resources if in use
     */
    private void shutdownReaderWriter() {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            logException(e);
        }
    }
}
