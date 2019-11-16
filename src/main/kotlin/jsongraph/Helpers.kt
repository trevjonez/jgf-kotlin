package jsongraph

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

fun defaultMoshi(): Moshi = Moshi.Builder()
    .add(GraphsObject.Adapter.Factory)
    .build()

private inline fun <reified T> Moshi.adapter() = adapter(T::class.java)

fun Moshi.graphsAdapter(): JsonAdapter<GraphsObject> = adapter()
fun Moshi.graphAdapter(): JsonAdapter<Graph> = adapter()