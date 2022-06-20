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
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.utils.FileUtils;

import java.io.File;

public class GestaoAlunosGUI extends BorderPane {
    ModelManager model;
    Button btnEdit;
    Button btnDelete;
    Button btnLoadFile;
    Button btnPrevious;
    Text txtAlunosCount;
    Text txtWarning;
    boolean warningWasShown = false;
    ListView<Aluno> list;
//    TableView<List<String>> table;

    public GestaoAlunosGUI(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        list = new ListView<>();
        list.getItems().addAll( model.getAlunos() );

        txtAlunosCount = new Text("Numero de Alunos: " + model.getAlunos().size());
        txtAlunosCount.setVisible(true);
        btnEdit = new Button("Editar");
        btnDelete = new Button("Eliminar");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white");
        txtWarning = new Text(); txtWarning.setVisible(false);
        txtWarning.setFill(Color.RED);
        HBox below = new HBox(txtAlunosCount, btnEdit, btnDelete);
        below.setSpacing(10);
        below.setAlignment(Pos.CENTER);

//        table = new TableView<>();
//        table.getColumns().addAll(
//                new TableColumn("ID"),
//                new TableColumn("Nome"),
//                new TableColumn("E-mail"),
//                new TableColumn("Curso"),
//                new TableColumn("Ramo"),
//                new TableColumn("????"),
//                new TableColumn("DEW?D")
//        );
//        buildTable();

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
                    model.removeAluno( list.getSelectionModel().getSelectedItem() );
                } else
                    throw new Exception("Nenhum aluno foi selecionado!");
            } catch (Exception e) {
                txtWarning.setText(e.getMessage());
            }
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.GESTAO_ALUNOS);
        list.getItems().clear();
        list.getItems().addAll( model.getAlunos() );
        txtAlunosCount.setText("Numero de Alunos: " + model.getAlunos().size());
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
        fc.setTitle("Escolha o ficheiro dos alunos");
        fc.setInitialDirectory( new File(System.getProperty("user.dir")) );
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV File", "*.csv"));

        File f = fc.showOpenDialog( this.getScene().getWindow() );
        if( f == null )
            return;     //Caso nao seja selecionado nenhum ficheiro
        try {
            String ret = model.addAlunos(
                    FileUtils.readAlunosFromCSV( f.getAbsolutePath() )
            );
            if (!ret.isEmpty())
                throw new Exception(ret);
        } catch (Exception e) {
            txtWarning.setText(e.getMessage());
            update();
        }
    }

//    private void buildTable(){
//
//        table.getItems().clear();
//
////        ObservableList<List<String>> lst = FXCollections.observableArrayList();
//        for(Aluno a : model.getAlunos()){
////            lst.add( List.of(a.toString().split(" ")) );
//            table.getItems().add( List.of(a.toString().split(" ")) );
//        }
//
////        table.setItems(lst);
//    }
}