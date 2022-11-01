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
