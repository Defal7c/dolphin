# ![logo](/Data/dolphin-emu.png "logo") BrGL-Dolphin
## A Sonic Riders centric fork of Dolphin.

This project aims to be an updated and modified version of Dolphin, centered around **Sonic Riders** and
**Sonic Riders: Zero Gravity** Netplay.

Features:
  - Up-to-date code base (based on Dolphin 5.0-18992 currently)
  - ExGL Configurations
  - Gecko Codes for 16:9 support and other _QoL_ stuff 
  - Controllers presets (also from ExGL)
  - Better Discord Rich Presence (it shows when you are playing Sonic Riders!)
  - Better Linux support
  - ~~OpenBSD support~~ (in the near future)

## Original Dolphin ReadMe:

## Building for Windows

Use the solution file `Source/dolphin-emu.sln` to build Dolphin on Windows.
Visual Studio 2022 17.2.3 or later is a hard requirement. Other compilers might be
able to build Dolphin on Windows but have not been tested and are not
recommended to be used. Git and Windows 11 SDK must be installed when building.

Make sure to pull submodules before building:
```sh
git submodule update --init
```

The "Release" solution configuration includes performance optimizations for the best user experience but complicates debugging Dolphin.
The "Debug" solution configuration is significantly slower, more verbose and less permissive but makes debugging Dolphin easier.

## Building for Linux and macOS

Dolphin requires [CMake](https://cmake.org/) for systems other than Windows. Many libraries are
bundled with Dolphin and used if they're not installed on your system. CMake
will inform you if a bundled library is used or if you need to install any
missing packages yourself. You may refer to the [wiki](https://github.com/dolphin-emu/dolphin/wiki/Building-for-Linux) for more information.

Make sure to pull submodules before building:
```sh
git submodule update --init
```

### macOS Build Steps:

A binary supporting a single architecture can be built using the following steps: 

1. `mkdir build`
2. `cd build`
3. `cmake ..`
4. `make -j $(sysctl -n hw.logicalcpu)`

An application bundle will be created in `./Binaries`.

A script is also provided to build universal binaries supporting both x64 and ARM in the same
application bundle using the following steps:

1. `mkdir build`
2. `cd build`
3. `python ../BuildMacOSUniversalBinary.py`
4. Universal binaries will be available in the `universal` folder

Doing this is more complex as it requires installation of library dependencies for both x64 and ARM (or universal library
equivalents) and may require specifying additional arguments to point to relevant library locations. 
Execute BuildMacOSUniversalBinary.py --help for more details.  

### Linux Global Build Steps:

To install to your system.

1. `mkdir build`
2. `cd build`
3. `cmake ..`
4. `make -j $(nproc)`
5. `sudo make install`

### Linux Local Build Steps:

Useful for development as root access is not required.

1. `mkdir Build`
2. `cd Build`
3. `cmake .. -DLINUX_LOCAL_DEV=true`
4. `make -j $(nproc)`
5. `ln -s ../../Data/Sys Binaries/`

### Linux Portable Build Steps:

Can be stored on external storage and used on different Linux systems.
Or useful for having multiple distinct Dolphin setups for testing/development/TAS.

1. `mkdir Build`
2. `cd Build`
3. `cmake .. -DLINUX_LOCAL_DEV=true`
4. `make -j $(nproc)`
5. `cp -r ../Data/Sys/ Binaries/`
6. `touch Binaries/portable.txt`

## Building for Android

These instructions assume familiarity with Android development. If you do not have an
Android dev environment set up, see [AndroidSetup.md](AndroidSetup.md).

Make sure to pull submodules before building:
```sh
git submodule update --init
```

If using Android Studio, import the Gradle project located in `./Source/Android`.

Android apps are compiled using a build system called Gradle. Dolphin's native component,
however, is compiled using CMake. The Gradle script will attempt to run a CMake build
automatically while building the Java code.
