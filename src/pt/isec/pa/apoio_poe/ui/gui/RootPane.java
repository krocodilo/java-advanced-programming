package pt.isec.pa.apoio_poe.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.apoio_poe.ui.gui.phaseOne.gestaoAlunosGUI;
import pt.isec.pa.apoio_poe.ui.gui.phaseOne.phaseOneGUI;

public class RootPane extends BorderPane {
    ModelManager model;

    public RootPane(ModelManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.setStyle("-fx-background-color: #333333;");
        StackPane stackPane = new StackPane(
                new phaseOneGUI(model), new gestaoAlunosGUI(model)
        );
        this.setCenter(stackPane);
    }

    private void registerHandlers() { }

    private void update() { }
}
