package jsongraph

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Graph(
    val label: String? = null,
    val directed: Boolean = true,
    val type: String? = null,
    val metadata: Map<String, Any>? = null,
    val nodes: Set<Node> = emptySet(),
    val edges: Set<Edge> = emptySet()
)