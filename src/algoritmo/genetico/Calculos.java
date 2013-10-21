package algoritmo.genetico;

import algoritmo.genetico.dominio.RestricoesLaterais;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: wesley.oyama
 * Date: 18/10/13
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class Calculos {

    public Integer obtemTotalBits(Integer tamanhoPopulacao, Integer numeroGenes, Integer comprimento){
        return tamanhoPopulacao * numeroGenes * comprimento;
    }

    public BigDecimal[] obtemValorDeMelhorResultado(BigDecimal resultadoAdaptacao, BigDecimal[] resultPopInicial) {
        BigDecimal[] resultado = new BigDecimal[resultPopInicial.length];

        BigDecimal valor = BigDecimal.ZERO;
        for(int i = 0; i < resultPopInicial.length; i++){
            valor = valor.add(resultPopInicial[i].divide(resultadoAdaptacao, RoundingMode.HALF_EVEN));
            resultado[i] = valor;
        }

        return resultado;
    }

    public BigDecimal calcularCodigoGenetico(RestricoesLaterais restricoesLaterais, Integer comprimento, Double valor) {
        return BigDecimal.valueOf(valor).multiply(restricoesLaterais.getXu().subtract(restricoesLaterais.getXl()))
                .divide(((new BigDecimal(2).pow(comprimento)).subtract(BigDecimal.ONE)), 3, RoundingMode.CEILING)
                .add(restricoesLaterais.getXl());
    }

    public BigDecimal funcaoObjetiva(BigDecimal x, BigDecimal y){
        //max g(x, y) = - [x sen (4x) + 1,1 y sen (2y)]
        x = x.setScale(2, RoundingMode.HALF_EVEN);
        y = y.setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal total = x.multiply( BigDecimal.valueOf(Math.sin(4 * x.doubleValue()) ))
                .add(new BigDecimal(1.1)
                        .multiply(y.multiply(
                                BigDecimal.valueOf(Math.sin(2 * y.doubleValue()))
                        ))
                ).negate();

        return total;
    }

    public Integer indiceDaMutacao(Integer indice, Integer tamanhoIndividuo) {

        int validador = 0;
        int index = new BigDecimal(indice - tamanhoIndividuo).abs().intValue();

        while(indice / tamanhoIndividuo != validador){
            if(indice / tamanhoIndividuo == 0){
                index = indice;
                break;
            } else if(indice / tamanhoIndividuo == 1){
                index = indice - tamanhoIndividuo;
                break;
            } else {
                return indiceDaMutacao(indice - tamanhoIndividuo, tamanhoIndividuo);
            }
        }

        return index;
    }
}
