package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.State;

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
        PhaseThreeUI three = new PhaseThreeUI(fsm);

        while ( !exit ) {

            State state = fsm.getState();

            try {
                switch (state) {
                    case PHASE_ONE -> phaseOne();
                    case PHASE_TWO -> phaseTwo(two);
                    case PHASE_THREE -> phaseThree(three);
                    //case PHASE_FOUR -> false;
                    //case PHASE_FIVE -> false;
                    case GESTAO_ALUNOS -> one.gestaoAlunos();
                    case GESTAO_DOCENTES -> one.gestaoDocentes();
                    case GESTAO_PROPOSTAS -> one.gestaoPropostas();
                    case GESTAO_CANDIDATURAS -> two.gestaoCandidaturas();
                    //case GESTAO_ORIENTADORES -> false;
                }
            } catch (Exception e){
                System.err.println("\n" + e.getMessage() + "\n");
            }
        }
    }

    private void phaseOne() throws Exception {

        printMenu("Fase 1: Configuracao",
                "1 - Gestao de Alunos...",
                "2 - Gestao de Docentes...",
                "3 - Gestao de propostas...\n",
                "4 - Fechar Fase",
                "5 - Fase Seguinte\n",
                "6 - Gravar Estado Atual",
                "7 - Carregar\n",
                "0 - Sair");
        switch (readOption(null, 0, 7)) {
            case 1 -> fsm.goToState( GESTAO_ALUNOS );
            case 2 -> fsm.goToState( GESTAO_DOCENTES );
            case 3 -> fsm.goToState( GESTAO_PROPOSTAS );
            case 4 -> fsm.lockCurrentState();
            case 5 -> fsm.nextState();
            case 6 -> saveStateToDisk();
            case 7 -> loadStateFromDisk();
            case 0 -> exit = true;
        }
    }

    private void phaseTwo( PhaseTwoUI ui ) throws Exception {
        printMenu("Fase 2: Opcoes de Candidatura",
                "1 - Gestao de Candidaturas...",
                "2 - Consultar listas de alunos",
                "3 - Consultar listas de propostas\n",
                "4 - Fechar Fase",
                "5 - Fase Anterior",
                "6 - Fase Seguinte\n",
                "7 - Gravar Estado Atual",
                "8 - Carregar\n",
                "0 - Sair");
        switch (readOption(null, 0, 8)) {
            case 1 -> fsm.goToState( GESTAO_CANDIDATURAS );
            case 2 -> ui.showAlunosFiltered();
            case 3 -> ui.showPropostasFiltered();
            case 4 -> fsm.lockCurrentState();
            case 5 -> fsm.previousState();
            case 6 -> fsm.nextState();
            case 7 -> saveStateToDisk();
            case 8 -> loadStateFromDisk();
            case 0 -> exit = true;
        }
    }

    private void phaseThree(PhaseThreeUI ui) throws Exception {

        System.out.println("\n-> Atribuicao automatica de autopropostas ou propostas de " +
                "docentes com aluno associado.");
        fsm.atribuicaoAutomaticaAutoPropostas();

        //TODO nao deixa fazer nenhuma atribuicao se fase anterior nao bloqueada

        if( fsm.phaseTwoLocked() ) {
            System.out.println("\n-> Atribuicao automatica de propostas.");
            fsm.atribuicaoAutomaticaPropostas();
        }

        printMenu("Fase 3: Atribuicao de Propostas",
                "1 - Atribuicao de propostas",
                "2 - Remocao de atribuicoes...",
                "3 - Consultar listas de alunos",
                "4 - Consultar listas de propostas\n",
                "5 - Fechar Fase",
                "6 - Fase Anterior",
                "7 - Fase Seguinte\n",
                "8 - Gravar Estado Atual",
                "9 - Carregar\n",
                "0 - Sair");
        switch (readOption(null, 0, 9)) {
            case 1 -> {
                if( ! fsm.phaseTwoLocked() )
                    throw new Exception("A fase anterior tem de estar bloqueada para fazer atribuicoes.");
                Aluno a = selectOneFrom( fsm.getAlunosSemPropostaAtribuida() );
                Proposta p = selectOneFrom( fsm.getPropostasDisponiveis() );
                fsm.AtribuicaoManual( a, p );
            }
            case 2 -> ui.removerAtribuicoes();
            case 3 -> ui.showAlunosFiltered();
            case 4 -> ui.showPropostasFiltered();
            case 5 -> {
                fsm.lockCurrentState();
                fsm.nextState();
            }
            case 6 -> fsm.previousState();
            case 7 -> fsm.nextState();
            case 8 -> saveStateToDisk();
            case 9 -> loadStateFromDisk();
            case 0 -> exit = true;
        }
    }

    private void phaseFour() throws Exception {
        printMenu("Fase 4: Atribuicao de Propostas",
                "1 - ",
                "4 - Fechar Fase",
                "5 - Fase Anterior",
                "6 - Fase Seguinte\n",
                "7 - Gravar Estado Atual",
                "8 - Carregar\n",
                "0 - Sair");
        switch (readOption(null, 0, -1)) {
            case 1 -> fsm.goToState( GESTAO_CANDIDATURAS );
            case 4 -> fsm.lockCurrentState();
            case 5 -> fsm.previousState();
            case 6 -> fsm.nextState();
            case 7 -> saveStateToDisk();
            case 8 -> loadStateFromDisk();
            case 0 -> exit = true;
        }
    }

    private void saveStateToDisk() throws Exception {
        fsm.saveStateToDisk( prompt("Nome do ficheiro onde gravar o estado atual da aplicacao") );
        System.out.println("\nDados gravados com sucesso.\n");
    }

    private void loadStateFromDisk() throws Exception {
        fsm.loadStateFromDisk( prompt("Nome do ficheiro onde os dados foram guardados") );
        System.out.println("\nDados carregados com sucesso.\n");
    }
}
