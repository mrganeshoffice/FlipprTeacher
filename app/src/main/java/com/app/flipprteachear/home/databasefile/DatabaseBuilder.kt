package com.app.flipprteachear.home.databasefile

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
	private var INSTANCE: AppDatabase? = null
	fun getInstance(context: Context): AppDatabase {
		if (INSTANCE == null) {
			synchronized(AppDatabase::class) {
				INSTANCE = buildRoomDB(context)
			}
		}
		return INSTANCE!!
	}
	private fun buildRoomDB(context: Context) =
		Room.databaseBuilder(
			context.applicationContext,
			AppDatabase::class.java,
			"FlipperTeacher"
		).build()
}
