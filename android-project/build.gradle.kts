// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    //id("androidx.navigation.safeargs.kotlin") version "2.5.1" apply false
}

buildscript{
    repositories{
        google()
    }
    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}