package avanceRapido;

public class CambioMinimo {
	
	public static int[] cambioMonedas(double cantidad, double[]  monedas){
		int [] sol = new int [monedas.length];
		for (int i=0; i<monedas.length;i++){
			if (cantidad>=monedas[i]){
				sol[i]=(int) (cantidad/monedas[i]);
				cantidad= cantidad- sol[i]*monedas[i];
			}
		}
		return sol;
	}

	public static void main(String[] args) {
		double [] monedas = {2,1,0.5,0.2,0.1,0.05,0.02,0.01};
		double cantidad = 5.89;
		int [] sol =cambioMonedas(cantidad,monedas);
		for (int i : sol){
			System.out.print(i+" ");
		}
	}
}