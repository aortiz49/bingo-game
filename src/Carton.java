import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Carton {

    public String nombreJugador;
    public ArrayList<ArrayList<String>> listaDeLineas;
    public HashMap<Integer, Integer> values;


    public Carton(int fileIndex) throws IOException {

        listaDeLineas = new ArrayList<>();
        values = new HashMap<>();
        File file = new File("in/carton" + fileIndex + ".txt");
        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string

        nombreJugador = br.readLine();

        while ((st = br.readLine()) != null) {
            // Print the string
            String[] arr = st.split(" ");
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(arr));
            listaDeLineas.add(temp);
        }
        System.out.println();

        for (int i = 0; i < listaDeLineas.size(); i++) {
            ArrayList<String> linea = listaDeLineas.get(i);
            for (int j = 0; j < linea.size(); j++) {
                String curr = linea.get(j);
                if (!curr.equals("*")) {
                    values.put(Integer.parseInt(curr), i);
                }
            }
        }
        //System.out.println(values.toString());

    }



}

