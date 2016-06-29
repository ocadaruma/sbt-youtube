package com.mayreh.sbt.youtube

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import sbt._, Keys._

object SbtPlugin extends AutoPlugin {

  object autoImport {
    private[this] val briefHelp = "Play youtube video while executing command."
    private[this] val detail =
      """Play youtube video while executing command.
        |Video will be randomly chosen from `youtubePlaylist`.
      """.stripMargin

    lazy val u = Command("u", ("u <command>", briefHelp), detail)(BasicCommands.otherCommandParser) { (state, args) =>
      var driver: WebDriver = null
      var tempFile: File = null

      try {
        val playlist = Project.extract(state).get(youtubePlaylist)
        playlist.headOption.foreach { _ =>
          val videoId = playlist(scala.util.Random.nextInt(playlist.size))

          tempFile = java.io.File.createTempFile("player", ".html")
          Using.fileWriter(IO.utf8, append = false)(tempFile)(_.write(playerHtml(videoId)))

          driver = new ChromeDriver()
          driver.get(tempFile.toURI.toString)
        }

        Command.process(args, state)
      } finally {
        Option(driver).foreach { d =>
          try d.quit()
          catch { case _: Throwable => }
        }
        Option(tempFile).foreach(_.delete())
      }
    }

    lazy val youtubePlaylist = SettingKey[Seq[String]]("youtube-playlist", "Specify youtube video ids to play.")
  }

  import autoImport._

  override def trigger = allRequirements

  override def globalSettings = Seq(
    commands += u,
    youtubePlaylist := Nil
  )

  private[this] def playerHtml(videoId: String): String =
    raw"""<!DOCTYPE html>
       |<html>
       |  <body style="background: #000; text-align: center;">
       |    <pre style="color: #0f0;">
       |   _____  ____  ______  __  __ ____   __  __ ______ __  __ ____   ______
       |  / ___/ / __ )/_  __/  \ \/ // __ \ / / / //_  __// / / // __ ) / ____/
       |  \__ \ / __  | / /______\  // / / // / / /  / /  / / / // __  |/ __/   
       | ___/ // /_/ / / //_____// // /_/ // /_/ /  / /  / /_/ // /_/ // /___   
       |/____//_____/ /_/       /_/ \____/ \____/  /_/   \____//_____//_____/   
       |                                                                
       |
       |    </pre>
       |
       |    <div id="player"></div>
       |
       |    <script>
       |     var tag = document.createElement('script');
       |
       |     tag.src = "https://www.youtube.com/iframe_api";
       |     var firstScriptTag = document.getElementsByTagName('script')[0];
       |     firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
       |
       |     var player;
       |     function onYouTubeIframeAPIReady() {
       |       player = new YT.Player('player', {
       |         height: '390',
       |         width: '640',
       |         videoId: '$videoId',
       |         events: {
       |           'onReady': onPlayerReady,
       |           'onStateChange': onPlayerStateChange
       |         }
       |       });
       |     }
       |
       |     function onPlayerReady(event) {
       |       event.target.setVolume(100);
       |       event.target.playVideo();
       |     }
       |
       |     function onPlayerStateChange(event) {
       |       if (event.data === YT.PlayerState.ENDED) {
       |         event.target.playVideo();
       |       }
       |     }
       |    </script>
       |  </body>
       |</html>
       |""".stripMargin
}
