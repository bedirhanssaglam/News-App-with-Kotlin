package com.android.newsapp.presentation.util.helpers

import androidx.room.TypeConverter
import com.android.newsapp.domain.models.Source

class Converters  {
    @TypeConverter
     fun fromSource(source: Source): String = source.name

    @TypeConverter
     fun toSource(name: String): Source = Source(name, name)
}