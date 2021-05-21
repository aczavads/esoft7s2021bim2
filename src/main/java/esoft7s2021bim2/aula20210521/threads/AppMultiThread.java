package esoft7s2021bim2.aula20210521.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppMultiThread {
	
	public static void main(String[] args) {
		
		TimingThread tt = new TimingThread();
		
		int quantidadeDeThreads = 6;
		int n = 8000;
		int tamanhoPartição = (int)(n/quantidadeDeThreads);
		ContadorThread[] contadores = new ContadorThread[quantidadeDeThreads];
		for (int i = 0; i < quantidadeDeThreads; i++) {
			int de = tamanhoPartição*i;
			int até = (tamanhoPartição*i)+tamanhoPartição-1;
			if (i == quantidadeDeThreads-1) 
				até++;
			System.out.println(de  + " até " + até);
			ContadorThread ct = new ContadorThread(de, até, tt);
			contadores[i] = ct;
		}
		tt.start();
		for (ContadorThread contadorThread : contadores) {
			contadorThread.start();
		}
		
				
//		ContadorThread c1 = new ContadorThread(0, 1000, tt);
//		ContadorThread c2 = new ContadorThread(1001, 2000, tt);
//		ContadorThread c3 = new ContadorThread(2001, 3001, tt);
//		ContadorThread c4 = new ContadorThread(3001, 4000, tt);
		
//		tt.start();
		
//		c1.start();
//		System.out.println("c1..");
//		c2.start();
//		System.out.println("c2..");
//		c3.start();
//		System.out.println("c3..");
//		c4.start();
//		System.out.println("c4..");
	}
	
	private static class TimingThread extends Thread {
		private List<ContadorThread> contadores = new ArrayList<>();
		private long início;
		private long término;

		public synchronized void register(ContadorThread contadorThread) {
			contadores.add(contadorThread);
		}

		public synchronized void started(ContadorThread contadorThread) {
			if (início == 0) {
				início = System.currentTimeMillis();
				System.out.println("Início>>> " + new Date().toLocaleString());
			}
		}

		public synchronized void finished(ContadorThread contadorThread) {
			if (contadores.size() == 1) {
				término = System.currentTimeMillis();
				System.out.println("Início>>> " + new Date().toLocaleString());
			}
			this.contadores.remove(contadorThread);			
		}
		
		@Override
		public void run() {
			while (contadores.size() > 0) {
				try {
					this.sleep(1000);
				} catch (Exception e) {
				}
			}
			System.out.println("Tempo total: " + (término-início));
		}
		
	}
	
	private static class ContadorThread extends Thread {
		private int inícioContagem;
		private int términoContagem;
		private TimingThread tt;

		public ContadorThread(int inícioContagem, int términoContagem, TimingThread tt) {
			this.inícioContagem = inícioContagem;
			this.términoContagem = términoContagem;
			tt.register(this);
			this.tt = tt;
		}

		@Override
		public void run() {
			//long início = System.currentTimeMillis();
			//comecei!
			tt.started(this);
			for (int i = inícioContagem; i <= términoContagem; i++) {
				String[] valores = new String[100000];
				for (int j = 0; j < valores.length; j++) {
					valores[j] = "" + i;				
				}
			}
			//terminei!
			tt.finished(this);
			//long término = System.currentTimeMillis();
			//System.out.println("Tempo total: " + (término-início));
		}
		
	}

}
