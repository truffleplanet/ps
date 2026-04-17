cmake_minimum_required(VERSION 4.2)
project(cses)

set(CMAKE_CXX_STANDARD 20)

file(GLOB_RECURSE ALL_SOURCES
        "*/*.cpp"
)
list(FILTER ALL_SOURCES EXCLUDE REGEX ".*/cmake-build-debug/.*")
list(FILTER ALL_SOURCES EXCLUDE REGEX ".*/cmake-build-release/.*")

foreach(SOURCE ${ALL_SOURCES})
    get_filename_component(FILE_NAME ${SOURCE} NAME_WE)
    get_filename_component(DIR_NAME ${SOURCE} DIRECTORY)
    get_filename_component(PARENT_DIR ${DIR_NAME} NAME)

    set(TARGET_NAME "${PARENT_DIR}_${FILE_NAME}")

    add_executable(${TARGET_NAME} ${SOURCE})
endforeach()
