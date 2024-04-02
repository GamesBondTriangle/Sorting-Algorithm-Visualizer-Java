import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingVisualizer extends JFrame {
    private final int WIDTH = 1380;
    private final int HEIGHT = 800;

    protected String selectedAlgorithm;
    private JPanel canvas;
    private JLabel sliderLabel;
    private JSlider sizeSlider;
    private Algorithms algorithms;

    public SortingVisualizer() {
        initialize();
        createUIFrames();
        setVisible(true);
    }

    private void initialize() {
        setTitle("Sorting Algorithm Visualizer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        algorithms = new Algorithms(this);
    }

    private void createUIFrames() {
        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(WIDTH, 90));
        add(menuPanel, BorderLayout.NORTH);

        // Create menu
        menu(menuPanel);

        // Create canvas
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawData(g, new int[]{}, new String[]{});
            }
        };
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT - 90));
        canvas.setBackground(Color.WHITE);
        add(canvas, BorderLayout.CENTER);
    }

    private void menu(JPanel menuPanel) {
        JLabel algoLabel = new JLabel("Algorithm: ");
        menuPanel.add(algoLabel);

        JComboBox<String> algoMenu = new JComboBox<>(new String[]{"bubbleSort", "insertionSort", "mergeSort", "heapSort",
                "quickSort", "shellSort", "combSort", "exchangeSort"});
        algoMenu.addActionListener(e -> selectedAlgorithm = (String) algoMenu.getSelectedItem());
        menuPanel.add(algoMenu);

        JButton genButton = new JButton("Generate Array");
        genButton.addActionListener(e -> generate());
        menuPanel.add(genButton);

        sizeSlider = new JSlider(5, 500, 5);
        sizeSlider.addChangeListener(e -> sliderLabel.setText(Integer.toString(sizeSlider.getValue())));
        menuPanel.add(sizeSlider);

        sliderLabel = new JLabel(Integer.toString(sizeSlider.getValue()));
        menuPanel.add(sliderLabel);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startAlgorithm());
        menuPanel.add(startButton);
    }

    private void generate() {
        int size = sizeSlider.getValue();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i + 1;
        }
        shuffleArray(data);
        drawData(canvas.getGraphics(), data, new String[data.length]);
    }

    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private void startAlgorithm() {
        int size = sizeSlider.getValue();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i + 1;
        }
        shuffleArray(data);
        algorithms.startAlgorithm(data);
    }

    public void drawData(Graphics g, int[] data, String[] color) {
        g.clearRect(0, 0, WIDTH, HEIGHT - 90);

        int cHeight = HEIGHT - 90;
        int cWidth = WIDTH - 20;

        double xWidth = (double) cWidth / (data.length + 1);
        int offset = 10;
        int spacing = 1;

        double max = Arrays.stream(data).max().orElse(0);
        for (int i = 0; i < data.length; i++) {
            int x0 = (int) (i * xWidth) + offset + spacing;
            int y0 = cHeight - (int) ((data[i] / max) * cHeight);
            int x1 = (int) ((i + 1) * xWidth) + offset;
            int y1 = cHeight;

            g.setColor(color[i] != null && color[i].equals("white") ? Color.WHITE : Color.RED);
            g.fillRect(x0, y0, x1 - x0, y1 - y0);
        }
    }
}
