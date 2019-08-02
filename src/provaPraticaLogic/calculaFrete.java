package provaPraticaLogic;

import java.text.DecimalFormat;

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
	
	protected double[][] ordena(double selection[][]) {
		double temp[] = new double[2];
		int i, j, min;
		
		for (i = 0; i < selection.length-1; i++){
			min = i;
			for (j = i+1; j < selection.length-1; j++){
			    
				if (selection[1][j] < selection[1][min]) {
					min = j;
			    }
			}
			
			temp[0] = selection[0][i];
			temp[1] = selection[1][i];
			selection[0][i] = selection[0][min];
			selection[1][i] = selection[1][min];
			selection[0][min] = temp[0];
			selection[1][min] = temp[1];
		}
		return selection;
	}
	
	protected int desempate() {
		int i;
		
		for (i = 0; i < this.ordenadas.length-1; i++) {
			if (this.ordenadas[1][i] < this.ordenadas[1][i]) {
				return i;
			}
		}
		return i;
	}
	
	protected void buildResposta(int count, double[][] melhores) {
		Transportadora pointer;
		int i, j;
		for (i = 0; i <count; i++) {
			pointer = this.originais.first();
			for (j = 0; j < this.ordenadas[0][i]; j++) {
				pointer = pointer.next();
			}
			this.resposta = this.resposta.concat("Transportadora: "+pointer.getNome()+" |"+"Valor total: "+
			new DecimalFormat("#.##").format((pointer.getTempoMedioKm()*distancia/10))
			+" |Tempo total: "+this.converteMin((pointer.getTempoMedioKm()*distancia))+" mins");
		}
	}
	
	protected String converteMin(double s) {
		s = s/60;
		return new DecimalFormat("#.##").format(s);
	}
	
	public String getResposta() {
		return this.resposta;
	}
}
