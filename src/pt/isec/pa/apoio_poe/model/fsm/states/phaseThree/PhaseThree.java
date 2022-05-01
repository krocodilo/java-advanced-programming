package pt.isec.pa.apoio_poe.model.fsm.states.phaseThree;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

public class PhaseThree extends StateAdapter {

    public PhaseThree(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public State getState() {
        return State.PHASE_THREE;
    }

    @Override
    public IState getNextState() {
        //TODO : return PHASE4
        return this;
    }

    @Override
    public IState getPreviousState() {
        return new PhaseTwo(context, data);
    }

    @Override
    public boolean isLocked() {
        return data.phaseThreeClosed;
    }

    @Override
    public void lock() throws Exception {

        //TODO: verify se todos os alunos têm projeto atribuido

        if( ! data.phaseTwoClosed )
            throw new Exception("A fase anterior tem de ser fechada primeiro!");

        data.phaseThreeClosed = true;
    }
}
