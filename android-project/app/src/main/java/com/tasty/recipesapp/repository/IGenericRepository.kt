package com.tasty.recipesapp.repository

import android.content.Context


interface IGenericRepository<T> {
    fun getAll(context: Context): List<T>

}