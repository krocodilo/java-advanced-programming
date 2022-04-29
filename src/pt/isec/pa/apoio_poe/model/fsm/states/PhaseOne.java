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

    public PhaseOne(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void addAluno(Aluno newAluno) {
        data.getAlunos().add( newAluno );
    }

    @Override
    public ArrayList<Aluno> getAlunos() {
        return null;
    }

    @Override
    public void editAluno(Aluno newVersionAluno) {

    }

    @Override
    public void removeAluno(Aluno toRemove) {

    }

    @Override
    public State getState() {
        return State.PHASE_ONE;
    }

    @Override
    public IState getNextState() {
        return this;
    }

    @Override
    public IState getPreviousState() {
        return null;
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
