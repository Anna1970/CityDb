package ru.naa.citydb.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.naa.citydb.R
import ru.naa.citydb.databinding.ItemCityBinding
import ru.naa.citydb.model.CityRepository
import ru.naa.citydb.room.CityEntity
import ru.naa.citydb.room.LocalDb
import ru.naa.citydb.ui.CityEditFragment

class CityListAdapter(private val db : LocalDb) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    var cities: List<CityEntity> = mutableListOf()


    fun updateList(cities: List<CityEntity>){
        this.cities = cities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.cityData = cities[position]

        holder.binding.editBtn.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_cityListFragment_to_cityEditFragment,
                    CityEditFragment.createArguments(cities[position].id,
                        cities[position].key,
                        cities[position].name,
                        cities[position].type))
        }

        holder.binding.deleteBtn.setOnClickListener {

            val repository = CityRepository(db)
            GlobalScope.launch {
                repository.delete(cities[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemCityBinding.bind(view)
    }
}