package esoft7s2021bim2.aula20210528.cópiaDeDiretóriosManager;

import java.io.File;
import java.util.List;

public class CopiadorDeArquivosGerenciável extends Thread {
	private CopiadorDeArquivosGerenciávelListener listener = null;
	private boolean paused = false;
	private List<File> filesToCopy;
	private String nameOfDestinationDir;
	
	public CopiadorDeArquivosGerenciável(List<File> filesToCopy, String nameOfDestinationDir) {
		this.filesToCopy = filesToCopy;
		this.nameOfDestinationDir = nameOfDestinationDir;
	}

	@Override
	public void run() {
		int fileCounter = 0;
		for (File f : filesToCopy) {
			try {
				copyFile(f, nameOfDestinationDir);
				fileCounter++;
				if (listener != null) {
					System.out.println(fileCounter);
					System.out.println(filesToCopy.size());
					double step = (fileCounter / filesToCopy.size()) * 100.0;
					System.out.println(step);
					System.out.println();
					listener.valorMudou( (int)step );
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
	
	private void copyFile(File f, String nameOfDestinationDir2) {
		// TODO Auto-generated method stub
		
	}

	public void setListener(CopiadorDeArquivosGerenciávelListener listener) {
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
