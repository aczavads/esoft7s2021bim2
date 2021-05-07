package esoft7s2021bim2.aula20210503;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppTamanhoDosArquivos {

	public static void main(String[] args) throws IOException {
		String nomeDoDiretório = "c:/qualquer";
		
		listarArquivosNoConsole(nomeDoDiretório);
		
		long tamanhoTotalDosArquivos = calcularTamanhoTotalArquivos(nomeDoDiretório);
		System.out.println(
				"O tamanho total dos aquivos de '" + nomeDoDiretório + " é: " + tamanhoTotalDosArquivos + " bytes");
	}

	private static void listarArquivosNoConsole(String nomeDoDiretório) throws IOException {
		List<String> nomesParaListar = new ArrayList<>();
		listarArquivosNoConsole(nomeDoDiretório, nomesParaListar);
		System.out.println("Listando: " + nomeDoDiretório);
		for (int i = nomesParaListar.size()-1; i >= 0; i--) {
			System.out.println(nomesParaListar.get(i));
		}
		
	}
	private static void listarArquivosNoConsole(String nomeDoDiretório, List<String> nomesParaListar) throws IOException {
		File diretório = new File(nomeDoDiretório);
		File[] auxDiretórios = Arrays.stream(diretório.listFiles())
				.filter( f -> f.isDirectory())
				.collect(Collectors.toList())
				.toArray(File[]::new);
		File[] auxArquivos = Arrays.stream(diretório.listFiles())
				.filter( f -> !f.isDirectory())
				.collect(Collectors.toList())
				.toArray(File[]::new);
		File[] arquivosDoDiretório = Stream.of(auxDiretórios, auxArquivos)
				.flatMap(Stream::of)
				.toArray(File[]::new);
		for (File file : arquivosDoDiretório) {
			if (file.isDirectory()) {
				listarArquivosNoConsole(file.getCanonicalPath(), nomesParaListar);
			} 
			//System.out.println(file.getCanonicalPath());				
			nomesParaListar.add(file.getCanonicalPath());
		}		
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
