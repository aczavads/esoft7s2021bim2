package esoft7s2021bim2.aula20210524.cópiaDeDiretóriosMultiThread;

public class CopiadorDeDiretórioThread extends Thread {
	private String nomeDirOrigem;
	private String nomeDirDestino;

	public CopiadorDeDiretórioThread(String nomeDirOrigem, String nomeDirDestino) {
		this.nomeDirOrigem = nomeDirOrigem;
		this.nomeDirDestino = nomeDirDestino;
	}
	
	@Override
	public void run() {		
		copiarDiretório(nomeDirOrigem, nomeDirDestino);		
	}
	
	
	//colar os métodos aqui! :D

}
