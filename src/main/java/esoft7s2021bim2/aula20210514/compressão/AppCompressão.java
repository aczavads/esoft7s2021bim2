package esoft7s2021bim2.aula20210514.compressão;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class AppCompressão {
	
	
	public static void main(String[] args) {
		File arquivo = new File("d:/formandos.txt.gz");
		
		//gravarArquivo(arquivo);
		listarArquivo(arquivo);
		
		System.out.println("Foi.");
	}

	private static void listarArquivo(File arquivo) {
		try {
			//InputStream input = new GZIPInputStream(new FileInputStream(arquivo));
			InputStream input = new GZIPInputStream(new FileInputStream(arquivo));
			Scanner scanner = new Scanner(input);
			int índice = 1;
			while (scanner.hasNextLine()) {
				System.out.println("Linha " + (índice++) +   ": " + scanner.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void gravarArquivo(File arquivo) {
		String texto = "Olá formandos 2021 de Esoft da UniCesumar! :D";
		
		try {
			OutputStream output = new GZIPOutputStream(new FileOutputStream(arquivo));
			for (int i = 0; i < 100_000; i++) {
				output.write((texto + "\n").getBytes());
			}
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
