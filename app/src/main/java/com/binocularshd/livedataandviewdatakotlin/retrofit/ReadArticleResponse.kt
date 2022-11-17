package com.binocularshd.livedataandviewdatakotlin.retrofit

import com.google.gson.annotations.SerializedName

data class ReadArticleResponse(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Article(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null
)

data class RelatedArticlesItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null
)

data class Data(

	@field:SerializedName("related_articles")
	val relatedArticles: List<RelatedArticlesItem?>? = null,

	@field:SerializedName("article")
	val article: Article? = null
)
