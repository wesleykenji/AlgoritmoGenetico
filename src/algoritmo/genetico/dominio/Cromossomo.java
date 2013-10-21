package algoritmo.genetico.dominio;

import algoritmo.genetico.util.AlgoritmoUtils;
import algoritmo.genetico.util.GeradorRandomico;

public class Cromossomo {

    private Integer numeroGenes;
    private Genes[] genes;
    private String individuo = "";
    private Double cromossomoDouble;

    public Cromossomo(Integer numeroGenes, Integer comprimento) {
        this.numeroGenes = numeroGenes;
        genes = new Genes[numeroGenes];

        StringBuilder geneString = new StringBuilder();

        for(int i =0; i < numeroGenes; i++) {
            genes[i] = new Genes(comprimento, criarGene(comprimento, AlgoritmoUtils.CODIGO_BINARIO));
        }

    }

    public Cromossomo(String individuo, Integer comprimento, Integer numeroGenes){
        genes = new Genes[numeroGenes];
        this.numeroGenes = numeroGenes;
        int posicaoInicial = 0;
        int posicaoFinal = comprimento;

        for(int i = 0; i < numeroGenes; i++){
            genes[i] = new Genes(comprimento, individuo.substring(posicaoInicial, posicaoFinal));
            posicaoInicial += comprimento;
            posicaoFinal += comprimento;
        }

    }

    private String criarGene(Integer tamanho, String caracteres){
        String gene = "";

        for(int i = 0; i < tamanho; i++){
            gene += caracteres.charAt(GeradorRandomico.obtemIndiceDeCaracterRandomico());
        }

        return gene;
    }

    public Genes[] getGenes() {
        return genes;
    }

    public String getIndividuo() {
        this.individuo = "";
        for(int i = 0; i < this.genes.length; i++){
            this.individuo += this.genes[i].getGene();
        }

        return individuo;
    }

    public void setIndividuo(String individuo) {
        this.individuo = individuo;
        int inicio = 0;
        int fim = individuo.length()/numeroGenes;

        for (int i = 0; i < this.numeroGenes; i++){
            this.getGenes()[i] = new Genes(individuo.length()/numeroGenes, individuo.substring(inicio, fim));
            inicio = fim;
            fim += individuo.length()/numeroGenes;
        }
    }

    public Double getCromossomoDouble() {
        return cromossomoDouble;
    }

    public void setCromossomoDouble(Double cromossomoDouble) {
        this.cromossomoDouble = cromossomoDouble;
    }
}
