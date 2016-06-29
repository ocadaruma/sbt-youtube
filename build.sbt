lazy val root = (project in file("."))
  .settings(
    sbtPlugin := true,
    name := "sbt-youtube",
    licenses += "MIT" -> url("https://raw.githubusercontent.com/ocadaruma/sbt-youtube/master/LICENSE"),

    version := "0.1.2-SNAPSHOT",
    organization := "com.mayreh",
    organizationName := "Haruki Okada",
    startYear := Some(2016),

    libraryDependencies ++= Seq(
      "org.seleniumhq.selenium" % "selenium-chrome-driver" % "2.53.0"
    )
  )
