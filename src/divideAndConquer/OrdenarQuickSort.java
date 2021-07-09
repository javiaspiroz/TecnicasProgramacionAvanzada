package divideAndConquer;

//C�digo realizado por la profesora completamente

public class OrdenarQuickSort {

	/* M�todo QuickSort. 
	 * Dado un array de enteros numbers y dos posiciones, inicial (low) y final (high),  
	 * ordena no decrecientemente los elementos del array entre las posiciones inicial y final.
	 * Primera invocaci�n: quickSort(A,0,A.length-1)
	 */
	public static void quickSort(int []numbers, int low, int high) { 
		if (low<high) {
			int posicionPivote = pivot (numbers, low, high);   
			quickSort(numbers, low, posicionPivote-1);
			quickSort(numbers, posicionPivote+1, high);
		}
	}

	/* M�todo pivot. 
	 * Dado un array de enteros numbers y dos posiciones, inicial (low) y final (high), 
	 * considera pivote al elemento que est� en la posici�n inicial al invocar
	 * devuelve la posici�n final en la que quedar�a el pivote, modificando el array
	 * dejando a la izquierda los elementos m�s peque�os o iguales que el pivote,
	 * dejando a la derecha los elementos m�s grandes que el pivote. 
	 */
	public static int pivot(int []numbers, int low, int high){

		int i = low;
		int pivot = numbers[i];
		for (int j=low+1; j<=high; j++) {
			if (numbers[j] <= pivot) {
				i++;
				if (i !=j) {
					exchange(numbers,  i, j);
				}
			}
		}
		exchange(numbers, low, i);
		return i;
	}

	/* M�todo QuickSort2. 
	 * Dado un array de enteros numbers y dos posiciones, inicial (low) y final (high),
	 * ordena no decrecientemente los elementos del array entre las posiciones inicial (low) y final (high). 
	 * Utiliza pivotbis: con dos �ndices encuentra la primera ocurrencia y la �ltima del pivote. 
	 * Primera invocaci�n: quickSort(A,0,A.length-1)
	 */
	public static void quickSort2(int []numbers, int low, int high) { 
		int posicionesPivote[]= new int[2];

		if (low<high) {
			pivotebis (numbers, low, high, posicionesPivote);   
			quickSort2(numbers, low, posicionesPivote[0]-1);
			quickSort2(numbers, posicionesPivote[1]+1, high);
		}
	}

	/* 
	 * M�todo pivotebis. 
	 * Dado un array de enteros T y dos posiciones, inicial i y final j, 
	 * Este procedimiento elige como pivote el contenido de la primera casilla, T[inicial], 
	 * modifica el array colocando juntas todas las ocurrencias del pivote
	 * dejando a la izquierda de la primera ocurrencia del pivote los elementos m�s peque�os que �ste
	 * a continuaci�n, todos los elementos iguales al pivote
	 * y a la derecha de la �ltima ocurrencia del pivote los elementos m�s grandes que �ste,
	 * devuelve en posicionesPivote[0] la primera ocurrencia del pivote
	 * devuelve en posicionesPivote[1] la �ltima ocurrencia del pivote
	 */
	public static void pivotebis(int []T, int i, int j, int []posicionesPivote){
			
			int k,l; // posicionesPivote[0] y posicionesPivote[1]
			
			//inicializo el pivote y las posiciones inferior y superior donde se encuentra
			int p = T[i];
			k = i-1;
			l = j+1;
			int m = i; // variable para ir inspeccionando el array
			while (m < l) {//mientras haya elementos que inspeccionar
				if (T[m] == p)
					m++; // m avanza con los elementos iguales al pivote
				else
					if (T[m] > p){
						l--; // dejo a la derecha de l los elementos mayores al pivote
						exchange(T,m,l); // aqu� no avanzo m porque no se qu� hab�a en la posici�n l
					} else {
						k++; // dejo a la izquierda de k los elementos menores al pivote
						exchange(T,m,k);
						m++;
					}
			} 
			
		posicionesPivote[0]=k+1;
		posicionesPivote[1]=l-1;
	}
	
	/* 
	 * M�todo exchange: intercambia el contenido de dos posiciones de un array de enteros.	  
	 */
	public static void exchange(int []numbers, int i, int j){
		int k = numbers[i];
		numbers[i]=numbers[j];
		numbers[j]=k;
	}
	
	public static void main(String args[]){
		
		int [] A = {4,5,3,4,5,8,4,8,3,2};

		System.out.print("A = ");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
		System.out.println();

		int l = pivot(A,0,A.length-1);
		System.out.println("Valor devuelto por pivote " + l);

		System.out.print("Tras la primera invocaci�n a Pivot A = ");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
		System.out.println();

		quickSort(A,0,A.length-1);


		System.out.print("Ordenado A = ");
		for (int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
		System.out.println();

		
		// probamos ahora el pivotebis
		//int [] B = {4,5,3,4,5,8,4,8,3,2};
		int [] B = {4,8,5,3,4,5,8,4,8,3,4,2};
		int [] posicionPivote = new int[2];

		System.out.print("B = ");
		for (int i = 0; i < B.length; i++)
			System.out.print(B[i] + " ");
		System.out.println();

		pivotebis(B,0,B.length-1, posicionPivote);
		System.out.println("posici�n inicial del primer pivote " + (posicionPivote[0]));
		System.out.println("posici�n final del primer pivote " + (posicionPivote[1]));
		System.out.print("Tras la primera invocaci�n a pivotebis B = ");
		for (int i = 0; i < B.length; i++)
			System.out.print(B[i] + " ");
		System.out.println();

		quickSort2(B,0,B.length-1);


		System.out.print("Ordenado B = ");
		for (int i = 0; i < B.length; i++)
			System.out.print(B[i] + " ");
		System.out.println();
	}
}
