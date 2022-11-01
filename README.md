# JODConverter

**JODConverter**, the Java OpenDocument Converter, converts documents between different office formats.
It leverages OpenOffice.org, which provides arguably the best import/export filters for OpenDocument and Microsoft Office formats available today.

*JODConverter* automates all conversions supported by OpenOffice.org, including

- Microsoft Office to OpenDocument, and viceversa
  - Word to OpenDocument Text (odt); OpenDocument Text (odt) to Word
  - Excel to OpenDocument Spreadsheet (ods); OpenDocument Spreadsheet (ods) to Excel
  - PowerPoint to OpenDocument Presentation (odp); OpenDocument Presentation (odp) to PowerPoint
- Any format to PDF
  - OpenDocument (Text, Spreadsheet, Presentation) to PDF
  - Word to PDF; Excel to PDF; PowerPoint to PDF
  - RTF to PDF; WordPerfect to PDF; ...
- And more
  - OpenDocument Presentation (odp) to Flash; PowerPoint to Flash
  - RTF to OpenDocument; WordPerfect to OpenDocument
  - Any format to HTML (with limitations)
  - Support for OpenOffice.org 1.0 and old StarOffice formats
  - ...

*JODConverter* can be used in many different ways

- As a Java library, embedded in your own Java application
- As a command line tool, possibly invoked from your own scripts
- As a simple web application: upload your input document, select the desired format and download the converted version
- As a web service, invoked from your own application written in your favourite language (.NET, PHP, Python, Ruby, ...)