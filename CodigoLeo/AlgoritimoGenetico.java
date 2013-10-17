import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Teste com Cromossomos
 * 
 * @author Leonardo Seiji
 * @date 2013-10-10
 */
public class AlgoritimoGenetico {
	
	//VARIAVEIS
	/**TOTAL_CROMOSSOMOS = 100*/
	private static int TOTAL_CROMOSSOMOS = 100;
	/**TOTAL_MELHORES = 5*/
	private static int TOTAL_MELHORES = 5;
	/**TOTAL_PIORES = 5*/
	private static int TOTAL_PIORES = 5;
	/**TOTAL_CROSSOVER = 90*/
	private static int TOTAL_CROSSOVER = 90;
	/**TOTAL_CROSSOVER_EXTRA = 1*/
	private static int TOTAL_CROSSOVER_EXTRA = 1;
	
	private Random rand = new Random();
	private ArrayList<Integer[]> listaCromossomo;
	private ArrayList<Double> listaCromossomoDouble;
	private ArrayList<Double> listaCromossomoErro;
	private List<Cromossomo> lista;
	private ArrayList<Cromossomo> novaListaCromossomo;
	
	public Integer[] criaVetorRandomico(){
		Integer cromossomo[] = new Integer[9];
		
		for (int i = 0; i < cromossomo.length; i++) {
			cromossomo[i] = rand.nextInt(10-1) + 1;
		}

		return cromossomo;
	}
	
	public ArrayList<Integer[]> criaPopulacaoInicial(){
		ArrayList<Integer[]> listaCromossomo = new ArrayList<Integer[]>();
		
		for (int i = 0; i < TOTAL_CROMOSSOMOS; i++) {
			listaCromossomo.add(criaVetorRandomico());
		}
		
		return listaCromossomo;
	}
	
	public ArrayList<Double> criaListaCromossomoDouble(ArrayList<Integer[]> listaCromossomo){
		ArrayList<Double> listaCromossomoDouble = new ArrayList<Double>();
		
		for (Integer[] cromossomo : listaCromossomo) {
			listaCromossomoDouble.add(extraiCromossomoDouble(cromossomo));
		}
		
		return listaCromossomoDouble;
	}
	
	public void printCromossomo(Integer[] cromossomo){
		System.out.print("Cromossomo: ");
		for (Integer integer : cromossomo) {
			System.out.print(integer);
		}
		System.out.println();
	}
	
	public Double extraiCromossomoDouble(Integer[] cromossomo){
		double d = new Double(cromossomo[0])+
				(new Double(cromossomo[0])/new Double(10))+
				(new Double(cromossomo[1])/new Double(100))+
				(new Double(cromossomo[2])/new Double(1000))+
				(new Double(cromossomo[3])/new Double(10000))+
				(new Double(cromossomo[4])/new Double(100000))+
				(new Double(cromossomo[5])/new Double(1000000))+
				(new Double(cromossomo[6])/new Double(10000000))+
				(new Double(cromossomo[7])/new Double(100000000))+
				(new Double(cromossomo[8])/new Double(1000000000));
		
		return d;
	}
	
	
	public ArrayList<Double> criaListaCromossomoErro(ArrayList<Double> listaCromossoDouble){
		ArrayList<Double> listaCromossomoErro = new ArrayList<Double>();
		
		for (Double cromossomo : listaCromossoDouble) {
			listaCromossomoErro.add(Math.abs((cromossomo*cromossomo)-5));
		}
		
		return listaCromossomoErro;
	}
	
	public ArrayList<Cromossomo> consolidaInformcao(ArrayList<Integer[]> cromossomo, ArrayList<Double> dcromossomo, ArrayList<Double> erro){
		ArrayList<Cromossomo> listaCromossomo = new ArrayList<Cromossomo>();
		for (int i = 0 ; i < TOTAL_CROMOSSOMOS ; i++) {
			listaCromossomo.add(new Cromossomo(cromossomo.get(i), dcromossomo.get(i), erro.get(i)));
		}
		
		return listaCromossomo;
	}

	@SuppressWarnings("unchecked")
	public void ordenaCromossomos(List<Cromossomo> lista){
		Collections.sort(lista, new Comparator() {
			public int compare(Object o1, Object o2) {
				Cromossomo cro1 = (Cromossomo) o1;
				Cromossomo cro2 = (Cromossomo) o2;
				
				return cro1.getErro() < cro2.getErro() ? -1 : (cro1.getErro() > cro2.getErro() ? +1 : 0);
			}
		});
	}
	
	public ArrayList<Cromossomo> separaMelhores(List<Cromossomo> lista){
		ArrayList<Cromossomo> listaCromossomo = new ArrayList<Cromossomo>();
		for (int i = 0; i < TOTAL_MELHORES; i++) {
			listaCromossomo.add(lista.get(i));
		}
		return listaCromossomo;
	}
	
