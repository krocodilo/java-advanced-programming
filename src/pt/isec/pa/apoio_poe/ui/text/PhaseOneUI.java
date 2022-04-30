package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

import static pt.isec.pa.apoio_poe.utils.IO.*;

public class PhaseOneUI {

    final private Context fsm;

    public PhaseOneUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoAlunos() {

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
                    try{
                        ArrayList<Aluno> alunos = FileUtils.readAlunosFromCSV(
                                prompt("Ficheiro de alunos")
                        );
                        fsm.addAlunos( alunos );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                }
                case 2 ->
                    showList( fsm.getAlunos() );
                case 3 -> {
                    Aluno selected = selectOneFrom( fsm.getAlunos() );
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

    public void gestaoDocentes() {

        while (true) {
            printMenu("Gestao de Docentes",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "\n0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    /*Docente tmp = new Docente(
                            prompt("Nome"),
                            prompt("E-mail")
                    );
                    fsm.addDocente( tmp );*/
                    try{
                        ArrayList<Docente> docentes = FileUtils.readDocentesFromCSV(
                                prompt("Ficheiro de docentes")
                        );
                        fsm.addDocentes( docentes );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                }
                case 2 ->
                    showList(fsm.getDocentes());
                case 3 -> {
                    Docente selected = selectOneFrom( fsm.getDocentes() );
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


    public void gestaoPropostas() {

        while (true) {
            printMenu("Gestao de Propostas",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "0 - Voltar");
            switch (readOption(null, 0, 4)) {
                case 1 -> {
                    try{
                        ArrayList<Proposta> propostas = FileUtils.readPropostasFromCSV(
                                prompt("Ficheiro de propostas")
                        );
                        fsm.addPropostas( propostas );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                }
                case 2 ->
                        showList( fsm.getPropostas() );
                case 3 -> {
                    Proposta selected = selectOneFrom( fsm.getPropostas() );
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

    private <E> E selectOneFrom(List<E> list) {
        showList( list );
        return list.get(
                readOption("Select the index from the list: ", 0, list.size())
        );
    }
}
