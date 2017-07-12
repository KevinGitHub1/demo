package com.zmk.cms.common.util;

/**
 * 由字节码转换base64编码处理类，配置页面登录使用。
 * 
 * 状态：创建 日期：2008-6-8
 * @version v1.0
 * @author 王一宁
 *
 */
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Base64Encrypt {
    public String getBASE64(String s) {
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        if (s == null) {
            return null;
        }
        return encoder.encode(s.getBytes());
    }

    public String getBase64FromBytes(byte[] buf) {
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(buf);
    }

    public String getFromBASE64(String s) {
        if (s == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    public String encode(String strTest) {
        String[] unicode = strTest.split("%u");
        StringBuffer buf = new StringBuffer(unicode.length - 1);
        for (int i = 1; i < unicode.length; i++) {
            if (unicode[i].length() == 4) {
                buf.append((char) Integer.parseInt(unicode[i], 16));
            } else {
                String strTemp = unicode[i].substring(0, 4);
                buf.append((char) Integer.parseInt(strTemp, 16));
                buf.append(unicode[i].substring(4, unicode[i].length()));
            }
        }
        return buf.toString();
    }

    public static void main(String[] args) throws Exception {
        Base64Encrypt be = new Base64Encrypt();
        String strTest = be.getFromBASE64("ODg0MjAxOS4xMjE3NTEwMC5qcGc=");

        System.out.println(be.encode(strTest));
    }
}
