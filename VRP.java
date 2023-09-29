package vrp;

import java.io.IOException;

public class VRP {
    public static void main(String[] args) throws IOException{
        // Caminho para o arquivo
        Grafo g = new Grafo("src\\vrp\\Tam130.3.txt");
        
        int cidadesNaoVisitadas = g.Ck.length;
        
        // Quantidade de veiculos 
        int numeroDeVeiculos = 5;
        
        int maximo=g.teto(cidadesNaoVisitadas,numeroDeVeiculos);
        int matrizResposta[][] = new int [numeroDeVeiculos][maximo];
        int veiculo=0;
        int numrepete=0;
        int ondeEuTo=0;
        while(cidadesNaoVisitadas>1){
            if(numrepete==maximo){
                numrepete=0;
                veiculo++;
                continue;
            }
            if(numrepete==0){
                ondeEuTo=g.menorCk();
                g.Ck[ondeEuTo]=0;
                matrizResposta[veiculo][numrepete]=ondeEuTo;
            }else{
                ondeEuTo=g.menorA(ondeEuTo);
                matrizResposta[veiculo][numrepete]=ondeEuTo;
            }
            numrepete++;
            cidadesNaoVisitadas--;
        }
        if(veiculo<numeroDeVeiculos){
            int r=(numeroDeVeiculos-veiculo-1);
            System.out.println("Você tem "+r+" veiculo(s) sobrando, pois a divisão de cidades por veiculos não é inteira");
        }
        int custoTotal=0;
        for (int i = 0; i < numeroDeVeiculos; i++) {
            int anterior=0;
            int somatorio=0;
            for (int j = 0; j < maximo; j++) {
                somatorio+=g.grafo[anterior][matrizResposta[i][j]];
                anterior=matrizResposta[i][j];
            }
            somatorio+=g.grafo[anterior][0];
            custoTotal+=somatorio;
            System.out.print("custo do veiculo "+i+" -> "+somatorio);
            System.out.println("");
        }
        System.out.println("custo total -> "+custoTotal);
    }
}
