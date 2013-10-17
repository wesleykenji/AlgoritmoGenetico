package algoritmo.genetico;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 01/10/13
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public interface AlgoritmoGenetico {

    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoDaPopulacao, BigDecimal xu,
                                              BigDecimal xl, Integer comprimento);
}
