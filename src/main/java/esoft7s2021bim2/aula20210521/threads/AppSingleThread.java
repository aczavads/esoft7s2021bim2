package esoft7s2021bim2.aula20210521.threads;

public class AppSingleThread {
	
	public static void main(String[] args) {
		long início = System.currentTimeMillis();
		contarAté(4000);
		long término = System.currentTimeMillis();
		System.out.println("Tempo total: " + (término-início));
	}

	private static void contarAté(int limite) {
		for (int i = 0; i <= limite; i++) {
			String[] valores = new String[100000];
			for (int j = 0; j < valores.length; j++) {
				valores[j] = "" + i;				
			}
		}
	}

}
