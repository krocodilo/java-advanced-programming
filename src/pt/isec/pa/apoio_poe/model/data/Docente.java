package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class Docente implements Serializable {

    private static final long SerialVersionUID = 1L;

    private final String email;
    private String name;

    private boolean orientador;
    private boolean proponenteProjeto;

    public Docente(String email,String name) {
        this.email = email;
        this.name = name;
        this.orientador = false;
        this.proponenteProjeto = false;
    }

    public static Docente parseDocenteCSV(String str) throws Exception {

        ArrayList<String> values = FileUtils.splitLineCSV( str );
        if( values.size() != 2)
            throw new Exception("Must have 2 values!");

        try {
            return new Docente(
                    values.get(0),
                    values.get(1)
            );
        } catch (NumberFormatException e){
            throw new Exception("Error in values");
        }
    }
    public boolean isOrientador() {
        return orientador;
    }

    public void setOrientador(boolean orientador) {
        this.orientador = orientador;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(getClass() != obj.getClass())
            return false;
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return String.join("    ",  name, email );
    }
}
