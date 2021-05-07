package esoft7s2021bim2.aula20210507;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class AppLeituraArquivoTexto {
	
	
	public static void main(String[] args) throws Exception {
		
		File arquivoTexto = new File("c:/qualquer/arquivo.txt");
		Scanner scanner = new Scanner(new FileInputStream(arquivoTexto));
		int linha = 1;
		while (scanner.hasNextLine()) {
			System.out.println("Linha " + linha + ": " + scanner.nextLine());
			linha ++;
		}
		
	}

}
