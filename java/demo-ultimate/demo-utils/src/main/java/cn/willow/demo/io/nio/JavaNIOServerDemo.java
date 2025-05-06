package cn.willow.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 服务器示例
 * @author willow
 * @since 2024/12/11
 */
public class JavaNIOServerDemo {
    public static void main(String[] args) {
        try(Selector selector = Selector.open(); ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            //初始化
            serverSocketChannel.bind(new InetSocketAddress(7777));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //字符转码准备
            Charset charset = StandardCharsets.UTF_8;
            CharsetDecoder decoder = charset.newDecoder();
            //开始服务
            while (true) {
                //阻塞直到有 IO 事件
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        //处理新连接
                        System.out.println("accept event");
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //处理读操作
                        System.out.println("read event");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        CharBuffer charBuffer = CharBuffer.allocate(256);
                        int read = socketChannel.read(buffer);
                        if (read > 0) {
                            //处理读到的数据
                            buffer.flip();// 切换到读模式
                            //普通读，中文乱码
//                            StringBuilder sb = new StringBuilder();
//                            while (buffer.hasRemaining()) {
//                                sb.append((char) buffer.get());
//                            }
//                            System.out.println(new String(sb));
//                            buffer.clear();
                            //转码读
                            CoderResult result = decoder.decode(buffer, charBuffer, true);
                            buffer.clear();
                            if (result.isError()) {
                                throw new RuntimeException("decode error");
                            }
                            charBuffer.flip();
                            StringBuilder sb = new StringBuilder();
                            while (charBuffer.hasRemaining()) {
                                sb.append(charBuffer.get());
                            }
                            System.out.println(sb);
                            charBuffer.clear();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
