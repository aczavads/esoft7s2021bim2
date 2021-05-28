package esoft7s2021bim2.aula20210524.cópiaDeDiretóriosMultiThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopiadorDeDiretórioThread extends Thread {
	private String nomeDirOrigem;
	private String nomeDirDestino;

	public CopiadorDeDiretórioThread(String nomeDirOrigem, String nomeDirDestino) {
		this.nomeDirOrigem = nomeDirOrigem;
		this.nomeDirDestino = nomeDirDestino;
	}
	
	@Override
	public void run() {		
		try {
			copiarDiretório(nomeDirOrigem, nomeDirDestino);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	//colar os métodos aqui! :D
	private void copiarDiretório(String nomeDirOrigem, String nomeDirDestino) throws Exception {
		File dirOrigem = new File(nomeDirOrigem);
		File dirDestino = new File(nomeDirDestino);
		dirDestino.mkdir();
		for (File arquivo : dirOrigem.listFiles()) {
			copiarArquivo(arquivo, nomeDirDestino + "/" + arquivo.getName());
		}
		
		
	}

	private void copiarArquivo(File arquivo, String destino) throws Exception {
		FileInputStream input = new FileInputStream(arquivo);
		FileOutputStream output = new FileOutputStream(destino);
		try {
			System.out.println("Copiando arquivo [" + arquivo +"] ==> " + this.toString());
			this.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int data = input.read();
		while (data != -1) {
			output.write(data);
			data = input.read();
		}
		output.close();
		input.close();
	}

}
