package provaPraticaLogic;

//Classe filha de calculaFrete especifica para encontrar o menor preco
public class FretePreco extends calculaFrete{

	public FretePreco(int distancia, String tipo, TransportadorasConhecidas tc) {
		super(distancia, tipo, tc);
		this.preenche();
		this.ordenadas = this.ordena(this.ordenadas);
		int des = this.empates();
		if (des > 0) {
			double[][] desempate = new double[2][des+1];
			int i;
			for (i = 0; i < des+1; i++) {
				desempate[0][i] = this.ordenadas[0][i];
				desempate[1][i] = this.originais.getTransportadora((int)this.ordenadas[0][i]).getTempoMedioKm()*this.distancia;
			}
			this.ordenadas = this.ordena(desempate);
		}
		des = this.empates();
		this.buildResposta(des, this.ordenadas);
	}
	
	//Metodo que calcula um novo array com as posicoes das transportadoras na lista
	//e seus respectivos precos
	protected void preenche() {
		Transportadora pointer = this.originais.first();
		int idCount = 0;
		while (pointer != null) {
			this.ordenadas[0][idCount] = idCount;
			if (this.tipo.equals(pointer.tipoTransporte) || this.tipo.equals("")) {
				this.ordenadas[1][idCount] = pointer.getValorpKm()*this.distancia/10;
			}else {
				this.ordenadas[1][idCount] = Double.POSITIVE_INFINITY;
			}
			idCount++;
			pointer = pointer.next();
		}
	}
	
}