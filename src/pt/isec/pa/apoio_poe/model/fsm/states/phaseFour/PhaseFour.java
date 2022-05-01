package pt.isec.pa.apoio_poe.model.fsm.states.phaseFour;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

public class PhaseFour extends StateAdapter{

    public PhaseFour(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public State getState() {
        return State.PHASE_FOUR;
    }

    @Override
    public IState getNextState() {
        return this;    //TODO five
    }

    @Override
    public boolean isLocked() {
        return data.phaseFourLocked;
    }

    @Override
    public void lock() throws Exception {


        if( ! data.phaseThreeLocked )
            throw new Exception("A fase anterior tem de ser fechada primeiro!");


        data.phaseFourLocked = true;
    }
}
