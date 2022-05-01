package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Estagio;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class GestaoPropostas extends StateAdapter {

    public GestaoPropostas(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void addProposta(Proposta newProposta) throws Exception {
        //TODO : restrições
        if( data.getPropostas().contains(newProposta) ) // compares with .equals()
            throw new Exception("Proposal already exists: " + newProposta.toString());
        if( newProposta.getType() == TipoProposta.AUTOPROPOSTO ) {
            boolean studentFound = false;
            AutoProposto tmp = (AutoProposto) newProposta;

            // Verify if student exists
            for (Aluno a : data.getAlunos())
                if (a.getId() == tmp.getIdAluno()){
                    studentFound = true;
                    break;
                }
            if( ! studentFound )
                throw new Exception("Student does not exist: " + tmp);

            // Verify if student has already been assigned a proposition
            for(Proposta p : data.getPropostas())
                if(p.getType() == TipoProposta.AUTOPROPOSTO)
                    if( ((AutoProposto) p).getIdAluno() == tmp.getIdAluno() )
                        throw new Exception("Student has already submitted a Proposal: " + tmp);

            data.getAutoPropostos().add( (AutoProposto) newProposta );
        }
        else if( newProposta.getType() == TipoProposta.ESTAGIO )
            data.getEstagios().add( (Estagio) newProposta );
        else
            data.getProjetos().add( (Projeto) newProposta );

        // TODO: remove this after checking its nt necessary:
        data.getPropostas().add( newProposta );
    }

    @Override
    public void editProposta(Proposta newVersionProposta) {
        //TODO : meta2
    }

    @Override
    public void removeProposta(Proposta toRemove) {
        //TODO : meta2
        data.getPropostas().remove( toRemove );
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(context, data);
    }

    @Override
    public State getState() {
        return State.GESTAO_PROPOSTAS;
    }
}
