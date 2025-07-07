package modules.unidadeMedida.tests;

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

import modules.unidadeMedida.pages.ListaUnidadeMedidaPage;
import utils.driver.DriverFactory;
import utils.dsl.DSL;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class TesteListaUnidadeMedida {
	private DSL dsl = new DSL();
	private static ListaUnidadeMedidaPage medidasPage = new ListaUnidadeMedidaPage();
	
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
		dsl.definirUrl("http://35.209.123.161/front/medidas");
	}
	
	
	@Test
	public void deveFiltrarResultadosLista() throws IOException {
		medidasPage.setFiltroDescricao(nome);
		medidasPage.setFiltroStatus(status);
		medidasPage.filtrar();
		
		var nomeEncontrado = medidasPage.pegarNomePrimeiroItem();
		
		assertTrue(nomeEncontrado.contains(nome));
	}
	
	@Test(expected = Exception.class)
	public void naoDeveEncontrarResultadosLista() throws IOException {
		medidasPage.setFiltroDescricao(nome + "----- NOME INCORRETO -----");
		medidasPage.setFiltroStatus(status);
		medidasPage.filtrar();	
		
		assertEquals("Atenção\nMedida não encontrada.",medidasPage.getTextoMensagem());

		var nomeEncontrado = medidasPage.pegarNomePrimeiroItem();
	}
	
	@Test
	public void deveInativarUnidadeMedida() throws IOException {
		var ItemInativado = medidasPage.pegarNomePrimeiroItem();
		medidasPage.inativarItem();
		medidasPage.confirmarInativacao();
		assertEquals("Sucesso\nMedida inativada com sucesso!", medidasPage.getTextoMensagem());
		medidasPage.setFiltroDescricao(ItemInativado);
		medidasPage.setFiltroStatus(false);
		medidasPage.filtrar();
		var nomeEncontrado = medidasPage.pegarNomePrimeiroItem();
		
		assertTrue(nomeEncontrado.contains(ItemInativado));
	}
}
