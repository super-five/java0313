package day004_Quartz_001;

import java.io.IOException;
import java.util.List;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class MyStatement {
	@SuppressWarnings({ "deprecation", "resource" })
	public static void getStatement(List<Item> list ) throws IOException{
		PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
		
		PdfWriter writer = new PdfWriter("d://chart//statement.pdf");
		PdfDocument pdfDocument = new PdfDocument(writer);
		Document document = new Document(pdfDocument);
		
		Table table = new Table(new float[]{100,100,100,100,100});
		
		String[] title = {"name","minPrice","avgPrice","maxPrice","date"};
		for(int i=0;i<5;i++){
			Cell cell = new Cell(1,1);
			cell.add(new Paragraph(title[i]).setFont(font).setFontColor(Color.BLACK));
			table.addCell(cell);
		}
		Cell cell1 = null;
		Cell cell2 = null;
		Cell cell3 = null;
		Cell cell4 = null;
		Cell cell5 = null;
		
		for(Item item : list){
			cell1 = new Cell(1,1);
			cell1.add(new Paragraph(item.getName()).setFont(font).setFontColor(Color.BLACK));
			cell2 = new Cell(1,1);
			cell2.add(new Paragraph(item.getMinPrice().toString()).setFont(font).setFontColor(Color.BLACK));
			cell3 = new Cell(1,1);
			cell3.add(new Paragraph(item.getAveragePrice().toString()).setFont(font).setFontColor(Color.BLACK));
			cell4 = new Cell(1,1);
			cell4.add(new Paragraph(item.getMaxPrice().toString()).setFont(font).setFontColor(Color.BLACK));
			cell5 = new Cell(1,1);
			cell5.add(new Paragraph(item.getDate().toLocaleString()).setFont(font).setFontColor(Color.BLACK));
			
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
		}
		
		document.add(table);
	}
}
