package algoritmo.genetico;

import java.util.*;

import algoritmo.genetico.dominio.Cromossomo;
import algoritmo.genetico.dominio.Genes;
import algoritmo.genetico.dominio.Populacao;
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
    private static final int TOTAL_MELHORES = 6;
    private static final int TOTAL_CROSSOVER = 90;
    private static final int TOTAL_CROSSOVER_EXTRA = 1;
    private static final int TOTAL_CROMOSSOMOS = 100;
    /*
    public BigDecimal reproducaoEAdaptacao(Genes[] genes, RestricoesLaterais restricoesLaterais, Integer comprimento){
        String gene = "";
        String adaptacao = "";
        BigDecimal[] genesAdaptacao = new BigDecimal[genes.length];
        gene = reproducao(genes, restricoesLaterais, comprimento, gene, genesAdaptacao);

        System.out.println("Cromossomo em Decimal: " + gene);
        return this.adaptacao(genesAdaptacao);
    } */

    public String reproducao(Genes[] genes, RestricoesLaterais restricoesLaterais, Integer comprimento, BigDecimal[] genesAdaptacao) {
        String gene = "";

        for(int i = 0; i < genes.length; i++){
            Double valor = this.converteBinarioEmDecimal(genes[i].getGene());

            BigDecimal resultado = calcularCodigoGenetico(restricoesLaterais, comprimento, valor);
            genesAdaptacao[i] = resultado;
            gene += valor;
            gene += " ";
        }

        return gene;
    }

    public BigDecimal adaptacao(BigDecimal[] genesAdaptacao){

        BigDecimal x = genesAdaptacao[0];
        BigDecimal y = genesAdaptacao[1];
        BigDecimal resultado = this.funcaoObjetiva(x, y);

        return resultado;
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

    public BigDecimal[] obtemValorDeMelhorResultado(BigDecimal resultadoAdaptacao, BigDecimal[] resultPopInicial) {
        //TODO
        BigDecimal[] resultado = new BigDecimal[resultPopInicial.length];
        BigDecimal valor = BigDecimal.ZERO;
        for(int i = 0; i < resultPopInicial.length; i++){
            resultado[i] = valor.add(resultPopInicial[i].divide(resultadoAdaptacao));
        }

        return resultado;
    }

    public void criaCrossoverMutado(Populacao populacao, Integer comprimento, Integer numeroGenes){
        //List<Cromossomo> novosCromossomos = new ArrayList<Cromossomo>();
        Random random = new Random();
        List<Integer> index = obterPopulacaoParaCrossOver(this.geraVetorRandomico(populacao.getTamanhoDaPopulacao()), populacao);
        this.criaCrossover(populacao, this.numeroRandomicoParaRetornoDeK(comprimento, numeroGenes).intValue(), index);
        this.mutacao(Arrays.asList(populacao.getIndividuo()));
    }

    private List<Integer> obterPopulacaoParaCrossOver(BigDecimal[] rand, Populacao populacao) {
        List<Integer> index = new ArrayList<Integer>();
        for(int i = 0; i < populacao.getTamanhoDaPopulacao(); i++){
            if(rand[i].doubleValue() < populacao.getIndividuo()[i].getCromossomoDouble()){
                index.add(i);
            }
        }

        return index;
    }

    private void criaCrossover(Populacao populacao, Integer randomK, List<Integer> index) {

        for(int i = 0; i < index.size(); i = i + 2){
            Cromossomo cromossomo = populacao.getIndividuo()[index.get(i)];

            if(index.size() % 2 == 0){
                geraCrossover(cromossomo, populacao.getIndividuo()[index.get(i) + 1], randomK);
            }

        }
    }

    private void geraCrossover(Cromossomo cromossomo, Cromossomo novo, Integer randomK){

        String c1 = cromossomo.getIndividuo().substring(randomK - 1);
        String c2 = novo.getIndividuo().substring(randomK - 1);

        cromossomo.setIndividuo(cromossomo.getIndividuo().substring(0, randomK - 1) + c2);
        novo.setIndividuo(novo.getIndividuo().substring(0, randomK - 1) + c1);

    }

    private BigDecimal calcularCodigoGenetico(RestricoesLaterais restricoesLaterais, Integer comprimento, Double valor) {
        return BigDecimal.valueOf(valor).multiply(restricoesLaterais.getXu().subtract(restricoesLaterais.getXl()))
                .divide(((new BigDecimal(2).pow(comprimento)).subtract(BigDecimal.ONE)), 2, RoundingMode.CEILING)
                .add(restricoesLaterais.getXl());
    }

    private BigDecimal funcaoObjetiva(BigDecimal x, BigDecimal y){
        //max g(x, y) = - [x sen (4x) + 1,1 y sen (2y)]
        return x.multiply( BigDecimal.valueOf(Math.sin(4 * x.doubleValue()) ))
                .add(new BigDecimal(1.1)
                        .multiply(y.multiply(
                                BigDecimal.valueOf(Math.sin(2 * y.doubleValue()))
                        ))
                ).negate();
    }

    private Double converteBinarioEmDecimal(String binario){
        Double valor = new Double(0);
        // soma ao valor final o d�gito bin�rio da posi��o * 2 elevado ao contador da posi��o (come�a em 0)
        for (int i = binario.length() - 1; i >= 0; i--) {
            valor += Integer.parseInt( binario.charAt(i) + "" ) * Math.pow( 2, ((binario.length() -1) - i ) );
        }

        return valor;
    }

    public BigDecimal[] geraVetorRandomico(Integer tamanhoPopulacao){
        BigDecimal[] vetor = new BigDecimal[tamanhoPopulacao];
        Random random = new Random();
        for(int i = 0; i < tamanhoPopulacao; i++){
            vetor[i] = new BigDecimal(random.nextDouble());
        }

        return vetor;
    }

    public Populacao separaMelhores(BigDecimal[] vetorRand, BigDecimal[] probabilidade, Map<BigDecimal, Cromossomo> individuo) {
        //TODO
        int contador = 0;
        Cromossomo[] cromossomos = new Cromossomo[individuo.size()];
        while(vetorRand.length > contador){
            cromossomos[contador] = compareEObtenhaOMelhor(vetorRand[contador], probabilidade, individuo);
            contador++;
        }

        return new Populacao(cromossomos);
    }

    private Cromossomo compareEObtenhaOMelhor(BigDecimal vetorRand, BigDecimal[] probabilidade, Map<BigDecimal, Cromossomo> individuo) {
        int i = 0;
        boolean encontrou = false;
        while(!encontrou){
            if(vetorRand.compareTo(probabilidade[i]) == -1){
                encontrou = true;
                break;
            } else if(vetorRand.compareTo(probabilidade[i]) == 1){
                encontrou = false;
                i++;
                continue;
            }
        }

        return individuo.get(probabilidade[i]);
    }

    public Double numeroRandomicoParaRetornoDeK(Integer comprimento, Integer numeroGenes){
        Random random = new Random();
        return 1 + random.nextDouble() * ((comprimento * numeroGenes - 1) - 1);
    }

    private String converteDecimalEmBinario(BigDecimal decimal) {
        int resto = -1;
        StringBuilder sb = new StringBuilder();
        int valor = decimal.intValue();
        if (valor == 0) {
            return "0";
        }

        // enquanto o resultado da divis�o por 2 for maior que 0 adiciona o resto ao in�cio da String de retorno
        while (valor > 0) {
            resto = valor % 2;
            valor = valor / 2;
            sb.insert(0, resto);
        }

        return sb.toString();
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
