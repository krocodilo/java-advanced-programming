package pt.isec.pa.apoio_poe.ui.gui.phaseThree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.common.PhaseMenuTemplate;

public class PhaseThreeGUI extends PhaseMenuTemplate {

    Button btnAtribuicaoPropostas;
    Button btnRemocaoAtribuicoes;
    Button btnConsultaAlunos;
    Button btnConsultaPropostas;
    ListView<Object> list;

    public PhaseThreeGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title = new Text("Fase 3");
        title.setFont( Font.font("Arial", FontWeight.BOLD, 35) );

        btnAtribuicaoPropostas = new Button("Atribuição Propostas");
        btnRemocaoAtribuicoes = new Button("Remoção Atribuições");
        btnConsultaAlunos = new Button("Consultar Alunos");
        btnConsultaPropostas = new Button("Consultar Propostas");

        VBox vbox = new VBox(title, btnAtribuicaoPropostas, btnRemocaoAtribuicoes, btnConsultaAlunos, btnConsultaPropostas);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );





        //TODO -> add button btnSaveData and btnLoadData (already set up in parent)





        HBox hbox = new HBox(btnCloseState, btnPrevious, btnNext);
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
            Stage window = new Stage();
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner( this.getScene().getWindow() );
            window.setTitle("Consultar Listas de Alunos");

            Scene scene = new Scene(new ConsultaListasAlunos(model), 1000, 600);
            window.setScene(
                    scene
            );
            window.show();
        });

        btnConsultaPropostas.setOnAction( actionEvent -> {

        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_THREE);
    }
}
