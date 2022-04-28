package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

public class Projeto extends Proposta {

    private String emailDocente;
    private long idAluno;

    public Projeto(String id, String ramoDestino, String titulo, String emailDocente) {
        super(id, ramoDestino, titulo);
        this.emailDocente = emailDocente;
        this.idAluno = -1; // não tem aluno atribuido
    }

    public Projeto(String id, String ramoDestino, String titulo, String emailDocente, long idAluno) {
        super(id, ramoDestino, titulo);
        this.emailDocente = emailDocente;
        this.idAluno = idAluno;
    }
}
