package pagesPackages;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import helperPackage.BrowserFactory;
import testPackage.BaseClass;
import utilityPackage.ConfigReader;
import utilityPackage.ExcelDataReader;
import utilityPackage.ScreenShot;

public class AutoLoanCalculator extends BaseClass {

	public AutoLoanCalculator() {

		PageFactory.initElements(BrowserFactory.driver, this);
	}

	// Initialize Webelements

	@FindBy(how = How.CLASS_NAME, using = "close")
	static WebElement continueButton;

	// Find the CarResearch dropdown
	@FindBy(how = How.ID, using = ("CarResearch & Reviews"))
	WebElement carResearch;

	// Find Monthly payment calculator
	@FindBy(how = How.XPATH, using = ("//a[contains(text(),'Monthly Payment Calculator')]"))
	WebElement monthlyPaymentCalculator;

	// Find tenure options
	@FindBy(how = How.XPATH, using = ("//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-loanCalculatorMonths-loanCalculatorLengthOfLoan\']/tbody/tr"))
	WebElement monthsTable;

	// Find 24 months
	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/table[1]/tbody[1]/tr[1]/td[1]/label[1]"))
	WebElement month;

	// Find interest rate textbox
	@FindBy(how = How.XPATH, using = ("//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_55-loanCalculatorInterestRate\']"))
	WebElement interestRate;

	// Find price textbox
	@FindBy(how = How.XPATH, using = ("//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_5k-priceInput\']"))
	WebElement price;

	// Find salestax rate textbox
	@FindBy(how = How.XPATH, using = ("//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_5y-loanCalculatorSalesTaxRate\']"))
	WebElement salesTaxRate;

	// Find down payment
	@FindBy(how = How.XPATH, using = ("//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_6d-loanCalculatorDownPaymentInput\']"))
	WebElement downPayment;

	// Find calculate button
	@FindBy(how = How.XPATH, using = ("//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_7m-loanCalculatorCalculateActionPanel-j_id_7r-j_id_7r\']"))
	WebElement calculateBtn;

	// Find the results in Auto Loan calculator
	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[3]"))
	WebElement MonthandInterest;

	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/span[2]/span[1]"))
	WebElement price1;

	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/span[2]/span[1]"))
	WebElement totalFinance;

	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/span[2]/span[1]"))
	WebElement totalInterest;

	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/span[2]/span[1]"))
	WebElement totalLoan;

	@FindBy(how = How.XPATH, using = ("/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/span[2]"))
	WebElement actualEMI;

	// Check for successfull navigation to home page and loan calculator page
	public void CheckForNavigation() throws IOException, InterruptedException, HeadlessException, AWTException {
		Thread.sleep(2000);

		continueButton.click();

		// Check whether navigated to Autotrader homepage
		Thread.sleep(1000);
		WebElement image = BrowserFactory.driver.findElement(
				By.xpath("//*[@id=\'mountNode\']/div/div[2]/header/div/div/nav/div/div[1]/div/div[1]/a/img[1]"));

		if (image.isDisplayed())

			testLog.log(Status.PASS, "Navigated to homepage");
		else {

			testLog.log(Status.FAIL, "Could not navigate to homepage");
			ScreenShot.captureScreenshot(BrowserFactory.driver, "Homepage not displayed");
		}

		// Click on Car research & reviews
		Thread.sleep(1000);
		carResearch.click();

		// Select monthly payment calculator
		Thread.sleep(1000);
		monthlyPaymentCalculator.click();

		// Check whether navigated to Auto loan calculator page
		Thread.sleep(1000);
		String expectedTitle = "Auto Loan Calculator";
		String actualTitle = BrowserFactory.driver
				.findElement(By.xpath("//*[@id=\"atcui-page-wrapper\"]/div[3]/div[2]/h1/span")).getText();

		if (expectedTitle.equals(actualTitle))
			testLog.log(Status.PASS, "Navigated to loan calculator page");
		else {
			ScreenShot.captureScreenshot(BrowserFactory.driver, "Loan calculator page not displayed");
			testLog.log(Status.FAIL, "Loan calculator page not displayed");
		}

	}

	// Check if error message is displayed if data is not entered in price field
	public void ValidatePrice(int TestID) throws HeadlessException, IOException, AWTException {

		// Get value from ExcelSheet
		ExcelDataReader excel = new ExcelDataReader(ConfigReader.getExcelPath());

		try {

			// click on 24 months
			Thread.sleep(500);
			month.click();

			// enter interest rate
			Thread.sleep(500);
			interestRate.clear();
			interestRate.sendKeys(excel.getData("EMIData", TestID, 3));

			// click on calculate button
			Thread.sleep(500);
			calculateBtn.click();

			// Check for error message displayed
			Thread.sleep(2000);
			String expectedErrorMsg = "Please fill out the Vehicle Price field.";
			String actualErrorMsg = BrowserFactory.driver.findElement(By.xpath(
					"/html[1]/body[1]/div[2]/div[3]/div[2]/div[1]/form[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]"))
					.getText();
			Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
			testLog.log(Status.PASS, "Error message is displayed");
			ScreenShot.captureScreenshot(BrowserFactory.driver, "Price Error message");
		} catch (Exception e) {
			testLog.log(Status.FAIL, "Price  Error Message not displayed ");
			ScreenShot.captureScreenshot(BrowserFactory.driver, "No Price Error Message");
		}

	}

