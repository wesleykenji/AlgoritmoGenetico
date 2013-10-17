package algoritmo.genetico;

import algoritmo.genetico.dominio.Cromossomo;
import algoritmo.genetico.dominio.Populacao;
import algoritmo.genetico.dominio.RestricoesLaterais;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 01/10/13
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class Algoritmo implements AlgoritmoGenetico {

    private static String caracteres = "01";
    private RestricoesLaterais calculadora;

    @Override
    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoPopulacao, BigDecimal xu, BigDecimal xl, Integer comprimento) {

        CalculadoraGenetica calculadoraGenetica = new CalculadoraGenetica();

        //Criar Populacao inicial
        Populacao populacaoInicial = new Populacao(numeroGenes, tamanhoPopulacao, comprimento);
        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("Cinicial" + contador + ":" + populacaoInicial.getIndividuo()[i].getIndividuo());
        }

        //Criar populacao das gerações
        /*Populacao populacao = new Populacao(numeroGenes,CalculadoraGenetica.getTotalCromossomos());
        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("pFi" + contador + ":" + populacaoInicial.getIndividuo()[i].getIndividuo());
        }*/

        BigDecimal resultadoAdaptacao = BigDecimal.ZERO;
        BigDecimal[] resultPopInicial = new BigDecimal[populacaoInicial.getIndividuo().length];
        Map<BigDecimal, Cromossomo> mapPopulacao = new HashMap<BigDecimal,Cromossomo>();

        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            //Avaliar Custos

            //Reprodução e adaptação
            //BigDecimal resultado = calculadoraGenetica.reproducaoEAdaptacao(populacao.getIndividuo()[i].getGenes(), new RestricoesLaterais(xu, xl), comprimento));
            BigDecimal[] genesAdaptacao = new BigDecimal[populacaoInicial.getIndividuo()[i].getGenes().length];
            calculadoraGenetica.reproducao(populacaoInicial.getIndividuo()[i].getGenes(), new RestricoesLaterais(xu, xl), comprimento, genesAdaptacao);
            resultPopInicial[i] = calculadoraGenetica.adaptacao(genesAdaptacao);

            populacaoInicial.getIndividuo()[i].setCromossomoDouble(resultPopInicial[i].doubleValue());
            mapPopulacao.put(resultPopInicial[i], populacaoInicial.getIndividuo()[i]);

            resultadoAdaptacao.add(resultPopInicial[i]);
        }

        //Melhores Resultados
        BigDecimal[] probabilidade = calculadoraGenetica.obtemValorDeMelhorResultado(resultadoAdaptacao, resultPopInicial);
        Populacao populacaoNova = calculadoraGenetica.separaMelhores(calculadoraGenetica.geraVetorRandomico(tamanhoPopulacao),probabilidade, mapPopulacao);
        //Cruzamento e mutação
        calculadoraGenetica.criaCrossoverMutado(populacaoInicial, comprimento, numeroGenes);
        //Teste de convergencia
        return this;
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        Algoritmo.caracteres = caracteres;
    }
}
