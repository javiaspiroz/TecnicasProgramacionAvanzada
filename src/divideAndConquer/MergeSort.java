package divideAndConquer;

public class MergeSort {

	public static void main(String[] args) {
		int [] a = {9,3,5,2,1,6,4,3};
		mergeSort(a, 0, a.length-1);
		for (int i=0; i<a.length; i++){
			System.out.print(a[i]+" ");
		}
	}

	public static void mergeSort(int a[], int ini, int fin){//part 2 pag 7
		if (ini<fin){
			int med= ini + (fin-ini)/2;
			//en vez de crear otros dos arrays como en el pseudocodigo, simplemente paso la parte del array que me interesa
			mergeSort(a, ini, med);
			mergeSort(a, med+1, fin);
			merge(a, ini, fin, med);
		}
	}
	
	public static void merge(int[] a, int ini, int fin, int med){
		int [] aux = new int [a.length];
		for (int i=ini; i<=fin; i++){
			aux[i]=a[i];
		}
		int i=ini;
		int j=med+1;
		int cont=ini;
		while (i<=med && j<=fin){
			if(aux[i]<=aux[j]){
				a[cont]=aux[i];
				i++;
			}
			else{
				a[cont]=aux[j];
				j++;
			}
			cont++;
		}
		while (i<=med){
			a[cont]=aux[i];//si llego antes la j, copio los datos que me queden
			cont++;
			i++;
		}
		while (j<=fin){
			a[cont]=aux[j];//si llego antes la i, copio los datos que me queden
			cont++;
			j++;
		}
	}
}