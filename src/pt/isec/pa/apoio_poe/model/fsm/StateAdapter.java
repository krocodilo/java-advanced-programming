package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.*;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.GestaoCandidaturas;

import java.util.ArrayList;
import java.util.Set;

public class StateAdapter implements IState {

    protected Context context;
    protected DataCapsule data;

    protected StateAdapter(Context context, DataCapsule data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public IState getNextState() {
        return this;
    }

    @Override
    public IState getPreviousState() {
        return this;
    }

    @Override
    public Aluno findAluno(long idAluno) {
        for( Aluno a : data.getAlunos() )
            if( a.getId() == idAluno )
                return a;
        return null;
    }

    @Override
    public IState goToState(State state) {
        return switch (state) {
            case GESTAO_ALUNOS -> new GestaoAlunos(context, data);
            case GESTAO_DOCENTES -> new GestaoDocentes(context, data);
            case GESTAO_PROPOSTAS -> new GestaoPropostas(context, data);
            case GESTAO_CANDIDATURAS -> new GestaoCandidaturas(context, data);
            default -> this;
        };
    }

    //======GESTAO ALUNOS===========================
    @Override
    public void addAluno(Aluno newAluno) {
    }

    @Override
    public void editAluno(Aluno newVersionAluno) {
    }

    @Override
    public void removeAluno(Aluno toRemove) {
    }


    //======GESTAO DOCENTES===========================
    @Override
    public void addDocente(Docente newDocente) {

    }


    @Override
    public void editDocente(Docente newVersionDocente) {

    }

    @Override
    public void removeDocente(Docente toRemove) {

    }


    //======GESTAO PROPOSTAS===========================
    @Override
    public void addProposta(Proposta newProposta) throws Exception {

    }

    @Override
    public Proposta getPropostas() {
        return null;
    }

    @Override
    public void editProposta(Proposta newVersionProposta) {

    }

    @Override
    public void removeProposta(Proposta toRemove) {

    }

    //======GESTAO CANDIDATURAS===========================
    @Override
    public void addCandidatura(Candidaturas newCandidatura){}

    @Override
    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosComCandidatura() {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura, ArrayList<Aluno> comAutoproposta) {
        return null;
    }

    @Override
    public ArrayList<Proposta> getAutopropostasAlunos() {
        return null;
    }

    @Override
    public ArrayList<Proposta> getPropostasDocentes() {
        return null;
    }

    @Override
    public Set<Proposta> getPropostasComCandidaturas() {
        return null;
    }

    @Override
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public void lock() throws Exception { }
}
