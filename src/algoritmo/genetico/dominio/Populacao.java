package algoritmo.genetico.dominio;

/**
 * Created with IntelliJ IDEA.
 * User: wesleykenji
 * Date: 01/10/13
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class Populacao {

    private Cromossomo[] individuo;
    private Integer tamanhoDaPopulacao;

    public Populacao(Integer numeroGenes, Integer tamanhoDaPopulacao, Integer comprimento) {
        this.tamanhoDaPopulacao = tamanhoDaPopulacao;
        this.individuo = new Cromossomo[tamanhoDaPopulacao];

        individuo[0] = new Cromossomo("1000010100100111", comprimento, numeroGenes);
        individuo[1] = new Cromossomo("0000111000001001", comprimento, numeroGenes);
        individuo[2] = new Cromossomo("1001000100000001", comprimento, numeroGenes);
        individuo[3] = new Cromossomo("1100010100101001", comprimento, numeroGenes);
        individuo[4] = new Cromossomo("0111110010101100", comprimento, numeroGenes);
        individuo[5] = new Cromossomo("1110001001001010", comprimento, numeroGenes);
        /*for(int i = 0; i < individuo.length; i++){
            individuo[i] = new Cromossomo(numeroGenes, comprimento);
        } */
    }

    public Populacao(Cromossomo[] cromossomos){
        this.individuo = cromossomos;
        this.tamanhoDaPopulacao = cromossomos.length;
    }

    public Cromossomo[] getIndividuo() {
        return individuo;
    }

    public void setIndividuo(Cromossomo[] individuo) {
        this.individuo = individuo;
    }

    public Integer getTamanhoDaPopulacao() {
        return tamanhoDaPopulacao;
    }

    public void setTamanhoDaPopulacao(Integer tamanhoDaPopulacao) {
        this.tamanhoDaPopulacao = tamanhoDaPopulacao;
    }
}
