package com.glug.musicxmatch.api

/*
* @author Ayush Gupta
* Since 5/21/11 10:14 PM
*/
class MusixMatch {
  def readUrl(url: String) = io.Source.fromURL(url).mkString

}

