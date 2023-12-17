public class Artikel {

    private String name;
    private double preisProEinheit;

    public Artikel(String name, double preisProEinheit) {
        this.name = name;
        this.preisProEinheit = preisProEinheit;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public double getPreisProEinheit() {
        return preisProEinheit;
    }

    public void setPreisProEinheit(double preisProEinheit) {
        this.preisProEinheit = preisProEinheit;
    }
}
