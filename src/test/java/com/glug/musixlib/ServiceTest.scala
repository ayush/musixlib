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

/*
 * User: ayush
 * Date: 5/23/11
 * Time: 9:20 AM
 */

import org.scalatest.FunSuite
import com.glug.musicxlib.service._
class ServiceTest extends FunSuite {

  private def service: MusixMatchService = {
    new MusixMatchService("741422372f12076301e6f5ef02c07862")
  }


  test("is able to create query string") {
    val searchRequest = new SearchArtist()
    searchRequest.q = "explosions in the sky"
    val qs = searchRequest.queryString
    assert(qs != null && qs.length() > 0)

  }

  test("is able to search for Moby") {
    val searchRequest = new SearchArtist()
    searchRequest.q_artist = "moby"

    val serviceResponse = service.search(searchRequest)
    assert(serviceResponse!= null && serviceResponse.message.body.artist_list.size() > 0)
//    println(serviceResponse.message.body.artist_list.iterator().next())
  }

  test("is able to get Moby") {
    val serviceResponse = service.getArtist(new GetArtist("202"))
    assert(serviceResponse!= null && serviceResponse.message.body.artist != null)
    assert(serviceResponse!= null && serviceResponse.message.body.artist.artist_alias_list.size() > 0)
//    println(serviceResponse.message.body.artist)
  }

  test("is able to get Moby's Albums") {
    val serviceResponse = service.getArtistAlbums(new GetArtistAlbums("202"))
    assert(serviceResponse!= null && serviceResponse.message.body.album_list.size() > 0)
  }

  test("is able to get Moby's Album Play") {
    val serviceResponse = service.getAlbum(new GetAlbum("10275958"))
    val album = serviceResponse.message.body.album
    assert(serviceResponse!= null && album != null, "No album retrieved")
    assert(serviceResponse!= null && album.album_name.equals("Play"), "Expected album Play, got something else")
    assert(serviceResponse!= null && album.artist_name.equals("Moby"), "Expected album artist Moby, got something else")
//    println(album)
  }


  test("is able to get US Charts") {
    val serviceResponse = service.getArtistCharts(new GetArtistChart("US"))

    assert(serviceResponse!= null && serviceResponse.message.body.artist_list.size() > 0, "Unable to get charts for US")
  }

  test("is able to get the track Porcelain by Moby") {
    val serviceResponse = service.getMatcherTrack(new GetMatcherTrack("Porcelain", "Moby", "Play"))

    assert(serviceResponse.message.body.track != null, "Unable to fetch track")
    assert(serviceResponse.message.body.track.track_id.equals("673092") && serviceResponse.message.body.track.track_mbid.equals("1e3668b5-3053-40f7-8bda-0ba987b37cbe"), "track id did not match the expected one")
//    println(serviceResponse.message.body.track)
  }

  test("is able to get track Charts") {
    val serviceResponse = service.getTrackCharts(new GetTrackChart("US"))

    assert(serviceResponse.message.body.track_list.size() > 0, "Unable to fetch track charts")
//    println(serviceResponse.message.body.track_list.iterator().next().track)
  }

  test("is able to search for kaleidoscopic and find Porcelain") {
    val searchTrack = new SearchTrack()
    searchTrack.q_lyrics = "kaleidoscopic"
    searchTrack.q_artist = "Moby"

    val serviceResponse = service.search(searchTrack)

    assert(serviceResponse.message.body.track_list.size() > 0, "Unable to search for Porcelain")
    val firstTrack = serviceResponse.message.body.track_list.iterator().next().track
    assert(firstTrack.track_id.equals("673092") && firstTrack.track_mbid.equals("1e3668b5-3053-40f7-8bda-0ba987b37cbe"), "track id did not match the expected one")
//    println(firstTrack)
  }

  test("is able to get tracks for album Play") {
    val serviceResponse = service.getAlbumTracks(new GetAlbum("10275958"))

    assert(serviceResponse.message.body.track_list.size() > 0, "Unable to get tracks for album Play")
    val firstTrack = serviceResponse.message.body.track_list.iterator().next().track
    assert(firstTrack.track_id.equals("673090") && firstTrack.track_mbid.equals("1618b28c-2de2-428a-a460-fbe451b5fbde"), "track id did not match the expected one")
//    println(firstTrack)
  }

  test("is able to get track by id") {
    val serviceResponse = service.getTrack(new GetTrack("673092"))

    val track = serviceResponse.message.body.track
    assert(track != null, "Unable to fetch track")
    assert(track.track_id.equals("673092") && track.track_mbid.equals("1e3668b5-3053-40f7-8bda-0ba987b37cbe"), "track id did not match the expected one")
  }


  test("is able to get track lyrics for Faith") {
    val serviceResponse = service.getLyrics(new GetTrack("745388"))

    assert(serviceResponse.message.body.lyrics != null && serviceResponse.message.body.lyrics.lyrics_body != null)

  }

}