package utils.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import modules.itens.tests.CadastroItensTest;

import modules.local.tests.TesteListaLocais;
import modules.login.tests.TesteLogin;
import modules.relatorio.tests.GeracaoRelatorioTest;
import modules.unidadeMedida.tests.TesteCadastroUnidadeMedida;
import modules.unidadeMedida.tests.TesteListaUnidadeMedida;

@RunWith(Suite.class)
@SuiteClasses({
	TesteLogin.class, 
//	TesteListaLocais.class, 
//	TesteCadastroLocais
	CadastroItensTest.class,
//	TesteCadastroUnidadeMedida.class,
//	TesteListaUnidadeMedida.class,
//	GeracaoRelatorioTest.class,
})
public class TestSuite {
	
}
