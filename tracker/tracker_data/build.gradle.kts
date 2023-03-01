plugins {
    id("com.android.library")
}
apply{
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.hossain_ehs.tracker_data"
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))
}