package provaPraticaLogic;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean finaliza = false; //Variavel de finalização do programa
		Scanner sc = new Scanner(System.in); //variavel de entrada de dados de usuário para o menu
		int escolha = 0;
		TransportadorasConhecidas lista = new TransportadorasConhecidas();
		
		System.out.println("Bem-vindo ao sistema!");
		while (!finaliza) {
			System.out.println("Menu - Selecione uma opção:");
			System.out.println("1 - Adicione uma transportadora ao sistema");
			System.out.println("2 - Calcule a melhor transportadora para um frete");
			System.out.println("3 - Listar transportadoras");
			System.out.println("4 - Fianlizar programa");
			try {
				escolha = Integer.valueOf(sc.nextLine());
				switch (escolha) {
					case 1:
						addTransportadora(lista);
						break;
					case 2:
						calcFrete(lista);
						break;
					case 3:
						Transportadora pointer = lista.first();
						while (pointer != null) {
							System.out.println(pointer.getNome()+" "+pointer.getTempoMedioKm()+" "+
						pointer.getTipoTransporte()+" "+pointer.getValorpKm());
							pointer = pointer.next();
						}
						break;
					case 4:
						finaliza = true;
						break;
				}
			}catch (NumberFormatException e){
				System.out.println("Valor inválido!");
			}
		}
		sc.close();
		System.out.println("Programa finalizado com sucesso!");
	}
	
	public static void addTransportadora(TransportadorasConhecidas lista) {
		Scanner sc = new Scanner(System.in);
		String nome;
		String tipoBD;
		manipulaBD banco = null;
		
		System.out.println("Qual o nome da transportadora?");
		nome = sc.nextLine();
		
		System.out.println("Postgre ou REST?");
		tipoBD = sc.nextLine();
		
		if (tipoBD.equals("Postgre")) {
			banco = new Postgre(nome);
		}else {
			if(tipoBD.equals("REST")) {
				
			}
		}
		if (banco != null) {
			lista.addTransportadora(banco.getResults());
	
		}
	}
	
	public static void calcFrete(TransportadorasConhecidas lista) {
		Scanner sc = new Scanner(System.in);
		TransportadorasConhecidas ordenadas = new TransportadorasConhecidas();
		int prioridade; //1 - preco | 2 - tempo
		String trajeto;
		int distancia;
		String tipo = "";
		calculaFrete cf = null;
		
		System.out.println("Informe o Trajeto:");
		trajeto = sc.nextLine();
		
		System.out.println("Informe a Distancia (em KM):");
		distancia = Integer.valueOf(sc.nextLine());
		
		System.out.println("Informe o Tipo de Transporte:");
		System.out.println("1 - Aereo");
		System.out.println("2 - Terrestre");
		System.out.println("3 - Indiferente");
		int i = Integer.valueOf(sc.nextLine());
		switch (i){
			case 1:
				tipo = "Aereo";
			break;
			case 2:
				tipo = "Terrestre";
			break;
		}
		
		System.out.println("Informe a Prioridade:");
		System.out.println("1 - Preco");
		System.out.println("2 - Tempo");
		prioridade = Integer.valueOf(sc.nextLine());
		
		switch (prioridade){
			case 1:
				cf = new FretePreco(distancia, tipo, lista);
				break;
			case 2:
				cf = new FreteTempo(distancia, tipo, lista);
				break;
			default:
				System.out.println("Comando não reconhecido.");
		}
		if (cf != null) {
			System.out.println(cf.getResposta());
		}
	}
}
