import java.util.Scanner;

class Connection {
    private double value;

    public void setWeight(double value) {
        this.value = value;
    }

    public double getWeight() {
        return value;
    }
}

class Network {
    private int numLayers;
    private int[] numNodes;
    private Connection[][] connections;

    public Network(int numLayers, int[] numNodes) {
        this.numLayers = numLayers;
        this.numNodes = numNodes;
        connections = new Connection[numLayers - 1][];
        for (int i = 0; i < numLayers - 1; i++) {
            connections[i] = new Connection[numNodes[i] * numNodes[i + 1]];
            for (int j = 0; j < numNodes[i] * numNodes[i + 1]; j++) {
                connections[i][j] = new Connection();
            }
        }
    }

    public void setConnectionValue(int layer, int nodeFrom, int nodeTo, double value) {
        int index = nodeFrom * numNodes[layer + 1] + nodeTo;
        connections[layer][index].setWeight(value);
    }

    public double getConnectionValue(int layer, int nodeFrom, int nodeTo) {
        int index = nodeFrom * numNodes[layer + 1] + nodeTo;
        return connections[layer][index].getWeight();
    }
}

public class Hidden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of layers: ");
        int numLayers = scanner.nextInt();

        int[] numNodes = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Number of nodes in layer " + (i + 1) + ": ");
            numNodes[i] = scanner.nextInt();
        }

        Network network = new Network(numLayers, numNodes);

        for (int i = 0; i < numLayers - 1; i++) {
            for (int j = 0; j < numNodes[i]; j++) {
                for (int k = 0; k < numNodes[i + 1]; k++) {
                    System.out.print("Connection value from node " + (j + 1) + " in layer " + (i + 1) +
                            " to node " + (k + 1) + " in layer " + (i + 2) + ": ");
                    double value = scanner.nextDouble();
                    network.setConnectionValue(i, j, k, value);
                }
            }
        }

        System.out.print("Layer number: ");
        int layer = scanner.nextInt() - 1;
        System.out.print("Node number in layer " + (layer + 1) + ": ");
        int nodeFrom = scanner.nextInt() - 1;
        System.out.print("Destination node number in layer " + (layer + 2) + ": ");
        int nodeTo = scanner.nextInt() - 1;

        double value = network.getConnectionValue(layer, nodeFrom, nodeTo);
        System.out.println("Connection value from node " + (nodeFrom + 1) + " to node " + (nodeTo + 1) +
                " in layer " + (layer + 1) + " to layer " + (layer + 2) + ": " + value);

        scanner.close();
    }
}
