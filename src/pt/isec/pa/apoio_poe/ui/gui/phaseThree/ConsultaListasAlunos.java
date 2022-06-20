package pt.isec.pa.apoio_poe.ui.gui.phaseThree;

import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;

public class ConsultaListasAlunos extends BorderPane {
    ModelManager model;
    ListView<Aluno> alunosComAutoproposta;
    ListView<Aluno> alunosComCandidatura;
    ListView<Aluno> alunosComPropostaAtribuida;
    ListView<Aluno> alunosSemPropostaAtribuida;

    public ConsultaListasAlunos(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title1 = new Text("Alunos Com Autoproposta:");
        title1.setFont( Font.font("Arial", FontWeight.BOLD, 15) );
        alunosComAutoproposta = new ListView<>();
        alunosComAutoproposta.getItems().addAll( model.getAlunosComAutoproposta() );

        Text title2 = new Text("Alunos Com Candidatura Registada:");
        title2.setFont( Font.font("Arial", FontWeight.BOLD, 15) );
        alunosComCandidatura = new ListView<>();
        alunosComCandidatura.getItems().addAll( model.getAlunosComCandidatura() );

        Text title3 = new Text("Alunos Com Proposta Atribuida:");
        title3.setFont( Font.font("Arial", FontWeight.BOLD, 15) );
        alunosComPropostaAtribuida = new ListView<>();
        alunosComPropostaAtribuida.getItems().addAll( model.getAlunosComPropostaAtribuida() );

        Text title4 = new Text("Alunos Sem Proposta Atribuida:");
        title4.setFont( Font.font("Arial", FontWeight.BOLD, 15) );
        alunosSemPropostaAtribuida = new ListView<>();
        alunosSemPropostaAtribuida.getItems().addAll( model.getAlunosSemPropostaAtribuida() );

        VBox vbox = new VBox(
                title1, alunosComAutoproposta,
                title2, alunosComCandidatura,
                title3, alunosComPropostaAtribuida,
                title4, alunosSemPropostaAtribuida
        );
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> update() );
        //Probably not needed
    }

    private void update() {
        alunosComAutoproposta.getItems().clear();
        alunosComCandidatura.getItems().clear();
        alunosComPropostaAtribuida.getItems().clear();
        alunosSemPropostaAtribuida.getItems().clear();

        alunosComAutoproposta.getItems().addAll( model.getAlunosComAutoproposta() );
        alunosComCandidatura.getItems().addAll( model.getAlunosComCandidatura() );
        alunosComPropostaAtribuida.getItems().addAll( model.getAlunosComPropostaAtribuida() );
        alunosSemPropostaAtribuida.getItems().addAll( model.getAlunosSemPropostaAtribuida() );
    }
}