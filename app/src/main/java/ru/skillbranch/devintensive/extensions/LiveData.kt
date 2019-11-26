package ru.skillbranch.devintensive.extensions

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveData(defaultvalue: T? = null): MutableLiveData<T> {
    val data = MutableLiveData<T>()
    if (defaultvalue != null) {
        data.value = defaultvalue
    }
    return data
}