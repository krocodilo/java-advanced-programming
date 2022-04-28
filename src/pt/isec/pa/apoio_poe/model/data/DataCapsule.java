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

    public DataCapsule(ArrayList<Aluno> alunos, ArrayList<Docente> docentes, ArrayList<Proposta> propostas) {
        this.alunos = alunos;
        this.docentes = docentes;
        this.propostas = propostas;
    }
}
