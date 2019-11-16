/*
 *    Copyright 2019 Trevor Jones
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package jsongraph

import com.squareup.moshi.*
import java.lang.reflect.Type

data class GraphsObject(val graphs: List<Graph>) {
  class Adapter(private val moshi: Moshi) : JsonAdapter<GraphsObject>() {
    private val graphAdapter: JsonAdapter<Graph> by lazy {
      moshi.adapter(Graph::class.java)
    }

    private val graphsAdapter: JsonAdapter<List<Graph>> by lazy {
      moshi.adapter<List<Graph>>(Types.newParameterizedType(List::class.java, Graph::class.java))
    }

    private val options = JsonReader.Options.of("graph", "graphs")

    override fun fromJson(reader: JsonReader): GraphsObject {
      if (reader.peek() == JsonReader.Token.NULL)
        throw NullPointerException("Wrap with .nullSafe() to parse null")

      reader.beginObject()

      val graphs = when (reader.selectName(options)) {
        0 -> listOfNotNull(graphAdapter.fromJson(reader))
        1 -> graphsAdapter.fromJson(reader) ?: emptyList()
        else -> throw JsonDataException("Unexpected property: \"${reader.nextName()}\" at ${reader.path}")
      }

      reader.endObject()

      return GraphsObject(graphs)
    }

    override fun toJson(writer: JsonWriter, value: GraphsObject?) {
      when {
        value == null -> throw NullPointerException("Wrap with .nullSafe() to serialize null")
        value.graphs.size == 1 -> {
          writer.beginObject()
          writer.name("graph")
          graphAdapter.toJson(writer, value.graphs.single())
          writer.endObject()
        }
        value.graphs.size > 1 -> {
          writer.beginObject()
          writer.name("graphs")
          graphsAdapter.toJson(writer, value.graphs)
          writer.endObject()
        }
      }
    }

    companion object Factory : JsonAdapter.Factory {
      override fun create(
          type: Type,
          annotations: MutableSet<out Annotation>,
          moshi: Moshi
      ): JsonAdapter<*>? {
        return when {
          annotations.isNotEmpty() -> null
          type == GraphsObject::class.java -> Adapter(moshi)
          else -> null
        }
      }
    }
  }
}