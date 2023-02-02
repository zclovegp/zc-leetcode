package socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhaochong on 2023/1/29 08:48
 */
public class BioServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		while (true) {
			System.out.println("模拟RedisServer启动-----step1 等待连接");
			Socket socket = serverSocket.accept();
			System.out.println("-----step2 成功连接 => " + socket.getRemoteSocketAddress());
			InputStream inputStream = socket.getInputStream();
			int length = -1;
			byte[] bytes = new byte[1024];
			System.out.println("-----step3 等待读取");
			while ((length = inputStream.read(bytes)) != -1) {//阻塞2 ,等待客户端发送数据
				System.out.println("-----step4 成功读取" + new String(bytes, 0, length));
				System.out.println("====================");
			}
			inputStream.close();
			socket.close();
		}
	}
}
