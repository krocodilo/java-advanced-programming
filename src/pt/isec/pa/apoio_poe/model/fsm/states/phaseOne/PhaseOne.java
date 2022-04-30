package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

public class PhaseOne extends StateAdapter {

    private boolean isLocked = false;

    public PhaseOne(DataCapsule data) {
        super(data);
    }

    @Override
    public State getState() {
        return State.PHASE_ONE;
    }

    @Override
    public IState getNextState() {
        //TODO : PHASE2 só se nº alunos > propostas
            //TODO -> isto acho que é so quando se fecha a fase
        if(data.getAlunos().size() > data.getPropostas().size())
            return new PhaseTwo(data);
        return this;
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public void lock() {
        //TODO verificacoes



        isLocked = true;
    }
}
