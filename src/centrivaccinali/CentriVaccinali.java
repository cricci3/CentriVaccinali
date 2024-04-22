/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrivaccinali;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

/**
 *
 * @author De Blasi Lorenzo, n°745124, VA
 * @author Ricci Claudio, n°747555, VA
 * @author Cheema Umair Tariq, n°746934, VA
 */
public class CentriVaccinali {

    /**
     *
     * @param args the command line arguments
     *
     */
    static Path currentRelativePath = Paths.get("");
    static String path = currentRelativePath.toAbsolutePath().toString();

    public static void main(String[] args) throws IOException {

        String input="";
        Cittadini cittadini = new Cittadini(null);
        Scanner sc = new Scanner(System.in);
        do{

            System.out.println("#=============================================#");
            System.out.println("|                 Menu'                       |");
            System.out.println("| 1) Sei un operatore? Scrivi 1               |");
            System.out.println("| 2) Sei un cittadino? Scrivi 2               |");
            System.out.println("| Per terminare l'applicazione Scrivi esci    |");
            System.out.println("#=============================================#");
            input = sc.nextLine();
            switch(input){
                case "esci":
                    System.out.println("#============================================#");
                    System.out.println("|                  Arrivederci               |");
                    System.out.println("#============================================#");

                    break;
                case "1":
                    System.out.println("#=============================================#");
                    System.out.println("| Buongiorno quale operazione vuole eseguire? |");
                    System.out.println("| 1) Aggiungi nuovi centri vaccinali          |");
                    System.out.println("| 2) Aggiungi nuovi vaccinati                 |");
                    System.out.println("| 0) Torna indietro                           |");
                    System.out.println("#=============================================#");
                    input = sc.nextLine();
                    switch(input){
                        case("0"):
                            break;
                        case("1"):
                            String[] input_centriVaccinali = new String[6];
                            do{
                                System.out.println("#==========================================================#");
                                System.out.println("|  Inserisci tutti i dati richiesti                        |");
                                System.out.println("#==========================================================#");
                                System.out.println("# -Inserisci il nome del centro vaccinale                  #");
                                input_centriVaccinali[0] = "Nome: "+sc.nextLine();
                                System.out.println("# -Inserisci la via/piazza (e numero) del centro vaccinale #");
                                input_centriVaccinali[1] = "via-piazza: "+sc.nextLine();
                                System.out.println("# -Inserisci il comune del centro vaccinale                #");
                                input_centriVaccinali[2] = "Comune: "+sc.nextLine();
                                System.out.println("# -Inserisci la sigla provinciale del centro vaccinale     #");
                                input_centriVaccinali[3] = sc.nextLine();
                                System.out.println("# -Inserisci il cap del comune del centro vaccinale        #");
                                input_centriVaccinali[4] = sc.nextLine();
                                System.out.println("# -Inserisci la tipologia del centro vaccinale             #");
                                input_centriVaccinali[5] = "Tipologia: "+sc.nextLine();

                                registraCentroVaccinale(input_centriVaccinali);


                                System.out.println("# -Vuoi inserire un altro centro vaccinale? (s/n)          #");
                                input = sc.nextLine();
                            }while(input.equalsIgnoreCase("s"));

                            break;
                        case("2"):
                            String[] input_cittadini = new String[6];
                            int gg,mm,anno;
                            String g,m,a, codice;
                            do{
                                System.out.println("#========================================================#");
                                System.out.println("|  Inserisci tutti i dati richiesti                      |");
                                System.out.println("#========================================================#");
                                do{
                                    System.out.println("# -Inserisci il nome del centro vaccinale                #");
                                    input_cittadini[0] = sc.nextLine();
                                }while(!controlloEsistenzaCentro(input_cittadini[0]));
                                System.out.println("# -Inserisci il nome del vaccinato                       #");
                                input_cittadini[1] = "Nome: "+sc.nextLine();
                                System.out.println("# -Inserisci il cognome del vaccinato                    #");
                                input_cittadini[2] = "Cognome: "+sc.nextLine();
                                do{
                                    System.out.println("# -Inserisci il codice fiscale del vaccinato (16 cifre)  #");
                                    codice = sc.nextLine();
                                }while (codice.length()!=16);
                                input_cittadini[3]="codice fiscale: "+codice;
                                System.out.println("# -Inserisci il nome del vaccino somministrato           #");
                                input_cittadini[4] = "nome vaccino: "+sc.nextLine();
                                System.out.println("# -Data di vaccinazione (GG/MM/AAAA)                     #");
                                do {
                                    System.out.println("#  Inserisci il giorno (GG)                              #");
                                    g = sc.nextLine();
                                    gg= Integer.parseInt(g);
                                }while(gg<1 || gg>31);
                                do {
                                    System.out.println("#  Inserisci il mese (MM)                                #");
                                    m = sc.nextLine();
                                    mm=Integer.parseInt(m);
                                }while(mm<1 || mm>12);
                                do {
                                    System.out.println("#  Inserisci l'anno (AAAA)                               #");
                                    a= sc.nextLine();
                                    anno = Integer.parseInt(a);
                                }while(anno!=2021 && anno!=2022);
                                input_cittadini[5] = "data di vaccinazione: "+gg+"/"+mm+"/"+anno;

                                registraVaccinato(input_cittadini,input_cittadini[0]);

                                System.out.println("# -Vuoi inserire un altro vaccinato? (s/n)               #");
                                input = sc.nextLine();
                            }while(input.equalsIgnoreCase("s"));
                            break;
                    }
                    break;
                case "2":
                    System.out.println("#========================================================================#");
                    System.out.println("| Buongiorno quale operazione vuole eseguire? (Scrivere 1,2,3 o 0)       |");
                    System.out.println("| 1) Cerca e visualizza informazioni su i centri vaccinali disponibili   |");
                    System.out.println("| 2) Registrazione per i vaccinati (*)                                   |");
                    System.out.println("| 3) Login per i vaccinati (inserire eventi avversi) (*)                 |");
                    System.out.println("| 0) Torna indietro                                                      |");
                    System.out.println("| (*) Funzioni riservate a cittadini gia' vaccinati                      |");
                    System.out.println("#========================================================================#");
                    input = sc.nextLine();
                    switch(input){
                        case "0":
                            break;

                        case "1":
                            String nomeCentro;
                            String comuneCentro;
                            String tipoCentro;
                            String mdt="MdT", febbre="Febbre", dolori="Dolori", linfoadenopatia="Linfoadenopatia", tachicardia="Tachicardia", crisiI="CrisiI", altro="Altro";
                            int i, nSegnalazioni;
                            int mediaMdT, mediaFebbre, mediaDolori, mediaLinfoadenopatia, mediaTachicardia, mediaCrisiI, mediaAltro;
                            System.out.println("#========================================================#");
                            System.out.println("|  Ricerca Centro Vaccinale (Scrivere 1 o 2)             |");
                            System.out.println("|  1) Ricerca per nome                                   |");
                            System.out.println("|  2) Ricerca per comune e tipologia                     |");
                            System.out.println("|  0) Torna indietro                                     |");
                            System.out.println("#========================================================#");
                            input = sc.nextLine();
                            switch (input){
                                case "0":
                                    break;

                                case "1":
                                    System.out.println("#===========================================================================#");
                                    System.out.println("|  Inserire il nome (o parte del nome) del Centro Vaccinale desiderato      |");
                                    System.out.println("#===========================================================================#");
                                    System.out.println("Nome: ");
                                    nomeCentro = sc.nextLine();
                                    i=cercaCentroVaccinale_Nome(nomeCentro);
                                    if(i>=1){
                                        System.out.println("#==========================================================#");
                                        System.out.println("|  Selezionare il centro vaccinale desiderato,             |");
                                        System.out.println("|  inserendone il nome esatto:                             |");
                                        System.out.println("#==========================================================#");
                                        nomeCentro=sc.nextLine();
                                        if(controlloEsistenzaCentro(nomeCentro)){
                                            nSegnalazioni=numSegnalazioni(nomeCentro);
                                            if(nSegnalazioni!=0){
                                                mediaMdT=media(nomeCentro,mdt);
                                                mediaFebbre=media(nomeCentro,febbre);
                                                mediaDolori=media(nomeCentro,dolori);
                                                mediaLinfoadenopatia=media(nomeCentro,linfoadenopatia);
                                                mediaTachicardia=media(nomeCentro,tachicardia);
                                                mediaCrisiI=media(nomeCentro,crisiI);
                                                mediaAltro=media(nomeCentro,altro);

                                                visualizzaInfoCentroVaccinale(mediaMdT, mediaFebbre, mediaDolori, mediaLinfoadenopatia, mediaTachicardia, mediaCrisiI, mediaAltro, nSegnalazioni);
                                            }else
                                                System.out.println("Per il centro selezionato non sono ancora stati segnalati eventi avversi");
                                        }else{
                                            System.out.println("Il centro vaccinale inserito non esiste");
                                        }
                                    }

                                    break;
                                case "2":
                                    System.out.println("#========================================================#");
                                    System.out.println("|  Inserire comune e tipologia del Centro Vaccinale     |");
                                    System.out.println("#========================================================#");
                                    System.out.println("Comune: ");
                                    comuneCentro = sc.nextLine();
                                    System.out.println("Tipologia (ospedale-hub-aziendale): ");
                                    tipoCentro = sc.nextLine();
                                    i=cercaCentroVaccinale(comuneCentro, tipoCentro);
                                    if(i>=1){
                                        System.out.println("#==========================================================#");
                                        System.out.println("|  Selezionare il centro vaccinale desiderato,             |");
                                        System.out.println("|  inserendone il nome esatto:                             |");
                                        System.out.println("#==========================================================#");
                                        nomeCentro=sc.nextLine();
                                        if(controlloEsistenzaCentro(nomeCentro)){
                                            nSegnalazioni=numSegnalazioni(nomeCentro);
                                            if(nSegnalazioni!=0){
                                                mediaMdT=media(nomeCentro,mdt);
                                                mediaFebbre=media(nomeCentro,febbre);
                                                mediaDolori=media(nomeCentro,dolori);
                                                mediaLinfoadenopatia=media(nomeCentro,linfoadenopatia);
                                                mediaTachicardia=media(nomeCentro,tachicardia);
                                                mediaCrisiI=media(nomeCentro,crisiI);
                                                mediaAltro=media(nomeCentro,altro);

                                                visualizzaInfoCentroVaccinale(mediaMdT, mediaFebbre, mediaDolori, mediaLinfoadenopatia, mediaTachicardia, mediaCrisiI, mediaAltro, nSegnalazioni);
                                            }else
                                                System.out.println("Per il centro selezionato non sono ancora stati segnalati eventi avversi");
                                        }else{
                                            System.out.println("Il centro vaccinale inserito non esiste");
                                        }

                                    }
                                    break;
                                default:
                                    System.out.println("Errore, campo inesistente riprova");
                                    break;
                            }
                            break;
                        case "2":
                            String[] input_registrato = new String[6];
                            String nome_centro;
                            boolean id, nome, cognome, cf, password, centrob;
                            System.out.println("#========================================================#");
                            System.out.println("|  Inserisci tutti i dati richiesti                      |");
                            System.out.println("#========================================================#");
                            System.out.println("# -Inserisci il tuo id fornito durante la vaccinazione   #");
                            input_registrato[5] = sc.nextLine();
                            id = controllo_idRegistrazione(input_registrato[5]);
                            if(!id){
                                do {
                                    System.out.println("# -Inserisci il nome del centro dove hai effettuato la vaccinazione#");
                                    nome_centro=sc.nextLine();
                                    centrob = controllo_id(nome_centro, input_registrato[5]);
                                    if(!centrob){
                                        System.out.println("Errore, il centro vaccinale inserito non è quello dove si e' effettuata la vaccinazione");
                                        System.out.println();
                                    }
                                }while(!centrob);
                                do {
                                    System.out.println("# -Inserisci il tuo nome                                 #");
                                    input_registrato[0] = "Nome: "+sc.nextLine();
                                    nome = controllo_datiRegistrazione(input_registrato[5], input_registrato[0], nome_centro);
                                    if(!nome){
                                        System.out.println("Errore, il nome inserito non corrisponde all'id");
                                        System.out.println();
                                    }
                                }while (!nome);
                                do {
                                    System.out.println("# -Inserisci il tuo cognome                              #");
                                    input_registrato[1] = "Cognome: "+sc.nextLine();
                                    cognome=controllo_datiRegistrazione(input_registrato[5], input_registrato[1], nome_centro);
                                    if(!cognome){
                                        System.out.println("Errore, il cognome inserito non corrisponde all'id");
                                        System.out.println();
                                    }
                                }while (!cognome);
                                System.out.println("# -Inserisci la tua email                                #");
                                input_registrato[2] = "mail: "+sc.nextLine();
                                do {
                                    System.out.println("# -Inserisci il nome utente (codice fiscale)             #");
                                    input_registrato[3] = sc.nextLine();
                                    cf = controllo_datiRegistrazione(input_registrato[5], input_registrato[3], nome_centro);
                                    if(!cf){
                                        System.out.println("Errore, il codice fiscale inserito non corrisponde all'id");

                                    }
                                }while (!cf);
                                do {
                                    System.out.println("# -Inserisci la password (Minimo 8 caratteri con 2 maiuscole e 2 numeri) #");
                                    input_registrato[4] = sc.nextLine();
                                    password = is_Valid_Password(input_registrato[4]);
                                    if(!password){
                                        System.out.println("Errore, la password non ha i requisiti minimi per essere accettata");
                                        System.out.println();
                                    } else  input_registrato[4] = "password: "+input_registrato[4];
                                }while (!password);
                                input_registrato[5] = "id: "+ input_registrato[5];
                                input_registrato[3] = "nome utente: "+input_registrato[3];
                                cittadini = new Cittadini(input_registrato);
                                cittadini.registraCittadino();
                                System.out.println("Registrazione avvenuta con successo");
                            }else
                                System.out.println("Impossibile continuare con la registrazione perche' già stata fatta in precedenza");

                            break;

                        case "3":
                            String[] input_login = new String[2];
                            boolean flag2;
                            do{
                                System.out.println("#=============================================================================#");
                                System.out.println("|  Inserisci nome utente e password per accedere (scrivere 'esci' per uscire) |");
                                System.out.println("#=============================================================================#");
                                System.out.println("# -Inserisci il nome utente (codice fiscale da 16 caratteri)     #");
                                input_login[0] = sc.nextLine();
                                if(input_login[0].equals("esci"))
                                        break;
                                System.out.println("# -inserisci la password                  #");
                                input_login[1] = sc.nextLine();
                                flag2 = controllo_Login(input_login[0], input_login[1]);
                            }while(!flag2);
                            if(input_login[0].equals("esci"))
                                break;
                            System.out.println("#========================================================#");
                            System.out.println("|  Si vogliono segnalare eventi avversi?  (s/n)          |");
                            System.out.println("#========================================================#");
                            input = sc.nextLine();
                            if(input.equalsIgnoreCase("S")){
                                String centro;
                                boolean flag3;
                                do {
                                    System.out.println("#========================================================#");
                                    System.out.println("|  Inserire il proprio centro vaccinale                  |");
                                    System.out.println("#========================================================#");
                                    centro = sc.nextLine();
                                    flag3 = controllo_centro(centro, input_login[0]);
                                }while(!flag3);
                                if(!controlloEventiAvversi(centro, input_login[0])){
                                    String[] input_eventi = new String[14];
                                    String value;
                                    String note;
                                    String mdt1="MdT", febbre1="Febbre", dolori1="Dolori", linfoadenopatia1="Linfoadenopatia", tachicardia1="Tachicardia", crisiI1="CrisiI", altro1="Altro";
                                    System.out.println("#=====================================================================================#");
                                    System.out.println("|  Inserisci per ogni evento un valore tra 1 e 5 (1 = minima severita', 5 = massima)  |");
                                    System.out.println("#=====================================================================================#");
                                    do{
                                        System.out.println("# -mal di testa:                    #");
                                        value = sc.nextLine();
                                    }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                    input_eventi[0]="mal di testa: "+value;
                                    invio_valore(centro,mdt1,value);
                                    input_eventi[1] = note();

                                    do{
                                        System.out.println("# -febbre:                          #");
                                        value = sc.nextLine();
                                    }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                    input_eventi[2]="febbre: "+value;
                                    invio_valore(centro,febbre1,value);
                                    input_eventi[3]=note();

                                    do{
                                        System.out.println("# -dolori muscolari e articolari:   #");
                                        value= sc.nextLine();
                                    }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                    input_eventi[4]="dolori muscolari e articolari: "+value;
                                    invio_valore(centro,dolori1,value);
                                    input_eventi[5]=note();

                                    do{
                                        System.out.println("# -linfoadenopatia:                 #");
                                        value = sc.nextLine();
                                    }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                    input_eventi[6]="linfoadenopatia: "+value;
                                    invio_valore(centro,linfoadenopatia1,value);
                                    input_eventi[7]=note();

                                    do{
                                        System.out.println("# -tachicardia:                     #");
                                        value = sc.nextLine();
                                    }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                    input_eventi[8]="tachicardia: "+value;
                                    invio_valore(centro,tachicardia1,value);
                                    input_eventi[9]=note();

                                    do{
                                        System.out.println("# -crisi ipertensiva:               #");
                                        value = sc.nextLine();
                                    }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                    input_eventi[10]="crisi ipertensiva: "+value;
                                    invio_valore(centro,crisiI1,value);
                                    input_eventi[11]=note();

                                    System.out.println("Si vogliono aggiungere altri eventi? (s/n)");
                                    input = sc.nextLine();
                                    if(input.equalsIgnoreCase("s")){
                                        do{
                                            System.out.println("# -altro: (inserire prima l'intensita' e nelle note descrivere l'evento) #");
                                            value = sc.nextLine();
                                        }while(!(value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")|| value.equals("5")));
                                        input_eventi[12]="altro: "+value;
                                        invio_valore(centro,altro1,value);
                                        input_eventi[13]=note();
                                    }
                                    else{
                                        input_eventi[12] = "Nessun altro evento avverso inserito";
                                        value = "0";
                                        invio_valore(centro, altro1,value);
                                        input_eventi[13]="/";
                                    }

                                    System.out.println("attesa... invio dati...");

                                    String newString = trovaLineaCittadino(centro, input_login[0],input_eventi);

                                    inserisciEventiAvversi(newString, centro);
                                    System.out.println("Fine, dati inviati con successo");
                                }else{
                                    System.out.println("L'utente ha gia' inserito eventi avversi");
                                }


                            }
                            else if(input.equalsIgnoreCase("n")){
                                break;
                            }
                            else
                                System.out.print("Errore, inserimento errato");
                            break;

                    }
                    break;
                    default:
                        System.out.println("Errore, campo inesistente riprova");
                        break;
            }
        }while(!(input.equalsIgnoreCase("esci")));
    }


    /**
     * Metodo che permette di inserire all'interno del file "centrivaccinali.txt" i dati di un nuovo centro vaccinale inseriti da un operatore
     * @param input Array di String contenente le informazioni su un centro vaccinale
     */
    public static void registraCentroVaccinale(String[] input){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path+"\\data\\centrivaccinali.txt",true));
            for (int i=0;i <input.length;i++){
                bw.write(input[i]+" |");
                bw.flush();
            }
            bw.newLine();
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Metodo statico che permette di inserire all'interno del file "vaccinati_nomeCentro.txt" i dati di un cittadino inseriti da un operatore
     * @param input Array di String contenente le informazioni su un cittadino
     * @param nomeCentro Nome del centro vaccinale in cui si sta inserendo un nuovo cittadino
     */
    public static void registraVaccinato(String[] input,String nomeCentro){
        boolean flag;
        String id;
        File file_output = new File(path+"\\data\\vaccinati_"+nomeCentro+".txt");
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file_output,true));//scrivere sul file
            for (int i=1;i <input.length;i++){
                bw.write(input[i]+ " |");
                bw.flush();
            }
            do{
                    Random r = new Random();
                    int n = r.nextInt(65536);//2^16
                    id = Integer.toString(n);
                    flag = controllo_creazioneId(id);
            }while(flag);
            bw.write("id vaccinazione: "+id+ " |");
            BufferedWriter writer = new BufferedWriter(new FileWriter(path+"\\data\\idCittadini.txt",true));
            writer.write(id);
            writer.newLine();
            writer.flush();
            writer.close();
            bw.newLine();
            bw.close();
        }catch(IOException e){
        e.printStackTrace();
        }
    }

    /**
     * Metodo statico che permette di verificare se un codice id è già associato ad un altro cittadino
     * @param id_input Id numerico a 16 bit
     * @return True se l'id passato come parametro è già associato ad un altro cittadino, false altrimenti
     */
    public static boolean controllo_creazioneId(String id_input){
        boolean flag=false;
        try {
            File id_vaccinati = new File(path+"\\data\\idCittadini.txt");
            if(id_vaccinati.createNewFile()) //se il file "idCittadini non esiste ancora viene creato
                System.out.println("Creato file idCittadini.txt");
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\idCittadini.txt"));//leggere il file
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains(id_input)) {
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = true;
        }
        return flag;
    }

    /**
     * Metodo statico che cerca nel file "vaccinati_nomeCentro" la linea contenente i dati di un cittadino
     * @param nomeCentro Nome di un centro vaccinale
     * @param nomeUtente Nome utente di un cittadino
     * @param input_eventi Array di String contenente i valori agli eventi avversi inseriti dal cittadino
     * @return Una stringa contenente tutti i dati del cittadino che erano già presenti nel file "vaccinati_nomeCentro", a queste informazioni aggiunge gli eventi avversi inseriti dall'utente
     */
    public static String trovaLineaCittadino(String nomeCentro, String nomeUtente, String[] input_eventi){
        String returnLine = "";

        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\vaccinati_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("codice fiscale: "+nomeUtente)){
                    line+=" Eventi avversi |";
                    for(int c = 0;c<input_eventi.length;c++){
                        line+= input_eventi[c] + " |";
                    }
                }
                returnLine +=line + "\n";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return returnLine;
    }

    /**
     * Metodo statico che modifica il file "vaccinati_nomeCentro" inserendo una linea contenente i dati di un cittadino e i suoi eventi avversi
     * @param newString Stringa da inserire nel file
     * @param nomeCentro Nome di un centro vaccinale
     */
    public static void inserisciEventiAvversi(String newString, String nomeCentro){
        File fileToBeModified = new File(path+"\\data\\vaccinati_"+nomeCentro+".txt");

        FileWriter writer = null;
        try{

            writer = new FileWriter(fileToBeModified);
            writer.write(newString);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Metodo statico che cerca tutti i centri vaccinali nel cui nome sia presente la string passata in input
     * @param nomeCentro Nome o parte di esso, di un centro vaccinale
     * @return Il numero di centri vaccinali il cui nome contiene @param nomeCentro
     */
    public static int cercaCentroVaccinale_Nome(String nomeCentro){
        int i=0;
        boolean flag=false;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\centrivaccinali.txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("Nome: "+nomeCentro)){
                    System.out.println((++i)+"- "+line);
                    flag = true;
                }
            }
            if(!flag)
                System.out.println("Non esistono centri vaccinali con il nome cercato");
        }catch (IOException e){
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Metodo statico che cerca tutti i centri vaccinali all'interno di un comune desiderato il cui tipo sia quello passato come parametro
     * @param comuneCentro Nome (o parte di esso) di un comune
     * @param tipoCentro Tipologia di centro vaccinale
     * @return Il numero di centri vaccinali che soddisfano i parametri
     */
    public static int cercaCentroVaccinale(String comuneCentro, String tipoCentro){
        int i=0;
        boolean flag=false;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\centrivaccinali.txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("Comune: "+comuneCentro)&&line.contains("Tipologia: "+tipoCentro)){
                    System.out.println((++i)+"- "+line);
                    flag = true;
                }
            }
            if(!flag)
                System.out.println("Non esistono centri vaccinali che soddisfano le caratteristiche cercate");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Metodo statico che si occupa di stampare una tabella riassuntiva di eventi avversi
     * @param mediaMdT Media intensità mal di testa
     * @param mediaFebbre Media intensità febbre
     * @param mediaDolori Media intensità dolori articolari e muscolari
     * @param mediaLinfoadenopatia Media intensità linfoadenopatia
     * @param mediaTachicardia Media intensità tachicardia
     * @param mediaCrisiI Media intensità crisi ipertensive
     * @param mediaAltro Media intensità di altri eventi avversi
     * @param nSegnalazioni Numero di segnalazioni su cui si basano le media
     */
    public static void visualizzaInfoCentroVaccinale(int mediaMdT, int mediaFebbre, int mediaDolori, int mediaLinfoadenopatia, int mediaTachicardia, int mediaCrisiI, int mediaAltro, int nSegnalazioni) {
        System.out.println();
        System.out.println("#================================================================#");
        System.out.println("|  Severita' media degli eventi avversi del centro selezionato:  |");
        System.out.println("|  (1 = severita' minima, 5 = severita' massima)                 |");
        System.out.println("#================================================================#");
        System.out.println("|  mal di testa: "+mediaMdT+"                                               |");
        System.out.println("|  febbre: "+mediaFebbre+"                                                     |");
        System.out.println("|  dolori muscolari e articolatri: "+mediaDolori+"                             |");
        System.out.println("|  linfoadenopatia: "+mediaLinfoadenopatia+"                                            |");
        System.out.println("|  tachicardia: "+mediaTachicardia+"                                                |");
        System.out.println("|  crisi ipertensiva: "+mediaCrisiI+"                                          |");
        System.out.println("|  altri eventi: "+mediaAltro+"                                               |");
        System.out.println("|                                                                |");
        System.out.println("|  Su un totale di: "+nSegnalazioni+" segnalazioni                               |");
        System.out.println("#================================================================#");
        System.out.println();
    }

    /**
     * Metodo statico che controlla se l'id inserito da un utente è corretto
     * @param id_input Id numerico a 16 bit
     * @return True se l'id passato come parametro è presente in una linea del file "cittadiniregistrati.txt"
     */
    public static boolean controllo_idRegistrazione(String id_input){
        boolean flag=false;
        try {
            File id_vaccinati = new File(path+"\\data\\cittadiniregistrati.txt");
            if(id_vaccinati.createNewFile()) //se il file "idCittadini non esiste ancora viene creato
                System.out.println("Creato file cittadiniregistrati.txt");
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\cittadiniregistrati.txt"));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains("id: "+id_input)) {
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = true;
        }
        return flag;
    }

    /**
     * Metodo statico che controlla se il centro vaccinale inserito dall'utente sia uguale a quello presente nel file "vaccinati_nomeCentro.txt"
     * @param nomeCentro Nome di un centro vaccinale
     * @param id Nome utente di un cittadino
     * @return True se nel file "vaccinati_nomeCentro" esiste una linea contenente @param nomeUtente
     */
    public static boolean controllo_id(String nomeCentro, String id){
        boolean flag = false;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\vaccinati_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("id vaccinazione: "+id))
                    flag = true;
            }
        }catch (IOException e){
            System.out.println("Errore, centro vaccinale non esistente");
        }
        return flag;
    }

    /**
     * Metodo statico che controlla le credenziali inserite da un cittadino
     * @param nomeUtente Nome utente del cittadino
     * @param password Password del cittadino
     * @return True se nel file "cittadiniregistrati.txt" esiste una linea contenente @param nomeUtente e @param password
     */
    public static boolean controllo_Login(String nomeUtente, String password){
        boolean flag = false;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\cittadiniregistrati.txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("nome utente: "+nomeUtente))
                    if(line.contains("password: "+password))
                        flag = true;
            }
            if(!flag){
                System.out.println("#========================================================#");
                System.out.println("|  Dati errati, riprovare                                |");
                System.out.println("#========================================================#");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Metodo statico che controlla se il centro vaccinale inserito dall'utente sia giusto
     * @param nomeCentro Nome di un centro vaccinale
     * @param nomeUtente Nome utente di un cittadino
     * @return True se nel file "vaccinati_nomeCentro" esiste una linea contenente @param nomeUtente
     */
    public static boolean controllo_centro(String nomeCentro, String nomeUtente){
        boolean flag = false;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\vaccinati_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("codice fiscale: "+nomeUtente))
                    flag = true;
            }
        }catch (IOException e){
            System.out.println("Errore, centro vaccinale non esistente");
        }
        return flag;
    }

    /**
     * Metodo statico che controlla che un centro vaccinale esista nel file "centrivaccinali.txt"
     * @param nomeCentro Nome di un centro vaccinale
     * @return True se @param nomeCentro esiste nel file "centrivaccinali.txt"
     */
    public static boolean controlloEsistenzaCentro(String nomeCentro){
        boolean flag = false;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\centrivaccinali.txt"));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("Nome: "+nomeCentro))
                    flag=true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Metodo statico che controlla se un cittadino ha già inserito eventi avversi
     * @param nomeCentro Nome di un centro vaccinale
     * @param nomeUtente Nome utente di un cittadino
     * @return True se nella linea contenente @param nomeUtente del file "vaccinati_nomeCentro.txt" sono già presenti i valori degli eventi avveris
     */
    public static boolean controlloEventiAvversi(String nomeCentro, String nomeUtente){
        boolean flag = false;
        try {
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\vaccinati_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains(nomeUtente))
                    if(line.contains("Eventi avversi")){
                        flag = true;
                        break;
                    }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Metodo statico che controlla se un dato inserito corrisponde a quelli già presenti nel file "vaccinati_nomeCentro.txt"
     * @param idUtente Id di un utente
     * @param dato Dato da controllare
     * @param nomeCentro Nome del centro vaccinale
     * @return True se il dato inserito corrisponde a un dato nel file "vaccinati_nomeCentro.txt", false se non c'è corrispondenza
     */
    public static boolean controllo_datiRegistrazione(String idUtente, String dato, String nomeCentro){
        boolean flag = false;
        try {
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\vaccinati_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains(idUtente))
                    if(line.contains(dato))
                        flag = true;
                    else
                        flag = false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Metodo statico che calcola il numero di segnalazioni di eventi avversi effettuate presso un centro
     * @param nomeCentro Nome del centro vaccinale di cui si vuole trovare il numero di segnalazioni
     * @return Numero di segnalazioni
     */
    public static int numSegnalazioni(String nomeCentro){
        int n=0;
        try{
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\vaccinati_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.contains("Eventi avversi"))
                    n++;
            }
        }catch (IOException e){
            //e.printStackTrace();
            System.out.println("Per il centro selezionato non sono ancora stati segnalati eventi avversi");
        }
        return n;
    }

    /**
     * Metodo statico che invia il valore inserito da un utente, relativo a un evento avverso, al file "valoriEvento_nomeCentro.txt" contenente tutti i valori relativi a quel evento avverso per un determinato centro vaccinale
     * @param nomeCentro Nome del centro vaccinale a cui viene mandato il valore
     * @param evento Nome del evento avverso a cui fa riferimento @param value
     * @param value Valore relativo all'intensità del mal di testa
     */
    public static void invio_valore(String nomeCentro, String evento, String value) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path+"\\data\\valori\\valori"+evento+"_"+nomeCentro+".txt",true));
            bw.write(value);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo statico che calcola la media aritmetica su una serie di valori presenti nel file "valoriEvento_nomeCentro.txt"
     * @param nomeCentro Nome di un centro vaccinale
     * @param evento Evento avverso di cui calcola la media
     * @return Media aritmetica
     */
    public static int media(String nomeCentro, String evento){
        int n, media=0, i=0, somma=0;
        try {
            Scanner sc = new Scanner(new FileInputStream(path+"\\data\\valori\\valori"+evento+"_"+nomeCentro+".txt"));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                n= Integer.parseInt(line);
                if(n!=0){
                    somma += n;
                    i++;
                }
            }
            if(i!=0)
                media=somma/(i);
        }catch (IOException e){
            e.printStackTrace();
        }
        return media;
    }

    /**
     * Metodo statico che si occupa dell'inserimento di note riferite ad eventi avversi
     * @return String contente la nota inserita oppure "Note non inserite" se l'utente ha scelto di non inserirne
     */
    public static String note(){
        String input;
        Scanner sc = new Scanner(System.in);
        String note;

        System.out.println("Si vogliono inserire note opzionali? (s/n) ");
        input = sc.nextLine();
        if(input.equalsIgnoreCase("S")){
            do{
                System.out.println("Non superare i 256 caratteri");
                System.out.println("Note: ");
                note = sc.nextLine();
            }while(note.length()>256);
            note = "Note: "+note;
        }
        else{
            note = "Note non inserite";
        }
        return note;
    }

    /**
     * Metodo statico che si occupa di controllare la validità della password
     * @param password String contenente la password
     * @return True se la password rispetta i parametri (almeno 8 caratteri tra cui 2 maiuscole e 2 numeri), False altrimenti
     */
    public static boolean is_Valid_Password(String password) {
        boolean contr = true;
        char ch;
        if (password.length() < 8){
            contr= false;
            return (contr);
        }
        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length() && contr; i++) {

            ch = password.charAt(i);
            if (ch >= 48 && ch <= 56)
                numCount++;
            else if (ch >= 65 && ch <= 90)
                charCount++;
        }
        if (charCount >= 2 && numCount >= 2) {
            contr = true;
        }
        else {
            contr = false;
        }
        return (contr);
    }
}