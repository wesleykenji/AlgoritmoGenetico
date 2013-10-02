/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 01/10/13
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class Algoritmo implements AlgoritmoGenetico {

    private static String caracteres = "01";

    @Override
    public void calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoPopulacao) {
        //Criar Populacao
        Populacao populacao = new Populacao(numeroGenes, tamanhoPopulacao);
        for(int i = 0; i < populacao.getIndividuo().length; i++){
            System.out.println("p" + i + ":" + populacao.getIndividuo()[i].getIndividuo());
        }
        //Avaliar Custos

        //Reprodução

        //Cruzamento

        //Mutação

        //Teste de convergencia
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        Algoritmo.caracteres = caracteres;
    }
}
