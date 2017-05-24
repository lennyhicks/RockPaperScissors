package inburst.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import inburst.tic_tac_toe.AI.Bot;
import inburst.tic_tac_toe.AI.Config.AIConfig;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_rock)
    Button mButtonRock;
    @BindView(R.id.button_paper)
    Button mButtonPaper;
    @BindView(R.id.button_scissors)
    Button mButtonScissors;
    @BindView(R.id.win_count)
    TextView mTextWincount;
    @BindView(R.id.tie_count)
    TextView mTextTiecount;
    @BindView(R.id.lost_count)
    TextView mTextLoseCount;
    @BindView(R.id.text_gamestatus)
    TextView mTextGamestatus;
    private Bot bot;

    int winCount = 0;
    int loseCount = 0;
    int tieCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        bot = new Bot();
        ButterKnife.bind(this);
        runRandomSim(10000);

    }

    private void tieGame(AIConfig.NodeType move, AIConfig.NodeType oppMove) {
        tieCount++;
        mTextGamestatus.setText("You Tied. \n Your move : " + move.name() + " \n Opponent Move : " + oppMove.name() + " \n Wins : " + winCount + " " + getPercent(winCount) + " \n Loses : " + loseCount + " " + getPercent(loseCount) + " \n Ties : " + tieCount + " " + getPercent(tieCount));
    }

    private String getPercent(int count) {
        int total = winCount + loseCount + tieCount;

        if(count == 0){
            return "Test";
        } else {
            return (count * 100 / total) + "%";
        }

    }

    private void loseGame(AIConfig.NodeType move, AIConfig.NodeType oppMove) {
        loseCount++;
        mTextGamestatus.setText("You Lost. \n Your move : " + move.name() + " \n Opponent Move : " + oppMove.name() + " \n Wins : " + winCount + " " + getPercent(winCount) + " \n Loses : " + loseCount + " " + getPercent(loseCount) + " \n Ties : " + tieCount + " " + getPercent(tieCount));
    }

    private void winGame(AIConfig.NodeType move, AIConfig.NodeType oppMove) {
        winCount++;
        mTextGamestatus.setText("You Won. \n Your move : " + move.name() + " \n Opponent Move : " + oppMove.name() + " \n Wins : " + winCount + " " + getPercent(winCount) + " \n Loses : " + loseCount + " " + getPercent(loseCount) + " \n Ties : " + tieCount + " " + getPercent(tieCount));
    }

    @OnClick({R.id.button_rock, R.id.button_paper, R.id.button_scissors})
    public void onViewClicked(View view) {
        AIConfig.NodeType oppMove;

        switch (view.getId()) {

            case R.id.button_rock:
                bot.addMove(AIConfig.NodeType.ROCK);
                oppMove = bot.getMove();

                if (AIConfig.NodeType.ROCK.winsAgainst(oppMove)) {
                    winGame(AIConfig.NodeType.ROCK, oppMove);
                } else if (AIConfig.NodeType.ROCK.losesAgainst(oppMove)) {
                    loseGame(AIConfig.NodeType.ROCK, oppMove);
                } else if (AIConfig.NodeType.ROCK.tiesAgainst(oppMove)) {
                    tieGame(AIConfig.NodeType.ROCK, oppMove);
                }
                break;
            case R.id.button_paper:
                bot.addMove(AIConfig.NodeType.PAPER);
                oppMove = bot.getMove();

                if (AIConfig.NodeType.PAPER.winsAgainst(oppMove)) {
                    winGame(AIConfig.NodeType.PAPER, oppMove);
                } else if (AIConfig.NodeType.PAPER.losesAgainst(oppMove)) {
                    loseGame(AIConfig.NodeType.PAPER, oppMove);
                } else if (AIConfig.NodeType.PAPER.tiesAgainst(oppMove)) {
                    tieGame(AIConfig.NodeType.PAPER, oppMove);
                }
                break;
            case R.id.button_scissors:
                bot.addMove(AIConfig.NodeType.SCISSORS);
                oppMove = bot.getMove();

                if (AIConfig.NodeType.SCISSORS.winsAgainst(oppMove)) {
                    winGame(AIConfig.NodeType.SCISSORS, oppMove);
                } else if (AIConfig.NodeType.SCISSORS.losesAgainst(oppMove)) {
                    loseGame(AIConfig.NodeType.SCISSORS, oppMove);
                } else if (AIConfig.NodeType.SCISSORS.tiesAgainst(oppMove)) {
                    tieGame(AIConfig.NodeType.SCISSORS, oppMove);
                }
                break;
        }


    }

    private void runRandomSim(int amount) {
        Random random = new Random();
        while(amount > 0){
            int ran = random.nextInt(3);
            switch (ran){
                case 1 : useMove(AIConfig.NodeType.ROCK); break;
                case 2 : useMove(AIConfig.NodeType.PAPER); break;
                case 3 : useMove(AIConfig.NodeType.SCISSORS); break;
            }
            amount--;
        }
    }

    public void useMove(AIConfig.NodeType move) {
        AIConfig.NodeType oppMove;
        bot.addMove(move);
        oppMove = bot.getMove();

        if (move.winsAgainst(oppMove)) {
            winGame(move, oppMove);
        } else if (move.losesAgainst(oppMove)) {
            loseGame(move, oppMove);
        } else if (move.tiesAgainst(oppMove)) {
            tieGame(move, oppMove);
        }
    }
}
