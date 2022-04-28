package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

public class PoE_autoproposto extends Proposta {

    private long idAluno;


    public PoE_autoproposto(String id, String titulo, long idAluno) {
        super(id, "null", titulo);
        this.idAluno = idAluno;
    }
}
