package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.Context;

import static pt.isec.pa.apoio_poe.utils.IO.*;
import static pt.isec.pa.apoio_poe.utils.IO.showList;

public class PhaseFourUI {

    private final Context fsm;

    public PhaseFourUI(Context fsm) {
        this.fsm = fsm;
    }

    public void gestaoOrientadores() {

        printMenu("Gestao de Orientadores",
                "1 - Atribuir",
                "2 - Consultar",
                "3 - Alterar",
                "4 - Eliminar\n",
                "0 - Voltar");
        switch (readOption(null, 0, 6)) {
            case 1 -> {
                Docente ori = selectOneFrom( fsm.getDocentes() );
                Projeto p = selectOneFrom( fsm.getProjetos() );
                fsm.atribuicaoOrientadorProposta( ori, p );
            }
            case 2 -> showList( fsm.getOrientadores() );
            case 3 -> System.out.println("Not implemented yet!");
            case 4 -> System.out.println("Not implemented yet!!");
            case 0 -> fsm.previousState();
        }
    }
}
