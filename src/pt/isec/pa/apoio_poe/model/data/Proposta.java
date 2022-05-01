package pt.isec.pa.apoio_poe.model.data;

import pt.isec.pa.apoio_poe.model.data.tipos_proposta.Projeto;
import pt.isec.pa.apoio_poe.model.data.tipos_proposta.TipoProposta;
import pt.isec.pa.apoio_poe.utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Proposta implements Serializable {

    private static final long SerialVersionUID = 1L;

    private String id;
    private String titulo;

    public Proposta(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public long getIdAluno() {
        return -1;
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
