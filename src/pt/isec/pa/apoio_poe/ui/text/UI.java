package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.State;

import java.util.List;

import static pt.isec.pa.apoio_poe.model.fsm.State.*;
import static pt.isec.pa.apoio_poe.utils.IO.*;

public class UI {

    private final Context fsm;
    private boolean exit = false;

    public UI(Context fsm) {
        this.fsm = fsm;
    }

    public void start() {

        PhaseOneUI one = new PhaseOneUI(fsm);
        PhaseTwoUI two = new PhaseTwoUI(fsm);

        while ( !exit ) {

            State state = fsm.getState();

            switch (state){
                case PHASE_ONE -> phaseOne();
                case PHASE_TWO -> phaseTwo( two );
                case PHASE_THREE -> phaseThree();
                //case PHASE_FOUR -> false;
                //case PHASE_FIVE -> false;
                case GESTAO_ALUNOS -> one.gestaoAlunos();
                case GESTAO_DOCENTES -> one.gestaoDocentes();
                case GESTAO_PROPOSTAS -> one.gestaoPropostas();
                case GESTAO_CANDIDATURAS -> two.gestaoCandidaturas();
                //case ATRIBUICAO_PROPOSTAS -> false;
                //case GESTAO_ORIENTADORES -> false;
            }
        }
    }

    private void phaseOne() {

        printMenu("Fase 1: Configuracao",
                "1 - Gestao de Alunos",
                "2 - Gestao de Docentes",
                "3 - Gestao de propostas\n",
                "4 - Fechar Fase",
                "5 - Fase Seguinte\n",
                "0 - Sair");
        switch (readOption(null, 0, 5)) {
            case 1 -> fsm.goToState( GESTAO_ALUNOS );
            case 2 -> fsm.goToState( GESTAO_DOCENTES );
            case 3 -> fsm.goToState( GESTAO_PROPOSTAS );
            case 4 -> {
                try {
                    fsm.lockCurrentState();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 5 -> fsm.nextState();
            case 0 -> exit = true;
        }
    }

    private void phaseTwo( PhaseTwoUI ui ) {
        printMenu("Fase 2: Opcoes de Candidatura",
                "1 - Gestao de Candidaturas",
                "2 - Consultar listas de alunos",
                "3 - Consultar listas de propostas\n",
                "4 - Fechar Fase",
                "5 - Fase Anterior",
                "6 - Fase Seguinte\n",
                "0 - Sair");
        switch (readOption(null, 0, 6)) {
            case 1 -> fsm.goToState( GESTAO_CANDIDATURAS );
            case 2 -> ui.showAlunosFiltered();
            case 3 -> ui.showPropostasFiltered();
            case 4 -> {
                try {
                    fsm.lockCurrentState();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            case 5 -> fsm.previousState();
            case 6 -> fsm.nextState();
            case 0 -> exit = true;
        }

    }

    private void phaseThree() {

    }
}
