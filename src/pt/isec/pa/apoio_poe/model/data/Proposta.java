package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Proposta implements Serializable {

    private static final long SerialVersionUID = 1L;

    private final String id;
    private String titulo;
    private long idAluno;
    private ArrayList<Aluno> alunosCandidatos = new ArrayList<>();
    private Docente orientador = null;

    public Proposta(String id, String titulo, long idAluno) {
        this.id = id;
        this.titulo = titulo;
        this.idAluno = idAluno;
    }

    public String getId() {
        return id;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public Docente getOrientador() {
        return orientador;
    }

    public void setOrientador(Docente orientador) {
        this.orientador = orientador;
    }


    public ArrayList<Aluno> getAlunosCandidatos() {
        return alunosCandidatos;
    }

    public Aluno getMelhorCandidato(){
        Aluno aluno = null;
        double melhorClassificacao = 0;

        for (Aluno a : alunosCandidatos){
            if(a.getClassificacao() > melhorClassificacao) {
                melhorClassificacao = a.getClassificacao();
                aluno = a;
            }
        }

        return aluno;
    }

    public TipoProposta getType() {
        return null;
    }

    public String getRamo(){
        return "";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
//        if(getClass() != obj.getClass())
//            return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return String.join("    ",  id, titulo );
    }
}
