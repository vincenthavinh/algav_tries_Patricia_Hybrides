package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import triepatricia.TriePatricia;

public class MesureTemps {

	public static void main(String[] args) {
		
		File folder = new File("Shakespeare");
		File[] listOfFiles = folder.listFiles();
		
		long nbAjouts = 0;
		long nbNanosAjouts = 0;
		
		long nbRecherches = 0;
		long nbNanosRecherches = 0;
		
		long nbSuppressions = 0;
		long nbNanosSuppressions = 0;
		
		for(int i=0; i<10; i++) {
			for(File file : listOfFiles) {
				if (file.isFile()) {
					//System.out.println(file.getName());
					
					TriePatricia p = new TriePatricia();
					
					
					/*Ajouts*/
					try(BufferedReader br = new BufferedReader(new FileReader(file))) {
					    for(String line; (line = br.readLine()) != null; ) {
					    	
					    	long startTime = System.nanoTime();
					    	p.ajout(line);
					    	long stopTime = System.nanoTime();
					    	long elapsedTime = Math.abs(stopTime) - Math.abs(startTime);
					    	nbAjouts++;
					    	nbNanosAjouts += elapsedTime;
					    }
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					/*Recherches*/
					try(BufferedReader br = new BufferedReader(new FileReader(file))) {
					    for(String line; (line = br.readLine()) != null; ) {
					    	
					    	long startTime = System.nanoTime();
					    	p.recherche(line);
					    	long stopTime = System.nanoTime();
					    	long elapsedTime = Math.abs(stopTime) - Math.abs(startTime);
					    	nbRecherches++;
					    	nbNanosRecherches += elapsedTime;
					    }
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*Suppressions*/
					try(BufferedReader br = new BufferedReader(new FileReader(file))) {
					    for(String line; (line = br.readLine()) != null; ) {
					    	
					    	long startTime = System.nanoTime();
					    	p.recherche(line);
					    	long stopTime = System.nanoTime();
					    	long elapsedTime = Math.abs(stopTime) - Math.abs(startTime);
					    	nbSuppressions++;
					    	nbNanosSuppressions += elapsedTime;
					    }
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		long tempsAjout = (long)nbNanosAjouts / nbAjouts;
		long tempsRecherche = (long)nbNanosRecherches / nbRecherches;
		long tempsSuppression = (long)nbNanosSuppressions / nbSuppressions;
		System.out.println("tempsAjout= "+tempsAjout+", tempsRecherche= "+tempsRecherche+", tempsSuppression= "+tempsSuppression);
		
		
	}

}
