package pt.isec.pa.apoio_poe.model.fsm.states.phaseFour;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;
import java.util.HashSet;


public class PhaseFour extends StateAdapter{

    public PhaseFour(Context context, DataCapsule data) {
        super(context, data);
    }

    @Override
    public void atribuicaoOrientadoresProponentes(){
        for(Projeto p : data.getProjetos()){
            for(Docente d : data.getDocentes()){
                if(d.getEmail().equals(p.getEmailDocente())){
                    p.setOrientador(d);
                    d.setOrientador(true);
                }
            }
        }
    }

    @Override
    public ArrayList<Aluno> getAlunosComPropostaComOrientador() {
        HashSet<Aluno> res = new HashSet<>();
        for(Aluno a : data.getAlunosComPropostaAtribuida())
            for(Projeto p : data.getProjetos())
                if(p.getOrientador() != null && p.getIdAluno() == a.getId())
                    res.add( a );
        return new ArrayList<>( res );
    }

    @Override
    public ArrayList<Aluno> getAlunosComPropostaSemOrientador() {
        ArrayList<Aluno> res = new ArrayList<>();
        ArrayList<Aluno> comOrientador = getAlunosComPropostaComOrientador();
        for(Aluno a : data.getAlunosComPropostaAtribuida())
            if( ! comOrientador.contains( a ) )
                res.add( a );
        return res;
    }

    @Override
    public String getEstatisticasOrientadores() {
        return data.getEstatisticasOrientadores();
    }

    @Override
    public State getState() {
        return State.PHASE_FOUR;
    }

    @Override
    public IState getNextState() {
        return this;    //TODO five
    }

    @Override
    public boolean isLocked() {
        return data.phaseFourLocked;
    }

    @Override
    public void lock() throws Exception {

//        if( ! data.phaseThreeLocked )
//            throw new Exception("A fase anterior tem de ser fechada primeiro!");

        data.phaseFourLocked = true;
    }
}
