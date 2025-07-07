package modules.categoria.tests;

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
import modules.categoria.pages.ListaCategoriaPage;
import utils.driver.DriverFactory;
import utils.dsl.DSL;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class TesteListaCategoria {
	private DSL dsl = new DSL();
	private static ListaCategoriaPage categoriaPage = new ListaCategoriaPage();
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public boolean status;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"Teste novo Ativo", true},
			{"Teste novo Inativo", false},
			{"Teste novo Ativo", true}
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/categorias");
	}
	
	@Test
	public void deveFiltrarResultadosLista() throws IOException {
		categoriaPage.setFiltroNome(nome);
		categoriaPage.setFiltroStatus(status);
		categoriaPage.filtrar();	
		
		var nomeEncontrado = categoriaPage.pegarNomePrimeiroItem();
		
		assertTrue(nomeEncontrado.contains(nome));
	}
	
	@Test(expected = Exception.class)
	public void naoDeveEncontrarResultadosLista() throws IOException {
		categoriaPage.setFiltroNome(nome + "----- NOME INCORRETO -----");
		categoriaPage.setFiltroStatus(status);
		categoriaPage.filtrar();
		
		assertEquals("Atenção\nCategoria não encontrada.",categoriaPage.getTextoMensagem());
		
		var nomeEncontrado = categoriaPage.pegarNomePrimeiroItem();
	}
}
