package divideAndConquer;

public class QuickSort {//part 3

	public static void main(String[] args) {
		int arr[] = {9,3,5,2,1,6,4,3};
		imprimirLista(arr);
		System.out.println();
		System.out.println("Número en el índice 3: "+ seleccionar(arr, 3));
		Quicksort(arr, 0, arr.length-1);
		imprimirLista(arr);
	}
	
	public static void imprimirLista(int arr[]) {
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
	
	public static void Quicksort(int arr[], int ini, int fin) {
		if (ini<fin) {
			int pos_pivote = pivotar(arr, ini, fin);
			Quicksort(arr, ini, pos_pivote-1);
			Quicksort(arr, pos_pivote+1, fin);
		}
	}
	
	public static int pivotar(int arr[], int ini, int fin) {
		int i=ini;
		int p=arr[ini];
		for (int j = ini+1; j<=fin; j++) {
			if (arr[j]<p) {
				i++;
				if (i != j) {
					//intercambiar
					int aux=arr[i];
					arr[i]=arr[j];
					arr[j]=aux;
				}
			}
		}
		//intercambiar
		int aux=arr[i];
		arr[i]=arr[ini];
		arr[ini]=aux;
		return i;
	}
	
	public static int seleccionar(int arr[], int k) {//pequeñas mods al pseudocodigo comentadas
		int ini=0;//ini=1
		int fin = arr.length-1;//fin=n => fin=arr.length
		int x=pivotar(arr, ini, fin);
		while (k!=x){
			if (x>k){
				fin = x-1;
			}
			else{
				ini = x+1;
			}
			x=pivotar(arr, ini, fin);
		}
		return arr[x];
	}
}