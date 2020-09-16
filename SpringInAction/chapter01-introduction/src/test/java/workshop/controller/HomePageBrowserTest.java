package workshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomePageBrowserTest {

	@LocalServerPort
	private int port;

	private static HtmlUnitDriver browser;

	@BeforeAll
	public static void init() {
		browser = new HtmlUnitDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterAll
	public static void destroy() {
		browser.quit();
	}

	@Test
	@DisplayName("test home page")
	public void testHomePage() {
		String homePage = "http://localhost:" + port;
		browser.get(homePage);

		String title = browser.getTitle();
		assertEquals("taco cloud", title);

		String h1Text = browser.findElementByTagName("h1").getText();
		assertEquals("Welcome to...", h1Text);

		String imageSrc = browser.findElementByTagName("img").getAttribute("src");
		assertEquals(homePage + "/images/TacoCloud.png", imageSrc);
	}
}
