package pt.isec.pa.apoio_poe.ui.gui.phaseTwo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.common.PhaseMenuTemplate;

public class phaseTwoGUI extends PhaseMenuTemplate {

    Button btnGestaoCandidaturas;
    Button btnConsultaAlunos;
    Button btnConsultaPropostas;
    ListView<Object> list;

    public phaseTwoGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        //TODO -> add this title text
        Text title = new Text("Fase 1");
        title.setFont( Font.font("de", FontWeight.BOLD, 35) );

        btnGestaoCandidaturas = new Button("Gestao Candidaturas");
        btnConsultaAlunos = new Button("Consulta Alunos");
        btnConsultaPropostas = new Button("Consulta Propostas");

        VBox vbox = new VBox(btnGestaoCandidaturas, btnConsultaAlunos, btnConsultaPropostas);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        this.setLeft( vbox );

        list = new ListView<>();
        VBox vboxl = new VBox(list);
        vboxl.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vboxl.setAlignment(Pos.CENTER);
        this.setCenter( vboxl );

        //TODO -> add button btnSaveData and btnLoadData (already set up in parent)

        HBox hbox = new HBox(btnCloseState, btnPrevious, btnNext);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));
        this.setBottom( hbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );
        btnGestaoCandidaturas.setOnAction( actionEvent -> model.goToState(State.GESTAO_CANDIDATURAS) );

        btnConsultaAlunos.setOnAction( actionEvent -> {
                    list.getItems().clear();
                    list.getItems().addAll(model.getAlunos());
        });


        btnConsultaPropostas.setOnAction( actionEvent -> {
            list.getItems().clear();
            list.getItems().addAll(model.getPropostas());
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_TWO);
    }

}
