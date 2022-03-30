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

import java.io.IOException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class will be capable of writing data-classes to json (using Gson library) and reading them
 * from json files stored at /resources/serialized-objects/[file].json
 * @version 0.2.1
 * @author Noah Owens
 */
public class IOManager {
    Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .serializeNulls()
                    .setPrettyPrinting()
                    //  Uncomment setLenient if reading handwritten json becomes an issue.
                    //  Writing better json would be preferable to accepting worse json however.
                    // .setLenient()
                    .create();

    /**
     * Non-parameterized constructor allows for accessing methods outside IOManager class
     */
    public IOManager() {}


}