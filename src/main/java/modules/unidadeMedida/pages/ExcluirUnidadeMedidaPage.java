package modules.unidadeMedida.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class ExcluirUnidadeMedidaPage {
	
	String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-unidade-medida/app-confirm-modal/div/div/div/div[3]/button[2]";
	String pathBotaoVoltar = "/html/body/app-root/app-container/main/div/app-unidade-medida/app-confirm-modal/div/div/div/div[3]/button[1]";
	String pathMensagem = "/html/body/app-root/app-container/main/div/app-unidade-medida/p-toast/div/p-toastitem/div/div/div";
	
	
	private DSL dsl = new DSL();
	
		
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
