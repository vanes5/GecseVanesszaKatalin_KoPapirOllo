package com.example.rps;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonOllo;
    private Button buttonKo;
    private Button buttonPapir;
    private ImageView imgPlayer;
    private ImageView imgComputer;
    private TextView pointPlayer;
    private TextView pointComputer;
    private int compPoint;
    private int playerPoint;
    private Random rand;
    private int randSzam;
    private AlertDialog.Builder alertDialog;
    private int compKepAzon;
    private int playerKepAzon;
    //0-ko 1-papir 2-ollo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        buttonOllo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerKepAzon= 2;
                    imgPlayer.setImageResource(R.drawable.scissors);
                    menet();
            }
        });
        buttonKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerKepAzon = 0;
                imgPlayer.setImageResource(R.drawable.rock);
                    menet();

            }
        });
        buttonPapir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerKepAzon = 1;
                    imgPlayer.setImageResource(R.drawable.paper);
                    menet();

            }
        });

    }

    public void computerRandom(){
        randSzam = rand.nextInt(3);
        if (randSzam == 0){
            imgComputer.setImageResource(R.drawable.paper);
            compKepAzon = 1;
        }else if(randSzam == 1){
            imgComputer.setImageResource(R.drawable.rock);
            compKepAzon = 0;
        }else if(randSzam == 2){
            imgComputer.setImageResource(R.drawable.scissors);
            compKepAzon = 2;
        }
    }
    // 0-ko 1-papir 2-ollo
    public void gyoztes(){
        if((compKepAzon == 0 && playerKepAzon == 2) ||(compKepAzon== 1 && playerKepAzon == 0) || (compKepAzon == 2 && playerKepAzon == 1)){
            compPoint++;
            if (compPoint == 3 || playerPoint == 3){
                felirat();
            }else {
                pointComputer.setText(String.valueOf(compPoint));
                Toast.makeText(MainActivity.this, "Computer nyert", Toast.LENGTH_SHORT).show();
            }
        }else if((playerKepAzon == 0 && compKepAzon == 2) || (playerKepAzon == 1 && compKepAzon == 0) || (playerKepAzon == 2 && compKepAzon == 1)){
            playerPoint++;
            if (compPoint == 3 || playerPoint == 3){
                felirat();
            }else {
            pointPlayer.setText(String.valueOf(playerPoint));
            Toast.makeText(MainActivity.this, "Te nyertél", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void menet(){
        computerRandom();
        gyoztes();
    }

    public void ujJatek(){
        init();
        pointPlayer.setText(String.valueOf(0));
        pointComputer.setText(String.valueOf(0));
    }

    public void felirat(){
            if (compPoint == 3){
                alertDialog.setTitle("Vesztettél!");
                alertDialog.create();
                alertDialog.show();
            }
            else {
                alertDialog.setTitle("Győztél!");
                alertDialog.create();
                alertDialog.show();
            }
    }

    public void init(){
        buttonKo = findViewById(R.id.buttonKo);
        buttonOllo = findViewById(R.id.buttonOllo);
        buttonPapir = findViewById(R.id.buttonPapir);
        imgPlayer = findViewById(R.id.playerImage);
        imgComputer = findViewById(R.id.computerImage);
        pointComputer = findViewById(R.id.computerPont);
        pointPlayer = findViewById(R.id.playerPont);
        rand = new Random();
        compPoint = 0;
        playerPoint = 0;
        compKepAzon = 0;
        playerKepAzon = 0;
        alertDialog = new AlertDialog.Builder(this).
                setTitle("Játék vége").
                setMessage("Szeretnél új játékot?").
                setCancelable(false).
                setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                    }
                }).
                setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
    }
}