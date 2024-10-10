package com.example.rps;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    //gombok
    private ImageView buttonOllo;
    private ImageView buttonKo;
    private ImageView buttonPapir;

    //megjelenites
    private ImageView imgPlayer;
    private ImageView imgComputer;

    //pontok
    private int compPoint;
    private int playerPoint;

    //comp random
    private Random rand;
    private int randSzam;

    //jatek vege
    private AlertDialog.Builder alertDialog;

    //0-ko 1-papir 2-ollo
    private int compKepAzon;
    private int playerKepAzon;

    //dontetlen
    private int dontetlen;
    private TextView dontetlenText;

    //szivek
    private ImageView pSziv1;
    private ImageView pSziv2;
    private ImageView pSziv3;
    private ImageView cSziv1;
    private ImageView cSziv2;
    private ImageView cSziv3;

    //kod kezdete
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
            if (playerPoint == 1){
                pSziv1.setImageResource(R.drawable.heart1);
                felirat();
            }else {
                szivVesztes("Player");
                Toast.makeText(MainActivity.this, "Computer nyert", Toast.LENGTH_SHORT).show();
            }
        }else if((playerKepAzon == 0 && compKepAzon == 2) || (playerKepAzon == 1 && compKepAzon == 0) || (playerKepAzon == 2 && compKepAzon == 1)){
            if (compPoint == 1){
                cSziv3.setImageResource(R.drawable.heart1);
                felirat();
            }else {
                szivVesztes("Computer");
                Toast.makeText(MainActivity.this, "Te nyertél", Toast.LENGTH_SHORT).show();
            }
        }else if(playerKepAzon == compKepAzon){
            dontetlen++;
            dontetlenText.setText(String.valueOf(dontetlen));
        }
    }

    public void menet(){
        computerRandom();
        gyoztes();
    }

    public void ujJatek(){
        init();
        pSziv1.setImageResource(R.drawable.heart2);
        pSziv2.setImageResource(R.drawable.heart2);
        pSziv3.setImageResource(R.drawable.heart2);
        cSziv1.setImageResource(R.drawable.heart2);
        cSziv2.setImageResource(R.drawable.heart2);
        cSziv3.setImageResource(R.drawable.heart2);
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

    public void szivVesztes(String vesztes ){
        if (vesztes == "Player"){
            if (playerPoint == 3){
                pSziv3.setImageResource(R.drawable.heart1);
                playerPoint--;
            }
            else if (playerPoint == 2) {
                pSziv2.setImageResource(R.drawable.heart1);
                playerPoint--;
            }
        }
        else if (vesztes == "Computer"){
            if (compPoint == 3){
                cSziv1.setImageResource(R.drawable.heart1);
                compPoint--;
            }
            else if (compPoint == 2){
                cSziv2.setImageResource(R.drawable.heart1);
                compPoint--;
            }
        }

    }

    public void init(){
        buttonKo = findViewById(R.id.buttonKo);
        buttonOllo = findViewById(R.id.buttonOllo);
        buttonPapir = findViewById(R.id.buttonPapir);
        imgPlayer = findViewById(R.id.playerImage);
        imgComputer = findViewById(R.id.computerImage);
        dontetlenText = findViewById(R.id.textDontetlen);
        pSziv1 = findViewById(R.id.playersziv1);
        pSziv2 = findViewById(R.id.playersziv2);
        pSziv3 = findViewById(R.id.playersziv3);
        cSziv1 = findViewById(R.id.compsziv1);
        cSziv2 = findViewById(R.id.compsziv2);
        cSziv3 = findViewById(R.id.compsziv3);
        rand = new Random();
        compPoint = 3;
        playerPoint = 3;
        compKepAzon = 0;
        playerKepAzon = 0;
        dontetlen = 0;
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