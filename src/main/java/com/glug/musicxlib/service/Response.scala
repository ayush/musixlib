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

import com.glug.musicxlib.util.ReflectiveToString

/*
 * User: ayush
 * Date: 5/23/11
 * Time: 1:46 PM
 */
class Header extends ReflectiveToString {
  var status_code: Number = -1
  var execute_time: Number = -1
  var available: Number = -1
}

class RawAlbum extends ReflectiveToString {
  var album_id: Number = -1
  var artist_id: Number = -1
  var artist_name: String = null
  var album_name: String = null
  var album_release_date: String = null
  var album_release_type: String = null
  var album_coverart_100x100: String = null


}

class RawAlbumWrapper {
  var album: RawAlbum = null
}


class RawArtist extends ReflectiveToString {
  var artist_id: Number = -1
  var artist_mbid: String = null
  var artist_name: String = null
  var artist_alias_list: java.util.List[RawArtistAliasWrapper] = null
}

class RawArtistWrapper {
  var artist: RawArtist = null

  override def toString = if (artist == null) null else artist.toString

}

class RawArtistAliasWrapper {
  var artist_alias: String = null
}

class RawTrack extends ReflectiveToString {
  var track_id: String = null
  var track_mbid: String = null
  var track_length: String = null
  var lyrics_id: String = null
  var instrumental: String = null
  var subtitle_id: String = null
  var track_name: String = null
  var album_name: String = null
  var album_id: String = null
  var artist_id: String = null
  var album_coverart_100x100: String = null
  var artist_mbid: String = null
  var artist_name: String = null
}

class RawTrackWrapper {
  var track: RawTrack = null
}

class Body {
  var album_list: java.util.List[RawAlbumWrapper] = null
  var artist_list: java.util.List[RawArtistWrapper] = null
  var track_list: java.util.List[RawTrackWrapper] = null
  var artist: RawArtist = null
  var album: RawAlbum = null
  var track: RawTrack = null
}

class Message {
  var header: Header = null
  var body: Body = null
}

class ServiceResponse {
  var message: Message = null
}