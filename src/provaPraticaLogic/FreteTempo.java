package provaPraticaLogic;

public class FreteTempo extends calculaFrete{

	public FreteTempo(int distancia, String tipo, TransportadorasConhecidas tc) {
		super(distancia, tipo, tc);
		this.preenche();
		this.ordenadas = this.ordena(this.ordenadas);
		int des = this.desempate();
		if (des > 1) {
			Transportadora pointer;
			double desempateTransp[][] = new double[2][des];
			int i, j;
			for (i = 0; i <=des; i++) {
				pointer = this.originais.first();
				for (j = 0; j < this.ordenadas[0][i]; j++) {
					pointer = pointer.next();
				}
				desempateTransp[0][i] = this.ordenadas[0][i]; 
				desempateTransp[1][i] = pointer.getValorpKm();
			}
			this.ordenadas = this.ordena(desempateTransp);
			des = this.desempate();
		}
		this.buildResposta(des, this.ordenadas);
	}
	
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
