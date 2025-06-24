package modules.login.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import modules.login.pages.LoginPage;
import utils.driver.DriverFactory;
import utils.dsl.DSL;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TesteLogin {
	
	private DSL dsl;
	private static LoginPage page = new LoginPage();
	
	@Parameter
	public String email;
	@Parameter(value=1)
	public String senha;
	@Parameter(value=2)
	public String localResposta;
	@Parameter(value=3)
	public String resposta;

	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("http://35.209.123.161/front");
		dsl = new DSL();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"email@email.com", "senha_incorreta", page.getPathMensagemSenhaIncorreta(), page.getMsgSenhaIncorreta()}
		});
	}
	
	@Test
	public void deveValidarRegrasNegocioLogin() throws IOException {
		page.setEmail(email);
		page.setSenha(senha);
		page.logar();	
		Assert.assertEquals(resposta, dsl.obterTexto(By.xpath(localResposta)));
	}
	
	@Test
	public void deveLogarComSucesso() throws IOException {
	    page.setEmail("harrison.dietze@aluno.feliz.ifrs.edu.br");
	    page.setSenha("IronBridgeFC");
	    page.logar();
	    
	    Assert.assertEquals("http://35.209.123.161/front/", DriverFactory.getDriver().getCurrentUrl());
	}

}
