/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package com.codespair.androidudacitykotlin.utilities
import android.net.Uri
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import java.util.*

/**
 * These utilities will be used to communicate with the network.
 */
class NetworkUtils {
    companion object {
        val GITHUB_BASE_URL = "https://api.github.com/search/repositories"
        val PARAM_QUERY = "q"
        val PARAM_SORT = "sort"
        val STARS = "stars"

        /**
         * Builds the URL used to query Github.
         *
         * @param githubSearchQuery The keyword that will be queried for.
         * @return The URL to use to query the weather server.
         */
        fun buildUrl(gitHubSearchQuery: String): URL? {
            val uriBuilder = Uri.parse(GITHUB_BASE_URL).buildUpon()
                    .appendQueryParameter(PARAM_QUERY, gitHubSearchQuery)
                    .appendQueryParameter(PARAM_SORT, STARS)
                    .build()
            return URL(uriBuilder.toString())
        }



        /**
         * This method returns the entire result from the HTTP response.
         *
         * @param url The URL to fetch the HTTP response from.
         * @return The contents of the HTTP response.
         * @throws IOException Related to network and stream reading
         */
        fun getResponseFromHttpUrl(url: URL) : String? {
            val urlConnection: URLConnection? = url.openConnection()
            val inputStream: InputStream? = urlConnection!!.getInputStream()
            val scanner = Scanner(inputStream)
            scanner.useDelimiter("\\A")
            if(scanner.hasNext()) {
                return scanner.next()
            }
            return null
        }
    }
}
