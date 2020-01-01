package com.jeremyg.difftest

import androidx.lifecycle.ViewModel


class ItemViewModel(var itemRepository: ItemRepository) : ViewModel() {

    fun getListObserver() = itemRepository.getListObserver()

    fun addItem() = itemRepository.addItem()

    fun deleteItem(value: Int) {
        itemRepository.deleteItem(value)
    }
}