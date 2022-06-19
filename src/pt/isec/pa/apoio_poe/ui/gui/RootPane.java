package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.ui.gui.PhaseThree.phaseThreeGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseOne.gestaoAlunosGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseOne.phaseOneGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseTwo.phaseTwoGUI;
import pt.isec.pa.apoio_poe.ui.text.PhaseThreeUI;

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
                new phaseOneGUI(model), new gestaoAlunosGUI(model), new phaseTwoGUI(model), new phaseThreeGUI(model)
        );
        this.setCenter(stackPane);
    }

    private void registerHandlers() { }

    private void update() { }
}
