package mapping.test.mapping

import mapping.test.from.FromA
import mapping.test.from.FromB
import mapping.test.to.To

class TestMapping {
    companion object {
        fun convert(fromA: FromA, fromB: FromB): To {
            return To(fromA.fieldA, fromB.fieldC, 0, null)
        }
    }
}
