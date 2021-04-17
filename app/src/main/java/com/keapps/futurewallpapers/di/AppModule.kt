package com.keapps.futurewallpapers.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.keapps.futurewallpapers.data.WallDAO
import com.keapps.futurewallpapers.data.WallPaperRoomDatabase
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.WallpapersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideWallPaperDao(database: WallPaperRoomDatabase): WallDAO {
        return database.wallDao()
    }

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext applicationContext: Context)
            : WallPaperRoomDatabase = WallPaperRoomDatabase.getDatabase(applicationContext)


    @Provides
    @Singleton
    fun providesRepository(wallDAO: WallDAO) = WallpapersRepo(wallDAO)





}
