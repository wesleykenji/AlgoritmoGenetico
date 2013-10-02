import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 30/09/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class CalculadoraGenetica {

    private String caracteres = "01";

    public void geraCromossomosRandomicos(){
        //TODO
    }

    public void funcaoObjetiva(){
        //max g(x, y) = - [x sen (4x) + 1,1 y sen (2y)]

    }

    public void utilizarAlgoritmoGenetico(){

    }

    public void reproducao(){

    }

    public void mutacao(){

    }

    public void adaptacao(){

    }

    public Double geraNumeroRandomico(){
        //TODO
        Double random = StrictMath.random();
        return random;
    }

    public void calculaGenetica(String x, String y){
        //TODO
    }

    public void converteBinarioEmDecimal(String binario){
        //TODO
    }

//    public void gerarPopulacao(){
//         Map<Integer, Cromossomo> populacao = new HashMap<Integer, Cromossomo>();
//
//         resultado = xl + geraNumeroRandomico()*()
//    }

//    public void calcularEmOrdemZero(String xl, String xu, Integer qmax){
//        Map<Integer, String> resultado = new HashMap<Integer, String>();
//        String f0;
//        String f1;
//        Integer q = 1;
//
//        String teste = calcularComBaseNoMetodoZero(xl, xu);
//
//        f0 = FUNCAO(teste);
//
//        while(q < qmax){
//
//            resultado.put(q, calcularComBaseNoMetodoZero(xl, xu));
//            String r = resultado.get(q);
//            f1 = FUNCAO();
//            q = q + 1;
//
//            if(f1 < f0){
//               teste = r;
//                f0 = f1;
//            }
//        }
//    }

//    private String calcularComBaseNoMetodoZero(String xl, String xu) {
//        return xl + geraNumeroRandomico() * ( xu - xl);
//    }
}
