package pt.isec.pa.apoio_poe.ui.gui;

import pt.isec.pa.apoio_poe.model.fsm.Context;
import pt.isec.pa.apoio_poe.model.fsm.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager {
    public static final String PROP_STATE = "state";
    public static final String PROP_DATA  = "data";

    Context context;
    PropertyChangeSupport pcs;

    public ModelManager() {
        this.context = new Context();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public State getState() {
        return context.getState();
    }

    public void goToState(State s){
        context.goToState( s );
        pcs.firePropertyChange(PROP_STATE,null,context.getState());
    }

    public void next() {
        context.nextState();
        pcs.firePropertyChange(PROP_STATE,null,context.getState());
    }

    public void previous() {
        context.previousState();
        pcs.firePropertyChange(PROP_STATE,null,context.getState());
    }

    public void changeMessage(String msg) {
//        context.changeMessage(msg);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public void changeNumber(int nr) {
//        context.changeNumber(nr);
        pcs.firePropertyChange(PROP_DATA,null,null);
    }

    public int getNumber() {
        return 0;//context.getNumber();
    }

    public String getMessage() {
        return "";//context.getMessage();
    }

    public void lockCurrentState() throws Exception {
        context.lockCurrentState();
    }
}
