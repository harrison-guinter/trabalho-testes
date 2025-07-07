package modules.categoria.tests;

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

import modules.categoria.pages.CadastroCategoriaPage;
import modules.categoria.pages.ListaCategoriaPage;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class TesteCadastroCategoria {
	private DSL dsl = new DSL();
	private static ListaCategoriaPage categoriaPage = new ListaCategoriaPage();
	private static CadastroCategoriaPage cadastroPage = new CadastroCategoriaPage();
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public boolean status;
	@Parameter(value=2)
	public String conteudoMensagemCriacao;
	@Parameter(value=3)
	public String conteudoMensagemEdicao;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		var id = LocalDateTime.now().getNano();
		
		return Arrays.asList(new Object[][] {
			{"Teste novo Ativo" + id, true, "Sucesso\nCategoria cadastrada com sucesso!", "Sucesso\nCategoria editada com sucesso!"},
			{"Teste novo Inativo" + id, false, "Sucesso\nCategoria cadastrada com sucesso!", "Sucesso\nCategoria editada com sucesso!"},
			{"Teste novo Ativo" + id, true, "Erro\nJá existe uma Categoria com o mesmo nome!", "Erro\nJá existe uma Categoria com o mesmo nome!"},
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/categorias");
	}
	
	@Test
	public void deveCadastrarCategoriaVerificandoMensagem() throws IOException {
		categoriaPage.abrirModalCadastro();
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagemCriacao, categoriaPage.getTextoMensagem());
	}
	
	@Test
	public void deveEditarCategoriaVerificandoMensagem() throws IOException {
		categoriaPage.editarItem();
		cadastroPage.setNome(nome + "-testeEdicao");
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagemEdicao, categoriaPage.getTextoMensagem());
	}
	
}
