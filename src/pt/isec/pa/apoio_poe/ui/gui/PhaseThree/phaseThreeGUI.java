package pt.isec.pa.apoio_poe.ui.gui.PhaseThree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;

public class phaseThreeGUI extends BorderPane {
    ModelManager model;
    Button btnAtribuicaoPropostas;
    Button btnRemocaoAtribuicoes;
    Button btnConsultaAlunos;
    Button btnConsultaPropostas;
    Button btnCloseState;
    Button btnNext;
    Button btnPrevious;
    ListView<Object> list;

    public phaseThreeGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnAtribuicaoPropostas = new Button("Atribuição Propostas");
        btnRemocaoAtribuicoes = new Button("Remoção Atribuições");
        btnConsultaAlunos = new Button("Consultar Alunos");
        btnConsultaPropostas = new Button("Consultar Propostas");
        btnCloseState = new Button("Fechar Fase");
        btnNext = new Button("Fase Seguinte");
        btnPrevious = new Button("Fase Anterior");

        VBox vbox = new VBox(btnAtribuicaoPropostas, btnRemocaoAtribuicoes, btnConsultaAlunos, btnConsultaPropostas);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );

        HBox hbox = new HBox(btnCloseState, btnNext, btnPrevious);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));
        this.setBottom( hbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );

        btnAtribuicaoPropostas.setOnAction( actionEvent -> {

        } );

        btnRemocaoAtribuicoes.setOnAction( actionEvent -> {

        } );

        btnConsultaAlunos.setOnAction( actionEvent -> {

        });

        btnConsultaPropostas.setOnAction( actionEvent -> {

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
        this.setVisible(model != null && model.getState() == State.PHASE_THREE);
    }
}
