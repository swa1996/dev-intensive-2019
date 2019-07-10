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

    fun transliteration(payload: String, divider: String = " "): String {
        var res: String = payload
        res = res.replace(" ", divider)
        res = res.replace("а", "a")
        res = res.replace("б", "b")
        res = res.replace("в", "v")
        res = res.replace("г", "g")
        res = res.replace("д", "d")
        res = res.replace("е", "e")
        res = res.replace("ё", "e")
        res = res.replace("ж", "zh")
        res = res.replace("з", "z")
        res = res.replace("и", "i")
        res = res.replace("й", "i")
        res = res.replace("к", "k")
        res = res.replace("л", "l")
        res = res.replace("м", "m")
        res = res.replace("н", "n")
        res = res.replace("о", "o")
        res = res.replace("п", "p")
        res = res.replace("р", "r")
        res = res.replace("с", "s")
        res = res.replace("т", "t")
        res = res.replace("у", "u")
        res = res.replace("ф", "f")
        res = res.replace("х", "h")
        res = res.replace("ц", "c")
        res = res.replace("ч", "ch")
        res = res.replace("ш", "sh")
        res = res.replace("щ", "sh'")
        res = res.replace("ъ", "")
        res = res.replace("ы", "i")
        res = res.replace("ь", "")
        res = res.replace("э", "e")
        res = res.replace("ю", "yu")
        res = res.replace("я", "ya")

        res = res.replace("А", "A")
        res = res.replace("Б", "B")
        res = res.replace("В", "V")
        res = res.replace("Г", "G")
        res = res.replace("Д", "D")
        res = res.replace("Е", "E")
        res = res.replace("Ё", "E")
        res = res.replace("Ж", "Zh")
        res = res.replace("З", "Z")
        res = res.replace("И", "I")
        res = res.replace("Й", "I")
        res = res.replace("К", "K")
        res = res.replace("Л", "L")
        res = res.replace("М", "M")
        res = res.replace("Н", "N")
        res = res.replace("О", "O")
        res = res.replace("П", "P")
        res = res.replace("Р", "R")
        res = res.replace("С", "S")
        res = res.replace("Т", "T")
        res = res.replace("У", "U")
        res = res.replace("Ф", "F")
        res = res.replace("Х", "H")
        res = res.replace("Ц", "C")
        res = res.replace("Ч", "Ch")
        res = res.replace("Ш", "Sh")
        res = res.replace("Щ", "Sh'")
        res = res.replace("Ъ", "")
        res = res.replace("Ы", "I")
        res = res.replace("Ь", "")
        res = res.replace("Э", "E")
        res = res.replace("Ю", "Yu")
        res = res.replace("Я", "Ya")
        return res
    }
}