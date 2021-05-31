package esoft7s2021bim2.aula20210528.threadManager;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class AppThreadManager extends JDialog {
	private JButton btnAddThread = new JButton("Add Thread");
	
	public static void main(String[] args) {
		AppThreadManager app = new AppThreadManager();
		app.setVisible(true);
	}
	
	public AppThreadManager() {
		this.setSize(500,700);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(20,1));
		this.add(btnAddThread);
		
		btnAddThread.addActionListener(event -> {
			this.add(new ThreadManager(new ContadorThreadGerenciável()));
			revalidate();
		});
		
	}
	
	private class ThreadManager extends JPanel {
		private JProgressBar progressBar = new JProgressBar();
		private JButton btnThreadControl = new JButton("Start");
		private ThreadManagerStatus status = ThreadManagerStatus.CREATED;
		private ContadorThreadGerenciável contadorThreadGerenciável;
		
		public ThreadManager(ContadorThreadGerenciável contadorThreadGerenciável) {
			this.contadorThreadGerenciável = contadorThreadGerenciável;
			//Aqui estamos vinculando o listener à Thread, de modo que possamos
			//obter as atualizações dos valores conforme a Thread trabalha.
			this.contadorThreadGerenciável.setListener(novoValor -> {
				progressBar.setValue(novoValor);
				progressBar.revalidate();
			});
			
			this.add(progressBar);
			this.add(btnThreadControl);
			btnThreadControl.addActionListener(event -> {
				switch (status) {
				case CREATED:{
					btnThreadControl.setText("Pause");
					status = ThreadManagerStatus.RUNNING;
					contadorThreadGerenciável.start();
					break;
				}					
				case RUNNING : {
					btnThreadControl.setText("Restart");
					status = ThreadManagerStatus.PAUSED;
					contadorThreadGerenciável.pause();
					break;
				}			
				case PAUSED: {
					btnThreadControl.setText("Pause");
					status = ThreadManagerStatus.RUNNING;
					contadorThreadGerenciável.restart();
					break;
				}			
				default:
					break;
				}
			});
		}
	}
	
	private enum ThreadManagerStatus {
		CREATED, RUNNING, PAUSED
	}

}



