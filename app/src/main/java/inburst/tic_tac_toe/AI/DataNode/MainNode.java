package inburst.tic_tac_toe.AI.DataNode;

import java.util.List;

import inburst.tic_tac_toe.AI.Config.AIConfig;

/**
 * Created by lennyhicks on 5/23/17.
 * TicTacToe.
 */

public class MainNode {

    public Node mRockParentNode = new Node(AIConfig.NodeType.ROCK, 1);
    public Node mPaperParentNode = new Node(AIConfig.NodeType.PAPER, 1);
    public Node mScissorsParentNode = new Node(AIConfig.NodeType.SCISSORS, 1);

    public Node getRockParentNode() {
        return mRockParentNode;
    }

    public Node getPaperParentNode() {
        return mPaperParentNode;
    }

    public Node getScissorsParentNode() {
        return mScissorsParentNode;
    }

    public Node getNode(AIConfig.NodeType node) {
        switch (node) {
            case ROCK:
                return getRockParentNode();
            case PAPER:
                return getPaperParentNode();
            case SCISSORS:
                return getScissorsParentNode();
            default:
                return null;
        }
    }

    public void addProbability(List<AIConfig.NodeType> nodeType) {
        switch (nodeType.size()) {
            case 1:
                getNode(nodeType.get(0)).addProbability(nodeType.get(0));
                break;
            case 2:
                getNode(nodeType.get(0)).getNode(nodeType.get(1)).addProbability(nodeType.get(1));
                break;
            case 3:
                getNode(nodeType.get(0)).getNode(nodeType.get(1)).getNode(nodeType.get(2)).addProbability(nodeType.get(2));
            case 4:
                getNode(nodeType.get(0)).getNode(nodeType.get(1)).getNode(nodeType.get(2)).getNode(nodeType.get(3)).addProbability(nodeType.get(3));
            case 5:
                getNode(nodeType.get(0)).getNode(nodeType.get(1)).getNode(nodeType.get(2)).getNode(nodeType.get(3)).getNode(nodeType.get(4)).addProbability(nodeType.get(4));
        }
    }

    public AIConfig.NodeType getBestMove() {
        AIConfig.NodeType bestMove = AIConfig.NodeType.ROCK;
        int chance = 0;
        for (AIConfig.NodeType nodeType : AIConfig.NodeType.values()) {
            Node node = getNode(nodeType);
            if (node.getProbability(nodeType) > chance) {
                bestMove = nodeType.getWeakness();
                chance = node.getProbability(nodeType);

                if (checkChildrenChance(node, chance) > chance) {
                    bestMove = checkChildren(node, chance);
                }
            }
        }
        return bestMove;
    }

    private int checkChildrenChance(Node node, int chance) {
        if (node.getChildren() != null) {
            for (Node nodeTypeChildren : node.getChildren()) {
                for (AIConfig.NodeType nodeChildTypes : AIConfig.NodeType.values()) {
                    if (chance < nodeTypeChildren.getProbability(nodeChildTypes)) {
                        chance = nodeTypeChildren.getProbability(nodeChildTypes);
                    }
                }
            }
        }
        return chance;
    }

    private AIConfig.NodeType checkChildren(Node node, int chance) {
        AIConfig.NodeType bestMove = AIConfig.NodeType.ROCK;

        if (node.getChildren() != null) {
            for (Node nodeTypeChildren : node.getChildren()) {
                for (AIConfig.NodeType nodeChildTypes : AIConfig.NodeType.values())
                    if (chance < nodeTypeChildren.getProbability(nodeChildTypes)) {
                        chance = nodeTypeChildren.getProbability(nodeChildTypes);
                        bestMove = nodeChildTypes;
                        if (checkChildrenChance(node, chance) > chance) {
                            bestMove = checkChildren(node, chance);
                        }
                    }
            }
        }
        return bestMove;
    }
//
//    private void showAddedProbabilitys(List<AIConfig.NodeType> nodeTypes){
//        for (AIConfig.NodeType nodeType : nodeTypes){
//            Log.i("Test", " Type : " + getNode(nodeType) + " - Chance : " + getNode(nodeType).getProbability(nodeType));
//        }
//    }
//
//    private Node getNode(Node node){
//        Node nodeMain = node;
//
//        if(nodeMain.getChildren() != null) {
//            for(AIConfig.NodeType nodeType : AIConfig.NodeType.values()){
//                nodeMain.getNode(nodeType);
//                Log.i("Test", " Type : " + nodeType.name() + " - Chance : " + getNode(nodeType).getProbability(nodeType));
//
//            }
//        }
//        return nodeMain;
//    }
}