package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataCapsule implements Serializable {

    private static final long SerialVersionUID = 1L;

    ArrayList<Aluno> alunos;
    ArrayList<Docente> docentes;
    ArrayList<Proposta> propostas;
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
