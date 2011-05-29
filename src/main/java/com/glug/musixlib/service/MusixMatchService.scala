/*
 * Copyright (c) 2011 Ayush Gupta (http://glugbot.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.glug.musixlib.service

import com.glug.musixlib.util.AsQueryString
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
    call(makeApiUrl(SEARCH_ARTIST, artistSearchRequest))
  }

  /**
    * Get details on a specific artist
    */
  def getArtist(req: GetArtist) = {
    call(makeApiUrl(GET_ARTIST, req))
  }


  /**
    * Get albums of a specific artist
    */
  def getArtistAlbums(req: GetArtistAlbums) = {
    call(makeApiUrl(GET_ARTIST_ALBUMS, req))
  }

  /**
    *
    * Get a specific album
    */
  def getAlbum(req: GetAlbum) = {
    call(makeApiUrl(GET_ALBUM, req))
  }

  /**
    *
    * Get tracks of an album
    */
  def getAlbumTracks(req: GetAlbum) = {
    call(makeApiUrl(GET_ALBUM_TRACKS, req))
  }


  /**
    *
    * Get charts for a specific artist
    */
  def getArtistCharts(req: GetArtistChart) = {
    call(makeApiUrl(GET_ARTIST_CHART, req))
  }


  /**
    *
    * Look for tracks
    */
  def getMatcherTrack(req: GetMatcherTrack) = {
    call(makeApiUrl(GET_MATCHER_TRACK, req))
  }

  /**
    *
    * Get charts for tracks
    */
  def getTrackCharts(req: GetTrackChart) = {
    call(makeApiUrl(GET_TRACK_CHART, req))
  }

  /**
    *
    * Search for tracks
    */
  def search(req: SearchTrack) = {
    call(makeApiUrl(SEARCH_TRACK, req))
  }


  /**
    *
    * Get track
    */
  def getTrack(req: GetTrack) = {
    call(makeApiUrl(GET_TRACK, req))
  }

  /**
    *
    * Get lyrics
    */
  def getLyrics(req: GetTrack) = {
    call(makeApiUrl(GET_TRACK_LYRICS, req))
  }


  private def makeApiUrl(apiName: String, qsObject: AsQueryString) = apiEndpoint + apiName + "?apikey=" + apiKey + "&format=json&" + qsObject.queryString()

  private def call(url: String) = {
//    LOGGER.info("Calling " + url)

    val jsonResponse = io.Source.fromURL(url).mkString
//    LOGGER.info("got " + jsonResponse)

    val aux = GSON.fromJson(jsonResponse, classOf[AuxServiceResponse])
    aux.check

    try {
      if (aux.is404) {
        val r = new ServiceResponse
        r.message = new Message
        r.message.header = aux.message.header
        r
      } else
        GSON.fromJson(jsonResponse, classOf[ServiceResponse])
    }
    catch {
      case e: Exception => LOGGER.error("Unable to unmarshall json " + jsonResponse, e); throw e
    }

  }

}

