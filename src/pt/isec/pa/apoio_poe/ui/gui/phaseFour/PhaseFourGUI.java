package pt.isec.pa.apoio_poe.ui.gui.phaseFour;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import pt.isec.pa.apoio_poe.ui.gui.phaseThree.ConsultaListasAlunos;
import pt.isec.pa.apoio_poe.ui.gui.phaseThree.ConsultarListasPropostas;

public class PhaseFourGUI extends PhaseMenuTemplate {

    Button btnGestaoOrientadores;
    Button btnAlunosPropostaOrientador;
    Button btnAlunosPropostaSemOrientador;
    Button btnEstatisticasOrientadores;
    ListView<Object> list;
    boolean atribuicaoAutomaticaAconteceu = false;

    public PhaseFourGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title = new Text("Fase 4");
        title.setFont( Font.font("Arial", FontWeight.BOLD, 35) );

        btnGestaoOrientadores = new Button("Gestao Orientadores");
        btnAlunosPropostaOrientador = new Button("Alunos c/ Proposta e Orientador");
        btnAlunosPropostaSemOrientador = new Button("Alunos c/ Proposta e s/ Orientador");
        btnEstatisticasOrientadores = new Button("Estatisticas Orientadores");

        VBox vbox = new VBox(title, btnGestaoOrientadores, btnAlunosPropostaOrientador,
                btnAlunosPropostaSemOrientador, btnEstatisticasOrientadores, btnSaveData, btnLoadData, txtWarning);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        this.setLeft( vbox );

        list = new ListView<>();
        VBox vboxl = new VBox(list);
        vboxl.setSpacing(10);
        vboxl.setPadding(new Insets(10));
        vboxl.setAlignment(Pos.CENTER);
        this.setCenter( vboxl );

        HBox hbox = new HBox(btnCloseState, btnPrevious, btnNext);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));
        this.setBottom( hbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );

        btnGestaoOrientadores.setOnAction( actionEvent -> model.goToState(State.GESTAO_ORIENTADORES) );

        btnAlunosPropostaOrientador.setOnAction( actionEvent -> {
            list.getItems().clear();
            list.getItems().addAll(model.getAlunosComPropostaComOrientador());
        });

        btnAlunosPropostaSemOrientador.setOnAction( actionEvent -> {
            list.getItems().clear();
            list.getItems().addAll(model.getAlunosComPropostaSemOrientador());
        } );

        btnEstatisticasOrientadores.setOnAction( actionEvent -> {
            list.getItems().clear();
            list.getItems().addAll(model.getEstatisticasOrientadores());
        } );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_FOUR);

        if(! (model.getState() == State.PHASE_FOUR) )
            atribuicaoAutomaticaAconteceu = false;
        else if(!atribuicaoAutomaticaAconteceu) {
            model.atribuicaoOrientadoresProponentes();

            txtWarning.setFill(Color.GREEN);
            txtWarning.setText("-> Occoreu atribuicao automatica de docentes " +
                    "proponentes de projetos comoo orientador dos mesmos.");
            txtWarning.setVisible(true);
            atribuicaoAutomaticaAconteceu = true;
        }
    }
}
