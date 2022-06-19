package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Estagio;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.memento.IMemento;
import pt.isec.pa.apoio_poe.model.memento.IOriginator;
import pt.isec.pa.apoio_poe.model.memento.PoEMemento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataCapsule implements Serializable, IOriginator {

    private static final long SerialVersionUID = 1L;

    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Docente> docentes = new ArrayList<>();

    private ArrayList<Proposta> propostas = new ArrayList<>();
    private ArrayList<Estagio> estagios = new ArrayList<>();
    private ArrayList<Projeto> projetos = new ArrayList<>();
    private ArrayList<AutoProposto> autoPropostos = new ArrayList<>();

    private final ArrayList<Candidaturas> candidaturas = new ArrayList<>();

    private HashMap<Aluno,Proposta> atribuicoesAlunos = new HashMap<>();

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

    /**
     * @return List of all Aluno objects
     */
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    /**
     * @return List of all Docente objects
     */
    public ArrayList<Docente> getDocentes() {
        return docentes;
    }

    /**
     * @return List of all Proposta objects
     */
    public ArrayList<Proposta> getPropostas() {
        return propostas;
    }

    /**
     * @return List of all Estagio objects
     */
    public ArrayList<Estagio> getEstagios() {
        return estagios;
    }

    /**
     * @return List of all Projetos objects
     */
    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    /**
     * @return List of all Autoproposta objects
     */
    public ArrayList<AutoProposto> getAutoPropostos() {
        return autoPropostos;
    }

    /**
     * @return List of all Candidatura objects
     */
    public ArrayList<Candidaturas> getCandidaturas() {
        return candidaturas;
    }

    /**
     * @return List of all Aluno objects and their respective Posposta objects
     */
    public HashMap<Aluno, Proposta> getAtribuicoesAlunos() {
        return atribuicoesAlunos;
    }

    /**
     * @param email The email address to look for
     * @return True if there's either an Aluno or Docente with the same email address
     */
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

    /**
     * @return Lista de todos os Alunos com Autoproposta
     */
    public ArrayList<Aluno> getAlunosComAutoproposta() {
        ArrayList<Aluno> alunoAuto = new ArrayList<>();

        for(AutoProposto prop : autoPropostos){
            Aluno a = findAluno( prop.getIdAluno() );
            if( a != null )
                alunoAuto.add( a );
        }
        return alunoAuto;
    }

    /**
     * @return Lista de todos os Alunos com candidatura
     */
    public ArrayList<Aluno> getAlunosComCandidatura(){
        ArrayList<Aluno> comCandidatura = new ArrayList<>();
        for(Candidaturas c : candidaturas){
            Aluno a = findAluno( c.getIdAluno() );
            if( a != null )
                comCandidatura.add( a );
        }
        return comCandidatura;
    }

    /**
     * @return Lista de todos os Alunos sem candidatura
     */
    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura, ArrayList<Aluno> comAutoproposta){
        // Recebe a lista de alunos com candidatura e com autoproposta, para ser mais facil

        ArrayList<Aluno> semCandidatura = new ArrayList<>();
        for(Aluno a : alunos)
            if( ! comCandidatura.contains( a ) && ! comAutoproposta.contains(a))
                semCandidatura.add( a );
        return semCandidatura;
    }

    /**
     * @return Lista de todas as Autopropostas
     */
    public ArrayList<Proposta> getAutopropostasAlunos() {
        ArrayList<Proposta> autopropostas = new ArrayList<>();
        for(Proposta p : propostas)
            if(p.getType() == TipoProposta.AUTOPROPOSTO)
                autopropostas.add(p);
        return autopropostas;
    }

    /**
     * @return Lista de todas as propostas atribuidas pelos docentes (ou seja, os projetos)
     */
    public ArrayList<Proposta> getPropostasDocentes() {
        ArrayList<Proposta> pdocentes = new ArrayList<>();
        for(Proposta p : propostas)
            if(p.getType() == TipoProposta.PROJETO)
                pdocentes.add(p);
        return pdocentes;
    }

    /**
     * @return Lista de todas as Propostas com Candidaturas
     */
    public Set<Proposta> getPropostasComCandidaturas() {
        Set<Proposta> pcandidatura = new HashSet<>();
        for (Candidaturas c : candidaturas)
            for(Proposta p : propostas)
                if( c.getHashCodePropostas().contains(p.hashCode()) )
                    pcandidatura.add(p);
        return pcandidatura;
    }

    /**
     * @return Lista de todas as Propostas sem Candidatura
     */
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        ArrayList<Proposta> props = new ArrayList<>();
        for (Proposta p : getPropostasComCandidaturas())
            if( ! propostas.contains(p) )
                props.add( p );
        return props;
    }

    /**
     * @return Lista de todos os Alunos com Proposta atribuida
     */
    public ArrayList<Aluno> getAlunosComPropostaAtribuida(){
        return new ArrayList<>( atribuicoesAlunos.keySet() );
    }

    /**
     * @return Lista de todos os Alunos sem Proposta atribuída
     */
    public ArrayList<Aluno> getAlunosSemPropostaAtribuidas(){
        ArrayList<Aluno> stud = new ArrayList<>();
        for( Aluno a : alunos ){
            if( ! atribuicoesAlunos.containsKey(a) )
                stud.add(a);
        }
        return stud;
    }

    /**
     * @return Lista de todas as Propostas disponíveis (ainda nao atribuidas)
     */
    public ArrayList<Proposta> getPropostasDisponiveis(){
        ArrayList<Proposta> propostasDisponiveis = new ArrayList<>();
        for ( Proposta p : propostas){
            if(!atribuicoesAlunos.containsValue(p))
                propostasDisponiveis.add(p);
        }
        return propostasDisponiveis;
    }

    /**
     * @return Lista de todas as Propostas ja atribuidas
     */
    public ArrayList<Proposta> getPropostasAtribuidas(){
        return new ArrayList<>( atribuicoesAlunos.values() );
    }

    /**
     * @return Lista de todos os Alunos com Candidaturas e sem Proposta atribuida
     */
    public ArrayList<Aluno> getAlunosSemPropostasComCandidaturas() {
        HashSet<Aluno> alunos = new HashSet<>();    //does not allow dupplicates
        ArrayList<Aluno> semProposta = getAlunosSemPropostaAtribuidas();
        for(Aluno a : getAlunosComCandidatura() )
            if( semProposta.contains(a) )
                alunos.add( a );
        return new ArrayList<>( alunos );
    }

    /**
     * @return Lista de todos os Docentes orientadores
     */
    public ArrayList<Docente> getOrientadores() {
        ArrayList<Docente> orientadores = new ArrayList<>();
        for(Docente d : docentes){
            if(d.isOrientador())
                orientadores.add(d);
        }
        return orientadores;
    }

    /**
     * @return String multi-linha de estatísticas sobre os Docentes orientadores
     */
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

    /**
     * @param idAluno ID do aluno a procurar
     * @return Objeto Aluno com aquele ID, ou null caso nao exista
     */
    public Aluno findAluno(long idAluno) {
        for( Aluno a : alunos )
            if( a.getId() == idAluno )
                return a;
        return null;
    }

    /**
     * @param idProposta ID da proposta a procurar
     * @return Objeto Proposta com aquele ID, ou null caso nao exista
     */
    public Proposta findProposta(String idProposta) {
        for( Proposta p : propostas )
            if( idProposta.equals( p.getId() ) )
                return p;
        return null;
    }

    /**
     * Guarda nesta capsula o estado atual da aplicacao para que seja possivel
     * identificar o estado em que a aplicacao se encontrava quando estes dados
     * forem gravados no disco
     * @param lastState Estado atual da aplicacao
     */
    public void setLastState(State lastState) {
        this.lastState = lastState;
    }

    /**
     * Devolve o estado em qye a aplicacao estava quando estes dados foram gravados no disco
     * @return O ultimo estado em que a aplicacao se encontrava
     */
    public State getLastState() {
        return lastState;
    }


    @Override
    public IMemento save() {
        return new PoEMemento(this);
    }

    @Override
    public void restore(IMemento memento) {
        Object obj = memento.getSnapshot();
        if (obj instanceof DataCapsule m) {
            this.atribuicoesAlunos = m.atribuicoesAlunos;
            this.docentes = m.docentes;
            this.propostas = m.propostas;
            this.estagios = m.estagios;
            this.projetos = m.projetos;
            this.autoPropostos = m.autoPropostos;
        }
    }
}
