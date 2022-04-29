package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.states.PhaseOne;

import java.util.ArrayList;

public class Context {

    private IState state;
    private DataCapsule data;

    public Context() {
        data = new DataCapsule();
        state = new PhaseOne(data);
    }

    // PHASE1
    public void goGestaoAlunos(){
        state = state.goGestaoAlunos();
    }

    //======GESTAO ALUNOS===========================
    public void addAluno(Aluno newAluno) {
        state = state.addAluno(newAluno);
    }

    public ArrayList<Aluno> getAlunos() {
        return data.getAlunos();
    }

    public String mostraAlunos(){
        return data.mostraAlunos();
    }

    public void editAluno(Aluno newVersionAluno) {
        state = state.editAluno(newVersionAluno);
    }

    public void removeAluno(Aluno toRemove) {
        state = state.removeAluno(toRemove);
    }

    //======GESTAO DOCENTES===========================
    public void addDocente() {

    }
    
    public Docente getDocente() {
        return null;
    }

    public void editDocente(Docente newVersionDocente) {

    }
    
    public void removeDocente() {

    }
    
    public void addProposta() {

    }

    public Proposta getProposta() {
        return null;
    }

    
    public void editProposta(Proposta newVersionProposta) {

    }
    
    public void removeProposta() {

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
