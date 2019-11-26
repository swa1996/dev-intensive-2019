package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class MainViewModel : ViewModel() {
    private val chatRepository = ChatRepository
    private val query = mutableLiveData("")
    private val chats = Transformations.map(chatRepository.loadChats()) { chats ->
        return@map chats.filter { !it.isArchived }.map { it.toChatItem() }
            .sortedBy { it.id.toInt() }
    }

    fun getChatData(): LiveData<List<ChatItem>> {
        val result = MediatorLiveData<List<ChatItem>>()
        val filterF = {
            val queryStr = query.value!!
            val users = chats.value!!
            result.value = if (queryStr.isEmpty()) users
            else users.filter { it.title.contains(queryStr, true) }
        }
        result.addSource(chats) { filterF.invoke() }
        result.addSource(query) { filterF.invoke() }
        return result
    }


//    private fun loadChats(): List<ChatItem>{
//       val chats = chatRepository.loadChats()
//        return chats.map { it.toChatItem() } .sortedBy { it.id.toInt() }
//    }

//    fun addItems() {
//       val newItems = DataGenerator.generateChatsWithOffset(chats.value!!.size, 5).map { it.toChatItem() }
//        val copy = chats.value!!.toMutableList()
//        copy.addAll(newItems)
//        chats.value = copy.sortedBy { it.id.toInt() }
//
//    }

    fun addToArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))

    }

    fun handleSearchQuery(text: String) {
        query.value = text
    }

    fun restoreFromArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))

    }
}