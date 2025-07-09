package modules.itens.tests;

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

import modules.itens.pages.CadastroItensPage;
import modules.itens.pages.ListaItensPage;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class CadastroItensTest {

	private DSL dsl = new DSL();
	private static ListaItensPage listaPage = new ListaItensPage();
	private static CadastroItensPage cadastroPage = new CadastroItensPage();

	@Parameter
	public String codigo;

	@Parameter(1)
	public String nome;

	@Parameter(2)
	public boolean statusAtivo;

	@Parameter(3)
	public String unidadeMedida;

	@Parameter(4)
	public String categoria;

	@Parameter(5)
	public String quantidadeMinima;

	@Parameter(6)
	public boolean monitoradoPF;

	@Parameter(7)
	public boolean monitoradoExercito;

	@Parameter(8)
	public String observacoes;

	@Parameter(9)
	public String mensagemCadastroEsperada;

	@Parameter(10)
	public String mensagemEdicaoEsperada;

	@Parameters
	public static Collection<Object[]> getCollection() {
		var id = LocalDateTime.now().getNano();

		return Arrays.asList(new Object[][] {
			{ "COD" + id, "Item de Teste Ativo" + id, true, "471", "458", "5", true, false, "Observação teste", 
				"Sucesso\nItem cadastrado com sucesso!", "Sucesso\nItem editado com sucesso!" },
			{ "COD" + id, "Item de Teste Inativo" + id, false, "471", "458", "0", false, true, "", 
				"Erro\nError: Já existe um Elemento com o mesmo código!", "Erro\nError: Já existe um Elemento com o mesmo nome!" }
		});
	}

	@Before
	public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/cadastro-item");
	}
	
	public void irParaPaginaLista() {
		dsl.definirUrl("http://35.209.123.161/front/itens");
	}

	@Test
	public void deveCadastrarItemVerificandoMensagem() throws IOException {
		
		cadastroPage.setCodigo(codigo);
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(statusAtivo ? "A" : "I");
		cadastroPage.setUnidadeMedida(unidadeMedida);
		cadastroPage.setCategoria(categoria);
		cadastroPage.setQuantidadeMinima(quantidadeMinima);
		cadastroPage.setMonitoradoPF();
		cadastroPage.setMonitoradoExercito();
		cadastroPage.setObservacoes(observacoes);
		cadastroPage.salvar();

		assertEquals(mensagemCadastroEsperada, cadastroPage.getTextoMensagem());
	}

	@Test
	public void deveEditarItemVerificandoMensagem() throws IOException {
	
		irParaPaginaLista();
		listaPage.editarItemExistente(); 
		cadastroPage.setNome(nome + "-editado");
		cadastroPage.setStatus(statusAtivo ? "A" : "I");
		cadastroPage.salvar();

		assertEquals(mensagemEdicaoEsperada, cadastroPage.getTextoMensagem());
	}
}
