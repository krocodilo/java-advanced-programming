package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable {

    private static final long SerialVersionUID = 1L;

    private final long id;
    private String name;
    private final String email;
    private String curso;
    private String ramo;
    private double classificacao;
    private boolean podeEstagiar;

    public Aluno(long id, String name, String email, String curso, String ramo, double classificacao, boolean podeEstagiar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classificacao = classificacao;
        this.podeEstagiar = podeEstagiar;
    }

    public long getId() {
        return id;
    }

    public String getRamo() {
        return ramo;
    }

    public String getEmail() {
        return email;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public static Aluno parseAlunoCSV(String str) throws Exception {

        ArrayList<String> values = FileUtils.splitLineCSV( str );
        if( values.size() != 7)
            throw new Exception("Must have 7 values!");

        try {
            return new Aluno(
                    Long.parseLong(values.get(0)),
                    values.get(1),
                    values.get(2),
                    values.get(3),
                    values.get(4),
                    Double.parseDouble(values.get(5)),
                    Boolean.parseBoolean(values.get(6))
            );
        } catch (NumberFormatException e){
            throw new Exception("Error in values");
        }
    }

    @Override
    public boolean equals(Object obj) {
        //if(getClass() != obj.getClass())  // ListView throws an exception when selecting items if we verify this way
        if(! (obj instanceof Aluno))
            return false;
        return this.hashCode() == obj.hashCode();
    }

    public boolean equals() {
        return true;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public String toString() {
        return String.join("    ", String.valueOf(id), name, email, curso, ramo, String.valueOf(classificacao) );
    }
}
