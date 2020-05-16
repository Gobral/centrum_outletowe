package com.example.myapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Produkt
import com.example.myapplication.R
import com.example.myapplication.TestAdapter
import kotlinx.android.synthetic.main.fragment_wyszukiwarka.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wyszukiwarka, container, false)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exampleList =   ArrayList<Produkt>()
        exampleList.add(Produkt("test1", "linia1"))
        exampleList.add(Produkt("test2", "linia2"))
        exampleList.add(Produkt("test3", "linia2"))
        exampleList.add(Produkt("test4", "linia2"))
        exampleList.add(Produkt("test5", "linia2"))
        exampleList.add(Produkt("test6", "linia2"))
        exampleList.add(Produkt("test7", "linia2"))
        exampleList.add(Produkt("test8", "linia2"))
        exampleList.add(Produkt("test9", "linia2"))
        exampleList.add(Produkt("test10", "linia2"))
        exampleList.add(Produkt("test11", "linia2"))
        exampleList.add(Produkt("test12", "linia2"))
        exampleList.add(Produkt("test13", "linia2"))

        recycler_wyszukiwarka.adapter = TestAdapter(exampleList)
        recycler_wyszukiwarka.layoutManager = LinearLayoutManager(this.context)
        recycler_wyszukiwarka.setHasFixedSize(true)
    }
}