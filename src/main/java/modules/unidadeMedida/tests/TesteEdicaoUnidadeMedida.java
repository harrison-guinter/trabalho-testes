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
public class TesteEdicaoUnidadeMedida {
	private DSL dsl = new DSL();
	private static ListaUnidadeMedidaPage locaisPage = new ListaUnidadeMedidaPage();
	private static CadastroUnidadeMedidaPage cadastroPage = new CadastroUnidadeMedidaPage();
	
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
			{"Teste edicao Ativo" + id, true, "Sucesso\nMedida editada com sucesso!!"},
			{"Teste edicao Inativo" + id, false, "Sucesso\nMedida editada com sucesso!"},
			{"Teste edicao Ativo" + id, true, "Erro\nJá existe uma Unidade de Medida com a mesma descrição!"},
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/medidas");
	}
	
	@Test
	public void deveCadastrarUnidadeMedidaVerificandoMensagem() throws IOException {
		locaisPage.editarItem();
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagem, cadastroPage.getTextoMensagem());
	}
	
}
