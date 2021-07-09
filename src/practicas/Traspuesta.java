package practicas;//quitar para la entrega

/**
 * 
 * @author Javier Aspiroz de la Calle
 * @expediente 21957644
 * @fecha 20-04-2020
 *
 */

public class Traspuesta {

	public static boolean esTraspuesta_v1 (int[][] a, int[][] b) {
		boolean traspuesta = true;
		//Recorro la matriz
		for (int fila = 0; fila < a.length; fila++) {
			for (int col = 0; col < a[fila].length; col++) {
				if (a[fila][col] != b[col][fila])
					traspuesta = false;//si no se cumple en alguna ocasion es falso
			}
		}
		return traspuesta;
	}
	
	public static boolean esTraspuesta_v2 (int[][] a, int[][] b) {
		boolean traspuesta = true;
		//pasamos el las matrices a arrays 
		int size = a.length*a[0].length;
		int [] c = new int [size];
		int [] d = new int [size];
		//ordeno por filas
		int i=0, j=0, k=0;
		while (i < size){
			c[i]=a[j][k];
			i++; k++;
			if (k==a.length){
				j++;
				k=0;
			}
		}
		
		//ordeno por columnas
		i=0; j=0; k=0;
		while (i<size){
			d[i]=b[j][k];
			i++; j++;
			if (j==a.length){
				k++;
				j=0;
			}
		}		
		//compruebo si no son iguales
		for (i =0; i<size; i++){
			if (c[i]!=d[i])
				traspuesta=false;
		}
		
		return traspuesta;
	}
	
	public static boolean esTraspuesta_DyV (int[][] a, int[][] b) {
		//hago la traspuesta de b con menor complejidad posible (usando 1 solo bucle)
		int [][] c = new int [b.length][b.length];
		int i=0, j=0, k=0;
		
		//paso la matriz a array
		int size = b.length*b[0].length;
		int [] aux = new int [size];//array auxiliar
		while (i < size){
			aux[i]=b[j][k];
			i++; k++;
			if (k==b.length){
				j++;
				k=0;
			}
		}
		//paso el array aux a una matriz c ordenandolo como si fuera la traspuesta de a
		int w=0;
		i=0; j=0;
		while (w<size){
			c[j][i]=aux[w];
			w++;
			j++;
			if (j==c.length){
				i++;
				j=0;
			}
		}
		
		//invoco a la funcion que compara si dos matrices son iguales
		//al tener a y la traspuesta de b (c), si a y c son iguales; a sera la traspuesta de b
		return matricesIgualesDC(a, c, 0, a.length-1, 0, a[a.length-1].length-1);
	}
	
	//Comparo si dos matrices son iguales por divide y venceras
	private static boolean matricesIgualesDC(int[][] a, int[][] b, int filIni, int filFin, int colIni, int colFin){
		if (filIni==filFin){//caso base
			return (a[filIni][colIni]==b[filIni][colIni]);
		}
		else{//caso general, divido en 4 submatrices
			return matricesIgualesDC(a, b, filIni, (filFin+filIni)/2, colIni, (colIni+colFin)/2)
				&& matricesIgualesDC(a, b, filIni, (filFin+filIni)/2, (colIni+colFin)/2, colFin)
				&& matricesIgualesDC(a, b, ((filFin+filIni)/2)+1, filFin, colIni, (colIni+colFin)/2)
				&& matricesIgualesDC(a, b, ((filFin+filIni)/2)+1, filFin, ((colIni+colFin)/2)+1, colFin);
		}
	}
	
	public static void main(String[] args) {
		
		int[][] m1 = {	{1,2,3,4},
						{5,6,7,8},
						{1,2,3,4},
						{5,6,7,8}};

		int[][] m2 = {	{1,5,1,5},
						{2,6,2,6},
						{3,7,3,7},
						{4,8,4,8}};	
		
		int[][] m3 = {	{0,5,1,5},
						{2,6,2,6},
						{3,7,3,7},
						{4,8,4,8}};	
	
		System.out.println("PRUEBA 1:");
		System.out.println(" * esTraspuesta_v1 (m1, m2): " + esTraspuesta_v1(m1,m2));
		System.out.println(" * esTraspuesta_v1 (m1, m3): " + esTraspuesta_v1(m1,m3));
		
		System.out.println("\nPRUEBA 2:");
		System.out.println(" * esTraspuesta_v2 (m1, m2): " + esTraspuesta_v2(m1,m2));
		System.out.println(" * esTraspuesta_v2 (m1, m3): " + esTraspuesta_v2(m1,m3));
		
		System.out.println("\nPRUEBA 3:");
		System.out.println(" * esTraspuesta_DyV (m1, m2): " + esTraspuesta_DyV(m1,m2));
		System.out.println(" * esTraspuesta_DyV (m1, m3): " + esTraspuesta_DyV(m1,m3));
		
		/* ****** DATOS PERSONALES ****** */
		
		String miNombre = "Javier";
		String misApellidos = "Aspiroz de la Calle";
		String miExpediente = "21957644";
		
		System.out.println("\nESTUDIANTE:");
		System.out.println(" * Apellidos:\t" + miNombre);
		System.out.println(" * Nombre:\t" + misApellidos);
		System.out.println(" * Expediente:\t" + miExpediente);
		
		System.out.println("\n*** FIN ***");
	
	}//main

}//class
