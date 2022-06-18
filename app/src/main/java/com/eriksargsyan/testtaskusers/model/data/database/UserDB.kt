package com.eriksargsyan.testtaskusers.model.data.database

import androidx.room.*
import com.eriksargsyan.testtaskusers.model.data.domain.EyeColor
import com.eriksargsyan.testtaskusers.model.data.domain.Fruit
import com.eriksargsyan.testtaskusers.model.data.domain.Gender
import com.eriksargsyan.testtaskusers.model.database.UserRoomDatabase

@Entity(tableName = UserDB.TABLE_NAME)
data class UserDB(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "is_active")
    val isActive: Boolean,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "eye_color")
    val eyeColor: EyeColor,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "about")
    val about: String,

    @ColumnInfo(name = "registered")
    val registered: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "friend_ids")
    val friendIds: List<Int>,

    @ColumnInfo(name = "favorite_fruit")
    val favoriteFruit: Fruit
) {
    companion object {
        const val TABLE_NAME = "user_table"
        const val ID = "id"
    }
}

class EyeColorConverter {

    @TypeConverter
    fun fromEyeColor(value: EyeColor): String {
        return value.str
    }

    @TypeConverter
    fun toEyeColor(value: String): EyeColor {
        return EyeColor.fromStr(value)
    }
}

class FruitConverter {

    @TypeConverter
    fun fromFruit(value: Fruit): String {
        return value.str
    }

    @TypeConverter
    fun toFruit(value: String): Fruit {
        return Fruit.fromStr(value)
    }
}

class GenderConverter {

    @TypeConverter
    fun fromGender(value: Gender): String {
        return value.str
    }

    @TypeConverter
    fun toGender(value: String): Gender {
        return Gender.fromStr(value)
    }
}

class FriendsConverter {

    @TypeConverter
    fun toFriends(friends: String): List<Int> {
        return friends.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun fromFriends(friends: List<Int>): String {
        return friends.joinToString(separator = ",")
    }
}
