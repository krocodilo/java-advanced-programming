package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.ui.gui.phaseThree.PhaseThreeGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseOne.GestaoAlunosGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseOne.PhaseOneGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseTwo.GestaoCandidaturasGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseTwo.PhaseTwoGUI;

public class RootPane extends BorderPane {
    ModelManager model;

    public RootPane(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setStyle("-fx-background-color: #FFFFFF;");
        StackPane stackPane = new StackPane(
                new PhaseOneGUI(model), new GestaoAlunosGUI(model), new PhaseTwoGUI(model), new PhaseThreeGUI(model), new GestaoCandidaturasGUI(model)
        );
        this.setCenter(stackPane);
    }

    private void registerHandlers() { }

    private void update() { }
}
