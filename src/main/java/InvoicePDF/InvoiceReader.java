package InvoicePDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class InvoiceReader {

    public static void ReadInvoice() {

        String pdfPath = "pdfFiles/Invoice(william-2024-11-19_11-35-24).pdf";

        try {
            // Load pdf file
            PDDocument document = PDDocument.load(new File(pdfPath));

            try (document) {
                //text extractor
                PDFTextStripper textStripper = new PDFTextStripper();

                // read pdf file
                String pdfText = textStripper.getText(document);

                System.out.println("text in pdf:");
                System.out.println(pdfText);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Something was wrong: " + e.getMessage());
        }
    }
}
