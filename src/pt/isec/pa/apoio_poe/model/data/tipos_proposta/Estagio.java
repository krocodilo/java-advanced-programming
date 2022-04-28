package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

public class Estagio extends Proposta {

    private String entidade;
    private long idAluno;

    public Estagio(String id, String ramoDestino, String titulo, String entidade) {
        super(id, ramoDestino, titulo);
        this.entidade = entidade;
        this.idAluno = -1; //não tem aluno atribuido
    }


    public Estagio(String id, String ramoDestino, String titulo, String entidade, long idAluno) {
        super(id, ramoDestino, titulo);
        this.entidade = entidade;
        this.idAluno = idAluno;
    }
}
