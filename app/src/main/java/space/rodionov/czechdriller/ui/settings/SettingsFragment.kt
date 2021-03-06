package space.rodionov.czechdriller.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import space.rodionov.czechdriller.R
import space.rodionov.czechdriller.databinding.SettingsLayoutBinding

private const val TAG = "SettingsFragment"

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_layout)/*, PopupMenu.OnMenuItemClickListener */{

    private val viewModel: SettingsViewModel by viewModels()
//    private lateinit var binding: SettingsLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = SettingsLayoutBinding.bind(view)

        binding.apply {
            viewModel.readTransDir.observe(viewLifecycleOwner) {
                Log.d(TAG, "onViewCreated:  transDirPreference = $it")
                switchTranslationDirection.isChecked = it
                switchTranslationDirection.text = if (it) "С русского на чешский" else "С чешского на русский"

                switchTranslationDirection.setOnCheckedChangeListener { switchBtn, isChecked ->
                    viewModel.saveTransDir(switchBtn.isChecked)
                }
            }

//            viewModel.readNatLang.observe(viewLifecycleOwner) {
//                languageButton.text = if (it == NativeLanguage.RUS) "Русский" else "English" //Исправить хардкодед
//                languageButton.setOnClickListener { showPopup(languageButton) }
//            }
        }

//        setHasOptionsMenu(true)
    }

//    fun showPopup(v: View) {
//        val popup = PopupMenu(requireContext(), v)
//        popup.setOnMenuItemClickListener(this)
//        popup.inflate(R.menu.language_menu)
//        popup.show()
//    }
//
//    override fun onMenuItemClick(item: MenuItem): Boolean {
//        return when(item.itemId) {
//            R.id.item_ru -> {
//                Log.d(TAG, "lang РУССКИЙ ЕЗЫК")
//                viewModel.saveNatLang(NativeLanguage.RUS)
//                true
//            }
//            R.id.item_en -> {
//                Log.d(TAG, "lang ANGLIJSKIJ")
//                viewModel.saveNatLang(NativeLanguage.ENG)
//                true
//            }
//            else -> false
//        }
//    }


}