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

import java.util.ArrayList
import com.glug.musicxlib.util.ReflectiveToString

/*
 * User: ayush
 * Date: 5/23/11
 * Time: 10:12 PM
 */
class Artist extends ReflectiveToString {
  var id: String = null
  var mbid: String = null
  var name: String = null
  var alias: java.util.List[String] = new ArrayList[String]
}

class Album extends ReflectiveToString {
  var id: String = null
  var name: String = null
  var artistId: String = null
  var artistName: String = null
  var releaseDate: String = null
  var releaseType: String = null
  var coverart: String = null
}

class Track extends ReflectiveToString {
  var id: String = null
  var mbid: String = null
  var length: String = null
  var lyricsId: String = null
  var instrumental: String = null
  var subtitleId: String = null
  var name: String = null
  var albumName: String = null
  var albumId: String = null
  var artistId: String = null
  var coverart: String = null
  var artistMbid: String = null
  var artistName: String = null
}

class Lyrics extends ReflectiveToString {
  var id: String = null
  var body: String = null
  var language: String = null
  var scriptTrackingUrl: String = null
  var pixelTrackingUrl: String = null
  var copyright: String = null
}