import java.math.BigDecimal;

import javax.swing.JOptionPane;


/**
 * 
 * @author Leonardo Seiji
 * @date   2013-09-26
 * @version 1.0
 */
public class CalculaRaiz {
	
	/**
	 * Recebe um valor e calcula a raiz quadrada
	 * 
	 * @param valor RAIZ QUADRADA
	 * @param chute VALOR CHUTE
	 * @param erro  VALOR ERRO
	 * @return
	 */
	public Double calcula(double valor, double chute, double erro){
		double resultado = 0.0;
		double erroInterno = 0.1;
		
		while(Math.abs(erroInterno) > erro){
			resultado = chute - ((chute*chute - valor)/(2*chute));
			
			erroInterno = resultado - chute;
			
			chute = resultado;
		}
			
		return resultado;
	}
	
	public static void main(String[] args) {
		///		Inputs		
		String raiz = JOptionPane.showInputDialog("Digite o numero que se desjea fazer a raiz: ");
		String erro = JOptionPane.showInputDialog("Digite o numero referente ao erro: ");
		String chutei = JOptionPane.showInputDialog("Digite o numero do chute inicial: ");

		/// 	Convers�es
		try {
			Double.parseDouble(raiz);
			Double.parseDouble(chutei);
			Double.parseDouble(erro);
		} catch ( Exception e) {
			JOptionPane.showMessageDialog(null, "Digite apenas n�meros");
			System.exit(0);
		}
		
		///		C�lculos
		Double valor = new CalculaRaiz().calcula(new Double(raiz), new Double(chutei) , new Double(erro));
		JOptionPane.showMessageDialog(null, "Resultado: "+new BigDecimal(valor).setScale(7,BigDecimal.ROUND_HALF_UP));
	}
}
		
/*		
				///		new CalculaRaiz().calcula(new BigDecimal(args[0]));
		Double valor = new CalculaRaiz().calcula(new Double(8), new Double(4.5) , new Double(0.000001));
		System.out.println("Resultado: "+new BigDecimal(valor).setScale(7,BigDecimal.ROUND_HALF_UP));
	}
}
*/