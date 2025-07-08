package modules.itens.pages;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import utils.dsl.DSL;

import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CadastroItensPage {

    private DSL dsl = new DSL();

    private final String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-item/div[1]/div/div[2]/div[2]/button[2]";
    private final String pathBotaoVoltar = "/html/body/app-root/app-container/main/div/app-item/div[1]/div/div[2]/div[2]/a";
    private final String pathMensagem = "/html/body/app-root/app-container/main/div/app-item/p-toast/div";

    private final String pathStatus = "//*[@id='status']";
    private final String pathCodigo = "//*[@id=\"codigo\"]";
    private final String pathNome = "nome";
    private final String pathUnidadeMedida = "//*[@id='unidadeMedidaId']";
    private final String pathCategoria = "//*[@id='categoriaId']";
    private final String pathQuantidadeMinima = "quantidadeMinima";
    private final String pathMonitoradoPF = "monitoradoPF";
    private final String pathMonitoradoExercito = "monitoradoExercito";
    private final String pathObservacoes = "observacoes";

    public void setStatus(String valor) {
        dsl.selecionarComboByPath(pathStatus, valor);
    }

    public void setCodigo(String valor) {
        dsl.escrever(By.xpath(pathCodigo), valor);
    }

    public void setNome(String valor) {
        dsl.escrever(pathNome, valor);
    }

    public void setUnidadeMedida(String valor) {
        dsl.selecionarComboByPath(pathUnidadeMedida, valor);
    }

    public void setCategoria(String valor) {
        dsl.selecionarComboByPath(pathCategoria, valor);
    }

    public void setQuantidadeMinima(String valor) {
        dsl.escrever(pathQuantidadeMinima, valor);
    }

    public void setMonitoradoPF() {
        dsl.clicarCheck(pathMonitoradoPF);
    }

    public void setMonitoradoExercito() {
        dsl.clicarCheck(pathMonitoradoExercito);
    }

    public void setObservacoes(String valor) {
        dsl.escrever(pathObservacoes, valor);
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

    public boolean isBotaoSalvarBloqueado() {
        return !dsl.isEnabled(By.xpath(pathBotaoSalvar));
    }
}
