package algoritmo.genetico;

import algoritmo.genetico.dominio.Genes;
import algoritmo.genetico.dominio.RestricoesLaterais;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public void reproducao(Genes[] genes, RestricoesLaterais restricoesLaterais, Integer comprimento){
        String gene = "";
        String adaptacao = "";
        BigDecimal[] genesAdaptacao = new BigDecimal[genes.length];
        for(int i = 0; i < genes.length; i++){
            Double valor = this.converteBinarioEmDecimal(genes[i].getGene());

            BigDecimal resultado = calcularCodigoGenetico(restricoesLaterais, comprimento, valor);
            genesAdaptacao[i] = resultado;
            gene += valor;
            gene += " ";
        }

        System.out.println("Cromossomo em Decimal: " + gene);
        this.adaptacao(genesAdaptacao);
    }

    private BigDecimal calcularCodigoGenetico(RestricoesLaterais restricoesLaterais, Integer comprimento, Double valor) {
        return BigDecimal.valueOf(valor).multiply(restricoesLaterais.getXu().subtract(restricoesLaterais.getXl()))
                        .divide(((new BigDecimal(2).pow(comprimento)).subtract(BigDecimal.ONE)), 2, RoundingMode.CEILING)
                        .add(restricoesLaterais.getXl());
    }

    public void mutacao(){

    }

    public void adaptacao(BigDecimal[] genesAdaptacao){

        BigDecimal x = genesAdaptacao[0];
        BigDecimal y = genesAdaptacao[1];
        x = x.multiply( BigDecimal.valueOf(Math.sin(4 * x.doubleValue()) )).add(BigDecimal.ONE).;
        y = BigDecimal.ONE.multiply(
                y.multiply(BigDecimal.valueOf(Math.sin( 2 * y.doubleValue() )))
        );
        //BigDecimal cromossomo = BigDecimal.valueOf(Double.parseDouble(x.toString() + "," + y.toString())).negate();

    }

    public Double geraNumeroRandomico(){
        //TODO
        Double random = StrictMath.random();
        return random;
    }

    public void calculaGenetica(String x, String y){
        //TODO
    }

    public Double converteBinarioEmDecimal(String binario){
        Double valor = new Double(0);
        // soma ao valor final o dígito binário da posição * 2 elevado ao contador da posição (começa em 0)
        for (int i = binario.length(); i > 0; i--) {
            valor += Integer.parseInt( binario.charAt(i-1) + "" ) * Math.pow( 2, (binario.length() - i ) );
        }

        return valor;
    }

//    public void gerarPopulacao(){
//         Map<Integer, algoritmo.genetico.dominio.Cromossomo> populacao = new HashMap<Integer, algoritmo.genetico.dominio.Cromossomo>();
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
