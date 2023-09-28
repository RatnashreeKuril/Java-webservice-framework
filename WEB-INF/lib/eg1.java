import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.*;
import com.itextpdf.io.font.constants.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.layout.property.*;
import java.io.*;
class abcd
{
public void exportToPDF(File file)
{
try
{
PdfWriter pdfWriter=new PdfWriter(new File("pdf1.pdf"));
System.out.println("0000000000000000000");
PdfDocument pdfDocument=new PdfDocument(pdfWriter);
Document document=new Document(pdfDocument);
document.setMargins(15,15,15,15);
System.out.println("1111111111111111111111");
Image companyLogo=new Image(ImageDataFactory.create("companyLogo.png"));
System.out.println("2222222222222222222222222");

Paragraph p1=new Paragraph();
p1.add(companyLogo);
p1.add("Company name : abcd");
document.add(p1);
document.add(new Paragraph("1").setTextAlignment(TextAlignment.RIGHT));
Paragraph p2=new Paragraph();
p2.add("Designation");
System.out.println("3333333333333333333333333");

p2.setTextAlignment(TextAlignment.CENTER);
PdfFont designationFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
p2.setFont(designationFont);
p2.setFontSize(18);
System.out.println("4444444444444444444444444444");
document.add(p2);
float columnWidths[]={1,10};
Table table=new Table(UnitValue.createPercentArray(columnWidths));
PdfFont titleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont dataFont=PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

Paragraph title1=new Paragraph("S.No.");
title1.setFont(titleFont);
title1.setFontSize(16);
Paragraph title2=new Paragraph("Designation");
title2.setFont(titleFont);
title2.setFontSize(16);
title2.setTextAlignment(TextAlignment.CENTER);

table.addHeaderCell(new Cell().add(title1));
table.addHeaderCell(new Cell().add(title2));



document.add(table);
document.close();
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
public static void main(String gg[])
{
abcd a=new abcd();
a.exportToPDF(new File("pdf1.pdf"));
}
}
