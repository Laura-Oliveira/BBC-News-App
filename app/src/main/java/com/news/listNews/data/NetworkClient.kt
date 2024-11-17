package com.news.listNews.data

import com.news.data.Article

class NetworkClient
{
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://newsapi.org/docs/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val service = retrofit.create(APIService::class.java)
//
//    suspend fun getItems(processData: (List<Article>) -> Unit) {
//        withContext(Dispatchers.IO) {
//            try {
//                val response = service.getTopHeadlines()
//                Log.d("Retrofit Response", "${response}")
//                processResponseData(response, processData)
//            } catch (e: Exception) {
//                Log.e("NETWORK_ERROR", "Error fetching items", e)
//                processData(emptyList())
//            }
//        }
//    }

    private fun processResponseData(responseData: List<Article>, processData: (List<Article>) -> Unit) {
        processData(responseData)
    }
}