package esoft7s2021bim2.aula20210524.cópiaDeDiretóriosMultiThread;

public class AppCópiaDeDiretóriosMultiThread {
	
	public static void main(String[] args) {
//		String nomeDirOrigem = "c:/qualquer";
//		String nomeDirDestino = "c:/qualquer-bkp";
//		
//		copiarDiretório(nomeDirOrigem, nomeDirDestino);
		CopiadorDeDiretórioThread copiador01 = new CopiadorDeDiretórioThread("c:/qualquer", "c:/qualquer-bkp"); 
		CopiadorDeDiretórioThread copiador02 = new CopiadorDeDiretórioThread("c:/teste", "c:/teste-bkp"); 
		CopiadorDeDiretórioThread copiador03 = new CopiadorDeDiretórioThread("c:/dados", "c:/dados-bkp");
		
		copiador01.start();
		copiador02.start();
		copiador03.start();
	}

}
