package esoft7s2021bim2.aula20210503;

import java.io.File;
import java.io.IOException;

public class AppTamanhoDosArquivos {

	public static void main(String[] args) throws IOException {
		String nomeDoDiretório = "c:/qualquer";
		long tamanhoTotalDosArquivos = calcularTamanhoTotalArquivos(nomeDoDiretório);
		System.out.println(
				"O tamanho total dos aquivos de '" + nomeDoDiretório + "' é: " + tamanhoTotalDosArquivos + " bytes");
	}

	private static long calcularTamanhoTotalArquivos(String nomeDoDiretório) throws IOException {
		long total = 0;
		File diretório = new File(nomeDoDiretório);
		File[] arquivosDoDiretório = diretório.listFiles();
		for (File file : arquivosDoDiretório) {
			if (file.isDirectory()) {
				total += calcularTamanhoTotalArquivos(file.getCanonicalPath());
			} else {
				total += file.length();				
			}
		}
		return total;
	}
}
