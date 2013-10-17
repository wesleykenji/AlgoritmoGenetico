package algoritmo.genetico;

import algoritmo.genetico.dominio.Genes;

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
          algoritmoGenetico.calculaAlgoritmoGenetico(2, 6, new BigDecimal(10), new BigDecimal(8), 8);
    }
}
