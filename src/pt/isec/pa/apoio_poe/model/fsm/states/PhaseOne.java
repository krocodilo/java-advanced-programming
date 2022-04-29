package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;

public class PhaseOne extends StateAdapter {

    private boolean isLocked = false;

    public PhaseOne(DataCapsule data) {
        super(data);
    }

    @Override
    public IState goGestaoAlunos() {
        if(!isLocked)
            return new PhaseGestaoAlunos(data);
        return this;
    }

    @Override
    public State getState() {
        return State.PHASE_ONE;
    }

    @Override
    public IState getNextState() {
        //TODO : return PHASE2
        return this;
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
