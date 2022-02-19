package com.example.weatherapp.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.fiveday.FiveDayFragment
import com.squareup.picasso.Picasso
import java.time.DayOfWeek
import java.util.*

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        //default city
        viewModel.fetchWeatherByCity("Bishkek")
        binding.cityTv.text = "tokyo"

        observeWeather()
        setupSearch()

        binding.fiveDayBtn.setOnClickListener{
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, FiveDayFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
    private fun loadWeatherIcon(imageUrl: String, imageView: ImageView){
        Picasso.get().load("http://openweathermap.org/img/wn/$imageUrl@2x.png").into(imageView)
    }
    private fun loadWeatherIconGlide(imageUrl: String, imageView: ImageView){
        Glide.with(this).load("http://openweathermap.org/img/wn/$imageUrl@2x.png").into(imageView)
    }

    private fun setupSearch() {
        binding.searchBtn.setOnClickListener {
            val cityFromInput: String = binding.cityEt.text.toString()
            viewModel.fetchWeatherByCity(cityFromInput)
            viewModel.fetchFiveDayWeather(cityFromInput)
        }
    }

    private fun observeWeather() {
        viewModel.weather.observe(viewLifecycleOwner, Observer { weather ->
            binding.tempTv.text = getString(R.string.temp_with_symbol, weather.main.temp.toString())
            binding.cityTv.text = weather.name
            binding.WeatherDescription.text = weather.weather[0].description
            binding.windTv.text = getString(R.string.speed_per_second, weather.wind.speed.toString())
            binding.humidityTv.text = getString(R.string.humidity, weather.main.humidity.toString())
            //loadWeatherIcon(weather.weather[0].icon, binding.weatherIv)
            loadWeatherIconGlide(weather.weather[0].icon, binding.weatherIv)
            val visibilityInKm: Int = viewModel.metresToKilometres(weather.visibility)
            binding.visibilityTv.text = getString(R.string.visibility, visibilityInKm.toString())
        })
    }
}
















