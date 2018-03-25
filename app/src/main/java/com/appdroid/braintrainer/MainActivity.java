package com.appdroid.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout gameRelativeLayout;
    Button startButton;
    TextView correctIncorrectTextView;
    TextView scoreTextView;
    TextView mathTextView;
    TextView timerTextView;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numOfQues = 0;

    public void playAgain(View view){

        score = 0;
        numOfQues = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(" / ");
        correctIncorrectTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQues();

        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long milliSecFinished) {

                timerTextView.setText(String.valueOf(milliSecFinished/1000)+"s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                correctIncorrectTextView.setText("Your Score: "+Integer.toString(score)+" / "+Integer.toString(numOfQues));
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void generateQues(){

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        mathTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        int incorrectAnswers;

        for (int i=0; i<4; i++){

            if(i == locationOfCorrectAnswer){

                answers.add(a+b);

            } else {

                incorrectAnswers = random.nextInt(41);

                while(incorrectAnswers == a+b){

                    incorrectAnswers = random.nextInt(41);

                }

                answers.add(incorrectAnswers);

            }

        }

        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chosenAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            correctIncorrectTextView.setText("Correct!");


        } else {

            correctIncorrectTextView.setText("Incorrect!");

        }

        numOfQues++;
        scoreTextView.setText(Integer.toString(score)+" / "+Integer.toString(numOfQues));
        generateQues();

    }

    public void goButton(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        mathTextView = (TextView) findViewById(R.id.mathTextView);
        correctIncorrectTextView = (TextView) findViewById(R.id.correctIncorrectTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

    }
}
