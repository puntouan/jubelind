package com.jubel.jubelind.shared

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import java.sql.Connection
import java.sql.DriverManager

class TestSQLiteModule: AbstractModule() {

    @Provides @Singleton
    fun provideConnection(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:db/jubelind.test.db")
    }

}