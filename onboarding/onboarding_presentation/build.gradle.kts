plugins {
    id("com.android.library")
}
apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.hossain_ehs.onboarding_presentation"
}

dependencies {
    "implementation"(project(":core"))
    "implementation"(project(":onboarding:onboarding_domain"))
}