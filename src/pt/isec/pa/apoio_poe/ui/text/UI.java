package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.Context;

import static pt.isec.pa.apoio_poe.utils.IO.printMenu;
import static pt.isec.pa.apoio_poe.utils.IO.readOption;

public class UI {

    private final Context fsm;

    public UI(Context fsm) {
        this.fsm = fsm;
    }

    public void start() {

        while ( switch (fsm.getState() ) {
            case PHASE_ONE -> phaseOne();
            case PHASE_TWO -> phaseTwo();
            case PHASE_THREE -> phaseThree();
            case PHASE_FOUR -> false;
            case PHASE_FIVE -> false;
            case GESTAO_ALUNOS -> false;
            case GESTAO_DOCENTES -> false;
            case GESTAO_PROPOSTAS -> false;
            case GESTAO_CANDIDATURAS -> false;
            case ATRIBUICAO_PROPOSTAS -> false;
            case GESTAO_ORIENTADORES -> false;
        }) {
            System.out.printf("\nCurrent state: %s\n\n",fsm.getState()); // (only for debug)
        }
    }

    private boolean phaseOne() {

        PhaseOneUI ui = new PhaseOneUI(fsm);

        printMenu("Configuracao",
                "1 - Gestao de Alunos",
                "2 - Gestao de Docentes",
                "3 - Gestao de propostas",
                "\n0 - Voltar");
        switch (readOption(null, 0, 3)){
            case 1: System.out.println(1);
            case 2:
                System.out.println(2);
        }

        return false;
    }

    private boolean phaseTwo() {

        return false;
    }

    private boolean phaseThree() {

        return false;
    }
}
