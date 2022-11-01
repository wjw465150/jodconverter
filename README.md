# JODConverter简介

## 简介

**JODConverter**，Java OpenDocument 转换器，可在不同办公格式之间转换文档。
它依赖于OpenOffice或者LibreOffice提供的服务来进行转换，它为当今可用的 OpenDocument格式 和 Microsoft Office 格式提供了可以说是最好的导入/导出。

*JODConverter* 自动执行 LibreOffice/OpenOffice 支持的所有转换，包括

- Microsoft Office文档 到 OpenDocument文档, 反之亦然
  - Word 到 OpenDocument Text (odt); OpenDocument Text (odt) 到 Word
  - Excel 到 OpenDocument Spreadsheet (ods); OpenDocument Spreadsheet (ods) 到 Excel
  - PowerPoint 到 OpenDocument Presentation (odp); OpenDocument Presentation (odp) 到 PowerPoint
- 任何格式 到 PDF
  - OpenDocument (Text, Spreadsheet, Presentation) 到 PDF
  - Word 到 PDF; Excel 到 PDF; PowerPoint 到 PDF
  - RTF 到 PDF; WordPerfect 到 PDF; ...
- 更多
  - OpenDocument Presentation (odp) 到 Flash; PowerPoint 到 Flash
  - RTF 到 OpenDocument; WordPerfect 到 OpenDocument
  - 任何格式 到 HTML (有限制)
  - 支持 OpenOffice.org 1.0 和旧的 StarOffice 格式
  - ...

*JODConverter* 可以以多种不同方式使用

- 作为 Java 库，嵌入到您自己的 Java 应用程序中
- 作为命令行工具，可能从您自己的脚本中调用

## 用法

### 1: 先启动LibreOffice/OpenOffice

- for Linux:

  ```sh
  #! /bin/bash
  
  #xhost +
  #export DISPLAY=:1.0 
  
  /path/to/openoffice/program/soffice.bin
              -headless -nofirststartwizard
              -accept="socket,host=localhost,port=8100;urp;StarOffice.Service"
  ```

- for Mac OS X:

  ```sh
  /path/to/openoffice.app/Contents/MacOS/soffice.bin
              -headless -nofirststartwizard
              -accept="socket,host=localhost,port=8100;urp;StarOffice.Service"
  ```

- for Windows:

  ```sh
  soffice.exe -headless
              -nofirststartwizard
              -accept="socket,host=localhost,port=8100;urp;StarOffice.Service"
  ```

### 2: 通过命令行来转换

例如把一个word文档转换成PDF文档:

```sh
REM 进入当前批处理文件所在的目录
cd /d %~dp0

java -jar ./jodconverter-all-2.2.2.jar -v -h 127.0.0.1 -p 8100 ./test.doc ./test.pdf
```

### 3: 嵌入到Java程序中来转换

引入依赖:

+ Maven:

```xml
<dependency>
  <groupId>com.github.wjw465150</groupId>
  <artifactId>jodconverter-all</artifactId>
  <version>2.2.2</version>
</dependency>
```

+ Gradle:

```groovy
dependencies {
  implementation "com.github.wjw465150:jodconverter-all:2.2.2"
}
```

编写代码:

```java
package wjw.test.JODConverter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

class Test {

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @org.junit.jupiter.api.Test
  void test() {
    InputStream inFile = null;
    OutputStream outFile = null;
    OpenOfficeConnection con = null;
    try {
      inFile = Test.class.getClassLoader().getResourceAsStream("test.doc");
      outFile = new FileOutputStream("c:\\WJW_D\\test.pdf");

      // 创建Openoffice连接
      con = new SocketOpenOfficeConnection(8100);
      // 连接
      con.connect();

      DocumentFormat docDocumentFormat = (new DefaultDocumentFormatRegistry()).getFormatByFileExtension("doc");
      DocumentFormat pdfDocumentFormat = (new DefaultDocumentFormatRegistry()).getFormatByFileExtension("pdf");

      // 创建转换器
      DocumentConverter converter = new OpenOfficeDocumentConverter(con);
      // 转换文档
      converter.convert(inFile, docDocumentFormat, outFile, pdfDocumentFormat);
      
      System.out.println("转换成功!");
    } catch (Exception e) {
      System.out.println("转换失败!");
      e.printStackTrace();
    } finally {
      if (con != null) {
        // 关闭openoffice连接
        con.disconnect();
      }

      if (inFile != null) {
        try {
          inFile.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (outFile != null) {
        try {
          outFile.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

}

```

## 项目地址

https://github.com/wjw465150/jodconverter

------

<<<<<<<<<<<< [完] >>>>>>>>>>>