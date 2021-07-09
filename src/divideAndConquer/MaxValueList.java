package divideAndConquer;

public class MaxValueList {
	
	public static int MaxValList (int [] a, int inic, int fin){
		int max=0;
		if (inic==fin){
			max = a[inic];
		}
		else{
			int medio = (inic+fin)/2;
			int maxA = MaxValList(a, inic, medio);
			int maxB = MaxValList(a, medio+1, fin);
			if(maxA>maxB){
				max=maxA;
			}
			else{
				max=maxB;
			}
		}		
		return max;
	}
	
	public static int MaxValMatrixDCIter (int [][] a, int inic, int fin){//solucion medio recursiva, usando divide and conquer
		int max=a[0][0];
		for (int i=0; i<a.length; i++){
			int maxFil = MaxValList(a[i], 0, a[i].length-1);
			if (maxFil>max){
				max=maxFil;
			}
		}
		return max;
	}
	
	public static int MaxValMatrixDC (int [][] a, int filIni, int filFin, int colIni, int colFin){//part 1 pag 20
		int max=a[0][0];
		
		if (filIni==filFin){
			if (colIni==colFin){
				max=a[filIni][colFin];
			}
			else{
				max=MaxValList(a[filIni], colIni, colFin);
			}
		}
		else if (colIni==colFin){//pseudocodigo: max=MaxValList(a[][colIni], filIni, filFin)
			//pasamos la columna a array
			int arr[] = new int [filFin-filIni];
			for (int i=0; i<arr.length; i++){
				arr[i]=a[i][colIni];
			}			
			max=MaxValList(arr, filIni, filFin);
		}
		else{
			int m1 = MaxValMatrixDC(a, filIni, (filIni+filFin)/2, colIni, (colIni+colFin)/2);
			int m2 = MaxValMatrixDC(a, filIni, (filIni+filFin)/2, ((colIni+colFin)/2)+1, colFin);
			int m3 = MaxValMatrixDC(a, ((filIni+filFin)/2)+1, filFin, colIni, (colIni+colFin)/2);
			int m4 = MaxValMatrixDC(a, ((filIni+filFin)/2)+1, filFin, ((colIni+colFin)/2)+1, colFin);
			max=maxCuatro(m1, m2, m3, m4);
		}
		return max;
	}
	
	public static int maxCuatro (int m1, int m2, int m3, int m4){
		int max=m1;
		if(m2>max){
			max=m2;
		}
		if(m3>max){
			max=m3;
		}
		if(m4>max){
			max=m4;
		}
		return max;
	}
	
	public static int MaxValMatrixIter (int [][] a/*, int inic, int fin*/){
		int max=a[0][0];
		for (int i=0; i< a.length; i++){
			for (int j=0; j<a[i].length; j++){
				if (a[i][j]>max){
					max=a[i][j];
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int [] a ={1,2,5,7,9, 4};
		System.out.println(MaxValList(a, 0, a.length-1));
		int [][] b = {{1,2,3},{8,45,0}};
		System.out.println(MaxValMatrixIter(b));
		System.out.println(MaxValMatrixDCIter(b, 0, b.length-1));
		System.out.println(MaxValMatrixDC(b, 0, b.length-1, 0, b[b.length-1].length-1));
	}
}