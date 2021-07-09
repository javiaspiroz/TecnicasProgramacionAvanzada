package divideAndConquer;

public class MultiplicarNumsGrandes {

	public static void main(String[] args) {
		System.out.println(multiplicar(1981, 1234, 4));
	}
	
	public static int multiplicar (int x, int y, int n){//part 1 pag 29
		int sol = 0;
		if (x<Math.pow(10, 2) && y<Math.pow(10, 2)){
			sol=x*y;
		}
		else{
			//descomponer, usar modulo
			int b = (int) (x%Math.pow(10, n/2));
			int a = (int) ((x-b)/Math.pow(10, n/2));
			int d = (int) (y%Math.pow(10, n/2));
			int c = (int) ((y-d)/Math.pow(10, n/2));
			
			int s1 = multiplicar(a, c, n/2);
			int s2 = multiplicar(a, d, n/2);
			int s3 = multiplicar(b, c, n/2);
			int s4 = multiplicar(b, d, n/2);
			//desplazar numeros, modificar para usar for?
			s1 = (int) (s1*Math.pow(10, n));
			int aux = s2+s3;
			aux = (int) (aux*Math.pow(10, n/2));
			sol = s1+aux;
			sol += s4;
		}
		return sol;
	}
}