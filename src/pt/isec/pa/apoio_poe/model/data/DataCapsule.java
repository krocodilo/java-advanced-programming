package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Estagio;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataCapsule implements Serializable {

    private static final long SerialVersionUID = 1L;

    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private final ArrayList<Docente> docentes = new ArrayList<>();

    private final ArrayList<Proposta> propostas = new ArrayList<>();
    private final ArrayList<Estagio> estagios = new ArrayList<>();
    private final ArrayList<Projeto> projetos = new ArrayList<>();
    private final ArrayList<AutoProposto> autoPropostos = new ArrayList<>();

    private final ArrayList<Candidaturas> candidaturas = new ArrayList<>();

    private final HashMap<Aluno,Proposta> atribuicoesAlunos = new HashMap<>();

    public int numAlunosDA = 0;
    public int numAlunosRAS = 0;
    public int numAlunosSI = 0;

    public boolean phaseOneLocked = false;
    public boolean phaseTwoLocked = false;
    public boolean phaseThreeLocked = false;
    public boolean phaseFourLocked = false;

    // For when saving and reading this data from a file
    private State lastState = State.PHASE_ONE;  // default state

    public DataCapsule() {
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public ArrayList<Docente> getDocentes() {
        return docentes;
    }

    public ArrayList<Proposta> getPropostas() {
        return propostas;
    }

    public ArrayList<Estagio> getEstagios() {
        return estagios;
    }

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public ArrayList<AutoProposto> getAutoPropostos() {
        return autoPropostos;
    }

    public ArrayList<Candidaturas> getCandidaturas() {
        return candidaturas;
    }

    public HashMap<Aluno, Proposta> getAtribuicoesAlunos() {
        return atribuicoesAlunos;
    }

    public boolean emailExists(String email) {
        // verifica se existe aluno ou docente com este endereço de email

        for(Aluno a : alunos)
            if(email.equalsIgnoreCase( a.getEmail() ))
                return true;
        for(Docente d : docentes)
            if(email.equalsIgnoreCase( d.getEmail() ))
                return true;
        return false;
    }

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        ArrayList<Aluno> autopropostos = new ArrayList<>();

        for(AutoProposto prop : autoPropostos){
            Aluno a = findAluno( prop.getIdAluno() );
            if( a != null )
                autopropostos.add( a );
        }

        return autopropostos;
    }

    public ArrayList<Aluno> getAlunosComCandidatura(){
        ArrayList<Aluno> comCandidatura = new ArrayList<>();
        for(Candidaturas c : candidaturas){
            Aluno a = findAluno( c.getIdAluno() );
            if( a != null )
                comCandidatura.add( a );
        }
        return comCandidatura;
    }

    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura, ArrayList<Aluno> comAutoproposta){
        // Recebe a lista de alunos com candidatura e com autoproposta, para ser mais facil

        ArrayList<Aluno> semCandidatura = new ArrayList<>();
        for(Aluno a : alunos)
            if( ! comCandidatura.contains( a ) && ! comAutoproposta.contains(a))
                semCandidatura.add( a );
        return semCandidatura;
    }

    public ArrayList<Proposta> getAutopropostasAlunos() {
        ArrayList<Proposta> autopropostas = new ArrayList<>();
        for(Proposta p : propostas)
            if(p.getType() == TipoProposta.AUTOPROPOSTO)
                autopropostas.add(p);
        return autopropostas;
    }

    public ArrayList<Proposta> getPropostasDocentes() {
        ArrayList<Proposta> pdocentes = new ArrayList<>();
        for(Proposta p : propostas)
            if(p.getType() == TipoProposta.PROJETO)
                pdocentes.add(p);
        return pdocentes;
    }

    public Set<Proposta> getPropostasComCandidaturas() {
        Set<Proposta> pcandidatura = new HashSet<>();
        for (Candidaturas c : candidaturas)
            for(Proposta p : propostas)
                if( c.getHashCodePropostas().contains(p.hashCode()) )
                    pcandidatura.add(p);
        return pcandidatura;
    }

    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        ArrayList<Proposta> props = new ArrayList<>();
        for (Proposta p : getPropostasComCandidaturas())
            if( ! propostas.contains(p) )
                props.add( p );
        return props;
    }

    public ArrayList<Aluno> getAtribuicoesAlunosLista(){
        ArrayList<Aluno> alunos = new ArrayList<>();
        for( Aluno a : alunos ){
            if( atribuicoesAlunos.containsKey(a))
                alunos.add(a);
        }
        return alunos;
    }

    public ArrayList<Aluno> getSemAtribuicoesAlunosLista(){
        ArrayList<Aluno> alunos = new ArrayList<>();
        for( Aluno a : alunos ){
            if( ! atribuicoesAlunos.containsKey(a))
                alunos.add(a);
        }
        return alunos;
    }

    public Aluno findAluno(long idAluno) {
        for( Aluno a : alunos )
            if( a.getId() == idAluno )
                return a;
        return null;
    }

    public Proposta findProposta(String idProposta) {
        for( Proposta p : propostas )
            if( p.getId() == idProposta )
                return p;
        return null;
    }

    public State getLastState() {
        return lastState;
    }

    public void setLastState(State lastState) {
        this.lastState = lastState;
    }
}
