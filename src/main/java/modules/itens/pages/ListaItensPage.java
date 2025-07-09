package modules.itens.pages;

import org.openqa.selenium.By;

import utils.dsl.DSL;

public class ListaItensPage {

	private DSL dsl = new DSL();

	String pathBotaoConfirmarDesativacao = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[2]";
	String pathBotaoInativarPrimeiroItem = "/html/body/app-root/app-container/main/div/app-lista-elemento/div[2]/table/tbody/tr/td[4]/i";
	String pathBotaoFiltrar= "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[3]/div/button[2]";
	String pathPrimeiroItemNome = "/html/body/app-root/app-container/main/div/app-lista-elemento/div/table/tbody/tr[1]/td[3]";
	String pathSelectStatus = "//*[@id=\"filtro_status\"]";
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

	
	public void setFiltroNome(String valor) {
		dsl.escrever("filtro_nome", valor);
	}
	
	public void setFiltroStatus(boolean ativo) {
		dsl.selecionarComboByPath(pathSelectStatus, ativo ? "A" : "I");
	}
	
	public String pegarNomePrimeiroItem() {
		return dsl.obterTexto(By.xpath(pathPrimeiroItemNome));
	}
	
	public void filtrar() {
		dsl.clicarBotao(pathBotaoFiltrar);
	}
	public void abrirModalInativacao(String nome, boolean status) {
		setFiltroNome(nome);
		setFiltroStatus(status);
		
		filtrar();

		dsl.clicarBotao(pathBotaoInativarPrimeiroItem);
	}

	public void confirmarDesativacao() {
		dsl.clicarBotao(pathBotaoConfirmarDesativacao);
	}
}
