package ru.unit.barsdiary.other

import androidx.lifecycle.MutableLiveData

class FailLiveData : MutableLiveData<Throwable>() {

    inline fun safety(tryBlock: () -> Unit, catchBlock: () -> Unit) {
        try {
            tryBlock()
        } catch (exception: Throwable) {
            catchBlock()
            postValue(exception)
        }
    }

    inline fun safety(tryBlock: () -> Unit) {
        safety(tryBlock, {})
    }
}