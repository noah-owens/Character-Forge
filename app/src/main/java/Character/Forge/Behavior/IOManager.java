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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        boolean requiresCloseBracket = false;

        try {
            File file = new File(filePath);

            // create writer with append parameter set to the opposite of overwriteData,
            // so if overwriteData is true, append will be false.
            writer = new BufferedWriter(new FileWriter(file, !overwriteData));

            // Make first character in file an open bracket
            if (fileIsEmpty(file) || overwriteData) {
                writer.write("[");
                requiresCloseBracket = true;
            } else if (!fileIsEmpty(file)) {
                replaceLastCharacter(file);
                requiresCloseBracket = true;
            }

            // Write object to specified file
            writer.write(jsonObject);

            if (requiresCloseBracket) {
                writer.write("]");
            }
        } catch (IOException e) {
            logException(e);
        } finally {
            shutdownReaderWriter();
        }
    }

    /**
     * Removes a "]" if it is the last character and replaces with a "," by reading the content out to a
     * temporary file and copying it back over the original file.
     * @param file file which needs a trailing close bracket replaced by a comma.
     */
    private void replaceLastCharacter(File file) {
        try {
            File tempFile = new File("src/main/resources/temp/temp_" + file.getName());
            String filePath = file.getPath();
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputReader);
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            int closeBracketIndex = sb.lastIndexOf("]");
            if (closeBracketIndex > -1){
                sb.deleteCharAt(closeBracketIndex);
                sb.append(",");
            }

            writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write(String.valueOf(sb));
            boolean fileDeleted = file.delete();
            boolean fileRenamed = tempFile.renameTo(new File(filePath));

            log.info("Original File Deleted: " + fileDeleted + "\n Temp File Renamed: " + fileRenamed);
        } catch (IOException | SecurityException e) {
            logException(e);
        } finally {
            shutdownReaderWriter();
        }
    }

    /**
     * Check to see if the file has been used.
     * <p>
     * @param file object of type File
     * @return true if file is empty, false otherwise
     */
    public boolean fileIsEmpty(File file) {
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
