package com.example.myapplication.ui.zapisane

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.*
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.UlubioneEntity
import kotlinx.android.synthetic.main.fragment_zapisane.*

class ZapisaneFragment : Fragment() {

    private lateinit var zapisaneViewModel: ZapisaneViewModel
    lateinit var adaptor: TestAdapter
    val exampleList =   ArrayList<Produkt>()
    var ulubione = ArrayList<UlubioneEntity>()
    private lateinit var scrollManager: Skrol

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        zapisaneViewModel =
            ViewModelProviders.of(this).get(ZapisaneViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_zapisane, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val baza = AppDatabase.getAppDataBase(this.requireContext())
        adaptor = TestAdapter(exampleList, this.context)

        val oberwatorUlubionych : Observer<List<UlubioneEntity>> =
            Observer<List<UlubioneEntity>> { updatedUlubione ->
                ulubione = updatedUlubione as ArrayList<UlubioneEntity>
                val samelinki = ArrayList<String>()
                for(u in ulubione){
                    samelinki.add(u.link)
                }
                ZaladujUlubione(exampleList, adaptor, samelinki).execute()
                adaptor.setUlubione(samelinki)
                println("Updejt")
            }

        val ulubioneiLiveData: LiveData<List<UlubioneEntity>> = baza!!.ulubioneDao().livelubione()
        ulubioneiLiveData.observe(viewLifecycleOwner, oberwatorUlubionych)

        recycler_zapisane.adapter = adaptor
        val menager = LinearLayoutManager(this.context)
        recycler_zapisane.layoutManager = menager
        recycler_zapisane.setHasFixedSize(true)
    }

}