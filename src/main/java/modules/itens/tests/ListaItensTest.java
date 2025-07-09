package modules.itens.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import modules.itens.pages.ListaItensPage;
import modules.local.pages.ListaLocaisPage;
import utils.dsl.DSL;

public class ListaItensTest {

	private DSL dsl = new DSL();
	private static ListaItensPage locaisPage = new ListaItensPage();
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public boolean status;
	
	private String conteudoMensagemInativacao = "Sucesso\nLocal inativado com sucesso!";
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"Teste novo Ativo", true},
			{"Teste novo Inativo", false}
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/locais");
	}
	
	@Test
	public void deveFiltrarResultadosLista() throws IOException, InterruptedException {
		locaisPage.setFiltroNome(nome);
		locaisPage.setFiltroStatus(status);
		locaisPage.filtrar();	
		
		Thread.sleep(100);
		
		var nomeEncontrado = locaisPage.pegarNomePrimeiroItem();
		
		assertTrue(nomeEncontrado.contains(nome));
	}
	
	@Test(expected = Exception.class)
	public void naoDeveEncontrarResultadosLista() {
		locaisPage.setFiltroNome(nome + "----- NOME INCORRETO -----");
		locaisPage.setFiltroStatus(status);
		locaisPage.filtrar();	
		
		var nomeEncontrado = locaisPage.pegarNomePrimeiroItem();
	}
	

	@Test
	public void deveInativar() throws IOException {
		if (status == false) {
			return;
		}
		locaisPage.abrirModalInativacao(nome, status);
		locaisPage.confirmarDesativacao();
		assertEquals(conteudoMensagemInativacao, locaisPage.getTextoMensagem());
	}

}
