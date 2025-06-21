package prova03.simulado01.controller;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.model.State;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BulletinManagementController {

    @FXML private TableView<Bulletin> tableView;
    @FXML private TableColumn<Bulletin, String> cCity;
    @FXML private TableColumn<Bulletin, LocalDate> cDate;
    @FXML private TableColumn<Bulletin, String> cState;
    @FXML private TableColumn<Bulletin, Integer> cId;
    @FXML private TableColumn<Bulletin, Integer> cInfected;
    @FXML private TableColumn<Bulletin, Double> cIcuRatio;
    @FXML private TableColumn<Bulletin, Integer> cDeaths;

    @FXML private ComboBox<String> cbState;
    @FXML private DatePicker dpEnd;
    @FXML private DatePicker dpBegin;
    @FXML private TextField txtCity;

    @FXML private Label lbAverageIcu;
    @FXML private Label lbTotalDeaths;
    @FXML private Label lbTotalInfected;

    private List<Bulletin> databaseData;
    private ObservableList<Bulletin> tableData;

    @FXML
    private void initialize() {
        loadStates();
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndUpdateTable();
    }

    private void loadStates() {
        final var stateNames = Arrays.stream(State.values())
                .map(State::toString)
                .collect(Collectors.toList());
        cbState.setItems(FXCollections.observableArrayList(stateNames));
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        cState.setCellValueFactory(new PropertyValueFactory<>("state"));
        cInfected.setCellValueFactory(new PropertyValueFactory<>("infected"));
        cDeaths.setCellValueFactory(new PropertyValueFactory<>("deaths"));
        cIcuRatio.setCellValueFactory(new PropertyValueFactory<>("IcuRatio"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadDataAndUpdateTable() {

    }

    private void updateTable(List<Bulletin> data){
        tableData.clear();
        tableData.addAll(data);
    }

    @FXML
    public void registerBulletin() throws IOException {
    }

    @FXML
    public void editBulletin() throws IOException {
    }

    @FXML
    public void removeBulletin() {

    }

    @FXML
    public void filter() {

    }

    private void updateStatistics(List<Bulletin> data) {
    }
}
