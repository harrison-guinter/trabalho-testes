package utils.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import modules.categoria.tests.TesteCadastroCategoria;
import modules.categoria.tests.TesteListaCategoria;
import modules.itens.tests.CadastroItensTest;
import modules.itens.tests.ListaItensTest;
import modules.local.tests.TesteCadastroLocais;
import modules.local.tests.TesteListaLocais;
import modules.login.tests.TesteLogin;
import modules.marca.tests.TesteCadastroMarca;
import modules.marca.tests.TesteListaMarca;
import modules.relatorio.tests.GeracaoRelatorioTest;
import modules.unidadeMedida.tests.TesteCadastroUnidadeMedida;
import modules.unidadeMedida.tests.TesteListaUnidadeMedida;

@RunWith(Suite.class)
@SuiteClasses({
	TesteLogin.class, 
	TesteListaLocais.class, 
	TesteCadastroLocais.class,
	TesteCadastroUnidadeMedida.class,
	TesteListaUnidadeMedida.class,
	TesteCadastroMarca.class,
	TesteListaMarca.class,
	TesteCadastroCategoria.class,
	TesteListaCategoria.class,
	GeracaoRelatorioTest.class,
	CadastroItensTest.class,
	ListaItensTest.class
})
public class TestSuite {
	
}
