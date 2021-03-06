package com.example.routemap.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.routemap.database.DAO.EventoDAO
import com.example.routemap.database.DAO.InscricaoDAO
import com.example.routemap.database.DAO.PositionDAO
import com.example.routemap.database.DAO.UserDAO
import com.example.routemap.database.model.Evento
import com.example.routemap.database.model.Inscricao
import com.example.routemap.database.model.Position
import com.example.routemap.database.model.User
import com.example.routemap.database.utils.LocalDateTimeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [User::class, Evento::class, Inscricao::class], version = 2, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
public abstract class AppDatabase : RoomDatabase() {

    //abstract fun positionDAO(): PositionDAO
    abstract fun userDAO(): UserDAO
    abstract fun eventoDAO(): EventoDAO
    abstract fun inscricaoDAO(): InscricaoDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addCallback(RouteDatabaseCallback(scope)).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
                //instance
            }
        }
    }

    private class RouteDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDAO())
                }
            }
        }

        suspend fun populateDatabase(userDAO: UserDAO) {
            // Delete all content here.
            //  positionDAO.deleteAll()

            // Add sample positions.
            var u = User(id = 0, email = "saargov", password = "12345678", password2 = "12345678", nom_pessoa = "rAFAEL", num_cpf = "12313211321", ind_atualizado = 1)
            userDAO.insert(u)
           /* position = Position(date = LocalDateTime.now().minusDays(1), latitude =  -29.6119323, longitude = -52.2000415)
            positionDAO.insert(position)*/
        }
    }

}