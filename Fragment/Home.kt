package com.example.mentalhealth.Fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mentalhealth.Meditation2
import com.example.mentalhealth.activities.MainActivityR
import com.example.mentalhealth.otp.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import mentalhealth.R

class Home : Fragment(R.layout.home) {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var database: FirebaseDatabase


    val number = "911"
    val REQUEST_PHONE_CALL = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home, container, false)
        val call = view.findViewById<Button>(R.id.btncall)


        var usertyp = view.findViewById<TextView>(R.id.usertype)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        mDbRef = database?.reference!!.child("users").child(
            FirebaseAuth.getInstance().getCurrentUser()!!.getUid())
        val user = mAuth.currentUser

        var usert = mDbRef.child(user?.uid!!)

        usert.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var type = snapshot.child("type").value.toString()
                if (type.equals("doctor")){
                    usertyp.setText("patient")
                }else{
                    usertyp.setText("Therapist")
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })



        val cardViewResources = view.findViewById<CardView>(R.id.cardviewResources)
        val cardviewMeditation = view.findViewById<CardView>(R.id.cardviewMeditation)
        val cardviewProfile = view.findViewById<CardView>(R.id.cardviewProfile)
        val cardviewCounsellors = view.findViewById<CardView>(R.id.cardviewCounselors)
        val cardViewconnect = view.findViewById<CardView>(R.id.cardviewConnect)
        val Success_stories = view.findViewById<CardView>(R.id.cardviewsuccessStories)

        cardViewResources.setOnClickListener {
//            val intent = Intent(requireContext(),Resources::class.java)
//            startActivity(intent)
            val resources =   Resources()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout, resources)
            transaction.commit()
        }

        cardviewMeditation.setOnClickListener {
            val meditation =   Meditation2()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout, meditation)
            transaction.commit()
        }

        cardviewCounsellors.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        cardviewProfile.setOnClickListener {
            val intent = Intent(requireContext(),MainActivityR::class.java)
            startActivity(intent)
        }

        cardViewconnect.setOnClickListener {
            val connect =   GetConnected()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frlayout, connect)
            transaction.commit()
        }

        Success_stories.setOnClickListener {

            val intent = Intent(requireContext(),QusestionsActivity::class.java)
            startActivity(intent)
//            val Success_stories =   FAQFragment()
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.frlayout, Success_stories)
//            transaction.commit()
        }

        call.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL )
            }
            else{
                startCall()
            }
        }
        return view
    }
    private fun startCall() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + number)
        startActivity(callIntent)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray)
    {
        if (requestCode == REQUEST_PHONE_CALL) {
            startCall()
        }
    }

}