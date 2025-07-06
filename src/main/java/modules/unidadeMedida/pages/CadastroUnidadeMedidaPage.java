package modules.unidadeMedida.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class CadastroUnidadeMedidaPage {
	
	String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-unidade-medida/div[1]/div/div/form/div[2]/button[2]";
	String pathBotaoVoltar = "/html/body/app-root/app-container/main/div/app-unidade-medida/div[1]/div/div/form/div[2]/button[1]";
	String pathSelectStatus = "//*[@id=\"status\"]";
	String pathMensagem = "/html/body/app-root/app-container/main/div/app-unidade-medida/p-toast/div/p-toastitem/div/div/div";
	
	
	private DSL dsl = new DSL();
	
	public void setNome(String valor) {
		dsl.escrever("descricao", valor);
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
	
	public String getTextoMensagem() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}

}
