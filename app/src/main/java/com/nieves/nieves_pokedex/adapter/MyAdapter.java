package com.nieves.nieves_pokedex.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nieves.nieves_pokedex.Details;
import com.nieves.nieves_pokedex.R;
import com.nieves.nieves_pokedex.model.Pokemon;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Pokemon> pokemonList;

    public MyAdapter(List<Pokemon> pokemon) { this.pokemonList = pokemon;}

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
       Pokemon pokemon = pokemonList.get(holder.getAdapterPosition());
      holder.bind(pokemon);

    }
    public int getItemCount() {
        return pokemonList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvPokemonID;
        ImageView ivPokemonImage;
        TextView tvPokemonName;
        TextView tvPokemonType;
        ImageView ivTypeLogo;
        TextView tvDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvPokemonID = itemView.findViewById(R.id.tvPokemonID);
            ivPokemonImage = itemView.findViewById(R.id.ivPokemonImage);
            tvPokemonName = itemView.findViewById(R.id.tvPokemonName);
            tvPokemonType = itemView.findViewById(R.id.tvPokemonType);
            ivTypeLogo = itemView.findViewById(R.id.ivTypeLogo);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
        public void bind(Pokemon pokemon) {
            tvPokemonID.setText(String.valueOf(pokemon.getId()));
            ivPokemonImage.setImageResource(pokemon.getImageResId());
            tvPokemonName.setText(pokemon.getName());
            tvPokemonType.setText(pokemon.getType());
            ivTypeLogo.setImageResource(pokemon.getImageLogo());
            tvDescription.setText(pokemon.getDescription());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Details.class);
                    intent.putExtra("pokemonId", pokemon.getId());
                    intent.putExtra("pokemonName", pokemon.getName());
                    intent.putExtra("pokemonType", pokemon.getType());
                    intent.putExtra("pokemonImage", pokemon.getImageResId());
                    intent.putExtra("pokemonLogo", pokemon.getImageLogo());
                    intent.putExtra("pokemonDescription", pokemon.getDescription());
                    itemView.getContext().startActivity(intent);
                }});
        }

    }
}
