package pt.isec.pa.apoio_poe.ui.gui.phaseOne;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.File;

public class GestaoDocentesGUI extends BorderPane {
    ModelManager model;
    Button btnEdit;
    Button btnDelete;
    Button btnLoadFile;
    Button btnPrevious;
    Text txtDocenteCount;
    Text txtWarning;
    boolean warningWasShown = false;
    ListView<Docente> list;

    public GestaoDocentesGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        list = new ListView<>();
        list.getItems().addAll( model.getDocentes() );

        txtDocenteCount = new Text("Numero de Docentes: " + model.getDocentes().size());
        txtDocenteCount.setVisible(true);
        btnEdit = new Button("Editar");
        btnDelete = new Button("Eliminar");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white");
        txtWarning = new Text(); txtWarning.setVisible(false);
        txtWarning.setFill(Color.RED);
        HBox below = new HBox(txtDocenteCount, btnEdit, btnDelete);
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
                    model.removeDocente( list.getSelectionModel().getSelectedItem() );
                    update();
                } else
                    throw new Exception("Nenhum doccente foi selecionado!");
            } catch (Exception e) {
                txtWarning.setText(e.getMessage());
                update();
            }
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.GESTAO_DOCENTES);
        list.getItems().clear();
        list.getItems().addAll( model.getDocentes() );
        txtDocenteCount.setText("Numero de Docentes: " + model.getDocentes().size());
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
        fc.setTitle("Escolha o ficheiro dos docentes");
        fc.setInitialDirectory( new File(System.getProperty("user.dir")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));

        File f = fc.showOpenDialog( this.getScene().getWindow() );
        if( f == null )
            return;     //Caso nao seja selecionado nenhum ficheiro
        try {
            String ret = model.addDocentes(
                    FileUtils.readDocentesFromCSV( f.getAbsolutePath() )
            );
            if (!ret.isEmpty())
                throw new Exception(ret);
        } catch (Exception e) {
            txtWarning.setText(e.getMessage());
            update();
        }
    }
}