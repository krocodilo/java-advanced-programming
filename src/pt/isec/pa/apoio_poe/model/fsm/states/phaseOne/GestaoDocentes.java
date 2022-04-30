package pt.isec.pa.apoio_poe.model.fsm.states.phaseOne;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.DataCapsule;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.fsm.IState;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class GestaoDocentes extends StateAdapter {

    public GestaoDocentes(DataCapsule data) {
        super(data);
    }

    @Override
    public void addDocente(Docente newDocente) {
        //TODO : restrições - no construtor ?
        if( data.getDocentes().contains( newDocente ) )
            return;
        data.getDocentes().add( newDocente );
    }

    @Override
    public void editDocente(Docente newVersionDocente) {
        //TODO : meta2
    }

    @Override
    public void removeDocente(Docente toRemove) {
        //TODO : meta2
        data.getDocentes().remove( toRemove );
    }

    @Override
    public IState getPreviousState() {
        return new PhaseOne(data);
    }

    @Override
    public State getState() {
        return State.GESTAO_DOCENTES;
    }

}
