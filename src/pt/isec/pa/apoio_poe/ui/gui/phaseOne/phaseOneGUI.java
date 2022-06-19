package pt.isec.pa.apoio_poe.ui.gui.phaseOne;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.apoio_poe.model.fsm.State;
import pt.isec.pa.apoio_poe.ui.gui.ModelManager;
import pt.isec.pa.apoio_poe.ui.gui.common.PhaseMenuTemplate;


public class phaseOneGUI extends PhaseMenuTemplate {
    Button btnGestaoAlunos;
    Button btnGestaoDocentes;
    Button btnGestaoPropostas;
//    Button btnCloseState;
//    Button btnNext;
//    Button btnSaveData;
//    Button btnLoadData;

    public phaseOneGUI(ModelManager model) {
        super(model);
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {

        Text title = new Text("Fase 1");
        title.setFont( Font.font("de", FontWeight.BOLD, 35) );

        btnGestaoAlunos = new Button("Gestao Alunos");
        btnGestaoDocentes = new Button("Gestão Docentes");
        btnGestaoPropostas = new Button("Gestão Propostas");
//        btnCloseState = new Button("Fechar Fase");
//        btnNext = new Button("Fase Seguinte");
//        btnSaveData = new Button("Gravar Estado Atual");
//        btnLoadData = new Button("Carregar Estado Anterior");

        VBox vbox = new VBox(title, btnGestaoAlunos, btnGestaoDocentes, btnGestaoPropostas,
                btnCloseState, btnSaveData, btnLoadData, btnNext, txtWarning);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        this.setCenter( vbox );
    }

    private void registerHandlers() {
        model.addPropertyChangeListener( ModelManager.PROP_STATE, evt -> update() );
        btnGestaoAlunos.setOnAction( actionEvent -> model.goToState(State.GESTAO_ALUNOS) );
        btnGestaoDocentes.setOnAction( actionEvent -> model.goToState(State.GESTAO_DOCENTES) );
        btnGestaoPropostas.setOnAction( actionEvent -> model.goToState(State.GESTAO_PROPOSTAS) );
//        btnCloseState.setOnAction( actionEvent -> {
//            try {
//                model.lockCurrentState();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        btnNext.setOnAction( actionEvent -> model.next() );
////        btnSaveData.setOnAction( actionEvent -> );
    }

    private void update() {
        this.setVisible(model != null && model.getState() == State.PHASE_ONE);
        toggleWarning();
    }


}
