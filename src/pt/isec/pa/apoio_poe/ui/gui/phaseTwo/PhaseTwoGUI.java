package pt.isec.pa.apoio_poe.ui.gui.phaseTwo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.common.PhaseMenuTemplate;

public class PhaseTwoGUI extends PhaseMenuTemplate {

    Button btnGestaoCandidaturas;
    Button btnConsultaAlunos;
    Button btnConsultaPropostas;
    ListView<Object> list;

    ChoiceBox<String> choiceBoxAlunos;
    ChoiceBox<String> choiceBoxPropostas;

    public PhaseTwoGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        //TODO -> add this title text
        Text title = new Text("Fase 2");
        title.setFont( Font.font("Arial", FontWeight.BOLD, 35) );

        btnGestaoCandidaturas = new Button("Gestao Candidaturas");
        btnConsultaAlunos = new Button("Consulta Alunos");
        btnConsultaPropostas = new Button("Consulta Propostas");

        choiceBoxAlunos = new ChoiceBox<>();
        choiceBoxPropostas = new ChoiceBox<>();

        choiceBoxAlunos.getItems().addAll("Alunos Autoproposta","Alunos c/ candidatura","Alunos s/ candidatura");
        choiceBoxPropostas.getItems().addAll("Autopropostas Alunos","Propostas Docentes","Propostas c/ candidatura", "Propostas s/ candidatura");

        VBox vbox1 = new VBox(title, btnGestaoCandidaturas);
        vbox1.setSpacing(50);
        vbox1.setPadding(new Insets(20));
        vbox1.setAlignment(Pos.CENTER);

        VBox vbox2 = new VBox(choiceBoxAlunos,btnConsultaAlunos,choiceBoxPropostas,btnConsultaPropostas);
        vbox2.setSpacing(10);
        vbox2.setPadding(new Insets(40,20,20,20));
        vbox2.setAlignment(Pos.CENTER);

        VBox vbox3 = new VBox(btnSaveData, btnLoadData);
        vbox3.setSpacing(10);
        vbox3.setPadding(new Insets(40,20,20,20));
        vbox3.setAlignment(Pos.CENTER);

        VBox form=new VBox();
        form.getChildren().add(vbox1);
        form.getChildren().add(vbox2);
        form.getChildren().add(vbox3);
        this.setLeft(form);

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
        btnGestaoCandidaturas.setOnAction( actionEvent -> model.goToState(State.GESTAO_CANDIDATURAS) );

        btnConsultaAlunos.setOnAction( actionEvent -> {
            String choice = choiceBoxAlunos.getValue();
                    if(choice.equals("Alunos Autoproposta")){
                        list.getItems().clear();
                        list.getItems().addAll(model.getAlunosComAutoproposta());
                    }
            else if(choice.equals("Alunos c/ candidatura")){
                list.getItems().clear();
                list.getItems().addAll(model.getAlunosComCandidatura());
            }
            else if(choice.equals("Alunos s/ candidatura")){
                list.getItems().clear();
                list.getItems().addAll(model.getAlunosSemCandidatura());
            }
        });


        btnConsultaPropostas.setOnAction( actionEvent -> {
            String choice = choiceBoxPropostas.getValue();
            if(choice.equals("Autopropostas Alunos")){
                list.getItems().clear();
                list.getItems().addAll(model.getAutopropostasAlunos());
            }
            else if(choice.equals("Propostas Docentes")){
                list.getItems().clear();
                list.getItems().addAll(model.getPropostasDocentes());
            }
            else if(choice.equals("Propostas c/ candidatura")){
                list.getItems().clear();
                list.getItems().addAll(model.getPropostasComCandidaturas());
            }
            else if(choice.equals("Propostas s/ candidatura")){
                list.getItems().clear();
                list.getItems().addAll(model.getPropostasSemCandidaturas());
            }
        });
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_TWO);
    }

}
