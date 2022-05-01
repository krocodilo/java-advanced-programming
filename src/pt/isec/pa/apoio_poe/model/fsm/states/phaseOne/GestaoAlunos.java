package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class GestaoAlunos extends StateAdapter {

    public GestaoAlunos(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void addAluno(Aluno newAluno) throws Exception {
        //TODO : restrições - no construtor ?
        if( data.getAlunos().contains( newAluno ) )
            throw new Exception("Aluno ja existe: " + newAluno.toString());

        if( data.emailExists( newAluno.getEmail() ) )
            throw new Exception("Ja existe um aluno/docente com o mesmo email: " +
                    newAluno.toString());

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
        return new PhaseOne(context, data);
    }

    @Override
    public State getState() {
        return State.GESTAO_ALUNOS;
    }
}
