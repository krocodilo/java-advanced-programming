package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.io.Serializable;
import java.util.List;

public class AutoProposto extends Proposta implements Serializable {
    // T3

    private Aluno aluno;

    public AutoProposto(String id, String titulo, long idAluno) {
        super(id, titulo, idAluno);
    }

    @Override
    public TipoProposta getType() {
        return TipoProposta.AUTOPROPOSTO;
    }

    public static AutoProposto parseProjetoCSV(List<String> values) throws Exception {
        if( values.size() != 4 )
            throw new Exception("Must have 4 values!");

        try {
            return new AutoProposto(
                    values.get(1),
                    values.get(2),
                    Long.parseLong(values.get(3))
            );
        } catch (NumberFormatException e){
            throw new Exception("Unable to read number from last value");
        }
    }
}
