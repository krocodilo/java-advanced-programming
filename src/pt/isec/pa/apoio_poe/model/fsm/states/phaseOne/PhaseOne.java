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

    @Override
    public void lock() throws Exception {


        //TODO verificacoes
        if(data.getAlunos().size() > data.getPropostas().size())
            throw new Exception("Nao existem propostas suficientes!");


        data.phaseOneLocked = true;
    }
}
