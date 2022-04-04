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
import java.util.ArrayList;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;

/**
 * This class will be capable of writing data-classes to json (using Gson library) and reading them
 * from json files stored at src/main/resources/serialized-objects/[file].json
 * <p>
 * Uses generic typing to serialize and deserialize different data-class types, meaning
 * a new IOManager<[object type]> will be required for each type of object being serialized. A small
 * price to pay for not needing to write 21 separate read, write, and append methods.
 * <p>
 * @version 0.2.1
 * @author Noah Owens
 */

@Log4j2
public class IOManager<T> {
    private final Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .serializeNulls()
                    .setPrettyPrinting()
                    //  Uncomment setLenient if reading handwritten json becomes an issue.
                    //  Writing better json would be preferable to accepting json w/
                    //  variable formatting however.
                    // .setLenient()
                    .create();
    private final TypeToken<ArrayList<T>> listType;
    private BufferedWriter writer;
    private BufferedReader reader;

    /**
     * Constructor binds T to a type at runtime to circumvent type erasure. Use like:
     * Executor<[myClass]> ioManager = new IOManager<[myClass]>(new TypeToken<ArrayList<[myClass]>>() {})
     * Read why this works at https://stackoverflow.com/a/14506181
     * <p>
     * @param listType new TypeToken
     */
    public IOManager(TypeToken<ArrayList<T>> listType) {
        this.listType = listType;
    }

    /**
     * Writes a collection of objects into json at specified location. Overwrites file at destination if
     * specified file already exists
     * <p>
     * When specifying path in this project, the filepath passed to jsonWrite() should be in
     * src/main/resources/serialized-objects/[fileName].json format.
     * <p>
     * @param objList a collection of objects to be serialized
     * @param filePath destination file, typically src/main/resources/serialized-objects/[fileName].json
     */
    public void jsonWrite(ArrayList<T> objList, String filePath) {
        String jsonObjectList = gson.toJson(objList);

        try {
            File file = new File(filePath);
            boolean fileDeleted = file.delete();
            File blankFile = new File(filePath);

            writer = new BufferedWriter(new FileWriter(blankFile, false));
            writer.write("");
            writer.write(jsonObjectList);
        } catch (IOException | SecurityException e) {
            logException(e);
        } finally {
            shutdownReaderWriter();
        }
    }

    /**
     * Deserialize ArrayList from file, use add method to append list, then reserialize.
     * <p>
     * @param filePath string location of file, typically src/main/resources/serialized-objects/[fileName].json
     */
    public void appendFile(ArrayList<T> objList, String filePath) {
        File file = new File(filePath);

        if (fileIsEmpty(file)){
            log.error("appendFile() called on unoccupied file");
        } else {
            ArrayList<T> appendableList = jsonRead(filePath);
            appendableList.addAll(objList);
            jsonWrite(appendableList, filePath);
        }
    }

    public ArrayList<T> jsonRead(String filePath) {
        File file = new File(filePath);
        ArrayList<T> listFromJson = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            StringBuilder accumulatorString = new StringBuilder();
            String line = "";
            reader = new BufferedReader(inputReader);

            while ((line = reader.readLine()) != null) {
                accumulatorString.append(line);
            }

            listFromJson = gson.fromJson(String.valueOf(accumulatorString), listType.getType());
        } catch (IOException e) {
            logException(e);
        } finally {
            shutdownReaderWriter();
        }
        return listFromJson;
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
