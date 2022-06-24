package pt.isec.pa.apoio_poe.ui.gui.common;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;

import java.io.File;

public class PhaseMenuTemplate extends BorderPane {

    protected ModelManager model;
    protected Button btnCloseState = new Button("Fechar Fase");
    protected Button btnPrevious = new Button("<< Fase Anterior");
    protected Button btnNext = new Button("Fase Seguinte >>");
    protected Button btnSaveData = new Button("Gravar Estado Atual");
    protected Button btnLoadData = new Button("Carregar Estado Anterior");
    protected Text txtWarning = new Text("");
    boolean warningWasShown = false;

    public PhaseMenuTemplate(ModelManager model) {
        this.model = model;

        txtWarning.setFill(Color.RED);

        registerBaseHandlers();
    }

    protected void registerBaseHandlers() {
        btnCloseState.setOnAction( actionEvent -> {
            try {
                model.lockCurrentState();
            } catch (Exception e) {
                txtWarning.setText(e.getMessage());
            }
        });
        btnNext.setOnAction(
                actionEvent -> model.next()
        );
        btnPrevious.setOnAction( actionEvent -> model.previous() );
        btnLoadData.setOnAction( actionEvent -> loadData() );
        btnSaveData.setOnAction( actionEvent -> saveData() );
    }

    protected void loadData() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Escolha o ficheiro dos dados a carregar");
        fc.setInitialDirectory( new File(System.getProperty("user.dir")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data File", "*.dat"));

        File f = fc.showOpenDialog( this.getScene().getWindow() );
        if( f == null )
            return;     //Caso nao seja selecionado nenhum ficheiro

        try {
            model.loadStateFromDisk( f.getAbsolutePath() );
        } catch (Exception e) {
            txtWarning.setText(e.getMessage());
        }
    }

    protected void saveData() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Escolha um ficheiro onde gravar os dados");
        fc.setInitialDirectory( new File(System.getProperty("user.dir")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data File", "*.dat"));

        File f = fc.showSaveDialog( this.getScene().getWindow() );
        if( f == null )
            return;     //Caso nao seja selecionado nenhum ficheiro

        try {
            model.saveStateToDisk( f.getAbsolutePath() );
        } catch (Exception e) {
            txtWarning.setText(e.getMessage());
        }
    }

    protected void toggleWarning() {
        if( !txtWarning.isVisible() && !txtWarning.getText().isBlank()){
            txtWarning.setVisible(true);
            warningWasShown = true;
        }
        else if(txtWarning.isVisible() && warningWasShown){
            txtWarning.setText("");
            txtWarning.setVisible(false);
            warningWasShown = false;
        }
    }
}
