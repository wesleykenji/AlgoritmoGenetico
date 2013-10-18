package algoritmo.genetico;

import algoritmo.genetico.dominio.Cromossomo;
import algoritmo.genetico.dominio.Populacao;
import algoritmo.genetico.dominio.RestricoesLaterais;
import algoritmo.genetico.util.GeradorRandomico;

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

    private RestricoesLaterais calculadora;

    @Override
    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoPopulacao, RestricoesLaterais restricoesLaterais, Integer comprimento) {

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
        Map<Integer, Cromossomo> mapPopulacao = new HashMap<Integer,Cromossomo>();

        resultadoAdaptacao = reproduzirEAdaptar(restricoesLaterais, comprimento, calculadoraGenetica, populacaoInicial, resultadoAdaptacao, resultPopInicial, mapPopulacao);
        //System.out.println(resultadoAdaptacao);
        //Melhores Resultados
        //BigDecimal[] probabilidade = calculadoraGenetica.obtemValorDeMelhorResultado(resultadoAdaptacao, resultPopInicial);
        Populacao populacaoNova = calculadoraGenetica.separaMelhores(GeradorRandomico.geraVetorRandomico(tamanhoPopulacao),resultadoAdaptacao, resultPopInicial, mapPopulacao);

        //Cruzamento e mutação
        populacaoNova = calculadoraGenetica.criaCrossoverMutado(populacaoNova, comprimento, numeroGenes);
        //Teste de convergencia
        resultadoAdaptacao = reproduzirEAdaptar(restricoesLaterais, comprimento, calculadoraGenetica, populacaoInicial, resultadoAdaptacao, resultPopInicial, mapPopulacao);

        return this;
    }

    private BigDecimal reproduzirEAdaptar(RestricoesLaterais restricoesLaterais, Integer comprimento, CalculadoraGenetica calculadoraGenetica, Populacao populacaoInicial, BigDecimal resultadoAdaptacao, BigDecimal[] resultPopInicial, Map<Integer, Cromossomo> mapPopulacao) {
        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            //Avaliar Custos

            //Reprodução e adaptação
            //BigDecimal resultado = calculadoraGenetica.reproducaoEAdaptacao(populacao.getIndividuo()[i].getGenes(), new RestricoesLaterais(xu, xl), comprimento));
            BigDecimal[] genesAdaptacao = new BigDecimal[populacaoInicial.getIndividuo()[i].getGenes().length];
            String gene = calculadoraGenetica.reproducao(populacaoInicial.getIndividuo()[i].getGenes(), restricoesLaterais, comprimento, genesAdaptacao);

            //System.out.println("Gene após reprodução: " + gene);

            resultPopInicial[i] = calculadoraGenetica.adaptacao(genesAdaptacao);

            //System.out.println("valor da adaptação: " + resultPopInicial[i]);

            populacaoInicial.getIndividuo()[i].setCromossomoDouble(resultPopInicial[i].doubleValue());
            mapPopulacao.put(i, populacaoInicial.getIndividuo()[i]);

            resultadoAdaptacao = resultadoAdaptacao.add(resultPopInicial[i]);
        }

        return resultadoAdaptacao;
    }

}
