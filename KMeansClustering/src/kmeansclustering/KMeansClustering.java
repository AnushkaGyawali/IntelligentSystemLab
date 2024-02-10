/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kmeansclustering;
import java.util.Random;

public class KMeansClustering {

    int x[], y[]; // data points
    int num; // number of data points (supplied by the user)
    int k; // number of clusters (supplied by the user)
    double meanX[], meanY[]; // cluster centres
    double oldX[], oldY[]; // backup old cluster centres
    int cAssign[]; // cluster assignment
    int iterations;
    // Constructor to initialize k, num, and arrays
    public KMeansClustering(int k, int num) {
        this.k = k;
        this.num = num;
        x = new int[num];
        y = new int[num];
        meanX = new double[k];
        meanY = new double[k];
        oldX = new double[k];
        oldY = new double[k];
        cAssign = new int[num];
    }

    void randomMean() {
        //Initialize meanX and meanY with random values between 0 and 1000 for all k centres 
        //Use the nextInt() method in the java.util.Random class
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            meanX[i] = rand.nextInt(1000);
            meanY[i] = rand.nextInt(1000);
        }
    }

    void randomData() {
        //Initialize ‘num’ data points with random values between 0 and 1000 
        //Use the nextInt() method
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            x[i] = rand.nextInt(1000);
            y[i] = rand.nextInt(1000);
        }

    }

    void assignCluster() {
        // Calculate the distance between the point and the cluster centre 
        // The Euclidean distance between the jth data point and ith cluster centre is: distance = Math.sqrt(Math.pow(x[j]-meanX[i],2) + Math.pow(y[j]-meanY[i],2))

        // Calculate distance for all k clusters and assign j to whichever i has the smallest distance 
        // Assign this i to cAssign
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
    void assignClusterByManhattan() {
    for (int j = 0; j < num; j++) {
        double minDistance = Double.MAX_VALUE;
        int clusterIndex = 0;
        for (int i = 0; i < k; i++) {
            double distance = Math.abs(x[j] - meanX[i]) + Math.abs(y[j] - meanY[i]);
            if (distance < minDistance) {
                minDistance = distance;
                clusterIndex = i;
            }
        }
        cAssign[j] = clusterIndex;
    }
}

    void updateMeans() {
        // Before updating the centres, backup meanX and meanY (copy to oldX and oldY) 
        // Calculate meanX and meanY for each cluster
        for(int i=0;i<k;i++){
        oldX[i]=meanX[i];
        oldY[i]=meanY[i];
        int clusterSize=0;
        double sumX=0,sumY=0;
            for(int j=0;j<num;j++){
                if(cAssign[j]==i){
                clusterSize++;
                sumX+=x[j];
                sumY+=y[j];
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

    int doClustering() {
        randomMean();
        randomData();
        int MAXITER = 20;
        int  iteration = 0;
        do {
            assignCluster();
                        updateMeans();
//            assignClusterByManhattan();
//                        updateMeans();

            iteration++;
        } while (isDifferent() && iteration < MAXITER);
        return iteration;
    }
    void calculateRSS() {
    double rss = 0.0;
    for (int j = 0; j < num; j++) {
        int clusterIndex = cAssign[j];
        double distance = Math.sqrt(Math.pow(x[j] - meanX[clusterIndex], 2) + Math.pow(y[j] - meanY[clusterIndex], 2));
        rss += distance * distance;
    }
    System.out.printf("\n\nResidue Sum of Square is :- %f",rss);
}

    void countPointsInCluster(){
        int count[];
        count=new int[3];
        for(int i=0;i<3;i++){
            count[i]=0;
            for(int j=0;j<num;j++){
                if(cAssign[j]==i){
                count[i]++;
                }
            }
        }
        for(int i=0;i<3;i++){
          System.out.printf("Num of points in  %d  cluster are %d\n",i,count[i]);  
        }
    }
    void generatedData(){
         System.out.println("Generated Data are :- "); 
            for (int i = 0; i < num; i++) {
        
                    System.out.printf("%d,%d",x[i],y[i]);
                    System.out.print("\t\t");
        }
    }
    
   void dataAtEachClusters(){
           for(int i=0;i<3;i++){
           System.out.printf("\n\nDatas at %d clusters are:- ",i);    
            for(int j=0;j<num;j++){
                if(cAssign[j]==i){
                    System.out.printf("|%d,%d|",x[j],y[j]);
                }
            }
            
           }
   }
    void findCentroid(){
            System.out.println("\n\nCetroid of each clusters are :-\n "); 
            for (int i = 0; i <3; i++) {
        
                    System.out.printf("Centroid of %d cluster is :- %f,%f \n",i,meanX[i],meanY[i]);
          
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        KMeansClustering kms=new KMeansClustering(3,100);
        int iteration=kms.doClustering();
        kms.generatedData();
        kms.dataAtEachClusters();
        System.out.printf("\n\nTotal num of iterations required are :- %d \n\n",iteration);
        kms.countPointsInCluster();
        kms.findCentroid();
        
        //TASK B:                  n             
        // to calculate RSS: RSS = ∑ (Yi - F(Xi))^2
        //                        i=1            
        kms.calculateRSS(); 
        
        // Manhattan Distance = | x 1 − x 2 | + | y 1 − y 2 | 
        
    }
    
}
