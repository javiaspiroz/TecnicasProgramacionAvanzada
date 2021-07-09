package iterVsRecursivo;

public class Factorial {

	public static int factRecur(int n){
		if (n==0){
			System.out.print("*");//traza de ejecucion
			return 1;
		}
		else{
			System.out.print("*");//traza de ejecucion
			return n*factRecur(n-1);
		}
	}
	
	public static int factIter(int n){
		int result = 1;
		for (int i=1; i<=n; i++){
			System.out.print("+");//traza de ejecucion
			result=result*i;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(" "+factIter(24));
		System.out.println(" "+factRecur(24));
	}
}