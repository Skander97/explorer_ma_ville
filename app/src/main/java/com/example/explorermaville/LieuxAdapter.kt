package com.example.explorermaville

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso


class LieuxAdapter : RecyclerView.Adapter<LieuxAdapter.LieuxViewHolder>() {

    private val lieuxList: MutableList<FoursquareVenue> = mutableListOf()

    // Méthode pour mettre à jour les données de l'adaptateur
    fun setData(lieux: List<FoursquareVenue>) {
        lieuxList.clear()
        lieuxList.addAll(lieux)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LieuxViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_lieux, parent, false)
        return LieuxViewHolder(view)
    }

    override fun onBindViewHolder(holder: LieuxViewHolder, position: Int) {
        val lieu = lieuxList[position]

        // Remplir les données du lieu dans les éléments de la vue
        holder.nomTextView.text = lieu.name
        holder.latitudeTextView.text = "Latitude : ${lieu.location.latitude}"
        holder.longitudeTextView.text = "Longitude : ${lieu.location.longitude}"
        holder.addressTextView.text = "Adresse : ${lieu.location.address ?: "Non disponible"}"
        holder.formattedAddressTextView.text = "Adresse formatée : ${lieu.location.formatted_address ?: "Non disponible"}"
        holder.localityTextView.text = "Localité : ${lieu.location.locality ?: "Non disponible"}"
        holder.regionTextView.text = "Région : ${lieu.location.region ?: "Non disponible"}"
        if (lieu.categories.isNotEmpty()) {
            holder.categoriesTextView.text = "Catégories : ${lieu.categories.joinToString(", ") { it.name }}"
        } else {
            holder.categoriesTextView.text = "Catégories : Non disponible"
        }
        Picasso.get().load("${lieu.categories.firstOrNull()?.icon?.prefix}88${lieu.categories.firstOrNull()?.icon?.suffix}")
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(holder.iconImageView)

    }

    override fun getItemCount(): Int {
        return lieuxList.size
    }

    // Classe ViewHolder pour maintenir les références aux éléments de la vue
    inner class LieuxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        val nomTextView: TextView = itemView.findViewById(R.id.nomTextView)
        val latitudeTextView: TextView = itemView.findViewById(R.id.latitudeTextView)
        val longitudeTextView: TextView = itemView.findViewById(R.id.longitudeTextView)
        val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        val formattedAddressTextView: TextView = itemView.findViewById(R.id.formattedAddressTextView)
        val localityTextView: TextView = itemView.findViewById(R.id.localityTextView)
        val regionTextView: TextView = itemView.findViewById(R.id.regionTextView)
        val categoriesTextView: TextView = itemView.findViewById(R.id.categoriesTextView)
    }
}