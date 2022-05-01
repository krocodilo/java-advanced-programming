package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Estagio;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pt.isec.pa.apoio_poe.utils.IO.*;

public class PhaseTwoUI {

    final private Context fsm;

    public PhaseTwoUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoCandidaturas() throws Exception {

        printMenu("Gestao de Candidaturas",
                "1 - Adicionar",
                "2 - Consultar",
                "3 - Editar",
                "4 - Eliminar\n",
                "0 - Voltar");
        switch (readOption(null, 0, 6)) {
            case 1 -> {
                ArrayList<Candidaturas> candidaturas = FileUtils.readCandidaturasFromCSV(
                        prompt("Ficheiro de candidaturas")
                );
                fsm.addCandidaturas(candidaturas);
            }
            case 2 ->{
                showList( fsm.getCandidaturas() );
            }
            case 3 -> {
                System.out.println("Not implemented yet!");
            }
            case 4 -> {
                System.out.println("Not implemented yet!!");
            }
            case 0 -> fsm.previousState();
        }
    }

    public void showAlunosFiltered() {

        System.out.println("\n\n\t\t Alunos Com Autoproposta:");
        ArrayList<Aluno> alunosAutoproposta = fsm.getAlunosComAutoproposta();
        showList( alunosAutoproposta);

        System.out.println("\n\n\t Alunos Com Candidatura Registada:");
        ArrayList<Aluno> alunosComCandidatura = fsm.getAlunosComCandidatura();
        showList( alunosComCandidatura );

        System.out.println("\n\n\t\t Alunos Sem Candidatura Registada:");
        showList(
                fsm.getAlunosSemCandidatura( alunosComCandidatura , alunosAutoproposta)
        );
    }

    public void showPropostasFiltered() {
        ArrayList<Boolean> filters = new ArrayList<>( List.of(false,false,false,false) );

        boolean execute = false;
        while ( ! execute ){
            printMenu("Filtros Possiveis",
                    "1 - Autopropostas de Alunos" + (filters.get(0)? "\t\t[Selected]" : ""),
                    "2 - Propostas de Docentes" + (filters.get(1)? "\t\t[Selected]" : ""),
                    "3 - Propostas com Candidaturas" + (filters.get(2)? "\t[Selected]" : ""),
                    "4 - Propostas sem Candidatura" + (filters.get(3)? "\t[Selected]" : "") + "\n",
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
            propostas.addAll( fsm.getPropostasComCandidaturas() );
        }
        if( filters.get(3) ){
            propostas.addAll( fsm.getPropostasSemCandidaturas() );
        }
        if( !filters.get(0) && !filters.get(1) && !filters.get(2) && !filters.get(3) ){
            // If no filter selected
            propostas.addAll( fsm.getPropostas() );
        }

        showList( new ArrayList<>(propostas) );
        //TODO: improve this to use the tostring of each type
    }
}
