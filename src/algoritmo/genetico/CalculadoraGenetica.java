package algoritmo.genetico;

import java.util.HashMap;
import java.util.Map;

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
    private static final int TOTAL_MELHORES = 5;
    private static final int TOTAL_CROSSOVER = 90;
    private static final int TOTAL_CROSSOVER_EXTRA = 1;
    private static final int TOTAL_CROMOSSOMOS = 100;

    public void geraCromossomosRandomicos(){
        //TODO
    }

    public void funcaoObjetiva(){
        //max g(x, y) = - [x sen (4x) + 1,1 y sen (2y)]

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

    public void mutacao(List<Cromossomo> novosCromossomos){
        Random random = new Random();

        for (int i = 0; i < TOTAL_CROSSOVER_EXTRA; i++) {
            int index = random.nextInt(TOTAL_CROSSOVER) + TOTAL_MELHORES;
            int posicao = random.nextInt(9);

            Cromossomo cromossomo = novosCromossomos.get(index);
            char[] mutacao = cromossomo.getIndividuo().toCharArray();
            mutacao[posicao] = caracteres.charAt( mutacao[posicao] == caracteres.charAt(0) ? caracteres.charAt(1) : caracteres.charAt(0) );//random.nextInt(10-1)+1;

            novosCromossomos.set(index, cromossomo);
        }
    }

    public void criaCrossoverMutado(Populacao populacao){
        List<Cromossomo> novosCromossomos = new ArrayList<Cromossomo>();
        Random random = new Random();
        
        this.criaCrossover(populacao, novosCromossomos);
        //this.mutacao(novosCromossomos);
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
        for (int i = binario.length() - 1; i >= 0; i--) {
            valor += Integer.parseInt( binario.charAt(i) + "" ) * Math.pow( 2, ((binario.length() -1) - i ) );
        }

        return valor;
    }

    private void criaCrossover(Populacao populacao, List<Cromossomo> novosCromossomos) {

        for (int i = TOTAL_MELHORES; i < TOTAL_CROSSOVER + TOTAL_MELHORES; i++) {
            Cromossomo cromossomo = populacao.getIndividuo()[i];
            cromossomo.setIndividuo(geraCrossover(cromossomo.getIndividuo()));
            //cromossomo.setErro(Math.abs((c.getDcromossomo()*c.getDcromossomo())-5));

            novosCromossomos.add(cromossomo);
        }
    }

    public String geraCrossover(String cromossomo){
        char[] caracteres = cromossomo.toCharArray();
        Integer contador = cromossomo.length() / 2;
        char[] novoCromossomo = new char[8];

        for(Integer tamanho = 0; tamanho <= cromossomo.length(); tamanho++){
            novoCromossomo[tamanho] = caracteres[contador];

            if(contador == tamanho){
                contador = 0;
            }

            contador++;
        }

        return String.valueOf(novoCromossomo);
    }

    public static int getTotalMelhores() {
        return TOTAL_MELHORES;
    }

    public static int getTotalCrossover() {
        return TOTAL_CROSSOVER;
    }

    public static int getTotalCrossoverExtra() {
        return TOTAL_CROSSOVER_EXTRA;
    }

    public static int getTotalCromossomos() {
        return TOTAL_CROMOSSOMOS;
    }
}
