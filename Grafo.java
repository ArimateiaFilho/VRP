package vrp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.pow;



public class Grafo {
    public int grafo[][];
    public int Ck[];
    private int visitados[];
    //construtor
    public Grafo(String arq) throws IOException{
        BufferedReader buffRead = new BufferedReader(new FileReader(arq));
        String linha = buffRead.readLine();
        int n=Integer.parseInt(linha);
        this.grafo = new int[n][n];
        this.Ck = new int[n];
        this.visitados = new int[n];
        int x[] = new int[n];
        int y[] = new int[n];
        String aux[] = new String[2];
        for (int i = 0; i < n; i++) {
            this.grafo[i][i]=0;
            linha = buffRead.readLine();
            aux = linha.split(" ");
            x[i]=Integer.parseInt(aux[0]);
            y[i]=Integer.parseInt(aux[1]);
        }
        buffRead.close();
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                int r=D(x[i],x[j],y[i],y[j]);
                this.grafo[j][i]=this.grafo[i][j]=r;
            }
        }
        for (int i = 0; i < this.Ck.length; i++) {
            for (int j = 0; j < this.Ck.length; j++) {
                Ck[i]+=grafo[i][j];
            }
        }
        Ck[0]=0;
    }
    
    // d=raiz((xi-xj)²+(yi-yj)²)
    private int D(int x1,int x2, int y1, int y2){
        double result=0;
        result=(pow((x1-x2),2)+pow((y1-y2),2));
        result=pow(result,0.5);
        return (int)result;
    }
    
    public int menorCk(){
        int ind=0;
        int menor=0;
        for (int i = 1; i < Ck.length; i++) {
            if(Ck[i]>0&&visitados[i]==0){
                ind=i;
                menor=Ck[ind];
            }
        }
        for (int i = 2; i < Ck.length ; i++) {
            if(Ck[i]<menor&&Ck[i]>0&&visitados[i]==0){
                menor=Ck[i];
                ind = i;
            }
        }
        visitados[ind]=1;
        return ind;
    }
    
    public int menorA(int ind0){
        int menor=99999999;
        int ind1=1;
        for (int i = 1; i < Ck.length ; i++) {
            if(grafo[ind0][i]<menor&&grafo[ind0][i]>0&&visitados[i]==0){
                menor=grafo[ind0][i];
                ind1=i;
            }
        }
        visitados[ind1]=1;
        return ind1;
    }
    public int teto(double x, double y){
        int aux=(int)(x/y);
        double r=x/y;
        if((r-aux)>0){
            aux++;
        }
        return aux;
    }
}
