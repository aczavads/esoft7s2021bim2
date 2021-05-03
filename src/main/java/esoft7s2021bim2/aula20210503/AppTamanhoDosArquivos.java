package esoft7s2021bim2.aula20210503;

import java.io.File;
import java.io.IOException;

public class AppTamanhoDosArquivos {

	public static void main(String[] args) throws IOException {
		String nomeDoDiret�rio = "c:/qualquer";
		long tamanhoTotalDosArquivos = calcularTamanhoTotalArquivos(nomeDoDiret�rio);
		System.out.println(
				"O tamanho total dos aquivos de '" + nomeDoDiret�rio + "' �: " + tamanhoTotalDosArquivos + " bytes");
	}

	private static long calcularTamanhoTotalArquivos(String nomeDoDiret�rio) throws IOException {
		long total = 0;
		File diret�rio = new File(nomeDoDiret�rio);
		File[] arquivosDoDiret�rio = diret�rio.listFiles();
		for (File file : arquivosDoDiret�rio) {
			if (file.isDirectory()) {
				total += calcularTamanhoTotalArquivos(file.getCanonicalPath());
			} else {
				total += file.length();				
			}
		}
		return total;
	}
}
