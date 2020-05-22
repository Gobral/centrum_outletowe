package com.example.myapplication.ui.usatwienia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class UstawieniaFragment : Fragment() {

    private lateinit var ustawieniaViewModel: UstawieniaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ustawieniaViewModel =
            ViewModelProviders.of(this).get(UstawieniaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        ustawieniaViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}