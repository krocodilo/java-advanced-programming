package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IState {

    //Common
    IState getNextState();
    IState getPreviousState();

    // Fase 1
    IState goToState(State state);

    // GESTAO ALUNOS
    void addAluno(Aluno newAluno);
    void editAluno(Aluno newVersionAluno); //meta2
    void removeAluno(Aluno toRemove); //meta2

    // GESTAO DOCENTES
    void addDocente(Docente newDocente);
    void editDocente(Docente newVersionDocente); //meta2
    void removeDocente(Docente toRemove); //meta2

    // GESTAO PROPOSTAS
    void addProposta(Proposta newProposta);
    Proposta getPropostas();
    void editProposta(Proposta newVersionProposta); //meta2
    void removeProposta(Proposta toRemove); //meta2

    // Fase 2

    // GESTAO CANDIDATURAS



    State getState();
    boolean isLocked();
    void lock();
}
