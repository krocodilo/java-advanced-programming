package pt.isec.pa.apoio_poe.model.fsm.states.phaseFour;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;


public class GestaoOrientadores extends StateAdapter {

    public GestaoOrientadores(Context context, DataCapsule data) {
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
    public void removeOrientador(Docente toRemove){
        data.getOrientadores().remove( toRemove );
    }

    @Override
    public ArrayList<Projeto> getProjetos() {
        return data.getProjetos();
    }

    @Override
    public IState getPreviousState() {
        return new PhaseFour(context, data);
    }

    @Override
    public State getState() {
        return State.GESTAO_ORIENTADORES;
    }

}
