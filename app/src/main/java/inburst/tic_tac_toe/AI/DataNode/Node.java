package inburst.tic_tac_toe.AI.DataNode;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import inburst.tic_tac_toe.AI.Config.AIConfig;

/**
 * Created by lennyhicks on 5/23/17.
 * TicTacToe.
 */

public class Node {

    private int currentDepth;
    private ArrayList<Node> children = new ArrayList<>();
    private AIConfig.NodeType mNodeType;

    private HashMap<AIConfig.NodeType, Integer> probabilityChart = new HashMap<>();

    public Node(AIConfig.NodeType nodeType, Integer currentDepth) {
        if (currentDepth != null) {
            this.currentDepth = currentDepth;
        }

        probabilityChart = new HashMap<>();
        probabilityChart.put(AIConfig.NodeType.ROCK, 0);
        probabilityChart.put(AIConfig.NodeType.PAPER, 0);
        probabilityChart.put(AIConfig.NodeType.SCISSORS, 0);

        this.mNodeType = nodeType;
        if (this.currentDepth < AIConfig.DEPTH_LEARNING_WANTED) {
            for (AIConfig.NodeType nodeTypes : AIConfig.NodeType.values()) {
                Log.i("Creating Data", " Type : " + nodeTypes.name() + " depth : " +this.currentDepth + 1);
                getChildren().add(new Node(nodeTypes, this.currentDepth + 1));
            }
        }
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public AIConfig.NodeType getNodeType() {
        return mNodeType;
    }

    public HashMap<AIConfig.NodeType, Integer> getChances() {
        HashMap<AIConfig.NodeType, Integer> tempList = new HashMap<>();
        for (Node node : children) {
            tempList.put(node.getNodeType(), node.getChances().get(node.getNodeType()));
        }
        return tempList;
    }

    public Node getNode(AIConfig.NodeType node) {
        for (Node childNode : children) {
            if(childNode.getNodeType().equals(node)){
                return childNode;
            }
        }
        return null;
    }

    public void addProbability(AIConfig.NodeType nodeType) {
        probabilityChart.put(nodeType, probabilityChart.get(nodeType) + 1);
    }

    public int getProbability(AIConfig.NodeType nodeType) {
        return probabilityChart.get(nodeType);
    }
}
