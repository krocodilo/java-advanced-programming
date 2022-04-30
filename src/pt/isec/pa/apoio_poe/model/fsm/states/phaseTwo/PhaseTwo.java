package pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;

public class PhaseTwo extends StateAdapter {

    private boolean isLocked = false;

    public PhaseTwo(DataCapsule data) {
        super(data);
    }

    @Override
    public State getState() {
        return State.PHASE_TWO;
    }

    @Override
    public IState getNextState() {
        //TODO : return PHASE3
        return this;
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(data);
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void lock() {
        isLocked = true;
    }
}
