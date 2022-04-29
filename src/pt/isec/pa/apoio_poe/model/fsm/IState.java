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
    void addAluno(Aluno newAluno);
    ArrayList<Aluno> getAlunos();
    void editAluno(Aluno newVersionAluno); //meta2
    void removeAluno(Aluno toRemove); //meta2

    // Fase 2
    void addDocente(Docente newDocente);
    Docente getDocente();
    void editDocente(Docente newVersionDocente); //meta2
    void removeDocente(); //meta2

    // Fase 3
    void addProposta(Proposta newProposta);
    Proposta getProposta();
    void editProposta(Proposta newVersionProposta); //meta2
    void removeProposta(); //meta2


    State getState();
    boolean isLocked();
    void lock();
}
