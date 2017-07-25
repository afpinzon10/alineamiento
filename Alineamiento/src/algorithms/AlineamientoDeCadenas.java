package algorithms;

public class AlineamientoDeCadenas {
	String cadena1;
	String cadena2;
	
	int[][] puntajes;
	int[][] caminos;
	String[] salida;
	
	public AlineamientoDeCadenas(String c1, String c2) {
		puntajes = new int[c1.length()+2][c2.length()+2];
		caminos = new int[c1.length()+2][c2.length()+2];
		cadena1 = c1;
		cadena2 = c2;
		salida = new String[2];
	}
	
	public String[] alinearCadenas(){
		int num = -1;
		puntajes[0][0] = 0;
		for (int i = 1; i < puntajes.length; i++) {
			puntajes[0][i] = num;
			puntajes[i][0] = num;
			//izq=1,arr=2,diag=3
			caminos[0][i] = 1;
			caminos[i][0] = 2;
			num--;
		}
		
		for (int i = 2; i < puntajes.length; i++) {
			for (int j = 2; j < puntajes[0].length; j++) {
				int izq = puntajes[i][j-1]-1;
				int arr = puntajes[i-1][j]-1;
				boolean igual = cadena1.charAt(i) == cadena2.charAt(j);
				int diag = puntajes[i-1][j-1] + (igual ? 1 : -1);
				int maximo = Math.max(diag,Math.max(izq, arr));
				puntajes[i][j] = maximo;
				if(diag == maximo && igual){
					caminos[i][j] = 3;
				}else if(arr == maximo){
					caminos[i][j] = 1;
				}else{
					caminos[i][j] = 2;
				}
			}
		}
		int[]secuencia = new int[cadena1.length()+cadena2.length()];
		
		int fila =cadena1.length()+1;
		int col = cadena2.length()+1;
		String revez ="";
		String revez2 ="";
		
		while (fila !=0 && col !=0){
			if(	caminos[fila][col] == 3){
				revez += cadena1.charAt(fila);
				revez +=cadena2.charAt(col);
				fila--;
				col--;
			}else if(caminos[fila][col] == 2){
				revez2+="-";
				revez+= cadena1.charAt(fila);
				col--;
			}else{
				revez+="-";
				revez2+= cadena1.charAt(col);
				fila--;
			
			}
		}
			
	;
		
		
		
		
	}
}
