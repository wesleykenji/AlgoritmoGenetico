package algoritmo.genetico;

import algoritmo.genetico.dominio.RestricoesLaterais;

import javax.swing.*;
import java.math.BigDecimal;

public class GeneticaMain {

    public static void main(String[] args){

        AlgoritmoGenetico algoritmoGenetico = new Algoritmo();

        ///		Inputs
        String nGene = JOptionPane.showInputDialog("Digite o numero de genes que deve ter (Default 2): ");
        String nPop = JOptionPane.showInputDialog("Digite o numero do tamanho da população (Default 6): ");
        String nComprimento = JOptionPane.showInputDialog("Digite o comprimento do gene (Default 8): ");
        String nXu = JOptionPane.showInputDialog("Digite a restrição lateral maior (Default 10): ");
        String nXl = JOptionPane.showInputDialog("Digite a restrição lateral menor (Default 8): ");
        //calculaAlgoritmoGenetico(numeroGenes,tamanhoDaPopulacao,xu,xl)

        RestricoesLaterais restricoesLaterais = new RestricoesLaterais(nXu.isEmpty() ? new BigDecimal(10) : new BigDecimal(nXu),
                nXl.isEmpty() ? new BigDecimal(8): new BigDecimal(nXl));
        Integer numeroGenes = nGene.isEmpty() ? 2 : new Integer(nGene);//2;
        Integer tamanhoDaPopulacao = nPop.isEmpty() ? 6 : new Integer(nPop);//6;
        Integer comprimentoGene = nComprimento.isEmpty() ? 8 : new Integer(nComprimento);//8;

        algoritmoGenetico.calculaAlgoritmoGenetico(numeroGenes, tamanhoDaPopulacao, restricoesLaterais, comprimentoGene);
    }
}
