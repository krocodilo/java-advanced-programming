package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.ArrayList;

public class Context {

    private IState state;


    public void addAluno(Aluno newAluno) {

    }

    public ArrayList<Aluno> getAlunos() {
        return state.getAlunos();
    }

    public void editAluno(Aluno newVersionAluno) {

    }

    public void removeAluno(Aluno toRemove) {
        state.removeAluno(toRemove);
    }
    
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

    public State getState(){
        return state.getState();
    }

    private void changeState(IState newState) {

    }
}
