package com.jeremyg.difftest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemRepository {

    var counter = 0

    private var _list = MutableLiveData<MutableList<Item>>()
    private val observableList : LiveData<MutableList<Item>>
        get() = _list

    init {
        _list.value = mutableListOf()
    }

    fun getListObserver() = observableList

    fun addItem() {
        GlobalScope.launch {
            val l = _list.value
            l?.run {
                val c = counter++
                this.add(Item(c, c.toString()))
                _list.postValue(this)
            }
        }
    }

    fun deleteItem(value: Int) {
        GlobalScope.launch {
            val old = _list.value
            val new = old?.filter { i -> i.index != value }?.toMutableList()
            _list.postValue(new)
        }
    }

}