package esoft7s2021bim2.aula20210503;

import java.io.File;

public class AppIO {
	
	public static void main(String[] args) {
		File diret�rioQualquer = new File("c:/qualquer");
		
		File[] arquivosEmDiret�rioQualquer = diret�rioQualquer.listFiles();
		for (File file : arquivosEmDiret�rioQualquer) {
			System.out.println(file.getName() + ", size=" + file.length());
		}
		
		System.out.println("Foi.");
	}

}
