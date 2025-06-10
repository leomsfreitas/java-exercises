module covid_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens prova03.simulado.view to javafx.fxml;
    exports prova03.simulado.view;

    exports prova03.simulado.model;
    opens prova03.simulado.model to javafx.fxml;

    exports prova03.simulado.persistence;
    opens prova03.simulado.persistence to javafx.fxml;

    exports prova03.simulado.controller;
    opens prova03.simulado.controller to javafx.fxml;
}