package algoritmo.genetico.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wesley.oyama
 * Date: 18/10/13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class GeradorRandomico {

    private static Random random;

    static {
        random = new Random();
    }

    public static BigDecimal numeroRandomicoParaRetornoDeK(Integer comprimento, Integer numeroGenes){
        return new BigDecimal(1 + random.nextDouble() * ( (comprimento * numeroGenes - 1) - 1) ).setScale(1,RoundingMode.UP);
    }

    public static BigDecimal[] geraVetorRandomico(Integer tamanhoPopulacao){
        BigDecimal[] vetor = new BigDecimal[tamanhoPopulacao];

        for(int i = 0; i < tamanhoPopulacao; i++){
            vetor[i] = new BigDecimal(random.nextDouble());
        }

        return vetor;
    }

    public static void gerarParesRandomicosMutacao(){

    }

    public static Map<Integer, Double> obterCaracterMutacao(Integer totalDeBits) {

        Map<Integer, Double> listaIndicesMutaveis = new HashMap<Integer, Double>();

        for(int i = 0; i < totalDeBits; i++){
            Double gerado = random.nextDouble();
            if(gerado < AlgoritmoUtils.PORCENTAGEM_MUTACAO){
                listaIndicesMutaveis.put(i, gerado);
            }
        }

        return listaIndicesMutaveis;
    }

    public static Integer obtemIndiceDeCaracterRandomico(){
        return random.nextInt(2);
    }
}
