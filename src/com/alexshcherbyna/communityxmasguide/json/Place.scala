package com.alexshcherbyna.communityxmasguide.json

/**
 * Created by al on 29.08.15.
 */
case class Place(id: Long, name: String, description: String, imageurl: String, countryid: Long, cityid : Long,
                 typeofhouse: String, lattitude: String, longitude: String, likes: String, votes : String, verified: String, cityname : String, countryname : String)
