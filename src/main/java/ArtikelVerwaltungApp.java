import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ArtikelVerwaltungApp {

    private final JFrame frame;
    private final JPanel panel;
    private final JLabel gesamtpreisLabel;
    private final Map<Artikel, JComboBox<Integer>> artikelZuComboBoxMap = new HashMap<>();

    public ArtikelVerwaltungApp() {
        frame = new JFrame("Artikel Verwaltung");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Verbessertes Layout

        addArtikelUI(new Artikel("Artikel 1", 10.0));
        addArtikelUI(new Artikel("Artikel 2", 20.0));

        gesamtpreisLabel = new JLabel("Gesamtpreis: 0.0 €");
        panel.add(gesamtpreisLabel);

        frame.add(new JScrollPane(panel)); // Ermöglicht das Scrollen bei vielen Artikeln
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300)); // Setzt eine bevorzugte Größe
        frame.pack();
        frame.setLocationRelativeTo(null); // Zentriert das Fenster
        frame.setVisible(true);
    }

    private void addArtikelUI(Artikel artikel) {
        JPanel artikelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(artikel.getName() + ": ");
        JComboBox<Integer> comboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5});
        JLabel preisLabel = new JLabel("0.0 €");

        comboBox.addActionListener(e -> {
            Integer menge = (Integer) comboBox.getSelectedItem();
            if (menge != null) {
                double preis = menge * artikel.getPreisProEinheit();
                preisLabel.setText(String.format("%.2f €", preis));
                updateGesamtpreis();
            }
        });

        artikelPanel.add(label);
        artikelPanel.add(comboBox);
        artikelPanel.add(preisLabel);
        panel.add(artikelPanel);

        artikelZuComboBoxMap.put(artikel, comboBox);
    }

    private void updateGesamtpreis() {
        double gesamtpreis = artikelZuComboBoxMap.entrySet().stream()
                .filter(e -> e.getValue().getSelectedItem() != null)
                .mapToDouble(e -> e.getKey().getPreisProEinheit() * (Integer) e.getValue().getSelectedItem())
                .sum();

        gesamtpreisLabel.setText("Gesamtpreis: " + String.format("%.2f €", gesamtpreis));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArtikelVerwaltungApp::new);
    }
}