package com.example.mentalhealth.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.mentalhealth.player.MainActivity
import com.example.mentalhealth.podcast.PodcastsActivity
import com.example.mentalhealth.resources.MainActivityRs
import com.example.mentalhealth.stories.StroryList
import com.google.firebase.database.*
import mentalhealth.R
class Resources: Fragment() {

//    private lateinit var mAuth: FirebaseAuth
//    private lateinit var mDbRef: DatabaseReference
//    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.resources, container, false)
//        var addresource =  view.findViewById<FloatingActionButton>(R.id.addresource)


        val booksandnovels = view.findViewById<CardView>(R.id.booksandnovel)
        val cardviewSongs = view.findViewById<CardView>(R.id.cardviewSongs)
        val cardviewVideos = view.findViewById<CardView>(R.id.cardviewMeditation)
        val cardviewStories = view.findViewById<CardView>(R.id.cardviewstories)

        booksandnovels.setOnClickListener {
            val intent = Intent(requireContext(), MainActivityRs::class.java)
            startActivity(intent)
        }

        cardviewSongs.setOnClickListener {
            val intent = Intent(requireContext(), PodcastsActivity::class.java)
            startActivity(intent)
        }
        cardviewVideos.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        cardviewStories.setOnClickListener {

            val intent = Intent(requireContext(), StroryList::class.java)
            startActivity(intent)
//            val podcasts =   Podcasts()
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.frlayout, podcasts)
//            transaction.commit()
        }
//        addresource.setOnClickListener {
//            val intent = Intent(requireContext(), uploadfile::class.java)
//            startActivity(intent)
//        }


//        var welcomemessage = view.findViewById<TextView>(R.id.welcomemessageR)
//        mAuth = FirebaseAuth.getInstance()
//        database = FirebaseDatabase.getInstance()
//        mDbRef = database?.reference!!.child("user_patient")
//        val user = mAuth.currentUser
//
//        var userreference = mDbRef.child(user?.uid!!)
//        userreference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                welcomemessage.text =  "Welcome " + snapshot.child("name").value.toString() + "to  E-Library Module"
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
        return view
    }




}