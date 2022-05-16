import java.util.ArrayList;

public class Jugador {

    public String nombre;
    public Carton carton;
    public ArrayList<Integer>[] lineas;
    public int puntos;
    public int numTachados;
    public ArrayList<Integer> tachados;

    public Jugador(Carton carton, String nombre) {
        this.numTachados = 0;
        this.carton = carton;
        this.nombre = nombre;
        this.tachados = new ArrayList<>();
        lineas = new ArrayList[3];
        lineas[0] = new ArrayList<>();
        lineas[1] = new ArrayList<>();
        lineas[2] = new ArrayList<>();

    }

    public void imprimirCarton() {
        System.out.println("Cartón \"" + nombre + "\": ");
        ArrayList<ArrayList<String>> lineasCarton = carton.listaDeLineas;
        for (int i = 0; i < lineasCarton.size(); i++) {
            ArrayList<String> linea = lineasCarton.get(i);
            System.out.print("\t LÍNEA " + (i + 1) + ": ");
            for (int j = 0; j < linea.size(); j++) {
                System.out.print(linea.get(j) + " ");
            }
            System.out.println();
        }
    }

    public void tachar() {
        this.numTachados++;
    }

    public void limpiar() {
       lineas[0].clear();
       lineas[1].clear();
       lineas[2].clear();
       tachados.clear();
       numTachados = 0;
    }


}
