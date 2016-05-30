/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antonioleiva.bandhookkotlin.data

import com.antonioleiva.bandhookkotlin.data.lastfm.LastFmService
import com.antonioleiva.bandhookkotlin.data.mapper.ArtistMapper
import com.antonioleiva.bandhookkotlin.domain.entity.Artist
import com.antonioleiva.bandhookkotlin.repository.dataset.ArtistDataSet

public class CloudArtistDataSet(val language: String, val lastFmService: LastFmService) : ArtistDataSet {

    override fun requestRecommendedArtists(): List<Artist> {
        // Search for coldplay similar artists.
        val result = lastFmService.requestSimilar("cc197bad-dc9c-440d-a5b5-d52ba2e14234")
        return ArtistMapper().transform(result.similarArtists.artists)
    }

    override fun requestArtist(id: String): Artist {
        val result = lastFmService.requestArtistInfo(id, language)
        return ArtistMapper().transform(result.artist)
    }
}