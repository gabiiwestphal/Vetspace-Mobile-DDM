package com.example.ddm_vetspace.repository

import android.content.ContentValues
import com.example.ddm_vetspace.database.DatabaseHelper
import com.example.ddm_vetspace.services.blogService
import com.example.ddm_vetspace.model.Blog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlogRepository (private val dbHelper: DatabaseHelper) {
    suspend fun getBlogs(): Result<List<Blog>> {
        return withContext(Dispatchers.IO) {
            val db = dbHelper.readableDatabase
            val cursor = db.query(
                "blog",
                arrayOf("blog_id", "descricao", "user_id", "titulo", "data"),
                null, null, null, null, null
            )

            val blogs = mutableListOf<Blog>()
            while (cursor.moveToNext()) {
                val blog = Blog(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("blog_id")),
                    descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao")),
                    user_id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                    titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                    data = cursor.getString(cursor.getColumnIndexOrThrow("data"))
                )
                blogs.add(blog)
            }
            cursor.close()
            db.close()

            Result.success(blogs)
        }
    }

    suspend fun inserirBlog(blog: Blog) {
        withContext(Dispatchers.IO) {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("blog_id", blog.id)
                put("descricao", blog.descricao)
                put("user_id", blog.user_id)
                put("titulo", blog.titulo)
                put("data", blog.data)
            }
            db.insert("blog", null, values)
        }
    }
}