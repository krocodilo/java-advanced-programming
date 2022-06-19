package pt.isec.pa.apoio_poe.ui.gui.phaseOne;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.common.PhaseMenuTemplate;


public class phaseOneGUI extends PhaseMenuTemplate {
    Button btnGestaoAlunos;
    Button btnGestaoDocentes;
    Button btnGestaoPropostas;

    public phaseOneGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title = new Text("Fase 1");
        title.setFont( Font.font("Arial", FontWeight.BOLD, 35) );

        btnGestaoAlunos = new Button("Gestao Alunos");
        btnGestaoDocentes = new Button("Gestão Docentes");
        btnGestaoPropostas = new Button("Gestão Propostas");

        VBox vbox = new VBox(title, btnGestaoAlunos, btnGestaoDocentes, btnGestaoPropostas,
                btnSaveData, btnLoadData, txtWarning);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );

        HBox hbox = new HBox(btnCloseState, btnNext);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));
        this.setBottom(hbox);
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );
        btnGestaoAlunos.setOnAction( actionEvent -> model.goToState(State.GESTAO_ALUNOS) );
        btnGestaoDocentes.setOnAction( actionEvent -> model.goToState(State.GESTAO_DOCENTES) );
        btnGestaoPropostas.setOnAction( actionEvent -> model.goToState(State.GESTAO_PROPOSTAS) );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_ONE);
        toggleWarning();
    }


}
