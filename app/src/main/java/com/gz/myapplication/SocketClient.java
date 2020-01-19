package com.gz.myapplication;


import android.net.Uri;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class SocketClient extends WebSocketClient {

    private static WebSocketClient client;

    public static WebSocketClient connect(String url) {
        client = null;
        try {
            client = new SocketClient(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (client != null) {
            client.connect();
        }
        return client;
    }

    public SocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println( "fuck:" + "opened connection" );
        this.send(SocketData.authData("211ab96841754ae2afa4c4c43b36eee3"));
        System.out.println( "fuck:" + "sendAuth" );
    }

    @Override
    public void onMessage(String message) {
        System.out.println("fuck:" + message);
        // send(message);

    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        super.onMessage(bytes);
        byte[] data = bytes.array();
        byte[] r = new byte[4];
        byte[] r1 = new byte[2];
        byte[] r2 = new byte[2];
        byte[] r3 = new byte[4];
        byte[] c = new byte[4];
        System.arraycopy(data, 0, r, 0, r.length);
        System.arraycopy(data, r.length, r1, 0, r1.length);
        System.arraycopy(data, r.length + r1.length, r2, 0, r2.length);
        System.arraycopy(data, r.length + r1.length + r2.length, r3, 0,  r3.length);
        System.arraycopy(data, 12, c, 0, c.length);
        int r1i = SocketData.bytes2short(r1);
        int r2i = SocketData.bytes2short(r2);
        int r3i = SocketData.bytes2int(r3);
        int type = r3i;
        switch (type) {
            case 8:
                //心跳
                client.send(SocketData.heartbeatData());
                break;
            case 5:
                //认证
                client.send(SocketData.heartbeatData());
                break;
            case 21:
                //消息
                Log.e("11111", "11111");
                break;
            case 23:
                System.out.println("fuck:" + "此账号已在别的浏览器登录");
                break;
            case 24:
                System.out.println("fuck:" + "APP端已退出WEB登录");
                break;
            case 28:
                System.out.println("fuck:" + "APP退出登录");
                break;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println( "fuck:" + "Connection closed by " + ( remote ? "remote peer" : "us" ) );
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        System.out.println( "fuck:" + ex.toString());
    }
}

