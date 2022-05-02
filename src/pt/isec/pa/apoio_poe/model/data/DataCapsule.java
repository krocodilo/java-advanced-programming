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

    public ArrayList<Aluno> getAlunosComPropostaAtribuida(){
        return new ArrayList<>( atribuicoesAlunos.keySet() );
    }

    public ArrayList<Aluno> getAlunosSemPropostaAtribuidas(){
        ArrayList<Aluno> stud = new ArrayList<>();
        for( Aluno a : alunos ){
            if( ! atribuicoesAlunos.containsKey(a) )
                stud.add(a);
        }
        return stud;
    }

    public ArrayList<Proposta> getPropostasDisponiveis(){
        ArrayList<Proposta> propostasDisponiveis = new ArrayList<>();
        for ( Proposta p : propostas){
            if(!atribuicoesAlunos.containsValue(p))
                propostasDisponiveis.add(p);
        }
        return propostasDisponiveis;
    }

    public ArrayList<Proposta> getPropostasAtribuidas(){
        return new ArrayList<>( atribuicoesAlunos.values() );
    }

    public ArrayList<Aluno> getAlunosSemPropostasComCandidaturas() {
        HashSet<Aluno> alunos = new HashSet<>();    //does not allow dupplicates
        ArrayList<Aluno> semProposta = getAlunosSemPropostaAtribuidas();
        for(Aluno a : getAlunosComCandidatura() )
            if( semProposta.contains(a) )
                alunos.add( a );
        return new ArrayList<>( alunos );
    }

    public ArrayList<Docente> getOrientadores() {
        ArrayList<Docente> orientadores = new ArrayList<>();
        for(Docente d : docentes){
            if(d.isOrientador())
                orientadores.add(d);
        }
        return orientadores;
    }

    public String getEstatisticasOrientadores() {
        StringBuilder str = new StringBuilder();
        ArrayList<Docente> orientadores = getOrientadores();
        ArrayList<Proposta> props = getPropostasAtribuidas();
        int media = 0, min = props.size(), max = 0;
        ArrayList<Integer> countArray = new ArrayList<>();

        for(Docente ori : orientadores){
            int count = 0;
            for(Proposta p : props){
                if( ori == p.getOrientador() ){
                    str.append( ori.getName() ).append(" -> ").append( p.getId() ).append(":  ")
                            .append( p.getTitulo() ).append("\n");
                    count++;
                }
            }
            countArray.add( count );
            media += count;
            if(count < min)
                min = count;
            if(count > max)
                max = count;
        }
        media = media / orientadores.size();
        str.append("\n").append("\t\tNumero de orientacoes por Docente:");
        for(int i = 0; i < docentes.size(); i++)
            str.append( orientadores.get(i).getName() ).append(" -> ").append( countArray.get(i) );

        str.append("\n\n").append("Média: ").append(media)
                .append("\tMin: ").append(min).append("\tMax: ").append(max);
        return str.toString();
    }

    public Aluno findAluno(long idAluno) {
        for( Aluno a : alunos )
            if( a.getId() == idAluno )
                return a;
        return null;
    }

    public Proposta findProposta(String idProposta) {
        for( Proposta p : propostas )
            if( idProposta.equals( p.getId() ) )
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
