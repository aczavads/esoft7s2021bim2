package esoft7s2021bim2.aula20210503;

import java.io.File;
import java.io.IOException;

public class AppSizeOfFiles {

	public static void main(String[] args) throws IOException {
		String directoryName = "/home/web/Desktop/qualquer";
		long totalFileSize = calculateTotalFileSize(directoryName);
		System.out.println(
				"O tamanho total dos aquivos de '" + directoryName + "' é: " + totalFileSize + " bytes");

		System.out.println("Ready.");
	}

	private static long calculateTotalFileSize(String directoryName) throws IOException {
		long total = 0;
		File directory = new File(directoryName);
		File[] directoryFiles = directory.listFiles();
		for (File file : directoryFiles) {
			if (file.isDirectory()) {
				total += calculateTotalFileSize(file.getCanonicalPath());
			} else {
				total += file.length();				
			}
		}
		return total;
	}
}
