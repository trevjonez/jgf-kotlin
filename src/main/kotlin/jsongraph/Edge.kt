package jsongraph

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Edge(
    val id: String? = null,
    val source: String,
    val target: String,
    val relation: String? = null,
    val directed: Boolean = true,
    val label: String? = null,
    val metadata: Map<String, Any>? = null
)