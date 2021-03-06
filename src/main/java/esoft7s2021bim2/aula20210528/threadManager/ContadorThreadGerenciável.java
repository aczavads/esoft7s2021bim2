package esoft7s2021bim2.aula20210528.threadManager;

public class ContadorThreadGerenciÃ¡vel extends Thread {
	private ContadorThreadGereciÃ¡velListener listener = null;
	private boolean paused = false;
	
	@Override
	public void run() {
		for (int i = 0; i <= 100; i++) {
			try {
				if (listener != null) {
					listener.valorMudou(i);
				}
				
				//parando a thread...
				synchronized (this) {
					while (this.paused) {
						this.wait();
					}
				}
				
				this.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
	}
	
	public void setListener(ContadorThreadGereciÃ¡velListener listener) {
		this.listener = listener;
	}

	public void pause() {
		this.paused = true;
	}

	public synchronized void restart() {
		this.paused = false;
		this.notify();
	}
}
