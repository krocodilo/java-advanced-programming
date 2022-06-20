package pt.isec.pa.apoio_poe.ui.gui.phaseThree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.common.PhaseMenuTemplate;

public class PhaseThreeGUI extends PhaseMenuTemplate {

    Button btnEditarAtribuicoes;
    Button btnConsultaAlunos;
    Button btnConsultaPropostas;
    boolean atribuicaoAutomaticaAconteceu = false;

    public PhaseThreeGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title = new Text("Fase 3");
        title.setFont( Font.font("Arial", FontWeight.BOLD, 35) );

        btnEditarAtribuicoes = new Button("Editar Atribuição Propostas");
        btnConsultaAlunos = new Button("Consultar Alunos");
        btnConsultaPropostas = new Button("Consultar Propostas");

        VBox vbox = new VBox(title, btnEditarAtribuicoes, btnConsultaAlunos,
                btnConsultaPropostas, btnSaveData, btnLoadData, txtWarning);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );

        HBox hbox = new HBox(btnCloseState, btnPrevious, btnNext);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));
        this.setBottom( hbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );

        btnEditarAtribuicoes.setOnAction(actionEvent -> openWindowAdicionarAtribuicao() );

        btnConsultaAlunos.setOnAction( actionEvent -> openWindowConsultarAlunos() );

        btnConsultaPropostas.setOnAction( actionEvent -> openWindowConsultarPropostas() );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_THREE);

        if(! (model.getState() == State.PHASE_THREE) )
            atribuicaoAutomaticaAconteceu = false;
        else if(!atribuicaoAutomaticaAconteceu) {
            model.atribuicaoAutomaticaAutoPropostas();
            String extraInfo;
            if (model.phaseTwoLocked()) {
                extraInfo = "\n-> Atribuicao automatica de propostas.";
                model.atribuicaoAutomaticaPropostas();
            } else
                extraInfo = "\n-> Nao se realizou a atribuicao automatica de propostas," +
                        " pois fase dois nao esta fechada.";

            txtWarning.setFill(Color.GREEN);
            txtWarning.setText("-> Occoreu atribuicao automatica de autopropostas ou propostas de " +
                    "docentes com aluno associado." + extraInfo);
            txtWarning.setVisible(true);
            atribuicaoAutomaticaAconteceu = true;
        }
    }

    private void openWindowAdicionarAtribuicao() {
        Stage window = new Stage();
        window.initModality( Modality.WINDOW_MODAL );
        window.initOwner( this.getScene().getWindow() );
        window.setTitle("Editar Atribuicoes de Propostas");
        window.setScene(
                new Scene( new EditarAtribuicoes(model), 950, 550 )
        );
        window.show();
    }

    private void openWindowConsultarAlunos(){
        Stage window = new Stage();
        window.initModality( Modality.WINDOW_MODAL );
        window.initOwner( this.getScene().getWindow() );
        window.setTitle("Consultar Listas de Alunos");
        window.setScene(
                new Scene( new ConsultaListasAlunos(model), 950, 550 )
        );
        window.show();
    }

    private void openWindowConsultarPropostas(){
        Stage window = new Stage();
        window.initModality( Modality.WINDOW_MODAL );
        window.initOwner( this.getScene().getWindow() );
        window.setTitle("Consultar Listas de Propostas");
        window.setScene(
                new Scene( new ConsultarListasPropostas(model), 950, 550 )
        );
        window.show();
    }
}
