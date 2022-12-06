package com.app.flipprteachear.home.daofile

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.flipprteachear.home.pojo.Detail

@Dao
interface DetailDao {

//	@Query("SELECT * FROM Detail")
//	suspend fun getAll(): List<Detail>
	
//	@Insert(onConflict = OnConflictStrategy.IGNORE)
//	suspend fun insertAll(details: Detail): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	@JvmSuppressWildcards
	fun insertAll(details: List<Detail>?)

	@Query("SELECT * FROM FlipperTeacher")
	fun getAllData(): LiveData<List<Detail>?>?

	@Query("DELETE FROM FlipperTeacher")
	fun deleteAllData()
	
//	@Delete
//	suspend fun delete(Course: Detail)
	
}
