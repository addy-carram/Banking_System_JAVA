package Practica;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Functii {
    double calculeazaComision(Contract contract, Banca banca);
}



public class practica {
    static ArrayList<Credit> credits=new ArrayList<>();
    static ArrayList<Client> clients=new ArrayList<>();
    static ArrayList<Contract> contracts=new ArrayList<>();
    static ArrayList<practica> practica=new ArrayList<>();
    static ArrayList<Angajat> angajati=new ArrayList<>();

    private static Banca banca;
    private static Scanner scanner = new Scanner(System.in);
    private static int numarContract = 1;
    private static int numarCredit = 1;

    public static final String RESET = "\033[0m";  // Reset la culoarea implicită
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    // Culori bright/luminoase
    public static final String BRIGHT_BLACK = "\033[0;90m";
    public static final String BRIGHT_RED = "\033[0;91m";
    public static final String BRIGHT_GREEN = "\033[0;92m";
    public static final String BRIGHT_YELLOW = "\033[0;93m";
    public static final String BRIGHT_BLUE = "\033[0;94m";
    public static final String BRIGHT_PURPLE = "\033[0;95m";
    public static final String BRIGHT_CYAN = "\033[0;96m";
    public static final String BRIGHT_WHITE = "\033[0;97m";

    // Background colors
    public static final String BLACK_BG = "\033[40m";
    public static final String RED_BG = "\033[41m";
    public static final String GREEN_BG = "\033[42m";
    public static final String YELLOW_BG = "\033[43m";
    public static final String BLUE_BG = "\033[44m";
    public static final String PURPLE_BG = "\033[45m";
    public static final String CYAN_BG = "\033[46m";
    public static final String WHITE_BG = "\033[47m";

    // Stiluri text
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String ITALIC = "\033[3m";
    public static void main(String[] args){

Scanner scanner=new Scanner(System.in);
initializareBanca();
meniu();

    }

