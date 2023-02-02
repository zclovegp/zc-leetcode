package socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author zhaochong on 2023/1/29 10:27
 */
public class NioServer {

	static ArrayList<SocketChannel> socketList = new ArrayList<>();
	static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

	public static void main(String[] args) throws IOException {
		System.out.println("---------RedisServerNIO 启动等待中......");
		ServerSocketChannel serverSocket = ServerSocketChannel.open();
		// 绑定ip和端口
		serverSocket.bind(new InetSocketAddress("127.0.0.1", 8080));
		serverSocket.configureBlocking(false);//设置为非阻塞模式

		while (true) {
			for (SocketChannel element : socketList) {
				int read = element.read(byteBuffer);
				if (read > 0) {
					System.out.println("-----读取数据: " + read);
					byteBuffer.flip();
					byte[] bytes = new byte[read];
					byteBuffer.get(bytes);
					System.out.println(new String(bytes));
					byteBuffer.clear();
				}
			}
			// 不会阻塞，直接没有连接直接返回null
			SocketChannel socketChannel = serverSocket.accept();
			if (socketChannel != null) {
				System.out.println("-----成功连接: ");
				socketChannel.configureBlocking(false);//设置为非阻塞模式
				socketList.add(socketChannel);
				System.out.println("-----socketList size: " + socketList.size());
			}
		}
	}
}
