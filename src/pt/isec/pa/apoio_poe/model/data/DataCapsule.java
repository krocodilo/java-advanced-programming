package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataCapsule implements Serializable {

    private static final long SerialVersionUID = 1L;

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

    public String mostraAlunos(){
        StringBuilder sb = new StringBuilder("Alunos inscritos na cadeira PoE :");
        for(Aluno a : alunos){
            sb.append("INFO:"+a.getId()+";"+a.getName()+";"+a.getEmail()+";"+a.getCurso()+";"+a.getRamo()+";"+a.getClassificacao()+";Pode estagiar?:"+a.isPodeEstagiar()+"\n");
        }
        return sb.toString();
    }
}
