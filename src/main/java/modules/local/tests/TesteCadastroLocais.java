package modules.local.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
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
	public long id;
	@Parameter(value=2)
	public boolean status;
	@Parameter(value=3)
	public String conteudoMensagemCadastro;
	@Parameter(value=4)
	public String conteudoMensagemEdicao;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		var id = System.currentTimeMillis();
		
		return Arrays.asList(new Object[][] {
			{"Teste novo Ativo ", id, true, "Sucesso\nLocal cadastrado com sucesso!", "Sucesso\nLocal editado com sucesso!"},
			{"Teste novo Inativo ", id, false, "Sucesso\nLocal cadastrado com sucesso!", "Sucesso\nLocal editado com sucesso!"},
			{"Teste novo Ativo ", id, true, "Erro\nJá existe um Local com o mesmo nome!", "Erro\nJá existe um Local com o mesmo nome!"},
			{"Teste novo Ativo ", id, true, "Erro\nJá existe um Local com o mesmo nome!", "Erro\nJá existe um Local com o mesmo nome!"},
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/locais");
	}
	
	@Test
	public void deveCadastrarLocalVerificandoMensagem() throws IOException {
		locaisPage.abrirModalCadastro();
		cadastroPage.setNome(nome + id);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagemCadastro, cadastroPage.getTextoMensagem());
	}
	
	@Test
	public void deveEditarLocalVerificandoMensagem() throws IOException {
	
		locaisPage.abrirModalEdicao(nome, status);
		cadastroPage.setNome(nome + " EDITADO" + id);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagemEdicao, cadastroPage.getTextoMensagem());
	}
	
	@Test
	public void deveBloquearBotaoSalvar() throws IOException {
		locaisPage.abrirModalCadastro();
		cadastroPage.setNome("");
		
		assertTrue(cadastroPage.isBotaoSalvarBloqueado());
		
		cadastroPage.voltar();
	}
	
}
