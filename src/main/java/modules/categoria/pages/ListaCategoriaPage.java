package modules.categoria.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class ListaCategoriaPage {
	
	String pathPrimeiroItemNome = "/html/body/app-root/app-container/main/div/app-categoria/div[2]/table/tbody/tr[1]/td[2]";
	String pathBotaoFiltrar= "/html/body/app-root/app-container/main/div/app-categoria/form/div/div[3]/div/button[2]";
	String pathBotaoLimpar = "/html/body/app-root/app-container/main/div/app-categoria/form/div/div[3]/div/button[1]";
	String pathBotaoCadastrar = "/html/body/app-root/app-container/main/div/app-categoria/form/div/div[3]/div/button[3]";
	String pathBotaoInativar = "/html/body/app-root/app-container/main/div/app-categoria/div[2]/table/tbody/tr[1]/td[4]/i";
	String pathSelectStatus = "//*[@id=\"filtro_status\"]";
	String pathMensagem = "/html/body/app-root/app-container/main/div/app-categoria/p-toast/div/p-toastitem/div/div/div";
	
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
	
	public void excluirItem() {
		dsl.clicarBotao(pathBotaoInativar);
	}	
	
	public String pegarNomePrimeiroItem() {
		return dsl.obterTexto(By.xpath(pathPrimeiroItemNome));
	}
	
	public String getTextoMensagem() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}
	
}
