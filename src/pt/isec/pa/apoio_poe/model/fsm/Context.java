package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;
import pt.isec.pa.apoio_poe.model.memento.PoECareTaker;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Context {

    private IState state;
    private DataCapsule data;
    private final PoECareTaker careTaker;

    public Context() {
        data = new DataCapsule();
        state = new PhaseOne(this, data);
        careTaker = new PoECareTaker(data);
    }

    /**
     * @param s The state to go to
     */
    public void goToState(State s){
        state = state.goToState( s );
    }


    //======GESTAO ALUNOS===========================
    /**
     * @param newAluno The new Aluno object
     */
    public void addAluno(Aluno newAluno) throws Exception {
        state.addAluno(newAluno);
    }

    /**
     * @param alunos List of Aluno objects
     * @return String of error messages. If none found, the string will be empty
     */
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

    /**
     * @return List of all Aluno objects
     */
    public ArrayList<Aluno> getAlunos() {
        return data.getAlunos();
    }

//    public void editAluno(Aluno newVersionAluno) throws Exception {
//        state.editAluno(newVersionAluno);
//    }

    /**
     * @param toRemove The Aluno object to remove from memory
     */
    public void removeAluno(Aluno toRemove) throws Exception {
        state.removeAluno(toRemove);
    }


    //======GESTAO DOCENTES===========================
    /**
     * @param newDocente The new Docente object to be added
     */
    public void addDocente(Docente newDocente) throws Exception {
        state.addDocente(newDocente);
    }

    /**
     * @param docentes List of Docente objects to be added
     * @return String of errors found, if any
     */
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

    /**
     * @return List of all Docente objects
     */
    public ArrayList<Docente> getDocentes() {
        return data.getDocentes();
    }

//    public void editDocente(Docente newVersionDocente) throws Exception {
//        state.editDocente(newVersionDocente);
//    }

    /**
     * @param toRemove Docente object to be removed
     */
    public void removeDocente(Docente toRemove) throws Exception {
        state.removeDocente(toRemove);
    }


    //======GESTAO PROPOSTAS===========================
    /**
     * @param p The Proposta object to be added
     */
    public void addProposta(Proposta p) throws Exception {
        state.addProposta( p );
    }

    /**
     * @param propostas List of Proposta objects to be added
     */
    public String addPropostas(ArrayList<Proposta> propostas) throws Exception {
        StringBuilder sb = new StringBuilder();
        for(Proposta p : propostas) {
            try {
                addProposta( p );
            } catch (Exception e) {
                sb.append(e.getMessage()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * @return List of all Proposta objects
     */
    public ArrayList<Proposta> getPropostas() {
        return data.getPropostas();
    }

    
//    public void editProposta(Proposta newVersionProposta) {
//
//    }

    /**
     * @param toRemove The Proposta object to be removed
     */
    public void removeProposta(Proposta toRemove) throws Exception {
        state.removeProposta( toRemove );
    }


    //=====GESTAO CANDIDATURAS==========================
    /**
     * @param newCandidatura The new Candidatura object to be added
     */
    public void addCandidatura(Candidaturas newCandidatura) throws Exception {
        state.addCandidatura(newCandidatura);
    }

    /**
     * @param candidaturas List of Candidatura objects to be added
     */
    public String addCandidaturas(ArrayList<Candidaturas> candidaturas) {
        StringBuilder sb = new StringBuilder();
        for(Candidaturas c : candidaturas) {
            try {
                addCandidatura( c );
            } catch (Exception e) {
                sb.append(e.getMessage()).append("\n");
            }
        }
        return sb.toString();
    }

    public void removeCandidatura(Candidaturas toRemove) throws Exception {
        state.removeCandidatura( toRemove );
    }

    /**
     * @return List of all Candidatura objects
     */
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
        careTaker.save();
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

    public ArrayList<Docente> getOrientadores(){ return data.getOrientadores(); }

    public void removeOrientador(Docente toRemove){ state.removeOrientador(toRemove);}

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

    /**
     * Tries to lock the current state. If not possible, throws an exception
     */
    public void lockCurrentState() throws Exception {
        state.lock();
    }

    /**
     * Tries to save all the data to a binary file. If not possible, throws an exception
     * @param filename Name of the file to save data in
     */
    public void saveStateToDisk(String filename) throws Exception {
        data.setLastState( getState() );
        FileUtils.saveDataToDisk( filename, data );
    }

    /**
     * Tries to load data from a file. If not possible, throws an exception
     * @param filename Name of file to load data from
     */
    public void loadStateFromDisk(String filename) throws Exception {
        data = FileUtils.loadDataFromDisk( filename );
        goToState( data.getLastState() );
    }

    public boolean hasUndo() {
        return (careTaker.hasUndo());
    }
    public boolean hasRedo() {
        return (careTaker.hasRedo());
    }
    public void undo() {
        careTaker.undo();
    }
    public void redo() {
        careTaker.redo();
    }
}
