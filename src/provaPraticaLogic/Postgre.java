package provaPraticaLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import provaPraticaLogic.manipulaBD;

public class Postgre extends manipulaBD {

	//Classe para conexao com banco de dados Postgre
	public Postgre(String nome) {
		Scanner sc = new Scanner(System.in);
		String url;
		String user;
		String pass;
		
		System.out.println("URL do banco de dados:");
		url = sc.nextLine();
		
		System.out.println("Usuario:");
		user = sc.nextLine();
		
		System.out.println("Senha:");
		pass = sc.nextLine();
		
		try {
			Connection con = criarConexao(url,user,pass);
			ResultSet s = con.createStatement().executeQuery("Select * from dadosTransportadoraView");
			double vpkm;
			String tt;
			int tmkm;
			while (s.next()) {
				vpkm = s.getDouble("valor");
				switch (s.getInt("idTipoTransporte")) {
					case 1:
						tt = "Aereo";
						break;
					case 2:
						tt = "Terrestre";
						break;
					default:
						tt = "Não especificado";
						break;
				}
				tmkm = s.getInt("tempo");
				if((vpkm > 0) && (tmkm > 0)) {
					retorno.addTransportadora(buildDados(nome, vpkm, tt, tmkm));
				}else {
					System.out.println(nome+" possui dados incompletos!");
				}
			}
			con.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	public Connection criarConexao(String url, String user, String pass) throws Throwable{
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection(url, user, pass);
		if (conecta != null){
			System.out.println("Conexão efetuada com sucesso!");
			return conecta;
		}
		System.out.println("Conexão falhou, favor checar usuario e senha.");
		return null;
	}
}

