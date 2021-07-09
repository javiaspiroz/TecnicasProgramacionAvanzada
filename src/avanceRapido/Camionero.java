package avanceRapido;

public class Camionero {

	//para mi ejemplo concreto
	static int distanciaTotal = 1300;
	static int autonomia = 300;
	static int distancias [] = {100,75,200,50,125,165,40,160,35,155,75};
	static int paradas [] = new int [11];
	
	
	public static boolean deboParar (int indice, int recorridoDesdeUltimaParada){
		return (recorridoDesdeUltimaParada+distancias[indice]>autonomia);
	}
	
	public static void main(String[] args) {
		
		//datos que necesito controlar
		int recorridoSinRepostar=0;
		int totalRecorrido=0;
		int indiceGasolinera=0;
		
		while (totalRecorrido<distanciaTotal && recorridoSinRepostar<=autonomia && indiceGasolinera<distancias.length){
			if (!deboParar(indiceGasolinera, recorridoSinRepostar)){
				recorridoSinRepostar+=distancias[indiceGasolinera];
			} else{
				paradas[indiceGasolinera-1]=1;
				recorridoSinRepostar=distancias[indiceGasolinera];
			}
			totalRecorrido+=distancias[indiceGasolinera];
			indiceGasolinera++;
		}
		
		//invocacion siusamos el metodo
		//boolean haySolucion = calcularParadasMinimas(distanciaTotal, autonomia, distancias, paradas);
		
		if (recorridoSinRepostar>autonomia){
			System.out.println("No es posible");
		}
		else{
			System.out.println("Si es posible. Total recorrido: "+ totalRecorrido);
			for (int i=0; i<paradas.length; i++){
				System.out.println(distancias[i]+"-"+paradas[i]);
			}
		}
	}

	//si queremos generalizarlo para cualquier caso creamos este metodo
	public static boolean calcularParadasMinimas(int distTotal, int autonomia, int distancias[], int paradas[]){
		int recorridoSinRepostar=0;
		int totalRecorrido=0;
		int indiceGasolinera=0;
		
		while (totalRecorrido<distanciaTotal && recorridoSinRepostar<=autonomia && indiceGasolinera<distancias.length){
			if (!deboParar(indiceGasolinera, recorridoSinRepostar)){
				recorridoSinRepostar+=distancias[indiceGasolinera];
			} else{
				paradas[indiceGasolinera-1]=1;
				recorridoSinRepostar=distancias[indiceGasolinera];
			}
			totalRecorrido+=distancias[indiceGasolinera];
			indiceGasolinera++;
		}
		
		return (recorridoSinRepostar>=autonomia);		
	}
}