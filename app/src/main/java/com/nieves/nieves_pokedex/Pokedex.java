package com.nieves.nieves_pokedex;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nieves.nieves_pokedex.adapter.MyAdapter;
import com.nieves.nieves_pokedex.model.Pokemon;

import java.util.List;

public class Pokedex extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;

    List<Pokemon> pokemonList = List.of(
            new Pokemon(1,"Bulbasaur", "Grass","Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger", R.drawable.bulbasaur,R.drawable.grass),
            new Pokemon(25,"Pikachu", "Electric", "While sleeping, it generates electricity in the sacs in its cheeks. If it’s not getting enough sleep, it will be able to use only weak electricity.",R.drawable.pikachu,R.drawable.electric),
            new Pokemon(133,"Eevee", "Normal","Its genes are easily influenced by its surroundings. Even its face starts to look like that of its Trainer.", R.drawable.eevee,R.drawable.normal),
            new Pokemon(143,"Snorlax", "Normal","It has no interest in anything other than eating. Even if you climb up on its stomach while it’s napping, it doesn’t seem to mind at all!", R.drawable.snorlax,R.drawable.normal),
            new Pokemon(152,"Chikorita", "Grass","In battle, Chikorita waves its leaf around to keep the foe at bay. However, a sweet fragrance also wafts from the leaf, becalming the battling Pokémon and creating a cozy, friendly atmosphere all around.",R.drawable.chikorita,R.drawable.grass),
            new Pokemon(393,"Piplup", "Water","Because it is very proud, it hates accepting food from people. Its thick down guards it from cold.", R.drawable.piplup,R.drawable.water));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokedex);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //initialize adapter and set it to the RecyclerView
        adapter = new MyAdapter(pokemonList);
        recyclerView.setAdapter(adapter);


    }
}