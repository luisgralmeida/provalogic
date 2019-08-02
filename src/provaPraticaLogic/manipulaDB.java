package provaPraticaLogic;

abstract class manipulaBD {

	protected TransportadorasConhecidas retorno = new TransportadorasConhecidas();
	
	protected Transportadora buildDados(String nome, double vpkm, String tt, int tmkm) {
		return new Transportadora(nome, vpkm, tt, tmkm);
	}
	
	public Transportadora getResults() {
		return this.retorno.first();
	}
}
