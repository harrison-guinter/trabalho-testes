package modules.local.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class ListaLocaisPage {
	
	String pathBotaoFiltrar= "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[2]";
	String pathBotaoLimpar = "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[1]";
	
	private DSL dsl = new DSL();
	
	public void setFiltroNome(String valor) {
		dsl.escrever("filtro_nome", valor);
	}
	
	public void setFiltroStatus(boolean ativo) {
		dsl.selecionarCombo("filtro_status", ativo ? "A" : "I");
	}
	
	public void filtrar() {
		dsl.clicarBotao(pathBotaoFiltrar);
	}	
	
	public void limpar() {
		dsl.clicarBotao(pathBotaoLimpar);
	}	
}
