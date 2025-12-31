
class Angajat implements Functii {
    private String nume;
    private String prenume;
    private String idnp;
    private String functie;
    private double procentComision;

    public Angajat(String nume, String prenume, String idnp, String functie, double procentComision) {
        this.nume = nume;
        this.prenume = prenume;
        this.idnp = idnp;
        this.functie = functie;
        this.procentComision = procentComision;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getIdnp() {
        return idnp;
    }

    public String getFunctie() {
        return functie;
    }

    @Override
    public double calculeazaComision(Contract contract, Banca banca) {
        // Calculul comisionului în funcție de suma totală a creditului
        double sumaTotal = contract.getSumaTotal();
        String valuta = contract.getCredit().getValuta();

        // Convertim suma în MDL
        double sumaMDL = banca.convertToMDL(sumaTotal, valuta);

        // Calculăm comisionul
        return sumaMDL * (procentComision / 100.0);
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", idnp='" + idnp + '\'' +
                ", functie='" + functie + '\'' +
                ", procentComision=" + procentComision +
                '}';
    }
}