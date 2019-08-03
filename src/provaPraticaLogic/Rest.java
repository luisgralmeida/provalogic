package provaPraticaLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Rest extends manipulaBD{

	//Classe para obtencao de dados por metodo REST
	public Rest(String nome) {
		Scanner sc = new Scanner(System.in);
		String link, leitura;
		String[] output;
		URL url;
		
		System.out.println("URL do banco de dados:");
		link = sc.nextLine();
		
		try {
			url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "plain/text");
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			leitura = br.readLine();
			output = leitura.split(":");
			
			double vpkm;
			String tt;
			int tmkm;
			int numeroEntradas;
			
			for (numeroEntradas = output.length; numeroEntradas > 0; numeroEntradas = numeroEntradas-6){		
				vpkm = Integer.valueOf(output[numeroEntradas-1]);
				tmkm = Integer.valueOf(output[numeroEntradas-3]);
				if (output[numeroEntradas-5].equals("A")) {
					tt = "Aereo";
				}else {
					if (output[numeroEntradas-5].equals("T")) {
						tt = "Terrestre";
					}else {
						tt = "Nao especificado";
					}
				}
				if((vpkm > 0) && (tmkm > 0)) {
					retorno.addTransportadora(buildDados(nome, vpkm, tt, tmkm));
				}else {
					System.out.println(nome+" possui dados incompletos!");
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}