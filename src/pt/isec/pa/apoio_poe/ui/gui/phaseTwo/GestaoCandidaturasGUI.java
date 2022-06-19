package pt.isec.pa.apoio_poe.ui.gui.phaseTwo;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidaturas;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.File;

public class GestaoCandidaturasGUI extends BorderPane {

    ModelManager model;
    Button btnEdit;
    Button btnDelete;
    Button btnLoadFile;
    Button btnPrevious;
    Text txtCandidaturasCount;
    Text txtWarning;
    boolean warningWasShown = false;
    ListView<Candidaturas> list;

    public GestaoCandidaturasGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        list = new ListView<>();
        list.getItems().addAll( model.getCandidaturas());

        txtCandidaturasCount = new Text("Numero de Candidaturas: " + model.getCandidaturas().size());
        txtCandidaturasCount.setVisible(true);
        btnEdit = new Button("Editar");
        btnDelete = new Button("Eliminar");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white");
        txtWarning = new Text(); txtWarning.setVisible(false);
        txtWarning.setFill(Color.RED);
        HBox below = new HBox(txtCandidaturasCount, btnEdit, btnDelete);
        below.setSpacing(10);
        below.setAlignment(Pos.CENTER);

        btnLoadFile = new Button("Carregar Ficheiro");
        btnPrevious = new Button("Voltar");
        VBox vbox = new VBox(list, below, txtWarning, btnLoadFile, btnPrevious);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> update());
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> update() );
        btnLoadFile.setOnAction(actionEvent -> importFromFile() );
        btnPrevious.setOnAction(actionEvent -> model.previous() );

        btnDelete.setOnAction(actionEvent -> {
            try {
                if( list.getSelectionModel().getSelectedItem() != null ){
                    model.removeCandidatura( list.getSelectionModel().getSelectedItem() );
                    update();
                } else
                    throw new Exception("Nenhuma candidatura foi selecionada!");
            } catch (Exception e) {
                txtWarning.setText(e.getMessage());
                update();
            }
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.GESTAO_CANDIDATURAS);
        list.getItems().clear();
        list.getItems().addAll( model.getCandidaturas());
        txtCandidaturasCount.setText("Numero de Candidaturas: " + model.getCandidaturas().size());
        if( !txtWarning.isVisible() && !txtWarning.getText().isBlank()){
            txtWarning.setVisible(true);
            warningWasShown = true;
        }
        else if(txtWarning.isVisible() && warningWasShown ){
            txtWarning.setText("");
            txtWarning.setVisible(false);
        }
    }

    private void importFromFile(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Escolha o ficheiro das candidaturas");
        fc.setInitialDirectory( new File(System.getProperty("user.dir")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));

        File f = fc.showOpenDialog( this.getScene().getWindow() );
        if( f == null )
            return;     //Caso nao seja selecionado nenhum ficheiro
        try {
            String ret = model.addCandidaturas(
                    FileUtils.readCandidaturasFromCSV( f.getAbsolutePath() )
            );
            if (!ret.isEmpty())
                throw new Exception(ret);
        } catch (Exception e) {
            txtWarning.setText(e.getMessage());
            update();
        }
    }
}
