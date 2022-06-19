package pt.isec.pa.apoio_poe.ui.gui;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

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

    public void changeMessage(String msg) {
//        context.changeMessage(msg);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public void changeNumber(int nr) {
//        context.changeNumber(nr);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public String addAlunos(List<Aluno> alunos) {
        String ret = context.addAlunos(alunos);
        pcs.firePropertyChange(PROP_DATA,null,null);
        return ret;
    }

    public ArrayList<Aluno> getAlunos() {
        return context.getAlunos();
    }

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return context.getAlunosComAutoproposta();
    }

    public ArrayList<Aluno> getAlunosComCandidatura() {
        return context.getAlunosComCandidatura();
    }

    public ArrayList<Aluno> getAlunosSemCandidatura() {
        return context.getAlunosSemCandidatura(context.getAlunosComCandidatura(),context.getAlunosComAutoproposta());
    }

    public void removeAluno(Aluno toRemove) throws Exception {
        context.removeAluno(toRemove);
    }

    public ArrayList<Proposta> getPropostas() {
        return context.getPropostas();
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

    public void lockCurrentState() throws Exception {
        context.lockCurrentState();
    }

    public void saveStateToDisk(String filename) throws Exception {
        context.saveStateToDisk(filename);
    }

    public void loadStateFromDisk(String filename) throws Exception {
        context.loadStateFromDisk(filename);
    }
}
