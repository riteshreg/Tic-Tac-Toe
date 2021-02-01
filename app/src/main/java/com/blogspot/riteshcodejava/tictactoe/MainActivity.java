package com.blogspot.riteshcodejava.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.blogspot.riteshcodejava.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int ACTIVE_PLAYER  =   PLAYER_O;
    int [] EMPTY_CELL = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean GAME_IS_RUNNING = true;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.notificationTextView.setText("O turn");
        binding.button0.setOnClickListener(this);
        binding.button1.setOnClickListener(this);
        binding.button2.setOnClickListener(this);
        binding.button3.setOnClickListener(this);
        binding.button4.setOnClickListener(this);
        binding.button5.setOnClickListener(this);
        binding.button6.setOnClickListener(this);
        binding.button7.setOnClickListener(this);
        binding.button8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

//        int PLAYER_O = 0;
//        int PLAYER_X = 1;
//        int ACTIVE_PLAYER  =   PLAYER_O;
//        int [] EMPTY_CELL = {-1,-1,-1,-1,-1,-1,-1,-1,-1};


        Button buttonId = findViewById(v.getId());
        int tagId = Integer.parseInt(v.getTag().toString());

        if (EMPTY_CELL[tagId]!= -1){
            return;
        }

        if (!GAME_IS_RUNNING){
            return;
        }

        EMPTY_CELL[tagId] = ACTIVE_PLAYER;

        if (ACTIVE_PLAYER == PLAYER_O){

            buttonId.setBackgroundColor(getResources().getColor(R.color.yellow));
            buttonId.setText("O");
            ACTIVE_PLAYER = PLAYER_X;
            binding.notificationTextView.setText("X Turn");


        }else{

            buttonId.setBackgroundColor(getResources().getColor(R.color.red));
            buttonId.setText("X");
            ACTIVE_PLAYER = PLAYER_O;
            binding.notificationTextView.setText("O Turn");


        }


        checkTheWinner();

    }

    private void checkTheWinner() {
                                //      0  1  2  3  4  5  6  7  8
        //        int [] EMPTY_CELL = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        //                               0  0  0
        //
        int [][] winnerPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for (int i=0;i<8;i++){

            int position0 = winnerPosition[i][0];  //== 0
            int position1 = winnerPosition[i][1];  // == 1
            int position2 = winnerPosition[i][2];  // == 2

            if (EMPTY_CELL[position0] == EMPTY_CELL[position1]  &&  EMPTY_CELL[position1] == EMPTY_CELL[position2]){

             if (EMPTY_CELL[position0] != -1){

                 GAME_IS_RUNNING = false;

                 if (EMPTY_CELL[position0] == PLAYER_O){

                    showMessage("Player O Win");

                 }else
                        showMessage("Player X win");

             }
             }

        }


    }
    private void showMessage(String message){
        new AlertDialog.Builder(this).setCancelable(false).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ResetGame();
            }
        }).setMessage(message).show();
    }

    private void ResetGame() {

        binding.button0.setText("");
        binding.button1.setText("");
        binding.button2.setText("");
        binding.button3.setText("");
        binding.button4.setText("");
        binding.button5.setText("");
        binding.button6.setText("");
        binding.button7.setText("");
        binding.button8.setText("");


        binding.button0.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button1.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button2.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button3.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button4.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button5.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button6.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button7.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        binding.button8.setBackgroundColor(getResources().getColor(R.color.defaultColor));


        EMPTY_CELL = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        ACTIVE_PLAYER = PLAYER_O;

        GAME_IS_RUNNING = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                ResetGame();
        }
        return super.onOptionsItemSelected(item);
    }
}