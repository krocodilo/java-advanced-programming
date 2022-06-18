package pt.isec.pa.apoio_poe.ui.text;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.ui.gui.JavaFXMain;

public class Main {

    public static void main(String[] args) {

        Application.launch(JavaFXMain.class, args);

        Context fsm = new Context();
        UI ui = new UI( fsm );

        ui.start();
    }
}
