package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private IState state;
    private DataCapsule data;

    public Context() {
        data = new DataCapsule();
        state = new PhaseOne(data);
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

    public void lockCurrentState() {
        state.lock();
    }
}
