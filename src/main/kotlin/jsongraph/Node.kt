package jsongraph

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Node(
    val id: String,
    val label: String? = null,
    val metadata: Map<String, Any>? = null
)