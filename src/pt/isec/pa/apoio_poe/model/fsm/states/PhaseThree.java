package pt.isec.pa.apoio_poe.model.fsm.states;

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
import pt.isec.pa.apoio_poe.model.fsm.states.phaseFour.PhaseFour;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

import java.util.ArrayList;

public class PhaseThree extends StateAdapter {

    public PhaseThree(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void atribuicaoAutomaticaAutoPropostas(){
        for(AutoProposto p : data.getAutoPropostos()){
            data.getAtribuicoesAlunos().put(data.findAluno(p.getIdAluno()),p);
        }
    }

    @Override
    public void atribuicaoAutomaticaPropostas(){

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
    public void AtribuicaoManual(Aluno aluno, Proposta proposta){
        if(! data.getAtribuicoesAlunos().containsKey(aluno))
            data.getAtribuicoesAlunos().put(aluno,proposta);
    }

    @Override
    public void RemoverAtribuicao(Aluno aluno){
        if( data.getAtribuicoesAlunos().get(aluno).getType() == TipoProposta.AUTOPROPOSTO)
            return;
        data.getAtribuicoesAlunos().remove(aluno);
    }

    @Override
    public void RemoverTodasAtribuicoes(){
        for( Aluno a : data.getAtribuicoesAlunos().keySet() )
            RemoverAtribuicao(a);
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
        return data.getAlunosComPropostaAtribuida();
    }

    @Override
    public ArrayList<Aluno> getAlunosSemPropostaAtribuida() {
        return data.getAlunosSemPropostaAtribuidas();
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
        return data.getPropostasDisponiveis();
    }

    @Override
    public ArrayList<Proposta> getPropostasAtribuidas() {
        return data.getPropostasAtribuidas();
    }


    @Override
    public State getState() {
        return State.PHASE_THREE;
    }

    @Override
    public IState getNextState() {
        return new PhaseFour(context, data);
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
