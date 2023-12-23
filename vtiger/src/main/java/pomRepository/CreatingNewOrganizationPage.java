package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class CreatingNewOrganizationPage {
	
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name = "accountname")
	private WebElement organizationNameTextField;

	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveButton;

	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}
	
	
	
	public WebElement getSaveButton() {
		return saveButton;
	}



	public void enterOrganizationName(String organizationName) {
		
		organizationNameTextField.sendKeys(organizationName);
	}
	
	public void clickOnSaveButton() {
		saveButton.click();
	}

}
