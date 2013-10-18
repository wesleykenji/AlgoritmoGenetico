package algoritmo.genetico.util;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: wesley.oyama
 * Date: 18/10/13
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class AlgoritmoUtils {

    public static final String CODIGO_BINARIO = "01";
    public static final Double PORCENTAGEM_MUTACAO = 0.01;

    public static Double converteBinarioEmDecimal(String binario){
        Double valor = new Double(0);
        // soma ao valor final o d�gito bin�rio da posi��o * 2 elevado ao contador da posi��o (come�a em 0)
        for (int i = binario.length() - 1; i >= 0; i--) {
            valor += Integer.parseInt( binario.charAt(i) + "" ) * Math.pow( 2, ((binario.length() -1) - i ) );
        }

        return valor;
    }

    public static String converteDecimalEmBinario(BigDecimal decimal) {
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
}
