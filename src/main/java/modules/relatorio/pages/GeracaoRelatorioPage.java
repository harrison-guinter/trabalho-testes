package modules.relatorio.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import utils.dsl.DSL;

@RunWith(Parameterized.class)
public class GeracaoRelatorioPage {

	private DSL dsl = new DSL();

	// XPaths ou IDs relevantes
	private String pathDataInicial = "dataInicial";
	private String pathDataFinal = "dataFinal";
	private String pathCheckboxPF = "monitoradoPF";
	private String pathCheckboxExercito = "exercito";
	private String pathCategoria = "categoriaId";
	private String pathBotaoGerar = "//button[contains(text(),'Gerar relatório')]";

	// Métodos de interação

	public void setDataInicial(String data) {
		dsl.escrever(pathDataInicial, data);
	}

	public void setDataFinal(String data) {
		dsl.escrever(pathDataFinal, data);
	}

	public void selecionarCategoria(String categoriaNome) {
		dsl.selecionarCombo(pathCategoria, categoriaNome);
	}

	public void selecionarCategoriaPorValor(String valor) {
		dsl.selecionarCombo(pathCategoria, valor);
	}

	public void marcarMonitoramentoPF() {
		dsl.clicarCheck(pathCheckboxPF);
	}

	public void marcarMonitoramentoExercito() {
		dsl.clicarCheck(pathCheckboxExercito);
	}

	public void gerarRelatorio() {
		dsl.clicarBotao(pathBotaoGerar);
	}

	public boolean isBotaoGerarHabilitado() {
		return dsl.isEnabled(By.xpath(pathBotaoGerar));
	}
}
