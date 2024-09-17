package com.nieves.nieves_pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Details extends AppCompatActivity {
    ImageView ivPokemonImage;
    TextView tvPokemonID;
    TextView tvPokemonName;
    TextView tvPokemonType;
    ImageView ivTypeLogo;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivPokemonImage = findViewById(R.id.ivPokemonImage);
        tvPokemonID = findViewById(R.id.tvPokemonID);
        tvPokemonName = findViewById(R.id.tvPokemonName);
        tvPokemonType = findViewById(R.id.tvPokemonType);
        ivPokemonImage = findViewById(R.id.ivPokemonImage);
        ivTypeLogo = findViewById(R.id.ivTypeLogo);
        tvDescription = findViewById(R.id.tvDescription);

        Intent intent = getIntent();
        int pokemonId = intent.getIntExtra("pokemonId", -1); // Use "pokemonId"

        if (pokemonId != -1) {
            String pokemonName = intent.getStringExtra("pokemonName");
            String pokemonType = intent.getStringExtra("pokemonType");
            int pokemonImageResId = intent.getIntExtra("pokemonImage", R.drawable.pokeball);
            int pokemonLogoResId = intent.getIntExtra("pokemonLogo", R.drawable.pokeball);
            String pokemonDescription = intent.getStringExtra("pokemonDescription");

            // Set the values to the TextViews and ImageView
            tvPokemonID.setText(String.valueOf(pokemonId));
            tvPokemonName.setText(pokemonName);
            tvPokemonType.setText(pokemonType);
            ivPokemonImage.setImageResource(pokemonImageResId);
            ivTypeLogo.setImageResource(pokemonLogoResId);
            tvDescription.setText(pokemonDescription);

        }
        ivPokemonImage.setOnClickListener(v -> {
            finish();
        });

    }
}