class PersoanaFizica extends Client {
        private long idnp;
        private String prenume;
        private String adresa;

        public PersoanaFizica(String nume, String prenume, long idnp, String adresa, String telefon) {
            super(nume, telefon);
            this.prenume = prenume;
            this.idnp = idnp;
            this.adresa = adresa;
        }

        public long getIdnp() {
            return idnp;
        }

        @Override
        public String getTipClient() {
            return "Persoană fizică";
        }

        @Override
        public String getNume() {
            return nume + " " + prenume;
        }

        public String getAdresa() {
            return adresa;
        }

        @Override
        public String toString() {
            return "PersoanaFizica{" +
                    "prenume='" + prenume + '\'' +
                    ", idnp=" + idnp +
                    ", adresa='" + adresa + '\'' +
                    ", nume='" + nume + '\'' +
                    ", telefon='" + telefon + '\'' +
                    '}';
        }
    }
