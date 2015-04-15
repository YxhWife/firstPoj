package com.zl.collection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {

	private Selector selector;
	
	public void initClient(String ip,int port) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		selector = Selector.open();
		channel.connect(new InetSocketAddress(ip, port));
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	public void listen() throws IOException{
		//轮询访问selector
		while(true){
			selector.select();
			//获得selector中选中项的迭代器
			Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
			while(iter.hasNext()){
				SelectionKey key = iter.next();
				iter.remove();
				if(key.isConnectable()){
					SocketChannel channel = (SocketChannel) key.channel();
					if(channel.isConnectionPending()){
						channel.finishConnect();
					}
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String().getBytes()));
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
		NioClient client = new NioClient();
		client.initClient("localhost", 9000);
		client.listen();
	}
}
