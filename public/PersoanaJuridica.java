 class PersoanaJuridica extends Client {
        private long codFiscal;
        private String denumire;
        private String tipProprietate;
        private String adresaComercială;
        private String numeAdministrator;
        private String persoanaContact;

        public PersoanaJuridica(String nume, long codFiscal, String denumire, String tipProprietate,
                                String adresaComercială, String numeAdministrator,
                                String persoanaContact, String telefon) {
            super(nume, telefon);
            this.codFiscal = codFiscal;
            this.denumire = denumire;
            this.tipProprietate = tipProprietate;
            this.adresaComercială = adresaComercială;
            this.numeAdministrator = numeAdministrator;
            this.persoanaContact = persoanaContact;
        }

        public long getCodFiscal() {
            return codFiscal;
        }

        @Override
        public String getTipClient() {
            return "Persoană juridică";
        }

        public String getDenumire() {
            return denumire;
        }

        public String getAdresaComercială() {
            return adresaComercială;
        }

        public String getNumeAdministrator() {
            return numeAdministrator;
        }
    }