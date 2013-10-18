package algoritmo.genetico;

import java.util.*;

import algoritmo.genetico.dominio.Cromossomo;
import algoritmo.genetico.dominio.Genes;
import algoritmo.genetico.dominio.Populacao;
import algoritmo.genetico.dominio.RestricoesLaterais;
import algoritmo.genetico.util.AlgoritmoUtils;
import algoritmo.genetico.util.GeradorRandomico;

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

    private static final int TOTAL_MELHORES = 6;
    private static final int TOTAL_CROSSOVER = 90;
    private static final int TOTAL_CROSSOVER_EXTRA = 1;
    private static final int TOTAL_CROMOSSOMOS = 100;

    private Calculos calculos;

    public CalculadoraGenetica(){
        this.calculos = new Calculos();
    }
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
            Double valor = AlgoritmoUtils.converteBinarioEmDecimal(genes[i].getGene());

            BigDecimal resultado = calculos.calcularCodigoGenetico(restricoesLaterais, comprimento, valor);
            genesAdaptacao[i] = resultado;
            gene += valor;
            gene += " ";
        }

        return gene;
    }

    public BigDecimal adaptacao(BigDecimal[] genesAdaptacao){

        BigDecimal x = genesAdaptacao[0];
        BigDecimal y = genesAdaptacao[1];
        BigDecimal resultado = calculos.funcaoObjetiva(x, y);

        return resultado.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void mutacao(Populacao populacao, Integer numeroGenes, Integer comprimento){
        Random random = new Random();
        String caracteres = AlgoritmoUtils.CODIGO_BINARIO;

        Integer totalDeBits = calculos.obtemTotalBits(populacao.getTamanhoDaPopulacao(), numeroGenes, comprimento);
        SortedMap<Integer, Double> indicesMutaveis = (SortedMap<Integer, Double>) GeradorRandomico.obterCaracterMutacao(totalDeBits);
        Integer contador = 0;
        Integer[] indices = (Integer[]) indicesMutaveis.keySet().toArray();
        Integer qtdBitsLidos = 0;

        for (int i = 0; i < populacao.getIndividuo().length; i++) {
            Integer comprimentoIndividuo = populacao.getIndividuo()[i].getIndividuo().length();
            qtdBitsLidos += comprimentoIndividuo * 2;
            char[] mutacao = populacao.getIndividuo()[i].getIndividuo().toCharArray();

            if(comprimentoIndividuo > indices[i]){
                mutacao[indices[i]] = (mutacao[indices[i]] == caracteres.charAt(0) ? caracteres.charAt(1) : caracteres.charAt(0));
                populacao.getIndividuo()[i].getIndividuo().toCharArray()[indices[i]] = mutacao[indices[i]];
            }

            if(qtdBitsLidos < indices[i+1]){
                i++;
            }
        }
    }

    public Populacao criaCrossoverMutado(Populacao populacao, Integer comprimento, Integer numeroGenes){
        //List<Cromossomo> novosCromossomos = new ArrayList<Cromossomo>();
        List<Integer> index = obterPopulacaoParaCrossOver(GeradorRandomico.geraVetorRandomico(populacao.getTamanhoDaPopulacao()), populacao);
        this.criaCrossover(populacao, GeradorRandomico.numeroRandomicoParaRetornoDeK(comprimento, numeroGenes).intValue(), index);
        this.mutacao(populacao, numeroGenes, comprimento);

        return populacao;
    }

    private List<Integer> obterPopulacaoParaCrossOver(BigDecimal[] rand, Populacao populacao) {
        List<Integer> index = new ArrayList<Integer>();
        boolean paresNaoDefinidos = true;
        int tamanho = populacao.getTamanhoDaPopulacao() % 2 == 0 ? populacao.getTamanhoDaPopulacao() : populacao.getTamanhoDaPopulacao() - 1;
        int i = 0;
        while(tamanho > i || index.size() % 2 == 1){
            if(i > tamanho) return index;
            double numero;

            if(i == tamanho && index.size() % 2 == 1){
                i--;
                numero = rand[0].doubleValue();
            } else {
                numero = rand[i].doubleValue();
            }

            if(numero < populacao.getIndividuo()[i].getCromossomoDouble()){
                index.add(i);
            }

            i++;
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

        String c1 = cromossomo.getIndividuo().substring(randomK);
        String c2 = novo.getIndividuo().substring(randomK);

        cromossomo.setIndividuo(cromossomo.getIndividuo().substring(0, randomK) + c2);
        novo.setIndividuo(novo.getIndividuo().substring(0, randomK) + c1);

    }

    public Populacao separaMelhores(BigDecimal[] vetorRand, BigDecimal resultadoAdaptacao, BigDecimal[] resultPopInicial, Map<Integer, Cromossomo> individuo) {
        //TODO
        int contador = 0;
        Cromossomo[] cromossomos = new Cromossomo[individuo.size()];
        BigDecimal[] probabilidade = calculos.obtemValorDeMelhorResultado(resultadoAdaptacao, resultPopInicial);

        while(vetorRand.length > contador){
            cromossomos[contador] = compareEObtenhaOMelhor(vetorRand[contador], probabilidade, individuo);
            contador++;
        }

        return new Populacao(cromossomos);
    }

    private Cromossomo compareEObtenhaOMelhor(BigDecimal vetorRand, BigDecimal[] probabilidade, Map<Integer, Cromossomo> individuo) {
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

        individuo.get(i).setCromossomoDouble(probabilidade[i].doubleValue());

        return individuo.get(i);
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
