package com.example.todowatanabeapplication;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class converter {
    // LocalDateをStringに変換してデータベースに保存
    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }

    // StringをLocalDateに変換してJavaオブジェクトとして取得
    @TypeConverter
    public static LocalDate toLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }
}
