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
    public void addAluno(Aluno newAluno) {
        //TODO : restrições - no construtor ?
        if( data.getAlunos().contains( newAluno ) )
            return;

        data.getAlunos().add( newAluno );

        if( newAluno.getRamo().equalsIgnoreCase("DA") )
            data.numAlunosDA++;
        else if( newAluno.getRamo().equalsIgnoreCase("RAS") )
            data.numAlunosRAS++;
        else if( newAluno.getRamo().equalsIgnoreCase("SI") )
            data.numAlunosSI++;
    }

    @Override
    public void editAluno(Aluno newVersionAluno) {
        //TODO : meta2
        int index = data.getAlunos().indexOf( newVersionAluno );
        data.getAlunos().set( index, newVersionAluno );
    }

    @Override
    public void removeAluno(Aluno toRemove) {
        //TODO : meta2
        data.getAlunos().remove( toRemove );
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
