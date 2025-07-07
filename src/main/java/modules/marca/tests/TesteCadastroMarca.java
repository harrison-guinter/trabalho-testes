package modules.marca.tests;

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

import modules.marca.pages.CadastroMarcaPage;
import modules.marca.pages.ListaMarcaPage;
import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class TesteCadastroMarca {
	private DSL dsl = new DSL();
	private static ListaMarcaPage marcaPage = new ListaMarcaPage();
	private static CadastroMarcaPage cadastroPage = new CadastroMarcaPage();
	
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
			{"Teste novo Ativo" + id, true, "Sucesso\nMarca cadastrada com sucesso!"},
			{"Teste novo Inativo" + id, false, "Sucesso\nMarca cadastrada com sucesso!"},
			{"Teste novo Ativo" + id, true, "Erro\nJá existe uma Marca com o mesmo nome!"},
		});
	}
	
	@Before
    public void irParaPagina() {
		dsl.definirUrl("http://35.209.123.161/front/marcas");
	}
	
	@Test
	public void deveCadastrarLocalVerificandoMensagem() throws IOException {
		marcaPage.abrirModalCadastro();
		cadastroPage.setNome(nome);
		cadastroPage.setStatus(status);
		cadastroPage.salvar();
		assertEquals(conteudoMensagem, marcaPage.getTextoMensagem());
	}
	
}
