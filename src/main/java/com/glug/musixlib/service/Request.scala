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

/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/artist-search">artist.search</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param q a string that will be searched in every data field (q_track, q_artist, q_lyrics)
  * @param q_track words to be searched among tracks titles
  * @param q_artist words to be searched among artists names
  * @param q_lyrics words to be searched into the lyrics
  * @param page requested page of results
  * @param page_size desired number of items per result page
  * @param f_has_lyrics exclude artists without any available lyrics (automatic if q_lyrics is set)
  * @param f_artist_id filter the results by the artist_id
  * @param f_artist_mbid filter the results by the artist_mbid
  */
class SearchArtist(var q: String = null,
                   var q_track: String = null,
                   var q_artist: String = null,
                   var q_lyrics: String = null,
                   var f_has_lyrics: String = null,
                   var f_artist_id: String = null,
                   var f_artist_mbid: String = null,
                   var page: Number = -1,
                   var page_size: Number = -1) extends AsQueryString {
  def this() = this (null, null, null, null, null, null, null, -1, -1)
}

/**
  *
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/track-search">track.search</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param q a string that will be searched in every data field (q_track, q_artist, q_lyrics)
  * @param q_track words to be searched among track titles
  * @param q_artist words to be searched among artist names
  * @param q_track_artist words to be searched among track titles or artist names
  * @param q_lyrics words to be searched into the lyrics
  * @param page requested page of results
  * @param page_size desired number of items per result page
  * @param f_has_lyrics exclude tracks without an available lyrics (automatic if q_lyrics is set)
  * @param f_artist_id filter the results by the artist_id
  * @param f_artist_mbid filter the results by the artist_mbid
  * @param quorum_factor only works together with q and q_track_artist parameter. Possible values goes from 0.1 to 0.9. A value of 0.9 means: “match at least 90% of the given words”.
  */
class SearchTrack(var q: String = null,
                  var q_track: String = null,
                  var q_artist: String = null,
                  var q_track_artist: String = null,
                  var q_lyrics: String = null,
                  var f_has_lyrics: String = null,
                  var f_artist_id: String = null,
                  var f_artist_mbid: String = null,
                  var quorum_factor: String = null,
                  var page: Number = -1,
                  var page_size: Number = -1) extends AsQueryString {
  def this() = this (null, null, null, null, null, null, null, null, null, -1, -1)

}

/**
  * Request object for:
  * <ul>
  *   <li><a href="https://developer.musixmatch.com/documentation/api-reference/track-get">track.get</a></li>
  *   <li><a href="https://developer.musixmatch.com/documentation/api-reference/track-subtitle-get">track.subtitle.get</a></li>
  *   <li><a href="https://developer.musixmatch.com/documentation/api-reference/track-lyrics-get">track.lyrics.get</a></li>
  * </ul>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param trackId track_id | track_mbid | track_echonest_id: the track identifier expressed as a musiXmatch ID, musicbrainz ID or echonest ID
  */
class GetTrack(var track_id: String) extends AsQueryString {

}

/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/track-chart-get">track.chart.get</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param page requested page of results
  * @param page_size desired number of items per result page
  * @param country the country code of the desired country chart (refer to [[input-parameters]] page for allowed values)
  * @param f_has_lyrics exclude tracks without an available lyrics */
class GetTrackChart(var country: String = null,
                    var f_has_lyrics: String = null,
                    var page: Number = -1,
                    var page_size: Number = -1) extends AsQueryString {
  def this() = this(null, null, -1, -1)
}


/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/track-lyrics-feedback-post">track.lyrics.feedback.post</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param lyrics_id the lyrics identifier
  * @param track_id the track identifier
  * @param feedback one of the support feedback type (please refer to the input parameters page)
  **/
class PostTrackLyricsFeedback(var lyrics_id: String,
                              var track_id: String,
                              var feedback: String

                               ) extends AsQueryString {

}

/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/matcher-track-get">matcher.track.get</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param q_track words to be searched among track titles
  * @param q_artist words to be searched among artist names
  * @param q_duration search for a track duration
  * @param q_album the name of the album
  **/
class GetMatcherTrack(var q_track: String = null,
                      var q_artist: String = null,
                      var q_album: String = null,
                      var q_duration: String = null) extends AsQueryString {
  def this() = this (null, null, null, null)
}


/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/artist-get">artist.get</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param artistId artist_id | artist_mbid : the artist identifier expressed as a musiXmatch ID or musicbrainz ID
  **/
class GetArtist(var artist_id: String) extends AsQueryString {
  def this() = this (null)
}


/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/artist-albums-get">artist.albums.get</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param artist_id the album identifier expressed as a musiXmatch ID
  * @param page requested page of results
  * @param page_size desired number of items per page
  * @param g_album_name group albums by name to avoid duplicates
  * @param s_release_date sort by release date (desc/asc)
  **/
class GetArtistAlbums(var artist_id: String,
                      var page: Number = -1,
                      var page_size: Number = -1,
                      var g_album_name: String = "1",
                      var s_release_date: String = null
                       ) extends AsQueryString {
  def this() = this (null, -1, -1, "1", null)

}

/**
  * Request object for <a href="https://developer.musixmatch.com/documentation/api-reference/artist-chart-get">artist.chart.get</a>
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param page requested page of results
  * @param page_size desired number of items per result page
  * @param country the <a href="https://developer.musixmatch.com/documentation/input-parameters">country code</a> of the desired country chart
  **/
class GetArtistChart(var country: String = null,
                     var page: Number = -1,
                     var page_size: Number = -1) extends AsQueryString {
  def this() = this (null, -1, -1)

}


/**
  * Request object for:
  * <ul>
  *   <li><a href="https://developer.musixmatch.com/documentation/api-reference/album-get">album.get</a></li>
  *   <li><a href="https://developer.musixmatch.com/documentation/api-reference/album-tracks-get">album.tracks.get</a></li>
  * </ul>
  *
  *
  * @author Ayush Gupta
  * @since May 21st 2011
  *
  * @param album_id the album identifier expressed as a musiXmatch ID
  **/
class GetAlbum(var album_id: String) extends AsQueryString {
  def this() = this (null)
}


