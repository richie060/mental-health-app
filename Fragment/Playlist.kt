
package com.example.mentalhealth.Fragment

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealth.model.Song_info
import kotlinx.android.synthetic.main.audio_ticket.view.*
import kotlinx.android.synthetic.main.player.*
import kotlinx.android.synthetic.main.playlist_activity.*
import mentalhealth.R


class Playlist : AppCompatActivity(){

//    private lateinit var actionBar: ActionBar

    var listSongs=ArrayList<Song_info>()
    var adapter:MySongAdapter?=null
    var mp:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player)

//        actionBar = supportActionBar!!
//        actionBar.title="Sleeping tracks"
//        actionBar.setDisplayHomeAsUpEnabled(true)
//        actionBar.setDisplayShowHomeEnabled(true)

        val mediaPlayer = MediaPlayer.create(this, R.raw.musi)

        imgplay.setOnClickListener {
            if (mediaPlayer.isPlaying){
                imgplay.setImageResource(R.drawable.ic_play)
                mediaPlayer.pause()
            }
            else{
                imgplay.setImageResource(R.drawable.ic_pause)
                mediaPlayer.start()
            }
        }

        LoadURLOnline()

        var mytracking=mySongTrack()
        mytracking.start()

    }
    fun LoadURLOnline(){
        listSongs.add(Song_info("Deep sleeping","rich","https://server6.mp3quran.net/thubti/001.mp3"))
        listSongs.add(Song_info("nature walk","rich","https://server6.mp3quran.net/thubti/002.mp3"))
        listSongs.add(Song_info(" mind calmer","Alex","https://server6.mp3quran.net/thubti/003.mp3"))
        listSongs.add(Song_info("walking","james","https://server6.mp3quran.net/thubti/004.mp3"))
        listSongs.add(Song_info("sleeping","Alex","https://server6.mp3quran.net/thubti/005.mp3"))
        listSongs.add(Song_info("love ting","music","android.resource://" + "/" + R.raw.musi))
    }
    inner  class MySongAdapter:BaseAdapter{
        var  myListSong=ArrayList<Song_info>()
        constructor(myListSong:ArrayList<Song_info>):super(){
            this.myListSong=myListSong

        }
        @SuppressLint("ViewHolder")
        override fun getView(postion: Int, p1: View?, p2: ViewGroup?): View {
            val myView= layoutInflater.inflate(R.layout.audio_ticket,null)
            val Song= this.myListSong[postion]

            myView.tvsong_name.text = Song.Title
            myView.tvsong_author.text = Song.Author
            myView.imgplay.setOnClickListener{
                //TODO: play song
                if(myView.imgplay.equals(R.drawable.ic_pause)){
                    mp!!.stop()
                    myView.imgplay.setImageResource(R.drawable.ic_play)
                }else {
                    mp = MediaPlayer()
                    try {
                        mp!!.setDataSource(Song.SongUrl)
                        mp!!.prepare()
                        mp!!.start()
                        myView.imgplay.setImageResource(R.drawable.ic_pause)
                        sbProgress.max=mp!!.duration
                    } catch (ex: Exception) {
                    }
                }
            }
            return  myView

        }

        override fun getItem(item: Int): Any {
            return this.myListSong[item]
        }

        override fun getItemId(p0: Int): Long {
            return  p0.toLong()
        }

        override fun getCount(): Int {
            return this.myListSong.size
        }
    }
    inner  class  mySongTrack :Thread(){
        override fun run() {
            while(true){
                try{
                    sleep(1000)
                }catch (ex:Exception){}

                runOnUiThread {

                    if (mp!=null){
                        sbProgress.progress = mp!!.currentPosition
                    }
                }
            }

        }
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
//    var listSongs= ArrayList<Song_info>()
//    var adapter: MySongAdapter? = null
//    var mp:MediaPlayer? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.musi)
//
//        imgplay.setOnClickListener {
//            if (mediaPlayer.isPlaying){
//                imgplay.setImageResource(R.drawable.ic_play)
//                mediaPlayer.pause()
//            }
//            else{
//                imgplay.setImageResource(R.drawable.ic_pause)
//                mediaPlayer.start()
//            }
//        }
//
//        LoadUrlOnline()
//
//        adapter = MySongAdapter(listSongs)
//        lsListSongs.adapter = adapter
//
//        var mytracking = mySongTrack()
//        mytracking.start()
//    }
//    fun LoadUrlOnline(){
//        listSongs.add(Song_info("001","Bahati","http://server6.mp3quran.net/thubti/001.mp3"))
//        listSongs.add(Song_info("002","Octopizo","http://server6.mp3quran.net/thubti/001.mp3"))
//        listSongs.add(Song_info("003","Stivo","http://server6.mp3quran.net/thubti/001.mp3"))
//        listSongs.add(Song_info("004","Bahati","http://server6.mp3quran.net/thubti/001.mp3"))
//        listSongs.add(Song_info("005","Octopizo","http://server6.mp3quran.net/thubti/001.mp3"))
//        listSongs.add(Song_info("006","Stivo","http://server6.mp3quran.net/thubti/001.mp3"))
//    }
//    inner class MySongAdapter: BaseAdapter{
//        var myListSong= ArrayList<Song_info>()
//        constructor(myListSong: ArrayList<Song_info>):super(){
//            this.myListSong=myListSong
//        }
//        override fun getCount(): Int {
//            return this.myListSong.size
//        }
//        override fun getItem(item: Int): Any {
//            return this.myListSong[item]
//        }
//        override fun getItemId(p0: Int): Long {
//            return p0.toLong()
//        }
//        override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
//            val myview = layoutInflater.inflate(R.layout.audio_ticket, null)
//            val Song = this.myListSong[position]
//            myview.tvsong_name.text = Song.Title
//            myview.tvsong_author.text = Song.Author
//            myview.imgplay.setOnClickListener{
//                if (myview.imgplay.equals(R.drawable.ic_pause)) {
//                    mp!!.stop()
//                    myview.imgplay.setImageResource(R.drawable.ic_play)
//                } else {
//                    mp = MediaPlayer()
//                    try {
//                        mp!!.setDataSource(Song.SongUrl)
//                        mp!!.prepare()
//                        mp!!.start()
//                        myview.imgplay.setImageResource(R.drawable.ic_pause)
//                        seekbarprogress.max = mp!!.duration
//                    } catch (ex: Exception) {
//                    }
//                }
//            }
//            return myview
//        }
//
//    }
//
//    inner class mySongTrack():Thread(){
//        override fun run() {
//           while (true){
//               try {
//                   Thread.sleep(1000)
//               }catch (ex:Exception){}
//               activity?.runOnUiThread {
//                   if (mp != null) {
//                       seekbarprogress.progress = mp!!.currentPosition
//                   }
//               }
//           }
//        }
//    }
//}
//package com.example.mentalhealth
//import android.media.MediaPlayer

//import android.os.Bundle
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.audio_ticket.view.*
//import kotlinx.android.synthetic.main.player.*
//
//class Playlist : AppCompatActivity() {
//
//    var listSongs = ArrayList<Song_info>()
//    var adapter: MySongAdapter? = null
//    var mp: MediaPlayer? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.player)
//        LoadURLOnline()
//        var mytracking = mySongTrack()
//        mytracking.start()
//
//    }
//
//    fun LoadURLOnline() {
//        listSongs.add(Song_info("001", "Ahmed", "https://server6.mp3quran.net/thubti/001.mp3"))
//        listSongs.add(Song_info("002", "Ahmed", "https://server6.mp3quran.net/thubti/002.mp3"))
//        listSongs.add(Song_info("003", "Alex", "https://server6.mp3quran.net/thubti/003.mp3"))
//        listSongs.add(Song_info("004", "Ahmed", "https://server6.mp3quran.net/thubti/004.mp3"))
//        listSongs.add(Song_info("005", "Alex", "https://server6.mp3quran.net/thubti/005.mp3"))
//    }
//
//    inner class MySongAdapter : BaseAdapter {
//        var myListSong = ArrayList<Song_info>()
//
//        constructor(myListSong: ArrayList<Song_info>) : super() {
//            this.myListSong = myListSong
//        }
//        override fun getView(postion: Int, p1: View?, p2: ViewGroup?): View {
//            val myView = layoutInflater.inflate(R.layout.audio_ticket, null)
//            val Song = this.myListSong[postion]
//            myView.tvsong_name.text = Song.Title
//            myView.tvsong_author.text = Song.Author
//            myView.btnplay.setOnClickListener {
//                //TODO: play song
//                if (myView.btnplay.text == "Stop") {
//                    mp!!.stop()
//                    myView.btnplay.text = "Start"
//                } else {
//                    mp = MediaPlayer()
//                    try {
//                        mp!!.setDataSource(Song.SongUrl)
//                        mp!!.prepare()
//                        mp!!.start()
//                        myView.btnplay.text = "Stop"
//                        seekbarprogress.max = mp!!.duration
//                    } catch (ex: Exception) {
//                    }
//                }
//            }
//
//            return myView
//
//        }
//
//        override fun getItem(item: Int): Any {
//            return this.myListSong[item]
//        }
//
//        override fun getItemId(p0: Int): Long {
//            return p0.toLong()
//        }
//
//        override fun getCount(): Int {
//            return this.myListSong.size
//        }
//
//
//    }
//
//    inner class mySongTrack : Thread() {
//        override fun run() {
//            while (true) {
//                try {
//                    sleep(1000)
//                } catch (ex: Exception) {
//                }
//
//                runOnUiThread {
//                    if (mp != null) {
//                        seekbarprogress.progress = mp!!.currentPosition
//                    }
//                }
//            }
//
//        }
//    }
//}
//
//    fun CheckUserPermsions() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
//                    REQUEST_CODE_ASK_PERMISSIONS)
//                return
//            }
//        }
//
//        LoadSong()
//
//    }

//    //get acces to location permsion
//    private val REQUEST_CODE_ASK_PERMISSIONS = 123
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        when (requestCode) {
//            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                LoadSong()
//            } else {
//                // Permission Denied
//                Toast.makeText(this, "denail", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        }
//    }

//    @SuppressLint("Range")
//    fun   LoadSong() {
//        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
//        val cursor = contentResolver.query(allSongsURI, null, selection, null, null)
//        if (cursor != null) {
//            if (cursor!!.moveToFirst()) {
//
//                do {
//
//                    val songURL = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
//                    val SongAuthor = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
//                    val SongName = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
//                    listSongs.add(Song_info(SongName, SongAuthor, songURL))
//                } while (cursor!!.moveToNext())
//
//
//            }
//            cursor!!.close()
//
//            adapter=MySongAdapter(listSongs)
//            lsListSongs.adapter=adapter
//        }
//    }

