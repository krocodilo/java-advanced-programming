package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class GestaoPropostas extends StateAdapter {

    public GestaoPropostas(DataCapsule data) {
        super(data);
    }

    @Override
    public void addProposta(Proposta newProposta) {
        //TODO : restrições - no construtor ?
        if( data.getPropostas().contains(newProposta) ) // compares with .equals()
            return;
        data.getPropostas().add( newProposta );
    }

    @Override
    public void editProposta(Proposta newVersionProposta) {
        //TODO : meta2
    }

    @Override
    public void removeProposta(Proposta toRemove) {
        //TODO : meta2
        data.getPropostas().remove( toRemove );
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(data);
    }

    @Override
    public State getState() {
        return State.GESTAO_ALUNOS;
    }
}
