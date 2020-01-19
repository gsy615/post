package com.gz.myapplication;

import android.net.Uri;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class SocketData {

    static int h = 16;

    public static byte[] closeData() {
        byte[] total = new byte[16];
        byte[] abyte = int2bytes(h);
        byte[] hbyte = shortToBytes(h);
        byte[] h1byte = shortToBytes(3001);
        byte[] h2byte = int2bytes(29);
        byte[] h3byte = int2bytes(1);
        System.arraycopy(abyte, 0, total, 0, abyte.length);
        System.arraycopy(hbyte, 0, total, abyte.length, hbyte.length);
        System.arraycopy(h1byte, 0, total, abyte.length + hbyte.length, h1byte.length);
        System.arraycopy(h2byte, 0, total, abyte.length + hbyte.length + h1byte.length, h2byte.length);
        System.arraycopy(h3byte, 0, total, abyte.length + hbyte.length + h1byte.length + h2byte.length, h3byte.length);
        return total;
    }

    public static byte[] authData(String key) {
        byte[] byteKey = key.getBytes();
        int a = h + byteKey.length;
        byte[] total = new byte[16];
        byte[] abyte = int2bytes(a);
        byte[] hbyte = shortToBytes(h);
        byte[] h1byte = shortToBytes(3001);
        byte[] h2byte = int2bytes(7);
        byte[] h3byte = int2bytes(1);
        System.arraycopy(abyte, 0, total, 0, abyte.length);
        System.arraycopy(hbyte, 0, total, abyte.length, hbyte.length);
        System.arraycopy(h1byte, 0, total, abyte.length + hbyte.length, h1byte.length);
        System.arraycopy(h2byte, 0, total, abyte.length + hbyte.length + h1byte.length, h2byte.length);
        System.arraycopy(h3byte, 0, total, abyte.length + hbyte.length + h1byte.length + h2byte.length, h3byte.length);
        byte[] all = new byte[total.length + byteKey.length];
        System.arraycopy(total, 0, all, 0, total.length);
        System.arraycopy(byteKey, 0, all, total.length, byteKey.length);
        return all;
    }

    public static byte[] heartbeatData() {
        byte[] total = new byte[16];
        byte[] abyte = int2bytes(h);
        byte[] hbyte = shortToBytes(h);
        byte[] h1byte = shortToBytes(3001);
        byte[] h2byte = int2bytes(2);
        byte[] h3byte = int2bytes(1);
        System.arraycopy(abyte, 0, total, 0, abyte.length);
        System.arraycopy(hbyte, 0, total, abyte.length, hbyte.length);
        System.arraycopy(h1byte, 0, total, abyte.length + hbyte.length, h1byte.length);
        System.arraycopy(h2byte, 0, total, abyte.length + hbyte.length + h1byte.length, h2byte.length);
        System.arraycopy(h3byte, 0, total, abyte.length + hbyte.length + h1byte.length + h2byte.length, h3byte.length);
        return total;
    }

    public static byte[] int2bytes(int i){
        byte[] bytes=new byte[4];
        bytes[0]=(byte) (i>>>24&0xff);
        bytes[1]=(byte) (i>>>16&0xff);
        bytes[2]=(byte) (i>>>8&0xff);
        bytes[3]=(byte) (i&0xff);
        return bytes;
    }

    public static int bytes2int(byte[] bytes){
        int result = 0;
        result = bytes[0] & 0xff;
        result = result << 8 | bytes[1] & 0xff;
        result = result << 8 | bytes[2] & 0xff;
        result = result << 8 | bytes[3] & 0xff;
        return result;
    }

    public static byte[] shortToBytes(int s) {
        byte[] targets = new byte[2];
        targets[0] = (byte) (s >> 8 & 0xFF);
        targets[1] = (byte) (s & 0xFF);
        return targets;
    }

    public static int bytes2short(byte[] bytes){
        int result = 0;
        result = bytes[0] & 0xff;
        result = result << 8 | bytes[1] & 0xff;
        return result;
    }
}
