package trees;

//Código desarrollado completamente por la profesora,hasta los ejercicios

public class ArbolBin<T> {

	class NodoBin {
		T info;
		NodoBin hijoIzq;
		NodoBin hijoDcho;

		NodoBin(NodoBin hIzq, T info, NodoBin hDcho) {
			this.hijoIzq = hIzq;
			this.hijoDcho = hDcho;
			this.info = info;
		}
	}

	private NodoBin raiz;

	public ArbolBin() {
		raiz = null;
	}

	public ArbolBin(ArbolBin<T> subIzq, T infoRaiz, ArbolBin<T> subDcho) {
		NodoBin izq = subIzq == null? null : subIzq.raiz; 
		NodoBin dcho = subDcho == null? null : subDcho.raiz; 
		this.raiz = new NodoBin(izq, infoRaiz, dcho);
	}

	public boolean esVacio() {
		return raiz == null;
	}
	
	public T raiz() {
		return this.raiz.info;
	}

	public ArbolBin<T> hijoIzquierdo() {
		ArbolBin<T> subIzq = new ArbolBin<T>();
		subIzq.raiz = this.raiz.hijoIzq;

		return subIzq;
	}

	public ArbolBin<T> hijoDerecho() {
		ArbolBin<T> subDcho = new ArbolBin<T>();
		subDcho.raiz = this.raiz.hijoDcho;
		return subDcho;
	}
	
	public void dibujar(int nivel){		
		if ( !this.esVacio() ){
			for (int i = 1; i<= nivel; i++)
				System.out.print("  ");
			System.out.println(this.raiz());
			this.hijoIzquierdo().dibujar(nivel+1);
			this.hijoDerecho().dibujar(nivel+1);			
		}
	}
	
	//Empezamos con la resolución de ejercicios
	//ejers 3 7 8 9* 20 21 22
	
	public static <T> void eliminarHojas(ArbolBin<T> a){//ejercicio 1
		if (!a.esVacio()){
			if (a.hijoIzquierdo().esVacio() && a.hijoDerecho().esVacio()){
				a=null;
			}
			else{
				eliminarHojas(a.hijoIzquierdo());
				eliminarHojas(a.hijoDerecho());
			}
		}
	}
	
	public int contarNodos(ArbolBin<T> a){//ejercicio 2
		if(! a.esVacio()){
			return 1 + contarNodos(a.hijoIzquierdo()) + contarNodos(a.hijoDerecho());
		}
		else{
			return 0;
		}
	}
	
	//ejercico 4, tip: 1+max(profundidad(hDer), profundidad(hIzqu))
	public int profundidad(ArbolBin<T> a){//ejercicio 4
		int prof=0;
		if (!a.esVacio()){
			int aux1 = 1+ profundidad(a.hijoIzquierdo());
			int aux2 = 1+ profundidad(a.hijoDerecho());
			if (aux1>aux2){
				prof = aux1;
			}
			else{
				prof = aux2;
			}
		}
		return prof;
	}
	
	public static <T> int contarHojas(ArbolBin<T> a){//ejercicio 5
		if (a.esVacio()){
			return 0;
		}
		else{
			if (a.hijoIzquierdo().esVacio() && a.hijoDerecho().esVacio()){
				return 1;
			}
			else{
				return contarHojas(a.hijoIzquierdo()) + contarHojas(a.hijoDerecho());
			}
		}
	}
	
	public int sumarNodosArbol(ArbolBin<Integer> a, int total){//ejercico 6, mejorar el codigo
		if (!a.esVacio()){
			total += a.raiz();
			total = sumarNodosArbol(a.hijoIzquierdo(), total);
			total = sumarNodosArbol(a.hijoDerecho(), total);
		}
		return total;
	}
	
	public static <T> void printRoots(ArbolBin<T> a){//ejercicio 8, no funciona correctamente
		if (a != null && a.hijoDerecho()!= null && a.hijoIzquierdo()!=null){
			System.out.print(a.raiz+" ");
			
			printRoots(a.hijoIzquierdo());
			printRoots(a.hijoDerecho());
		}
	}
	
	public boolean esCompleto(ArbolBin<T> a) {//ejercicio 11
		 boolean respuesta=false;
		 //Completar: true si contar nodos=(2^prof)-1
		 if(contarNodos(a)==(Math.pow(2, profundidad(a))-1)){
			 respuesta=true;
		 }
		 return respuesta;
	 }
	
	public boolean buscar(int valor){
		boolean res = false;
		
		//pseudocodigo
		/*public boolean buscar (Comparable elem) { 
			if (esVacio()) 
				return false; 
			else if (elem.compareTo(raiz()) == 0) 
				return true 
			else if (elem.compareTo(raiz()) < 0) 
				return buscarRec(hijoIzquierdo()); 
			else 
				return buscarRec(hijoDerecho()); 
		}*/
		
		return res;
	}
	
	//buscar2 de la pagina 60, firma: public ABB <T> buscar (T elem);
	public ArbolBin<T> buscarTree(ArbolBin<T> a, T elem){
		ArbolBin<T> aux=null;
		if (!a.esVacio()){
			if(a.raiz()==elem){
				//devuelvo arbol con raiz como elem
				aux = new ArbolBin<T>(new ArbolBin<T>(), elem, new ArbolBin<T>());
			}
			else{
				buscarTree(a.hijoIzquierdo(),elem);
				buscarTree(a.hijoDerecho(), elem);
				//como elem ya es null no hace falta que lo vuelva a asignar
			}
		}
		return aux;
	}

	public static void main(String[] args) {
		//ArbolBin<Integer> g = new ArbolBin<Integer>(new ArbolBin<Character>(),'G',new ArbolBin<Character>());
		ArbolBin<Integer> d = new ArbolBin<Integer>(new ArbolBin<Integer>(),4,new ArbolBin<Integer>());
		ArbolBin<Integer> f = new ArbolBin<Integer>(new ArbolBin<Integer>(),6,new ArbolBin<Integer>());
		//ArbolBin<Character> e = new ArbolBin<Character>(new ArbolBin<Character>(),'E',f);
		ArbolBin<Integer> e = new ArbolBin<Integer>(new ArbolBin<Integer>(),5,f);
		ArbolBin<Integer> b = new ArbolBin<Integer>(d,2,new ArbolBin<Integer>());
		ArbolBin<Integer> c = new ArbolBin<Integer>(e,3,new ArbolBin<Integer>());
		
		ArbolBin<Integer> a = new ArbolBin<Integer>(b,1,c);
		
		a.dibujar(1);
		
		System.out.println("Números de nodos del árbol "+ a.contarNodos(a));
		System.out.println("Suma de los nodos del árbol "+ a.sumarNodosArbol(a, 0));
		System.out.println("Profundidad el árbol "+ a.profundidad(a));
		System.out.println("Número de nodos terminales "+ a.contarHojas(a));
		System.out.println("El árbol es completo: "+a.esCompleto(a));
		
		//a.printRoots(a);//invoco a partir de la raiz
	}

}