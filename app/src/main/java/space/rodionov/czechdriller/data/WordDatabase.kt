package space.rodionov.czechdriller.data

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import space.rodionov.czechdriller.ApplicationScope
import space.rodionov.czechdriller.R
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Word::class, CategoryItem::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    class Callback @Inject constructor(
        private val context: Application,
        private val database: Provider<WordDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {



        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().wordDao()

            applicationScope.launch {

                dao.insertCategory(CategoryItem("Люди", 1))
                dao.insertCategory(CategoryItem("Работа", 2))
                dao.insertCategory(CategoryItem("Город", 3))
                dao.insertCategory(CategoryItem("Квартира", 4))
                dao.insertCategory(CategoryItem("Еда", 5))
                dao.insertCategory(CategoryItem("Время", 6))
                dao.insertCategory(CategoryItem("Вещи", 7))
                dao.insertCategory(CategoryItem("Одежда", 8))
                dao.insertCategory(CategoryItem("Местоимения, предлоги и т.п.", 9))
                dao.insertCategory(CategoryItem("Числа", 10))
                dao.insertCategory(CategoryItem("Прилагательные", 11))
                dao.insertCategory(CategoryItem("Цвета", 12))
                dao.insertCategory(CategoryItem("Частицы, союзы", 13))
                dao.insertCategory(CategoryItem("Всякая всячина", 14))
                dao.insertCategory(CategoryItem("Глаголы", 15))

                val nativ1 = context.resources.getStringArray(R.array.nativ1).toList()
                val nativ2 = context.resources.getStringArray(R.array.nativ2).toList()
                val nativ3 = context.resources.getStringArray(R.array.nativ3).toList()
                val nativ4 = context.resources.getStringArray(R.array.nativ4).toList()
                val nativ5 = context.resources.getStringArray(R.array.nativ5).toList()
                val nativ6 = context.resources.getStringArray(R.array.nativ6).toList()
                val nativ7 = context.resources.getStringArray(R.array.nativ7).toList()
                val nativ8 = context.resources.getStringArray(R.array.nativ8).toList()
                val nativ9 = context.resources.getStringArray(R.array.nativ9).toList()
                val nativ10 = context.resources.getStringArray(R.array.nativ10).toList()
                val nativ11 = context.resources.getStringArray(R.array.nativ11).toList()
                val nativ12 = context.resources.getStringArray(R.array.nativ12).toList()
                val nativ13 = context.resources.getStringArray(R.array.nativ13).toList()
                val nativ14 = context.resources.getStringArray(R.array.nativ14).toList()
                val nativ15 = context.resources.getStringArray(R.array.nativ15).toList()

                val foreign1 = context.resources.getStringArray(R.array.foreign1).toList()
                val foreign2 = context.resources.getStringArray(R.array.foreign2).toList()
                val foreign3 = context.resources.getStringArray(R.array.foreign3).toList()
                val foreign4 = context.resources.getStringArray(R.array.foreign4).toList()
                val foreign5 = context.resources.getStringArray(R.array.foreign5).toList()
                val foreign6 = context.resources.getStringArray(R.array.foreign6).toList()
                val foreign7 = context.resources.getStringArray(R.array.foreign7).toList()
                val foreign8 = context.resources.getStringArray(R.array.foreign8).toList()
                val foreign9 = context.resources.getStringArray(R.array.foreign9).toList()
                val foreign10 = context.resources.getStringArray(R.array.foreign10).toList()
                val foreign11 = context.resources.getStringArray(R.array.foreign11).toList()
                val foreign12 = context.resources.getStringArray(R.array.foreign12).toList()
                val foreign13 = context.resources.getStringArray(R.array.foreign13).toList()
                val foreign14 = context.resources.getStringArray(R.array.foreign14).toList()
                val foreign15 = context.resources.getStringArray(R.array.foreign15).toList()

                for (i in foreign1.indices) { dao.insert(Word(nativ1[i], foreign1[i], 1)) }
                for (i in foreign2.indices) { dao.insert(Word(nativ2[i], foreign2[i], 2)) }
                for (i in foreign3.indices) { dao.insert(Word(nativ3[i], foreign3[i], 3)) }
                for (i in foreign4.indices) { dao.insert(Word(nativ4[i], foreign4[i], 4)) }
                for (i in foreign5.indices) { dao.insert(Word(nativ5[i], foreign5[i], 5)) }
                for (i in foreign6.indices) { dao.insert(Word(nativ6[i], foreign6[i], 6)) }
                for (i in foreign7.indices) { dao.insert(Word(nativ7[i], foreign7[i], 7)) }
                for (i in foreign8.indices) { dao.insert(Word(nativ8[i], foreign8[i], 8)) }
                for (i in foreign9.indices) { dao.insert(Word(nativ9[i], foreign9[i], 9)) }
                for (i in foreign10.indices) { dao.insert(Word(nativ10[i], foreign10[i], 10)) }
                for (i in foreign11.indices) { dao.insert(Word(nativ11[i], foreign11[i], 11)) }
                for (i in foreign12.indices) { dao.insert(Word(nativ12[i], foreign12[i], 12)) }
                for (i in foreign13.indices) { dao.insert(Word(nativ13[i], foreign13[i], 13)) }
                for (i in foreign14.indices) { dao.insert(Word(nativ14[i], foreign14[i], 14)) }
                for (i in foreign15.indices) { dao.insert(Word(nativ15[i], foreign15[i], 15)) }



            }

        }
    }

}
