package org.fkjava.travel.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author 罗文强 <luo_wenqiang@qq.com>
 */
public class SHA {

    public static String sha256(String source) {
        Checksum cs = new Checksum("SHA-256");
        return cs.hash(source);
    }

    public static String sha256(byte[] source) {
        Checksum cs = new Checksum("SHA-256");
        return cs.sum(source);
    }

    public static String sha256(InputStream in) {
        Checksum cs = new Checksum("SHA-256");
        return cs.sum(in);
    }

    public static void main(String[] args) throws IOException {
        String x1 = sha256("1234");
        System.out.println(x1);

        try (FileInputStream in = new FileInputStream("/home/lwq/yqb.txt");) {
            String x2 = sha256(in);
            System.out.println(x2);
        }

        try (FileInputStream in = new FileInputStream("/home/lwq/yqb.txt");) {
            byte[] data = new byte[10000];
            int c = in.read(data);
            data = Arrays.copyOf(data, c);
            String x3 = sha256(data);
            System.out.println(x3);
        }
    }

}
