package com.example.android.convunit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
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
        ArrayAdapter.createFromResource(
            context!!, R.array.Length, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        input = root.findViewById(R.id.lengthInput)
/*
        ans.setOnClickListener {
            if((lengthInput.text).isNotEmpty())
            {
                when(length_input_spinner.selectedItem.toString())
                {
                    "kmph -> ms" -> {
                        var ans= lengthInput.text.toString().toDouble()
                        ans /= 3.6
                        lengthOutput.text = ("%.2f".format(ans))
                        lengthOutput.visibility= View.VISIBLE
                    }
                    "ms -> kmph" -> {
                        var ans= lengthInput.text.toString().toDouble()
                        ans *= 3.6
                        lengthOutput.text = ("%.2f".format(ans))
                        lengthOutput.visibility= View.VISIBLE

                    }
                    else-> { lengthOutput.text="None"
                        lengthOutput.visibility=View.VISIBLE
                    }
                }
            }
            else
            {
                Toast.makeText(activity, "Please Enter Value", Toast.LENGTH_SHORT).show()
            }
        }*/

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
                if ((lengthInput.text).isNotEmpty()) {
                    when (length_input_spinner.selectedItem.toString()) {
                        "kmph -> ms" -> {
                            var ans = lengthInput.text.toString().toDouble()
                            ans /= 3.6
                            lengthOutput.text = ("%.2f".format(ans)) + " ms"
                            lengthOutput.visibility = View.VISIBLE
                        }
                        "ms -> kmph" -> {
                            var ans = lengthInput.text.toString().toDouble()
                            ans *= 3.6
                            lengthOutput.text = ("%.2f".format(ans)) + " kmph"
                            lengthOutput.visibility = View.VISIBLE

                        }
                        else -> {
                            lengthOutput.text = "None"
                            lengthOutput.visibility = View.VISIBLE
                        }
                    }
                } else {
                    lengthOutput.text = "None"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // left blank intentionally
            }
        })
        return root
    }
}