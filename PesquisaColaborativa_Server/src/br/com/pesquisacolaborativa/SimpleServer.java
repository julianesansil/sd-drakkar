package br.com.pesquisacolaborativa;

import drakkar.stern.DrakkarStern;

public class SimpleServer {

	public static void main(String[] args) {
		int status = 0;
		String collectionPath = "collection/";
		String[] config = {"-sp", "11900"};
		DrakkarStern server = DrakkarStern.getInstance(config);

		try {
			server.start();
			server.waitForShutdown();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