    private static void meniu() {
        int optiune;
        do {
            System.out.println(BLUE_BG+"\n========== SISTEM DE MANAGEMENT AL CREDITELOR BANCARE ==========");
            System.out.println(GREEN+"1. Adaugă client nou");
            System.out.println(GREEN+"2. Adaugă credit nou");
            System.out.println(GREEN+"3. Adaugă contract nou");
            System.out.println(YELLOW+"4. Afișează toate creditele disponibile");
            System.out.println(YELLOW+"5. Afișează toți clienții");
            System.out.println(YELLOW+"6. Afișează toate contractele");
            System.out.println(RED+"7. Calculează venitul băncii");
            System.out.println(PURPLE+"8. Generează raport de venituri");
            System.out.println(BLUE+"9. Generează raport de performanță a angajaților");
            System.out.println("0. Ieșire");
            System.out.print(GREEN+"\nAlegeți o opțiune: ");

            Scanner scanner=new Scanner(System.in);
            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumăm new line

            switch (optiune) {
                case 1:
                    adaugaClient();
                    break;
                case 2:
                    adaugaCredit();
                    break;
                case 3:
                    adaugaContract();

                    break;
                case 4:
                    afiseazaCredite();
                    break;
                case 5:
                    afiseazaClienti();
                    break;
                case 6:
                    afiseazaContracte();
                    break;
                case 7:
                    calculeazaVenit();
                    break;
              case 8:
                    banca.genereazaRaportVenituri("raportVenit.txt");
                    break;
                case 9:
                    genereazaRaportAngajati("raportAngajat.txt");
                    break;
                case 0:
                    System.out.println("Programul se închide...");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        } while (optiune != 0);
    }

    public static boolean isStringDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean nrdeCifre(long number) {
        String numberString = String.valueOf(number);
        return numberString.length() == 13;
    }

        private static void adaugaClient() {
        System.out.println("\n========== ADĂUGARE CLIENT NOU ==========");
        System.out.println("Selectați tipul clientului:");
        System.out.println("1. Persoană fizică");
        System.out.println("2. Persoană juridică");
        System.out.print("Alegere: ");

        Scanner scanner=new Scanner(System.in);
        int tipClient = scanner.nextInt();
        scanner.nextLine(); // Consumăm new line

        Client client = null;

        if (tipClient == 1) {
            System.out.println("\n--- Introducere date persoană fizică ---");

            System.out.print("Nume: ");
            String nume = scanner.nextLine();

            if(isStringDigits(nume)) {
                System.out.print("Prenume: ");
                String prenume = scanner.nextLine();
                if(isStringDigits(prenume)) {
                System.out.print("IDNP (13 cifre): ");
                long idnp = scanner.nextLong();
                scanner.nextLine(); // Consumăm new line
                if(nrdeCifre(idnp) && idnp>0){
                System.out.print("Adresă: ");
                String adresa = scanner.nextLine();
                if(!adresa.isEmpty()){
                System.out.print("Telefon 0...: ");
                String telefon = scanner.nextLine();
                if(telefon.length()==9){
                client = new PersoanaFizica(nume, prenume, idnp, adresa, telefon);}
                else System.out.println(RED+"Introduceti nr de telefon incepand cu 0");}
                else System.out.println(RED+"Introduceti corect o adresa");

                }
                else {System.out.println(RED+" IDNP trebuie sa contina 13 cifre");

                }
            } else {System.out.println(RED+"Prenumele contine un nr.Invalid");}
            } else {System.out.println(RED+"Numele contine un nr.Invalid"); }
        } else if (tipClient == 2) {
            System.out.println("\n--- Introducere date persoană juridică ---");

            System.out.print("Denumire: ");
            String denumire = scanner.nextLine();
            if(isStringDigits(denumire)) {
            System.out.print("Cod fiscal: ");
            long codFiscal = scanner.nextLong();
            scanner.nextLine(); // Consumăm new line

                 if(nrdeCifre(codFiscal) && codFiscal>0){
            System.out.print("Tip proprietate: ");
            String tipProprietate = scanner.nextLine();
            if(isStringDigits(tipProprietate)){
            System.out.print("Adresă comercială: ");
            String adresaComercială = scanner.nextLine();

            System.out.print("Nume administrator: ");
            String numeAdministrator = scanner.nextLine();
            if(isStringDigits(numeAdministrator)){
            System.out.print("Persoană de contact: ");
            String persoanaContact = scanner.nextLine();
            if(isStringDigits(persoanaContact)){
            System.out.print("Telefon: ");
            String telefon = scanner.nextLine();
            if(telefon.length()==9){
            client = new PersoanaJuridica(denumire, codFiscal, denumire, tipProprietate,
                    adresaComercială, numeAdministrator, persoanaContact, telefon);}
            else System.out.println(RED+" Telfon trebuie sa aiba 9 cifre");}
            else System.out.println(RED+"Textul include un numar. Invalid");}
            else System.out.println(RED+"Textul include un nr");}
                else System.out.println(RED+"Tip proprietate nu trebuie sa includa nr. Invalid ");}
            else {System.out.println(RED+"Codul fiscal trebuie sa aiba 13 cifre sau e nr negativ");}}
             else {System.out.println(RED+"Denumirea contine un nr.Invalid"); }
        } else {
            System.out.println(RED+"Opțiune invalidă!");
            return;
        }

        if (client != null) {
            clients.add(client);
            System.out.println(BRIGHT_BLUE+"Client adăugat cu succes!");
        }
    }

    private static void initializareBanca() {
        banca = new Banca("Moldova Agroindbank", "Bancă comercială", 1991, 123456789, "BNM-001");

        // Adăugăm câteva filiale
        Filiala filiala1 = new Filiala("MAIB Centru", "Ion Popescu", "str. Ștefan cel Mare 1, Chișinău", "022-123456", 1);
        Filiala filiala2 = new Filiala("MAIB Botanica", "Maria Rusu", "str. Dacia 15, Chișinău", "022-234567", 2);
        Filiala filiala3 = new Filiala("MAIB Bălți", "Vasile Lungu", "str. Independenței 7, Bălți", "023-112233", 3);

        banca.adaugaFiliala(filiala1);
        banca.adaugaFiliala(filiala2);
        banca.adaugaFiliala(filiala3);

        // Adăugăm angajați (maxim 10)
        Angajat angajat1 = new Angajat("Dorin", "Cazacu", "1234567890123", "Manager credite", 0.5);
        Angajat angajat2 = new Angajat("Elena", "Munteanu", "2345678901234", "Specialist credite", 0.3);
        Angajat angajat3 = new Angajat("Andrei", "Rotaru", "3456789012345", "Consultant", 0.2);
        Angajat angajat4 = new Angajat("Cristina", "Codreanu", "4567890123456", "Director adjunct", 0.6);
        Angajat angajat5 = new Angajat("Victor", "Moraru", "5678901234567", "Specialist ipotecare", 0.4);
        Angajat angajat6 = new Angajat("Alina", "Rusu", "6789012345678", "Manager relații cu clienții", 0.35);
        Angajat angajat7 = new Angajat("Mihai", "Duca", "7890123456789", "Analist financiar", 0.4);
        Angajat angajat8 = new Angajat("Natalia", "Lungu", "8901234567890", "Consultant credite", 0.25);
        Angajat angajat9 = new Angajat("Sergiu", "Cojocaru", "9012345678901", "Specialist IMM", 0.45);
        Angajat angajat10 = new Angajat("Diana", "Popescu", "0123456789012", "Manager corporate", 0.55);

        // Distribuim angajații pe filiale
        filiala1.adaugaAngajat(angajat1);
        filiala1.adaugaAngajat(angajat2);
        filiala1.adaugaAngajat(angajat3);
        filiala1.adaugaAngajat(angajat4);
        filiala2.adaugaAngajat(angajat5);
        filiala2.adaugaAngajat(angajat6);
        filiala2.adaugaAngajat(angajat7);
        filiala3.adaugaAngajat(angajat8);
        filiala3.adaugaAngajat(angajat9);
        filiala3.adaugaAngajat(angajat10);

        // Adăugăm credite predefinite (maxim 10)
        Credit credit1 = new Credit(numarCredit++, "Nevoi personale", "Consumer", "MDL", 12.5f, 544000);
        Credit credit2 = new Credit(numarCredit++, "Prima casă", "Ipotecar", "EUR", 7.9f, 334000);
        Credit credit3 = new Credit(numarCredit++, "Business Start", "Afaceri", "USD", 9.5f, 1200000);
        Credit credit4 = new Credit(numarCredit++, "Credit auto", "Consumer", "EUR", 10.2f, 150000);
        Credit credit5 = new Credit(numarCredit++, "Credit imobiliar", "Ipotecar", "MDL", 8.7f, 1500000);
        Credit credit6 = new Credit(numarCredit++, "Dezvoltare afacere", "Afaceri", "EUR", 8.3f, 500000);
        Credit credit7 = new Credit(numarCredit++, "Renovare", "Consumer", "MDL", 13.1f, 100000);
        Credit credit8 = new Credit(numarCredit++, "Educație", "Consumer", "MDL", 9.0f, 80000);
        Credit credit9 = new Credit(numarCredit++, "Agricultură", "Afaceri", "USD", 7.2f, 1000000);
        Credit credit10 = new Credit(numarCredit++, "Investiții imobiliare", "Ipotecar", "EUR", 6.8f, 2000000);

        // Adăugăm în lista globală de credite
        credits.add(credit1);
        credits.add(credit2);
        credits.add(credit3);
        credits.add(credit4);
        credits.add(credit5);
        credits.add(credit6);
        credits.add(credit7);
        credits.add(credit8);
        credits.add(credit9);
        credits.add(credit10);

        // Adăugăm clienți (maxim 10)
        // Persoane fizice
        PersoanaFizica client1 = new PersoanaFizica("Ionescu", "Alexandru", 1234567890123L, "str. Florilor 10, Chișinău", "079123456");
        PersoanaFizica client2 = new PersoanaFizica("Marinescu", "Elena", 2345678901234L, "str. Albișoara 15, Chișinău", "079234567");
        PersoanaFizica client3 = new PersoanaFizica("Popescu", "Ion", 3456789012345L, "str. Mihai Eminescu 7, Bălți", "079345678");
        PersoanaFizica client4 = new PersoanaFizica("Dumitrescu", "Ana", 4567890123456L, "str. Decebal 20, Chișinău", "079456789");
        PersoanaFizica client5 = new PersoanaFizica("Gheorghiu", "Mihai", 5678901234567L, "str. Independenței 12, Orhei", "079567890");

        // Persoane juridice
        PersoanaJuridica client6 = new PersoanaJuridica("ProSoft SRL", 123456, "ProSoft", "SRL", "str. Arborilor 5, Chișinău", "Vasile Rusu", "Maria Lungu", "022123456");
        PersoanaJuridica client7 = new PersoanaJuridica("Agro Farms SRL", 234567, "Agro Farms", "SRL", "str. Industrială 10, Bălți", "Nicolae Moraru", "Petru Rotaru", "022234567");
        PersoanaJuridica client8 = new PersoanaJuridica("EuroConstruct SA", 345678, "EuroConstruct", "SA", "str. Calea Ieșilor 15, Chișinău", "Andrei Munteanu", "Cristina Codreanu", "022345678");
        PersoanaJuridica client9 = new PersoanaJuridica("MoldTrade Import-Export", 456789, "MoldTrade", "SRL", "str. Dacia 25, Chișinău", "Ion Dascăl", "Victor Lungu", "022456789");
        PersoanaJuridica client10 = new PersoanaJuridica("IT Solutions SRL", 567890, "IT Solutions", "SRL", "str. Sciusev 12, Chișinău", "Diana Negru", "Alexandru Carp", "022567890");

        // Adăugăm în lista globală de clienți
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);
        clients.add(client6);
        clients.add(client7);
        clients.add(client8);
        clients.add(client9);
        clients.add(client10);

        // Adăugăm contracte (maxim 10)
        LocalDate azi = LocalDate.now();

        Contract contract1 = new Contract(numarContract++, azi.minusMonths(2), client1, credit1, 50000, azi.plusYears(3), "Dorin Cazacu");
        Contract contract2 = new Contract(numarContract++, azi.minusMonths(3), client2, credit2, 75000, azi.plusYears(10), "Elena Munteanu");
        Contract contract3 = new Contract(numarContract++, azi.minusMonths(5), client6, credit3, 100000, azi.plusYears(5), "Andrei Rotaru");
        Contract contract4 = new Contract(numarContract++, azi.minusMonths(1), client3, credit4, 15000, azi.plusYears(2), "Cristina Codreanu");
        Contract contract5 = new Contract(numarContract++, azi.minusMonths(6), client7, credit6, 200000, azi.plusYears(7), "Victor Moraru");
        Contract contract6 = new Contract(numarContract++, azi.minusMonths(4), client4, credit7, 30000, azi.plusYears(1), "Alina Rusu");
        Contract contract7 = new Contract(numarContract++, azi.minusMonths(7), client8, credit9, 500000, azi.plusYears(5), "Mihai Duca");
        Contract contract8 = new Contract(numarContract++, azi.minusMonths(2), client5, credit8, 25000, azi.plusYears(2), "Natalia Lungu");
        Contract contract9 = new Contract(numarContract++, azi.minusMonths(8), client9, credit10, 750000, azi.plusYears(15), "Sergiu Cojocaru");
        Contract contract10 = new Contract(numarContract++, azi.minusMonths(1), client10, credit5, 300000, azi.plusYears(8), "Diana Popescu");

        // Adăugăm în lista globală de contracte
        contracts.add(contract1);
        contracts.add(contract2);
        contracts.add(contract3);
        contracts.add(contract4);
        contracts.add(contract5);
        contracts.add(contract6);
        contracts.add(contract7);
        contracts.add(contract8);
        contracts.add(contract9);
        contracts.add(contract10);

        // Distribuim contractele pe filiale
        filiala1.adaugaContract(contract1);
        filiala1.adaugaContract(contract2);
        filiala1.adaugaContract(contract3);
        filiala1.adaugaContract(contract4);
        filiala2.adaugaContract(contract5);
        filiala2.adaugaContract(contract6);
        filiala2.adaugaContract(contract7);
        filiala3.adaugaContract(contract8);
        filiala3.adaugaContract(contract9);
        filiala3.adaugaContract(contract10);

        System.out.println("Banca " + Banca.getNume() + " a fost inițializată cu succes.");
        System.out.println("Au fost adăugate " + credits.size() + " credite, " + clients.size() + " clienți, " +
                contracts.size() + " contracte și " + angajati.size() + " angajați.");
    }
    private static void adaugaCredit() {
        System.out.println("\n========== ADĂUGARE CREDIT NOU ==========");

        System.out.print("Denumire credit: ");
        String denumire = scanner.nextLine();
        if(isStringDigits(denumire)){
        System.out.println("Selectați tipul creditului:");
        System.out.println("1. Consumer");
        System.out.println("2. Ipotecar");
        System.out.println("3. Afaceri");
        System.out.println("4. Alt tip");
        System.out.print("Alegere: ");

        int tipAlgere = scanner.nextInt();
        scanner.nextLine(); // Consumăm new line

        String tipCredit;
        switch(tipAlgere) {
            case 1:
                tipCredit = "Consumer";
                break;
            case 2:
                tipCredit = "Ipotecar";
                break;
            case 3:
                tipCredit = "Afaceri";
                break;
            case 4:
                System.out.print("Introduceți tipul de credit: ");
                tipCredit = scanner.nextLine();
                break;
            default:{
                tipCredit = "Nedefinit";
                System.out.println(RED+"Credit nedefinit");}
        }

        System.out.println("Selectați valuta:");
        System.out.println("1. MDL");
        System.out.println("2. EUR");
        System.out.println("3. USD");
        System.out.println("4. RON");
        System.out.print("Alegere: ");

        int valutaAlegere = scanner.nextInt();
        scanner.nextLine(); // Consumăm new line

        String valuta;
        switch(valutaAlegere) {
            case 1:
                valuta = "MDL";
                break;
            case 2:
                valuta = "EUR";
                break;
            case 3:
                valuta = "USD";
                break;
            case 4:
                valuta = "RON";
                break;
            default:
                valuta = "MDL";
                System.out.println(RED+"Defaul MDL");
        }

        System.out.print("Procent anual (%): ");
        float procentAnual = scanner.nextFloat();
        scanner.nextLine(); // Consumăm new line
        if(procentAnual>0){
        System.out.println("Suma dorita ");
        int suma= scanner.nextInt();
        scanner.nextLine();
        if(suma>0){
        Credit credit = new Credit(numarCredit++, denumire, tipCredit, valuta, procentAnual,suma);
        credits.add(credit);

        System.out.println(GREEN+"Credit adăugat cu succes!");
        System.out.println(GREEN+"ID: " + credit.getId());
        System.out.println(GREEN+"Denumire: " + credit.getDenumire());
        System.out.println(GREEN+"Tip: " + credit.getTipCredit());
        System.out.println(GREEN+"Valuta: " + credit.getValuta());
        System.out.println(GREEN+"Procent anual: " + credit.getProcentAnual() + "%");
        System.out.println(GREEN+"Suma: "+suma+" lei");}
        else System.out.println(RED+"Ati introdus un nr negativ");


}
        else System.out.println(RED+"Ati introdus un nr negativ");}
    else System.out.println(RED+"Creditul nu trebuie sa contine cifre");}

