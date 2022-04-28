package pt.isec.pa.apoio_poe.model.data;

public class Docente {

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
}
