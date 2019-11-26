package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class MainViewModel : ViewModel() {
    private val chatRepository = ChatRepository
    private val chats = Transformations.map(chatRepository.loadChats()) { chats ->
        return@map chats.filter { !it.isArchived }.map { it.toChatItem() }
            .sortedBy { it.id.toInt() }
    }

    fun getChatData(): LiveData<List<ChatItem>> {
//        val archiveChats = chatRepository.loadChats().value!!.filter { it.isArchived }
//        val chat: Chat = Chat("", "")
//        archiveChats.map {
//            chat.messages.addAll(it.messages)
//        }
//        val newChats = chats.value!!.toMutableList()
//            newChats.add(0, chat.toChatItem())
//        chats.value = newChats
        return chats
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

    fun restoreFromArchive(id: String) {
        val chat = chatRepository.find(id)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))

    }
}