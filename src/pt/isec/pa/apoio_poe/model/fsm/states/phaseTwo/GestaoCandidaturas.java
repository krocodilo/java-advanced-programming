package pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo;

import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;

public class GestaoCandidaturas extends StateAdapter {

    public GestaoCandidaturas(DataCapsule data) {
        super(data);
    }

    @Override
    public void addCandidatura(Candidaturas newCandidatura) {
        if( data.getCandidaturas().contains( newCandidatura ) )
            return;
        data.getCandidaturas().add( newCandidatura );
    }

    @Override
    public IState getPreviousState() {
        return new PhaseTwo(data);
    }

    @Override
    public State getState() {
        return State.GESTAO_CANDIDATURAS;
    }
}
