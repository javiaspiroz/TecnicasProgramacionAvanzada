package practicas;

public class Practica1Ejercicio2 {
	
	public static int busquedaCuaternaria(int[] a, int find, int ini, int fin) {
		int pos = -1;//almaceno la posicion, -1 si no esta
		if(a[ini]== find) {//compruebo posicion inicial          
			pos = ini;
		}
		else if(a[fin]==find) {//compruebo posicion final
			pos = fin;
		}
		//compruebo que find esta en fragmento del array
		else if (a[ini] > find || a[fin]<find ||  ini > fin ) { 			
			pos = -1;
		}
		else {
			int med = (ini + fin)/2;//obtengo mitad del array
			int q1 = med/2;//obtengo el limite del primer cuarto
			int q3 = (med+fin)/2;//obtengo el limite del tercer cuarto
			//compruebo que los limites no sean find
			if(a[med]==find) {
				pos = med;
			}
			else if(a[q1] == find) {
				pos = q1;
			}
			else if(a[q3] == find) {
				pos = q3;
			}
			//invoco la funcion dividiendo el array en 4 si no he encontrado el elemento
			else if(find < med) {
				pos = busquedaCuaternaria(a, find, ini+1, med-1);
			}
			else if(find > med && find < q1){
				pos = busquedaCuaternaria(a, find, med+1, q1-1);
			}
			else if(find > q1 && find < q3) {
				pos = busquedaCuaternaria(a, find, q1+1, q3-1);
			}
			else {
				pos = busquedaCuaternaria(a, find, q3+1, fin-1);
			} 
		}
		return pos;
	}
	
	public static void main(String[] args) {
		int [] arr = {1, 5, 6, 11, 20, 115, 900};
		System.out.println(busquedaCuaternaria(arr, 115, 0, arr.length-1));
	}
}