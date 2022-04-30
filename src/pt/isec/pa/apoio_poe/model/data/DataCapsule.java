package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataCapsule implements Serializable {

    private static final long SerialVersionUID = 1L;

    private ArrayList<Aluno> alunos;
    private ArrayList<Docente> docentes;
    private ArrayList<Proposta> propostas;

    public int numAlunosDA = 0;
    public int numAlunosRAS = 0;
    public int numAlunosSI = 0;
    ArrayList<Candidaturas> candidaturas;

    public DataCapsule() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
        candidaturas = new ArrayList<>();
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

    public ArrayList<Candidaturas> getCandidaturas() {
        return candidaturas;
    }
}
