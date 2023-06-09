plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
apply{
    from("$rootDir/base-ui-module.gradle")
}
android {
    namespace = "com.hossain_ehs.tracker_presentation"
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.trackerDomain))
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    "implementation"(Coil.coilCompose)

}