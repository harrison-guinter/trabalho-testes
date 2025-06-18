package modules.local.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;

import modules.local.pages.ListaLocaisPage;
import utils.driver.DriverFactory;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class TesteListaLocais {
	private DSL dsl = new DSL();
	private static ListaLocaisPage locaisPage = new ListaLocaisPage();
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public boolean status;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"dadasd", true}
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/locais");
	}
	
	@Test
	public void deveFiltrarResultadosLista() throws IOException {
		locaisPage.setFiltroNome(nome);
//		locaisPage.setFiltroStatus(status);
		locaisPage.filtrar();	
		
	}
}
