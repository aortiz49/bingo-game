import java.io.IOException;
import java.util.HashSet;

public class Juego {

    public int numPartidas;
    public Jugador ganador;
    public Partida[] rondas;
    public Jugador[] jugadores;
    public int puntosLinea;
    public int puntosBingo;

    public Juego(int numPartidas, int numJugadores, int puntosLinea, int puntosBingo) throws IOException {
        this.numPartidas = numPartidas;
        this.rondas = new Partida[numPartidas];
        this.jugadores = new Jugador[numJugadores];
        this.puntosLinea = puntosLinea;
        this.puntosBingo = puntosBingo;
        this.ganador = null;

        // crear jugadores con sus cartones
        for (int i = 0; i < numJugadores; i++) {

            Carton carton = new Carton(i + 1);

            String nombre = carton.nombreJugador;
            Jugador jugador = new Jugador(carton, nombre);
            jugadores[i] = jugador;
        }

        for (int i = 0; i < numPartidas; i++) {
            rondas[i] = new Partida(jugadores);
        }
    }


    public void calcualarMaximo(){
        int max = 0;
        for (int i = 0; i < jugadores.length; i++) {
            int currPuntos = jugadores[i].puntos;
            if(currPuntos > max){
                ganador = jugadores[i];
                max = currPuntos;
            }
        }

    }
}
