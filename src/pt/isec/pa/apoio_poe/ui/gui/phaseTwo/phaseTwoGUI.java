package pt.isec.pa.apoio_poe.ui.gui.phaseTwo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;

public class phaseTwoGUI extends BorderPane {

    ModelManager model;
    Button btnGestaoCandidaturas;

    Button btnConsultaAlunos;

    Button btnConsultaPropostas;
    Button btnCloseState;
    Button btnNext;
    Button btnPrevious;
    ListView<Object> list;

    public phaseTwoGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnGestaoCandidaturas = new Button("Gestao Candidaturas");
        btnConsultaAlunos = new Button("Consulta Alunos");
        btnConsultaPropostas = new Button("Consulta Propostas");
        btnCloseState = new Button("Fechar Fase");
        btnNext = new Button("Fase Seguinte");
        btnPrevious = new Button("Fase Anterior");

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

        HBox hbox = new HBox(btnCloseState, btnNext, btnPrevious);
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

        btnCloseState.setOnAction( actionEvent -> {
            try {
                model.lockCurrentState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnNext.setOnAction( actionEvent -> model.next() );
        btnPrevious.setOnAction( actionEvent -> model.previous() );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_TWO);
    }

}
