# sbt-youtube

No more boredom!

## Requirements

- Google Chrome
- ChromeDriver (https://sites.google.com/a/chromium.org/chromedriver/)

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
