plugins {
    id ("hegunhee.application")
    id ("kotlin-android")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
}

android {

}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":feature"))
    implementation(project(":common_resource"))

}