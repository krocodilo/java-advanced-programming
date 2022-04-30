package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class GestaoAlunos extends StateAdapter {

    public GestaoAlunos(DataCapsule data) {
        super(data);
    }

    @Override
    public IState addAluno(Aluno newAluno) {
        //TODO : restrições - no construtor ?
        data.getAlunos().add( newAluno );
        return new GestaoAlunos(data);
    }

    @Override
    public IState editAluno(Aluno newVersionAluno) {
        //TODO : meta2
        int index = data.getAlunos().indexOf( newVersionAluno );
        data.getAlunos().set( index, newVersionAluno );
        return new GestaoAlunos(data);
    }

    @Override
    public IState removeAluno(Aluno toRemove) {
        //TODO : meta2
        data.getAlunos().remove( toRemove );
        return new GestaoAlunos(data);
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
