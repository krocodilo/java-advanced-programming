package pt.isec.pa.apoio_poe.model.fsm;

public class Context {

    private IState state;





    public State getState(){
        return state.getState();
    }

    private void changeState(IState newState) {

    }
}
