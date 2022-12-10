package ru.naa.citydb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.room.Room
import ru.naa.citydb.adapter.CityListAdapter
import ru.naa.citydb.R
import ru.naa.citydb.databinding.FragmentCityListBinding
import ru.naa.citydb.model.CityRepository
import ru.naa.citydb.room.LocalDb

class CityListFragment : Fragment() {

    lateinit var binding: FragmentCityListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(requireContext(),LocalDb::class.java,"db")
            .build()
        val repository = CityRepository(db)
        val adapter = CityListAdapter(db)

        repository.cities.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

        binding.rvCityList.adapter = adapter

        binding.addCityBtn.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_cityListFragment_to_cityEditFragment,CityEditFragment.createArguments(-1))
        }

    }
}