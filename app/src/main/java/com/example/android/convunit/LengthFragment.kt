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
import kotlinx.android.synthetic.main.fragment_length_.*

class LengthFragment : Fragment() {

    lateinit var input: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_length_, container, false)
        val spinner: Spinner = root.findViewById(R.id.length_input_spinner)
        val reset = root.findViewById<Button>(R.id.lengthreset)
        reset.isEnabled = false
        reset.isClickable = false
        reset.setBackgroundColor(Color.parseColor("#ABB8C7"))
        ArrayAdapter.createFromResource(
            context!!, R.array.Length, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        reset.setOnClickListener {
            if (reset.isEnabled) {
                lengthInput.text.clear()
            }
        }
        input = root.findViewById(R.id.lengthInput)

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
                lengthInput.setText("")
            }

        }
        input.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lengthOutput.text = "None"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((lengthInput.text).toString() != "") {
                    when (length_input_spinner.selectedItem.toString()) {
                        "m->cm" -> {

                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = lengthInput.text.toString().toDouble()
                            ans *= 100
                            lengthOutput.text = ("%.4f".format(ans)) + " cm"
                            lengthOutput.visibility = View.VISIBLE
                        }
                        "km->m" -> {
                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = lengthInput.text.toString().toDouble()
                            ans *= 1000
                            lengthOutput.text = ("%.4f".format(ans)) + " m"
                            lengthOutput.visibility = View.VISIBLE

                        }

                        "km->cm" -> {
                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = lengthInput.text.toString().toDouble()
                            ans *= 100000
                            lengthOutput.text = ("%.4f".format(ans)) + " cm"
                            lengthOutput.visibility = View.VISIBLE
                        }
                        else -> {

                            lengthOutput.text = "None"
                            lengthOutput.visibility = View.VISIBLE
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