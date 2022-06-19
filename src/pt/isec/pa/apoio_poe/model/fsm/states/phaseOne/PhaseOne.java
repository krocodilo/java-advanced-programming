package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

public class PhaseOne extends StateAdapter {

    public PhaseOne(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public State getState() {
        return State.PHASE_ONE;
    }

    @Override
    public IState getNextState() {
        return new PhaseTwo(context, data);
    }

    @Override
    public boolean isLocked() {
        return data.phaseOneLocked;
    }

    /**
     * Tries to lock phase one.
     * @throws Exception If the number of Alunos is superior to the number of Propostas, or if the number of Alunos is zero
     */
    @Override
    public void lock() throws Exception {
        if(data.getAlunos().size() < 1)
            throw new Exception("Nao existem alunos!");
        if(data.getAlunos().size() > data.getPropostas().size())
            throw new Exception("Nao existem propostas suficientes!");


        data.phaseOneLocked = true;
    }
}
