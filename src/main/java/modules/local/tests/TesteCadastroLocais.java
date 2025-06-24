package modules.local.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
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
	@Parameter(value=2)
	public String conteudoMensagem;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		var id = LocalDateTime.now().getNano();
		
		return Arrays.asList(new Object[][] {
			{"Teste novo Ativo" + id, true, "Sucesso\nLocal cadastrado com sucesso!"},
			{"Teste novo Inativo" + id, false, "Sucesso\nLocal cadastrado com sucesso!"},
			{"Teste novo Ativo" + id, true, "Erro\nJá existe um Local com o mesmo nome!"},
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/locais");
	}
	
	@Test
	public void deveCadastrarLocalVerificandoMensagem() throws IOException {
		locaisPage.abrirModalCadastro();
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagem, cadastroPage.getTextoMensagem());
	}
	
}
