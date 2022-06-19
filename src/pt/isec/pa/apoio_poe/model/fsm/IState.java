package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;

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
    void editAluno(Aluno newVersionAluno) throws Exception; //meta2
    void removeAluno(Aluno toRemove) throws Exception; //meta2

    // GESTAO DOCENTES
    void addDocente(Docente newDocente) throws Exception;
    void editDocente(Docente newVersionDocente) throws Exception; //meta2
    void removeDocente(Docente toRemove) throws Exception; //meta2

    // GESTAO PROPOSTAS
    void addProposta(Proposta newProposta) throws Exception;
    Proposta getPropostas();
    void editProposta(Proposta newVersionProposta) throws Exception; //meta2
    void removeProposta(Proposta toRemove) throws Exception; //meta2

    // Fase 2

    // GESTAO CANDIDATURAS
    void addCandidatura(Candidaturas newCandidatura) throws Exception;
    void removeCandidatura(Candidaturas toRemove) throws Exception;
    ArrayList<Candidaturas> getCandidaturas();
    ArrayList<Aluno> getAlunosComAutoproposta();
    ArrayList<Aluno> getAlunosComCandidatura();
    ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura,ArrayList<Aluno> comAutoproposta);
    ArrayList<Proposta> getAutopropostasAlunos();
    ArrayList<Proposta> getPropostasDocentes();
    Set<Proposta> getPropostasComCandidaturas();
    ArrayList<Proposta> getPropostasSemCandidaturas();

    // Phase 3
    ArrayList<Aluno> getAlunosComPropostaAtribuida();
    ArrayList<Aluno> getAlunosSemPropostaAtribuida();
    ArrayList<Proposta> getPropostasDisponiveis();
    ArrayList<Proposta> getPropostasAtribuidas();
    void atribuicaoAutomaticaAutoPropostas();
    void atribuicaoAutomaticaPropostas();
    void AtribuicaoManual(Aluno aluno, Proposta proposta);
    void RemoverAtribuicao(Aluno aluno);
    void RemoverTodasAtribuicoes();

    //PHASE4
    void atribuicaoOrientadoresProponentes();
    void AtribuicaoOrientadorProposta(Docente d,Proposta p);
    ArrayList<Docente> getOrientadores();
    ArrayList<Aluno> getAlunosComPropostaComOrientador();
    ArrayList<Aluno> getAlunosComPropostaSemOrientador();
    String getEstatisticasOrientadores();

    //Gestao Orientadores
    ArrayList<Projeto> getProjetos();

    //PHASE5
    ArrayList<Aluno> getAlunosSemPropostasComCandidaturas();

    State getState();
    boolean isLocked();
    void lock() throws Exception;
    void checkIfLocked(boolean isLocked) throws Exception;
}
