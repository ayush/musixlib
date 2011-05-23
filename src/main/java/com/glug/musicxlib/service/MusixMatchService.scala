/*
 * Copyright (c) 2011 Ayush Gupta (http://glugbot.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.glug.musicxlib.service

/*
 * Copyright (c) 2011 Ayush Gupta (http://glugbot.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.glug.musicxlib.util.AsQueryString
import com.google.gson.Gson
import org.slf4j.LoggerFactory


/**
  *
  * Executes calls against the MusixMatch Service.
  * Typically, the higher level methods in api package should be all you need but you could
  * use this for direct access to MusixMatch service.
  *
  * @author Ayush Gupta
  * @since 5/21/11 10:14 PM
  */
class MusixMatchService(val apiKey: String) {
  private val LOGGER = LoggerFactory.getLogger(classOf[MusixMatchService])
  private val GSON = new Gson();

  private val apiEndpoint = "http://api.musixmatch.com/ws/1.1/"
  private val SEARCH_ARTIST = "artist.search"
  private val GET_ARTIST = "artist.get"
  private val GET_ARTIST_ALBUMS = "artist.albums.get"
  private val GET_ALBUM = "album.get"
  private val GET_ALBUM_TRACKS = "album.tracks.get"
  private val GET_ARTIST_CHART = "artist.chart.get"
  private val GET_MATCHER_TRACK = "matcher.track.get"
  private val GET_TRACK_CHART = "track.chart.get"
  private val SEARCH_TRACK = "track.search"
  private val GET_TRACK = "track.get"
  private val GET_TRACK_SUBTITLE = "track.subtitle.get"
  private val GET_TRACK_LYRICS = "track.lyrics.get"

  /**
    * Search for artists
    */
  def search(artistSearchRequest: SearchArtist) = {
    val jsonResponse = call(makeApiUrl(SEARCH_ARTIST, artistSearchRequest))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }

  /**
    * Get details on a specific artist
    */
  def getArtist(req: GetArtist) = {
    val jsonResponse = call(makeApiUrl(GET_ARTIST, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }


  /**
    * Get albums of a specific artist
    */
  def getArtistAlbums(req: GetArtistAlbums) = {
    val jsonResponse = call(makeApiUrl(GET_ARTIST_ALBUMS, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }

  /**
    *
    * Get a specific album
    */
  def getAlbum(req: GetAlbum) = {
    val jsonResponse = call(makeApiUrl(GET_ALBUM, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }

  /**
    *
    * Get tracks of an album
    */
  def getAlbumTracks(req: GetAlbum) = {
    val jsonResponse = call(makeApiUrl(GET_ALBUM_TRACKS, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }


  /**
    *
    * Get charts for a specific artist
    */
  def getArtistCharts(req: GetArtistChart) = {
    val jsonResponse = call(makeApiUrl(GET_ARTIST_CHART, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }


  /**
    *
    * Look for tracks
    */
  def getMatcherTrack(req: GetMatcherTrack) = {
    val jsonResponse = call(makeApiUrl(GET_MATCHER_TRACK, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }

  /**
    *
    * Get charts for tracks
    */
  def getTrackCharts(req: GetTrackChart) = {
    val jsonResponse = call(makeApiUrl(GET_TRACK_CHART, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }

  /**
    *
    * Search for tracks
    */
  def search(req: SearchTrack) = {
    val jsonResponse = call(makeApiUrl(SEARCH_TRACK, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }


  /**
    *
    * Get track
    */
  def getTrack(req: GetTrack) = {
    val jsonResponse = call(makeApiUrl(GET_TRACK, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }

  /**
    *
    * Get lyrics
    */
  def getLyrics(req: GetTrack) = {
    val jsonResponse = call(makeApiUrl(GET_TRACK_LYRICS, req))
//    LOGGER.info("got " + jsonResponse)
    GSON.fromJson(jsonResponse, classOf[ServiceResponse])
  }


  private def makeApiUrl(apiName: String, qsObject: AsQueryString) = apiEndpoint + apiName + "?apikey=" + apiKey + "&format=json&" + qsObject.queryString()

  private def call(url: String) = {
    LOGGER.info("Calling " + url)

    io.Source.fromURL(url).mkString
  }

}

