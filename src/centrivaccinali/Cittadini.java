/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrivaccinali;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author De Blasi Lorenzo, n°745124, VA
 * @author Ricci Claudio, n°747555, VA
 * @author Cheema Umair Tariq, n°746934, VA
 */
public class Cittadini {
    String[] dati_utente = new String[7];

    static Path currentRelativePath = Paths.get("");
    static String path = currentRelativePath.toAbsolutePath().toString();

    public Cittadini(String[] dati) {
            this.dati_utente = dati;
    }

    /**
     * Metodo statico che scrive i dati presenti su un array di String nel file "cittadiniregistrati.txt"
     */
    public void registraCittadino(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path+"\\data\\cittadiniregistrati.txt",true));
            for (int i=0;i <dati_utente.length;i++){
                bw.write(dati_utente[i]+ " |");
                bw.flush();
            }
            bw.newLine();
            bw.close();
        }catch(IOException e){
        e.printStackTrace();
        }
    }
}

