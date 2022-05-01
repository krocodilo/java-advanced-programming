package pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseThree.PhaseThree;

import java.util.ArrayList;
import java.util.Set;

public class PhaseTwo extends StateAdapter {

    private boolean isLocked = false;

    public PhaseTwo(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return data.getAlunosComAutoproposta();
    }

    @Override
    public ArrayList<Aluno> getAlunosComCandidatura(){
        return data.getAlunosComCandidatura();
    }

    @Override
    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura, ArrayList<Aluno> comAutoproposta){
        // Recebe a lista de alunos com candidatura e com autoproposta, para ser mais facil

        return data.getAlunosSemCandidatura(comCandidatura,comAutoproposta);
    }

    @Override
    public ArrayList<Proposta> getAutopropostasAlunos() {
        return data.getAutopropostasAlunos();
    }

    @Override
    public ArrayList<Proposta> getPropostasDocentes() {
        return data.getPropostasDocentes();
    }

    @Override
    public Set<Proposta> getPropostasComCandidaturas() {
        return data.getPropostasComCandidaturas();
    }

    @Override
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        return data.getPropostasSemCandidaturas();
    }



    @Override
    public State getState() {
        return State.PHASE_TWO;
    }

    @Override
    public IState getNextState() {
        return new PhaseThree(context,data);
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(context, data);
    }

    @Override
    public boolean isLocked() {
        return data.phaseTwoLocked;
    }

    @Override
    public void lock() throws Exception {

        //TODO: verify

        if( ! data.phaseOneLocked )
            throw new Exception("A fase anterior tem de ser fechada primeiro!");

        data.phaseTwoLocked = true;
    }
}
