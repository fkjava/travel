package org.fkjava.travel.commons;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author 罗文强 <luo_wenqiang@qq.com>
 */
public class Checksum {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    private final String algorithm;

    public Checksum(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * 把字符串转换为MD5散列数据摘要，通常用于密码验证。
     *
     * @param source
     * @return 密文。把明文散列后得到的数字转换为16进制格式的字符串
     */
    public String hash(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(source.getBytes(CHARSET));
            byte[] digest = messageDigest.digest();

            String result = ByteUtils.toHex(digest);
            return result;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("无法计算字符串的"
                    + algorithm + "的散列值: "
                    + ex.getLocalizedMessage(), ex);
        }
    }

    /**
     * 对字节数组计算校验和，通常用于用于判断文件内容是否相同。
     *
     * @param source
     * @return
     */
    public String sum(byte[] source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(source);
            byte[] digest = messageDigest.digest();

            String result = ByteUtils.toHex(digest);
            return result;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("无法计算字节数组的"
                    + algorithm + "校验和: "
                    + ex.getLocalizedMessage(), ex);
        }
    }

    /**
     * 对输入流里面的内容生成指纹，使用此方法的时候要注意：流只能被使用一次！
     *
     * @param in
     * @return
     */
    public String sum(InputStream in) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.reset();

            byte[] buf = new byte[1024];
            for (int i = in.read(buf); i != -1; i = in.read(buf)) {
                md.update(buf, 0, i);
            }
            byte[] digest = md.digest();
            String result = ByteUtils.toHex(digest);
            return result;
        } catch (IOException | NoSuchAlgorithmException ex) {
            throw new RuntimeException("无法计算字节数组的"
                    + algorithm + "校验和: "
                    + ex.getLocalizedMessage(), ex);
        }
    }
}
