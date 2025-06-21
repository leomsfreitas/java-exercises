package prova03.simulado02.controller;

import prova03.simulado02.persistence.EmployeeDAO;
import prova03.simulado02.persistence.SQLiteEmployeeDAO;
import prova03.simulado02.services.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class MainViewController {

    @FXML private TableView<EmployeeDTO> tableView;
    @FXML private TableColumn<EmployeeDTO, String> colId;
    @FXML private TableColumn<EmployeeDTO, String> colName;
    @FXML private TableColumn<EmployeeDTO, LocalDate> colBirthDate;
    @FXML private TableColumn<EmployeeDTO, Double> colSoldValue;
    @FXML private TableColumn<EmployeeDTO, String> colInChargeId;

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtSoldValue;
    @FXML private ComboBox<String> comboInCharge;
    @FXML private Label lblCommission;

    private final EmployeeDAO dao = new SQLiteEmployeeDAO();

    private final RegisterEmployeeService registerService = new RegisterEmployeeService(dao);
    private final UpdateEmployeeService updateService = new UpdateEmployeeService(dao);
    private final RemoveEmployeeService removeService = new RemoveEmployeeService(dao);
    private final CommissionService commissionService = new CommissionService(dao);

    private final ObservableList<EmployeeDTO> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().id()));
        colName.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().name()));
        colBirthDate.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().birthDate()));
        colSoldValue.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().soldValue()));
        colInChargeId.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().inChargeId()));

        tableView.setItems(employees);
        loadEmployees();
    }

    private void loadEmployees() {
        employees.setAll(dao.findAll());
        comboInCharge.setItems(FXCollections.observableArrayList(
                employees.stream().map(EmployeeDTO::id).toList()
        ));
    }

    @FXML
    public void onRegister() {
        EmployeeDTO employee = readForm();
        registerService.register(employee);
        clearForm();
        loadEmployees();
    }

    @FXML
    public void onUpdate() {
        EmployeeDTO employee = readForm();
        updateService.updateEmployee(employee);
        clearForm();
        loadEmployees();
    }

    @FXML
    public void onRemove() {
        String id = txtId.getText();
        removeService.remove(id);
        clearForm();
        loadEmployees();
    }

    @FXML
    public void showCommission(MouseEvent event) {
        EmployeeDTO selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            double commission = commissionService.calculateCommission(selected.id());
            lblCommission.setText(String.format("Commission: R$ %.2f", commission));
        }
    }

    private EmployeeDTO readForm() {
        return new EmployeeDTO(
                txtId.getText(),
                txtName.getText(),
                datePicker.getValue(),
                Double.parseDouble(txtSoldValue.getText()),
                comboInCharge.getValue()
        );
    }

    private void clearForm() {
        txtId.clear();
        txtName.clear();
        datePicker.setValue(null);
        txtSoldValue.clear();
        comboInCharge.getSelectionModel().clearSelection();
        lblCommission.setText("");
    }

    @FXML
    public void btnUpdate() {
        onUpdate();
    }

    @FXML
    public void btnView() {
        System.out.println("Botão View clicado!");
    }

    @FXML
    public void btnDelete() {
        onRemove();
    }

    @FXML
    public void btnNew() {
        clearForm();
    }
}
