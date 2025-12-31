
    // Clasa pentru contractele de credit
    class Contract {
        private int numar;
        private LocalDate dataSemnarii;
        private Client client;
        private Credit credit;
        private double sumaTotal;
        private LocalDate termenRambursare;
        private String managerPrincipal;

        public Contract(int numar, LocalDate dataSemnarii, Client client, Credit credit,
                        double sumaTotal, LocalDate termenRambursare, String managerPrincipal) {
            this.numar = numar;
            this.dataSemnarii = dataSemnarii;
            this.client = client;
            this.credit = credit;
            this.sumaTotal = sumaTotal;
            this.termenRambursare = termenRambursare;
            this.managerPrincipal = managerPrincipal;
        }

        @Override
        public String toString() {
            return "Contract{" +
                    "numar=" + numar +
                    ", dataSemnarii=" + dataSemnarii +
                    ", client=" + client +
                    ", credit=" + credit +
                    ", sumaTotal=" + sumaTotal +
                    ", termenRambursare=" + termenRambursare +
                    ", managerPrincipal='" + managerPrincipal + '\'' +
                    '}';
        }

        public void salveazaContract(String numeFisier) {
            try (FileWriter writer = new FileWriter(numeFisier)) {
                writer.write("Contract de credit nr. " + numar + "\n");
                writer.write("Data semnării: " + dataSemnarii.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n");
                writer.write("Manager principal: " + managerPrincipal + "\n\n");

                writer.write("INFORMAȚII CLIENT:\n");
                writer.write("Tip client: " + client.getTipClient() + "\n");
                writer.write("Nume: " + client.getNume() + "\n");

                if (client instanceof PersoanaFizica) {
                    PersoanaFizica pf = (PersoanaFizica) client;
                    writer.write("IDNP: " + pf.getIdnp() + "\n");
                    writer.write("Adresa: " + pf.getAdresa() + "\n");
                } else if (client instanceof PersoanaJuridica) {
                    PersoanaJuridica pj = (PersoanaJuridica) client;
                    writer.write("Cod fiscal: " + pj.getCodFiscal() + "\n");
                    writer.write("Denumire: " + pj.getDenumire() + "\n");
                    writer.write("Administrator: " + pj.getNumeAdministrator() + "\n");
                    writer.write("Adresa: " + pj.getAdresaComercială() + "\n");
                }

                writer.write("\nINFORMAȚII CREDIT:\n");
                writer.write("Tip credit: " + credit.getTipCredit() + "\n");
                writer.write("Denumire: " + credit.getDenumire() + "\n");
                writer.write("Suma totală: " + sumaTotal + " " + credit.getValuta() + "\n");
                writer.write("Procent anual: " + credit.getProcentAnual() + "%\n");
                writer.write("Termen de rambursare: " + termenRambursare.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n");

                writer.write("\nSemnăturile părților:\n");
                writer.write("Banca: _________________\n");
                writer.write("Client: _________________\n");

            } catch (IOException e) {
                System.err.println("Eroare la salvarea contractului: " + e.getMessage());
            }
        }

        public int getNumar() {
            return numar;
        }

        public LocalDate getDataSemnarii() {
            return dataSemnarii;
        }

        public Client getClient() {
            return client;
        }

        public Credit getCredit() {
            return credit;
        }

        public double getSumaTotal() {
            return sumaTotal;
        }

        public LocalDate getTermenRambursare() {
            return termenRambursare;
        }
        public String getManagerPrincipal(){
            return managerPrincipal;
        }
    }