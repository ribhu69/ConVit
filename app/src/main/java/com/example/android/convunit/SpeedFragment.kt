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
import kotlinx.android.synthetic.main.fragment_speed_.*

class SpeedFragment : Fragment() {

    lateinit var input: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_speed_, container, false)
        val spinner: Spinner = root.findViewById(R.id.speed_input_spinner)
        val reset = root.findViewById<Button>(R.id.speedreset)
        reset.isEnabled = false
        reset.isClickable = false
        reset.setBackgroundColor(Color.parseColor("#ABB8C7"))
        ArrayAdapter.createFromResource(
            context!!, R.array.Speed, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        reset.setOnClickListener {
            if (reset.isEnabled) {
                speedInput.text.clear()
            }
        }
        input = root.findViewById(R.id.speedInput)

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
                speedInput.setText("")
            }

        }
        input.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                speedOutput.text = "None"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if ((speedInput.text).toString() != "") {
                    when (speed_input_spinner.selectedItem.toString()) {
                        "kmph->ms" -> {

                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = speedInput.text.toString().toDouble()
                            ans /= 3.6
                            speedOutput.text = ("%.4f".format(ans)) + " ms"
                            speedOutput.visibility = View.VISIBLE
                        }
                        "ms->kmph" -> {
                            reset.isEnabled = true
                            reset.isClickable = true
                            reset.setBackgroundColor(Color.parseColor("#485461"))
                            var ans = speedInput.text.toString().toDouble()
                            ans *= 3.6
                            speedOutput.text = ("%.2f".format(ans)) + " kmph"
                            speedOutput.visibility = View.VISIBLE

                        }
                        else -> {

                            speedOutput.text = "None"
                            speedOutput.visibility = View.VISIBLE
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