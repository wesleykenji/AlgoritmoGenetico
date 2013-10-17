package algoritmo.genetico.dominio;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: wesley.oyama
 * Date: 02/10/13
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */
public class RestricoesLaterais {

    private BigDecimal xu;
    private BigDecimal xl;

    public RestricoesLaterais(BigDecimal xu, BigDecimal xl) {
        this.xl = xl;
        this.xu = xu;
    }

    public BigDecimal getXu() {
        return xu;
    }

    public void setXu(BigDecimal xu) {
        this.xu = xu;
    }

    public BigDecimal getXl() {
        return xl;
    }

    public void setXl(BigDecimal xl) {
        this.xl = xl;
    }
}
