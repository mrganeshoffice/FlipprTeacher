package com.app.flipprteachear.home.databasefile

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.flipprteachear.home.converter.Converters
import com.app.flipprteachear.home.daofile.DetailDao
import com.app.flipprteachear.home.pojo.Detail

@Database(entities = [Detail::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun DetailDao(): DetailDao
}
