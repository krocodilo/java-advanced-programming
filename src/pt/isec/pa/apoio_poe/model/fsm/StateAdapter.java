package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.fsm.states.PhaseFive;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseFour.GestaoOrientadores;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseFour.PhaseFour;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.*;
import pt.isec.pa.apoio_poe.model.fsm.states.PhaseThree;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.GestaoCandidaturas;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseTwo.PhaseTwo;

import java.util.ArrayList;
import java.util.Set;

public class StateAdapter implements IState {

    protected Context context;
    protected DataCapsule data;

    protected StateAdapter(Context context, DataCapsule data) {
        this.context = context;
        this.data = data;
    }

    /**
     * @return The next State (IState object), if it has one
     */
    @Override
    public IState getNextState() {
        return this;
    }

    /**
     * @return The previous State (IState object), if it has one
     */
    @Override
    public IState getPreviousState() {
        return this;
    }

    /**
     *
     * @param state The state to go to
     * @return The target IState object
     */
    @Override
    public IState goToState(State state) {
        return switch (state) {
            case PHASE_ONE -> new PhaseOne(context, data);
            case PHASE_TWO -> new PhaseTwo(context, data);
            case PHASE_THREE -> new PhaseThree(context, data);
            case PHASE_FOUR -> new PhaseFour(context, data);
            case PHASE_FIVE -> new PhaseFive(context, data);
            case GESTAO_ALUNOS -> new GestaoAlunos(context, data);
            case GESTAO_DOCENTES -> new GestaoDocentes(context, data);
            case GESTAO_PROPOSTAS -> new GestaoPropostas(context, data);
            case GESTAO_CANDIDATURAS -> new GestaoCandidaturas(context, data);
            case GESTAO_ORIENTADORES -> new GestaoOrientadores(context, data);
            default -> this;
        };
    }

    //======GESTAO ALUNOS===========================
    @Override
    public void addAluno(Aluno newAluno) throws Exception {
    }

    @Override
    public void editAluno(Aluno newVersionAluno) throws Exception {
    }

    @Override
    public void removeAluno(Aluno toRemove) throws Exception {
    }


    //======GESTAO DOCENTES===========================
    @Override
    public void addDocente(Docente newDocente) throws Exception {

    }


    @Override
    public void editDocente(Docente newVersionDocente) throws Exception {

    }

    @Override
    public void removeDocente(Docente toRemove) throws Exception {

    }


    //======GESTAO PROPOSTAS===========================
    @Override
    public void addProposta(Proposta newProposta) throws Exception {

    }

    @Override
    public Proposta getPropostas() {
        return null;
    }

    @Override
    public void editProposta(Proposta newVersionProposta) throws Exception {

    }

    @Override
    public void removeProposta(Proposta toRemove) throws Exception {

    }

    //======GESTAO CANDIDATURAS===========================
    @Override
    public void addCandidatura(Candidaturas newCandidatura) throws Exception {}

    @Override
    public void removeCandidatura(Candidaturas toRemove) throws Exception {}

    @Override
    public ArrayList<Candidaturas> getCandidaturas() {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosComCandidatura() {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosSemCandidatura(ArrayList<Aluno> comCandidatura, ArrayList<Aluno> comAutoproposta) {
        return null;
    }

    @Override
    public ArrayList<Proposta> getAutopropostasAlunos() {
        return null;
    }

    @Override
    public ArrayList<Proposta> getPropostasDocentes() {
        return null;
    }

    @Override
    public Set<Proposta> getPropostasComCandidaturas() {
        return null;
    }

    @Override
    public ArrayList<Proposta> getPropostasSemCandidaturas() {
        return null;
    }


    //ATRIBUICAO PROPOSTAS
    @Override
    public ArrayList<Aluno> getAlunosComPropostaAtribuida() {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosSemPropostaAtribuida() {
        return null;
    }

    @Override
    public ArrayList<Proposta> getPropostasDisponiveis() {
        return null;
    }

    @Override
    public ArrayList<Proposta> getPropostasAtribuidas() {
        return null;
    }

    @Override
    public void atribuicaoAutomaticaAutoPropostas(){}

    @Override
    public void atribuicaoAutomaticaPropostas(){}

    @Override
    public void AtribuicaoManual(Aluno aluno, Proposta proposta){}

    @Override
    public void RemoverAtribuicao(Aluno aluno){}

    @Override
    public void RemoverTodasAtribuicoes(){}

    //PhaseFour
    @Override
    public void atribuicaoOrientadoresProponentes(){}

    @Override
    public void AtribuicaoOrientadorProposta(Docente d,Proposta p){}

    @Override
    public void removeOrientador(Docente toRemove) {}

    @Override
    public ArrayList<Aluno> getAlunosComPropostaComOrientador(){
        return null;
    }

    @Override
    public ArrayList<Aluno> getAlunosComPropostaSemOrientador() {
        return null;
    }

    @Override
    public String getEstatisticasOrientadores() {
        return null;
    }

    @Override
    public ArrayList<Projeto> getProjetos() {
        return null;
    }

    // Phase 5
    @Override
    public ArrayList<Aluno> getAlunosSemPropostasComCandidaturas() {
        return null;
    }

    /**
     * @return Current state
     */
    @Override
    public State getState() {
        return null;
    }

    /**
     * @return True if the current phase is locked
     */
    @Override
    public boolean isLocked() {
        return false;
    }

    /**
     * Tries to lock the current phase
     * @throws Exception If unable to lock
     */
    @Override
    public void lock() throws Exception { }

    /**
     * @param isLocked Boolean (saved in DataCapsule object) that indicates if
     *                 it's phase is locked.
     * @throws Exception If the phase is locked
     */
    @Override
    public void checkIfLocked(boolean isLocked) throws Exception {
        if( isLocked )
            throw new Exception("Esta fase foi bloqueada. Nao e possivel realizar alteracoes.");
    }
}
