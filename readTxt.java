package tao.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by Tao on 2017/8/19.
 */
public class readTxt {
    public double[][] readData1(String str) throws IOException {
        FileReader fr = new FileReader(str);
        //可以换成工程目录下的其他文本文件
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        double[][] array = new double[610][340]; // 2行
        String[] sp;
        int k = 0;
        while((line = br.readLine()) != null){
            sp = line.split(" ");
//            System.out.println(sp.length);
            for(int j = 0;j < 340; j++){
                    array[k][j] = Double.parseDouble(sp[j]) + 3;
            }
            k++;
        }
        return array;
    }
}
