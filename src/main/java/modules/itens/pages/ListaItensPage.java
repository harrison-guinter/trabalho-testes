package modules.itens.pages;

import org.openqa.selenium.By;

import utils.dsl.DSL;

public class ListaItensPage {

	private DSL dsl = new DSL();

	private final String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/a";
	private final String pathBotaoEditarPrimeiroItem = "/html/body/app-root/app-container/main/div/app-lista-elemento/div/table/tbody/tr[1]/td[4]/i";
	private final String pathMensagem = "/html/body/app-root/app-container/main/div/app-item/p-toast/div";

	public void abrirFormularioNovoItem() {
		dsl.clicarBotao(pathBotaoNovo);
	}

	public void editarItemExistente() {
		dsl.clicarBotao(pathBotaoEditarPrimeiroItem);
	}

	public String getTextoMensagem() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}
}
