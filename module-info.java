module covid_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens prova03.simulado01.view to javafx.fxml;
    exports prova03.simulado01.view;

    exports prova03.simulado01.model;
    opens prova03.simulado01.model to javafx.fxml;

    exports prova03.simulado01.persistence;
    opens prova03.simulado01.persistence to javafx.fxml;

    exports prova03.simulado01.controller;
    opens prova03.simulado01.controller to javafx.fxml;
}