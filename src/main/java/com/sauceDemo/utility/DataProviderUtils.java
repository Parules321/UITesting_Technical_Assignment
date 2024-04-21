package com.sauceDemo.utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

	@DataProvider(name = "validLoginData")
	private String[][] loginInfoProvider() throws IOException {
		String filePath = "./src/test/resources/SauceDemoLoginData.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "ValidLoginData");
		int colCount = ExcelUtils.getColumnCount(filePath, "ValidLoginData", rowCount);
		String[][] loginData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = ExcelUtils.getCellValue(filePath, "ValidLoginData", i, j);
			}
		}
		return loginData;
	}

	@DataProvider(name = "invalidUsernameData")
	private String[][] invalidUsernameProvider() throws IOException {
		String filePath = "./src/test/resources/SauceDemoLoginData.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "InvalidUsername");
		int colCount = ExcelUtils.getColumnCount(filePath, "InvalidUsername", rowCount);
		String[][] loginData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = ExcelUtils.getCellValue(filePath, "InvalidUsername", i, j);
			}
		}
		return loginData;
	}

	@DataProvider(name = "invalidPasswordData")
	private String[][] invalidPasswordProvider() throws IOException {
		String filePath = "./src/test/resources/SauceDemoLoginData.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "InvalidPassword");
		int colCount = ExcelUtils.getColumnCount(filePath, "InvalidPassword", rowCount);
		String[][] loginData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = ExcelUtils.getCellValue(filePath, "InvalidPassword", i, j);
			}
		}
		return loginData;
	}

	@DataProvider(name = "incompleteFirstNameData")
	private String[][] incompleteFirstNameDataProvider() throws IOException {
		String filePath = "./src/test/resources/UserDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "IncompleteFirstName");
		int colCount = ExcelUtils.getColumnCount(filePath, "IncompleteFirstName", rowCount);
		String[][] userData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				userData[i - 1][j] = ExcelUtils.getCellValue(filePath, "IncompleteFirstName", i, j);
			}
		}
		return userData;
	}

	@DataProvider(name = "incompleteLastNameData")
	private String[][] firstNameDataProvider() throws IOException {
		String filePath = "./src/test/resources/UserDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "IncompleteLastName");
		int colCount = ExcelUtils.getColumnCount(filePath, "IncompleteLastName", rowCount);
		String[][] userData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				userData[i - 1][j] = ExcelUtils.getCellValue(filePath, "IncompleteLastName", i, j);
			}
		}
		return userData;
	}

	@DataProvider(name = "incompleteZipCodeData")
	private String[][] lastNameDataProvider() throws IOException {
		String filePath = "./src/test/resources/UserDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "IncompleteZipCode");
		int colCount = ExcelUtils.getColumnCount(filePath, "IncompleteZipCode", rowCount);
		String[][] userData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				userData[i - 1][j] = ExcelUtils.getCellValue(filePath, "IncompleteZipCode", i, j);
			}
		}
		return userData;
	}

	@DataProvider(name = "userData")
	private String[][] zipCodeDataProvider() throws IOException {
		String filePath = "./src/test/resources/UserDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "CompleteDetails");
		int colCount = ExcelUtils.getColumnCount(filePath, "CompleteDetails", rowCount);
		String[][] userData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				userData[i - 1][j] = ExcelUtils.getCellValue(filePath, "CompleteDetails", i, j);
			}
		}
		return userData;
	}

}
