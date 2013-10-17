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

        //Criar Populacao inicial
        Populacao populacao = new Populacao(numeroGenes, tamanhoPopulacao, comprimento);
        for(int i = 0; i < populacao.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("C" + contador + ":" + populacao.getIndividuo()[i].getIndividuo());
        }

        /*Populacao populacao = new Populacao(numeroGenes,CalculadoraGenetica.getTotalCromossomos());
        for(int i = 0; i < populacaoInicial.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("pFi" + contador + ":" + populacaoInicial.getIndividuo()[i].getIndividuo());
        }*/

        for(int i = 0; i < populacao.getIndividuo().length; i++){
            //Avaliar Custos

            //Reprodução
            calculadoraGenetica.reproducao(populacao.getIndividuo()[i].getGenes(), new RestricoesLaterais(xu, xl), comprimento);
            //Cruzamento e mutação
            calculadoraGenetica.criaCrossoverMutado(populacao);
            //Teste de convergencia

        }

        return this;
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        Algoritmo.caracteres = caracteres;
    }
}
