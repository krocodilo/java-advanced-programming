package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.Context;

public class Main {

    public static void main(String[] args) {

        Context fsm = new Context();
        UI ui = new UI( fsm );

        ui.start();
    }
}
