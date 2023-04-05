package runners;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "steps",
		tags = {"@funcionais"},
		plugin = {"pretty"},
		monochrome = false,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
		)
public class RunnerFuncionalTest {
	
	@BeforeClass
	public static void reset() {
		WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver", "c:/autodrivers/chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://seubarriga.wcaquino.me/");

		driver.manage().window().maximize();
		
		driver.findElement(By.id("email")).sendKeys("tc@teste.com");
		driver.findElement(By.id("senha")).sendKeys("teste");
		driver.findElement(By.className("btn-primary")).click();
		driver.findElement(By.linkText("reset")).click();
		driver.quit();
	}
	

}
