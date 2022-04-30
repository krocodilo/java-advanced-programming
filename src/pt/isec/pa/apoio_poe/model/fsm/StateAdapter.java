package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.*;

public class StateAdapter implements IState {

    protected DataCapsule data;

    protected StateAdapter(DataCapsule data) {
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
            case GESTAO_PROPOSTAS -> new GestaoPropostas(data);
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
