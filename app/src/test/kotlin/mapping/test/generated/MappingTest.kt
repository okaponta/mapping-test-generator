/**
 * GENERATED TEST CODE
 */
package mapping.test.generated

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mapping.test.mapping.TestMapping
import mapping.test.from.FromA
import mapping.test.from.FromB

class MappingTest : StringSpec({
    "standard case" {
        val fromA = FromA("hoge", 1, 2)
        val fromB = FromB("fuga", 3, 4)
        val actual = TestMapping.convert(fromA, fromB)
        actual.fieldA shouldBe fromA.fieldA
        actual.fieldB shouldBe fromB.fieldC
        actual.fieldC shouldBe 0
        actual.fieldD shouldBe null
    }
})
