package tablasMultiplicar;

public class TablasMultiplicar {
	
	public static void imprimirTablas (int n){
		for (int i=1; i<=n; i++){
			System.out.println("Tabla del "+i);
			for (int j=0; j<11; j++){
				//ahora imprimo los resultados
				System.out.println(i+"x"+j+" = "+i*j);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		imprimirTablas(2);
	}
}