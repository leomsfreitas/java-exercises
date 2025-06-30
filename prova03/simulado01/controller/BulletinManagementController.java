package prova03.simulado01.controller;

import prova03.simulado01.model.Bulletin;
import prova03.simulado01.model.State;
import prova03.simulado01.model.StatisticsData;
import prova03.simulado01.persistence.SqliteBulletinDao;
import prova03.simulado01.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    // Services
    private final SqliteBulletinDao bulletinDao = new SqliteBulletinDao();
    private final RegisterBulletinService registerService = new RegisterBulletinService(bulletinDao);
    private final UpdateBulletinService updateService = new UpdateBulletinService(bulletinDao);
    private final RemoveBulletinService removeService = new RemoveBulletinService(bulletinDao);
    private final FilterBulletinService filterService = new FilterBulletinService();
    private final StatisticsService statisticsService = new StatisticsService();

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
        try {
            databaseData = bulletinDao.findAll();
            updateTable(databaseData);
            updateStatistics(databaseData);
        } catch (Exception e) {
            showAlert("Erro", "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void updateTable(List<Bulletin> data){
        tableData.clear();
        tableData.addAll(data);
    }

    @FXML
    public void registerBulletin() throws IOException {
        try {
            Bulletin newBulletin = showBulletinDialog(null);
            if (newBulletin != null) {
                registerService.register(newBulletin);
                loadDataAndUpdateTable();
                showAlert("Sucesso", "Boletim cadastrado com sucesso!");
            }
        } catch (Exception e) {
            showAlert("Erro", "Erro ao registrar boletim: " + e.getMessage());
        }
    }

    @FXML
    public void editBulletin() throws IOException {
        Bulletin selectedBulletin = tableView.getSelectionModel().getSelectedItem();
        if (selectedBulletin == null) {
            showAlert("Aviso", "Selecione um boletim para editar.");
            return;
        }

        try {
            Bulletin editedBulletin = showBulletinDialog(selectedBulletin);
            if (editedBulletin != null) {
                updateService.update(editedBulletin);
                loadDataAndUpdateTable();
                showAlert("Sucesso", "Boletim atualizado com sucesso!");
            }
        } catch (Exception e) {
            showAlert("Erro", "Erro ao editar boletim: " + e.getMessage());
        }
    }

    @FXML
    public void removeBulletin() {
        Bulletin selectedBulletin = tableView.getSelectionModel().getSelectedItem();
        if (selectedBulletin == null) {
            showAlert("Aviso", "Selecione um boletim para remover.");
            return;
        }

        // Confirmar remoção
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmação");
        confirmation.setHeaderText("Remover Boletim");
        confirmation.setContentText("Tem certeza que deseja remover o boletim ID " +
                selectedBulletin.getId() + " de " + selectedBulletin.getCity() + "?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                removeService.remove(selectedBulletin);
                loadDataAndUpdateTable(); // Recarrega os dados
                showAlert("Sucesso", "Boletim removido com sucesso!");
            } catch (Exception e) {
                showAlert("Erro", "Erro ao remover boletim: " + e.getMessage());
            }
        }
    }

    @FXML
    public void filter() {
        try {
            // Obter valores dos filtros
            String cityFilter = txtCity.getText().trim();
            String stateFilter = cbState.getValue();
            LocalDate beginDate = dpBegin.getValue();
            LocalDate endDate = dpEnd.getValue();

            // Converter para Optional
            Optional<String> city = cityFilter.isEmpty() ? Optional.empty() : Optional.of(cityFilter);
            Optional<State> state = stateFilter == null ? Optional.empty() : Optional.of(State.fromName(stateFilter));
            Optional<LocalDate> begin = Optional.ofNullable(beginDate);
            Optional<LocalDate> end = Optional.ofNullable(endDate);

            // Aplicar filtros
            List<Bulletin> filteredData = filterService.filter(databaseData, city, state, begin, end);

            // Atualizar tabela e estatísticas
            updateTable(filteredData);
            updateStatistics(filteredData);

        } catch (Exception e) {
            showAlert("Erro", "Erro ao aplicar filtros: " + e.getMessage());
        }
    }

    private void updateStatistics(List<Bulletin> data) {
        if (data == null || data.isEmpty()) {
            lbTotalInfected.setText("0");
            lbTotalDeaths.setText("0");
            lbAverageIcu.setText("0.0%");
            return;
        }

        StatisticsData stats = statisticsService.createStatistics(data);

        lbTotalInfected.setText(String.valueOf(stats.cases()));
        lbTotalDeaths.setText(String.valueOf(stats.deaths()));
        lbAverageIcu.setText(String.format("%.2f%%", stats.icu()));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Bulletin showBulletinDialog(Bulletin bulletinToEdit) {
        Dialog<Bulletin> dialog = new Dialog<>();
        dialog.setTitle(bulletinToEdit == null ? "Cadastrar Boletim" : "Editar Boletim");
        dialog.setHeaderText(bulletinToEdit == null ? "Preencha os dados do novo boletim:" : "Edite os dados do boletim:");

        // Configurar botões
        ButtonType saveButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Criar campos de entrada
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField idField = new TextField();
        TextField cityField = new TextField();
        ComboBox<State> stateComboBox = new ComboBox<>();
        TextField infectedField = new TextField();
        TextField deathsField = new TextField();
        TextField icuRatioField = new TextField();
        DatePicker datePicker = new DatePicker();

        // Configurar ComboBox de estados
        stateComboBox.setItems(FXCollections.observableArrayList(State.values()));

        // Preencher campos se estiver editando
        if (bulletinToEdit != null) {
            idField.setText(String.valueOf(bulletinToEdit.getId()));
            idField.setDisable(true); // ID não pode ser alterado
            cityField.setText(bulletinToEdit.getCity());
            stateComboBox.setValue(bulletinToEdit.getState());
            infectedField.setText(String.valueOf(bulletinToEdit.getInfected()));
            deathsField.setText(String.valueOf(bulletinToEdit.getDeaths()));
            icuRatioField.setText(String.valueOf(bulletinToEdit.getIcuRatio()));
            datePicker.setValue(bulletinToEdit.getDate());
        } else {
            // Para novo boletim, gerar ID automaticamente
            int nextId = generateNextId();
            idField.setText(String.valueOf(nextId));
            idField.setDisable(true);
            datePicker.setValue(LocalDate.now());
        }

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Cidade:"), 0, 1);
        grid.add(cityField, 1, 1);
        grid.add(new Label("Estado:"), 0, 2);
        grid.add(stateComboBox, 1, 2);
        grid.add(new Label("Infectados:"), 0, 3);
        grid.add(infectedField, 1, 3);
        grid.add(new Label("Mortes:"), 0, 4);
        grid.add(deathsField, 1, 4);
        grid.add(new Label("Taxa UTI (%):"), 0, 5);
        grid.add(icuRatioField, 1, 5);
        grid.add(new Label("Data:"), 0, 6);
        grid.add(datePicker, 1, 6);

        dialog.getDialogPane().setContent(grid);

        // Converter resultado
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String city = cityField.getText().trim();
                    State state = stateComboBox.getValue();
                    int infected = Integer.parseInt(infectedField.getText());
                    int deaths = Integer.parseInt(deathsField.getText());
                    double icuRatio = Double.parseDouble(icuRatioField.getText());
                    LocalDate date = datePicker.getValue();

                    // Validações básicas
                    if (city.isEmpty()) {
                        showAlert("Erro", "Cidade é obrigatória!");
                        return null;
                    }
                    if (state == null) {
                        showAlert("Erro", "Estado é obrigatório!");
                        return null;
                    }
                    if (infected < 0 || deaths < 0 || icuRatio < 0) {
                        showAlert("Erro", "Valores não podem ser negativos!");
                        return null;
                    }
                    if (date == null) {
                        showAlert("Erro", "Data é obrigatória!");
                        return null;
                    }

                    return new Bulletin(id, city, state, infected, deaths, icuRatio, date);
                } catch (NumberFormatException e) {
                    showAlert("Erro", "Verifique se os valores numéricos estão corretos!");
                    return null;
                }
            }
            return null;
        });

        Optional<Bulletin> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private int generateNextId() {
        if (databaseData == null || databaseData.isEmpty()) {
            return 1;
        }
        return databaseData.stream()
                .mapToInt(Bulletin::getId)
                .max()
                .orElse(0) + 1;
    }

    @FXML
    public void clearFilters() {
        txtCity.clear();
        cbState.setValue(null);
        dpBegin.setValue(null);
        dpEnd.setValue(null);

        // Recarregar dados sem filtros
        updateTable(databaseData);
        updateStatistics(databaseData);
    }
}
