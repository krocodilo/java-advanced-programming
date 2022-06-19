package pt.isec.pa.apoio_poe.ui.text;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.ui.gui.MainJFX;

public class Main {

    public static final boolean gui = true;

    public static void main(String[] args) {

        if(gui)
            Application.launch(MainJFX.class, args);
        else {
            Context fsm = new Context();
            UI ui = new UI(fsm);

            ui.start();
        }
    }
}
