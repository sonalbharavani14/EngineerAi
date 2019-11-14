package com.app.engineraitest.data


import com.google.gson.annotations.SerializedName

data class ClsPost(
    @SerializedName("exhaustiveNbHits")
    var exhaustiveNbHits: Boolean = false,
    @SerializedName("hits")
    var hits: ArrayList<Hit> = ArrayList(),
    @SerializedName("hitsPerPage")
    var hitsPerPage: Int = 0,
    @SerializedName("nbHits")
    var nbHits: Int = 0,
    @SerializedName("nbPages")
    var nbPages: Int = 0,
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("params")
    var params: String = "",
    @SerializedName("processingTimeMS")
    var processingTimeMS: Int = 0,
    @SerializedName("query")
    var query: String = ""
) {
    data class Hit(
        @SerializedName("author")
        var author: String = "",
        @SerializedName("comment_text")
        var commentText: Any? = Any(),
        @SerializedName("created_at")
        var createdAt: String = "",
        @SerializedName("created_at_i")
        var createdAtI: Int = 0,
        @SerializedName("_highlightResult")
        var highlightResult: HighlightResult = HighlightResult(),
        @SerializedName("num_comments")
        var numComments: Int = 0,
        @SerializedName("objectID")
        var objectID: String = "",
        @SerializedName("parent_id")
        var parentId: Any? = Any(),
        @SerializedName("points")
        var points: Int = 0,
        @SerializedName("story_id")
        var storyId: Any? = Any(),
        @SerializedName("story_text")
        var storyText: Any? = Any(),
        @SerializedName("story_title")
        var storyTitle: Any? = Any(),
        @SerializedName("story_url")
        var storyUrl: Any? = Any(),
        @SerializedName("_tags")
        var tags: List<String> = listOf(),
        @SerializedName("title")
        var title: String = "",
        @SerializedName("url")
        var url: String? = "",
        var selected: Boolean = false
    ) {
        data class HighlightResult(
            @SerializedName("author")
            var author: Author = Author(),
            @SerializedName("title")
            var title: Title = Title(),
            @SerializedName("url")
            var url: Url = Url()
        ) {
            data class Author(
                @SerializedName("matchLevel")
                var matchLevel: String = "",
                @SerializedName("matchedWords")
                var matchedWords: List<Any> = listOf(),
                @SerializedName("value")
                var value: String = ""
            )

            data class Title(
                @SerializedName("matchLevel")
                var matchLevel: String = "",
                @SerializedName("matchedWords")
                var matchedWords: List<Any> = listOf(),
                @SerializedName("value")
                var value: String = ""
            )

            data class Url(
                @SerializedName("matchLevel")
                var matchLevel: String = "",
                @SerializedName("matchedWords")
                var matchedWords: List<Any> = listOf(),
                @SerializedName("value")
                var value: String = ""
            )
        }
    }
}