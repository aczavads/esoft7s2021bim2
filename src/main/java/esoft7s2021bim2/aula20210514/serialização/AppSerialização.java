package esoft7s2021bim2.aula20210514.serialização;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppSerialização {
	
	public static void main(String[] args) {
		
		ArtigoCientífico artigoBom = new ArtigoCientífico("Utilizando algoritmos genéticos na geração de casos de teste: um estudo de caso", 18);
		
		//serializarArtigo(artigoBom, "d:/artigoBom.ser");
		
		ArtigoCientífico recuperado = deserializarArtigo("d:/artigoBom.ser");
		
		System.out.println(recuperado.getTítulo() + ",  " + recuperado.getQuantidadeDePáginas());
		
		System.out.println("Foi.");
	}

	private static ArtigoCientífico deserializarArtigo(String nomeDoArquivo) {
		ArtigoCientífico recuperado = null;
		
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File(nomeDoArquivo)));
			recuperado = (ArtigoCientífico) input.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return recuperado;
	}

	private static void serializarArtigo(ArtigoCientífico artigo, String nomeDoArquivo) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(nomeDoArquivo)));
			output.writeObject(artigo);
			output.flush();
			output.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
