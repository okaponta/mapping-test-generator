package mapping.test.generator

data class GenerateFileInfo(
    var outputDir: String = "",
    var fileName: String = "",
    var to: ClassInfo? = null,
    var from: MutableList<ClassInfo> = mutableListOf(),
    var mapping: MutableList<MappingInfo> = mutableListOf()
)

data class ClassInfo(
    val pkg: String,
    val className: String,
)

data class MappingInfo(
    val field: String,
    val mappingFromClassName: String?,
    val mappingFromFieldName: String?,
    val constant: String?
)
