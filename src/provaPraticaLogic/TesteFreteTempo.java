package provaPraticaLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteFreteTempo {

	@Test
	public void testFreteTempo() {
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
		
		String retorno[] = new String[3];
		String esperado[] = new String[3];
		
		esperado[0] = "Transportadora: Transp. 4, Valor: 35315.0, Prazo: 1009 mins\n";
		esperado[1] = "Transportadora: Transp. 4, Valor: 23625.0, Prazo: 675 mins\n";
		esperado[2] = "Transportadora: Transp. 2, Valor: 10867.5, Prazo: 1424.85 mins\n";
		
		calculaFrete teste = new FreteTempo(2018, "", tc);
		retorno[0] = teste.getResposta();
		
		teste = new FreteTempo(1350, "", tc);
		retorno[1] = teste.getResposta();
		
		teste = new FreteTempo(1449, "Terrestre", tc);
		retorno[2] = teste.getResposta();
		
		extracted(retorno, esperado);
	}

	private void extracted(String[] retorno, String[] esperado) {
		assertEquals(esperado, retorno);
	}

}
