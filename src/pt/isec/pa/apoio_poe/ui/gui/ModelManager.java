package pt.isec.pa.apoio_poe.ui.gui;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelManager {
    public static final String PROP_STATE = "state";
    public static final String PROP_DATA  = "data";

    Context context;
    PropertyChangeSupport pcs;

    public ModelManager() {
        this.context = new Context();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public State getState() {
        return context.getState();
    }

    public void goToState(State s){
        context.goToState( s );
        pcs.firePropertyChange(PROP_STATE,null,context.getState());
    }

    public void next() {
        context.nextState();
        pcs.firePropertyChange(PROP_STATE,null,context.getState());
    }

    public void previous() {
        context.previousState();
        pcs.firePropertyChange(PROP_STATE,null,context.getState());
    }

    public String addAlunos(List<Aluno> alunos) {
        String ret = context.addAlunos(alunos);
        pcs.firePropertyChange(PROP_DATA,null,null);
        return ret;
    }

    public ArrayList<Aluno> getAlunos() {
        return context.getAlunos();
    }

    public void removeAluno(Aluno toRemove) throws Exception {
        context.removeAluno(toRemove);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public String addDocentes(List<Docente> docentes) {
        String ret = context.addDocentes(docentes);
        pcs.firePropertyChange(PROP_DATA,null,null);
        return ret;
    }

    public ArrayList<Docente> getDocentes() {
        return context.getDocentes();
    }

    public ArrayList<Projeto> getProjetos() {
        return context.getProjetos();
    }

    public void removeDocente(Docente toRemove) throws Exception {
        context.removeDocente(toRemove);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public String addPropostas(ArrayList<Proposta> propostas) throws Exception {
        String ret = context.addPropostas(propostas);
        pcs.firePropertyChange(PROP_DATA,null,null);
        return ret;
    }

    public ArrayList<Proposta> getPropostas() {
        return context.getPropostas();
    }

    public void removeProposta(Proposta toRemove) throws Exception {
        context.removeProposta( toRemove );
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return context.getAlunosComAutoproposta();
    }

    public ArrayList<Aluno> getAlunosComCandidatura(){
        return context.getAlunosComCandidatura();
    }

    public ArrayList<Aluno> getAlunosSemCandidatura(){
        return context.getAlunosSemCandidatura(
                getAlunosComCandidatura(), getAlunosComAutoproposta()
        );
    }

    public ArrayList<Aluno> getAlunosComPropostaAtribuida() {
        return context.getAlunosComPropostaAtribuida();
    }

    public ArrayList<Aluno> getAlunosSemPropostaAtribuida() {
        return context.getAlunosSemPropostaAtribuida();
    }

    public ArrayList<Proposta> getAutopropostasAlunos() {
        return context.getAutopropostasAlunos();
    }
    public ArrayList<Proposta> getPropostasDocentes() {
        return context.getPropostasDocentes();
    }
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        return context.getPropostasSemCandidaturas();
    }
    public Set<Proposta> getPropostasComCandidaturas() {
        return context.getPropostasComCandidaturas();
    }

    public ArrayList<Proposta> getPropostasDisponiveis() {
        return context.getPropostasDisponiveis();
    }

    public ArrayList<Proposta> getPropostasAtribuidas() {
        return context.getPropostasAtribuidas();
    }

    public void atribuicaoAutomaticaAutoPropostas(){
        context.atribuicaoAutomaticaAutoPropostas();
    }
    public void atribuicaoAutomaticaPropostas(){
        context.atribuicaoAutomaticaPropostas();
    }

    public void AtribuicaoManual(Aluno aluno, Proposta proposta){
        context.AtribuicaoManual(aluno,proposta);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public void RemoverAtribuicao(Aluno aluno){
        context.RemoverAtribuicao(aluno);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public ArrayList<Aluno> getAlunosSemPropostasComCandidaturas() {
        return context.getAlunosSemPropostasComCandidaturas();
    }

    public ArrayList<Candidaturas> getCandidaturas(){
        return context.getCandidaturas();
    }
    public String addCandidaturas(ArrayList<Candidaturas> candidaturas) {
        String ret = context.addCandidaturas(candidaturas);
        pcs.firePropertyChange(PROP_DATA,null,null);
        return ret;
    }
    public void removeCandidatura(Candidaturas candidaturas) throws Exception{
        context.removeCandidatura(candidaturas);
    }

    public ArrayList<Aluno> getAlunosComPropostaComOrientador(){
        return context.getAlunosComPropostaComOrientador();
    }

    public ArrayList<Aluno> getAlunosComPropostaSemOrientador(){
        return context.getAlunosComPropostaSemOrientador();
    }

    public void atribuicaoOrientadorProposta(Docente d,Proposta p){
        context.atribuicaoOrientadorProposta(d,p);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public String getEstatisticasOrientadores(){
        return context.getEstatisticasOrientadores();
    }

    public ArrayList<Docente> getOrientadores(){
        return context.getOrientadores();
    }

    public void removeOrientador(Docente toRemove){
        context.removeOrientador(toRemove);
    }

    public void atribuicaoOrientadoresProponentes(){
        context.atribuicaoOrientadoresProponentes();
    }

    public void lockCurrentState() throws Exception {
        context.lockCurrentState();
    }

    public boolean phaseTwoLocked(){
        return context.phaseTwoLocked();
    }

    public void saveStateToDisk(String filename) throws Exception {
        context.saveStateToDisk(filename);
    }

    public void loadStateFromDisk(String filename) throws Exception {
        context.loadStateFromDisk(filename);
    }
}
