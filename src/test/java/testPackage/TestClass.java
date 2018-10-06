package testPackage;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pagesPackages.AutoLoanCalculator;

public class TestClass extends BaseClass {

	@Test(description = "User story 1: Check error message is displayed if price not entered")
	public void Validate_Price() throws HeadlessException, AWTException, IOException, InterruptedException {
		testLog.log(Status.INFO, "User story 1");
		int TestID = 1;// Tester can choose which set of data in "Login" sheet in the TestData excel
						// file to test by providing ID

		// validate successful navigation and price textbox
		AutoLoanCalculator obj = new AutoLoanCalculator();
		obj.CheckForNavigation();
		obj.ValidatePrice(TestID);

	}

	@Test(description = "User story2 : Verify whether the Auto Loan calculator generated values are correct")
	public void EMI_Calculator() throws HeadlessException, AWTException, IOException, InterruptedException {
		// Tester can choose which set of data in the TestData excel file to test by
		// providing ID
		int TestID = 1;

		testLog.log(Status.INFO, "User story 2");

		// verify calculated emi an display the values
		AutoLoanCalculator obj = new AutoLoanCalculator();
		obj.CheckForNavigation();
		obj.EMICalculator(TestID);
		obj.ValidateCalculator(TestID);
	}

}
