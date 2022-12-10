package ru.naa.citydb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.naa.citydb.R
import ru.naa.citydb.databinding.FragmentCityEditBinding
import ru.naa.citydb.model.CityRepository
import ru.naa.citydb.room.CityEntity
import ru.naa.citydb.room.LocalDb
import kotlin.properties.Delegates

class CityEditFragment : Fragment() {

    lateinit var binding: FragmentCityEditBinding
    var cityId by Delegates.notNull<Int>()
    var cityKey by Delegates.notNull<String>()
    var cityName by Delegates.notNull<String>()
    var cityType by Delegates.notNull<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCityEditBinding.inflate(inflater)

        cityId = requireArguments().getInt(CITY_ID,-1)
        cityKey = requireArguments().getString(CITY_KEY,"")
        cityName = requireArguments().getString(CITY_NAME,"")
        cityType = requireArguments().getString(CITY_TYPE,"")

        binding.tvCityid.setText(cityKey.toString())//setText(cityKey)
        binding.tvCityname.setText(cityName.toString())
        binding.tvType.setText(cityType.toString())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveBtn.setOnClickListener {
            val key = binding.tvCityid.text.toString()
            val name = binding.tvCityname.text.toString()
            val type = binding.tvType.text.toString()

            val db = Room.databaseBuilder(requireContext(),LocalDb::class.java,"db")
                .build()
            val repository = CityRepository(db)

            GlobalScope.launch {
                if (cityId == -1)
                    repository.insert(CityEntity( key = key, name = name, type = type))
                else repository.update(CityEntity( id = cityId!!, key = key, name = name, type = type))
            }
            Navigation.findNavController(it).navigate(R.id.action_cityEditFragment_to_cityListFragment)
        }
    }

    companion object {
        private const val CITY_ID = "cityId"
        private const val CITY_KEY = "cityKey"
        private const val CITY_NAME = "cityName"
        private const val CITY_TYPE = "cityType"

        fun createArguments(cityId: Int = -1, cityKey:String = "", cityName: String = "", cityType: String = ""): Bundle {
            return bundleOf(CITY_ID to cityId, CITY_KEY to cityKey, CITY_NAME to cityName, CITY_TYPE to cityType)
        }
    }
}