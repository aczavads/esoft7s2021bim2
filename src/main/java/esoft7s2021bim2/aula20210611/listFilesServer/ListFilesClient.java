package esoft7s2021bim2.aula20210611.listFilesServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ListFilesClient {
	
	public static void main(String[] args) {
		ListFilesClient client = new ListFilesClient();
		client.doIt();
	}

	private void doIt() {
		try {
			Scanner fromConsole = new Scanner(System.in);
			String command = "";
			while (!command.equals("quit")) {
				System.out.println(">> ");
				command = fromConsole.nextLine();
				if (command.startsWith("ls")) {
					//ls c:/windows
					//ls d:/
					executeCommand(command);
				} else if (command.startsWith("size")) {
					//size c:/windows
					//size d:/
					executeCommand(command);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void executeCommand(String command) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 9099);
		PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
		Scanner fromServer = new Scanner(socket.getInputStream());
		
		toServer.println(command);
		
		while (fromServer.hasNextLine()) {
			System.out.println(fromServer.nextLine());
		}
	}

}
