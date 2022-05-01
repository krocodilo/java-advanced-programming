package pt.isec.pa.apoio_poe.model.fsm.states.phaseThree;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

import java.util.ArrayList;

public class AtribuicaoPropostas extends StateAdapter {

    public AtribuicaoPropostas(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public IState getPreviousState() {
        return new PhaseThree(context,data);
    }

    @Override
    public State getState() {
        return State.ATRIBUICAO_PROPOSTAS;
    }

    public void checkIfLocked() throws Exception {
        super.checkIfLocked( data.phaseThreeLocked );
    }
}
