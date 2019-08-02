package provaPraticaLogic;

public class TransportadorasConhecidas {
	//Lista de transportadoras
	
	protected Transportadora primeiraTrans = null; //Transportadoras conhecidas
	protected Transportadora pointerTrans = null;//Ponteiro da lista
	
	public void addTransportadora(Transportadora novaTrans) {
		if (this.primeiraTrans == null){
			this.primeiraTrans = novaTrans;
			this.resetaPointer();
		}else {
			while (this.pointerTrans.next() != null) {
				this.pointerTrans = this.pointerTrans.next();
			}
			this.pointerTrans.addNext(novaTrans);
			resetaPointer();
		}
	}
	
	public Transportadora first() {
		return this.primeiraTrans;
	}
	
	public int contaTransportadoras() {
		int result = 0;
		this.resetaPointer();
		while(this.pointerTrans != null) {
			result++;
			this.pointerTrans = this.next();
		}
		this.resetaPointer();
		return result;
	}
	
	protected Transportadora next() {
		return this.pointerTrans.next();
	}
	
	protected void resetaPointer() {
		this.pointerTrans = this.primeiraTrans;
	}
}
