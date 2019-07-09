package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView(): UserView {
    val nickName = ""
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "ни разу не был" else if (isOnline) "online" else "Последний рах был ${lastVisit}"
    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        initials = initials,
        status = status,
        nickName = nickName
    )
}