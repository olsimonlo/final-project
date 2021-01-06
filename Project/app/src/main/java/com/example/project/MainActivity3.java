package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private ImageView c_empty1,c_empty2,c_empty3,c_empty4,c_empty5,p_empty1,p_empty2,p_empty3,p_empty4,p_empty5;
    private Button return_btn;
    private TextView result,p_score,c_score;

    private int number=1,pnumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        c_empty1 = findViewById(R.id.c_empty_1);
        c_empty2 = findViewById(R.id.c_empty_2);
        c_empty3 = findViewById(R.id.c_empty_3);
        c_empty4 = findViewById(R.id.c_empty_4);
        c_empty5 = findViewById(R.id.c_empty_5);
        p_empty1 = findViewById(R.id.p_empty_1);
        p_empty2 = findViewById(R.id.p_empty_2);
        p_empty3 = findViewById(R.id.p_empty_3);
        p_empty4 = findViewById(R.id.p_empty_4);
        p_empty5 = findViewById(R.id.p_empty_5);

        result = findViewById(R.id.result);
        p_score = findViewById(R.id.result_play_score);
        c_score = findViewById(R.id.result_com_score);

        Bundle bundle = this.getIntent().getExtras();

            int gamesresult = bundle.getInt("gamesresult");
            int score_p = bundle.getInt("score_p");
            int score_c = bundle.getInt("score_c");

            byte[] play_times = bundle.getByteArray("play_times");

            byte[] com_times = bundle.getByteArray("com_times");

        p_score.setText(""+score_p);
        c_score.setText(""+score_c);

        if(gamesresult == 1){
            result.setText("電腦獲勝，您輸了");
        }
        else if(gamesresult == 2){
            result.setText("玩家獲勝，您贏了");
        }
        else if(gamesresult == 3){
            result.setText("電腦Black Jack，可惜您輸了");
        }
        else if(gamesresult == 4){
            result.setText("玩家Black Jack，恭喜您贏了");
        }
        else if(gamesresult == 5){
            result.setText("電腦爆牌，您贏了");
        }
        else if(gamesresult == 6){
            result.setText("玩家爆牌，您輸了");
        }
        else if(gamesresult == 7){
            result.setTextSize(40);
            result.setText("雖然分數一樣但電腦張數較多，所以您輸了");
        }
        else if(gamesresult == 8){
            result.setTextSize(40);
            result.setText("雖然分數一樣但玩家張數多，所以您贏了");
        }
        else if(gamesresult == 9){
            result.setTextSize(40);
            result.setText("兩者的分數及張數一樣，所以平局");
        }
        else if(gamesresult == 10){
            result.setTextSize(40);
            result.setText("電腦未達到17點，所以玩家獲勝");
        }

        for (int i=0;i<5;i++)
        {
            if(play_times[i] !=0)
            {
                runplayer(play_times[i]);
            }
            if(com_times[i] !=0)
            {
                runcomputer(com_times[i]);
            }
        }

        return_btn = findViewById(R.id.return_btn);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity3.this,MainActivity2.class);
                finish();
                onDestroy();
            }
        });
    }
    protected void onDestroy(){ //真正作用區
        super.onDestroy();
        //Kill myself
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void runplayer (int serial_num){
        switch (serial_num) {
            case 1: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 2: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 3: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 4: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 5: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 6: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 7: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 8: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 9: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 10: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 11: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 12: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 13: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 14: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 15: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 16: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 17: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 18: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 19: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 20: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 21: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 22: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 23: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 24: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 25: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 26: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 27: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 28: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 29: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 30: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 31: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 32: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 33: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 34: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 35: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 36: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 37: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 38: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 39: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 40: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 41: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 42: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 43: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 44: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 45: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 46: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 47: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 48: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 49: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 50: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 51: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    pnumber = pnumber + 1;
                }
                break;
            }
            case 52: {
                if (pnumber == 1) {
                    p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 2) {
                    p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 3) {
                    p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 4) {
                    p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    pnumber = pnumber + 1;
                } else if (pnumber == 5) {
                    p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    pnumber = pnumber + 1;
                }
                break;
            }
            default :{

            }
        }
    }

    public void runcomputer (int serial_num){
        switch (serial_num) {
            case 1: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades1));
                    number = 0;
                }
                break;
            }
            case 2: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades2));
                    number = 0;
                }
                break;
            }
            case 3: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades3));
                    number = 0;
                }
                break;
            }
            case 4: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades4));
                    number = 0;
                }
                break;
            }
            case 5: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades5));
                    number = 0;
                }
                break;
            }
            case 6: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades6));
                    number = 0;
                }
                break;
            }
            case 7: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades7));
                    number = 0;
                }
                break;
            }
            case 8: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades8));
                    number = 0;
                }
                break;
            }
            case 9: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades9));
                    number = 0;
                }
                break;
            }
            case 10: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades10));
                    number = 0;
                }
                break;
            }
            case 11: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades11));
                    number = 0;
                }
                break;
            }
            case 12: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades12));
                    number = 0;
                }
                break;
            }
            case 13: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.spades13));
                    number = 0;
                }
                break;
            }
            case 14: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts1));
                    number = 0;
                }
                break;
            }
            case 15: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts2));
                    number = 0;
                }
                break;
            }
            case 16: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts3));
                    number = 0;
                }
                break;
            }
            case 17: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts4));
                    number = 0;
                }
                break;
            }
            case 18: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts5));
                    number = 0;
                }
                break;
            }
            case 19: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts6));
                    number = 0;
                }
                break;
            }
            case 20: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts7));
                    number = 0;
                }
                break;
            }
            case 21: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts8));
                    number = 0;
                }
                break;
            }
            case 22: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts9));
                    number = 0;
                }
                break;
            }
            case 23: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts10));
                    number = 0;
                }
                break;
            }
            case 24: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts11));
                    number = 0;
                }
                break;
            }
            case 25: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts12));
                    number = 0;
                }
                break;
            }
            case 26: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.hearts13));
                    number = 0;
                }
                break;
            }
            case 27: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds1));
                    number = 0;
                }
                break;
            }
            case 28: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds2));
                    number = 0;
                }
                break;
            }
            case 29: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds3));
                    number = 0;
                }
                break;
            }
            case 30: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds4));
                    number = 0;
                }
                break;
            }
            case 31: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds5));
                    number = 0;
                }
                break;
            }
            case 32: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds6));
                    number = 0;
                }
                break;
            }
            case 33: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds7));
                    number = 0;
                }
                break;
            }
            case 34: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds8));
                    number = 0;
                }
                break;
            }
            case 35: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds9));
                    number = 0;
                }
                break;
            }
            case 36: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds10));
                    number = 0;
                }
                break;
            }
            case 37: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds11));
                    number = 0;
                }
                break;
            }
            case 38: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds12));
                    number = 0;
                }
                break;
            }
            case 39: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.diamonds13));
                    number = 0;
                }
                break;
            }
            case 40: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs1));
                    number = 0;
                }
                break;
            }
            case 41: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs2));
                    number = 0;
                }
                break;
            }
            case 42: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs3));
                    number = 0;
                }
                break;
            }
            case 43: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs4));
                    number = 0;
                }
                break;
            }
            case 44: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs5));
                    number = 0;
                }
                break;
            }
            case 45: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs6));
                    number = 0;
                }
                break;
            }
            case 46: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs7));
                    number = 0;
                }
                break;
            }
            case 47: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs8));
                    number = 0;
                }
                break;
            }
            case 48: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs9));
                    number = 0;
                }
                break;
            }
            case 49: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs10));
                    number = 0;
                }
                break;
            }
            case 50: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs11));
                    number = 0;
                }
                break;
            }
            case 51: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs12));
                    number = 0;
                }
                break;
            }
            case 52: {
                if (number == 1) {
                    c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    number = number + 1;
                } else if (number == 2) {
                    c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    number = number + 1;
                } else if (number == 3) {
                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    number = number + 1;
                } else if (number == 4) {
                    c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    number = number + 1;
                } else if (number == 5) {
                    c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.clubs13));
                    number = 0;
                }
                break;
            }
            default :{

            }
        }
    }
}