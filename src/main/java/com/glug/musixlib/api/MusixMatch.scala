/*
 * Copyright (c) 2011 Ayush Gupta (http://glugbot.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.glug.musixlib.api

import scala.collection.JavaConversions._
import com.glug.musixlib.service._

/**
  * Main class for using MusixMatch Service. To use create an instance by supplying API key and use its methods.
  *
  * @author Ayush Gupta
  * @since 5/24/11
  *
  */
class MusixMatch(val apiKey: String) {
  private val ALBUM = "Album"

  private def service: MusixMatchService = {
    new MusixMatchService(apiKey)
  }

  /**
    * Find artists by specifying full criteria in a SearchArtist object
    */
  def findArtists(v: SearchArtist) = service.search(v).artists

  /**
    * Finds all artists whose name exactly match the passed value
    */
  def findExactArtistsByName(keyword: String, page: Int = -1, pageSize: Int = -1): Seq[Artist] = {
    val serviceResponse = service.search(new SearchArtist(null, null, keyword, null, null, null, null, page, pageSize))

    if (serviceResponse.artists == null)
      null
    else
      serviceResponse.artists.filter((a: Artist) => a.name.equalsIgnoreCase(keyword))
  }

  /**
    * Finds all artist with names similar to the one passed
    */
  def findArtistsByName(keyword: String, page: Int, pageSize: Int): Seq[Artist] = {
    val serviceResponse = service.search(new SearchArtist(null, null, keyword, null, null, null, null, page, pageSize))

    serviceResponse.artists
  }

  /**
    * Gets a specific artist
    */
  def getArtist(artistId: String) = service.getArtist(new GetArtist(artistId)).artist

  /**
    * Get albums of a specific artist
    */
  def getArtistAlbums(artistId: String, page: Int = -1, pageSize: Int = -1): Seq[Album] = {
    val serviceResponse = service.getArtistAlbums(new GetArtistAlbums(artistId.toString, page, pageSize))

    var albumsList = serviceResponse.albums
    if (albumsList == null)
      null
    else {
      albumsList = albumsList.filter((a: Album) => a.releaseType.equals(ALBUM))

      albumsList = albumsList.sortWith((a: Album, b: Album) => (a.releaseDate != null && b.releaseDate == null) || (a.releaseDate != null && b.releaseDate != null && a.releaseDate.compareTo(b.releaseDate) >= 0))

      albumsList
    }
  }

  /**
    * Get albums of a specific artist which satisfy criteria specified in the passed GetArtistAlbums object
    */
  def getArtistAlbums(v: GetArtistAlbums) = service.getArtistAlbums(v).albums

  /**
    * Get a specific album
    */
  def getAlbum(albumId: String) = service.getAlbum(new GetAlbum(albumId)).album

  /**
    * Get all tracks of a given album
    */
  def getAlbumTracks(albumId: String): Seq[Track] = {
    val serviceResponse = service.getAlbumTracks(new GetAlbum(albumId))

    if (serviceResponse.tracks == null)
      null
    else
      serviceResponse.tracks
  }

  /**
    * Get top artists
    */
  def getArtistCharts(country: String = null, page: Int = -1, pageSize: Int = -1) = service.getArtistCharts(new GetArtistChart(country, page, pageSize)).artists

  /**
    * Get top tracks
    */
  def getTrackCharts(country: String = null, hasLyrics: String = null, page: Int = -1, pageSize: Int = -1) = service.getTrackCharts(new GetTrackChart(country, hasLyrics, page, pageSize)).tracks

  /**
    * Get tracks which match the criteria specified in passed GetMatcherTrack object
    */
  def getMatchingTracks(m: GetMatcherTrack) = service.getMatcherTrack(m).tracks

  /**
    * Get tracks which satify the criteria specified in passed SearchTrack object
    */
  def searchTracks(s: SearchTrack) = service.search(s).tracks

  /**
    * Get lyrics of a given track
    */
  def getLyrics(trackId: String): Lyrics = {
    val serviceResponse = service.getLyrics(new GetTrack(trackId))

    if (serviceResponse.lyrics == null)
      null
    else
      serviceResponse.lyrics
  }
}