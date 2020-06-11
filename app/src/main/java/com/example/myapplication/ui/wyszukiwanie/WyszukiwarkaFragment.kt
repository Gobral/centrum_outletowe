package com.example.myapplication.ui.wyszukiwanie

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.*
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.UlubioneEntity
import kotlinx.android.synthetic.main.fragment_wyszukiwarka.*


class WyszukiwarkaFragment : Fragment() {

    private lateinit var wyszukiwarkaViewModel: WyszukiwarkaViewModel
    lateinit var adaptor: TestAdapter
    val exampleList =   ArrayList<Produkt>()
    var ulubione = ArrayList<UlubioneEntity>()
    private lateinit var scrollManager: Skrol
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wyszukiwarkaViewModel =
            ViewModelProviders.of(this).get(WyszukiwarkaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wyszukiwarka, container, false)
        setHasOptionsMenu(true)
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
                adaptor.setUlubione(samelinki)
                println("Updejt")
            }

        val ulubioneiLiveData: LiveData<List<UlubioneEntity>> = baza!!.ulubioneDao().livelubione()
        ulubioneiLiveData.observe(viewLifecycleOwner, oberwatorUlubionych)

        recycler_wyszukiwarka.adapter = adaptor
        val menager = LinearLayoutManager(this.context)
        recycler_wyszukiwarka.layoutManager = menager
        ZaladujAsync(exampleList, adaptor, 1).execute()
        scrollManager = Skrol(exampleList, adaptor, menager)
        recycler_wyszukiwarka.addOnScrollListener(scrollManager)
        recycler_wyszukiwarka.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.example_menu, menu)
        println("wykonano")
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener{
            override fun onViewAttachedToWindow(v: View?) {}

            override fun onViewDetachedFromWindow(v: View?) {
                scrollManager.wlacz()
                exampleList.clear()
                adaptor.notifyDataSetChanged()
                ZaladujAsync(exampleList, adaptor, 1).execute()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var tekst: String = ""
                if (query != null) {
                    tekst = query
                }
                if(tekst.length >= 2){
                    scrollManager.wylacz()
                    exampleList.clear()
                    adaptor.notifyDataSetChanged()
                    WyszukajAsync(exampleList, adaptor, tekst).execute()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}