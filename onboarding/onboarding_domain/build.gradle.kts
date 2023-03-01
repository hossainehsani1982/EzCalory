plugins {
    id("com.android.library")
}
apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.hossain_ehs.onboarding_domain"
}

dependencies {
    "implementation"(project(Modules.core))
}