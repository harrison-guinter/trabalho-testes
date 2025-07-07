package modules.marca.tests;

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

import modules.marca.pages.ListaMarcaPage;
import utils.driver.DriverFactory;
import utils.dsl.DSL;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class TesteListaMarca {
	private DSL dsl = new DSL();
	private static ListaMarcaPage marcaPage = new ListaMarcaPage();
	
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
		dsl.definirUrl("http://35.209.123.161/front/marcas");
	}
	
	@Test
	public void deveFiltrarResultadosLista() throws IOException {
		marcaPage.setFiltroNome(nome);
		marcaPage.setFiltroStatus(status);
		marcaPage.filtrar();	
		
		var nomeEncontrado = marcaPage.pegarNomePrimeiroItem();
		
		assertTrue(nomeEncontrado.contains(nome));
	}
	
	@Test(expected = Exception.class)
	public void naoDeveEncontrarResultadosLista() throws IOException {
		marcaPage.setFiltroNome(nome + "----- NOME INCORRETO -----");
		marcaPage.setFiltroStatus(status);
		marcaPage.filtrar();
		
		assertEquals("Atenção\nMarca não encontrada.",marcaPage.getTextoMensagem());
		
		var nomeEncontrado = marcaPage.pegarNomePrimeiroItem();
	}
	
	@Test
	public void deveInativarMarca() throws IOException {
		var ItemInativado = marcaPage.pegarNomePrimeiroItem();
		marcaPage.inativarItem();
		marcaPage.confirmarInativacao();
		assertEquals("Sucesso\nMarca inativada com sucesso!", marcaPage.getTextoMensagem());
		marcaPage.setFiltroNome(ItemInativado);
		marcaPage.setFiltroStatus(false);
		marcaPage.filtrar();
		var nomeEncontrado = marcaPage.pegarNomePrimeiroItem();
		
		assertTrue(nomeEncontrado.contains(ItemInativado));
	}
}
