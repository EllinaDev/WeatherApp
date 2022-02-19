package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FiveDayItemBinding
import com.example.weatherapp.network.model.forecast.FiveDayForecast
import java.text.SimpleDateFormat
import java.util.*

    class FiveDayAdapter : RecyclerView.Adapter<FiveDayAdapter.FiveDayViewHolder>() {

        private var items: List<FiveDayForecast> = emptyList()

        fun setItems(forecast: List<FiveDayForecast>) {
            items = forecast.take(5)
            notifyDataSetChanged()
        }

        private fun getWeekDay(): List<String> {
            val sdf = SimpleDateFormat("EEEE", Locale.US)
            val weekDays = mutableListOf<String>()
            for (i in 0..4) {
                val calendar: Calendar = GregorianCalendar()
                calendar.add(Calendar.DATE, i)
                val day = sdf.format(calendar.time)
                weekDays.add(day)
            }
            return weekDays
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDayViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return FiveDayViewHolder(FiveDayItemBinding.inflate(inflater, parent, false))
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: FiveDayViewHolder, position: Int) {
            holder.bind(items[position], getWeekDay()[position])
        }

        class FiveDayViewHolder(private val binding: FiveDayItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(forecast: FiveDayForecast, weekDay: String) {
                binding.dayOfWeek.text = weekDay
                binding.maxTempFd.text = forecast.main.tempmax.toString()
                binding.minTempFd.text = forecast.main.tempMin.toString()
            }
        }
    }