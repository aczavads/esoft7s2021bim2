package esoft7s2021bim2.aula20210503;

import java.io.File;
import java.io.IOException;

public class AppIO {
	
	public static void main(String[] args) throws IOException {
		File anyDirectory = new File("/home/web/Desktop/qualquer");
		System.out.println(anyDirectory.getCanonicalPath());
		
		File[] filesInAnyDirectory = anyDirectory.listFiles();
		for (File file : filesInAnyDirectory) {
			System.out.println(file.getName() + ", Size: " + file.length() + " Bytes");
		}
		
		System.out.println("Ready.");
	}

}
