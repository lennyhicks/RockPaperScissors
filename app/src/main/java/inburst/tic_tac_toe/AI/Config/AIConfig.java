package inburst.tic_tac_toe.AI.Config;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lennyhicks on 5/23/17.
 * TicTacToe.
 */

public class AIConfig {

    public static int DEPTH_LEARNING_WANTED = 5;

    public List<NodeType> getAvailableTypes() {
        return Arrays.asList(NodeType.values());
    }

    public enum NodeType {
        ROCK, PAPER, SCISSORS;

        public boolean losesAgainst(NodeType move) {
            switch (this){
                case ROCK: if (move == PAPER){
                    return true;
                } break;
                case PAPER: if (move == SCISSORS){
                    return true;
                } break;
                case SCISSORS: if (move == ROCK){
                    return true;
                }break;
            }
            return false;
        }

        public boolean winsAgainst(NodeType move) {
            switch (this){
                case SCISSORS: if (move == PAPER){
                    return true;
                } break;
                case ROCK: if (move == SCISSORS){
                    return true;
                } break;
                case PAPER: if (move == ROCK){
                    return true;
                } break;
            }
            return false;
        }

        public boolean tiesAgainst(NodeType move) {
            return this.equals(move);
        }

        public NodeType getWeakness() {
            for(NodeType nodeType : values()){
                if(this.losesAgainst(nodeType)){
                    return nodeType;
                }
            }
            return ROCK;
        }
    }
}