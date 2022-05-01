package pt.isec.pa.apoio_poe.model.fsm.states.phaseFour;

import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class AtribuicaoOrientadores extends StateAdapter {

    public AtribuicaoOrientadores(Context context, DataCapsule data) {
        super(context, data);
    }
}
