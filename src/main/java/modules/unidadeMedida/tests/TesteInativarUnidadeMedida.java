package modules.unidadeMedida.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;

import modules.unidadeMedida.pages.ExcluirUnidadeMedidaPage;
import modules.unidadeMedida.pages.ListaUnidadeMedidaPage;
import utils.dsl.DSL;

public class TesteInativarUnidadeMedida {
	private DSL dsl = new DSL();
	private static ListaUnidadeMedidaPage medidasPage = new ListaUnidadeMedidaPage();
	private static ExcluirUnidadeMedidaPage exclusaoPage = new ExcluirUnidadeMedidaPage();
	
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/medidas");
	}
		
	/*@Test
	public void deveCancelarExclusao() throws IOException {
		medidasPage.excluirItem();
		exclusaoPage.voltar();
	}*/
	
	@Test
	public void deveInativarUnidadeMedidaVerificandoMensagem() throws IOException {
		medidasPage.excluirItem();
		exclusaoPage.salvar();
		assertEquals("Sucesso\nMedida inativada com sucesso!", exclusaoPage.getTextoMensagem());
	}
	
}
