package algoritmo.genetico;

import algoritmo.genetico.dominio.Populacao;
import algoritmo.genetico.dominio.RestricoesLaterais;

import java.math.BigDecimal;

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

        //Criar algoritmo.genetico.dominio.Populacao
        Populacao populacao = new Populacao(numeroGenes, tamanhoPopulacao, comprimento);
        for(int i = 0; i < populacao.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("C" + contador + ":" + populacao.getIndividuo()[i].getIndividuo());
        }
        for(int i = 0; i < populacao.getIndividuo().length; i++){
            //Avaliar Custos

            //Reprodução
            calculadoraGenetica.reproducao(populacao.getIndividuo()[i].getGenes(), new RestricoesLaterais(xu, xl), comprimento);
            //Cruzamento

            //Mutação

            //Teste de convergencia

        }

        return this;
    }

    public Algoritmo comBaseNoMetodoZero(BigDecimal xu, BigDecimal xl){
        this.calculadora = new RestricoesLaterais(xu,xl);
        return this;
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        Algoritmo.caracteres = caracteres;
    }
}
