package practicas;

/**
 * 
 * @author Aspiroz de la Calle, Javier
 * @expediente 21957644
 * @fecha 31-05-2020
 *
 */
public class AlgorimoCambioMinimoAspiroz {

	/**
	 * M�todo que selecciona la moneda a devolver en casa caso, suponiendo que haya existencias.
	 * 
	 * @param valores: array con los valores de las monedas
	 * @param numMonedas: array con el n�mero de monedas que se tiene de cada valor
	 * @param cantidad: importe que debe devolverse
	 * @param acumulado: cantidad que se lleva devuelta por el momento
	 * @return
	 */
	private int seleccionar (int []valores, int[] numMonedas, int cantidad, int acumulado){
		//No utilizado, los casos en los que se intent� hacer uso de �l inclu�an otro bucle, que a�ade complejidad y redundancia
		return 0;
	}//seleccionar
	
	
	/**
	 * M�todo que devuelve un array con el n�mero de monedas de cada valor que se usar�n para devolver 
	 * la cantidad que se quiere cambiar, a partir del n�mero de monedas de cada tipo disponibles. 
	 * Si no hay cambio posible, devuelve FALSE. Devuelve TRUE si existe soluci�n.
	 * 
	 * @param valores: array con los valores de las monedas
	 * @param numMonedas: array con el n�mero de monedas que se tiene de cada valor
	 * @param cantidad: importe que debe devolverse
	 * @param monedasDevueltas: SOLUCI�N: array con el n�mero de monedas de cada tipo que se devuelven. 
	 * 			Si no hay soluci�n posible, debe devolver falso y el array no ser� v�lido. 
	 * @return Verdadero si el problema tiene soluci�n. Falso en caso contrario.
	 */
	public boolean cambioMinimo(int []valores, int[] numMonedas, int cantidad, int[] monedasDevueltas){
		boolean res = false;
		
		int actual = cantidad;
		
		for (int i=0; i<valores.length; i++){
			int aux = actual/valores[i];//monedas maximas a devolver en este paso
			if (aux<=numMonedas[i] && actual>=valores[i]){//la segunda cond puede no ser necesaria
				monedasDevueltas[i]=aux;
				actual -= aux*valores[i];
			}
			else if (actual>=valores[i]){//uso el maximo de monedas posibles
				monedasDevueltas[i]=numMonedas[i];
				actual -= numMonedas[i]*valores[i];
			}
			while (actual>valores[i] && numMonedas[i]>0){
				actual -= valores[i];
				monedasDevueltas[i]++;
				numMonedas[i]--;
			}
		}
		//ajusto los valores devueltos por la funcion		
		if (actual==0){
			res=true;
		}
		else{
			monedasDevueltas[0]=-1;
		}
		
		return res;		
		
	}//cambioMinimo

	
	/**
	 * M�todo que imprime por pantalla el contenido de un array de enteros
	 * @param array
	 */
	public void mostrarArray (int[] array){
		// Para mostrar la soluci�n 
		System.out.print("[");
		for (int i=0; i<array.length; i++)
			System.out.print(array[i] + ", ");
		System.out.println("]");
	}
	
	
	public static void main(String[] args) {
		//TO DO: Instanciar e inicializar convenientemente los arrays siguientes:
		int[] valores = {200, 100, 50, 20, 10, 5, 2, 1}; //Array que alamacena los valores de las diferentes monedas: 1, 2, 5, 10, 25, 100. 
		int[] monedas = {1, 1, 1, 1, 1, 1, 1, 1}; //Array que almacena cuantas monedas de cada valor existen
		int[] cambio = new int [valores.length]; //Array que almacena el resultado: el num. de monedas de cada valor que usaremos 
		
		int cantidad = 289; //Variable para almacenar el cambio que se quiere devolver
		boolean resultado = false; //Variable para saber si hay soluci�n al problema
		
		AlgorimoCambioMinimoAspiroz cm = new AlgorimoCambioMinimoAspiroz();
		resultado = cm.cambioMinimo(valores, monedas, cantidad, cambio);
		if (resultado)
			cm.mostrarArray(cambio);
		else
			System.out.println("No hay soluci�n.\n");		
		
		/* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Javier";
		String misApellidos = "Aspiroz de la Calle";
		String miExpediente = "21957644";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + misApellidos);
		System.out.println(" * Nombre:\t" + miNombre);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");
		System.out.print("** FIN **");
	}//main
}//class
