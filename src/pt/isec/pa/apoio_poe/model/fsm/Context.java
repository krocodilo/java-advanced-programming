package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.PhaseOne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Context {

    private IState state;
    private DataCapsule data;

    public Context() {
        data = new DataCapsule();
        state = new PhaseOne(data);
    }

    public void goToState(State s){
        state = state.goToState( s );
    }


    //======GESTAO ALUNOS===========================
    public void addAluno(Aluno newAluno) {
        state.addAluno(newAluno);
    }

    public void addAlunos(List<Aluno> alunos) {
        for(Aluno a : alunos)
            addAluno( a );
    }

    public ArrayList<Aluno> getAlunos() {
        return data.getAlunos();
    }

    public void editAluno(Aluno newVersionAluno) {
        state.editAluno(newVersionAluno);
    }

    public void removeAluno(Aluno toRemove) {
        state.removeAluno(toRemove);
    }


    //======GESTAO DOCENTES===========================
    public void addDocente(Docente newDocente) {
        state.addDocente(newDocente);
    }

    public void addDocentes(List<Docente> docentes) {
        for(Docente d : docentes)
            addDocente( d );
    }
    
    public ArrayList<Docente> getDocentes() {
        return data.getDocentes();
    }

    public void editDocente(Docente newVersionDocente) {
        state.editDocente(newVersionDocente);
    }
    
    public void removeDocente(Docente toRemove) {
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

    
    public void editProposta(Proposta newVersionProposta) {

    }

    //=====GESTAO CANDIDATURAS==========================

    public void addCandidatura(Candidaturas newCandidatura){
        state.addCandidatura(newCandidatura);
    }

    public void addCandidaturas(ArrayList<Candidaturas> candidaturas){
        for(Candidaturas c : candidaturas)
            addCandidatura(c);
    }

    public ArrayList<Candidaturas> getCandidaturas() {
        return data.getCandidaturas();
    }

    public ArrayList<Aluno> getAlunosAutoproposta(){
        ArrayList<Aluno> autopropostos = new ArrayList<>();
        for(Proposta p : data.getPropostas()){
            if(p.getType() == TipoProposta.AUTOPROPOSTO){
                for(Aluno a : data.getAlunos()){
                    if(p.getIdAluno() == a.getId())
                        autopropostos.add(a);
                }
            }
        }
        return autopropostos;
    }

    public ArrayList<Aluno> getAlunosComCandidatura(){
        ArrayList<Aluno> comCandidatura = new ArrayList<>();
        for(Candidaturas c : data.getCandidaturas()){
            for(Aluno a : data.getAlunos()){
                if(c.getIdAluno() == a.getId())
                    comCandidatura.add(a);
            }
        }
        return comCandidatura;
    }


    /*public ArrayList<Aluno> getAlunosSemCandidatura(){

    }*/

    public ArrayList<Proposta> getAutopropostasAlunos(){
        ArrayList<Proposta> autopropostas = new ArrayList<>();
        for(Proposta p : data.getPropostas())
            if(p.getType() == TipoProposta.AUTOPROPOSTO)
                autopropostas.add(p);
        return autopropostas;
    }

    public ArrayList<Proposta> getPropostasDocentes(){
        ArrayList<Proposta> pdocentes = new ArrayList<>();
        for(Proposta p : data.getPropostas())
            if(p.getType() == TipoProposta.PROJETO)
                pdocentes.add(p);
        return pdocentes;
    }

    public Set<Proposta> getPropostasComCandidaturas(){
        Set<Proposta> pcandidatura = new HashSet<>();
        for (Candidaturas c : data.getCandidaturas()){
            for(Proposta p : data.getPropostas()){
                if(c.getIdsPropostas().contains(p))
                    pcandidatura.add(p);
            }
        }
        return pcandidatura;
    }

    /*public ArrayList<Proposta> getPropostasSemCandidaturas(){

    }*/


    
    public void removeProposta(Proposta toRemove) {

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

    public boolean isLocked() {
        return state.isLocked();
    }

    public void lockCurrentState() {
        state.lock();
    }
}
