class Banca {
        private static String nume;
        private String tipul;
        private int dataFondarii;
        private int codFiscal;
        private String licenta;
        private List<Filiala> filiale;

        public Banca(String nume, String tipul, int dataFondarii, int codFiscal, String licenta) {
            this.nume = nume;
            this.tipul = tipul;
            this.dataFondarii = dataFondarii;
            this.codFiscal = codFiscal;
            this.licenta = licenta;
            this.filiale = new ArrayList<>();
        }

        public void adaugaFiliala(Filiala filiala) {
            filiale.add(filiala);
        }

        public List<Filiala> getFiliale() {
            return filiale;
        }

        public static String getNume() {
            return nume;
        }

    public double calculVenitTotal() {
        double venitTotal = 0.0;

        for (Filiala filiala : filiale) {
            for (Contract contract : filiala.getContracte()) {
                venitTotal += calculVenitDinContract(contract);
            }
        }

        return venitTotal;
    }


    public double convertToMDL(double suma, String valuta) {
        // setarea nr predefinit
        double rataMDL = 1.0;
        double rataEUR = 19.85;
        double rataUSD = 18.32;
        double rataRON = 3.96;

        // convertire
        if (valuta.equals("MDL")) {
            return suma * rataMDL;
        } else if (valuta.equals("EUR")) {
            return suma * rataEUR;
        } else if (valuta.equals("USD")) {
            return suma * rataUSD;
        } else if (valuta.equals("RON")) {
            return suma * rataRON;
        } else {
            // in caz contrar valuta nerecunoscuta
            System.out.println("Valută nerecunoscută. Se folosește rata MDL (1.0).");
            return suma * rataMDL;
        }
    }
    // Calculul venitului din dobânzi pentru un contract
    public double calculVenitDinContract(Contract contract) {
        Credit credit = contract.getCredit();
        double sumaTotal = contract.getSumaTotal();
        double procentAnual = credit.getProcentAnual();

        // Calculul perioadei în ani
        Period perioada = Period.between(contract.getDataSemnarii(), contract.getTermenRambursare());
        int ani = perioada.getYears();
        int luni = perioada.getMonths();
        double perioadaAni = ani + (luni / 12.0);

        // Calculul dobânzii simple
        double dobanda = sumaTotal * (procentAnual / 100.0) * perioadaAni;

        // Conversia în MDL
        return convertToMDL(dobanda, credit.getValuta());
    }

    // Generarea unui raport de venituri pe filiale
    public void genereazaRaportVenituri(String numeFisier) {
        try (FileWriter writer = new FileWriter(numeFisier)) {
            writer.write("RAPORT DE VENITURI - " + nume + "\n");
            writer.write("Data generării: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n\n");

            double venitTotal = 0.0;

            for (Filiala filiala : filiale) {
                double venitFiliala = 0.0;

                writer.write("Filiala: " + filiala.getNumeFiliala() + "\n");
                writer.write("Adresa: " + filiala.getAdresa() + "\n");
                writer.write("Telefon: " + filiala.getTelefon() + "\n");
                writer.write("Contracte active: " + filiala.getContracte().size() + "\n\n");

                writer.write("DETALII CONTRACTE:\n");

                for (Contract contract : filiala.getContracte()) {
                    double venitContract = calculVenitDinContract(contract);
                    venitFiliala += venitContract;

                    writer.write("Contract nr. " + contract.getNumar() + "\n");
                    writer.write("  Client: " + contract.getClient().getNume() + "\n");
                    writer.write("  Tip credit: " + contract.getCredit().getTipCredit() + "\n");
                    writer.write("  Suma: " + contract.getSumaTotal() + " " + contract.getCredit().getValuta() + "\n");
                    writer.write("  Venit estimat: " + String.format("%.2f", venitContract) + " MDL\n\n");
                }

                writer.write("VENIT TOTAL FILIALĂ: " + String.format("%.2f", venitFiliala) + " MDL\n\n");
                writer.write("--------------------------------------------\n\n");

                venitTotal += venitFiliala;
            }

            writer.write("VENIT TOTAL BANCĂ: " + String.format("%.2f", venitTotal) + " MDL\n");
            System.out.println("Raportul de venit a bancii a fost creat cu succes în fișierul: " + numeFisier);
        } catch (IOException e) {
            System.err.println("Eroare la generarea raportului de venituri: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Banca{" +
                "tipul='" + tipul + '\'' +
                ", dataFondarii=" + dataFondarii +
                ", codFiscal=" + codFiscal +
                ", licenta='" + licenta + '\'' +
                ", filiale=" + filiale +
                '}';
    }
}