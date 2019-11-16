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