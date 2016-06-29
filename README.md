# sbt-youtube

No more compile time boredom!

This plugin is inspired by [sbt-musical](https://github.com/tototoshi/sbt-musical).

## Requirements

- Google Chrome
- ChromeDriver (https://sites.google.com/a/chromium.org/chromedriver/)

You have to add a chromedriver directory to PATH or specify chromedriver path via `webdriver.chrome.driver` java property.

```bash
$ export PATH="$PATH:/path/to/chromedriver_directory"
or
$ sbt -Dwebdriver.chrome.driver=/path/to/chromedriver
```

If you've installed chromedriver via `homebrew`, you don't need to do this.

## Usage

### Installation

Add the plugin in `project/plugins.sbt`:

```scala
addSbtPlugin("com.mayreh" % "sbt-youtube" % "0.1.0")
```

Setup playlist in `(project root)/youtube.sbt`:

```scala
// list of youtube video ids.
youtubePlaylist ++= Seq(
  // Perfume
  "q6T0wOMsNrI", // FLASH
  "K54CYowOqxM", // レーザービーム
  "7PtvnaEo9-0", // Spring of Life
  "H4znsXCH_2Y", // Spending all my time
  "guqVgQFvXXY", // Cling Cling
  "CYL3DnyA4e0", // Sweet Refrain
  "vhfYis6VuXY", // Pick Me Up
  "ihNaFCEd0Ms"  // 1mm
)
```

### Hook any command

Prefix `u` to any command.

```bash
$ sbt
> u compile
```

## License

Published under the MIT License.
