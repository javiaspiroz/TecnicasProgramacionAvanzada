package divideAndConquer;

public class BusquedaBinaria {

	public static int busquedaBinaria(int [] a, int find, int inic, int fin){//para lista ordenada, part 1 pag 42 con boolean
		int res=-1;//devolvera esto si no esta en la lista
		if (fin<inic){
			res=-1;
		}
		else{
			int medio = (inic+fin)/2;
			if (a[medio]==find){
				res=a[medio];
			}
			else if (a[medio]>find){
				res=busquedaBinaria(a, find, inic, medio-1);
			}
			else{
				res=busquedaBinaria(a, find, medio+1, fin);
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int a [] = {1, 5, 7, 9, 14};
		System.out.println(busquedaBinaria(a, 9, 0, a.length-1));
	}
}