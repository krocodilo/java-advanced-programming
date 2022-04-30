package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.util.ArrayList;

import static pt.isec.pa.apoio_poe.utils.IO.*;

public class PhaseTwoUI {

    final private Context fsm;

    public PhaseTwoUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoCandidaturas() {

        while (true) {
            printMenu("Gestao de Candidaturas",
                    "1 - Adicionar",
                    "2 - Consultar",
                    "3 - Editar",
                    "4 - Eliminar\n",
                    "5 - Consultar listas de alunos",
                    "6 - Consultar listas de propostas\n",
                    "0 - Voltar");
            switch (readOption(null, 0, 6)) {
                case 1 -> {
                    try{
                        ArrayList<Candidaturas> candidaturas = FileUtils.readCandidaturasFromCSV(
                                prompt("Ficheiro de candidaturas")
                        );
                        fsm.addCandidaturas(candidaturas);
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                }
                case 2 ->{
                    showList(fsm.getCandidaturas());
                }
                case 3 -> {
                    System.out.println("Not implemented yet!");
                }
                case 4 -> {
                    System.out.println("Not implemented yet!");
                }
                case 5 -> {
                    //TODO
                }
                case 6 -> {
                    //TODO
                }
                case 0 -> {
                    fsm.previousState();
                    return;
                }
            }
        }
    }
}
