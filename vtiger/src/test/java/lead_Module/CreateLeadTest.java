package lead_Module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import pomRepository.CreatingNewLeadPage;
import pomRepository.HomePage;
import pomRepository.LeadInformationPage;
import pomRepository.LeadPage;

@Listeners (genericUtility.ItestListenersImplementationClass.class)

public class CreateLeadTest extends BaseClass {
	
	@Test( groups = "smoke", retryAnalyzer = genericUtility.IRetryAnalyserImplementationClass.class)
	public void createLeadAndVerifyLeadInformationTest() throws EncryptedDocumentException, IOException {
		//Test Data
		String leadSalutation = eUtils.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 0);
		String leadFirstName = eUtils.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 1);
		String leadLastName = eUtils.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 2);
		String leadCompanyName = eUtils.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 3);
		String leadAssignedTo = eUtils.fetchStringDataFromExcelSheet(IPathConstant.LEAD_SHEET_NAME, 1, 4);

		//POM Object Creating
		HomePage home = new HomePage(driver);
		LeadPage lead = new LeadPage(driver);
		CreatingNewLeadPage createLead = new CreatingNewLeadPage(driver);
		LeadInformationPage leadInfo = new LeadInformationPage(driver);
		
		
		//Test Script
		home.clickOnLeadsModule();
		
		lead.clickOnLeadPlusButton();
		
		createLead.selectLeadSalutation(leadSalutation);
		createLead.enterLeadFirstName(leadFirstName);
		createLead.enterLeadLastName(leadLastName);
		createLead.enterLeadCompanyName(leadCompanyName);
		createLead.clickOnAssignedToGroupRadioButton();
		createLead.selectFromAssignedToGroupDropDown(leadAssignedTo);
		createLead.clickOnSaveButton();
		
		String actualLeadLastName = leadInfo.verifyLeadInfo(leadLastName);
		
		//Verification
		Assert.assertTrue(actualLeadLastName.contains(leadLastName), "Fail: the Lead has not been created");
		System.out.println("Pass: the Lead has been created");
		
	}

}
