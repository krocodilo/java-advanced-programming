package pt.isec.pa.apoio_poe.ui.gui.phaseOne;

import com.sun.glass.ui.CommonDialogs;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.File;

import static pt.isec.pa.apoio_poe.ui.utils.IO.prompt;

public class gestaoAlunosGUI extends BorderPane {
    ModelManager model;
    Button btnLoadFile;
    Button btnPrevious;
    Text txtWarning;
    ListView<Aluno> list;

    public gestaoAlunosGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        // ter uma lista e botoes paraa adicionar editar e eliminar
        // atualizar em real time
        txtWarning = new Text();
        txtWarning.setVisible(true);
        txtWarning.setText("Number of Alunos: " + model.getAlunos().size());
        list = new ListView<>();
        list.getItems().addAll( model.getAlunos() );

        btnLoadFile = new Button("Carregar Ficheiro");
        btnPrevious = new Button("Voltar");
        VBox vbox = new VBox(list, btnLoadFile, txtWarning, btnPrevious);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_STATE, evt -> update());
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> update() );
        btnLoadFile.setOnAction(actionEvent -> importFromFile() );
        btnPrevious.setOnAction(actionEvent -> model.previous() );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.GESTAO_ALUNOS);
        list.getItems().addAll( model.getAlunos() );
        txtWarning.setText("Number of Alunos: " + model.getAlunos().size());
    }

    private void importFromFile(){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*csv"));

        File f = fc.showOpenDialog(this.getScene().getWindow());
        try {
            model.addAlunos(
                    FileUtils.readAlunosFromCSV( f.getAbsolutePath() )
            );
        } catch (Exception e) {
            e.printStackTrace();    //TODO need to show in a text box
        }
        System.out.println( model.getAlunos());
        update();
    }
}