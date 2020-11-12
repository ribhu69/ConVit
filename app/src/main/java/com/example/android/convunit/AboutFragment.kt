package com.example.android.convunit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class About_Fragment : Fragment() {

    private lateinit var github: FloatingActionButton
    private lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_about_, container, false)
        github = root.findViewById(R.id.github)
        github.setOnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/ribhu69/ConVit")
            startActivity(intent)
        }
        return root
    }


}