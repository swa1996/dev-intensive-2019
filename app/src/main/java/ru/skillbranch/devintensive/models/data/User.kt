package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {

    fun toUserItem(): UserItem {
        val lastActivity = when {
            lastVisit == null -> "Еще ни разу не заходил"
            isOnline -> "online"
            else -> "Последний раз был ${lastVisit.humanizeDiff()}"
        }
        return UserItem(
            id,
            "${firstName.orEmpty()} ${lastName.orEmpty()}",
            Utils.toInitials(firstName, lastName),
            avatar,
            lastActivity,
            false,
            isOnline
        )
    }

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    init {

    }

    companion object Factory {
        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName
            )
        }

    }

    data class Builder(
        private var id: String = "",
        private var firstName: String? = "",
        private var lastName: String? = "",
        private var avatar: String? = "",
        private var rating: Int = 0,
        private var respect: Int = 0,
        private var lastVisit: Date? = Date(),
        private var isOnline: Boolean = false
    ) {

        fun id(s: String) = apply { this.id = s }
        fun firstName(s: String?) = apply { this.firstName = s }
        fun lastName(s: String?) = apply { this.lastName = s }
        fun avatar(s: String?) = apply { this.avatar = s }
        fun rating(n: Int) = apply { this.rating = n }
        fun respect(n: Int) = apply { this.respect = n }
        fun lastVisit(d: Date?) = apply { this.lastVisit = d }
        fun isOnline(b: Boolean) = apply { this.isOnline = b }
        fun build() = User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar,
            rating = rating,
            respect = respect,
            lastVisit = lastVisit,
            isOnline = isOnline
        )

    }
}