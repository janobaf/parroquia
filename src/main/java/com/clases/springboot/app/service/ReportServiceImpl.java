package com.clases.springboot.app.service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clases.springboot.app.models.entity.Acta;
import com.clases.springboot.app.models.entity.Report;
import com.inagro.springboot.backend.apirest.util.Util;
import com.inagro.springboot.backend.apirest.util.UtilFormat;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;




@Service
public class ReportServiceImpl implements IReportService {
	
	
	private String tempPath = System.getProperty("java.io.tmpdir");

	@Autowired
	private IActaService actaServie;

	@Override
	public Report reportePrueba() throws Exception,JRException, IOException,  ParseException, DocumentException{
		String nameFile = "/rpt_acta_prueba";
		FileOutputStream ficheroPDF = new FileOutputStream(tempPath + nameFile + ".pdf");
		PdfPTable table = null;
		PdfPCell cell = null;
		Document document = new Document(PageSize.A4, 0, 0, 10, 10);
		PdfWriter.getInstance(document, ficheroPDF);

		// <editor-fold defaultstate="collapsed" desc="ESTILOS">
		Font fontSubTitle = new Font(Font.FontFamily.HELVETICA, 9f, Font.NORMAL);
		Font fontCabecera = new Font(Font.FontFamily.HELVETICA, 9f, Font.BOLD);
		fontCabecera.setColor(BaseColor.WHITE);

		Font fontCabeceraHab = new Font(Font.FontFamily.HELVETICA, 9f, Font.BOLD);
		fontCabecera.setColor(BaseColor.BLACK);

		Font fontSubCabecera = new Font(Font.FontFamily.HELVETICA, 9f, Font.NORMAL);

		BaseColor colorCabecera = new BaseColor(192, 224, 255);
		BaseColor colorPie = new BaseColor(255, 245, 241);
		// </editor-fold>

		document.open();

		// <editor-fold defaultstate="collapsed" desc="CABECERA">
		PdfPTable cabeceraTable = new PdfPTable(2);
		cell = new PdfPCell();

		Image img = Image.getInstance(this.getClass().getClassLoader().getResource("logo.png"));
		img.scaleToFit(100, 100);

		cell = new PdfPCell(img);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(4);
		cabeceraTable.addCell(cell);

		cell = new PdfPCell(new Phrase("Fecha : " + UtilFormat.formatearFechaNombreDiaMes(new Date()), fontSubTitle));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cabeceraTable.addCell(cell);

//		BigDecimal totalServicios = detalles.stream().map(h -> (h.getTotalizado())).reduce(BigDecimal.ZERO,
//				BigDecimal::add);
//
//		cell = new PdfPCell(new Phrase(
//				"Total Servicios S/. : "
//						+ UtilFormat.formatearNumero(totalServicios, ConstantesUtil.PATTERN_DECIMAL_COMA_PUNTO_2),
//				fontSubTitle));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setVerticalAlignment(Element.ALIGN_CENTER);
//		cell.setBorder(Rectangle.NO_BORDER);
//		cabeceraTable.addCell(cell);
//
//		BigDecimal totalLiquidado = detalles.stream().map(h -> (h.getMontoLiquidado())).reduce(BigDecimal.ZERO,
//				BigDecimal::add);
//
//		cell = new PdfPCell(new Phrase(
//				"Total Liquidaciones S/. : "
//						+ UtilFormat.formatearNumero(totalLiquidado, ConstantesUtil.PATTERN_DECIMAL_COMA_PUNTO_2),
//				fontSubTitle));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setVerticalAlignment(Element.ALIGN_CENTER);
//		cell.setBorder(Rectangle.NO_BORDER);
//		cabeceraTable.addCell(cell);
//
//		BigDecimal totalPendiente = detalles.stream().map(h -> (h.getMontoPendiente())).reduce(BigDecimal.ZERO,
//				BigDecimal::add);
//
//		cell = new PdfPCell(new Phrase(
//				"Total Pendiente S/. : "
//						+ UtilFormat.formatearNumero(totalPendiente, ConstantesUtil.PATTERN_DECIMAL_COMA_PUNTO_2),
//				fontSubTitle));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setVerticalAlignment(Element.ALIGN_CENTER);
//		cell.setBorder(Rectangle.NO_BORDER);
//		cabeceraTable.addCell(cell);
//		document.add(cabeceraTable);

		saltoLinea(document);

		table = new PdfPTable(4);
		cell = new PdfPCell();

		cell = new PdfPCell(new Phrase("Número", fontCabecera));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
		cell.setBackgroundColor(colorCabecera);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Empleado", fontCabecera));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
		cell.setBackgroundColor(colorCabecera);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Cargo Empleado", fontCabecera));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
		cell.setBackgroundColor(colorCabecera);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Fecha Emisión", fontCabecera));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
		cell.setBackgroundColor(colorCabecera);
		table.addCell(cell);

	

		table.setWidths(new int[] { 25,25,25,25});
		document.add(table);
		
		for (Acta acta : actaServie.findAll()) {

			

		

			table = new PdfPTable(4);
			cell = new PdfPCell();

			cell = new PdfPCell(new Phrase(acta.getNumero(), fontCabecera));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
//			cell.setBackgroundColor(colorCabecera);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(acta.getEmpleadoId().getNombres(), fontCabecera));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
//			cell.setBackgroundColor(colorCabecera);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(acta.getEmpleadoId().getCargoId().getNombre(), fontCabecera));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
//			cell.setBackgroundColor(colorCabecera);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(acta.getFechaEmicion(), fontCabecera));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
//			cell.setBackgroundColor(colorCabecera);
			table.addCell(cell);

		

			table.setWidths(new int[] { 25,25,25,25});
			document.add(table);
			
		

//			saltoLinea(document);
		}

		document.newPage();
		document.close();
		String pathFile = tempPath + nameFile + ".pdf";
		String archivoBase = Util.convertBytes(pathFile);
		Util.deleteFile(pathFile);
		Report report = new Report();
		report.setPdfBase(archivoBase);
		return report;
	}
	private void saltoLinea(Document document) throws DocumentException {
		document.add(new Paragraph(Chunk.NEWLINE));
	}
	private PdfPTable agregarLogo() throws BadElementException, MalformedURLException, IOException {
		PdfPCell cell;
		PdfPTable cabeceraTable = new PdfPTable(2);
		cell = new PdfPCell();

		Image img = Image.getInstance(this.getClass().getClassLoader().getResource("logo.png"));
		img.scaleToFit(100, 100);

		cell = new PdfPCell(img);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(4);
		cabeceraTable.addCell(cell);
		return cabeceraTable;
	}
	
	
	
}
