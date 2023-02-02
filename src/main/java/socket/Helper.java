package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhaochong on 2023/1/29 10:00
 */
public class Helper {
	public static void sendToSocket(Socket socket) throws IOException {
		OutputStream outputStream = socket.getOutputStream();

		while (true) {
			Scanner scanner = new Scanner(System.in);
			String string = scanner.next();
			if (string.equalsIgnoreCase("quit")) {
				break;
			}
			socket.getOutputStream().write(string.getBytes());
			System.out.println("------input quit keyword to finish......");
		}
		outputStream.close();
		socket.close();
	}
}
