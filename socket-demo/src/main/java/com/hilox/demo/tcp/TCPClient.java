package com.hilox.demo.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Hilox on 2019/1/7 0007.
 */
public class TCPClient {

    public static void main(String[] args) throws Exception {
        // 创建socket, 并指定连接的是本机的端口号为65000的服务器socket
        Socket socket = new Socket("127.0.0.1", 65000);
        // 获取输出流
        OutputStream outputStream = socket.getOutputStream();
        // 获取输入流
        InputStream inputStream = socket.getInputStream();
        // 将要传递给server的字符串参数转换成byte数组, 并将数组写入到输出流当中
        outputStream.write(new String("Hello World!").getBytes());
        int ch = 0;
        byte[] buff = new byte[1024];
        // buff主要用来读取输入的内容, 存成byte数组, ch主要用来获取读取数组的长度
        ch = inputStream.read(buff);
        // 将接收流的byte数组转换成字符串, 这里获取的内容是客户端发送过来的字符串参数
        String content = new String(buff, 0, ch);
        System.out.println(content);
        // 不要忘记关闭输入输出流以及socket
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
