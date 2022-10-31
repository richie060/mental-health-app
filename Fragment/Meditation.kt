package com.example.mentalhealth.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.mentalhealth.VL.MusicActivity
import mentalhealth.R


class Meditation: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.meditation, container,false)
        val cardViewSad = view.findViewById<CardView>(R.id.cardviewSad)
        val cardViewAxiety = view.findViewById<CardView>(R.id.cardviewAxiety)
        val cardviewHappy = view.findViewById<CardView>(R.id.cardviewHappy)
        val cardviewCalm = view.findViewById<CardView>(R.id.cardviewCalm)

        cardViewSad.setOnClickListener {
            val intent = Intent(requireContext(), MusicActivity::class.java)
            startActivity(intent)
        }
        cardViewAxiety.setOnClickListener {
            val intent = Intent(requireContext(), MusicActivity::class.java)
            startActivity(intent)
        }
        cardviewCalm.setOnClickListener {
            val intent = Intent(requireContext(), MusicActivity::class.java)
            startActivity(intent)
        }
        cardviewHappy.setOnClickListener {
            val intent = Intent(requireContext(), MusicActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}