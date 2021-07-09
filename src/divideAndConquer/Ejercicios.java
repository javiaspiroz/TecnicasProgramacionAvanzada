package divideAndConquer;

public class Ejercicios {
	public static void main(String[] args) {
		int [] a ={1,2,6}, b={1,2,5};
		System.out.println(ejercicio1(a,b,0));
	}

	public static int ejercicio1(int a[], int b[], int i){//solo recursivo no dyc
		int pos=-1;
		if (a[i]!=b[i]){
			pos=i;
		}
		else if (i==a.length-1){
			pos=-1;
		}
		else{
			pos=ejercicio1(a,b,i+1);
		}
		return pos;
	}
	
	//ejercico 2 contrario al hecho en clase, en este caso es el minimo


}