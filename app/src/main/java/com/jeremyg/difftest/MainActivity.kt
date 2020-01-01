package com.jeremyg.difftest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity(), ItemAdapter.ListAdapterListener {

    val itemViewModel : ItemViewModel by viewModel()

    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemAdapter = ItemAdapter(this)
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = itemAdapter

        itemViewModel.getListObserver().observe(this, Observer {
            Timber.d("List Observer, items count ${it.size}")
            this.supportActionBar!!.subtitle = "Item size: ${it.size}"
            itemAdapter.submitList(it.toList())
        })

        fab_add_item.setOnClickListener {
            itemViewModel.addItem()
        }
    }

    override fun onLongViewClick(item: Item, position: Int): Boolean {
        itemViewModel.deleteItem(item.index)
        return true
    }
}
