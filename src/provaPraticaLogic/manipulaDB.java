package provaPraticaLogic;

//Classe abstrata que determina os metodos que as arquiteturas de conexao
//precisam para obterem dados externos e converte-los em objetos do tipo Transportadora
abstract class manipulaBD {

	protected TransportadorasConhecidas retorno = new TransportadorasConhecidas();
	
	protected Transportadora buildDados(String nome, double vpkm, String tt, int tmkm) {
		return new Transportadora(nome, vpkm, tt, tmkm);
	}
	
	public Transportadora getResults() {
		return this.retorno.first();
	}
}
