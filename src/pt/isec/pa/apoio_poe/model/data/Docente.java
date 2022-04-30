package pt.isec.pa.apoio_poe.model.data;

import java.io.Serializable;

public class Docente implements Serializable {

    private static final long SerialVersionUID = 1L;

    private String email;
    private String name;
    private boolean orientador;
    private boolean proponenteProjeto;

    public Docente(String email,String name) {
        this.email = email;
        this.name = name;
        this.orientador = false;
        this.proponenteProjeto = false;
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
