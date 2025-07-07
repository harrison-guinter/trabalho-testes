package modules.unidadeMedida.tests;

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

import modules.unidadeMedida.pages.CadastroUnidadeMedidaPage;
import modules.unidadeMedida.pages.ListaUnidadeMedidaPage;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class TesteCadastroUnidadeMedida {
	private DSL dsl = new DSL();
	private static ListaUnidadeMedidaPage unidadeMedidaPage = new ListaUnidadeMedidaPage();
	private static CadastroUnidadeMedidaPage cadastroPage = new CadastroUnidadeMedidaPage();
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public boolean status;
	@Parameter(value=2)
	public String conteudoMensagemCadastro;
	@Parameter(value=3)
	public String conteudoMensagemEdicao;
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		var id = LocalDateTime.now().getNano();
		
		return Arrays.asList(new Object[][] {
			{"Teste novo Ativo" + id, true, "Sucesso\nMedida cadastrada com sucesso!", "Sucesso\nMedida editada com sucesso!"},
			{"Teste novo Inativo" + id, false, "Sucesso\nMedida cadastrada com sucesso!", "Sucesso\nMedida editada com sucesso!"},
			{"Teste novo Ativo" + id, true, "Erro\nJá existe uma Unidade de Medida com a mesma descrição!", "Erro\nJá existe uma Unidade de Medida com a mesma descrição!"},
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/medidas");
	}
	
	@Test
	public void deveCadastrarUnidadeMedidaVerificandoMensagem() throws IOException {
		unidadeMedidaPage.abrirModalCadastro();
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagemCadastro, unidadeMedidaPage.getTextoMensagem());
	}
	
	@Test
	public void deveEditarUnidadeMedidaVerificandoMensagem() throws IOException {
		unidadeMedidaPage.editarItem();
		cadastroPage.setNome(nome + "-testeEdicao");
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagemEdicao, unidadeMedidaPage.getTextoMensagem());
	}
	
}
