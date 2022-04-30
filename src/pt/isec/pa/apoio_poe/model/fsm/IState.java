package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.ArrayList;

public interface IState {

    //Common
    IState getNextState();
    IState getPreviousState();

    // Fase 1
    IState goToState(State state);
    IState goGestaoAlunos();

    // GESTAO ALUNOS
    IState addAluno(Aluno newAluno);
    IState editAluno(Aluno newVersionAluno); //meta2
    IState removeAluno(Aluno toRemove); //meta2

    // Fase 2
    void addDocente(Docente newDocente);
    Docente getDocentes();
    void editDocente(Docente newVersionDocente); //meta2
    void removeDocente(Docente toRemove); //meta2

    // Fase 3
    void addProposta(Proposta newProposta);
    Proposta getPropostas();
    void editProposta(Proposta newVersionProposta); //meta2
    void removeProposta(Proposta toRemove); //meta2


    State getState();
    boolean isLocked();
    void lock();
}
