package iterVsRecursivo;

public class BuscarElemArray {
	
	public static boolean searchIter (int [] a, int b){
		boolean found = false;
		for (int i=0; i<a.length; i++){
			System.out.print("*");//traza de ejecucion
			if (b==a[i]){
				return found=true;//si encontrado salgo antes del bucle, la complejidad será la misma
			}
		}
		return found;
	}
	
	public static boolean searchRecur (int [] a, int b, int pos){
		boolean found = false;
		if (a[pos]==b){
			found = true;
			System.out.print("+");//traza de ejecucion
		}
		else if(a.length-1==pos/* && a[pos]!=b*/){//si llegamos al final del array y no esta
			System.out.print("+");//traza de ejecucion
			found = false;//tambien valdria continue
		}
		else{
			System.out.print("+");//traza de ejecucion
			return searchRecur(a, b, pos+1);
		}
		return found;
	}

	public static void main(String[] args) {
		int [] a = { 2, 5, 7, 9};
		System.out.println(" "+searchIter(a, 5));
		System.out.println(" "+searchRecur(a, 5, 0));
	}
}