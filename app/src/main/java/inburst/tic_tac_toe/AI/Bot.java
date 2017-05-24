package inburst.tic_tac_toe.AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import inburst.tic_tac_toe.AI.Config.AIConfig;
import inburst.tic_tac_toe.AI.DataNode.MainNode;

/**
 * Created by lennyhicks on 5/23/17.
 * TicTacToe.
 */

public class Bot {

    AIConfig mAIConfig;
    List<AIConfig.NodeType> nodeTypes;
    List<AIConfig.NodeType> lastMoves;
    MainNode mMainNode;



    public Bot(){
        mAIConfig = new AIConfig();
        nodeTypes = mAIConfig.getAvailableTypes();
        mMainNode = new MainNode();
        lastMoves = new ArrayList<>();
    }

    public void addMove(AIConfig.NodeType move) {
        if(lastMoves.size() > AIConfig.DEPTH_LEARNING_WANTED){
            lastMoves.remove(0);
        }
        lastMoves.add(move);
        mMainNode.addProbability(Arrays.asList(move));

    }

    public AIConfig.NodeType getMove(){
        return mMainNode.getBestMove();
    }

}
