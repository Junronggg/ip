package junny.Ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

