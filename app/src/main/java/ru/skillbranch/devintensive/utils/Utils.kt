package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")
        when {
            parts.isNullOrEmpty() || parts.get(0).isEmpty() -> return null to null
            else -> {
                val firstName = parts.getOrNull(0)
                val lastName = parts.getOrNull(1)
                return firstName to lastName
            }
        }
    }
    fun toInitials(firstName: String?, lastName: String?): String?{
        var res = "${if(!firstName.isNullOrEmpty()) "${firstName.first().toUpperCase()}" else ""}"
         res += "${ if (!lastName.isNullOrEmpty())"${lastName.first().toUpperCase()}" else ""}"
        if(res.isNullOrBlank())return null
        else return res
    }
}