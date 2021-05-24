package esoft7s2021bim2.aula20210524.cópiaDeDiretórios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AppCópiaDeDiretórios {
	
	public static void main(String[] args) throws Exception {
		String nomeDirOrigem = "c:/qualquer";
		String nomeDirDestino = "c:/qualquer-bkp";
		
		copiarDiretório(nomeDirOrigem, nomeDirDestino);
				
		
	}

	private static void copiarDiretório(String nomeDirOrigem, String nomeDirDestino) throws Exception {
		File dirOrigem = new File(nomeDirOrigem);
		File dirDestino = new File(nomeDirDestino);
		dirDestino.mkdir();
		for (File arquivo : dirOrigem.listFiles()) {
			copiarArquivo(arquivo, nomeDirDestino + "/" + arquivo.getName());
		}
		
		
	}

	private static void copiarArquivo(File arquivo, String destino) throws Exception {
		FileInputStream input = new FileInputStream(arquivo);
		FileOutputStream output = new FileOutputStream(destino);
		int data = input.read();
		while (data != -1) {
			output.write(data);
			data = input.read();
		}
		output.close();
		input.close();
	}

}
