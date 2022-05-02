package pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo;

import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;

public class GestaoCandidaturas extends StateAdapter {

    public GestaoCandidaturas(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void addCandidatura(Candidaturas newCandidatura) throws Exception {

        checkIfLocked();

        if( data.getCandidaturas().contains( newCandidatura ) )
            return;
        data.getCandidaturas().add( newCandidatura );
    }

    @Override
    public ArrayList<Candidaturas> getCandidaturas() {
        return data.getCandidaturas();
    }

    @Override
    public IState getPreviousState() {
        return new PhaseTwo(context, data);
    }

    @Override
    public State getState() {
        return State.GESTAO_CANDIDATURAS;
    }

    public void checkIfLocked() throws Exception {
        super.checkIfLocked( data.phaseTwoLocked );
    }
}
