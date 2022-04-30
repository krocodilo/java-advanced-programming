package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.*;

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

    public IState goToState(State state) {
        return switch (state) {
            case GESTAO_ALUNOS -> new GestaoAlunos(data);
            case GESTAO_DOCENTES -> new GestaoDocentes(data);
            default -> this;
        };
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
    public Docente getDocentes() {
        return null;
    }

    @Override
    public void editDocente(Docente newVersionDocente) {

    }

    @Override
    public void removeDocente(Docente toRemove) {

    }

    @Override
    public void addProposta(Proposta newProposta) {

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
