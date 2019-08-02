package provaPraticaLogic;

public class Transportadora {
	
	protected String nome; //Nome da transportadora
	protected double valorpKm; //Valor do frete por KM
	protected String tipoTransporte; //Tipo do transporte (A - Area / T - Terrestre)
	protected int tempoMedioKm; //Tempo medio por KM
	protected Transportadora nextTrans = null; //Proxima transportadora da lista
	
	public Transportadora(String nome, double vpkm, String tt, int tmkm) {
		this.nome = nome;
		this.valorpKm = vpkm;
		this.tipoTransporte = tt;
		this.tempoMedioKm = tmkm;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public double getValorpKm() {
		return this.valorpKm;
	}
	
	public String getTipoTransporte() {
		return this.tipoTransporte;
	}
	
	public int getTempoMedioKm() {
		return this.tempoMedioKm;
	}
	
	public Transportadora next() {
		return this.nextTrans;
	}
	
	public void addNext(Transportadora Trans) {
		this.nextTrans = Trans;
	}
}
