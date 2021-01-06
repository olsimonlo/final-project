package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import static android.view.View.INVISIBLE;
        import static android.view.View.VISIBLE;

public class MainActivity2 extends AppCompatActivity {

    private ImageView c_empty1,c_empty2,c_empty3,c_empty4,c_empty5,p_empty1,p_empty2,p_empty3,p_empty4,p_empty5;
    private ImageView imageView,imageView2,imageView3;
    private Button start_btn,stop_btn,reset_btn;
    private TextView p_score,c_score,game_hint2;

    private int gamesresult = 0;

    private int serial_num,score_p,score_c,card_num,shape_num,c_first_card,thinkofcom;
    private int total_num=52;
    private int i=0,k=0,j=0,l=0;
    private int pnumber=1,number=1,timeofbtn=1,timeofcom=1; //抽牌次數，上限為5

    private Boolean btn_set = false;
    private Boolean start_card = false;
    private Boolean Ace_check = false;
    private Boolean com_Ace_check = false;
    private Boolean player_OK = false;
    private Boolean computer_OK = false;
    private Boolean trans_stop_check = false;
    private Boolean trans_stop_check2 = false;
    private Boolean dont_start_check = false;

    int[] times_num = new int[52];

    byte[] play_times = new byte[5];

    byte[] com_times = new byte[5];

    Handler handler1 = new Handler();
    Handler handler2 = new Handler();
    Handler handler3 = new Handler();
    Handler handler4 = new Handler();
    Handler handler5 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //定義以下元件
        c_empty1 = findViewById(R.id.c_empty1);
        c_empty2 = findViewById(R.id.c_empty2);
        c_empty3 = findViewById(R.id.c_empty3);
        c_empty4 = findViewById(R.id.c_empty4);
        c_empty5 = findViewById(R.id.c_empty5);
        p_empty1 = findViewById(R.id.p_empty1);
        p_empty2 = findViewById(R.id.p_empty2);
        p_empty3 = findViewById(R.id.p_empty3);
        p_empty4 = findViewById(R.id.p_empty4);
        p_empty5 = findViewById(R.id.p_empty5);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        p_score = findViewById(R.id.p_score);
        c_score = findViewById(R.id.c_score);
        game_hint2 = findViewById(R.id.game_hint2);

