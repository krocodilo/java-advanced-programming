package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.List;

public class Projeto extends Proposta {
    // T2

    private String emailDocente;
    private List<String> ramosDestino;
    private long idAluno;

    public Projeto(String id, List<String> ramosDestino, String titulo, String emailDocente) {
        super(id, titulo);
        this.emailDocente = emailDocente;
        this.ramosDestino = ramosDestino;
        this.idAluno = -1; // não tem aluno atribuido
    }

    public Projeto(String id, List<String> ramosDestino, String titulo, String emailDocente, long idAluno) {
        super(id,  titulo);
        this.emailDocente = emailDocente;
        this.ramosDestino = ramosDestino;
        this.idAluno = idAluno;
    }

    @Override
    public TipoProposta getType() {
        return TipoProposta.PROJETO;
    }

    public static Projeto parseProjetoCSV(List<String> values) throws Exception {
        if( values.size() == 5 )
            return new Projeto(
                    values.get(1),
                    List.of( values.get(2).split("\\|") ),
                    values.get(3),
                    values.get(4)
            );
        else if( values.size() == 6 )
            try{
                return new Projeto(
                        values.get(1),
                        List.of( values.get(2).split("\\|") ),
                        values.get(3),
                        values.get(4),
                        Long.parseLong( values.get(5) )
                );
            } catch (NumberFormatException e) {
                throw new Exception("Unable to read number from the last value");
            }

        else
            throw new Exception("Must have 5 or 6 values!");
    }
}
