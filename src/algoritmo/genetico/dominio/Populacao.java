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

        for(int i = 0; i < individuo.length; i++){
            individuo[i] = new Cromossomo(numeroGenes, comprimento);
        }
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
