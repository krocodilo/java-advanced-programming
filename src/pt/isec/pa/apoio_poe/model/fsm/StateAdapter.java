package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.ArrayList;

public class StateAdapter implements IState {

    protected DataCapsule data;

    public StateAdapter(DataCapsule data) {
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

    //======PHASE 1===========================
    public IState goGestaoAlunos() {
        return this;
    }

    //======GESTAO ALUNOS===========================
    @Override
    public IState addAluno(Aluno newAluno) {
        return this;
    }

    @Override
    public IState editAluno(Aluno newVersionAluno) {
        return this;
    }

    @Override
    public IState removeAluno(Aluno toRemove) {
        return this;
    }


    //======GESTAO DOCENTES===========================
    @Override
    public void addDocente(Docente newDocente) {

    }

    @Override
    public Docente getDocente() {
        return null;
    }

    @Override
    public void editDocente(Docente newVersionDocente) {

    }

    @Override
    public void removeDocente() {

    }

    @Override
    public void addProposta(Proposta newProposta) {

    }

    @Override
    public Proposta getProposta() {
        return null;
    }

    @Override
    public void editProposta(Proposta newVersionProposta) {

    }

    @Override
    public void removeProposta() {

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
    public void lock() { }
}
