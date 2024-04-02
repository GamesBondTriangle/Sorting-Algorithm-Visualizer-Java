public class Algorithms {
    private SortingVisualizer visualizer;

    public Algorithms(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void startAlgorithm(int[] data) {
        switch (visualizer.selectedAlgorithm) {
            case "bubbleSort":
                bubbleSort(data);
                break;
            case "insertionSort":
                insertionSort(data);
                break;
            case "mergeSort":
                mergeSort(data, 0, data.length - 1);
                break;
            case "heapSort":
                heapSort(data);
                break;
            case "quickSort":
                quickSort(data, 0, data.length - 1);
                break;
            case "shellSort":
                shellSort(data, data.length);
                break;
            case "combSort":
                combSort(data);
                break;
            case "exchangeSort":
                exchangeSort(data);
                break;
        }
    }

    private void bubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void insertionSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
                visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data[j + 1] = key;
        }
    }

    private void mergeSort(int[] data, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(data, l, m);
            mergeSort(data, m + 1, r);
            merge(data, l, m, r);
        }
    }

    private void merge(int[] data, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = data[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = data[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                data[k] = L[i];
                i++;
            } else {
                data[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            data[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            data[k] = R[j];
            j++;
            k++;
        }

        visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void heapSort(int[] data) {
        int n = data.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(data, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;

            heapify(data, i, 0);
        }
    }

    private void heapify(int[] data, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && data[l] > data[largest])
            largest = l;

        if (r < n && data[r] > data[largest])
            largest = r;

        if (largest != i) {
            int swap = data[i];
            data[i] = data[largest];
            data[largest] = swap;

            heapify(data, n, largest);
        }
        visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pi = partition(data, low, high);

            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }

    private int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (data[j] < pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        int temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;
        return i + 1;
    }

    private void shellSort(int[] data, int n) {
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = data[i];
                int j;
                for (j = i; j >= gap && data[j - gap] > temp; j -= gap)
                    data[j] = data[j - gap];

                data[j] = temp;
                visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1)
            return 1;
        return gap;
    }

    private void combSort(int[] data) {
        int gap = data.length;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = getNextGap(gap);
            swapped = false;

            for (int i = 0; i < data.length - gap; i++) {
                if (data[i] > data[i + gap]) {
                    int temp = data[i];
                    data[i] = data[i + gap];
                    data[i + gap] = temp;
                    swapped = true;
                    visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void exchangeSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    visualizer.drawData(visualizer.getGraphics(), data, new String[data.length]);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}