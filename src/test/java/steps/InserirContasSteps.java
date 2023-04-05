package steps;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class InserirContasSteps {
	
	private WebDriver driver;
	
	
	@Dado("que estou acessando a aplicação")
	public void queEstouAcessandoAAplicação() {
		System.setProperty("webdriver.chrome.driver", "c:/autodrivers/chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://seubarriga.wcaquino.me/");

		driver.manage().window().maximize();
	}

	@Quando("informo o usuário {string}")
	public void informoOUsuário(String string) {
	    driver.findElement(By.id("email")).sendKeys(string);
	}

	@Quando("a senha {string}")
	public void aSenha(String string) {
		driver.findElement(By.id("senha")).sendKeys(string);
	}

	@Quando("seleciono entrar")
	public void selecionoEntrar() {
	    driver.findElement(By.className("btn-primary")).click();
	}

	@Então("visualizo a página inicial")
	public void visualizoAPáginaInicial() {
	    String mensagemEntrada = driver.findElement(By.className("alert-success")).getText();
	    String mensagemEsperada = "Bem vindo, Leonardo!";
	    Assert.assertEquals(mensagemEsperada, mensagemEntrada);
	}

	@Quando("seleciono Contas")
	public void selecionoContas() {
	    driver.findElement(By.linkText("Contas")).click();
	}

	@Quando("seleciono Adicionar")
	public void selecionoAdicionar() {
		driver.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("informo a conta {string}")
	public void informoAConta(String string) {
	    driver.findElement(By.id("nome")).sendKeys(string);
	}
	

	@Quando("seleciono Salvar")
	public void selecionoSalvar() {
		driver.findElement(By.tagName("button")).click();
	}

//	@Então("a conta é inserida com sucesso")
//	public void aContaÉInseridaComSucesso() {
//	    String msg = "Conta adicionada com sucesso!";
//	    String mensagemEntrada = driver.findElement(By.className("alert-success")).getText();
//	    Assert.assertEquals(msg, mensagemEntrada);
//	}
//	
//	@Então("sou notificado que o nome da conta é obrigatório")
//	public void souNotificadoQueONomeDaContaÉObrigatório() {
//		String msg = "Informe o nome da conta";
//	    String mensagemEntrada = driver.findElement(By.className("alert-danger")).getText();
//	    Assert.assertEquals(msg, mensagemEntrada);
//	}
//	
//	@Então("sou notificado que já existe uma conta com esse nome")
//	public void souNotificadoQueJáExisteUmaContaComEsseNome() {
//		String msg = "Já existe uma conta com esse nome!";
//	    String mensagemEntrada = driver.findElement(By.className("alert-danger")).getText();
//	    Assert.assertEquals(msg, mensagemEntrada);
//	}
	
	@Então("recebo a mensagem {string}")
	public void receboAMensagem(String string) {
	    String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
	    Assert.assertEquals(string, texto);
	}
	
	@Before(order = 0)
	public void inicio() {
		System.out.println("Começando aqui!");
	}
	
	@Before(order = 1)
	public void inicio2() {
		System.out.println("Começando aqui, parte 2!");
	}
	
	@After(order = 1, value = "@funcionais")
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getLine()+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@After(order = 0, value = "@funcionais")
	public void fecharBrowser() {
		driver.quit();
	}
}
