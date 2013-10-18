package algoritmo.genetico;

import algoritmo.genetico.dominio.RestricoesLaterais;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 01/10/13
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public interface AlgoritmoGenetico {

    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoDaPopulacao, RestricoesLaterais restricoesLaterais, Integer comprimento);
}
