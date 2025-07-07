package modules.categoria.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class CadastroCategoriaPage {
	
	String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-categoria/div[1]/div/div/form/div[2]/button[2]";
	String pathBotaoVoltar = "/html/body/app-root/app-container/main/div/app-categoria/div[1]/div/div/form/div[2]/button[1]";
	String pathSelectStatus = "//*[@id=\"status\"]";
	
	private DSL dsl = new DSL();
	
	public void setNome(String valor) {
		dsl.escrever("nome", valor);
	}
	
	public void setStatus(boolean ativo) {
		dsl.selecionarComboByPath(pathSelectStatus, ativo ? "A" : "I");
	}
	
	public void salvar() {
		dsl.clicarBotao(pathBotaoSalvar);
	}	
	
	public void voltar() {
		dsl.clicarBotao(pathBotaoVoltar);
	}

}
