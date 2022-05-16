import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Partida {


    public ArrayList<Integer> bombo;
    public Jugador[] jugadores;
    public ArrayList<Integer> bolasSacadas;
    public boolean linea;

    public static final int MAX = 90;

    public Partida(Jugador[] jugadores) {
        this.linea = false;
        this.jugadores = jugadores;
        this.bolasSacadas = new ArrayList<>();
        bombo = new ArrayList<>();
        // crear bombo de 90 valores
        for (int i = 0; i < MAX; i++) {
            bombo.add(i + 1);
        }
    }

    public void limpearJugadores(){
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].limpiar();
        }
    }

    public void printTotales(){
        for (int i = 0; i <jugadores.length ; i++) {
            System.out.println("Puntos finales de : " + jugadores[i].nombre+ " : " + jugadores[i].puntos);
        }
    }
}
