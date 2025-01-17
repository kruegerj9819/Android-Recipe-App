package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM Measurement")
    fun getAllMeasurements(): Flow<List<Measurement>>

    @Query("SELECT * FROM Measurement WHERE name = :measurementName")
    fun getMeasurement(measurementName: String): Flow<List<Measurement>>

    @Insert
    suspend fun insertMeasurement(meas: Measurement): Long

    @Delete
    suspend fun deleteMeasurement(
        meas: Measurement
    )
}