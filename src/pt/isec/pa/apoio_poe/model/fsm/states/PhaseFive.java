package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;

public class PhaseFive extends StateAdapter {

    public PhaseFive(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public ArrayList<Aluno> getAlunosSemPropostasComCandidaturas() {
        return data.getAlunosSemPropostasComCandidaturas();
    }

    @Override
    public String getEstatisticasOrientadores() {
        return data.getEstatisticasOrientadores();
    }

    @Override
    public IState getNextState() {
        return this;
    }

    @Override
    public IState getPreviousState() {
        return this;
    }

    @Override
    public State getState() {
        return State.PHASE_FIVE;
    }
}
