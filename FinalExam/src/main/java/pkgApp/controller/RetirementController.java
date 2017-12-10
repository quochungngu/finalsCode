package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {


	private RetirementApp mainApp = null;

	@FXML
	private Label TotalToSave;
	@FXML
	private Label MonthlyToSave;
	@FXML
	private TextField WorkReturn;
	@FXML
	private TextField RetiredReturn;
	@FXML
	private TextField YearsToWork;
	@FXML
	private TextField YearsRetired;
	@FXML
	private TextField IncomeRetired;
	@FXML
	private TextField MonthlySSI;

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
	}

	@FXML
	public void btnClear(ActionEvent event) {
		WorkReturn.clear();
		RetiredReturn.clear();
		YearsToWork.clear();
		YearsRetired.clear();
		IncomeRetired.clear();
		MonthlySSI.clear();

		TotalToSave.setText("Please fill in the fields.");
		MonthlyToSave.setText("Please fill in the fields.");
	}

	@FXML
	public void btnCalculate(ActionEvent event){
		if (isValid()) {
			System.out.println("Passed");
			double dReturnWork = Double.parseDouble(WorkReturn.getText());
			double dReturnRetired = Double.parseDouble(RetiredReturn.getText());
			int iYearsWork = Integer.parseInt(YearsToWork.getText());
			int iYearsRetired = Integer.parseInt(YearsRetired.getText());
			double dIncomeRetired = Double.parseDouble(IncomeRetired.getText());
			double dMonthlySSI = Double.parseDouble(MonthlySSI.getText());
			Retirement inst = new Retirement(iYearsWork, dReturnWork, iYearsRetired, 
					dReturnRetired, dIncomeRetired, dMonthlySSI);

			if (inst.TotalAmountSaved() == 0)
			{
				TotalToSave.setText("You're set. Your SSI is enough.");
				MonthlyToSave.setText("You're set. Your SSI is enough.");
			}
			else {
				TotalToSave.setText(Double.toString(inst.TotalAmountSaved()));
				MonthlyToSave.setText(Double.toString(inst.AmountToSave()));
			}

		}
	}

	private boolean isValid() {
		String errorMessage = "";
		if (WorkReturn.getText() == null || WorkReturn.getText().length() == 0 ||
				RetiredReturn.getText() == null || RetiredReturn.getText().length() == 0 ||
				YearsToWork.getText() == null || YearsToWork.getText().length() == 0 ||
				YearsRetired.getText() == null || YearsRetired.getText().length() == 0 ||
				IncomeRetired.getText() == null || IncomeRetired.getText().length() == 0 ||
				MonthlySSI.getText() == null || MonthlySSI.getText().length() == 0) {
			errorMessage += "*Not all fields are filled.\n";
		}
		else {
			try {
				Double.parseDouble(WorkReturn.getText());
				Double.parseDouble(RetiredReturn.getText());
				Double.parseDouble(IncomeRetired.getText());
				Double.parseDouble(MonthlySSI.getText());
			}
			catch (NumberFormatException e) {
				errorMessage += "*Annual returns, income, and SSI must be numbers.\n";
			}
			try {
				Integer.parseInt(YearsToWork.getText());
				Integer.parseInt(YearsRetired.getText());
			}
			catch (NumberFormatException e) {
				errorMessage += "*Years must be integers.";
			}
		}


		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText(errorMessage);
			alert.setContentText("Please correct the errors.");

			alert.showAndWait();
			return false;
		}
	}
}
