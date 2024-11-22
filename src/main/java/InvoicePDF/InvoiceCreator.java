package InvoicePDF;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.example.Customer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class InvoiceCreator {

    public static void createInvoice(Customer user, List<HashMap<String, String>> ProductsList, String totalPrice){
        Document document = new Document();

        //scheme for the date to invoice header
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //scheme for the date to pdf name
        DateTimeFormatter pdfName = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        //Date and Time with scheme for invoice header
        String Date_Time= LocalDateTime.now().format(formatter);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.format("pdfFiles/Invoice(%s-%s).pdf",user.getName(), LocalDateTime.now().format(pdfName))));
            document.open();

            //Headers for Company name and Date
            PdfPTable table = new PdfPTable(2); // // two columns
            table.setWidthPercentage(100); // Adjust the width of the document
            table.setWidths(new float[]{1, 1}); // Column width ratio

            PdfPCell companyNameCell = new PdfPCell(new Phrase("TEGREDY FARMS", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
            companyNameCell.setBorder(Rectangle.NO_BORDER); // Borderless
            companyNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell dateCell = new PdfPCell(new Phrase(Date_Time, FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD)));
            dateCell.setBorder(Rectangle.NO_BORDER); // Borderless
            dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table.addCell(companyNameCell);
            table.addCell(dateCell);

            document.add(table);

            //line break
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            //Write user information
            document.add(new Paragraph(user.getName(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
            document.add(new Paragraph(user.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
            document.add(new Paragraph(user.getAddress(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
            document.add(new Paragraph(user.getPhoneNumber(), FontFactory.getFont(FontFactory.HELVETICA, 14)));

            //line break
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            //Table for products
            PdfPTable tableProducts = new PdfPTable(5);
            tableProducts.setWidthPercentage(100); //table witch  100%

            //Headers for products table
            PdfPCell headerId = new PdfPCell(new Phrase("Id", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerId.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableProducts.addCell(headerId);

            PdfPCell headerName = new PdfPCell(new Phrase("Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerName.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableProducts.addCell(headerName);

            PdfPCell headerQuantity = new PdfPCell(new Phrase("Quantity", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerQuantity.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableProducts.addCell(headerQuantity);

            PdfPCell headerPrice = new PdfPCell(new Phrase("Price($)", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerPrice.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableProducts.addCell(headerPrice);

            PdfPCell headerTotalPrice = new PdfPCell(new Phrase("Total Price", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            headerTotalPrice.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableProducts.addCell(headerTotalPrice);

            for (HashMap<String, String> product : ProductsList) {
                tableProducts.addCell(product.get("Id"));
                tableProducts.addCell(product.get("Name"));
                tableProducts.addCell(product.get("Quantity"));
                tableProducts.addCell(product.get("Price"));
                tableProducts.addCell(product.get("TotalPrice"));
            }

            document.add(tableProducts);

            //line break
            document.add(new Paragraph(" "));

            //table for total to display total price
            PdfPTable tableTotalPrice = new PdfPTable(2); // two columns
            tableTotalPrice.setWidthPercentage(100); // Adjust the width of the document
            tableTotalPrice.setWidths(new float[]{1, 1}); // Column width ratio


            PdfPCell priceTotalCell = new PdfPCell(new Phrase("Total price: ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
            priceTotalCell.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell valueCell = new PdfPCell(new Phrase(totalPrice, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
            valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tableTotalPrice.addCell(priceTotalCell);
            tableTotalPrice.addCell(valueCell);

            document.add(tableTotalPrice);

            document.close();
            //System.out.println("PDF Created");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}




