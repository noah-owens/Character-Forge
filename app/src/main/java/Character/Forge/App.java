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

import Character.Forge.Behavior.*;
import Character.Forge.Data.*;
import Character.Forge.UI.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * App class launches GUI
 * <p>
 * @version v0.1.0
 * @author Noah Owens
 */
public class App extends Application {
    public static Stage stage = new Stage();
    public static Stage stageOne = new Stage();
    public static Stage stageTwo = new Stage();

    public App() {}

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String windowTitle = "Character Forge";

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/ui-resources/home.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(windowTitle);
        stage.show();

        FXMLLoader loaderOne = new FXMLLoader((getClass().getResource("/ui-resources/main.fxml")));
        Parent rootOne = loaderOne.load();
        Scene sceneOne = new Scene(rootOne);
        stageOne.setScene(sceneOne);
        stageOne.setTitle(windowTitle);

        FXMLLoader loaderTwo = new FXMLLoader((getClass().getResource("/ui-resources/choices.fxml")));
        Parent rootTwo = loaderTwo.load();
        Scene sceneTwo = new Scene(rootTwo);
        stageTwo.setScene(sceneTwo);
        stageTwo.setTitle(windowTitle);
    }

    public static void main(String[] args) {
        App.launch();
    }
}
