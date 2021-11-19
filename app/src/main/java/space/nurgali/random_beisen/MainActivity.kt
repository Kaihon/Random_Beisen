package space.nurgali.random_beisen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

//константа для сохранения ключа состояния
private const val LAST_SELECTED_ITEM = "LAST_SELECTED_ITEM"

class MainActivity : AppCompatActivity() {

    //ранняя инициализация нижней навигации
    private lateinit var bottomNavigationMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationMenu = findViewById(R.id.bottom_navigation_menu)

        //настроим клики по элементам нижней навигации
        bottomNavigationMenu.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.number -> {
                    fragment = NumberFragment()
                }
                R.id.dice -> {
                    fragment = DiceFragment()
                }
                R.id.coin -> {
                    fragment = CoinFragment()
                }
            }
            replaceFragment(fragment!!)
        }
        R.id.share_button ; {
            val chooser = Intent.createChooser(intent, "О нас")
            startActivity(chooser)
            true

        }
        //восстановление состояния нижней навигации
        //если не сохранено то по дефолту выбрать R.id.number
        bottomNavigationMenu.selectedItemId =
            savedInstanceState?.getInt(LAST_SELECTED_ITEM) ?: R.id.number
    }

    //сохраним состояние последнего нажатого элемента нижней навигации
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(LAST_SELECTED_ITEM, bottomNavigationMenu.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    //функция замены фрагментов с помощью supportFragmentManager
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(
    Intent.EXTRA_TEXT,
    value :"Бейсенов и команда"
    )

}