package grafos;

import java.util.ArrayDeque;

public class Grafo<Clave, InfoVertice, Coste> {

	protected class NodoVertice {
		Clave clave;
		InfoVertice vertice;
		int gradoEntrada;
		int gradoSalida;

		public NodoVertice(Clave c, InfoVertice v) {
			clave = c;
			vertice = v;
			gradoEntrada = 0;
			gradoSalida = 0;
		}
	}

	protected class NodoArista {
		NodoVertice destino;
		Coste coste;

		public NodoArista(NodoVertice d) {
			destino = d;
			coste = null;
		}

		public NodoArista(NodoVertice d, Coste c) {
			destino = d;
			coste = c;
		}
	}

	Lista<NodoVertice> vertices;

	Lista<Lista<NodoArista>> aristas;

	public Grafo() {
		vertices = new Lista<NodoVertice>();
		aristas = new Lista<Lista<NodoArista>>();
	}

	public boolean esVacio() {
		return vertices.longitud() == 0;
	}

	public void insertarVertice(Clave c, InfoVertice v) {
		vertices.insertar(1, new NodoVertice(c, v));
		aristas.insertar(1, new Lista<NodoArista>());
	}

	public void modificarVertice(Clave c, InfoVertice v) {
		int i = 1;
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(c))
			i++;
		if (i <= vertices.longitud())
			vertices.consultar(i).vertice = v;
	}

	public void eliminarVertice(Clave c) {
		int i = 1;

		// Busca el vertice que se le indique
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(c))
			i++;

		// Si el vertice a eliminar existe, se elimina, junto con todas las
		// aristas relacionadas.
		if (i <= vertices.longitud()) {
			vertices.borrar(i);
			// Borra las aristas de salida
			aristas.borrar(i);

			// Borra las aristas de entrada
			for (int j = 1; j <= aristas.longitud(); j++) {
				int k = 1;
				boolean aristaEliminada = false;
				while (!aristaEliminada && k <= aristas.consultar(j).longitud()) {
					if (aristas.consultar(j).consultar(k).destino.clave.equals(c)) {
						aristas.consultar(j).borrar(k);
						aristaEliminada = true;
					} else
						k++;
				}
			}
		}
	}

	public boolean existeVertice(Clave c) {
		int i = 1;
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(c))
			i++;
		return i <= vertices.longitud();
	}

	public void insertarArista(Clave o, Clave d, Coste c) {
		int i = 1;
		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		int j = 1;
		// Busca el vertice destino
		while (j <= vertices.longitud()
				&& !vertices.consultar(j).clave.equals(d))
			j++;

		// Si existe, introduce la arista
		if (i <= vertices.longitud() && j <= vertices.longitud()) {
			aristas.consultar(i).insertar(1,
					new NodoArista(vertices.consultar(j), c));
			// Actualiza el grado de salida del vertice origen
			vertices.consultar(i).gradoSalida++;

			// Actualiza el grado de entrada del vertice destino
			vertices.consultar(j).gradoEntrada++;
		}

	}

	public void modificarArista(Clave o, Clave d, Coste c) {
		int i = 1;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		// Si existe, busca el vertice destino y modifica el peso de la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaModificada = false;
			while (!aristaModificada && j <= aristas.consultar(i).longitud()) {
				if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
					aristas.consultar(i).consultar(j).coste = c;
					aristaModificada = true;
				}
				j++;
			}
		}
	}

	public void eliminarArista(Clave o, Clave d) {
		int i = 1;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		// Si existe, busca el vertice destino y elimina la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaEliminada = false;
			while (!aristaEliminada && j <= aristas.consultar(i).longitud()) {
				if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
					// Actualiza el grado de salida del vertice origen
					vertices.consultar(i).gradoSalida--;

					// Actualiza el grado de entrada del vertice destino
					aristas.consultar(i).consultar(j).destino.gradoSalida--;

					// Borra el vertice 
					aristas.consultar(i).borrar(j);
				} else
					j++;
			}
		}
	}

	public Coste costeArista(Clave o, Clave d) {
		int i = 1;
		Coste coste = null;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		// Si existe, busca el vertice destino y devuelve el peso de la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaEncontrada = false;
			while (!aristaEncontrada && j <= aristas.consultar(i).longitud()) {
				if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
					coste = aristas.consultar(i).consultar(j).coste;
					aristaEncontrada = true;
				}
				j++;
			}
		}

		return coste;
	}

	public int gradoEntrada(Clave v) {
		int i = 1;

		// Busca el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

		// Si lo encuentra, devuelve su grado de entrada. Si no, devuelve cero
		if (i <= vertices.longitud())
			return vertices.consultar(i).gradoEntrada;
		else
			return 0;
	}

	public int gradoSalida(Clave v) {
		int i = 1;

		// Busca el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

		// Si lo encuentra, devuelve su grado de salida. Si no, devuelve cero
		if (i <= vertices.longitud())
			return vertices.consultar(i).gradoSalida;
		else
			return 0;
	}

	public Lista<Clave> listaSucesores(Clave v) {
		int i = 1;
		Lista<Clave> sucesores = new Lista<Clave>();

		// Busca el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

		// Si lo encuentra, introduce sus sucesores a la lista
		if (i <= vertices.longitud())
			for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
				sucesores.insertar(j,
						aristas.consultar(i).consultar(j).destino.clave);

		return sucesores;
	}

	public Lista<Clave> listaPredecesores(Clave v) {
		Lista<Clave> predecesores = new Lista<Clave>();

		// Busca la aparicion del vertice como posible destino de otros vertices
		for (int i = 1; i <= vertices.longitud(); i++) {
			int j = 1;
			boolean verticeEncontrado = false;
			// Si encuentra el vertice v como destino de un vertice o, no
			// aparece mas veces como destino de o
			while (!verticeEncontrado && j <= aristas.consultar(i).longitud()) {
				if (v.equals(aristas.consultar(i).consultar(j).destino.clave)) {
					predecesores.insertar(1, vertices.consultar(i).clave);
					verticeEncontrado = true;
				} else
					j++;
			}
		}

		return predecesores;
	}

	public int numVertices() {
		return vertices.longitud();
	}

	public Lista<Clave> listaVertices() {
		Lista<Clave> listaVertices = new Lista<Clave>();

		for (int i = 1; i <= vertices.longitud(); i++)
			listaVertices.insertar(1, vertices.consultar(i).clave);

		return listaVertices;
	}

	public String toString() {
		String texto = "";
		for (int i = 1; i <= vertices.longitud(); i++) {
			texto += vertices.consultar(i).clave + " --> ";
			for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
				texto += aristas.consultar(i).consultar(j).destino.clave + "("
						+ aristas.consultar(i).consultar(j).coste + ") ";
			texto += "\n";
		}

		return texto;
	}
	
	//ejercicios
	
	//Contar vertices aislados: recorremos la lista de vertices y comprobamos sus grados
	public <Clave, Info, Coste> int contarAislados (Grafo<Clave, Info, Coste> grafo){
		int contador=0;
		for (int i=1; i<=vertices.longitud(); i++){
			Clave v = (Clave) vertices.consultar(i);
			if (grafo.gradoEntrada(v)==0 && grafo.gradoSalida(v)==0)
				contador++;
		}
		return contador;
	}
	
	
	//Contar bucles: 
	public static <Clave, Info, Coste> int contarBucles (Grafo<Clave, Info, Coste> grafo){
		int bucles=0;
		
		Lista<Clave> vertices = grafo.listaVertices();
		for (int i=1; i<=vertices.longitud(); i++){
			Clave v = vertices.consultar(i);
			Lista <Clave> sucesores = grafo.listaSucesores(v);
			if (sucesores.buscar(v)!=0){
				bucles++;
			}
		}
		
		return bucles;
	}
	
	//Recorrido en profundidad recursivo
	public static <Clave, Info, Coste> void recProfundidadRecursivo (Grafo<Clave, Info, Coste> g, Lista<Clave> noVisitados, Clave vertice){
		Lista<Clave> sucesores;
		
		System.out.println("Visitando vertice "+vertice);
		//invocamos funcion visitar vertice con la funcionalidad necesaria a impemantar
		//visitar(vertice);
		
		//elimino de no visitados el vertice que acabo de visitar
		int posicion = noVisitados.buscar(vertice);
		noVisitados.borrar(posicion);
		
		//realizo el recorrido en profundidad de sus sucesores
		sucesores = g.listaSucesores(vertice);
		Clave destino;
		for (int i=1; i<sucesores.longitud(); i++){
			destino = sucesores.consultar(i);
			if (noVisitados.buscar(destino)!=0)
				recProfundidadRecursivo(g, noVisitados, destino);
		}
	}
	
	//Recorrido en profundidad
	public static <Clave, Info, Coste> void recorridoProf (Grafo<Clave, Info, Coste> g){
		Lista <Clave> noVisitados = g.listaVertices();
		Clave vertice;
		while(!noVisitados.esVacia()){
			vertice = noVisitados.consultar(1);
			recProfundidadRecursivo(g, noVisitados, vertice);
		}
	}
	
	//recorrido en anchura
	public static <Clave, Info, Coste> void recAnchura(Grafo<Clave, Info, Coste> gr) {
	    
		if (!gr.esVacio()){
			Lista<Clave> colaLista = new Lista<Clave>();
			//ArrayDeque<Clave> cola = new ArrayDeque<Clave>();
			
			Lista<Clave> noVisitados = gr.listaVertices();
			Lista<Clave> sucesores = new Lista<Clave>();
			Clave v;
	    
			while (!noVisitados.esVacia()) {
				v = noVisitados.consultar(1);
				//cola.add(v);
				colaLista.insertar(colaLista.longitud()-1, v);
				//while(!cola.isEmpty()){
				while (!colaLista.esVacia()){
					//System.out.println(" --> Cola: " + cola);
					//v = cola.pop();
					v = colaLista.consultar(1);
					colaLista.borrar(1);
					if (noVisitados.buscar(v) != 0){
						System.out.println(v + " - ");//VSISTAR
						noVisitados.borrar(noVisitados.buscar(v));
						sucesores = gr.listaSucesores(v);
						//System.out.println(sucesores.toString());
						for (int i = 1; i<=sucesores.longitud(); i++){
							//cola.add(sucesores.consultar(i));
							colaLista.insertar(colaLista.longitud()+1, sucesores.consultar(i));
						}
					}
				}
			}
		}
	  }
	
	
	
	public static void main(String args[]) {
		Grafo<String, String, Integer> grafoCiudades = new Grafo<String, String, Integer>();
		grafoCiudades.insertarVertice("BAR", "Barcelona");
		grafoCiudades.insertarVertice("MAD", "Madrid");
		grafoCiudades.insertarVertice("COR", "La Coruña");
		grafoCiudades.insertarVertice("SEV", "Sevilla");
		grafoCiudades.insertarVertice("VAL", "Valencia");
		grafoCiudades.insertarVertice("BIL", "Bilbao");
		grafoCiudades.insertarVertice("CUE", "Cuenca");
		grafoCiudades.insertarVertice("JAE", "Jaen");

		grafoCiudades.insertarArista("COR", "BIL", 644);
		grafoCiudades.insertarArista("COR", "MAD", 609);

		grafoCiudades.insertarArista("BIL", "BAR", 620);
		grafoCiudades.insertarArista("BIL", "MAD", 395);

		grafoCiudades.insertarArista("BAR", "BIL", 620);
		grafoCiudades.insertarArista("BAR", "VAL", 649);

		grafoCiudades.insertarArista("MAD", "COR", 609);
		grafoCiudades.insertarArista("MAD", "VAL", 352);
		grafoCiudades.insertarArista("MAD", "SEV", 538);

		grafoCiudades.insertarArista("VAL", "MAD", 352);
		grafoCiudades.insertarArista("VAL", "SEV", 697);

		grafoCiudades.insertarArista("SEV", "MAD", 538);
		
		grafoCiudades.insertarArista("CUE", "JAE", 356);

		System.out.println(grafoCiudades);
		
		System.out.println("NumVertices= " + grafoCiudades.numVertices());
		System.out.println("ListaSucesores(BAR)\n"
				+ grafoCiudades.listaSucesores("BAR"));
		System.out.println("ListaPredecesores(MAD)\n"
				+ grafoCiudades.listaPredecesores("MAD"));

		System.out.println("\n*** FIN ***");
		
	}//main

}//class
