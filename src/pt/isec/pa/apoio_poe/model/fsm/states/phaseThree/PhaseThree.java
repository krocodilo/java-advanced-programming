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

public class PhaseThree extends StateAdapter {

    public PhaseThree(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void AtribuicaoAutomaticaAutoPropostas(){
        for(AutoProposto p : data.getAutoPropostos()){
            data.getAtribuicoesAlunos().put(data.findAluno(p.getIdAluno()),p);
        }
    }

    @Override
    public void AtribuicaoAutomaticaPropostas(){

        // adiciona os alunos candidatos ao array nas propostas
        for(Candidaturas c : data.getCandidaturas()){
            for(String s : c.getIdsPropostas()){
                if( data.findAluno(c.getIdAluno())==null  || data.findProposta(s)==null )
                    return;
                data.findProposta(s).getAlunosCandidatos().add(data.findAluno(c.getIdAluno()));
            }
        }

        // adiciona ao Map das Atribuicoes - só está a considerar a melhor classificação , mas falta a parte das preferências
        for(Proposta p : data.getPropostas()){
            data.getAtribuicoesAlunos().put(p.getMelhorCandidato(),p);
        }

    }

    @Override
    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return data.getAlunosComAutoproposta();
    }

    @Override
    public ArrayList<Aluno> getAlunosComCandidatura() {
        return data.getAlunosComCandidatura();
    }

    @Override
    public ArrayList<Aluno> getAlunosComPropostaAtribuida() {
        return data.getAtribuicoesAlunosLista();
    }

    @Override
    public ArrayList<Aluno> getAlunosSemPropostaAtribuida() {
        return data.getSemAtribuicoesAlunosLista();
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
    public ArrayList<Proposta> getPropostasDisponiveis() {
        //TODO
        return data.getPropostasDocentes();
    }

    @Override
    public ArrayList<Proposta> getPropostasAtribuidas() {
        //TODO
        return data.getPropostasDocentes();
    }


    @Override
    public State getState() {
        return State.PHASE_THREE;
    }

    @Override
    public IState getNextState() {
        //TODO : return PHASE4
        return this;
    }

    @Override
    public IState getPreviousState() {
        return new PhaseTwo(context, data);
    }

    @Override
    public boolean isLocked() {
        return data.phaseThreeLocked;
    }

    @Override
    public void lock() throws Exception {

        //TODO: verify se todos os alunos têm projeto atribuido

        if( ! data.phaseTwoLocked )
            throw new Exception("A fase anterior tem de ser fechada primeiro!");

        data.phaseThreeLocked = true;
    }
}
