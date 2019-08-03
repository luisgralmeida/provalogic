package provaPraticaLogic;

//Classe filha de calculaFrete especifica para encontrar o menor tempo de frete
public class FreteTempo extends calculaFrete{

	public FreteTempo(int distancia, String tipo, TransportadorasConhecidas tc) {
		super(distancia, tipo, tc);
		this.preenche();
		this.ordenadas = this.ordena(this.ordenadas);
		int des = this.empates();
		if (des > 0) {
			double[][] desempate = new double[2][des+1];
			int i;
			for (i = 0; i < des+1; i++) {
				desempate[0][i] = this.ordenadas[0][i];
				desempate[1][i] = this.originais.getTransportadora((int)this.ordenadas[0][i]).getValorpKm()*this.distancia/10;
			}
			this.ordenadas = this.ordena(desempate);
		}
		des = this.empates();
		this.buildResposta(des, this.ordenadas);
	}
	
	//Metodo que calcula um novo array com as posicoes das transportadoras na lista
	//e seus respectivos tempos de entrega
	protected void preenche() {
		Transportadora pointer = this.originais.first();
		int idCount = 0;
		while (pointer != null) {
			this.ordenadas[0][idCount] = idCount;
			if (this.tipo.equals(pointer.tipoTransporte) || this.tipo.equals("")) {
				this.ordenadas[1][idCount] = pointer.getTempoMedioKm()*this.distancia;
			}else {
				this.ordenadas[1][idCount] = Double.POSITIVE_INFINITY;
			}
			idCount++;
			pointer = pointer.next();
		}
	}
	
}
