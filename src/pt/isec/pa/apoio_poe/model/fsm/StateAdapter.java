package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.ArrayList;

public class StateAdapter implements IState {

    @Override
    public IState avancar() {
        return this;
    }

    @Override
    public IState faseAnterior() {
        return this;
    }

    @Override
    public void addAluno(Aluno newAluno) {

    }

    @Override
    public ArrayList<Aluno> getAlunos() {
        return null;
    }

    @Override
    public void editAluno(Aluno newVersionAluno) {

    }

    @Override
    public void removeAluno(Aluno toRemove) {

    }

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
}
