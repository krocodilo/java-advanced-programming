package pt.isec.pa.apoio_poe.ui.gui.phaseFive;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
import pt.isec.pa.apoio_poe.ui.gui.phaseThree.EditarAtribuicoes;

public class PhaseFiveUI extends PhaseMenuTemplate {

    RadioButton btnAlunosComPropostas;
    RadioButton btnSemPropostaComCandidatura;
    RadioButton btnPropostasDisponiveis;
    RadioButton btnPropostasAtribuidas;
    ListView<Object> lv;

    public PhaseFiveUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title = new Text("Fase 5");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        btnAlunosComPropostas = new RadioButton("Alunos com Propostas Atribuidas");
        btnSemPropostaComCandidatura = new RadioButton("Alunos sem Proposta e com Opcoes de Candidatura");
        btnPropostasDisponiveis = new RadioButton("Propostas Disponiveis");
        btnPropostasAtribuidas = new RadioButton("Propostas Atribuidas");

        ToggleGroup grp = new ToggleGroup();
        btnAlunosComPropostas.setToggleGroup(grp);
        btnSemPropostaComCandidatura.setToggleGroup(grp);
        btnPropostasDisponiveis.setToggleGroup(grp);
        btnPropostasAtribuidas.setToggleGroup(grp);

        VBox vbox = new VBox(btnAlunosComPropostas, btnSemPropostaComCandidatura,
                btnPropostasDisponiveis, btnPropostasAtribuidas);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(40, 20, 20, 20));
        vbox.setAlignment(Pos.CENTER_LEFT);
        this.setLeft(vbox);

        lv = new ListView<>();
        VBox vboxl = new VBox(lv);
        vboxl.setSpacing(10);
        vboxl.setPadding(new Insets(10));
        vboxl.setAlignment(Pos.CENTER);
        this.setCenter(vboxl);

        HBox hbox = new HBox(btnSaveData, btnLoadData);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10));
        this.setBottom(hbox);
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> update());
        btnAlunosComPropostas.setOnAction(actionEvent -> {
            lv.getItems().clear();
            lv.getItems().addAll( model.getAlunosComPropostaAtribuida() );
        });
        btnSemPropostaComCandidatura.setOnAction(actionEvent -> {
            lv.getItems().clear();
            lv.getItems().addAll( model.getAlunosSemPropostasComCandidaturas() );
        });
        btnPropostasDisponiveis.setOnAction(actionEvent -> {
            lv.getItems().clear();
            lv.getItems().addAll( model.getPropostasDisponiveis() );
        });
        btnPropostasAtribuidas.setOnAction(actionEvent -> {
            lv.getItems().clear();
            lv.getItems().addAll( model.getPropostasAtribuidas() );
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_FIVE);
    }

}