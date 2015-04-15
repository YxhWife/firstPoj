package com.zl.collection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
	private Selector selector;//通道管理器
	
	public void initServer(int port) throws IOException{
		//创建一个通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		//设置为非阻塞
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	public void listen() throws IOException{
		System.out.println();
		while(true){
			//当通道管理器的注册事件到达时
			selector.select();
			Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
			while(iter.hasNext()){
				SelectionKey key = iter.next();
				iter.remove();
				if(key.isAcceptable()){
					ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
					SocketChannel channel = serverChannel.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("11111111").getBytes()));
					channel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					read(key);
				}
			}
		}
	}
	private void read(SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel) key.channel();
		//创建读缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data);
		//System.out.println("服务器端收到消息："+msg);
		System.out.println(msg);
		ByteBuffer outBuffer = ByteBuffer.wrap((msg).getBytes());
		channel.write(outBuffer);
	}
	
	public static void main(String args[]) throws IOException{
		NioServer server = new NioServer();
		server.initServer(9000);
		server.listen();
	}

}
