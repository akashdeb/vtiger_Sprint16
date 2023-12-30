package opportunity_Module;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import pomRepository.CampaignInformation;
import pomRepository.CampaignPage;
import pomRepository.CreatingNewCampaignPage;
import pomRepository.CreatingNewOpportunityPage;
import pomRepository.CreatingNewOrganizationPage;
import pomRepository.HomePage;
import pomRepository.OpportunitiesPage;
import pomRepository.OpportunityInformationPage;
import pomRepository.OrganizationInformationPage;
import pomRepository.OrganizationsPage;

public class ToCreateOpportunityTest extends BaseClass {
	
	@Test(groups = "regression")
	public void ToCreateOpportunityWithOrganizationAndCampaignDetailsAndVerifyOpportunityTest() throws EncryptedDocumentException, IOException {
		
		int randomNo = jUtils.generateRandomNumber(10000);
		//TestData
		String organizationName = eUtils.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 1, 0)+randomNo;
		String campaignName = eUtils.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 3, 0);
		String opportunityName = eUtils.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 5, 0);
		String organizationLookUpPageTitle = eUtils.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 5, 1);
		String campaingLookUpPageTitle = eUtils.fetchStringDataFromExcelSheet(IPathConstant.OPPORTUNITY_SHEET_NAME, 5, 2);

		//Object Creating
		HomePage home = new HomePage(driver);
		
		OrganizationsPage organization = new OrganizationsPage(driver);
		CreatingNewOrganizationPage createOrganization = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage organizationInfo = new OrganizationInformationPage(driver);
		
		CampaignPage campaign = new CampaignPage(driver);
		CreatingNewCampaignPage createCampaign = new CreatingNewCampaignPage(driver);
		CampaignInformation campaignInfo = new CampaignInformation(driver);
		
		OpportunitiesPage opportunity = new OpportunitiesPage(driver);
		CreatingNewOpportunityPage createOpportunity = new CreatingNewOpportunityPage(driver);
		OpportunityInformationPage opportunityInfo = new OpportunityInformationPage(driver);
		
		home.clickOnOrganizationModule();
		organization.clickOrganizationPlusButton();
		createOrganization.enterOrganizationName(organizationName);
		createOrganization.clickOnSaveButton();
		String actualOrganizationName = organizationInfo.verifyOrganizationDetails(organizationName);
		Assert.assertTrue(actualOrganizationName.contains(organizationName));
		System.out.println("Pass: the organization has been created");
		
		
		home.clickOnCampaignsModule();
		campaign.clickOnCampignPlusButton();
		createCampaign.enterCampaignName(campaignName);
		createCampaign.clickOnSaveButton();
		String actualCampaignName = campaignInfo.verifyCampaignDetails(campaignName);
		Assert.assertTrue(actualCampaignName.contains(campaignName));
		System.out.println("Pass: the Campaign has been created");
		
		home.clickOnOpportunityModule();
		opportunity.clickOnOpportunityPlusButton();
		createOpportunity.enterOpportunityName(opportunityName);
		createOpportunity.enterOrganizationDetailsFromRelatedToLookUpPage(organizationLookUpPageTitle, organizationName);
		createOpportunity.enterCampaingsDetailsFromCampaignSourceLookUpPage(campaingLookUpPageTitle, campaignName);
		createOpportunity.clickOnSaveButton();
		
		String actualOpportuntityName = opportunityInfo.verifyOpportunityDetails(opportunityName);
		Assert.assertTrue(actualOpportuntityName.contains(opportunityName));
		System.out.println("Pass: the Opportunity has been created");
		
	}

}
