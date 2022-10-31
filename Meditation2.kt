package com.example.mentalhealth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mentalhealth.Fragment.*
import com.example.mentalhealth.player.MainActivity
import mentalhealth.R

class Meditation2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_meditation2, container, false)

        val music = view.findViewById<TextView>(R.id.music)
        val video = view.findViewById<TextView>(R.id.videos)
//        val podcast = view.findViewById<TextView>(R.id.podcast)
        val upload = view.findViewById<TextView>(R.id.upload)



        val home = Home()
//        val contact = Contact()
        val meditation = Meditation()
        val profile = Profile()
        val resources = Resources()


        setCurrentFragment(meditation)


        music.setOnClickListener {
//            if (music.isFocused) {
//                music.setBackgroundResource(R.drawable.button_background)
//            }
            val music = Meditation()
            val transaction: FragmentTransaction =
                requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout2, music)
            transaction.commit()
        }
        video.setOnClickListener {

//            if (video.isFocused) {
//                video.setBackgroundResource(R.drawable.button_background)
//            }else
//            {
//                video.setBackgroundResource(R.drawable.answer_bg)
//            }
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
//        podcast.setOnClickListener {
////            val podcast = Podcasts()
////            val transaction: FragmentTransaction =
////                requireFragmentManager().beginTransaction()
////            transaction.replace(R.id.frlayout2, podcast)
////            transaction.commit()
//        }
        upload.setOnClickListener {
            val upload = Uploads()
            val transaction: FragmentTransaction =
                requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout2, upload)
            transaction.commit()
        }
        return view
    }
    private fun setCurrentFragment(meditation: Meditation) {
        val transaction: FragmentTransaction =
            requireFragmentManager().beginTransaction()
        transaction.replace(R.id.frlayout2, meditation)
        transaction.commit()
    }
}

