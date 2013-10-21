package algoritmo.genetico.dominio;

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
