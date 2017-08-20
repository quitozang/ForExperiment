package tao.test;

import java.io.IOException;

/**
 * Created by Tao on 2017/8/7.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        double x = 0.01;
        double y = 0.02;
        System.out.println(x + y);
//        double[][] a = {{4.123,2.987,3.345},{7.765,5.345,2.876}};
//        double[][] b = {{7.676,4.565,8.323},{8.789,5.654,9.654}};
        readTxt myread = new readTxt();
        double[][] a = myread.readData1("data/image.txt");
        double[][] b = myread.readData1("data/tempB.txt");
        ImReconstruct r = new ImReconstruct();
        double[][] res = r.start(a,b);

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] -= 3;
            }
        }

        System.out.println("");
    }
}
