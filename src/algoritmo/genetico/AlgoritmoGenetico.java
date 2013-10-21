package algoritmo.genetico;

import algoritmo.genetico.dominio.RestricoesLaterais;

public interface AlgoritmoGenetico {

    public Algoritmo calculaAlgoritmoGenetico(Integer numeroGenes, Integer tamanhoDaPopulacao, RestricoesLaterais restricoesLaterais, Integer comprimento);
}
