package com.example.myapplication.ui.zapisane

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class ZapisaneFragment : Fragment() {

    private lateinit var zapisaneViewModel: ZapisaneViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        zapisaneViewModel =
            ViewModelProviders.of(this).get(ZapisaneViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_share, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        zapisaneViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}