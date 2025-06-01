# Build and run Haveno-dawn

These are the steps to build and run Haveno-dawn.

## Install dependencies

On Ubuntu: `sudo apt install make wget git`

On Windows, first install MSYS2:
  1. Install [MSYS2](https://www.msys2.org/).
  2. Start MSYS2 MINGW64 or MSYS MINGW32 depending on your system. Use MSYS2 for all commands throughout this document.
  4. Update pacman: `pacman -Syy`
  5. Install dependencies. During installation, use default=all by leaving the input blank and pressing enter.

      64-bit: `pacman -S mingw-w64-x86_64-toolchain make mingw-w64-x86_64-cmake git zip unzip`

      32-bit: `pacman -S mingw-w64-i686-toolchain make mingw-w64-i686-cmake git zip unzip`

On all platforms, install Java JDK 21:

```
curl -s "https://get.sdkman.io" | bash
sdk install java 21.0.2.fx-librca
```

Restart the terminal for the changes to take effect.

## Build Haveno-dawn

If it's the first time you are building Haveno, run the following commands to download the repository, the needed dependencies, and build the latest release:

```
git clone https://github.com/dawn-collective/haveno-dawn.git
cd haveno
git checkout master
make
```

*If you only want to quickly build the binaries, use `make skip-tests` instead of `make`. It will skip the tests and increase the build speed drastically.*

If you are updating from a previous version, run from the root of the repository:

```
git checkout master
git pull
make clean && make
```

## Run Haveno-dawn

> [!note]
> When you run Haveno-dawn, your application folder will be installed to:
> * Linux: `~/.local/share/Haveno-dawn/`
> * macOS: `~/Library/Application\ Support/Haveno-dawn/`
> * Windows: `~\AppData\Roaming\Haveno-dawn\`
