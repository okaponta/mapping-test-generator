/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package mapping.test.generator

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val fileName = "app/src/main/resources/mapping/MappingTest.md"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        val generateFileInfo = convertToObject(lines)
        writeToFile(generateFileInfo)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun convertToObject(lines: List<String>): GenerateFileInfo {
    val fileInfo =
        GenerateFileInfo()
    var generateFileInfoFlag = false
    var classFlag = false
    var mappingFlag = false
    for (line in lines) {
        if (line == "## GenerateFileInfo") {
            generateFileInfoFlag = true
        }
        if (line == "## Class") {
            generateFileInfoFlag = false
            classFlag = true
        }
        if (line == "## Mapping") {
            classFlag = false
            mappingFlag = true
        }
        println(line)
        if (generateFileInfoFlag) {
            inputFileInfo(line, fileInfo)
        }
        if (classFlag) {
            inputClassInfo(line, fileInfo)
        }
        if (mappingFlag) {
            inputMappingInfo(line, fileInfo)
        }
    }
    return fileInfo
}

fun inputFileInfo(line: String, fileInfo: GenerateFileInfo) {
    val arr = line.split("|")
    if (arr.size < 3) {
        return
    }
    if (arr[1].trim() == "outputDir") {
        fileInfo.outputDir = arr[2].trim()
        println(arr[2].trim())
    }
    if (arr[1].trim() == "fileName") {
        fileInfo.fileName = arr[2].trim()
        println(arr[2].trim())
    }
}

fun inputClassInfo(line: String, fileInfo: GenerateFileInfo) {
    val arr = line.split("|")
    if (arr.size < 3) {
        return
    }
    if (arr[1].trim() == "To") {
        val clazz = ClassInfo(arr[2].trim(), arr[3].trim())
        fileInfo.to = clazz
        println(arr[2].trim() + arr[3].trim())
    }
    if (arr[1].trim() == "From") {
        val clazz = ClassInfo(arr[2].trim(), arr[3].trim())
        fileInfo.from.add(clazz)
        println(arr[2].trim() + arr[3].trim())
    }
}

fun inputMappingInfo(line: String, fileInfo: GenerateFileInfo) {
    val arr = line.split("|")
    if (arr.size < 3) {
        return
    }
    // TODO なんとかする
    if (arr[1].trim() == "MappingTo" || arr[1].trim() == "-----------") {
        return
    }
    if (arr[4].trim() != "") {
        val mappingInfo =
            MappingInfo(arr[1].trim(), null, null, arr[4].trim())
        fileInfo.mapping.add(mappingInfo)
        return
    }
    val mappingFromClazz = arr[3].split(".")
    if (mappingFromClazz.size < 2) {
        return
    }
    val mappingInfo =
        MappingInfo(arr[1].trim(), mappingFromClazz[0].trim(), mappingFromClazz[1].trim(), arr[4].trim())
    fileInfo.mapping.add(mappingInfo)
}

fun writeToFile(generateFileInfo: GenerateFileInfo) {
    val fil = FileWriter(generateFileInfo.outputDir + "/" + generateFileInfo.fileName)
    val pw = PrintWriter(BufferedWriter(fil))
    pw.println("/**\n" + " * GENERATED TEST CODE\n" + " */")
    pw.println("package mapping.test.generated") // TODO: これ用のプロパティ追加
    pw.println()
    pw.println(
        "import io.kotest.core.spec.style.StringSpec\n" +
                "import io.kotest.matchers.shouldBe\n" +
                "import mapping.test.mapping.TestMapping"
    )
    for (clazzInfo in generateFileInfo.from) {
        pw.println("import " + clazzInfo.pkg + "." + clazzInfo.className)
    }
    pw.println()
    pw.println("class MappingTest : StringSpec({") // TODO: これ用のプロパティ追加
    pw.println("    \"standard case\" {")
    pw.println("        val fromA = FromA(\"hoge\", 1, 2)") // TODO 初期値はMarkdownから取得
    pw.println("        val fromB = FromB(\"fuga\", 3, 4)")
    pw.println("        val actual = TestMapping.convert(fromA, fromB)") // TODO 変換メソッドもMarkdownから取得
    for (map in generateFileInfo.mapping) {
        if (map.mappingFromClassName == null) {
            pw.println("        actual." + map.field + " shouldBe " + map.constant)
            continue
        }
        val varName = map.mappingFromClassName!!.replaceFirstChar { it.lowercaseChar() } // TODO 型きちんとやる
        pw.println("        actual." + map.field + " shouldBe " + varName + "." + map.mappingFromFieldName)
    }
    pw.println("    }")
    pw.println("})")
    pw.close()
}