    private static void afiseazaContracte() {
        System.out.println("\n========== LISTA CONTRACTELOR ==========");

        if (contracts.isEmpty()) {
            System.out.println("Nu există contracte înregistrate.");
            return;
        }

        for (int i = 0; i < contracts.size(); i++) {
            Contract contract = contracts.get(i);
            System.out.println("\nContract #" + (i + 1) + ":");
            System.out.println("  Număr: " + contract.getNumar());
            System.out.println("  Client: " + contract.getClient().getNume());
            System.out.println("  Credit: " + contract.getCredit().getDenumire() + " (" + contract.getCredit().getTipCredit() + ")");
            System.out.println("  Suma: " + contract.getSumaTotal() + " " + contract.getCredit().getValuta());
            System.out.println("  Data semnării: " + contract.getDataSemnarii().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println(RED+"  Termen de rambursare: " + contract.getTermenRambursare().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println("  Manager principal: " + contract.getManagerPrincipal());
        }
    }

    private static void afiseazaClienti() {
        System.out.println("\n========== LISTA CLIENȚILOR ==========");

        if (clients.isEmpty()) {
            System.out.println("Nu există clienți înregistrați.");
            return;
        }

        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            System.out.println("\nClient #" + (i + 1) + ":");
            System.out.println("  Nume: " + client.getNume());
            System.out.println("  Tip client: " + client.getTipClient());

            if (client instanceof PersoanaFizica) {
                PersoanaFizica pf = (PersoanaFizica) client;
                System.out.println("  IDNP: " + pf.getIdnp());
                System.out.println("  Adresa: " + pf.getAdresa());
            } else if (client instanceof PersoanaJuridica) {
                PersoanaJuridica pj = (PersoanaJuridica) client;
                System.out.println("  Cod fiscal: " + pj.getCodFiscal());
                System.out.println("  Administrator: " + pj.getNumeAdministrator());
                System.out.println("  Adresa: " + pj.getAdresaComercială());
            }
        }
    }
    private static void afiseazaCredite() {
        System.out.println("\n========== LISTA CREDITELOR ==========");

        if (credits.isEmpty()) {
            System.out.println("Nu există credite inregistrate înregistrați.");
            return;
        }

        for (int i = 0; i < credits.size(); i++) {
            Credit credit = credits.get(i);
            System.out.println("\nCredit #" + (i + 1) + ":"+
                    "\n Id credit "+credit.getId()+
                    " Denumire: " + credit.getDenumire()+
                    "  Tip credit: " + credit.getTipCredit()+
                    "\n Valuta "+credit.getValuta()+
                    "\n Procent anual "+credit.getProcentAnual());



        }
    }

    private static void calculeazaVenit() {
        System.out.println("\n========== CALCULUL VENITULUI BĂNCII ==========");

        if (banca == null) {
            // Dacă banca nu este inițializată, o inițializăm
            initializareBanca();
        }

        // Calculăm venitul total al băncii
        double venitTotal = banca.calculVenitTotal();

        System.out.println("Venitul total estimat al băncii din dobânzi: " + String.format("%.2f", venitTotal) + " MDL");

        // Detaliere pe filiale
        System.out.println("\nDetaliere pe filiale:");

        for (Filiala filiala : banca.getFiliale()) {
            double venitFiliala = 0.0;

            for (Contract contract : filiala.getContracte()) {
                double venitContract = banca.calculVenitDinContract(contract);
                venitFiliala += venitContract;
            }

            System.out.println("- " + filiala.getNumeFiliala() + ": " + String.format("%.2f", venitFiliala) + " MDL");
        }

        System.out.println("\nNotă: Venitul este calculat pe baza dobânzilor creditelor actuale convertite în MDL.");
    }

    private static void adaugaContract() {
        System.out.println("\n========== ADĂUGARE CONTRACT NOU ==========");

        // Verificăm dacă există clienți
        if (clients.isEmpty()) {
            System.out.println("Nu există clienți înregistrați. Vă rugăm să adăugați un client mai întâi.");
            return;
        }

        // Verificăm dacă există credite
        if (credits.isEmpty()) {
            System.out.println("Nu există credite disponibile. Vă rugăm să adăugați un credit mai întâi.");
            return;
        }

        // Selectarea clientului
        System.out.println("\n--- Selectați clientul ---");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i).getNume() + " (" + clients.get(i).getTipClient() + ")");
        }
        System.out.print("Alegeți numărul clientului: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // Consumăm new line

        if (clientId < 1 || clientId > clients.size()) {
            System.out.println("Selecție invalidă!");
            return;
        }

        Client clientSelectat = clients.get(clientId - 1);

        // Selectarea creditului
        System.out.println("\n--- Selectați creditul ---");
        for (int i = 0; i < credits.size(); i++) {
            Credit credit = credits.get(i);
            System.out.println((i + 1) + ". " + credit.getDenumire() + " - " +
                    credit.getTipCredit() + " (" + credit.getValuta() + ", " +
                    credit.getProcentAnual() + "%)");
        }
        System.out.print("Alegeți numărul creditului: ");
        int creditId = scanner.nextInt();
        scanner.nextLine(); // Consumăm new line

        if (creditId< 1 || creditId > credits.size()) {
            System.out.println("Selecție invalidă!");
            return;
        }

        Credit creditSelectat = credits.get(creditId - 1);

        // Data semnării (implicit astăzi)
        LocalDate dataSemnarii = LocalDate.now();
        System.out.println("Data semnării (implicit astăzi): " + dataSemnarii.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // Termen de rambursare
        System.out.println("\n--- Selectați termenul de rambursare ---");
        System.out.println("1. 1 an");
        System.out.println("2. 3 ani");
        System.out.println("3. 5 ani");
        System.out.println("4. 10 ani");
        System.out.println("5. 15 ani");
        System.out.println("7. Alt termen");
        System.out.print("Alegere: ");

        int termenAlegere = scanner.nextInt();
        scanner.nextLine(); // Consumăm new line

        LocalDate termenRambursare;

        switch (termenAlegere) {
            case 1:
                termenRambursare = dataSemnarii.plusYears(1);
                break;
            case 2:
                termenRambursare = dataSemnarii.plusYears(3);
                break;
            case 3:
                termenRambursare = dataSemnarii.plusYears(5);
                break;
            case 4:
                termenRambursare = dataSemnarii.plusYears(10);
                break;
            case 5:
                termenRambursare = dataSemnarii.plusYears(15);
                break;
            case 7:
                System.out.print("Introduceți numărul de ani: ");
                int ani = scanner.nextInt();
                scanner.nextLine(); // Consumăm new line
                termenRambursare = dataSemnarii.plusYears(ani);
                break;
            default:
                System.out.println("Opțiune invalidă! Se va folosi termen de 3 ani.");
                termenRambursare = dataSemnarii.plusYears(3);
        }

        // Manager principal
        System.out.print("\nIntroduceți numele managerului principal: ");
        System.out.print("\n1 Dorin Cazacu");
        System.out.print("\n2 Elena Munteanu ");
        System.out.print("\n3 Altul ");
        System.out.print("\n4 Credit online ");
        int alegere= scanner.nextInt();
        String managerPrincipal="";

        switch(alegere){
            case 1:{ managerPrincipal="Dorin Cazacu"; }break;
            case 2:{ managerPrincipal="Elena Munteanu";} break;
            case 3: {String aleg=scanner.nextLine();
                    if(isStringDigits(aleg)){
                        managerPrincipal=aleg;
                    }else System.out.println(RED+"Numele nu trebuie sa aiba cifre");} break;
            case 4: break;
            default:{
                System.out.println(RED+"Optiune invalida");}
        }
        // Crearea contractului
        Contract contract = new Contract(numarContract++, dataSemnarii, clientSelectat, creditSelectat,
                creditSelectat.getSuma()*creditSelectat.getProcentAnual(), termenRambursare,managerPrincipal);

        // Adăugăm contractul în lista de contracte
        contracts.add(contract);

        // Selectăm filiala pentru contract, dacă banca este inițializată
        if (banca != null && !banca.getFiliale().isEmpty()) {
            System.out.println("\n--- Selectați filiala ---");
            List<Filiala> filiale = banca.getFiliale();
            for (int i = 0; i < filiale.size(); i++) {
                System.out.println((i + 1) + ". " + filiale.get(i).getNumeFiliala() + " (" + filiale.get(i).getAdresa() + ")");
            }
            System.out.print("Alegeți numărul filialei: ");
            int filialaId = scanner.nextInt();
            scanner.nextLine(); // Consumăm new line

            if (filialaId >= 1 && filialaId <= filiale.size()) {
                Filiala filialaSelectata = filiale.get(filialaId - 1);
                filialaSelectata.adaugaContract(contract);
                System.out.println("Contractul a fost asociat cu filiala " + filialaSelectata.getNumeFiliala());
            } else {
                System.out.println("Selecție invalidă! Contractul nu a fost asociat cu o filială.");
            }
        } else {
            System.out.println("Atenție: Banca nu este inițializată sau nu are filiale. Inițializați banca pentru a asocia contractul cu o filială.");
        }
        contract.salveazaContract("ToateContractele");

        System.out.println("\nContract adăugat cu succes!");
        System.out.println("Număr contract: " + contract.getNumar());
        System.out.println("Client: " + contract.getClient().getNume());
        System.out.println("Credit: " + contract.getCredit().getDenumire());
        System.out.println("Suma totală: " + contract.getSumaTotal() + " " + contract.getCredit().getValuta());
        System.out.println("Data semnării: " + contract.getDataSemnarii().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        System.out.println("Termen rambursare: " + contract.getTermenRambursare().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        System.out.println("Manager principal: " + contract.getManagerPrincipal());
        System.out.println("Contractul a fost salvat în fișierul: " + " Toate contractele");
    }
    private static void genereazaRaportAngajati(String numeFisier) {
        System.out.println("\n========== GENERARE RAPORT DE PERFORMANȚĂ A ANGAJAȚILOR ==========");

        if (banca == null) {
            // Dacă banca nu este inițializată, o inițializăm
            initializareBanca();
        }

        try (FileWriter writer = new FileWriter(numeFisier)) {
            writer.write("RAPORT DE PERFORMANȚĂ A ANGAJAȚILOR - " + Banca.getNume() + "\n");
            writer.write("Data generării: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n\n");

            // Iterăm prin toate filialele
            for (Filiala filiala : banca.getFiliale()) {
                writer.write("Filiala: " + filiala.getNumeFiliala() + "\n");
                writer.write("Adresa: " + filiala.getAdresa() + "\n");
                writer.write("Telefon: " + filiala.getTelefon() + "\n");
                writer.write("Număr angajați: " + filiala.getAngajati().size() + "\n\n");

                writer.write("DETALII PERFORMANȚĂ ANGAJAȚI:\n");

                // Iterăm prin toți angajații filialei
                for (Angajat angajat : filiala.getAngajati()) {
                    writer.write("Angajat: " + angajat.getNume() + " " + angajat.getPrenume() + "\n");
                    writer.write("  Funcție: " + angajat.getFunctie() + "\n");
                    writer.write("  IDNP: " + angajat.getIdnp() + "\n");

                    double comisionTotal = 0.0;
                    int contracteProcesate = 0;

                    // Calculăm comisionul pentru fiecare contract din filială
                    for (Contract contract : filiala.getContracte()) {
                        // Verificăm dacă managerul principal este angajatul curent
                        // (aici putem adăuga o logică mai complexă în funcție de cerințe)
                        if (contract.getManagerPrincipal().equals(angajat.getNume() + " " + angajat.getPrenume())) {
                            double comision = angajat.calculeazaComision(contract, banca);
                            comisionTotal += comision;
                            contracteProcesate++;
                        }
                    }

                    writer.write("  Contracte procesate: " + contracteProcesate + "\n");
                    writer.write("  Comision total estimat: " + String.format("%.2f", comisionTotal) + " MDL\n\n");
                }

                writer.write("--------------------------------------------\n\n");
            }

            writer.write("NOTĂ: Acest raport prezintă performanța angajaților în funcție de comisioanele estimate din contractele procesate.\n");

            System.out.println("Raportul de performanță a angajaților a fost creat cu succes în fișierul: " + numeFisier);

        } catch (IOException e) {
            System.err.println("Eroare la generarea raportului de performanță a angajaților: " + e.getMessage());
        }
    }

    }