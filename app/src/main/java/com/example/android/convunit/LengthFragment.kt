package com.example.android.convunit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.adapters.TextViewBindingAdapter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_length_.*

class LengthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_length_, container, false)
        val spinner: Spinner = root.findViewById(R.id.length_input_spinner)
        ArrayAdapter.createFromResource(context!!, R.array.Length, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        return root
    }


}