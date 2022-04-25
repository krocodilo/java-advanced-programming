package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

public interface IState {

    // Fase 1
    void addAluno();
    Aluno getAluno();
    void editAluno(Aluno newVersionAluno);
    void removeAluno();

    // Fase 2
    void addDocente();
    Docente getDocente();
    void editDocente(Docente newVersionDocente);
    void removeDocente();

    // Fase 3
    void addProposta();
    Proposta getProposta();
    void editProposta(Proposta newVersionProposta);
    void removeProposta();


    State getState();
}
