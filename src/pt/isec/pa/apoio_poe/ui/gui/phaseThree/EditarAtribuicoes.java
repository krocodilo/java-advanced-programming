package pt.isec.pa.apoio_poe.ui.gui.phaseThree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;

public class EditarAtribuicoes extends BorderPane {
    ModelManager model;
    Button btnAdicionar;
    Button btnDelete;
    ChoiceBox<Aluno> cbAluno;
    ChoiceBox<Proposta> cbProposta;
    ListView<Aluno> lv;

    public EditarAtribuicoes(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        lv = new ListView<>();

        btnAdicionar = new Button("Adicionar");
        btnDelete = new Button("Eliminar");
        HBox hboxEliminar = new HBox(btnDelete);
        hboxEliminar.setAlignment(Pos.CENTER_RIGHT);
        hboxEliminar.setPadding(new Insets(20));

        Label lbAluno = new Label("Aluno");
        cbAluno = new ChoiceBox<>();
        Label lbProposta = new Label("Proposta");
        cbProposta = new ChoiceBox<>();

        VBox vbAluno = new VBox(lbAluno, cbAluno);
        vbAluno.setSpacing(10);
        vbAluno.setAlignment(Pos.CENTER);
        VBox vbProposta = new VBox(lbProposta, cbProposta);
        vbProposta.setSpacing(10);
        vbProposta.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(vbAluno, vbProposta);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(lv, hboxEliminar, hbox, btnAdicionar);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> update() );
        //Probably not needed
        btnAdicionar.setOnAction( actionEvent -> adicionarAtribuicao() );
        btnDelete.setOnAction(actionEvent -> {
            if( lv.getSelectionModel().getSelectedItem() != null )
                model.RemoverAtribuicao( lv.getSelectionModel().getSelectedItem() );
        });
    }

    private void update() {

        lv.getItems().clear();
        lv.getItems().addAll( model.getAlunosComPropostaAtribuida() );

        cbAluno.getItems().clear();
        cbAluno.getItems().addAll( model.getAlunosSemPropostaAtribuida() );
        cbProposta.getItems().clear();
        cbProposta.getItems().addAll( model.getPropostasDisponiveis() );
    }

    private void adicionarAtribuicao() {
        if(cbAluno.getSelectionModel().getSelectedItem() != null &&
                cbProposta.getSelectionModel().getSelectedItem() != null)
        {
            model.AtribuicaoManual(
                    cbAluno.getSelectionModel().getSelectedItem(),
                    cbProposta.getSelectionModel().getSelectedItem()
            );
        }
        // TODO say something if error
    }
}