package practicas;

/**
 * 
 * @author Javier Aspiroz de la Calle
 * @expediente 21957644
 * @fecha 31-05-2020
 *
 */

public class AlgoritmoPrimAspiroz <Clave, Info, Coste> {

	public static <Clave, Info, Coste> int/*Coste*/ algoritmoPrimAR (Grafo<Clave, Info, Coste> grafo, Clave a, Lista<Par<Clave>> T){
		int totalInt=0;
		//hago el proceso para el primer vertice
		Lista<Clave> listIni = grafo.listaSucesores(a);
		int aux = Integer.MAX_VALUE;
		int pos1 = -1;//guardo posicion de vertice con menor coste
		int c1 =0;
		for (int i=0; i<listIni.longitud(); i++){
			c1 = (int) grafo.costeArista(a, listIni.consultar(i+1));
			if (c1<aux){//selecciono arista con menor peso
				aux=c1;
				pos1=i+1;
			}
		}
		totalInt+=aux;//actualizo el coste
		Par<Clave> p1 = new Par<Clave>(a, listIni.consultar(pos1));
		T.insertar(1, p1);//meto 'arista' en la lista
		Lista<Clave> yaConsultados = new Lista<Clave>();
		yaConsultados.insertar(1, a);
		yaConsultados.insertar(1, listIni.consultar(pos1));
		Clave destino = null;
		
		while (T.longitud()<(grafo.numVertices()-1)){			
			//elegimos arista menor peso
			aux = Integer.MAX_VALUE;
			for (int i=0; i<yaConsultados.longitud(); i++){
				Lista<Clave> listTemp = grafo.listaSucesores(yaConsultados.consultar(i+1));//sucesores del vertice
				for (int j=0; j<listTemp.longitud(); j++){
					//si el vertice no esta en la lista yaConsultados calculo el coste
					if (!existeEnLista(yaConsultados, listTemp.consultar(j+1))){
						c1 = (int) grafo.costeArista(yaConsultados.consultar(i+1), listTemp.consultar(j+1));
						if (c1<aux){//selecciono arista con menor peso
							aux=c1;
							pos1=i+1;
							destino = listTemp.consultar(j+1);
							p1 = new Par<Clave>(yaConsultados.consultar(i+1), destino);
						}
					}
				}
			}
			totalInt+=aux;//actualizo el coste
			T.insertar(T.longitud()+1, p1);//meto 'arista' en la lista
			yaConsultados.insertar(yaConsultados.longitud()+1, destino);
		}
		
		//Coste total= (Integer) totalInt;
		return totalInt;
		//return total;
	}
	
	private static <Clave> boolean existeEnLista (Lista<Clave> listIni, Clave clave){
		boolean res = false;

		for (int i =0; i<listIni.longitud(); i++){
			if (clave==listIni.consultar(i+1)){
				res = true;
			}
		}
		return res;
	}

	
	public static void main(String[] args) {

		// declara e inicializa un grafo con los datos de los apuntes
		Grafo<Character,Character,Integer> gr = new Grafo<Character,Character,Integer>();
		
		//inserto las aristas
		gr.insertarVertice('A', 'A');
		gr.insertarVertice('B', 'B');
		gr.insertarVertice('C', 'C');
		gr.insertarVertice('D', 'D');
		gr.insertarVertice('E', 'E');
		gr.insertarVertice('F', 'F');
		gr.insertarVertice('G', 'G');
		
		//inserto aristas, como es no dirigido inserto ambos sentidos
		gr.insertarArista('A', 'B', 10);
		gr.insertarArista('A', 'C', 4);
		gr.insertarArista('A', 'D', 7);
		gr.insertarArista('B', 'A', 10);
		gr.insertarArista('B', 'D', 2);
		gr.insertarArista('B', 'E', 10);
		gr.insertarArista('C', 'A', 4);
		gr.insertarArista('C', 'D', 2);
		gr.insertarArista('C', 'F', 3);
		gr.insertarArista('D', 'A', 7);
		gr.insertarArista('D', 'B', 2);
		gr.insertarArista('D', 'C', 2);
		gr.insertarArista('D', 'G', 5);
		gr.insertarArista('E', 'B', 10);
		gr.insertarArista('E', 'G', 2);
		gr.insertarArista('F', 'C', 3);
		gr.insertarArista('F', 'G', 5);
		gr.insertarArista('G', 'D', 5);
		gr.insertarArista('G', 'E', 2);
		gr.insertarArista('G', 'F', 5);
		
		// invoca a la función algoritmoPrimAR, para el grafo anterior, partiendo del vértice con clave "A"
		Lista<Par<Character>> list = new Lista<Par<Character>>();
		Integer total = algoritmoPrimAR(gr, 'A', list);
		// muestra por pantalla el coste del árbol de recubrimiento mínimo generado
		System.out.println("El coste del árbol de recubrimiento es "+total);
		// muestra por pantalla la lista de pares que forman el árbol
		System.out.println("Vertices incluidos en el árbol de recubrimiento mínimo: ");
		for (int i=1; i<=list.longitud(); i++){
			System.out.print(list.consultar(i).getOrigen()+"-"+list.consultar(i).getDestino()+" -> ");
		}
		
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
