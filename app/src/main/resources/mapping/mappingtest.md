# MappingTest

## Class

| from/to | package                  | className |
|---------|--------------------------|-----------|
| To      | kotlin.mapping.test.to   | To        |
| From    | kotlin.mapping.test.from | FromA     |
| From    | kotlin.mapping.test.from | FromB     |

## Mapping

| MappingTo | Type   | MappingFrom  | Constant |
|-----------|--------|--------------|----------|
| fieldA    | String | FromA.fieldA |          |
| fieldB    | Int    | FromB.fieldC |          |
| fieldC    | Int    |              | 0        |
| fieldD    | String |              | null     |
