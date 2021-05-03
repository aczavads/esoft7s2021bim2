package esoft7s2021bim2.aula20210503;

import java.io.File;

public class AppIO {
	
	public static void main(String[] args) {
		File diretórioQualquer = new File("c:/qualquer");
		
		File[] arquivosEmDiretórioQualquer = diretórioQualquer.listFiles();
		for (File file : arquivosEmDiretórioQualquer) {
			System.out.println(file.getName() + ", size=" + file.length());
		}
		
		System.out.println("Foi.");
	}

}
