package pt.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;

public class DataCapsule {

    ArrayList<Aluno> alunos;
    ArrayList<Docente> docentes;
    ArrayList<Proposta> propostas;

    public DataCapsule() {
        alunos = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
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
}
