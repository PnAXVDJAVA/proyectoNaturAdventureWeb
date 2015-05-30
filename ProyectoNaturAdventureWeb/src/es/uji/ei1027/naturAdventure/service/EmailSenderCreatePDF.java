package es.uji.ei1027.naturAdventure.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import org.apache.catalina.startup.Tomcat;
import org.springframework.core.io.ClassPathResource;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class EmailSenderCreatePDF {
	private static String[] info;
	
	public static void setInfo(String[] infoNew) {
		info = infoNew;
	}
	
	public static void writePDF(OutputStream outputStream) throws Exception {
		Document documento = new Document();
		 
		PdfWriter.getInstance(documento,outputStream);
		documento.open();
		
		/* ESTO ES PARA INTENTAR BUSCAR UNA RUTA QUE ME SIRVA PARA PONER EL DICHOSO ICONO*/
		
		/* SI TE SIRVE, newFile(".").getAbsolutePath() DEVUELVE EL DIRECTORIO DE ECLIPSE ( C:/ECLIPSE )
		 * ES DECIR, SI PONGO LA IMAGEN EN MI DIRECTORIO ( C:/ECLIPSE ), ESTARÁ EN EL PDF PERO NO ES PLAN */
//		
//		System.out.println(new java.io.File(".").getAbsolutePath());
//		String[] rutas = {
//						"images/",
//						EmailSenderCreatePDF.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
//						EmailSenderCreatePDF.class.getPackage().getName(),
//						EmailSenderCreatePDF.class.getProtectionDomain().toString(),
//						EmailSenderCreatePDF.class.getProtectionDomain().getCodeSource().toString(),
//						EmailSenderCreatePDF.class.getProtectionDomain().getCodeSource().getLocation().toString(),
//						EmailSenderCreatePDF.class.getProtectionDomain().getCodeSource().getLocation().getFile(),
//						EmailSenderCreatePDF.class.getProtectionDomain().getCodeSource().getLocation().getHost(),
//						"../",
//						"..\\.\\",
//						"\\",
//						};
//		
//		for(String s : rutas){
//			try {
//				System.out.println("Ruta: " + s);
//				String r = s + "/icon.png";
//				System.out.println("Ruta + /icon.png: " + r);
//				java.io.File f = new java.io.File(r);
//				System.out.println("EXISTE: " + f.exists());
//				
//				r = s + "\\icon.png";
//				System.out.println("Ruta + \\icon.png: " + r);
//				f = new java.io.File(r);
//				System.out.println("EXISTE: " + f.exists());
//				
//			}catch(Exception e) {
//				System.out.println("ERROR CON " + s);
//			}
//		}
//		
		
		
		/* SI ENCUENTRAS LA FORMA DE CONSEGUIR LA IMAGEN, CAMBIAR RUTA DE FOTO */
	
		
//		PdfPTable tabla = new PdfPTable(2);
//		tabla.setWidths(new int[]{4, 20});
//		PdfPCell celda;
//		try	{
//			
//		    Image foto = Image.getInstance("icon.png");					/* RUTA DE FOTO */
//		    foto.scaleToFit(60, 60);
//		    
//		    celda = new PdfPCell(foto);
//		    celda.setBorder(Rectangle.NO_BORDER);
//		    tabla.addCell(celda);
//		}
//		catch ( Exception e ) {
//		    e.printStackTrace();
//		}
//		
//		celda = new PdfPCell(new Paragraph("NaturAdventure",
//                FontFactory.getFont("arial",
//                40,
//                Font.BOLDITALIC,
//                BaseColor.BLACK)));
//		
//	    celda.setBorder(Rectangle.NO_BORDER);
//		tabla.addCell(celda);
//		
//		tabla.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
//		documento.add(tabla);
		
		
		/* ESTO ES PARA QUE ESCRIBA EL TITULO SIN IMAGEN */
		
		documento.add(new Paragraph("NaturAdventure",
                FontFactory.getFont("arial",
                40,
                Font.BOLDITALIC,
                BaseColor.BLACK)));
		
		
		/* ESTO ES EL RESTO DEL PDF, NO LO TOQUES QUE ESTO VA BIEN */
		
		Paragraph texto = new Paragraph("La reserva realizada el día " + info[0] + " ha sido ACEPTADA.");
		texto.setSpacingBefore(30.0f);
		texto.setSpacingAfter(5.0f);
		documento.add(texto);
		
		texto = new Paragraph("A continuación le mostramos la información de su reserva:");
		texto.setSpacingAfter(15.0f);
		documento.add(texto);
		
		String[] infoReserva = {"Nombre de la actividad", ": " + info[1],
								"Código de la reserva",   ": " + info[2],
								"Fecha de realización",   ": " + info[3],
								"Hora de realización",    ": " + info[4],
								"Núm. participantes",     ": " + info[5],
								" ", " ",
								"TOTAL A PAGAR", 		  ": " + info[6] + " €"};
		
		addTabla(documento, infoReserva, 5);
		
		texto = new Paragraph("Para cualquier duda, puede contactar con nosotros a través de los siguientes metodos:");
		texto.setSpacingBefore(20.0f);
		texto.setSpacingAfter(15.0f);
		documento.add(texto);
		
		String[] infoContacto = {"Teléfono",              ": +34 964123456. Horarios: Lunes a viernes, de 9:00 a 20:00 h.",
								 "Correo electronico",    ": naturadventure.xvd@gmail.com",
								 "Página web",            ": www.naturadventure.com/contact.html"};
		
		addTabla(documento, infoContacto, 4);
		
		texto = new Paragraph("Un cordial saludo.");
		texto.setSpacingBefore(15.0f);
		texto.setSpacingAfter(5.0f);
		documento.add(texto);
		
		texto = new Paragraph("----------------------------");
		texto.setSpacingAfter(5.0f);
		documento.add(texto);
		
		texto = new Paragraph("Equipo NaturAdventure.");
		texto.setSpacingAfter(5.0f);
		documento.add(texto);
		
		documento.close();
	}
	
	private static void addTabla(Document document, String[] info, int anchoPrimeraColumna) {
		int numeroColumnas = 2;
		try {
			PdfPTable tabla = new PdfPTable(numeroColumnas);
			tabla.setWidths(new int[]{anchoPrimeraColumna, 15});
			PdfPCell celda;
			for (int i = 0; i < info.length; i+= numeroColumnas) {
				celda = new PdfPCell(new Paragraph(info[i],
										FontFactory.getFont("arial",
										11,
										Font.BOLDITALIC)));
				celda.setBorder(Rectangle.NO_BORDER);
				tabla.addCell(celda);
				
				celda = new PdfPCell(new Paragraph(info[i+1]));
				celda.setBorder(Rectangle.NO_BORDER);
				tabla.addCell(celda);
			}
			
			tabla.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			tabla.setWidthPercentage(100);
			document.add(tabla);
		
		} catch (DocumentException e) {}
		
	}
}
