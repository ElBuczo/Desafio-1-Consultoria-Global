package com.automatizacion;
import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GlobalContacto {
	private WebDriver driver;
	By ContáctenosLinkLocator = By.linkText("Contáctenos");
	By FormularioLocator = By.className("wpcf7-form");
	private String[] input;
	By NombreLocator = By.cssSelector("input[name='your-name']");
	By EmailLocator = By.cssSelector("input[name='your-email']");
	By AsuntoLocator = By.cssSelector("input[name='your-subject']");
	By MensajeLocator = By.cssSelector("textarea[name='your-message']");
	//By AutenticadorLocator = By.cssSelector("input[name='_wpcf7_captcha_challenge_captcha-636']");
	By EnviarLocator = By.cssSelector("input[value=\"Enviar\"]");
	//By AlertLocator = By.linkText("La dirección e-mail parece inválida.");

	@Before
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.consultoriaglobal.com.ar/cgweb/");
	}
	
	@Test
	public void Contáctenos() throws InterruptedException {
		System.out.println("Haciendo clic en el enlace 'Contáctenos'...");
		driver.findElement(ContáctenosLinkLocator).click();
		Thread.sleep(2000);
		if(driver.findElement(FormularioLocator).isDisplayed()) {
			System.out.println("Llenando el formulario...");
			driver.findElement(NombreLocator).sendKeys("Test Test");
			driver.findElement(EmailLocator).sendKeys("Test Test");
			driver.findElement(AsuntoLocator).sendKeys("Test Test");
			driver.findElement(MensajeLocator).sendKeys("Test Test");
			//driver.findElement(AutenticadorLocator).sendKeys("Test Test");
			
			System.out.println("Enviando el formulario...");
			driver.findElement(EnviarLocator).click();
		}
		else {
			System.out.print("No se encontro el formulario");
		}
		Thread.sleep(2000);
		List<WebElement> spans = driver.findElements(By.tagName("span"));
		String textoObtenido = spans.get(2).getText();
		System.out.println(textoObtenido);
		assertEquals("La dirección e-mail parece inválida.", textoObtenido);

	
	
	}
	
	@After
	
	public void tearDown() {
		 System.out.println("Cerrando el navegador...");
		driver.quit();
	}

}
