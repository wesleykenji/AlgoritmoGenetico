package algoritmo.genetico;

import algoritmo.genetico.dominio.Cromossomo;
import algoritmo.genetico.dominio.Genes;
import algoritmo.genetico.dominio.Populacao;
import algoritmo.genetico.dominio.RestricoesLaterais;
import algoritmo.genetico.util.AlgoritmoUtils;
import algoritmo.genetico.util.GeradorRandomico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 30/09/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
public class CalculadoraGenetica {

    private Calculos calculos;

    public CalculadoraGenetica(){
        this.calculos = new Calculos();
    }

    public String reproducao(Genes[] genes, RestricoesLaterais restricoesLaterais, Integer comprimento, BigDecimal[] genesAdaptacao) {
        String gene = "";
        System.out.println("Iniciando reprodução . . . ");
        for(int i = 0; i < genes.length; i++){
            Double valor = AlgoritmoUtils.converteBinarioEmDecimal(genes[i].getGene());

            BigDecimal resultado = calculos.calcularCodigoGenetico(restricoesLaterais, comprimento, valor);
            genesAdaptacao[i] = resultado;
            gene += valor;
            gene += " ";
        }
        System.out.println("Reprodução calculada!");
        return gene;
    }

    public BigDecimal adaptacao(BigDecimal[] genesAdaptacao){
        System.out.println("Iniciando Adaptação . . .");

        BigDecimal x = genesAdaptacao[0];
        BigDecimal y = genesAdaptacao[1];
        BigDecimal resultado = calculos.funcaoObjetiva(x, y);

        System.out.println("Adaptação Concluída!");

        return resultado.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Populacao mutacao(Populacao populacao, Integer numeroGenes, Integer comprimento){

        String caracteres = AlgoritmoUtils.CODIGO_BINARIO;

        System.out.println("Iniciando processo de mutação . . .");

        Integer contador = 0;
        Integer qtdBitsLidos = 0;
        int i = 0;
        int somaBits = 0;

        Integer totalDeBits = calculos.obtemTotalBits(populacao.getTamanhoDaPopulacao(), numeroGenes, comprimento);
        SortedMap<Integer, Double> indicesMutaveis = GeradorRandomico.obterCaracterMutacao(totalDeBits);
        Object[] indices = indicesMutaveis.keySet().toArray();


        Integer comprimentoIndividuo = populacao.getIndividuo()[contador].getIndividuo().length();

        while(indices.length > i) {

            int indice = 8;//new Integer(indices[i].toString());
            System.out.println("Indice a ser alterado na mutação: " + indice);
            //qtdBitsLidos += comprimentoIndividuo * 2;

            if(indice / comprimentoIndividuo > 1 && indice - somaBits > comprimentoIndividuo){
                contador = indice / comprimentoIndividuo;
                qtdBitsLidos = comprimentoIndividuo * (contador + 1);
                somaBits += comprimentoIndividuo * (contador + 1);
            } else if(indice / comprimentoIndividuo == 1){
                somaBits += comprimentoIndividuo;
                contador++;
            } else {
                somaBits += comprimentoIndividuo;
            }

            if(indice > somaBits) continue;

            if(somaBits >= indice){
                int index = calculos.indiceDaMutacao(indice, populacao.getIndividuo()[contador].getIndividuo().length());

                if(index == 0){
                    contador--;
                    index = populacao.getIndividuo()[contador].getIndividuo().length();
                }

                populacao.getIndividuo()[contador] = mutar(populacao.getIndividuo()[contador], caracteres, comprimento, index);
            }

            if(indice <= somaBits){
                i++;
                somaBits = 0;
            }

            contador++;
        }

        if(indices.length == 0){
            System.out.println("Não houve Mutação!");
        }

        System.out.println("Mutação concluída!");

        return populacao;
    }

    private Cromossomo mutar(Cromossomo cromossomo, String caracteres, Integer comprimento, int index) {

        System.out.println("mutar no indice " +  (index - 1) + " do cromossomo " + cromossomo.getIndividuo());

        char[] mutacao = cromossomo.getIndividuo().toCharArray();
        System.out.println("antes de mutar: " + mutacao[index-1]);
        mutacao[index-1] = (mutacao[index-1] == caracteres.charAt(0) ? caracteres.charAt(1) : caracteres.charAt(0));
        System.out.println("depois de mutado: " + mutacao[index-1]);
        cromossomo.setIndividuo(new String(mutacao));

        return cromossomo;
    }

    public Populacao criaCrossoverMutado(Populacao populacao, Integer comprimento, Integer numeroGenes){
        System.out.println("Iniciando processo de Crossover . . .");

        List<Integer> index = obterPopulacaoParaCrossOver(GeradorRandomico.geraVetorRandomico(populacao.getTamanhoDaPopulacao()), populacao);

        System.out.println("Tamanho da populacao a ser feita crossover: " + index.size());

        /*for(int i = 0; i < populacao.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("C antes do crossover" + contador + ":" + populacao.getIndividuo()[i].getIndividuo());
        } */

        populacao = this.criaCrossover(populacao, GeradorRandomico.numeroRandomicoParaRetornoDeK(comprimento, numeroGenes).intValue(), index);
        /*for(int i = 0; i < populacao.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("C depois do crossover" + contador + ":" + populacao.getIndividuo()[i].getIndividuo());
        }   */

        populacao = this.mutacao(populacao, numeroGenes, comprimento);
        /*for(int i = 0; i < populacao.getIndividuo().length; i++){
            int contador = i + 1;
            System.out.println("C depois da mutacao" + contador + ":" + populacao.getIndividuo()[i].getIndividuo());
        } */

        System.out.println("Finalizando Mutação e Crossover");
        return populacao;
    }

    private List<Integer> obterPopulacaoParaCrossOver(BigDecimal[] rand, Populacao populacao) {
        List<Integer> index = new ArrayList<Integer>();
        boolean paresNaoDefinidos = true;
        int tamanho = populacao.getTamanhoDaPopulacao() % 2 != 0 ? populacao.getTamanhoDaPopulacao() : populacao.getTamanhoDaPopulacao() - 1;
        int i = 0;
        int contadorEmergencial = 0;

        while(i < tamanho - 1 || index.size() % 2 == 1){
            if(i > tamanho) return index;
            double numero;

            if(i == tamanho && index.size() % 2 == 1){
                i--;
                numero = rand[contadorEmergencial].doubleValue();
                contadorEmergencial++;
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

    private Populacao criaCrossover(Populacao populacao, Integer randomK, List<Integer> index) {

        for(int i = 0; i < index.size(); i = i + 2){
            Cromossomo cromossomo = populacao.getIndividuo()[index.get(i)];

            if(index.size() % 2 == 0){
                populacao = geraCrossover(populacao, index.get(i), randomK);
            }

        }

        return populacao;
    }

    private Populacao geraCrossover(Populacao populacao, int i, Integer randomK){

        System.out.println("Iniciando crossover do par " + i + " e " + (i+1) + " com equivalente k = " + randomK);

        String c1 = populacao.getIndividuo()[i].getIndividuo().substring(randomK);
        String c2 = populacao.getIndividuo()[i+1].getIndividuo().substring(randomK);

        populacao.getIndividuo()[i].setIndividuo(populacao.getIndividuo()[i].getIndividuo().substring(0, randomK) + c2);
        populacao.getIndividuo()[i+1].setIndividuo(populacao.getIndividuo()[i+1].getIndividuo().substring(0, randomK) + c1);

        System.out.println("Crossover do par concluído!");

        return populacao;
    }

    public Populacao separaMelhores(BigDecimal[] vetorRand, BigDecimal resultadoAdaptacao, BigDecimal[] resultPopInicial, Map<Integer, Cromossomo> individuo) {

        System.out.println("Obtendo os Melhores cromossomos . . .");

        int contador = 0;
        Cromossomo[] cromossomos = new Cromossomo[individuo.size()];
        BigDecimal[] probabilidade = calculos.obtemValorDeMelhorResultado(resultadoAdaptacao, resultPopInicial);

        while(vetorRand.length > contador){
            cromossomos[contador] = compareEObtenhaOMelhor(vetorRand[contador], probabilidade, individuo);
            contador++;
        }

        System.out.println("Retornando melhores cromossomos como uma nova população");
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

        return populaCromossomo(individuo.get(i));
    }

    private Cromossomo populaCromossomo(Cromossomo cromossomo) {
        Integer numeroGenes = cromossomo.getGenes().length;
        Integer comprimento = cromossomo.getGenes()[0].getGene().length();

        Cromossomo cromossomoNovo = new Cromossomo(cromossomo.getIndividuo(), comprimento, numeroGenes);
        cromossomoNovo.setCromossomoDouble(cromossomo.getCromossomoDouble());
        return cromossomoNovo;
    }
}
