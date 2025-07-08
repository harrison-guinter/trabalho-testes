package modules.relatorio.tests;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import modules.relatorio.pages.GeracaoRelatorioPage;
import utils.driver.DriverFactory;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class GeracaoRelatorioTest {

	private DSL dsl = new DSL();
	private static GeracaoRelatorioPage page = new GeracaoRelatorioPage();

	private final String downloadDir = System.getProperty("user.home") + "/Downloads";

	private final String nomeEsperadoDoArquivo = "relatorio.pdf";

	@Parameter
	public String dataInicial;

	@Parameter(value = 1)
	public String dataFinal;

	@Parameter(value = 2)
	public String categoria;

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{ "2024-07-01", "2024-07-07", "Categoria RR74ab3b0a" },
			{ "2024-06-01", "2024-06-30", "Categoria RR39503269" }
		});
	}

	@BeforeClass
	public static void abrirNavegador() {
	
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverFactory.getDriver().manage().window().maximize();
	}

	@Before
	public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/relatorio");
	}

	@Test
	public void deveGerarRelatorioEValidarDownload() throws Exception {
		Path arquivo = Paths.get(downloadDir, nomeEsperadoDoArquivo);
		Files.deleteIfExists(arquivo);

	
		page.setDataInicial(dataInicial);
		page.setDataFinal(dataFinal);
		page.selecionarCategoria(categoria);
		page.marcarMonitoramentoPF();
		page.marcarMonitoramentoExercito();

		Assert.assertTrue("Botão deve estar habilitado", page.isBotaoGerarHabilitado());

		page.gerarRelatorio();

		boolean baixado = aguardarDownload(arquivo, 30);
		Assert.assertTrue("Arquivo não foi baixado", baixado);
	}

	private boolean aguardarDownload(Path caminho, int timeoutSegundos) throws InterruptedException {
		int esperou = 0;
		while (esperou < timeoutSegundos) {
			if (Files.exists(caminho)) {
				return true;
			}
			Thread.sleep(1000);
			esperou++;
		}
		return false;
	}
}