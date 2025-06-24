package utils.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import modules.local.tests.TesteCadastroLocais;
import modules.local.tests.TesteListaLocais;
import modules.login.tests.TesteLogin;

@RunWith(Suite.class)
@SuiteClasses({TesteLogin.class, TesteListaLocais.class, TesteCadastroLocais.class})
public class TestSuite {
}
