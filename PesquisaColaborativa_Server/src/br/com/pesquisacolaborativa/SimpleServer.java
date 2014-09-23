package br.com.pesquisacolaborativa;

import java.io.File;

import drakkar.mast.IndexException;
import drakkar.mast.retrieval.LuceneSearchEngine;
import drakkar.mast.retrieval.MinionSearchEngine;
import drakkar.mast.retrieval.SearchEngine;
import drakkar.stern.DrakkarStern;

public class SimpleServer {

	public static void main(String[] args) {
		int status = 0;
		String collectionPath = "collection/";
		String[] config = { "-sp", "11900" };

		DrakkarStern server = DrakkarStern.getInstance(config);
		SearchEngine lucene = new LuceneSearchEngine();
		SearchEngine minion = new MinionSearchEngine();
//		SearchEngine terrier = new TerrierSearchEngine();
// 		SearchEngine indri= new IndriSearchEngine();

		try {
			//By default, the indexes will be created at <application's root>/index/
			lucene.makeIndex(new File(collectionPath));
			minion.makeIndex(new File(collectionPath));
//			terrier.makeIndex(new File(collectionPath));
//			indri.makeIndex(new File(collectionPath));

			lucene.setEnabled(true);
			minion.setEnabled(true);
//			terrier.setEnabled(true);
//			indri.setEnabled(true);

			server.addSearcher(lucene);
			server.addSearcher(minion);
//			server.addSearcher(terrier);
//			server.addSearcher(indri);

			server.start();
			server.waitForShutdown();
		} catch (IndexException ie) {
			System.err.println(ie.getMessage());
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}

		//Clean up
		if (server != null) {
			try {
				server.stop();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}