	// Calculate EMI for different tenure and interest rates using Auto Loan
	// Calculator
	public void EMICalculator(int TestID) throws IOException, HeadlessException, AWTException, InterruptedException {

		// Get value from ExcelSheet
		ExcelDataReader excel = new ExcelDataReader(ConfigReader.getExcelPath());

		// Select month option/tenure
		String expectedTenure = excel.getData("EMIData", TestID, 1);
		String BeforeXPath = "//*[@id='j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-loanCalculatorMonths-loanCalculatorLengthOfLoan']/tbody/tr/td[";
		String AfterXpath = "]/label";
		for (int i = 1; i <= 5; i++) {
			Thread.sleep(1000);
			String tenureOption = BrowserFactory.driver.findElement(By.xpath(BeforeXPath + i + AfterXpath)).getText();

			if (tenureOption.contains(expectedTenure)) {

				BrowserFactory.driver.findElement(By.xpath(BeforeXPath + i + AfterXpath)).click();
				break;
			}

		}

		// Enter interest rates from excel sheet
		Thread.sleep(1000);
		interestRate.clear();
		Thread.sleep(1000);
		interestRate.sendKeys(excel.getData("EMIData", TestID, 2));

		// Enter price from excelsheet
		Thread.sleep(1000);
		price.sendKeys(excel.getData("EMIData", TestID, 3));

		// Enter sales tax rate
		Thread.sleep(1000);
		salesTaxRate.clear();
		salesTaxRate.sendKeys(excel.getData("EMIData", TestID, 4));

		// Enter down payments from excelSheet
		Thread.sleep(1000);
		downPayment.clear();
		downPayment.sendKeys(excel.getData("EMIData", TestID, 5));

		// Click on calculate button
		Thread.sleep(1000);
		calculateBtn.click();

		try {
			// Check if interest is calculated
			Thread.sleep(1000);
			String expectedTxt = "Calculate again";
			String actualTxt = BrowserFactory.driver.findElement(By.xpath(
					"//*[@id=\'j_id_3s-tabsColumn-j_id_43-j_id_44-autoLoanCalculator-j_id_4a-calculatorFormColumn-j_id_9e\']"))
					.getText();
			Thread.sleep(1000);
			Assert.assertEquals(actualTxt, expectedTxt);
			testLog.log(Status.PASS, "Interest calculated");
		} catch (Exception e) {
			testLog.log(Status.FAIL, "Interest not calculated");
			ScreenShot.captureScreenshot(BrowserFactory.driver, "Interest not calculated");
		}
	}

	// Method to Calculate EMI and verify the results from Auto Loan calculator
	public void ValidateCalculator(int TestID) throws HeadlessException, AWTException, IOException {
		// Get value from ExcelSheet
		ExcelDataReader excel = new ExcelDataReader(ConfigReader.getExcelPath());
		String exptedMonthInt = excel.getData("EMIData", TestID, 8);
		String expectedTotalInterest = excel.getData("EMIData", TestID, 9);
		String expectedTotalFinance = excel.getData("EMIData", TestID, 10);
		String expectedTotalLoan = excel.getData("EMIData", TestID, 11);
		String expectedEMI = excel.getData("EMIData", TestID, 12);

		// Check whether the results are the same
		try {
			Assert.assertEquals(MonthandInterest.getText(), exptedMonthInt, "Error: values not the same");
			testLog.log(Status.INFO, "The price values are" + MonthandInterest.getText() + exptedMonthInt);

			Assert.assertEquals(price1.getText(), price, "Error: values not the same");
			testLog.log(Status.INFO, "The price values are" + price1.getText() + price);

			Assert.assertEquals(totalInterest.getText(), expectedTotalInterest, "Error: values not the same");
			testLog.log(Status.INFO, "The interest values are" + totalInterest.getText() + expectedTotalInterest);

			Assert.assertEquals(actualEMI.getText(), expectedEMI, "Error: values not the same");
			testLog.log(Status.INFO, "The EMI values are" + actualEMI.getText() + expectedEMI);

			Assert.assertEquals(totalFinance.getText(), expectedTotalFinance, "Error: values not the same");
			testLog.log(Status.INFO, "The finance values are" + totalFinance.getText() + expectedTotalFinance);

			Assert.assertEquals(totalLoan.getText(), expectedTotalLoan, "Error: values not the same");
			testLog.log(Status.INFO, "The loan values are" + totalLoan.getText() + expectedTotalLoan);

		} catch (Exception e) {
			testLog.log(Status.FAIL, "The results are not as expected");
			ScreenShot.captureScreenshot(BrowserFactory.driver, "Error in calculation");
		}

	}

}
