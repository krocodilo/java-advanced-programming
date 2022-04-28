package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.Context;

import static pt.isec.pa.apoio_poe.utils.IO.printMenu;
import static pt.isec.pa.apoio_poe.utils.IO.readNumber;

public class PhaseOneUI {

    private final Context fsm;

    public PhaseOneUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoAlunos() {

        printMenu("Gestao de Alunos",
                "1 - Adicionar",
                "2 - Consultar",
                "3 - Editar",
                "4 - Eliminar",
                "\n0 - Voltar");
        switch (readNumber(null, 0, 2)){
            case 1: System.out.println(1);
            case 2:
                System.out.println(2);
        }
    }
}
