package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Context {

    private IState state;
    private DataCapsule data;

    public Context() {
        data = new DataCapsule();
        state = new PhaseOne(this, data);
    }

    public void goToState(State s){
        state = state.goToState( s );
    }


    //======GESTAO ALUNOS===========================
    public void addAluno(Aluno newAluno) throws Exception {
        state.addAluno(newAluno);
    }

    public String addAlunos(List<Aluno> alunos) {
        StringBuilder sb = new StringBuilder();
        for(Aluno a : alunos) {
            try {
                addAluno( a );
            } catch (Exception e) {
                sb.append(e.getMessage()).append("\n");
            }
        }
        return sb.toString();
    }

    public ArrayList<Aluno> getAlunos() {
        return data.getAlunos();
    }

//    public void editAluno(Aluno newVersionAluno) throws Exception {
//        state.editAluno(newVersionAluno);
//    }

    public void removeAluno(Aluno toRemove) throws Exception {
        state.removeAluno(toRemove);
    }


    //======GESTAO DOCENTES===========================
    public void addDocente(Docente newDocente) throws Exception {
        state.addDocente(newDocente);
    }

    public String addDocentes(List<Docente> docentes) {
        StringBuilder sb = new StringBuilder();
        for(Docente d : docentes) {
            try {
                addDocente( d );
            } catch (Exception e) {
                sb.append(e.getMessage()).append("\n");
            }
        }
        return sb.toString();
    }
    
    public ArrayList<Docente> getDocentes() {
        return data.getDocentes();
    }

//    public void editDocente(Docente newVersionDocente) throws Exception {
//        state.editDocente(newVersionDocente);
//    }
    
    public void removeDocente(Docente toRemove) throws Exception {
        state.removeDocente(toRemove);
    }


    //======GESTAO PROPOSTAS===========================
    public void addProposta(Proposta p) throws Exception {
        state.addProposta( p );
    }

    public void addPropostas(ArrayList<Proposta> propostas) throws Exception {
        for(Proposta p : propostas)
            addProposta( p );
    }

    public ArrayList<Proposta> getPropostas() {
        return data.getPropostas();
    }

    
//    public void editProposta(Proposta newVersionProposta) {
//
//    }

    public void removeProposta(Proposta toRemove) throws Exception {
        state.removeProposta( toRemove );
    }


    //=====GESTAO CANDIDATURAS==========================

    public void addCandidatura(Candidaturas newCandidatura) throws Exception {
        state.addCandidatura(newCandidatura);
    }

    public void addCandidaturas(ArrayList<Candidaturas> candidaturas) throws Exception {
        for(Candidaturas c : candidaturas)
            addCandidatura(c);
    }

    public ArrayList<Candidaturas> getCandidaturas() {
        return data.getCandidaturas();
    }


    //============================================================

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return state.getAlunosComAutoproposta();
    }


    public ArrayList<Aluno> getAlunosComCandidatura(){
        return state.getAlunosComCandidatura();
    }

    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura,ArrayList<Aluno> comAutoproposta){
        // Recebe a lista de alunos com candidatura, para ser mais facil
        return state.getAlunosSemCandidatura( comCandidatura, comAutoproposta );
    }

    public ArrayList<Proposta> getAutopropostasAlunos(){
        return state.getAutopropostasAlunos();
    }

    public ArrayList<Proposta> getPropostasDocentes(){
        return state.getPropostasDocentes();
    }

    public Set<Proposta> getPropostasComCandidaturas(){
        return state.getPropostasComCandidaturas();
    }

    public ArrayList<Proposta> getPropostasSemCandidaturas(){
        return state.getPropostasSemCandidaturas();
    }


    // ATRIBUICAO PROPOSTAS

    public ArrayList<Aluno> getAlunosComPropostaAtribuida() {
        return state.getAlunosComPropostaAtribuida();
    }

    public ArrayList<Aluno> getAlunosSemPropostaAtribuida() {
        return state.getAlunosSemPropostaAtribuida();
    }

    public ArrayList<Proposta> getPropostasDisponiveis() {
        return state.getPropostasDisponiveis();
    }

    public ArrayList<Proposta> getPropostasAtribuidas() {
        return state.getPropostasAtribuidas();
    }

    public void atribuicaoAutomaticaAutoPropostas(){
        state.atribuicaoAutomaticaAutoPropostas();
    }

    public void atribuicaoAutomaticaPropostas(){
        state.atribuicaoAutomaticaPropostas();
    }

    public void AtribuicaoManual(Aluno aluno, Proposta proposta){
        state.AtribuicaoManual(aluno,proposta);
    }

    public void RemoverAtribuicao(Aluno aluno){
        state.RemoverAtribuicao(aluno);
    }

    public void RemoverTodasAtribuicoes(){
        state.RemoverTodasAtribuicoes();
    }


    //PHASEFOUR
    public void atribuicaoOrientadoresProponentes(){ state.atribuicaoOrientadoresProponentes(); }

    public void atribuicaoOrientadorProposta(Docente d,Proposta p){ state.AtribuicaoOrientadorProposta(d,p);}

    public ArrayList<Docente> getOrientadores(){ return state.getOrientadores(); }

    public ArrayList<Aluno> getAlunosComPropostaComOrientador() {
        return state.getAlunosComPropostaComOrientador();
    }

    public ArrayList<Aluno> getAlunosComPropostaSemOrientador() {
        return state.getAlunosComPropostaSemOrientador();
    }

    public String getEstatisticasOrientadores() {
        return state.getEstatisticasOrientadores();
    }

    public ArrayList<Projeto> getProjetos() {
        return state.getProjetos();
    }

    //Phase 5
    public ArrayList<Aluno> getAlunosSemPropostasComCandidaturas() {
        return state.getAlunosSemPropostasComCandidaturas();
    }

    public void nextState() {
        state = state.getNextState();
    }

    public void previousState() {
        state = state.getPreviousState();
    }

    public State getState(){
        return state.getState();
    }

    public boolean phaseTwoLocked(){
        return data.phaseTwoLocked;
    }

    public void lockCurrentState() throws Exception {
        state.lock();
    }

    public void saveStateToDisk(String filename) throws Exception {
        data.setLastState( getState() );
        FileUtils.saveDataToDisk( filename, data );
    }

    public void loadStateFromDisk(String filename) throws Exception {
        data = FileUtils.loadDataFromDisk( filename );
        goToState( data.getLastState() );
    }
}
