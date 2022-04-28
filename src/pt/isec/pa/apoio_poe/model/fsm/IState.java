package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IState {

    //Common
    IState avancar();
    IState faseAnterior();

    // Fase 1
    void addAluno();
    Aluno getAluno();
    void editAluno(Aluno newVersionAluno); //meta2
    void removeAluno(); //meta2

    // Fase 2
    void addDocente();
    Docente getDocente();
    void editDocente(Docente newVersionDocente); //meta2
    void removeDocente(); //meta2

    // Fase 3
    void addProposta();
    Proposta getProposta();
    void editProposta(Proposta newVersionProposta); //meta2
    void removeProposta(); //meta2


    State getState();
}
