buildscript {
    extra.apply {
        set("glide_version", "4.14.1")
        set("room_version", "2.4.3")
        set("hilt_version", "2.42")
        set("hilt_compiler_version", "1.1.0")
        set("retrofit_version", "2.9.0")
        set("logging_interceptor_version", "4.9.0")
        set("kotlin_coroutines_version", "1.3.9")
        set("lifecycle_version", "2.2.0")
        set("koin_version", "2.1.6")
        set("dagger_version", "2.29.1")
        set("activity_ktx_version", "1.1.0")
        set("fragment_ktx_version", "1.2.5")
        set("round_image_version", "2.3.0")
        set("circle_progress_version", "1.0")
        set("sql_chipper_version", "4.4.0")
        set("sqlite_version", "2.1.0")
    }

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
       

    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hilt_version"]}")
    }

}



// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.android.library") version "8.2.1" apply false
    id("com.android.dynamic-feature") version "8.2.1" apply false
}
