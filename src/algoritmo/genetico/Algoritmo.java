package algoritmo.genetico;

import algoritmo.genetico.dominio.Cromossomo;
import algoritmo.genetico.dominio.Populacao;
import algoritmo.genetico.dominio.RestricoesLaterais;
import algoritmo.genetico.util.GeradorRandomico;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Algoritmo implements AlgoritmoGenetico {

    @Override
    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoPopulacao, RestricoesLaterais restricoesLaterais, Integer comprimento) {

        CalculadoraGenetica calculadoraGenetica = new CalculadoraGenetica();

        //Criar Populacao inicial
        Populacao populacaoInicial = new Populacao(numeroGenes, tamanhoPopulacao, comprimento);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("Cinicial" + contador + ":" + populacaoInicial.getIndividuo()[i].getIndividuo());
            builder.append("Cinicial" + contador + ":" + populacaoInicial.getIndividuo()[i].getIndividuo() + " \n");
        }
        JOptionPane.showMessageDialog(null, builder.toString(), "Populacao Inicial", JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(null, resultadoAdaptacao, "Resultado antes de algumas etapas (fx objetiva)", JOptionPane.INFORMATION_MESSAGE);
        //Melhores Resultados
        Populacao populacaoNova = calculadoraGenetica.separaMelhores(GeradorRandomico.geraVetorRandomico(tamanhoPopulacao),resultadoAdaptacao, resultPopInicial, mapPopulacao);

        //Cruzamento e mutação
        populacaoNova = calculadoraGenetica.criaCrossoverMutado(populacaoNova, comprimento, numeroGenes);

        //Teste de convergencia
        resultadoAdaptacao = reproduzirEAdaptar(restricoesLaterais, comprimento, calculadoraGenetica, populacaoNova, resultadoAdaptacao, resultPopInicial, mapPopulacao);
        JOptionPane.showMessageDialog(null, resultadoAdaptacao, "Resultado após todas as etapas (fx objetiva)", JOptionPane.INFORMATION_MESSAGE);

        return this;
    }

    private BigDecimal reproduzirEAdaptar(RestricoesLaterais restricoesLaterais, Integer comprimento, CalculadoraGenetica calculadoraGenetica, Populacao populacaoInicial, BigDecimal resultadoAdaptacao, BigDecimal[] resultPopInicial, Map<Integer, Cromossomo> mapPopulacao) {
        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            //Reprodução e adaptação
            BigDecimal[] genesAdaptacao = new BigDecimal[populacaoInicial.getIndividuo()[i].getGenes().length];
            String gene = calculadoraGenetica.reproducao(populacaoInicial.getIndividuo()[i].getGenes(), restricoesLaterais, comprimento, genesAdaptacao);

            resultPopInicial[i] = calculadoraGenetica.adaptacao(genesAdaptacao);

            populacaoInicial.getIndividuo()[i].setCromossomoDouble(resultPopInicial[i].doubleValue());
            mapPopulacao.put(i, populacaoInicial.getIndividuo()[i]);

            resultadoAdaptacao = resultadoAdaptacao.add(resultPopInicial[i]);
        }

        return resultadoAdaptacao;
    }

}
