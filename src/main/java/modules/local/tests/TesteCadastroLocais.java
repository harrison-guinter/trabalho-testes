package modules.local.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import modules.local.pages.CadastroLocaisPage;
import modules.local.pages.ListaLocaisPage;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class TesteCadastroLocais {
	private DSL dsl = new DSL();
	private static ListaLocaisPage locaisPage = new ListaLocaisPage();
	private static CadastroLocaisPage cadastroPage = new CadastroLocaisPage();
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public boolean status;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"teste novo", true}
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/locais");
	}
	
	@Test
	public void deveCadastrarLocal() throws IOException {
		locaisPage.abrirModalCadastro();
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals("Local cadastrado com sucesso!", cadastroPage.getTextoMensagemSucesso());
		
	}
}
