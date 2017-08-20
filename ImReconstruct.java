package tao.test;

/**
 * Created by lenovo on 2017/7/9.
 */
public class ImReconstruct {
    public double[][] start(double[][] marker,double[][] mask){
        int n,m;
        int conn;
        int dims;
        int ndims;
        int vettore;
        int mp1,np1,np2,mp2;
        int cordx1,cordx0;
        int cordx = 0,cordxm1,cordxmm,cordxmmm1,cordxpmm1;
        int cordxp1,cordxpm,cordxpmp1,cordxmmp1;
        int lettura;
        int scrittura;
        double max,v1,v2,v3,v4,v5;
        int percentuale;
        double[] a = new double[marker.length*marker[0].length];
        int sum = 0;
        for(int j = 0; j < marker[0].length; j++) {
            for (int i = 0 ;i<marker.length;i++) {
                a[sum] = marker[i][j];
                sum++;
            }
        }
//        System.out.println(a[1]);
        double[] b = new double[marker.length*marker[0].length];
        sum = 0;
        for(int j = 0; j < mask[0].length; j++) {
            for (int i = 0 ;i<mask.length;i++) {
                b[sum] = mask[i][j];
                sum++;
            }
        }
        n=marker.length;//行
        m=marker[0].length;//列
        double[] II = new double[(m+2)*(n+2)];
        double[] JJ = new double[(m+2)*(n+2)];
        percentuale=30;
        int coda_dim=(n*m*percentuale)%100;
        coda_dim=(n*m*percentuale-coda_dim)/100;
        long[] coda = new long[coda_dim];
//        System.out.println(coda.length);
        mp1=m+1;
        mp2=m+2;
        np1=n+1;
        np2=n+2;
        for (int jj = 1; jj < mp1; jj++){
            for (int ii = 1; ii < np1; ii++){
                cordx1=jj*np2+ii;
                cordx0=(jj-1)*n+ii-1;
                II[cordx1] = a[cordx0];
                JJ[cordx1] = b[cordx0];
            }
        }

        //if
        for (int ii = 2; ii < np2; ii++) {
            for (int jj = 2; jj < mp2; jj++) {
                cordx=(jj-1)*np2+ii-1;
                max = JJ[cordx];

                v1=JJ[cordx-1];
                if(max<v1)
                    max=v1;

                v1=JJ[cordx-np2];
                if(max<v1)
                    max=v1;

                v1=JJ[cordx-np2-1];
                if(max<v1)
                    max=v1;

                v1=JJ[cordx+np2-1];
                if(max<v1)
                    max=v1;

                v1=II[cordx];
                if(max>v1)
                    max=v1;
                JJ[cordx] = max;
            }
        }
        lettura=0;
        scrittura=0;
        for (int ii = np1; ii > 1; ii--) {
            for (int jj = mp1; jj > 1; jj--) {
                cordx=(jj-1)*np2+ii-1;
                cordxp1=cordx+1;
                cordxpm=cordx+np2;
                cordxpmp1=cordxpm+1;
                cordxmmp1=cordx-np2+1;

                max=JJ[cordx];
                v1=JJ[cordxp1];
                v2=JJ[cordxpm];
                v3=JJ[cordxmmp1];
                v4=JJ[cordxpmp1];
                v5=II[cordx];

                if(max<v1)
                    max=v1;

                if(max<v2)
                    max=v2;

                if(max<v3)
                    max=v3;

                if(max<v4)
                    max=v4;

                if(max>v5)
                    max=v5;
                JJ[cordx]=max;

                if ((v2<max) && (v2<II[cordxpm])){
                    coda[scrittura]=cordx;
                    scrittura=scrittura+1;
                }else{
                    if((v1<max) && (v1<II[cordxp1])){
                        coda[scrittura] = cordx;
                        scrittura = scrittura+1;
                    }else{
                        if((v4<max) && (v4<II[cordxpmp1])){
                            coda[scrittura] = cordx;
                            scrittura=scrittura+1;
                        }else{
//                            System.out.println(II[cordxmmp1]);
                            if((v3<max) && (v3<II[cordxmmp1])){
                                coda[scrittura] = cordx;
                                scrittura=scrittura+1;
                            }
                        }
                    }
                }
            }
        }

        while(lettura < scrittura) {
            cordx=(int)coda[lettura];
            lettura++;
            cordxm1 = cordx-1;
            cordxmm = cordx-np2;
            cordxmmm1=cordx-np2-1;
            cordxpmm1=cordx+np2-1;
            cordxp1=cordx+1;
            cordxpm=cordx+np2;
            cordxpmp1=cordxpm+1;
            cordxmmp1=cordx-np2+1;
            max = JJ[cordx];

            v1 = JJ[cordxmmm1];
            if((v1<max) && (II[cordxmmm1] != v1)){
                if(max < II[cordxmmm1])
                    JJ[cordxmmm1] = max;
                else
                    JJ[cordxmmm1] = II[cordxmmm1];
                coda[scrittura] = cordxmmm1;
                scrittura++;
            }
            v1 = JJ[cordxm1];
            if((v1<max) && (II[cordxm1] != v1)){
                if(max < II[cordxm1])
                    JJ[cordxm1] = max;
                else
                    JJ[cordxm1] = II[cordxm1];
                coda[scrittura] = cordxm1;
                scrittura++;
            }

            v1 = JJ[cordxpmm1];//3
            if((v1<max) && (II[cordxpmm1] != v1)){
                if(max < II[cordxpmm1])
                    JJ[cordxpmm1] = max;
                else
                    JJ[cordxpmm1] = II[cordxpmm1];
                coda[scrittura] = cordxpmm1;
                scrittura++;
            }
            v1 = JJ[cordxmm];//4
            if((v1<max) && (II[cordxmm] != v1)){
                if(max < II[cordxmm])
                    JJ[cordxmm] = max;
                else
                    JJ[cordxmm] =II[cordxmm];
                coda[scrittura] = cordxmm;
                scrittura++;
            }
            v1 = JJ[cordxpm];//5
            if((v1<max) && (II[cordxpm] != v1)){
                if(max < II[cordxpm])
                    JJ[cordxpm] = max;
                else
                    JJ[cordxpm] =II[cordxpm];
                coda[scrittura] = cordxpm;
                scrittura++;
            }
            v1 = JJ[cordxmmp1];//6
            if((v1<max) && (II[cordxmmp1] != v1)){
                if(max < II[cordxmmp1])
                    JJ[cordxmmp1] = max;
                else
                    JJ[cordxmmp1] =II[cordxmmp1];
                coda[scrittura] = cordxmmp1;
                scrittura++;
            }
            v1 = JJ[cordxp1];//7
            if((v1<max) && (II[cordxp1] != v1)){
                if(max < II[cordxp1])
                    JJ[cordxp1] = max;
                else
                    JJ[cordxp1] =II[cordxp1];
                coda[scrittura] = cordxp1;
                scrittura++;
            }
            v1 = JJ[cordxpmp1];
            if((v1<max) && (II[cordxpmp1] != v1)){
                if(max < II[cordxpmp1])
                    JJ[cordxpmp1] = max;
                else
                    JJ[cordxpmp1] =II[cordxpmp1];
                coda[scrittura] = cordxpmp1;
                scrittura++;
            }
        }

        double[] r = new double[n*m];
        for (int jj = 1; jj < mp1; jj++) {
            for (int ii = 1; ii < np1; ii++) {
                cordx1 = jj*np2+ii;
                cordx0 = (jj-1)*n+ii-1;
                r[cordx0] = JJ[cordx1];
            }
        }
        System.out.println(n + " " + m);
        double[][] result = new double[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = r[i*n+j];
            }
        }
        return result;
    }
}
