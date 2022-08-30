# MappingTest

## GenerateFileInfo

| key       | value                                      |
|-----------|--------------------------------------------|
| outputDir | app/src/test/kotlin/mapping/test/generated |
| fileName  | MappingTest.kt                             |

## Class

| from/to | package           | className |
|---------|-------------------|-----------|
| To      | mapping.test.to   | To        |
| From    | mapping.test.from | FromA     |
| From    | mapping.test.from | FromB     |

## Mapping

| MappingTo | Type   | MappingFrom  | Constant |
|-----------|--------|--------------|----------|
| fieldA    | String | FromA.fieldA |          |
| fieldB    | Int    | FromB.fieldC |          |
| fieldC    | Int    |              | 0        |
| fieldD    | String |              | null     |

## TODO

- 各クラスのフィールドの初期値も入力できるようにする
- 型が一致する場合のみのテストの場合はTypeの列不要
- Constant/MappingFromどっちもに値が含まれる場合は飛ばす
