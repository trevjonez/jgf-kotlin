package jsongraph

import okio.BufferedSource
import okio.buffer
import okio.source
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream
import kotlin.streams.asStream

internal class GraphsObjectJsonAdapterTest {

  private val moshi by lazy { defaultMoshi() }

  private val adapter by lazy {
    moshi.graphsAdapter().indent("  ")
  }

  @DisplayName("Parse example json graphs")
  @FromResources(names = [
    "/car_graphs.json",
    "/les_miserables.json",
    "/test.network.json",
    "/usual_suspects.json"
  ])
  @ParameterizedTest(name = "{0}")
  internal fun fromJson(fileName: String, source: BufferedSource) {
    assertNotNull(adapter.fromJson(source))
  }

  @DisplayName("Serializing example json graphs")
  @FromResources(names = [
    "/car_graphs.json",
    "/les_miserables.json",
    "/test.network.json",
    "/usual_suspects.json"
  ])
  @ParameterizedTest(name = "{0}")
  internal fun toJson(fileName: String, source: BufferedSource) {
    assertNotNull(adapter.toJson(adapter.fromJson(source)))
  }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ArgumentsSource(FromResources.Companion::class)
annotation class FromResources(val names: Array<String>) {
  companion object : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext): Stream<Arguments> {
      val names = context.requiredTestMethod.getAnnotation(FromResources::class.java).names
      val testClass = context.requiredTestClass
      return names
          .asSequence()
          .map { Arguments.of(it, testClass.getResourceAsStream(it).source().buffer()) }
          .asStream()
    }
  }
}
