package esoft7s2021bim2.aula20210528.cópiaDeDiretóriosManager;

import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class AppThreadManager extends JDialog {
	private JTextField fieldDirOrigem = new JTextField();
	private JTextField fieldDirDestino= new JTextField();
	private JTextField fieldArquivosPorThread = new JTextField();
	private List<ThreadManager> managers = new ArrayList<>();
	
	public static void main(String[] args) {
		AppThreadManager app = new AppThreadManager();
		app.setVisible(true);
	}
	
	public AppThreadManager() {
		this.setSize(500,700);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(20,1));
		this.add(new JLabel("Diretório de origem:"));
		this.add(fieldDirOrigem);
		this.add(new JLabel("Diretório de destino:"));
		this.add(fieldDirDestino);
		this.add(new JLabel("Qtd. arquivos por thread:"));
		this.add(fieldArquivosPorThread);
		
		fieldArquivosPorThread.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				File dirOrigem = new File(fieldDirOrigem.getText());
				File[] arquivosOrigem = dirOrigem.listFiles();
				//calcular este valor em função da quantidade de arquivos na origem
				//e da quantidade de arquivos que serão copiados por thread.				
				int quantidadeDeThreads = 0;
				int arquivosPorThread = 0;
				try {
					arquivosPorThread = Integer.parseInt(fieldArquivosPorThread.getText());
					quantidadeDeThreads = arquivosOrigem.length / arquivosPorThread;
					if (arquivosOrigem.length % arquivosPorThread != 0) {
						quantidadeDeThreads += 1;
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(rootPane, "Por favor, digite um número inteiro.");
				}
				
				for (ThreadManager tm : managers) {
					remove(tm);
					revalidate();
				}
				int índiceUltimoArquivoLido = 0;
				for (int i = 0; i < quantidadeDeThreads; i++) {
					
					//laço atualizando o índiceUltimoArquivoLido até atingir 
					//índiceUltimoArquivoLido + arquivosPorThread
					//dentro do laço, adicione os arquivos em uma lista que será passada
					//no construtor da thread.
					
					ThreadManager novo = new ThreadManager(
							new CopiadorDeArquivosGerenciável(
									/* passar a lista de arquivos que ESTA THREAD copiará. */));
					add(novo);
					managers.add(novo);
					revalidate();
				}
			}
		});
		
		
	}
	
	private class ThreadManager extends JPanel {
		private JProgressBar progressBar = new JProgressBar();
		private JButton btnThreadControl = new JButton("Start");
		private ThreadManagerStatus status = ThreadManagerStatus.CREATED;
		private CopiadorDeArquivosGerenciável copiadorDeArquivosGerenciável;
		
		public ThreadManager(CopiadorDeArquivosGerenciável contadorThreadGerenciável) {
			this.copiadorDeArquivosGerenciável = contadorThreadGerenciável;
			this.copiadorDeArquivosGerenciável.setListener(novoValor -> {
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



