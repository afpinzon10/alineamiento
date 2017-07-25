package algorithms;

public class AlineamientoDeCadenas {
	String cadena1;
	String cadena2;
	
	int[][] puntajes;
	int[][] caminos;
	String[] salida;
	int puntaje = 0;
	
	public AlineamientoDeCadenas(String c1, String c2) {
		puntajes = new int[c1.length()+1][c2.length()+1];
		caminos = new int[c1.length()+1][c2.length()+1];
		cadena1 = c1;
		cadena2 = c2;
		salida = new String[2];
		alinearCadenas();
	}

	public int getPuntaje() {
		return puntaje;
	}

	private void alinearCadenas(){
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
		
		for (int i = 1; i < puntajes.length; i++) {
			for (int j = 1; j < puntajes[0].length; j++) {
				int izq = puntajes[i][j-1]-1;
				int arr = puntajes[i-1][j]-1;
				boolean igual = cadena1.charAt(i-1) == cadena2.charAt(j-1);
				int diag = puntajes[i-1][j-1] + (igual ? 1 : -1);
				int maximo = Math.max(diag,Math.max(izq, arr));
				puntajes[i][j] = maximo;
				if(diag == maximo){
					caminos[i][j] = 3;
				}else if(arr == maximo){
					caminos[i][j] = 1;
				}else{
					caminos[i][j] = 2;
				}
			}
		}
		
		int fila =cadena1.length();
		int col = cadena2.length();
		String revez ="";
		String revez2 ="";
		for (int[] is : caminos) {
			for (int i : is) {
				System.out.print(i);
			}
			System.out.println();
		}
		while (fila !=0 && col !=0){
			if(	caminos[fila][col] == 3){
				revez += cadena1.charAt(fila-1);
				revez2 +=cadena2.charAt(col-1);
				if (cadena1.charAt(fila-1)!=cadena2.charAt(col-1)) {
					puntaje++;
				}
				fila--;
				col--;
				
			}else if(caminos[fila][col] == 2){
				revez2+="-";
				revez+= cadena1.charAt(fila-1);
				col--;
				puntaje++;
			}else{
				revez+="-";
				revez2+= cadena2.charAt(col-1);
				fila--;
				puntaje++;
			}
		}
		salida[0] = invertir(revez.toCharArray());
		salida[1] = invertir(revez2.toCharArray());
	}

	public String invertir(char[] array) {
        char[] invertido = new char[array.length];
        int maximo = array.length;
 
        for (int i = 0; i<array.length; i++) {
            invertido[maximo - 1] = array[i];
            maximo--;
        }
        
        return String.valueOf(invertido);
    }
	
	public String[] getSalida() {
		return salida;
	}
	
}
