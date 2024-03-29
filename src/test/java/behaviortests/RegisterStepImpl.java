package behaviortests;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import p2.dao.impl.UserDAO;
import pages.MainDriver;

public class RegisterStepImpl {

	static {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	}

	static ChromeDriver driver = new ChromeDriver();
	static MainDriver mainDriver = new MainDriver(driver);
	static UserDAO useful = new UserDAO();

	@Given("^I am on the register page$")
	public void i_am_on_the_register_page() {
		driver.get(MainDriver.homeUrl + "login");
	}

	@Given("^registration fields are correctly filled$")
	public void registration_fields_are_correctly_filled() {
		mainDriver.findRegisterEmail().sendKeys("testSeller@test.test");
		mainDriver.findRegisterPass().sendKeys("password");
		mainDriver.findRegisterFname().sendKeys("john");
		mainDriver.findRegisterLname().sendKeys("doe");
		mainDriver.findRegisterAddress().sendKeys("No ty");
		mainDriver.findCreditCardNumber().sendKeys("1346");
		Select role = new Select(driver.findElement(By.name("role")));
		role.selectByVisibleText("SELLER");
		mainDriver.findCVV().sendKeys("123");
	}

	@When("^I click on submit registration$")
	public void i_click_on_submit_registration() {
		mainDriver.requestRegistration().click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("^I am redirected home or given an email taken error$")
	public void i_am_redirected_home_or_given_an_email_taken_error() {
		Assert.assertEquals(driver.findElement(By.id("dropdownBasic")).getText(), "My PrettyPenny");
	}

}
