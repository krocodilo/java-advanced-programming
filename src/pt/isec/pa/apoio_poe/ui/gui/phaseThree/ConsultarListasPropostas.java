package pt.isec.pa.apoio_poe.ui.gui.phaseThree;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;

import java.util.HashSet;

public class ConsultarListasPropostas extends BorderPane {
    ModelManager model;
    Button btnConsultar;
    CheckBox cbAutopropostasAlunos;
    CheckBox cbPropostasDocentes;
    CheckBox cbPropostasDisponiveis;
    CheckBox cbPropostasAtribuidas;
    ListView<Proposta> lv;

    public ConsultarListasPropostas(ModelManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        btnConsultar = new Button("Consultar");
        cbAutopropostasAlunos = new CheckBox("Autopropostas de Alunos");
        cbPropostasDocentes = new CheckBox("Propostas de Docentes");
        cbPropostasDisponiveis = new CheckBox("Propostas Disponiveis");
        cbPropostasAtribuidas = new CheckBox("Propostas Atribuidas");

        VBox vbox = new VBox(
                cbAutopropostasAlunos,cbPropostasDocentes,
                cbPropostasDisponiveis,cbPropostasAtribuidas);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(40,20,20,20));
        vbox.setAlignment(Pos.CENTER_LEFT);

        VBox form = new VBox(vbox, btnConsultar);
        form.setAlignment(Pos.CENTER);
        this.setLeft(form);

        lv = new ListView<>();
        VBox vboxlv = new VBox(lv);
        vboxlv.setSpacing(10);
        vboxlv.setPadding(new Insets(10));
        vboxlv.setAlignment(Pos.CENTER);
        this.setCenter( vboxlv );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener(ModelManager.PROP_DATA, evt -> update() );
        //Probably not needed
        btnConsultar.setOnAction( actionEvent -> update());
    }

    private void update() {

        HashSet<Proposta> propostas = new HashSet<>();  //Does not allow duplicates

        if( cbAutopropostasAlunos.isSelected() ){
            propostas.addAll( model.getAutopropostasAlunos() );
        }
        if( cbPropostasDocentes.isSelected() ){
            propostas.addAll( model.getPropostasDocentes() );
        }
        if( cbPropostasDisponiveis.isSelected() ){
            propostas.addAll( model.getPropostasDisponiveis() );
        }
        if( cbPropostasAtribuidas.isSelected() ){
            propostas.addAll( model.getPropostasAtribuidas() );
        }
        if( !cbAutopropostasAlunos.isSelected() && !cbPropostasDocentes.isSelected() &&
                !cbPropostasDisponiveis.isSelected() && !cbPropostasAtribuidas.isSelected() ){
            // If no filter selected
            propostas.addAll( model.getPropostas() );
        }

        lv.getItems().clear();
        lv.getItems().addAll( propostas );
    }
}