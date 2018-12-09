package gsihome.reyst.fp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class PinViewModel: ViewModel() {

    private val isPinCorrect: MutableLiveData<Boolean> = MutableLiveData()

    private val pin: MutableLiveData<String> = MutableLiveData()

    init {
        isPinCorrect.value = false
        pin.value = ""
    }

    fun getObservableIsPinCorrect() : LiveData<Boolean> = isPinCorrect

    fun confirmPin(pinConfirmation:String) {
        isPinCorrect.value = !pin.value.isNullOrBlank() && pinConfirmation == pin.value
    }

    fun getObservablePin() : LiveData<String> = pin

    fun updatePin(newPin: String) {
        pin.value = newPin
    }

}