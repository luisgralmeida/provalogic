package provaPraticaLogic;

import java.text.DecimalFormat;

//Classe abstrata com os metodos para calculo do frete
abstract class calculaFrete {

	protected TransportadorasConhecidas originais;
	protected double ordenadas[][]; // [Posição na lista][Valor/Tempo do frete]
	protected int distancia;
	protected String tipo;
	protected String resposta = "";
	
	public calculaFrete(int dist, String tipo, TransportadorasConhecidas tc) {
		this.distancia = dist;
		this.tipo = tipo;
		this.originais = tc;
		this.ordenadas = new double[2][tc.contaTransportadoras()];
	}
	
	//Metodo de ordenacao Bubble Sort
	protected double[][] ordena(double selection[][]) {
		double temp[] = new double[2]; //temp[posicao da transportadora][Preco ou Tempo]
		int i = 0;
		boolean fim = true;
		while(fim) {
			fim = false;
			for (i = 0; i < selection[1].length-1; i++){
				if (selection[1][i] > selection[1][i+1]) {
					temp[0] = selection[0][i];
					temp[1] = selection[1][i];
					selection[0][i] = selection[0][i+1];
					selection[1][i] = selection[1][i+1];
					selection[0][i+1] = temp[0];
					selection[1][i+1] = temp[1];
					fim = true;
				}
			}
		}
		return selection;
	}
	
	//Metodo que conta os resultados iguais ao melhor
	protected int empates() {
		int retorno = 0;
		int i;
		for (i = 1; i < this.ordenadas[0].length; i++) {
			if (this.ordenadas[1][0] == this.ordenadas[1][i]) {
				retorno++;
			}
		}
		return retorno;
	}
	
	//Metodo construtor da string de resposta final do calculo do frete
	protected void buildResposta(int count, double[][] melhores) {
		Transportadora pointer;
		int t;
		for (t = 0; t < count+1; t++ ){
			pointer = this.originais.getTransportadora((int)melhores[0][t]);
			this.resposta = this.resposta.concat("Transportadora: "+pointer.getNome());
			this.resposta = this.resposta.concat(", Valor: "+(pointer.getValorpKm()*this.distancia/10));
			this.resposta = this.resposta.concat(", Prazo: "+converteMin(pointer.getTempoMedioKm()*this.distancia));
			this.resposta = this.resposta.concat(" mins\n");
		}		
	}
	
	//Conversao de segundos para minutos
	protected String converteMin(double s) {
		s = s/60;
		return new DecimalFormat("#.##").format(s);
	}
	
	public String getResposta() {
		return this.resposta;
	}
}
