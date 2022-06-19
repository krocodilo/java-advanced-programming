package pt.isec.pa.apoio_poe.ui.gui.phaseOne;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.File;

public class phaseOneGUI extends BorderPane {
    ModelManager model;
    Button btnGestaoAlunos;
    Button btnGestaoDocentes;
    Button btnGestaoPropostas;
    Button btnCloseState;
    Button btnNext;

    public phaseOneGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnGestaoAlunos = new Button("Gestao Alunos");
        btnGestaoDocentes = new Button("Gestão Docentes");
        btnGestaoPropostas = new Button("Gestão Propostas");
        btnCloseState = new Button("Fechar Fase");
        btnNext = new Button("Fase Seguinte");

        VBox vbox = new VBox(btnGestaoAlunos, btnGestaoDocentes, btnGestaoPropostas,
                btnCloseState, btnNext);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );
        btnGestaoAlunos.setOnAction( actionEvent -> model.goToState(State.GESTAO_ALUNOS) );
        btnGestaoDocentes.setOnAction( actionEvent -> model.goToState(State.GESTAO_DOCENTES) );
        btnGestaoPropostas.setOnAction( actionEvent -> model.goToState(State.GESTAO_PROPOSTAS) );
        btnCloseState.setOnAction( actionEvent -> {
            try {
                model.lockCurrentState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnNext.setOnAction( actionEvent -> model.next() );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_ONE);
    }
}
