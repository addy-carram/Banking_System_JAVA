abstract class Client {
        protected String nume;
        protected String telefon;

        public Client(String nume, String telefon) {
            this.nume = nume;
            this.telefon = telefon;
        }

        public abstract String getTipClient();

        public String getNume() {
            return nume;
        }
    }