package pt.isec.pa.apoio_poe.model.fsm.states.phaseFour;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

import java.util.ArrayList;

public class AtribuicaoOrientadores extends StateAdapter {

    public AtribuicaoOrientadores(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void AtribuicaoOrientadorProposta(Docente d, Proposta p){
        if(p.getOrientador()==null && ! d.isOrientador()){
            p.setOrientador(d);
            d.setOrientador(true);
        }
    }

    @Override
    public ArrayList<Docente> ConsultaOrientadores(){
        ArrayList<Docente> orientadores = new ArrayList<>();
        for(Docente d : data.getDocentes()){
            if(d.isOrientador())
                orientadores.add(d);
        }
        return orientadores;
    }

    @Override
    public IState getPreviousState() {
        return new PhaseFour(context, data);
    }

    @Override
    public State getState() {
        return State.ATRIBUICAO_ORIENTADORES;
    }

}
