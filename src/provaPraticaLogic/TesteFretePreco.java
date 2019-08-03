package provaPraticaLogic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.JUnit4;

public class TesteFretePreco {

	@Test
	public void testFretePreco() {
		TransportadorasConhecidas tc = new TransportadorasConhecidas();
		Transportadora nova = new Transportadora("Transp. 1", 50.0, "Terrestre", 60);
		tc.addTransportadora(nova);
		nova = new Transportadora("Transp. 2", 200.0, "Aereo", 30);
		tc.addTransportadora(nova);
		nova = new Transportadora("Transp. 2", 75.0, "Terrestre", 59);
		tc.addTransportadora(nova);
		nova = new Transportadora("Transp. 3", 180.0, "Aereo", 33);
		tc.addTransportadora(nova);
		nova = new Transportadora("Transp. 3", 55.0, "Terrestre", 63);
		tc.addTransportadora(nova);
		nova = new Transportadora("Transp. 4", 175.0, "Aereo", 30);
		tc.addTransportadora(nova);
		
		String retorno[] = new String[2];
		String esperado[] = new String[2];
		
		esperado[0] = "Transportadora: Transp. 4, Valor: 67812.5, Prazo: 1937.5 mins\n";
		esperado[1] = "Transportadora: Transp. 1, Valor: 3810.0, Prazo: 762 mins\n";
		
		calculaFrete teste = new FretePreco(3875, "Aereo", tc);
		retorno[0] = teste.getResposta();
		
		teste = new FretePreco(762, "Terrestre", tc);
		retorno[1] = teste.getResposta();
		
		extracted(retorno, esperado);
	}

	private void extracted(String[] retorno, String[] esperado) {
		assertEquals(esperado, retorno);
	}

}
