package esoft7s2021bim2.aula20210503;

import java.io.File;
import java.io.IOException;

public class AppIO {
	
	public static void main(String[] args) throws IOException {
		File diretórioQualquer = new File("c:/qualquer");
		System.out.println(diretórioQualquer.getCanonicalPath());
		
		File[] arquivosEmDiretórioQualquer = diretórioQualquer.listFiles();
		for (File file : arquivosEmDiretórioQualquer) {
			System.out.println(file.getName() + ", size=" + file.length());
		}
		
		System.out.println("Foi."); 
	}

}
