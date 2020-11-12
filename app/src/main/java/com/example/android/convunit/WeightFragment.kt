package com.example.android.convunit

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_weight_.*


class WeightFragment : Fragment() {

    lateinit var input: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_weight_, container, false)
        val spinner: Spinner = root.findViewById(R.id.weight_input_spinner)
        val reset = root.findViewById<Button>(R.id.weightreset)
        reset.isEnabled = false
        reset.isClickable = false
        reset.setBackgroundColor(Color.parseColor("#ABB8C7"))
        ArrayAdapter.createFromResource(
            context!!, R.array.Weight, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        reset.setOnClickListener {
            if (reset.isEnabled) {
                weightInput.text.clear()
            }
        }
        input = root.findViewById(R.id.weightInput)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nothing
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                weightInput.setText("")
            }

        }
        input.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                weightOutput.text = "None"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((weightInput.text).toString() != "") {
                    when (weight_input_spinner.selectedItem.toString()) {
                        "kg->g" -> {

                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = weightInput.text.toString().toDouble()
                            ans *= 1000
                            weightOutput.text = ("%.4f".format(ans)) + " g"
                            weightOutput.visibility = View.VISIBLE
                        }
                        "pound->kg" -> {
                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = weightInput.text.toString().toDouble()
                            ans *= 0.453592
                            weightOutput.text = ("%.2f".format(ans)) + " kg"
                            weightOutput.visibility = View.VISIBLE

                        }
                        "quintal->pound" -> {
                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = weightInput.text.toString().toDouble()
                            ans *= 220.462
                            weightOutput.text = ("%.2f".format(ans)) + " pound"
                            weightOutput.visibility = View.VISIBLE

                        }
                        else -> {

                            weightOutput.text = "None"
                            weightOutput.visibility = View.VISIBLE
                        }
                    }
                } else {
                    reset.isEnabled = false
                    reset.isClickable = false
                    reset.setBackgroundColor(Color.parseColor("#ABB8C7"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // left blank intentionally
            }

        })
        return root
    }
}