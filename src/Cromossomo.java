import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 30/09/13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class Cromossomo {

    private Integer numeroGenes;
    private Genes[] genes;
    private final Integer TAMANHO_INDIVIDUO = 16;
    private String individuo = "";

    public Cromossomo(Integer numeroGenes) {
        this.numeroGenes = numeroGenes;
        genes = new Genes[numeroGenes];

        String caracteres = Algoritmo.getCaracteres();
        StringBuilder geneString = new StringBuilder();

        for(int i =0; i < numeroGenes; i++) {
            Integer tamanho = TAMANHO_INDIVIDUO / 2;
            genes[i] = new Genes(tamanho, criarGene(tamanho, caracteres));
        }

    }

    public Cromossomo(String individuo){
        this.numeroGenes = 2;
        genes = new Genes[numeroGenes];

        //Considerando somente para casos onde há 2 genes de 8 bits
        if(individuo.length() == TAMANHO_INDIVIDUO){

            Genes gene = new Genes(TAMANHO_INDIVIDUO/2, individuo.substring(0,7));
            Genes gene1 = new Genes(TAMANHO_INDIVIDUO/2, individuo.substring(8,15));

            genes[0] = gene;
            genes[1] = gene1;
        }

        //TODO aplicar taxa de mutação
    }

    private String criarGene(Integer tamanho, String caracteres){
        String gene = "";
        Random random = new Random();
        for(int i = 0; i < tamanho; i++){
            gene += caracteres.charAt( random.nextInt(2) );
        }

        return gene;
    }

    public String getIndividuo() {
        for(int i = 0; i < this.genes.length; i++){
           this.individuo += this.genes[i].getGene();
        }

        return individuo;
    }

    public void setIndividuo(String individuo) {
        this.individuo = individuo;
    }
}
