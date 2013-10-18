package algoritmo.genetico;

import algoritmo.genetico.dominio.Genes;
import algoritmo.genetico.dominio.RestricoesLaterais;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 30/09/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class GeneticaMain {

    public static void main(String[] args){
        //CalculadoraGenetica calculadoraGenetica = new CalculadoraGenetica();
        //calculadoraGenetica.adaptacao(new BigDecimal[7]);

        AlgoritmoGenetico algoritmoGenetico = new Algoritmo();

        //calculaAlgoritmoGenetico(numeroGenes,tamanhoDaPopulacao,xu,xl)

        RestricoesLaterais restricoesLaterais = new RestricoesLaterais(new BigDecimal(10), new BigDecimal(8));
        Integer numeroGenes = 2;
        Integer tamanhoDaPopulacao = 6;
        Integer comprimentoGene = 8;
        algoritmoGenetico.calculaAlgoritmoGenetico(numeroGenes, tamanhoDaPopulacao, restricoesLaterais, comprimentoGene);
    }
}
