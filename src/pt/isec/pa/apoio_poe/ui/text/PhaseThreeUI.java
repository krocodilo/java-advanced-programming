package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static pt.isec.pa.apoio_poe.ui.utils.IO.*;

public class PhaseThreeUI {

    private final Context fsm;

    public PhaseThreeUI(Context fsm1) {
        this.fsm = fsm1;
    }

    public void removerAtribuicoes() {

        printMenu("Remover Atribuicoes",
                "1 - Remover Uma",
                "2 - Remover Todas\n",
                "0 - Voltar");
        switch (readOption(null, 0, 2)) {
            case 1 -> {
                Aluno a = selectOneFrom( fsm.getAlunosComPropostaAtribuida() );
                fsm.RemoverAtribuicao( a );
            }
            case 2 -> fsm.RemoverTodasAtribuicoes();
        }
    }

    public void showAlunosFiltered() {

        System.out.println("\n\n\t\t Alunos Com Autoproposta:");
        ArrayList<Aluno> alunosAutoproposta = fsm.getAlunosComAutoproposta();
        showList( alunosAutoproposta);

        System.out.println("\n\n\t Alunos Com Candidatura Registada:");
        showList( fsm.getAlunosComCandidatura() );

        System.out.println("\n\n\t\t Alunos Com Proposta Atribuida:");
        showList( fsm.getAlunosComPropostaAtribuida() );

        System.out.println("\n\n\t\t Alunos Sem Proposta Atribuida:");
        showList( fsm.getAlunosSemPropostaAtribuida() );
    }

    public void showPropostasFiltered() {
        ArrayList<Boolean> filters = new ArrayList<>( List.of(false,false,false,false) );

        boolean execute = false;
        while ( ! execute ){
            printMenu("Filtros Possiveis",
                    "1 - Autopropostas de Alunos" + (filters.get(0)? "\t\t[Selected]" : ""),
                    "2 - Propostas de Docentes" + (filters.get(1)? "\t\t[Selected]" : ""),
                    "3 - Propostas Disponiveis" + (filters.get(2)? "\t[Selected]" : ""),
                    "4 - Propostas Atribuidas" + (filters.get(3)? "\t[Selected]" : "") + "\n",
                    "0 - Executar Pesquisa");
            switch (readOption(null, 0, 4)){
                case 1 -> filters.set( 0, !filters.get(0) );
                case 2 -> filters.set( 1, !filters.get(1) );
                case 3 -> filters.set( 2, !filters.get(2) );
                case 4 -> filters.set( 3, !filters.get(3) );
                case 0 -> execute = true;
            }
        }
        System.out.println();

        HashSet<Proposta> propostas = new HashSet<>();  //Does not allow duplicates

        if( filters.get(0) ){
            propostas.addAll( fsm.getAutopropostasAlunos() );
        }
        if( filters.get(1) ){
            propostas.addAll( fsm.getPropostasDocentes() );
        }
        if( filters.get(2) ){
            propostas.addAll( fsm.getPropostasDisponiveis() );
        }
        if( filters.get(3) ){
            propostas.addAll( fsm.getPropostasAtribuidas() );
        }
        if( !filters.get(0) && !filters.get(1) && !filters.get(2) && !filters.get(3) ){
            // If no filter selected
            propostas.addAll( fsm.getPropostas() );
        }

        showList( new ArrayList<>(propostas) );
        //TODO: improve this to use the tostring of each type
    }
}
