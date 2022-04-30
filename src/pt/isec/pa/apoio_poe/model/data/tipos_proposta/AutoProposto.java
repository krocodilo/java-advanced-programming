package pt.isec.pa.apoio_poe.model.data.tipos_proposta;

import pt.isec.pa.apoio_poe.model.data.Proposta;

import java.util.List;

public class AutoProposto extends Proposta {
    // T3

    private long idAluno;

    public AutoProposto(String id, String titulo, long idAluno) {
        super(id, titulo);
        this.idAluno = idAluno;
    }

    @Override
    public long getIdAluno() {
        return idAluno;
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
