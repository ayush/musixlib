/*
 * Copyright (c) 2011 Ayush Gupta (http://glugbot.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.glug.musixlib

import scala.collection.JavaConversions._
import org.scalatest.FunSuite
import com.glug.musixlib.api.MusixMatch
/*
 * User: ayush
 * Date: 5/24/11
 * Time: 9:08 AM
 */
class ApiTest extends FunSuite {
  private def api = new MusixMatch(API_KEY.key)

  test("is able to find exact artist lada gaga") {
    val artists = api.findExactArtistsByName("lady gaga")
    assert(artists.length > 0)

    for (artist <- artists) {
      println(artist)
    }
  }

  test("is able to get nine inch nails's albums") {
    val albums = api.getArtistAlbums("193")
    assert(albums.length > 0)
    println("Got " + albums.length + " albums")
    for (album <- albums) {
      println(album)
    }

  }

  test("is able to get Madonna's albums") {
    val albums = api.getArtistAlbums("61")
    assert(albums.length > 0)
    println("Got " + albums.length + " albums")
    for (album <- albums) {
      println(album)
    }

  }

  test("is able to get Tori Amos's albums") {
    val albums = api.getArtistAlbums("46")
    assert(albums.length > 0)
    println("Got " + albums.length + " albums")
    for (album <- albums) {
      println(album)
    }

  }

  test("is able to get tracks for Album Faith") {
    val tracks = api.getAlbumTracks("10282648")
    assert(tracks.length > 0)
    println("Got " + tracks.length + " tracks")
    for (t <- tracks) {
      println(t)
    }

  }

  test("is able to get tyrics for track Faith") {
    val lyrics = api.getLyrics("745388")
    assert(lyrics != null && lyrics.body.length() > 0)
    println(lyrics.body)

  }

  test("is able to call for track with no lyrics") {
      val lyrics = api.getLyrics("855390")
      assert(lyrics == null)

  }


}