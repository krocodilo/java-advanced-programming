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

        while (true) {
            printMenu("Configuracao",
                    "1 - Gestao de Alunos",
                    "2 - Gestao de Docentes",
                    "3 - Gestao de propostas\n",
                    "4 - Fechar Fase",
                    "5 - Fase Seguinte\n",
                    "0 - Sair");
            switch (readOption(null, 0, 4)) {
                case 1: ui.gestaoAlunos();
                case 2: ui.gestaoDocentes();
                case 4: fsm.lockCurrentState();
                case 5: {

                }
                case 0: return false;
            }
        }
    }

    private boolean phaseTwo() {

        return false;
    }

    private boolean phaseThree() {

        return false;
    }
}
