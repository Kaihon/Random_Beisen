package space.nurgali.random_beisen

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

//константа для ключа сохранения состояния
private const val LAST_RANDOMIZED_VALUE = "LAST_RANDOMIZED_VALUE"

class NumberFragment() : Fragment(), Parcelable {
    //создал 2 переменные - ранняя инициализация
    private lateinit var resultTextView: TextView
    private lateinit var randomizeButton: Button

    private var randomValue: Int = 0

    constructor(parcel: Parcel) : this() {
        randomValue = parcel.readInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_number, container, false)
        //инициализирую 2 переменные//в фрагментах findViewByld вызывается только из view
        resultTextView = view.findViewById(R.id.result_text_view)
        randomizeButton = view.findViewById(R.id.randomize_button)

        //если состояние сохранено, то применить его значение к текстовому полю результата
        if (savedInstanceState != null)
            resultTextView.text = savedInstanceState.getInt(LAST_RANDOMIZED_VALUE).toString()
        //если нет, то зарандомить новое число
        else
            randomize()

        //вешаем на кнопку ClickListener, рандомить новое число при нажатии на кнопку
        randomizeButton.setOnClickListener { randomize() }

        return view
    }

    //сохраняем состояние приложение
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(LAST_RANDOMIZED_VALUE, randomValue)
        super.onSaveInstanceState(outState)
    }

    //расписываем функцию,которая,возвращает рандомное значение от 00 до 100
    private fun randomize() {
        randomValue= Random.nextInt(100)
        resultTextView.text = randomValue.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(randomValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NumberFragment> {
        override fun createFromParcel(parcel: Parcel): NumberFragment {
            return NumberFragment(parcel)
        }

        override fun newArray(size: Int): Array<NumberFragment?> {
            return arrayOfNulls(size)
        }
    }


}