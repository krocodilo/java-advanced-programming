package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.List;

public class PoE_autoproposto extends Proposta {
    // T3

    private long idAluno;

    public PoE_autoproposto(String id, String titulo, long idAluno) {
        super(id, titulo);
        this.idAluno = idAluno;
    }

    public static PoE_autoproposto parseProjetoCSV(List<String> values) throws Exception {
        if( values.size() != 5 )
            throw new Exception("Must have 4 values!");

        try {
            return new PoE_autoproposto(
                    values.get(1),
                    values.get(2),
                    Long.parseLong(values.get(3))
            );
        } catch (NumberFormatException e){
            throw new Exception("Unable to read number from last value");
        }
    }
}
