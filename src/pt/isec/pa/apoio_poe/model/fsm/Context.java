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

    public void removeProposta(Proposta toRemove) {

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

    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura,ArrayList<Aluno> comAutoproposta){
        // Recebe a lista de alunos com candidatura, para ser mais facil
        return state.getAlunosSemCandidatura( comCandidatura, comAutoproposta );
    }

    public ArrayList<Proposta> getAutopropostasAlunos(){
        return state.getAutopropostasAlunos();
    }

    public ArrayList<Proposta> getPropostasDocentes(){
        return state.getPropostasDocentes();
    }

    public Set<Proposta> getPropostasComCandidaturas(){
        return state.getPropostasComCandidaturas();
    }

    public ArrayList<Proposta> getPropostasSemCandidaturas(){
        return state.getPropostasSemCandidaturas();
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
