package space.rodionov.czechdriller.data

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import space.rodionov.czechdriller.ApplicationScope
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

            }

        }
    }

}
