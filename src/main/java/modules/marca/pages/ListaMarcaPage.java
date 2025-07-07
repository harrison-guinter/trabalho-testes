package modules.marca.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class ListaMarcaPage {
	
	String pathPrimeiroItemNome = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr[1]/td[2]";
	String pathBotaoFiltrar= "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[2]";
	String pathBotaoLimpar = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[1]";
	String pathBotaoCadastrar = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[3]";
	String pathSelectStatus = "//*[@id=\"filtro_status\"]";
	String pathMensagem = "/html/body/app-root/app-container/main/div/app-marca/p-toast/div/p-toastitem/div/div";
	String pathBotaoEditar = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr[1]/td[3]/i";
	String pathBotaoInativar = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr[1]/td[4]/i";
	String pathInativarBotaoSalvar = "/html/body/app-root/app-container/main/div/app-marca/app-confirm-modal/div/div/div/div[3]/button[2]";
	String pathInativarBotaoVoltar = "/html/body/app-root/app-container/main/div/app-marca/app-confirm-modal/div/div/div/div[3]/button[1]";
	
	private DSL dsl = new DSL();
	
	public void setFiltroNome(String valor) {
		dsl.escrever("filtro_nome", valor);
	}
	
	public void setFiltroStatus(boolean ativo) {
		dsl.selecionarComboByPath(pathSelectStatus, ativo ? "A" : "I");
	}
	
	public void filtrar() {
		dsl.clicarBotao(pathBotaoFiltrar);
	}
	
	public void limpar() {
		dsl.clicarBotao(pathBotaoLimpar);
	}	
	
	public void abrirModalCadastro() {
		dsl.clicarBotao(pathBotaoCadastrar);
	}	
	
	public void inativarItem() {
		dsl.clicarBotao(pathBotaoInativar);
	}	
	
	public void editarItem() {
		dsl.clicarBotao(pathBotaoEditar);
	}
	
	public String pegarNomePrimeiroItem() {
		return dsl.obterTexto(By.xpath(pathPrimeiroItemNome));
	}
	
	public String getTextoMensagem() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}
	
	public void confirmarInativacao() {
		dsl.clicarBotao(pathInativarBotaoSalvar);
	}
	
	public void cancelarInativacao() {
		dsl.clicarBotao(pathInativarBotaoVoltar);
	}
	
}
