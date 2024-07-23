package com.example.midtermq2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

class CitySelectorFragment : Fragment() {

    private lateinit var citySpinner: Spinner
    private lateinit var selectButton: Button
    private lateinit var selectedCityTextView: TextView
    private var selectedCity: String? = null
    private var cityFacts: Array<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_city_selector, container, false)

        citySpinner = view.findViewById(R.id.citySpinner)
        selectButton = view.findViewById(R.id.selectButton)
        selectedCityTextView = view.findViewById(R.id.selectedCityTextView)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            citySpinner.adapter = adapter
        }

        // Load city facts from resources
        cityFacts = resources.getStringArray(R.array.city_facts_array)

        // Set listeners
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCity = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCity = null
            }
        }

        selectButton.setOnClickListener {
            val position = citySpinner.selectedItemPosition
            val fact = cityFacts?.get(position)
            selectedCityTextView.text = "Fact about $selectedCity: $fact"
        }

        return view
    }
}
