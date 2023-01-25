package it.unicam.cs.ids.loyaltyIDS;

import it.unicam.cs.ids.loyaltyIDS.Controller.ControllerProgrammaFedelta;
import it.unicam.cs.ids.loyaltyIDS.Controller.ControllerRegistrazione;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;


public class LoyaltyProjectApplication {

    private static final Scanner sc = new Scanner(System.in);
    private static final ControllerProgrammaFedelta controllerGestore= new ControllerProgrammaFedelta();
    private static final ControllerRegistrazione controllerRegistrazioni= new ControllerRegistrazione();

    public static void main(String[] args) throws SQLException, DateMistake, AbilitationException {
        DBMSController.init();
        System.out.println("Welcome to Loyalty Platform!!/n");
         System.out.println("Seleziona il numero per scegliere l'azione da eseguire: /n");
         System.out.println("1-Effettua il login");
         System.out.println("2-Effettua la registrazione");
         switch (provaScannerInt()){
         case 1->login();
         case 2-> registrazione();
         }
         }
         private static void registrazione() throws SQLException, DateMistake {
        System.out.println("Inserisci il numero per il ruolo con cui ti vuoi registrare: /n");
             System.out.println("1-Se sei un cliente");
             System.out.println("2-Se sei un Titolare Punto Vendita");
             System.out.println("3-Se sei un Commesso Punto Vendita");
             switch (provaScannerInt()){
                 case 1->registrazioneCliente();
                 case 2-> registrazioneTitolare();
                 case 3->registrazioneCommesso();
             }
         }

    private static void registrazioneCommesso() {

    }

    private static void registrazioneTitolare() throws SQLException, DateMistake {
        System.out.println("Inserire il nome");
        String nome = sc.nextLine();
        System.out.println("Inserire il cognome");
        String cognome = sc.nextLine();
        System.out.println("Inserire il indirizzo");
        String indirizzo = sc.nextLine();
        System.out.println("Inserire il email business");
        String email = sc.nextLine();
        System.out.println("Inserire il username: /n");
        String username= sc.nextLine();
        System.out.println("Inserire la password");
        String password = sc.nextLine();
        System.out.println("Inserire il numero di telefono");
        int  telefono = sc.nextInt();
        Date nn= new Date(11112025);
        CartaDiCredito cc= new CartaDiCredito(3333333, nn, "333", "33333");
        TitolarePuntoVendita t= new TitolarePuntoVendita(nome, cognome,indirizzo,email,username,password,telefono, cc);
        controllerRegistrazioni.registrazioneTitolare(t);
        System.out.println("Magnifico!! ti sei registrato alla piattaforma.");
    }

    private static void registrazioneCliente() {

    }

    private static void login() throws SQLException, DateMistake, AbilitationException {
         System.out.println("Seleziona il numero per scegliere il ruolo: /n");
         System.out.println("1-Cliente");
         System.out.println("2-Titolare Punto Vendita");
         System.out.println("3-Commesso Punto Vendita");
         System.out.println("4-gestore Piattaforma");
         System.out.println("5-per uscire dalla piattaforma");
         switch (provaScannerInt()){
         case 1->homeClienti();
         case 2->homeTitolare();
         case 3->homeCommesso();
         case 4->homeGestore();
         case 5->System.out.println("Loyalty platform ti ringrazia e ti augura un buon proseguimento:)");
         }
         }
         private static void homeGestore() throws SQLException {
             System.out.println("Seleziona il numero per scegliere l'azione da eseguire: ");
             System.out.println("1- Aggiungere un programma fedelta");
             System.out.println("2- Elimina programma fedelta");
             switch (provaScannerInt()){
                 case 1->aggiungi();
                 case 2->elimina();
             }
         }
         private static void homeCommesso() {
         }
         private static void homeTitolare() throws DateMistake, SQLException, AbilitationException {
        System.out.println("Seleziona l'opzione da effettuare");
        System.out.println("1-Aderisci alla piattaforma");
        System.out.println("2-Aggiungi programma al tuo punto vendita");
        System.out.println("3-Elimina il tuo programma al tuo punto vendita");
        switch (provaScannerInt()){
            case 1->aderisciPiattaforma();


        }
         }

    private static void aderisciPiattaforma() throws DateMistake, SQLException, AbilitationException {
        System.out.println("Inserire il nome");
        String nome = sc.nextLine();
        System.out.println("Inserire il cognome");
        String cognome = sc.nextLine();
        System.out.println("Inserire il indirizzo");
        String indirizzo = sc.nextLine();
        System.out.println("Inserire il email business");
        String email = sc.nextLine();
        System.out.println("Inserire il username: /n");
        String username= sc.nextLine();
        System.out.println("Inserire la password");
        String password = sc.nextLine();
        System.out.println("Inserire il numero di telefono");
        int  telefono = sc.nextInt();
        Date nn= new Date(11112025);
        CartaDiCredito cc= new CartaDiCredito(3333333, nn, "333", "33333");
        TitolarePuntoVendita t= new TitolarePuntoVendita(nome, cognome,indirizzo,email,username,password,telefono, cc);
        System.out.println("Programmi della piattaforma inseriti dal gestore : ");
        controllerGestore.visualizzaProgrammiPunti();
        controllerGestore.visualizzaProgrammiLivelli();
        for (ProgrammaFedelta pf : controllerGestore.getListaProgrammi()){
            System.out.println(pf.toString());
        }
        System.out.println("Scegli id del programma da aggiungere nel tuo Punto Vendita");
        int idPf = sc.nextInt();
        ProgrammaFedelta programmaFedelta = null;
        for (ProgrammaFedelta pf : controllerGestore.getListaProgrammi()){
            if (pf.getId()== idPf){
                programmaFedelta=pf;
            }
        }
        t.aggiungiProgrammaFedeltaPuntoVendita(programmaFedelta.getId());
        System.out.println("Programma aggiunto !");

    }

    private static void homeClienti() {
         }

    private static void elimina() throws SQLException {
        System.out.println("Inserire il nome");
        String nome = sc.nextLine();
        System.out.println("Inserire id del programma");
        int id = sc.nextInt();
        ProgrammaFedelta programmaFedelta= new ProgrammaFedelta(nome, id);
        controllerGestore.visualizzaProgrammiPunti();
        controllerGestore.visualizzaProgrammiLivelli();
        controllerGestore.deleteById(programmaFedelta.getId());
        System.out.println("IL programma"+nome+" é stato eliminato");
    }

    private static void aggiungi() throws SQLException {
        System.out.println("Inserire il nome");
        String nome = sc.nextLine();
        System.out.println("Inserire la descrizione");
        String descrizione = sc.nextLine();
        System.out.println("Inserire 1- per aggiungere un programma punti /n" +
                "inserire 2- per aggiungere un programma a livelli ");
        int number= sc.nextInt();
        if(number==1){
            ProgrammaFedelta programPunti= new ProgrammaPunti(nome, descrizione);
            controllerGestore.addProgrammaFedelta(programPunti);
        }
        else if(number==2){
            ProgrammaFedelta programLivelli= new ProgrammaLivelli(nome, descrizione);
            controllerGestore.addProgrammaFedelta(programLivelli);
        } else System.out.println("Tipologia di programma non esistente");

        System.out.println("Il programma é stato inserito");
    }

    private static int provaScannerInt(){
        while(true){
            try{
                int intero = sc.nextInt();
                sc.nextLine();
                return intero;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Cio' che hai inserito non e' un valore numerico, ritenta ");
            }
        }
    }



}
