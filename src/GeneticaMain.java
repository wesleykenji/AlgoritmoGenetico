import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 30/09/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class GeneticaMain {

    public static void main(String[] args){
//        CalculadoraGenetica calculadoraGenetica = new CalculadoraGenetica();
//        Double random = calculadoraGenetica.geraNumeroRandomico();
//        System.out.println(random);
//        DecimalFormat df = new DecimalFormat("0.000");
//        System.out.println( df.format(random) );

          AlgoritmoGenetico algoritmoGenetico = new Algoritmo();

          algoritmoGenetico.calculaAlgoritmoGenetico(2,6,new BigDecimal(10),new BigDecimal(8));
    }
}
