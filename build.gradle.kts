@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.kotlinAndroid) apply false
  alias(libs.plugins.kotlinKsp) apply false
  alias(libs.plugins.googleServices) apply false
  alias(libs.plugins.hiltAndroid) apply false
}
