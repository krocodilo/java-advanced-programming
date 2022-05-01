package pt.isec.pa.apoio_poe.model.fsm.states.phaseThree;

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
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

import java.util.ArrayList;

public class AtribuicaoPropostas extends StateAdapter {

    public AtribuicaoPropostas(Context context, DataCapsule data) {
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
                if( data.findAluno(c.getIdAluno())==null  || data.findProposta(s)==null || data.findProposta(s).getType()== TipoProposta.AUTOPROPOSTO)
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
    public IState getPreviousState() {
        return new PhaseThree(context,data);
    }

    @Override
    public State getState() {
        return State.ATRIBUICAO_PROPOSTAS;
    }
}
