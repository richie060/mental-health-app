package com.example.mentalhealth;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.mentalhealth.VL.UploadSongActivity
import com.example.mentalhealth.player.UploadMovie
import com.example.mentalhealth.podcast.UploadPodcastActivity
import com.example.mentalhealth.resources.uploadfile
import com.example.mentalhealth.stories.Success
import mentalhealth.R

class Uploads : Fragment() {
    private var toolbar: Toolbar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_uploads, container, false)

        val podcast = view.findViewById<Button>(R.id.uploadPodcast)
        val music2 = view.findViewById<Button>(R.id.uploadSongs1)
        val video = view.findViewById<Button>(R.id.uploadVideos)
        val novel = view.findViewById<Button>(R.id.uploadNovel)
        val addstory = view.findViewById<Button>(R.id.addstory)

        music2.setOnClickListener {
            val intent = Intent(requireContext(), UploadSongActivity::class.java)
            startActivity(intent)
        }

        podcast.setOnClickListener {
            val intent = Intent(requireContext(), UploadPodcastActivity::class.java)
            startActivity(intent)
        }

        video.setOnClickListener {
            val intent = Intent(requireContext(), UploadMovie::class.java)
            startActivity(intent)
        }

        novel.setOnClickListener {
            val intent = Intent(requireContext(), uploadfile::class.java)
            startActivity(intent)
        }
        addstory.setOnClickListener {
            val intent = Intent(requireContext(), Success::class.java)
            startActivity(intent)
        }
     return view
        }
}