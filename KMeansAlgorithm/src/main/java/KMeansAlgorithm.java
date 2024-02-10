import java.util.Random;

public class KMeansAlgorithm {
    int x[], y[]; // data points
    int num; // number of data points (supplied by the user)
    int k; // number of clusters (supplied by the user)
    double meanX[], meanY[]; // cluster centres
    double oldX[], oldY[]; // backup old cluster centres
    int cAssign[]; // cluster assignment

    // Constructor to initialize k, num, and arrays
    public KMeansAlgorithm(int k, int num) {
        this.k = Math.min(k, num); // Handle edge case: set k to the minimum of k and num
        this.num = num;
        x = new int[num];
        y = new int[num];
        meanX = new double[this.k];
        meanY = new double[this.k];
        oldX = new double[this.k];
        oldY = new double[this.k];
        cAssign = new int[num];
    }

    void randomMean() {
        Random rand = new Random();
        int maxX = 0, maxY = 0;
        for (int i = 0; i < num; i++) {
            maxX = Math.max(maxX, x[i]);
            maxY = Math.max(maxY, y[i]);
        }
        for (int i = 0; i < k; i++) {
            meanX[i] = rand.nextInt(maxX + 1); // Add 1 to make it inclusive
            meanY[i] = rand.nextInt(maxY + 1);
        }
    }

    void randomData() {
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            x[i] = rand.nextInt(1000);
            y[i] = rand.nextInt(1000);
        }
    }

    void assignCluster() {
        for (int j = 0; j < num; j++) {
            double minDistance = Double.MAX_VALUE;
            int clusterIndex = 0;
            for (int i = 0; i < k; i++) {
                double distance = Math.sqrt(Math.pow(x[j] - meanX[i], 2) + Math.pow(y[j] - meanY[i], 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    clusterIndex = i;
                }
            }
            cAssign[j] = clusterIndex;
        }
    }

    void updateMeans() {
        for (int i = 0; i < k; i++) {
            oldX[i] = meanX[i];
            oldY[i] = meanY[i];

            int clusterSize = 0;
            double sumX = 0;
            double sumY = 0;
            for (int j = 0; j < num; j++) {
                if (cAssign[j] == i) {
                    clusterSize++;
                    sumX += x[j];
                    sumY += y[j];
                }
            }
            if (clusterSize > 0) {
                meanX[i] = sumX / clusterSize;
                meanY[i] = sumY / clusterSize;
            }
        }
    }

    boolean isDifferent() {
        for (int i = 0; i < k; i++) {
            if (meanX[i] != oldX[i] || meanY[i] != oldY[i]) {
                return true;
            }
        }
        return false;
    }

    void doClustering() {
        randomMean();
        randomData();
        int MAXITER = 20;
        int iteration = 0;
        do {
            assignCluster();
            updateMeans();
            iteration++;
        } while (isDifferent() && iteration < MAXITER);
    }

    double calculateRSS() {
        double rss = 0.0;
        for (int j = 0; j < num; j++) {
            int clusterIndex = cAssign[j];
            double distance = Math.sqrt(Math.pow(x[j] - meanX[clusterIndex], 2) + Math.pow(y[j] - meanY[clusterIndex], 2));
            rss += distance * distance;
        }
        return rss;
    }

    // Main method for testing
    public static void main(String[] args) {
        int k = 3;
        int num = 100;
        KMeansAlgorithm kMeansObj = new KMeansAlgorithm(k, num);
        kMeansObj.doClustering();
        double rss = kMeansObj.calculateRSS();
        System.out.println("RSS: " + rss);
    }
}
