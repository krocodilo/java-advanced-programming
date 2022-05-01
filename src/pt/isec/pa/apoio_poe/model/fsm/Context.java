package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Context {

    private IState state;
    private final DataCapsule data;

    public Context() {
        data = new DataCapsule();
        state = new PhaseOne(this, data);
    }

    public void goToState(State s){
        state = state.goToState( s );
    }


    //======GESTAO ALUNOS===========================
    public void addAluno(Aluno newAluno) {
        state.addAluno(newAluno);
    }

    public void addAlunos(List<Aluno> alunos) {
        for(Aluno a : alunos)
            addAluno( a );
    }

    public ArrayList<Aluno> getAlunos() {
        return data.getAlunos();
    }

    public void editAluno(Aluno newVersionAluno) {
        state.editAluno(newVersionAluno);
    }

    public void removeAluno(Aluno toRemove) {
        state.removeAluno(toRemove);
    }

    public Aluno findAluno(long idAluno) {
        return state.findAluno( idAluno );
    }


    //======GESTAO DOCENTES===========================
    public void addDocente(Docente newDocente) {
        state.addDocente(newDocente);
    }

    public void addDocentes(List<Docente> docentes) {
        for(Docente d : docentes)
            addDocente( d );
    }
    
    public ArrayList<Docente> getDocentes() {
        return data.getDocentes();
    }

    public void editDocente(Docente newVersionDocente) {
        state.editDocente(newVersionDocente);
    }
    
    public void removeDocente(Docente toRemove) {
        state.removeDocente(toRemove);
    }


    //======GESTAO PROPOSTAS===========================
    public void addProposta(Proposta p) throws Exception {
        state.addProposta( p );
    }

    public void addPropostas(ArrayList<Proposta> propostas) throws Exception {
        for(Proposta p : propostas)
            addProposta( p );
    }

    public ArrayList<Proposta> getPropostas() {
        return data.getPropostas();
    }

    
    public void editProposta(Proposta newVersionProposta) {

    }

    //=====GESTAO CANDIDATURAS==========================

    public void addCandidatura(Candidaturas newCandidatura){
        state.addCandidatura(newCandidatura);
    }

    public void addCandidaturas(ArrayList<Candidaturas> candidaturas){
        for(Candidaturas c : candidaturas)
            addCandidatura(c);
    }

    public ArrayList<Candidaturas> getCandidaturas() {
        return data.getCandidaturas();
    }


    //============================================================

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return state.getAlunosComAutoproposta();
    }


    public ArrayList<Aluno> getAlunosComCandidatura(){
        return state.getAlunosComCandidatura();
    }

    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura){
        // Recebe a lista de alunos com candidatura, para ser mais facil
        return state.getAlunosSemCandidatura( comCandidatura );
    }

    public ArrayList<Proposta> getAutopropostasAlunos(){
        ArrayList<Proposta> autopropostas = new ArrayList<>();
        for(Proposta p : data.getPropostas())
            if(p.getType() == TipoProposta.AUTOPROPOSTO)
                autopropostas.add(p);
        return autopropostas;
    }

    public ArrayList<Proposta> getPropostasDocentes(){
        ArrayList<Proposta> pdocentes = new ArrayList<>();
        for(Proposta p : data.getPropostas())
            if(p.getType() == TipoProposta.PROJETO)
                pdocentes.add(p);
        return pdocentes;
    }

    public Set<Proposta> getPropostasComCandidaturas(){
        Set<Proposta> pcandidatura = new HashSet<>();
        for (Candidaturas c : data.getCandidaturas())
            for(Proposta p : data.getPropostas())
                if( c.getHashCodePropostas().contains(p.hashCode()) )
                    pcandidatura.add(p);
        return pcandidatura;
    }

    public ArrayList<Proposta> getPropostasSemCandidaturas(){
        ArrayList<Proposta> props = new ArrayList<>();
        for (Proposta p : getPropostasComCandidaturas())
            if( ! data.getPropostas().contains(p) )
                props.add( p );
        return props;
    }


    
    public void removeProposta(Proposta toRemove) {

    }

    public void nextState() {
        state = state.getNextState();
    }

    public void previousState() {
        state = state.getPreviousState();
    }

    public State getState(){
        return state.getState();
    }

    public boolean isLocked() {
        return state.isLocked();
    }

    public void lockCurrentState() throws Exception {
        state.lock();
    }
}
