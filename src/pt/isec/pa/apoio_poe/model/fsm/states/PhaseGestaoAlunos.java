package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;

public class PhaseGestaoAlunos extends StateAdapter {

    public PhaseGestaoAlunos(DataCapsule data) {
        super(data);
    }

    @Override
    public IState addAluno(Aluno newAluno) {
        //TODO : restrições
        data.getAlunos().add( newAluno );
        return new PhaseGestaoAlunos(data);
    }

    @Override
    public IState editAluno(Aluno newVersionAluno) {
        //TODO : meta2
        return new PhaseGestaoAlunos(data);
    }

    @Override
    public IState removeAluno(Aluno toRemove) {
        //TODO : meta2
        return new PhaseGestaoAlunos(data);
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(data);
    }

    @Override
    public State getState() {
        return State.GESTAO_ALUNOS;
    }
}
