// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*
plugins {
    id("com.android.application") version libs.versions.androidGradlePlugin apply false
    id("com.android.library") version libs.versions.androidGradlePlugin apply false
//jetbrains-kotlin-plugin-compose
//    id("org.jetbrains.kotlin.android") version libs.versions.jetbrainsKotlin apply false
    id("org.jetbrains.kotlin.plugin.compose") version libs.versions.kotlin apply false
//    id(libs.versions.plugins.jetbrains.kotlin.plugin.compose) version libs.versions.jetbrainsKotlin apply false
//?    alias(libs.plugins.android.dynamic.feature) apply false
    alias(libs.plugins.kotlin.serialization) apply false


    id("com.google.devtools.ksp") version libs.versions.ksp apply false
    id("com.google.dagger.hilt.android") version libs.versions.hilt apply false

    // Plugin для настройки параметров компилятора Room
    id("androidx.room") version libs.versions.room apply false
}
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

 */
/*
 * By listing all the plugins used throughout all subprojects in the root project build script, it
 * ensures that the build script classpath remains the same for all projects. This avoids potential
 * problems with mismatching versions of transitive plugin dependencies. A subproject that applies
 * an unlisted plugin will have that plugin and its dependencies _appended_ to the classpath, not
 * replacing pre-existing dependencies.
 * * Если перечислить все плагины, используемые во всех подпроектах, в сценарии сборки корневого проекта, это
* гарантирует, что путь к классам (classpath) сценария сборки останется одинаковым для всех проектов. Тем самым
* предотвращаются возможные проблемы, связанные с несовпадением версий транзитивных зависимостей плагинов. Если в подпроекте
* применяется плагин, не указанный в этом списке, то такой плагин и его зависимости будут _добавлены_ к пути к классам,
* не заменяя при этом уже существующие зависимости.
 */
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.dependencyGuard) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.gms) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.roborazzi) apply false
    alias(libs.plugins.google.osslicenses) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.spotless) apply false
//    alias(libs.plugins.nowinandroid.root)
}
