package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseFive.PhaseFive;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseFour.PhaseFour;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseOne.*;
import pt.isec.pa.apoio_poe.model.fsm.states.phaseThree.PhaseThree;
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

    @Override
    public IState getNextState() {
        return this;
    }

    @Override
    public IState getPreviousState() {
        return this;
    }

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

    @Override
    public State getState() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public void lock() throws Exception { }

    @Override
    public void checkIfLocked(boolean isLocked) throws Exception {
        if( isLocked )
            throw new Exception("Esta fase foi bloqueada. Nao e possivel realizar alteracoes.");
    }
}
