package com.jubel.jubelind.shared.infrastructure

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import java.sql.Connection
import java.sql.DriverManager

class SQLiteModule: AbstractModule() {

    @Provides @Singleton
    fun provideConnection(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:/Users/juan/fuente/propio/jbelind.db")
    }

}