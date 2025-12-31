
    // Clasa pentru filialele băncii
    class Filiala {
        private String numeFiliala;
        private String director;
        private String adresa;
        private String telefon;
        private int codSucursala;
        private List<Contract> contracte;

        public Filiala(String numeFiliala, String director, String adresa, String telefon, int codSucursala) {
            this.numeFiliala = numeFiliala;
            this.director = director;
            this.adresa = adresa;
            this.telefon = telefon;
            this.codSucursala = codSucursala;
            this.contracte = new ArrayList<>();
        }

        public void adaugaContract(Contract contract) {
            contracte.add(contract);
        }

        public List<Contract> getContracte() {
            return contracte;
        }
        public void adaugaAngajat(Angajat angajat) {
            practica.angajati.add(angajat);
        }
        public List<Angajat> getAngajati() {
            return practica.angajati;
        }


        public String getNumeFiliala() {
            return numeFiliala;
        }

        public String getAdresa() {
            return adresa;
        }

        public String getTelefon() {
            return telefon;
        }

        public int getCodSucursala() {
            return codSucursala;
        }

        @Override
        public String toString() {
            return "Filiala{" +
                    "numeFiliala='" + numeFiliala + '\'' +
                    ", director='" + director + '\'' +
                    ", adresa='" + adresa + '\'' +
                    ", telefon='" + telefon + '\'' +
                    ", codSucursala=" + codSucursala +
                    ", contracte=" + contracte +
                    '}';
        }
    }