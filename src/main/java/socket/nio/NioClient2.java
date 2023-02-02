package socket.nio;

import socket.Helper;

import java.io.IOException;
import java.net.Socket;

/**
 * @author zhaochong on 2023/1/29 08:49
 */
public class NioClient2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = new Socket("localhost", 8080);
		System.out.println("------RedisClient02（NIO） start =>" + socket.getLocalSocketAddress());
		Helper.sendToSocket(socket);
	}
}
