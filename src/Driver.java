import java.io.IOException;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Introducir el número de partidas a jugar: ");
        int partidas = scan.nextInt();

        System.out.println("Introducir el número de jugadores: ");
        int jugadores = scan.nextInt();

        System.out.println("Puntos por cantar línea: ");
        int puntosLinea = scan.nextInt();

        System.out.println("Puntos por cantar bingo: ");
        int puntosBingo = scan.nextInt();

        ////////////
        // se ha creado un juego
        Juego juego = new Juego(partidas, jugadores, puntosLinea, puntosBingo);
        System.out.println("Comienza la ronda de " + (juego.numPartidas) + "  partidas!");


        for (int i = 0; i < juego.numPartidas; i++) {

            // numero de jugadas
            int jugada = 1;
            System.out.println();
            System.out.println("PARTIDA " + (i + 1) + "\n");
            Partida curPartida = juego.rondas[i];
            curPartida.linea = false;
            curPartida.limpearJugadores();

            int bolasBombo = curPartida.bombo.size();

            Jugador[] jugadoresLista = juego.jugadores;

            for (int j = 0; j < jugadoresLista.length; j++) {
                jugadoresLista[j].imprimirCarton();
                System.out.println();
            }


            ArrayList<String> tienen = new ArrayList<>();
            for (; ; ) {
                tienen.clear();
                System.out.println("\nJugada " + jugada++ + ": ");
                int index = (int) (Math.random() * ((bolasBombo)));

                if (curPartida.bombo.isEmpty()) {
                    System.out.println("NO HAY MÁS BOLAS");
                    break;
                }
                int bolaSacada = curPartida.bombo.remove(index);
                bolasBombo--;
                curPartida.bolasSacadas.add(bolaSacada);
                System.out.println("Se saca del bombo la bola: " + bolaSacada);
                //System.out.println(curPartida.bombo);
                System.out.print("Bolas sacadas: ");

                for (int bola : curPartida.bolasSacadas) {
                    System.out.print(bola + " ");
                }


                /// revisar quien tiene esa bola en el carton....
                // esta lista guarda nombres de los que tienen la bola

                Jugador tieneLinea = null;
                Jugador tieneBingo = null;
                int indexLinea = 0;
                for (int k = 0; k < jugadoresLista.length; k++) {
                    Jugador curJugador = jugadoresLista[k];
                    Carton curCarton = curJugador.carton;
                    HashMap<Integer, Integer> curValores = curCarton.values;

                    // esto debe dar 0,1,o 2. (representa la linea donde esta en el carton)
                    Integer encontrado = curValores.get(bolaSacada);
                    if (encontrado != null) {
                        ArrayList<Integer> curLinea = curJugador.lineas[encontrado];
                        curLinea.add(bolaSacada);
                        tienen.add(curJugador.nombre);

                        // tachar uno cuando tengo una bola
                        curJugador.tachar();

                        // agregar a lista de tachados
                        curJugador.tachados.add(bolaSacada);

                        // si el jugador llena una linea, sumamos los puntos de linea
                        if (curLinea.size() == 5 && !curPartida.linea) {
                            curJugador.puntos += juego.puntosLinea;
                            curPartida.linea = true;
                            tieneLinea = curJugador;
                            indexLinea = encontrado;
                        }

                        // si el jugador tiene bingo, decir bingo!
                        int total = curJugador.numTachados;
                        if (total == 15) {
                            tieneBingo = curJugador;
                            curJugador.puntos += juego.puntosBingo;
                        }
                    }
                }

                // se termina la jugada
                if (!tienen.isEmpty()) {
                    System.out.print("\nLa tiene: ");
                    for (int k = 0; k < tienen.size(); k++) {
                        System.out.print(tienen.get(k) + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("\nNo la tiene nigún jugador!");
                }

                // mensaje si hay linea
                if (tieneLinea != null) {
                    System.out.println("¡Línea de " + tieneLinea.nombre + "!");
                    System.out.print("Numero de la línea (en orden de aparición): ");
                    for (int l = 0; l < tieneLinea.lineas[indexLinea].size(); l++) {
                        System.out.print(tieneLinea.lineas[indexLinea].get(l) + " ");
                    }
                    System.out.println();
                }

                // mensaje si hay bingo
                if (tieneBingo != null) {
                    System.out.println("¡Bingo de " + tieneBingo.nombre + "!!!!!");

                    ArrayList<Integer> tachados = tieneBingo.tachados;
                    System.out.print("Números del bingo (en orden de aparición): ");
                    for (int l = 0; l < tachados.size(); l++) {
                        System.out.print(tachados.get(l) + " ");
                    }
                    System.out.println();
                    break;
                }

             // FIN DE JUGADA
            }
            // FIN DE PARTIDA

        }
        // FIN DE JUEGO
       //Ganador ronda de 1 partidas de bingo: Marta, ¡enhorabuena! Puntos finales de Marta : 30 Puntos finales de Juan : 10

        // calcular maximo
        juego.calcualarMaximo();
        Jugador ganador = juego.ganador;
        System.out.println("\nGanador ronda de " + juego.numPartidas + " partidas  de bingo: " + ganador.nombre);

        // mostrar puntos finales
        for (int i = 0; i < juego.jugadores.length; i++) {
            Jugador cur = juego.jugadores[i];
            System.out.println("Puntos finales de " + cur.nombre + " : " + cur.puntos );
        }



    }


}
