/*
 * Copyright (c) 2011 Ayush Gupta (http://glugbot.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.glug.musicxlib.api

import scala.collection.JavaConversions._
import com.glug.musicxlib.service._

/*
* User: ayush
* Date: 5/24/11
* Time: 8:57 AM
*/
class MusixMatch(val apiKey: String) {
  private val ALBUM = "Album"

  private def service: MusixMatchService = {
    new MusixMatchService(apiKey)
  }

  def findExactArtists(keyword: String): Seq[Artist] = {
    val serviceResponse = service.search(new SearchArtist(null, null, keyword))

    if (serviceResponse.artists == null)
      null
    else
      serviceResponse.artists.filter((a: Artist) => a.name.equalsIgnoreCase(keyword))
  }

  def getArtistAlbums(artistId: String): Seq[Album] = {
    val serviceResponse = service.getArtistAlbums(new GetArtistAlbums(artistId.toString, 1, 100))

    if (serviceResponse.albums == null)
      null
    else {
      serviceResponse.albums.filter((a: Album) => a.releaseType.equals(ALBUM))

      if (serviceResponse.albums.length > 20)
        serviceResponse.albums.subList(0, 20)
      else
        serviceResponse.albums
    }
  }

  def getAlbumTracks(albumId: String): Seq[Track] = {
    val serviceResponse = service.getAlbumTracks(new GetAlbum(albumId))

    if (serviceResponse.tracks == null)
      null
    else
      serviceResponse.tracks
  }

  def getLyrics(trackId: String): Lyrics = {
    val serviceResponse = service.getLyrics(new GetTrack(trackId))

    if (serviceResponse.lyrics == null)
      null
    else
      serviceResponse.lyrics
  }
}