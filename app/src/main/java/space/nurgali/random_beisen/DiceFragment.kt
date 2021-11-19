package space.nurgali.random_beisen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlin.random.Random

private const val LAST_RANDOM_VALUE = "LAST_RANDOM_VALUE"

class DiceFragment : Fragment() {

    private lateinit var diceImageView: ImageView
    private lateinit var randomizeButton: Button

    private var randomValue = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dice, container, false)
        diceImageView = view.findViewById(R.id.dice_image_view)
        randomizeButton = view.findViewById(R.id.randomize_button)

        if (savedInstanceState!=null)
        rollDice(savedInstanceState.getInt(LAST_RANDOM_VALUE))

        randomizeButton.setOnClickListener {
            randomValue = Random.nextInt(1, 6)
            rollDice(randomValue)
        }


        return view
    }

    private fun rollDice(randomValue: Int) {
        diceImageView.setImageResource(
            when (randomValue) {
                1 -> R.drawable.dice1_icon
                2 -> R.drawable.dice2_icon
                3 -> R.drawable.dice3_icon
                4 -> R.drawable.dice4_icon
                5 -> R.drawable.dice5_icon
                6 -> R.drawable.dice6_icon
                else -> R.drawable.dice1_icon
            }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(LAST_RANDOM_VALUE, randomValue)
        super.onSaveInstanceState(outState)
    }

}
