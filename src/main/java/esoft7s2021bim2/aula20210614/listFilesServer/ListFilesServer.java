package esoft7s2021bim2.aula20210614.listFilesServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class ListFilesServer {
	Logger logger = Logger.getLogger(ListFilesServer.class.getName());
	
	public static void main(String[] args) {
		ListFilesServer server = new ListFilesServer();
		server.startup();
	}

	private void startup() {
		logger.info("Starting server...");
		try {
			ServerSocket serverSocket = new ServerSocket(9099);
			logger.info("Server started!");
			logger.info("Server listening on port 9099.");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				logger.info("Client connected!");
				
				Scanner fromCliente = new Scanner(clientSocket.getInputStream());

				String command = fromCliente.nextLine();
				if (command.startsWith("ls")) {
					//ls c:/windows
					PrintWriter toCliente = new PrintWriter(clientSocket.getOutputStream(), true);
					String dirName = command.split(" ")[1];
					File diretório = new File(dirName);
					File[] auxDiretórios = diretório.listFiles();
					for (File file : auxDiretórios) {
						toCliente.println(file.getCanonicalPath());
						toCliente.flush();
					}					
					toCliente.close();					
				} else if (command.startsWith("size")) {
					//size c:/windows
					//size d:/
					PrintWriter toCliente = new PrintWriter(clientSocket.getOutputStream(), true);
					String dirName = command.split(" ")[1];
					File diretório = new File(dirName);
					File[] auxDiretórios = diretório.listFiles();
					long totalSize = 0;
					for (File file : auxDiretórios) {
						if (!file.isDirectory()) {							
							totalSize += file.length();
						}
					}					
					toCliente.println(totalSize);
					toCliente.flush();
					toCliente.close();					
				} else if (command.startsWith("copy")) {
					System.out.println("Copiando o arquivo...");
					String fileName = command.split(" ")[1];
					FileInputStream fromFile = new FileInputStream(fileName);
					OutputStream toClient = clientSocket.getOutputStream();
					
					int data = fromFile.read();
					while (data != -1) {
						toClient.write(data);
						data = fromFile.read();
					}
					toClient.write(-1);
					toClient.flush();
					toClient.close();
					fromFile.close();
					System.out.println("Pronto!");
				}
				
				fromCliente.close();
				clientSocket.close();
				logger.info("Client disconnected.");
			}
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
			                     
		}
		logger.info("Server stoped.");
	}
}
