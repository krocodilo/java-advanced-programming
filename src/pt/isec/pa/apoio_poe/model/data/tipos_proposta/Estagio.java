package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.List;

public class Estagio extends Proposta {
    // T1

    private String entidade;
    private List<String> ramosDestino;
    private long idAluno;

    public Estagio(String id, List<String> ramosDestino, String titulo, String entidade) {
        super(id, titulo, -1);   //não tem aluno atribuido
        this.entidade = entidade;
        this.ramosDestino = ramosDestino;
    }


    public Estagio(String id, List<String> ramosDestino, String titulo, String entidade, long idAluno) {
        super(id, titulo, idAluno);
        this.entidade = entidade;
        this.ramosDestino = ramosDestino;
    }

    public TipoProposta getType() {
        return TipoProposta.ESTAGIO;
    }

    public static Estagio parseEstagioCSV(List<String> values) throws Exception {
        if( values.size() == 5 )
            return new Estagio(
                    values.get(1),
                    List.of( values.get(2).split("\\|") ),
                    values.get(3),
                    values.get(4)
            );
        else if( values.size() == 6 )
            try{
                return new Estagio(
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
