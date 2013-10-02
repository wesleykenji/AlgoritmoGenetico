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
    private Calculadora calculadora;

    @Override
    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoPopulacao, BigDecimal xu, BigDecimal xl) {

        CalculadoraGenetica calculadoraGenetica = new CalculadoraGenetica();

        //Criar Populacao
        Populacao populacao = new Populacao(numeroGenes, tamanhoPopulacao);
/*        for(int i = 0; i < populacao.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("p" + contador + ":" + populacao.getIndividuo()[i].getIndividuo());
        }*/
        for(int i = 0; i < populacao.getIndividuo().length; i++){
            //Avaliar Custos

            //Reprodução
            calculadoraGenetica.reproducao(populacao.getIndividuo()[i].getGenes());
            //Cruzamento

            //Mutação

            //Teste de convergencia

        }

        return this;
    }

    public Algoritmo comBaseNoMetodoZero(BigDecimal xu, BigDecimal xl){
        this.calculadora = new Calculadora(xu,xl);
        return this;
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        Algoritmo.caracteres = caracteres;
    }
}
