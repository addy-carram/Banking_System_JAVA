
    class Credit {
        private int id;
        private String denumire;
        private String tipCredit;
        private String valuta;
        private float procentAnual;
        private int suma;

        public Credit(int id, String denumire, String tipCredit, String valuta, float procentAnual,int suma) {
            this.id = id;
            this.denumire = denumire;
            this.tipCredit = tipCredit;
            this.valuta = valuta;
            this.procentAnual = procentAnual;
            this.suma=suma;
        }

        public int getSuma() {
            return suma;
        }

        public int getId() {
            return id;
        }

        public String getDenumire() {
            return denumire;
        }

        public String getTipCredit() {
            return tipCredit;
        }

        public String getValuta() {
            return valuta;
        }

        public float getProcentAnual() {
            return procentAnual;
        }

        @Override
        public String toString() {
            System.out.println("\nINFORMAȚII CREDIT:\n");
            System.out.println("Tip credit: " + getTipCredit() + "\n");
            System.out.println("Denumire: " + getDenumire() + "\n");
            System.out.println("Suma totală: " +  " " +getValuta() + "\n");
            System.out.println("Procent anual: " + getProcentAnual() + "%\n");
            
            return "";
        }
    }