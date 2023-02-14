# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.22.1)

# Declares and names the project.

project("firstlab")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        firstlab
        SHARED
        native-lib.cpp)


add_library(spdlog STATIC IMPORTED)
set_target_properties(spdlog PROPERTIES IMPORTED_LOCATION
        /Users/yungnik/AndroidStudioProjects/spdlog/build/arm64-v8a/libspdlog.a)

add_library(mbedcrypto SHARED IMPORTED)
set_target_properties(mbedcrypto PROPERTIES IMPORTED_LOCATION
        /Users/yungnik/AndroidStudioProjects/mbedtls/build/arm64-v8a/library/libmbedcrypto.so)

target_include_directories(firstlab PUBLIC
        /Users/yungnik/AndroidStudioProjects/mbedtls/mbedtls/include
        /Users/yungnik/AndroidStudioProjects/spdlog/spdlog/include)


find_library(
        log-lib
        log)


# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        firstlab
        mbedcrypto
        spdlog
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})
