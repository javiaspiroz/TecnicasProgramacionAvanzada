package practicas;

/**
 * 
 * @author Javier Aspiroz de la Calle
 * @expediente 21957644
 * @fecha 31-05-2020
 *
 */

public class AlgoritmoEspejoAspiroz <Info> {

	public static <Info> ArbolBin<Info> algoritmoEspejoPractica (ArbolBin<Info> arbol){
		ArbolBin<Info> espejo = null;//declaro un nuevo arbol que sera el espejo
		//la idea es recorrer el arbol original recursivamente y asignar izq y der al reves en espejo
		
		if(!arbol.esVacio())
			espejo= new ArbolBin<Info>(algoritmoEspejoPractica(arbol.hijoDerecho()), arbol.raiz(), algoritmoEspejoPractica(arbol.hijoIzquierdo()));
		
		return espejo;
	}

	
	public static void main(String[] args) {

		// declaro e inicializo un árbol de enteros
		ArbolBin<Integer> d = new ArbolBin<Integer>(new ArbolBin<Integer>(),4,new ArbolBin<Integer>());
		ArbolBin<Integer> f = new ArbolBin<Integer>(new ArbolBin<Integer>(),6,new ArbolBin<Integer>());
		ArbolBin<Integer> e = new ArbolBin<Integer>(new ArbolBin<Integer>(),5,f);
		ArbolBin<Integer> b = new ArbolBin<Integer>(d,2,new ArbolBin<Integer>());
		ArbolBin<Integer> c = new ArbolBin<Integer>(e,3,new ArbolBin<Integer>());
		ArbolBin<Integer> a = new ArbolBin<Integer>(b,1,c);
		// lo dibujo
		a.dibujar(1);
		// declaro un árbol espejo, que modificaré con la función para 
		ArbolBin<Integer> espejo;
		// invoco a la función para calcular el espejo
		espejo=algoritmoEspejoPractica(a);
		// dibujo el espejo
		espejo.dibujar(1);
	
		// declaro e inicializo un árbol de caracteres
		ArbolBin<Character> a1 = new ArbolBin<Character>(new ArbolBin<Character>(), 'A', new ArbolBin<Character>());
		ArbolBin<Character> a2 = new ArbolBin<Character>(null, 'B', null);
		ArbolBin<Character> a3 = new ArbolBin<Character>(a1,'C',new ArbolBin<Character>());
		ArbolBin<Character> a4 = new ArbolBin<Character>(a3,'D', a2);
		
		// lo dibujo
		a4.dibujar(1);
		// declaro un árbol espejo, que modificaré con la función para 
		ArbolBin<Character> espejoChar;
		// invoco a la función para calcular el espejo
		espejoChar=algoritmoEspejoPractica(a4);
		// dibujo el espejo
		espejoChar.dibujar(1);
		

		/* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Javier";
		String misApellidos = "Aspiroz de la Calle";
		String miExpediente = "21957644";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + misApellidos);
		System.out.println(" * Nombre:\t" + miNombre);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");
	
	

	}

}