        for(i=0 ;i<52 ;i++)
        {
            times_num[i] = i+1;
        }

        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dont_start_check == false) {
                    btn_set = true;

                    start_btn.setText("拿牌");
                    stop_btn.setText("停牌");
                    reset_btn.setText("返回");

                    showback();
                    if (start_card == false) {
                        c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.background));
                        total_num = total_num - 1;

                        k++;
                        serial_num = getRandom();
                        play_times[j] = (byte)serial_num;
                        j++;
                        runplayer(serial_num);
                        p_21points(serial_num);
                        p_score.setText("" + score_p);

                        start_card = true;
                    }

                    if (timeofbtn >= 5) {
                        Toast.makeText(MainActivity2.this, "已達次數上限，請按'停牌'或'返回'", Toast.LENGTH_SHORT).show();
                        start_btn.setEnabled(false);
                        stop_btn.setEnabled(true);
                        reset_btn.setEnabled(true);
                    }

                    else if (timeofbtn < 5) {
                        total_num = total_num - 1;
                        game_hint2.setText(" " + total_num);

                        k++;
                        serial_num = getRandom();
                        play_times[j] = (byte)serial_num;
                        j++;
                        runplayer(serial_num);
                        p_21points(serial_num);
                        p_score.setText("" + score_p);

                        if(timeofbtn == 1) {
                            k++;
                            timeofcom++;
                            serial_num = getRandom();
                            com_times[l] = (byte)serial_num;
                            l++;
                            c_first_card = serial_num;

                            k++;
                            serial_num = getRandom();
                            com_times[l] = (byte)serial_num;
                            l++;
                            number = 2;
                            runcomputer(serial_num);
                            c_21points(serial_num);

                            c_score.setText("" + score_c);
                            total_num = total_num - 1;
                            game_hint2.setText(" " + total_num);

                        }

                        if(score_p > 21 && Ace_check == true) {
                            score_p = score_p - 10;
                            p_score.setText("" + score_p);
                            Ace_check = false;
                        }

                        if (score_p > 21) {     //gamesresult = 6，玩家爆牌
                            reset_btn.setEnabled(false);
                            start_btn.setEnabled(false);
                            stop_btn.setEnabled(false);
                            number = 1;
                            runcomputer(c_first_card);
                            c_21points(c_first_card);

                            c_score.setText("" + score_c);
                            total_num = total_num - 1;
                            game_hint2.setText(" " + total_num);

                            gamesresult = 1;

                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    gamesresult = 6;
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity2.this,MainActivity3.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putByteArray("play_times",play_times);
                                    bundle.putByteArray("com_times",com_times);
                                    bundle.putInt("gamesresult",gamesresult);
                                    bundle.putInt("score_p",score_p);
                                    bundle.putInt("score_c",score_c);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                    trans_stop_check = true;
                                    trans_stop_check2 = true;
                                }
                            },2000);
                        }
                        timeofbtn++;
                    }
                }
            }
        });

        stop_btn = findViewById(R.id.stop_btn);
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btn_set == false) {
                    Toast.makeText(MainActivity2.this, "此功能無效，請先按下'開始'", Toast.LENGTH_LONG).show();
                    start_btn.setText("開始");
                }

                else if (trans_stop_check == false && btn_set == true) {
                    start_btn.setEnabled(false);
                    stop_btn.setEnabled(false);
                    reset_btn.setEnabled(false);

                    stop_btn.setText("結算中");

                    dont_start_check = true;
                    player_OK = true;

                    if (dont_start_check == true) {

                        number = 1;
                        runcomputer(c_first_card);
                        c_21points(c_first_card);

                        c_score.setText("" + score_c);
                        total_num = total_num - 1;
                        game_hint2.setText(" " + total_num);

                        number = 3;
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(score_c > 21 && com_Ace_check == true) {
                                    score_c = score_c - 10;
                                    c_score.setText("" + score_c);
                                    com_Ace_check = false;
                                }

                                thinkofcom = (int) (Math.random() * (5));   //五分之一的機率
                                if (((score_c <=18 && score_c >= 17) && (thinkofcom == 0)) || (score_c < 17)) {
                                    c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.background));
                                    handler3.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            k++;
                                            timeofcom++;
                                            serial_num = getRandom();
                                            com_times[l] = (byte)serial_num;
                                            l++;
                                            runcomputer(serial_num);
                                            c_21points(serial_num);

                                            c_score.setText("" + score_c);
                                            total_num = total_num - 1;
                                            game_hint2.setText(" " + total_num);

                                            if(score_c > 21 && com_Ace_check == true) {
                                                score_c = score_c - 10;
                                                c_score.setText("" + score_c);
                                                com_Ace_check = false;
                                            }

                                            thinkofcom = (int) (Math.random() * (10));   //十分之一的機率
                                            if (((score_c <= 18 && score_c >= 17) && (thinkofcom == 0)) || (score_c < 17)) {
                                                c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.background));
                                                handler4.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        k++;
                                                        timeofcom++;
                                                        serial_num = getRandom();
                                                        com_times[l] = (byte)serial_num;
                                                        l++;
                                                        runcomputer(serial_num);
                                                        c_21points(serial_num);

                                                        c_score.setText("" + score_c);
                                                        total_num = total_num - 1;
                                                        game_hint2.setText(" " + total_num);

                                                        if(score_c > 21 && com_Ace_check == true) {
                                                            score_c = score_c - 10;
                                                            c_score.setText("" + score_c);
                                                            com_Ace_check = false;
                                                        }

                                                        thinkofcom = (int) (Math.random() * (100));   //百分之一的機率
                                                        if (((score_c <= 18 && score_c >= 17) && (score_c < 1)) || (score_c < 17)) {
                                                            c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.background));
                                                            handler5.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    k++;
                                                                    timeofcom++;
                                                                    serial_num = getRandom();
                                                                    com_times[l] = (byte)serial_num;
                                                                    l++;
                                                                    runcomputer(serial_num);
                                                                    c_21points(serial_num);

                                                                    c_score.setText("" + score_c);
                                                                    total_num = total_num - 1;
                                                                    game_hint2.setText(" " + total_num);

                                                                    if(score_c > 21 && com_Ace_check == true) {
                                                                        score_c = score_c - 10;
                                                                        c_score.setText("" + score_c);
                                                                        com_Ace_check = false;
                                                                    }

                                                                    computer_OK = true;
                                                                    stop_btn.setEnabled(true);
                                                                    stop_btn.setText("顯示結果");
                                                                    dont_start_check = false;
                                                                    trans_stop_check = true;
                                                                }
                                                            }, 1000);
                                                        } else if (score_c < 17){       //gamesresult = 10，電腦未達17點
                                                            gamesresult = 10;
                                                            Intent intent = new Intent();
                                                            intent.setClass(MainActivity2.this,MainActivity3.class);
                                                            Bundle bundle = new Bundle();
                                                            bundle.putByteArray("play_times",play_times);
                                                            bundle.putByteArray("com_times",com_times);
                                                            bundle.putInt("gamesresult",gamesresult);
                                                            bundle.putInt("score_p",score_p);
                                                            bundle.putInt("score_c",score_c);
                                                            intent.putExtras(bundle);
                                                            startActivity(intent);

                                                            trans_stop_check = true;
                                                            trans_stop_check2 = true;
                                                        }else {
                                                            computer_OK = true;
                                                            stop_btn.setEnabled(true);
                                                            stop_btn.setText("顯示結果");
                                                            dont_start_check = false;
                                                            trans_stop_check = true;
                                                        }
                                                    }
                                                }, 1000);
                                            } else {
                                                computer_OK = true;
                                                stop_btn.setEnabled(true);
                                                stop_btn.setText("顯示結果");
                                                dont_start_check = false;
                                                trans_stop_check = true;
                                            }
                                        }
                                    }, 1000);
                                } else {
                                    computer_OK = true;
                                    stop_btn.setEnabled(true);
                                    stop_btn.setText("顯示結果");
                                    dont_start_check = false;
                                    trans_stop_check = true;
                                }
                            }
                        }, 1000);
                    } else {
                        computer_OK = true;
                        stop_btn.setEnabled(true);
                        stop_btn.setText("顯示結果");
                        dont_start_check = false;
                        trans_stop_check = true;
                    }
                }

                else if ((player_OK == true) && (computer_OK == true) && trans_stop_check == true && trans_stop_check2 == false) {

                        if ((score_c > score_p) && (score_c < 21)) {       //gamesresult = 1，電腦獲勝
                            gamesresult = 1;
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this,MainActivity3.class);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("play_times",play_times);
                            bundle.putByteArray("com_times",com_times);
                            bundle.putInt("gamesresult",gamesresult);
                            bundle.putInt("score_p",score_p);
                            bundle.putInt("score_c",score_c);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            trans_stop_check = true;
                            trans_stop_check2 = true;
                        } else if ((score_c < score_p) && (score_p < 21)) {        //gamesresult = 2，玩家獲勝
                            gamesresult = 2;
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this,MainActivity3.class);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("play_times",play_times);
                            bundle.putByteArray("com_times",com_times);
                            bundle.putInt("gamesresult",gamesresult);
                            bundle.putInt("score_p",score_p);
                            bundle.putInt("score_c",score_c);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            trans_stop_check = true;
                            trans_stop_check2 = true;
                        } else if ((score_c > score_p) && (score_c == 21)) {       //gamesresult = 3，電腦Black Jack
                            gamesresult = 3;
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this,MainActivity3.class);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("play_times",play_times);
                            bundle.putByteArray("com_times",com_times);
                            bundle.putInt("gamesresult",gamesresult);
                            bundle.putInt("score_p",score_p);
                            bundle.putInt("score_c",score_c);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            trans_stop_check = true;
                            trans_stop_check2 = true;
                        } else if ((score_c < score_p) && (score_p == 21)) {       //gamesresult = 4，玩家Black Jack
                            gamesresult = 4;
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this,MainActivity3.class);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("play_times",play_times);
                            bundle.putByteArray("com_times",com_times);
                            bundle.putInt("gamesresult",gamesresult);
                            bundle.putInt("score_p",score_p);
                            bundle.putInt("score_c",score_c);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            trans_stop_check = true;
                            trans_stop_check2 = true;
                        } else if ((score_c > score_p) && (score_c > 21)) {     //gamesresult = 5，電腦爆牌
                            gamesresult = 5;
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this,MainActivity3.class);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("play_times",play_times);
                            bundle.putByteArray("com_times",com_times);
                            bundle.putInt("gamesresult",gamesresult);
                            bundle.putInt("score_p",score_p);
                            bundle.putInt("score_c",score_c);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            trans_stop_check = true;
                            trans_stop_check2 = true;
                        } else if ((score_c < score_p) && (score_p > 21)) {     //gamesresult = 6，玩家爆牌
                            gamesresult = 6;
                            Intent intent = new Intent();
                            intent.setClass(MainActivity2.this,MainActivity3.class);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("play_times",play_times);
                            bundle.putByteArray("com_times",com_times);
                            bundle.putInt("gamesresult",gamesresult);
                            bundle.putInt("score_p",score_p);
                            bundle.putInt("score_c",score_c);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            trans_stop_check = true;
                            trans_stop_check2 = true;
                        } else if((score_p == score_c)) {
                            if (timeofcom > timeofbtn) {        //gamesresult = 7，分數一樣但電腦張數多
                                gamesresult = 7;
                                Intent intent = new Intent();
                                intent.setClass(MainActivity2.this,MainActivity3.class);
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("play_times",play_times);
                                bundle.putByteArray("com_times",com_times);
                                bundle.putInt("gamesresult",gamesresult);
                                bundle.putInt("score_p",score_p);
                                bundle.putInt("score_c",score_c);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                trans_stop_check = true;
                                trans_stop_check2 = true;
                            }
                            else if (timeofcom < timeofbtn) {       //gamesresult = 8，分數一樣但玩家張數多
                                gamesresult = 8;
                                Intent intent = new Intent();
                                intent.setClass(MainActivity2.this,MainActivity3.class);
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("play_times",play_times);
                                bundle.putByteArray("com_times",com_times);
                                bundle.putInt("gamesresult",gamesresult);
                                bundle.putInt("score_p",score_p);
                                bundle.putInt("score_c",score_c);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                trans_stop_check = true;
                                trans_stop_check2 = true;
                            }
                            else {      //gamesresult = 9，平手
                                gamesresult = 9;
                                Intent intent = new Intent();
                                intent.setClass(MainActivity2.this,MainActivity3.class);
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("play_times",play_times);
                                bundle.putByteArray("com_times",com_times);
                                bundle.putInt("gamesresult",gamesresult);
                                bundle.putInt("score_p",score_p);
                                bundle.putInt("score_c",score_c);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                trans_stop_check = true;
                                trans_stop_check2 = true;
                            }
                        }
                }
            }
        });

        reset_btn = findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity2.this,MainActivity.class);
                    finish();
                    onDestroy();
                }
        });

        if (trans_stop_check == true && trans_stop_check2 == true) {
            c_empty1.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            c_empty2.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            c_empty3.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            c_empty4.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            c_empty5.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            p_empty1.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            p_empty2.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            p_empty3.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            p_empty4.setImageDrawable(getResources().getDrawable(R.drawable.empty));
            p_empty5.setImageDrawable(getResources().getDrawable(R.drawable.empty));

            btn_set = false;
            start_card = false;
            Ace_check = false;
            com_Ace_check = false;
            player_OK = false;
            computer_OK = false;
            trans_stop_check = false;
            trans_stop_check2 = false;
            dont_start_check = false;

            total_num = 52;
            timeofbtn = 1;
            timeofcom = 1;
            number = 1;
            pnumber = 1;

            p_score.setText("" + 0);
            c_score.setText("" + 0);
            game_hint2.setText("" + 52);

            score_p = 0;
            score_c = 0;
            card_num = 0;
            shape_num = 0;
            serial_num = 0;
            thinkofcom = 0;

            gamesresult = 0;

            i=0;
            k=0;
            j=0;
            l=0;

            for (i = 0; i < 52; i++) {
                times_num[i] = 0;
            }
            for (i = 0; i < 5; i++) {
                play_times[i] = 0;
            }
            for (i = 0; i < 5; i++) {
                com_times[i] = 0;
            }

            start_btn.setEnabled(true);
            stop_btn.setEnabled(true);
            stop_btn.setText("停止");
            reset_btn.setEnabled(true);
        }

    }
    protected void onDestroy(){ //真正作用區
        super.onDestroy();
        //Kill myself
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void showback(){
        if((total_num <= 52) && (total_num >34))
        { imageView.setVisibility(VISIBLE);imageView2.setVisibility(VISIBLE);imageView3.setVisibility(VISIBLE); }
        else if((total_num <= 34) && (total_num >17))
        { imageView.setVisibility(INVISIBLE);imageView2.setVisibility(VISIBLE);imageView3.setVisibility(VISIBLE); }
        else if((total_num < 52) && (total_num >0))
        { imageView.setVisibility(INVISIBLE);imageView2.setVisibility(INVISIBLE);imageView3.setVisibility(VISIBLE); }
    }

    public int getRandom() {
        int rand_ans;
        int n;
        n = (int)(Math.random()*(53-k));
        rand_ans = times_num[n];
        for(int j = n; j<((times_num.length)-1); j++)
        {
            times_num[j] = times_num[j+1];
        }
        return rand_ans;
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

    public void p_21points(int serial_num) {
        //二十一點的計分系統
        card_num = serial_num % 13;
        shape_num = serial_num / 13;
        if (card_num == 0 || card_num == 12 || card_num == 11) {
            score_p = score_p + 10;
        } else if (card_num == 1) {
            if (score_p <= 10) {
                score_p = score_p + 11;
                Ace_check = true;
            } else {
                score_p = score_p + 1;
            }
        } else {
            score_p = score_p + card_num;
        }

    }

    public void c_21points(int serial_num) {
        //二十一點的計分系統
        card_num = serial_num % 13;
        shape_num = serial_num / 13;
        if (card_num == 0 || card_num == 12 || card_num == 11) {
            score_c = score_c + 10;
        } else if (card_num == 1) {
            if (score_c <= 10) {
                score_c = score_c + 11;
                com_Ace_check = true;
            } else {
                score_c = score_c + 1;
            }
        } else {
            score_c = score_c + card_num;
        }
    }

}