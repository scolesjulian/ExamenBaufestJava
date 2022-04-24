package com.baufest.ingreso.alphabetSoup;

import java.util.ArrayList;
import java.util.List;

public class WordSearcher {

    private char soup[][];

    public WordSearcher(char soup[][]){
        this.soup = soup;
    }

    /**
     * El objetivo de este ejercicio es implementar una función que determine si una palabra está en una sopa de letras.
     *
     * ### Reglas
     * - Las palabras pueden estar dispuestas direcciones horizontal o vertical, _no_ en diagonal.
     * - Las palabras pueden estar orientadas en cualquier sentido, esto es, de derecha a izquierda o viceversa, y de arriba
     * para abajo o viceversa.
     * - El cambio de dirección puede estar a media palabra, de modo que, por ejemplo, parte de la palabra
     * esté horizontal y de izquierda a derecha, parte esté vertical y de arriba hacia abajo, y otra parte horizontal
     * de derecha a la izquierda.
     *
     * @param word	Palabra a buscar en la sopa de letras.
     *
     * @return {@link Boolean}	true si la palabra se encuentra
     * en la sopa de letras.
     * */
    public boolean isPresent(String word){
        char[] letras = word.toCharArray();
        boolean palabraEncontrada = false;

        int ultimaFila = this.soup.length;
        int ultimaColumna = this.soup[0].length;

        int indiceLetra = 0;
        List<String> memoriaRecorrido = new ArrayList<String>();

        busqueda:
        for(int fila = 0; fila < ultimaFila; fila++){
            for(int columna = 0; columna < ultimaColumna; columna++){
                palabraEncontrada = letraEncontrada(letras, indiceLetra, fila, columna, memoriaRecorrido);
                if(palabraEncontrada){
                    break busqueda;
                }
            }
        }

        return palabraEncontrada;
    }

    private boolean letraEncontrada(char[] letras, int indiceLetra, int fila, int columna, List<String> memoriaRecorrido) {
        boolean palabraEncontrada = false;

        if (letras[indiceLetra] == this.soup[fila][columna]) {
            System.out.println("letra " + letras[indiceLetra] + " encontrada en fila " + fila + " columna " + columna);

            memoriaRecorrido.add(fila +"-"+ columna);

            if(indiceLetra == letras.length-1){
                return true;
            }
            indiceLetra++;

            int filaMas = fila + 1;
            int filaMenos = fila - 1;
            int colMas = columna + 1;
            int colMenos = columna - 1;
            int ultimaFila = this.soup.length - 1;
            int ultimaCol = this.soup[0].length - 1;

            if (filaMas <= ultimaFila && !memoriaRecorrido.contains(filaMas + "-" + columna)) {
                palabraEncontrada =  letraEncontrada(letras, indiceLetra, filaMas, columna, memoriaRecorrido);
            }
            if (filaMenos >= 0 && !palabraEncontrada && !memoriaRecorrido.contains(filaMenos + "-" + columna)) {
                palabraEncontrada =  letraEncontrada(letras, indiceLetra, fila - 1, columna, memoriaRecorrido);
            }
            if (colMas <= ultimaCol && !palabraEncontrada && !memoriaRecorrido.contains(fila + "-" + colMas)) {
                palabraEncontrada =  letraEncontrada(letras, indiceLetra, fila, colMas, memoriaRecorrido);
            }
            if (colMenos >= 0 && !palabraEncontrada && !memoriaRecorrido.contains(fila + "-" + colMenos)) {
                palabraEncontrada = letraEncontrada(letras, indiceLetra, fila, colMenos, memoriaRecorrido);
            }
            if (filaMas <= ultimaFila && colMas <= ultimaCol && !palabraEncontrada && !memoriaRecorrido.contains(filaMas + "-" + colMas)) {
                palabraEncontrada =  letraEncontrada(letras, indiceLetra, filaMas, colMas, memoriaRecorrido);
            }
            if (filaMenos >= 0 && colMenos >= 0 && !palabraEncontrada && !memoriaRecorrido.contains(filaMenos + "-" + colMenos)) {
                palabraEncontrada =  letraEncontrada(letras, indiceLetra, filaMenos, colMenos, memoriaRecorrido);
            }
            if (filaMenos >= 0 && colMas <= ultimaCol && !palabraEncontrada && !memoriaRecorrido.contains(filaMenos + "-" + colMas)) {
                palabraEncontrada =  letraEncontrada(letras, indiceLetra, filaMenos, colMas, memoriaRecorrido);
            }
            if (filaMas <= ultimaFila && colMenos >= 0 && !palabraEncontrada && !memoriaRecorrido.contains(filaMas + "-" + colMenos)) {
                palabraEncontrada = letraEncontrada(letras, indiceLetra, filaMas, colMenos, memoriaRecorrido);
            }
        } else {
            System.out.println("" + this.soup[fila][columna] + " en fila " + fila + " columna " + columna + " no coincide");
        }
        return palabraEncontrada;
    }
}