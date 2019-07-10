package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    if (this.length >= count) return "${this.substring(0, count)}".trim().plus("...")
    else return this.trim()
}