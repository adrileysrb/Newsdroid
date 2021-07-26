package com.example.news.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.example.news.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

public class SelectionActivity extends AppCompatActivity {
    int contador = 5;
    MaterialButton button;
    LottieAnimationView animationView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        animationView2 = findViewById(R.id.textView2);
        animationView2.setMinFrame(88);
        int yourColor = ContextCompat.getColor(this, R.color.primaryTextColor);

        SimpleColorFilter filter = new SimpleColorFilter(yourColor);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        animationView2.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
        animationView2.setPadding(-320, -320, -320, -320);
        button = findViewById(R.id.materialButton);
        button.setCheckable(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (button.isCheckable()) {
                    startActivity(new Intent(SelectionActivity.this, MainActivity.class));
                    finish();
                }

                //startActivity(new Intent(SelectionActivity.this, MainActivity.class));
            }
        });
        button.setText("Escolha " + literal(contador) + " tópicos para prosseguir");
        button.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor2));


        Chip esportes = findViewById(R.id.esportes_chip);
        Chip politica = findViewById(R.id.politica_chip);
        Chip saude = findViewById(R.id.saude_chip);
        Chip jogos = findViewById(R.id.jogos_chip);
        Chip empreendedorismo = findViewById(R.id.empreendedorismo_chip);
        Chip esports = findViewById(R.id.esports_chip);
        Chip humor = findViewById(R.id.humor_chip);
        Chip tecnologia = findViewById(R.id.tecnologia_chip);
        Chip cinema = findViewById(R.id.cinema_chip);
        Chip celebridades = findViewById(R.id.celebridades_chip);
        Chip futebol = findViewById(R.id.futebol_chip);
        Chip viagem = findViewById(R.id.viagem_chip);
        Chip ciencia = findViewById(R.id.ciencia_chip);

        config(esportes, R.drawable.ic_baseline_sports_football_24);
        config(politica, R.drawable.account_supervisor);
        config(saude, R.drawable.ic_baseline_healing_24);
        config(jogos, R.drawable.ic_baseline_videogame_asset_24);
        config(empreendedorismo, R.drawable.ic_baseline_business_center_24);
        config(esports, R.drawable.ic_baseline_games_24);
        config(humor, R.drawable.ic_baseline_sentiment_very_satisfied_24);
        config(tecnologia, R.drawable.cellphone);
        config(cinema, R.drawable.ic_baseline_local_movies_24);
        config(celebridades, R.drawable.star_outline);
        config(futebol, R.drawable.soccer);
        config(viagem, R.drawable.ic_baseline_card_travel_24);
        config(ciencia, R.drawable.flask_outline);

        check(esportes, R.drawable.ic_baseline_sports_football_24);
        check(politica, R.drawable.account_supervisor);
        check(saude, R.drawable.ic_baseline_healing_24);
        check(jogos, R.drawable.ic_baseline_videogame_asset_24);
        check(empreendedorismo, R.drawable.ic_baseline_business_center_24);
        check(esports, R.drawable.ic_baseline_games_24);
        check(humor, R.drawable.ic_baseline_sentiment_very_satisfied_24);
        check(tecnologia, R.drawable.cellphone);
        check(cinema, R.drawable.ic_baseline_local_movies_24);
        check(celebridades, R.drawable.star_outline);
        check(futebol, R.drawable.soccer);
        check(viagem, R.drawable.ic_baseline_card_travel_24);
        check(ciencia, R.drawable.flask_outline);
    }

    private void config(Chip chip, int icon) {
        chip.setCheckable(true);
        chip.setChecked(false);
        Drawable drawable = getResources().getDrawable(icon);
        ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();

        drawable.setTint(getResources().getColor(R.color.chipson));
        chipDrawable.setChipBackgroundColorResource(R.color.primaryDarkColor);
        chip.setTextColor(getResources().getColor(R.color.chipson));

        chip.setCheckedIcon(drawable);
    }

    private void check(Chip chip, int icon) {
        chip.setCheckable(true);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chip.isChecked()) {
                    contador++;
                    button.setText("Escolha mais " + literal(contador) + " tópicos para prosseguir");
                    button.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor2));
                    chip.setChecked(false);
                    button.setCheckable(false);
                    Drawable drawable = getResources().getDrawable(icon);
                    ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();

                    drawable.setTint(getResources().getColor(R.color.chipson));
                    chipDrawable.setChipBackgroundColorResource(R.color.primaryDarkColor);
                    chip.setTextColor(getResources().getColor(R.color.chipson));

                    chip.setCheckedIcon(drawable);

                } else if (contador >= 1) {
                    contador--;
                    if (contador == 0) {
                        button.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                        button.setText("Iniciar");
                        button.setCheckable(true);
                        animationView2.setMinFrame(20);
                        animationView2.playAnimation();
                    }

                    else {

                        button.setBackgroundColor(getResources().getColor(R.color.primaryDarkColor2));
                        if (contador > 1) {
                            button.setText("Escolha mais " + literal(contador) + " tópicos para prosseguir");
                        } else {
                            button.setText("Escolha mais " + literal(contador) + " tópico para prosseguir");
                        }
                    }
                    chip.setChecked(true);
                    Drawable drawable = getResources().getDrawable(icon);
                    ChipDrawable chipDrawable = (ChipDrawable) chip.getChipDrawable();

                    drawable.setTint(getResources().getColor(R.color.black));
                    chipDrawable.setChipBackgroundColorResource(R.color.chipson_2);
                    chip.setTextColor(getResources().getColor(R.color.black));

                    chip.setCheckedIcon(drawable);

                }
            }
        });
    }

    private String literal(int number) {
        if (number == 1) return "um";
        else if (number == 2) return "dois";
        else if (number == 3) return "três";
        else if (number == 4) return "quatro";
        else return "cinco";
    }
}