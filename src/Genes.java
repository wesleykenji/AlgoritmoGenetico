/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 30/09/13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public class Genes {

    private Integer comprimento;
    private Character[] alelos;
    private String gene = "";

    public Genes(Integer comprimento, String gene) {
        this.comprimento = comprimento;
        this.gene = gene;

        alelos = new Character[gene.length()];

        for(int i = 0; i < gene.length(); i++){
           alelos[i] = gene.charAt(i);
        }
    }

    public String getGene() {
        return gene;
    }

}
