package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.util.ArrayList;

import static pt.isec.pa.apoio_poe.utils.IO.*;

public class PhaseOneUI {

    final private Context fsm;

    public PhaseOneUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoAlunos() throws Exception {

        while (true) {
            printMenu("Gestao de Alunos",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    /*Aluno tmp = new Aluno(
                            readLong("Numero de Aluno"),
                            prompt("Nome"),
                            prompt("E-mail"),
                            readCertainString("Curso", "LEI", "LEI-PL"),
                            readCertainString("Ramo", "DA", "RAS", "SI"),
                            readDouble("Classificacao"),
                            readBoolean("Pode estagiar")
                    );
                    fsm.addAluno(tmp);*/
                    ArrayList<Aluno> alunos = FileUtils.readAlunosFromCSV(
                            prompt("Ficheiro de alunos")
                    );
                    System.out.println( fsm.addAlunos( alunos )  );
                }
                case 2 ->
                    showList( fsm.getAlunos() );
                case 3 -> {
//                    Aluno selected = selectOneFrom( fsm.getAlunos() );
                }
                case 4 -> {
                    Aluno selected = selectOneFrom( fsm.getAlunos() );
                    if( readBoolean("Are you sure you want to delete it?") )
                        fsm.removeAluno( selected );
                }
                case 0 -> {
                    fsm.previousState();
                    return;
                }
            }
        }
    }

    public void gestaoDocentes() throws Exception {

        while (true) {
            printMenu("Gestao de Docentes",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    /*Docente tmp = new Docente(
                            prompt("Nome"),
                            prompt("E-mail")
                    );
                    fsm.addDocente( tmp );*/
                    ArrayList<Docente> docentes = FileUtils.readDocentesFromCSV(
                            prompt("Ficheiro de docentes")
                    );
                    System.out.println( fsm.addDocentes( docentes ) );
                }
                case 2 ->
                    showList(fsm.getDocentes());
                case 3 -> {
//                    Docente selected = selectOneFrom( fsm.getDocentes() );
                }
                case 4 -> {
                    Docente selected = selectOneFrom( fsm.getDocentes() );
                    if( readBoolean("Are you sure you want to delete it?") )
                        fsm.removeDocente( selected );
                }
                case 0 -> {
                    fsm.previousState();
                    return;
                }
            }
        }
    }


    public void gestaoPropostas() throws Exception {

        while (true) {
            printMenu("Gestao de Propostas",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    ArrayList<Proposta> propostas = FileUtils.readPropostasFromCSV(
                            prompt("Ficheiro de propostas")
                    );
                    fsm.addPropostas( propostas );
                }
                case 2 ->
                        showList( fsm.getPropostas() );
                case 3 -> {
//                    Proposta selected = selectOneFrom( fsm.getPropostas() );
                }
                case 4 -> {
                    Proposta selected = selectOneFrom( fsm.getPropostas() );
                    if( readBoolean("Are you sure you want to delete it?") )
                        fsm.removeProposta( selected );
                }
                case 0 -> {
                    fsm.previousState();
                    return;
                }
            }
        }
    }
}
