package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.tipos_proposta.AutoProposto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Estagio;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;

import java.io.Serializable;
import java.util.ArrayList;

public class DataCapsule implements Serializable {

    private static final long SerialVersionUID = 1L;

    private final ArrayList<Aluno> alunos = new ArrayList<>();
    private final ArrayList<Docente> docentes = new ArrayList<>();

    private final ArrayList<Proposta> propostas = new ArrayList<>();
    private final ArrayList<Estagio> estagios = new ArrayList<>();
    private final ArrayList<Projeto> projetos = new ArrayList<>();
    private final ArrayList<AutoProposto> autoPropostos = new ArrayList<>();

    private final ArrayList<Candidaturas> candidaturas = new ArrayList<>();

    public int numAlunosDA = 0;
    public int numAlunosRAS = 0;
    public int numAlunosSI = 0;

    public boolean phaseOneClosed = false;
    public boolean phaseTwoClosed = false;
    public boolean phaseThreeClosed = false;
    public boolean phaseFourClosed = false;

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
}