	public void criaCrossover(List<Cromossomo> lista, ArrayList<Cromossomo> novaLista){
		for (int i = TOTAL_MELHORES; i < TOTAL_CROSSOVER+TOTAL_MELHORES; i++) {
			Cromossomo c = lista.get(i);
			c.setCromossomo(geraCrossover(c.getCromossomo()));
			c.setDcromossomo(extraiCromossomoDouble(c.getCromossomo()));
			c.setErro(Math.abs((c.getDcromossomo()*c.getDcromossomo())-5));
			
			novaLista.add(c);
		}
		
		//MUTA OS EXTRA
		for (int i = 0; i < TOTAL_CROSSOVER_EXTRA; i++) {
			int index = rand.nextInt(TOTAL_CROSSOVER)+TOTAL_MELHORES;
			int posicao = rand.nextInt(9);

			Cromossomo cron = novaLista.get(index);
			Integer[] a = cron.getCromossomo();
			a[posicao] = rand.nextInt(10-1)+1;
			
			novaLista.set(index, cron);			
		}
	}
	
	public Integer[] geraCrossover(Integer[] cromossomo){
		Integer[] a = new Integer[9];
		a[0] = cromossomo[5];
		a[1] = cromossomo[6];
		a[2] = cromossomo[7];
		a[3] = cromossomo[8];
		a[4] = cromossomo[4];
		a[5] = cromossomo[0];
		a[6] = cromossomo[1];
		a[7] = cromossomo[2];
		a[8] = cromossomo[3];
		
		return a;
	}
	
	public void criaCromossomosFinais(List<Cromossomo> lista, ArrayList<Cromossomo> novaLista){
		for (int i = TOTAL_CROSSOVER+TOTAL_MELHORES; i < TOTAL_CROMOSSOMOS; i++) {
			int sortudo1 = rand.nextInt(TOTAL_CROSSOVER)+TOTAL_MELHORES;
			Cromossomo c1 = lista.get(sortudo1);
			Integer[] numero1 = c1.getCromossomo();
			
			int sortudo2 = rand.nextInt(TOTAL_CROSSOVER)+TOTAL_MELHORES;
			Cromossomo c2 = lista.get(sortudo2);
			Integer[] numero2 = c2.getCromossomo();
			
			Integer[] novo = {numero1[0],numero1[1],numero1[2],numero1[3],numero1[4],numero2[5],numero2[6],numero2[7],numero2[8]};
					
			Cromossomo nCrom = new Cromossomo(novo, extraiCromossomoDouble(novo), Math.abs((extraiCromossomoDouble(novo)*extraiCromossomoDouble(novo))-5));
			novaLista.add(nCrom);
		}
	}
	
	public AlgoritimoGenetico(){
		listaCromossomo = criaPopulacaoInicial();
		
//		for (Integer[] crom : listaCromossomo) {
//			printCromossomo(crom);
//		}
		
		listaCromossomoDouble = criaListaCromossomoDouble(listaCromossomo);
		
//		for (Double crom : listaCromossomoDouble) {
//			System.out.println("Cromossono: "+crom);
//		}
		
		listaCromossomoErro = criaListaCromossomoErro(listaCromossomoDouble);
		
		lista = consolidaInformcao(listaCromossomo, listaCromossomoDouble, listaCromossomoErro);
        
		ordenaCromossomos(lista);
		
//		for (Cromossomo crom : lista) {
//			printCromossomo(crom.getCromossomo());
//			System.out.print(" - double:"+crom.getDcromossomo()+" - erro:"+crom.getErro());
//			System.out.println();
//		}

		novaListaCromossomo = separaMelhores(lista);

		criaCrossover(lista, novaListaCromossomo);
		
		criaCromossomosFinais(lista, novaListaCromossomo);
		
		for (Cromossomo crom : novaListaCromossomo) {
			printCromossomo(crom.getCromossomo());
			System.out.print(" - double:"+crom.getDcromossomo()+" - erro:"+crom.getErro());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		new AlgoritimoGenetico();
	}
}

class Cromossomo{
	private Integer[] cromossomo;
	private Double dcromossomo;
	private Double erro;
	
	public Cromossomo(Integer[] cromossomo, Double dcromossomo, Double erro) {
		this.cromossomo = cromossomo;
		this.dcromossomo = dcromossomo;
		this.erro = erro;
	}
	
	public Integer[] getCromossomo() {
		return cromossomo;
	}
	public void setCromossomo(Integer[] cromossomo) {
		this.cromossomo = cromossomo;
	}
	public Double getDcromossomo() {
		return dcromossomo;
	}
	public void setDcromossomo(Double dcromossomo) {
		this.dcromossomo = dcromossomo;
	}
	public Double getErro() {
		return erro;
	}
	public void setErro(Double erro) {
		this.erro = erro;
	}
}
