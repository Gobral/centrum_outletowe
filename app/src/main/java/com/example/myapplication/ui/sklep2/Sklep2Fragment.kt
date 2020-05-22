package com.example.myapplication.ui.sklep2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class Sklep2Fragment : Fragment() {

    private lateinit var sklep2ViewModel: Sklep2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sklep2ViewModel =
            ViewModelProviders.of(this).get(Sklep2ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        sklep2ViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}