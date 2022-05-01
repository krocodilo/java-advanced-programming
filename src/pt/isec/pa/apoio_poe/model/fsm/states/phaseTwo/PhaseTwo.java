package pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseThree.PhaseThree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PhaseTwo extends StateAdapter {

    private boolean isLocked = false;

    public PhaseTwo(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public ArrayList<Aluno> getAlunosComAutoproposta() {
        ArrayList<Aluno> autopropostos = new ArrayList<>();

        for(AutoProposto prop : data.getAutoPropostos()){
            Aluno a = findAluno( prop.getIdAluno() );
            if( a != null )
                autopropostos.add( a );
        }

        return autopropostos;
    }

    @Override
    public ArrayList<Aluno> getAlunosComCandidatura(){
        ArrayList<Aluno> comCandidatura = new ArrayList<>();
        for(Candidaturas c : data.getCandidaturas()){
            Aluno a = findAluno( c.getIdAluno() );
            if( a != null )
                comCandidatura.add( a );
        }
        return comCandidatura;
    }

    @Override
    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura, ArrayList<Aluno> comAutoproposta){
        // Recebe a lista de alunos com candidatura e com autoproposta, para ser mais facil

        ArrayList<Aluno> semCandidatura = new ArrayList<>();
        for(Aluno a : data.getAlunos())
            if( ! comCandidatura.contains( a ) && ! comAutoproposta.contains(a))
                semCandidatura.add( a );
        return semCandidatura;
    }

    @Override
    public ArrayList<Proposta> getAutopropostasAlunos() {
        ArrayList<Proposta> autopropostas = new ArrayList<>();
        for(Proposta p : data.getPropostas())
            if(p.getType() == TipoProposta.AUTOPROPOSTO)
                autopropostas.add(p);
        return autopropostas;
    }

    @Override
    public ArrayList<Proposta> getPropostasDocentes() {
        ArrayList<Proposta> pdocentes = new ArrayList<>();
        for(Proposta p : data.getPropostas())
            if(p.getType() == TipoProposta.PROJETO)
                pdocentes.add(p);
        return pdocentes;
    }

    @Override
    public Set<Proposta> getPropostasComCandidaturas() {
        Set<Proposta> pcandidatura = new HashSet<>();
        for (Candidaturas c : data.getCandidaturas())
            for(Proposta p : data.getPropostas())
                if( c.getHashCodePropostas().contains(p.hashCode()) )
                    pcandidatura.add(p);
        return pcandidatura;
    }

    @Override
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        ArrayList<Proposta> props = new ArrayList<>();
        for (Proposta p : getPropostasComCandidaturas())
            if( ! data.getPropostas().contains(p) )
                props.add( p );
        return props;
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
        return data.phaseTwoClosed;
    }

    @Override
    public void lock() throws Exception {

        //TODO: verify

        if( ! data.phaseOneClosed )
            throw new Exception("A fase anterior tem de ser fechada primeiro!");

        data.phaseTwoClosed = true;
    }
}
