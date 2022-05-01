package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.ArrayList;
import java.util.Set;

public interface IState {

    //Common
    IState getNextState();
    IState getPreviousState();

    // Fase 1
    IState goToState(State state);

    // GESTAO ALUNOS
    void addAluno(Aluno newAluno) throws Exception;
    void editAluno(Aluno newVersionAluno); //meta2
    void removeAluno(Aluno toRemove); //meta2

    // GESTAO DOCENTES
    void addDocente(Docente newDocente) throws Exception;
    void editDocente(Docente newVersionDocente); //meta2
    void removeDocente(Docente toRemove); //meta2

    // GESTAO PROPOSTAS
    void addProposta(Proposta newProposta) throws Exception;
    Proposta getPropostas();
    void editProposta(Proposta newVersionProposta); //meta2
    void removeProposta(Proposta toRemove); //meta2

    // Fase 2

    // GESTAO CANDIDATURAS
    void addCandidatura(Candidaturas newCandidatura);
    ArrayList<Aluno> getAlunosComAutoproposta();
    ArrayList<Aluno> getAlunosComCandidatura();
    ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura,ArrayList<Aluno> comAutoproposta);
    ArrayList<Proposta> getAutopropostasAlunos();
    ArrayList<Proposta> getPropostasDocentes();
    Set<Proposta> getPropostasComCandidaturas();
    ArrayList<Proposta> getPropostasSemCandidaturas();

    //ATRIBUICAO PROPOSTAS
    ArrayList<Aluno> getAlunosComPropostaAtribuida();
    ArrayList<Aluno> getAlunosSemPropostaAtribuida();
    ArrayList<Proposta> getPropostasDisponiveis();
    ArrayList<Proposta> getPropostasAtribuidas();
    void AtribuicaoAutomaticaAutoPropostas();
    void AtribuicaoAutomaticaPropostas();


    State getState();
    boolean isLocked();
    void lock() throws Exception;
}
