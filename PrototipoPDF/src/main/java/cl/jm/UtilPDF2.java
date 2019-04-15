package cl.jm;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.contentstream.operator.graphics.LineTo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

public class UtilPDF2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// centraTextoEnRectangulo("");
		
		// doIt("Texto Texto Texto Texto Texto", "/Volumes/JMHD/_JediFiles/tmp/pdf23.pdf");
		
		dibujaRectangulo();

	}
	
	private static void centraTextoEnRectangulo(String texto) throws Exception{
		try {

            String title = "Apache PDFBox Center Text PDF Document";
            PDFont font = PDType1Font.HELVETICA_BOLD;
            int marginTop = 30;
            int fontSize = 16;

            final PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            page.setRotation(90); //poner en landscape
            PDRectangle mediaBox = page.getMediaBox();
            doc.addPage(page);

            PDPageContentStream stream = new PDPageContentStream(doc, page);

            float titleWidth = font.getStringWidth(title) / 1000 * fontSize;
            float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

            float startX = (mediaBox.getWidth() - titleWidth) / 2;
            float startY = mediaBox.getHeight() - marginTop - titleHeight;

            stream.beginText();
            stream.setFont(font, fontSize);
            stream.newLineAtOffset(startX, startY);
            stream.showText(title);
            stream.endText();
            stream.close();            

            doc.save(new File("/Volumes/JMHD/_JediFiles/tmp/pdf21.pdf"));
            System.out.println("Doc generado !");

        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }
	}
	
	@SuppressWarnings("deprecation")
	public static void doIt( String message, String  outfile ) throws IOException, Exception
    {
        // the document
        PDDocument doc = null;
        try
        {
            doc = new PDDocument();

            PDFont font = PDType1Font.HELVETICA;
            PDPage page = new PDPage();
            page.setMediaBox(PDRectangle.A4);
            page.setRotation(90);
            doc.addPage(page);
            PDRectangle pageSize = page.getMediaBox();//.findMediaBox();        
            
            // ANcho de la pagina
            float pageWidth = pageSize.getWidth();
            // Tamano de la letra
            float fontSize = 12;
            // Ancho del string
            float stringWidth = font.getStringWidth(message)*fontSize/1000f;
            // Coordenada de inicio
            float startX = 10;
            float startY = 10;
            
            System.out.println("ANcho pagina : " + pageWidth + " , ancho del string : " + stringWidth);
            
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);//, false, false);
            // add the rotation using the current transformation matrix
            // including a translation of pageWidth to use the lower left corner as 0,0 reference
            Matrix matriz = new Matrix(0, 1, -1, 0, pageWidth, 0);

            contentStream.transform(matriz);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            
            contentStream.showText( message + "inicio");
            contentStream.newLineAtOffset(0, 100);
            contentStream.showText( message + "intermedio");
            contentStream.newLineAtOffset(100, 100);
            contentStream.showText( message + "final");
            contentStream.endText();
            
            contentStream.moveTo(startX-2, startY-2);
            contentStream.lineTo( startX-2, startY+200+fontSize);
            contentStream.stroke();
            
            contentStream.moveTo(startX-2, startY+200+fontSize);
            contentStream.lineTo(startX+100+stringWidth+2, startY+200+fontSize);
            contentStream.stroke();
            
            contentStream.moveTo(startX+100+stringWidth+2, startY+200+fontSize);
            contentStream.lineTo(startX+100+stringWidth+2, startY-2);
            contentStream.stroke();
            
            contentStream.moveTo(startX+100+stringWidth+2, startY-2);
            contentStream.lineTo( startX-2, startY-2);
            contentStream.stroke();
            //contentStream.drawLine(startX-2, startY+200+fontSize, startX+100+stringWidth+2, startY+200+fontSize);
            // contentStream.drawLine(startX+100+stringWidth+2, startY+200+fontSize, startX+100+stringWidth+2, startY-2);
            // contentStream.drawLine(startX+100+stringWidth+2, startY-2, startX-2, startY-2);
            contentStream.close();

            doc.save( outfile );
            System.out.println("DoIt finalizado !!");
        }
        finally
        {
            if( doc != null )
            {
                doc.close();
            }
        } 

    }
	
    private static void dibujaRectangulo() throws Exception { //float posX, float posY, float ancho, float alto
    	PDDocument doc	= new PDDocument();
    	PDFont font	=	PDType1Font.COURIER;
    	
    	PDPage pagina	=	new PDPage();
    	
    	
    	String tituloPrincipal			=	"Acontecimientos Nacionales Informe Presidencial";
    	String tituloCriticidadAlta		=	"Criticidad Alta : ";
    	String tituloCriticidadMedia	=	"Criticidad Media : ";
    	String tituloCriticidadBaja		=	"Criticidad Baja : ";
    	
    	float tamanoFont	=	10;
    	
    	pagina.setMediaBox(PDRectangle.LETTER);
    	pagina.setRotation(90);
    	
    	doc.addPage(pagina);
    	
    	PDRectangle paginaCompleta	=	pagina.getMediaBox();
    	
    	float anchoPagina	=	paginaCompleta.getWidth();
    	float altoPagina	=	paginaCompleta.getHeight();
    	
    	PDPageContentStream areaContenido	=	new PDPageContentStream(doc, pagina);
    	
    	Matrix matriz = new Matrix(0, 1, -1, 0, anchoPagina, 0);
    	
    	areaContenido.transform(matriz);
    	
    	// Margen inferior
    	/*
    	areaContenido.moveTo(10, 10);
    	areaContenido.lineTo(altoPagina - 10, 10);
    	areaContenido.stroke();
    	*/
    	// Margen superior
    	/*
    	areaContenido.moveTo(10,anchoPagina - 10);
    	areaContenido.lineTo(altoPagina - 10, anchoPagina - 10);
    	areaContenido.stroke();
    	*/
    	// Punto central superior para poner el titulo
    	
    	float xCentro	=	(altoPagina - 20)/2.0f;
    	float yCentro	=	anchoPagina - 15;
    	
    	// Coordenada X del 25% , 10% , 33% del ancho de la página respectivamente
    	float x25	=	altoPagina / 4.0f;
    	float x10	=	altoPagina / 10.0f;
    	float x33	=	altoPagina / 3.0f;
    	
    	// Escribe el titulo Principal
    	
    	font		=	PDType1Font.COURIER_BOLD;    	
    	tamanoFont	=	20;
    	areaContenido.setFont(font, tamanoFont);
    	
    	areaContenido.beginText();
    	areaContenido.setNonStrokingColor(Color.BLACK);
    	areaContenido.newLineAtOffset(x10 , yCentro - 20);
    	areaContenido.showText(tituloPrincipal.toUpperCase());
    	areaContenido.endText();
    	
    	// Subraya titulo principal
    	areaContenido.moveTo(x10, yCentro - 22);
    	areaContenido.lineTo(x10 + 550, yCentro - 25); //tituloPrincipal.length()
    	areaContenido.stroke();
    	
    	// Escribe subtitulo Critico
    	font		=	PDType1Font.COURIER;
    	tamanoFont	=	10;
    	areaContenido.setFont(font, tamanoFont);
    	areaContenido.beginText();
    	areaContenido.setNonStrokingColor(Color.RED);
    	areaContenido.newLineAtOffset(x10 + 10 , yCentro - 40);
    	areaContenido.showText(tituloCriticidadAlta);
    	areaContenido.endText();
    	
    	// Escribe subtitulo Medio
    	font		=	PDType1Font.COURIER_BOLD;
    	tamanoFont	=	10;
    	areaContenido.setFont(font, tamanoFont);
    	areaContenido.beginText();
    	areaContenido.setNonStrokingColor(Color.green);
    	areaContenido.newLineAtOffset(x10 + 200 , yCentro - 40);
    	areaContenido.showText(tituloCriticidadMedia);
    	areaContenido.endText();
    	
    	// Escribe subtitulo Bajo
    	font		=	PDType1Font.COURIER;
    	tamanoFont	=	8;
    	areaContenido.setFont(font, tamanoFont);
    	areaContenido.beginText();
    	areaContenido.setNonStrokingColor(Color.BLUE);
    	areaContenido.newLineAtOffset(x10 + 400 , yCentro - 40);
    	areaContenido.showText(tituloCriticidadBaja);
    	areaContenido.endText();
    	
    	// Pone logo del gobierno
    	
    	PDImageXObject pdImagen	=	PDImageXObject.createFromFile("/Volumes/JMHD/_JediFiles/tmp/logo.png", doc);
    	areaContenido.drawImage(pdImagen, 5, anchoPagina - 70 , 60 , 60);
    	
    	// Pone primera area rectangular
    	// Lado superior
    	areaContenido.moveTo(100, anchoPagina - 100);
    	areaContenido.lineTo(x33, anchoPagina - 100);
    	areaContenido.stroke();
    	// Costado izquierdo
    	areaContenido.moveTo(100, anchoPagina - 100);
    	areaContenido.lineTo(100, anchoPagina - 250);
    	areaContenido.stroke();   
    	// Lado inferior
    	areaContenido.moveTo(100, anchoPagina - 250);
    	areaContenido.lineTo(x33, anchoPagina - 250);
    	areaContenido.stroke();
    	// Costado derecho
    	areaContenido.moveTo(x33, anchoPagina - 100);
    	areaContenido.lineTo(x33, anchoPagina - 250);
    	areaContenido.stroke(); 
    	
    	// Pone segunda area rectangular
    	// Lado superior
    	areaContenido.moveTo(x33, anchoPagina - 100);
    	areaContenido.lineTo(2* x33 - 100, anchoPagina - 100);
    	areaContenido.stroke();
    	// Costado izquierdo
    	areaContenido.moveTo(x33, anchoPagina - 100);
    	areaContenido.lineTo(x33, anchoPagina - 250);
    	areaContenido.stroke();   
    	// Lado inferior
    	areaContenido.moveTo(x33, anchoPagina - 250);
    	areaContenido.lineTo(2*x33 - 100, anchoPagina - 250);
    	areaContenido.stroke();
    	// Costado derecho
    	areaContenido.moveTo(2 * x33 - 100, anchoPagina - 100);
    	areaContenido.lineTo(2 * x33 - 100, anchoPagina - 250);
    	areaContenido.stroke(); 
    	
    	// Pone tercera area rectangular
    	// Lado superior
    	areaContenido.moveTo(2	* x33 - 100, anchoPagina - 100);
    	areaContenido.lineTo(3	* x33 - 200, anchoPagina - 100);
    	areaContenido.stroke();
    	// Costado izquierdo
    	areaContenido.moveTo(x33, anchoPagina - 100);
    	areaContenido.lineTo(x33, anchoPagina - 250);
    	areaContenido.stroke();   
    	// Lado inferior
    	areaContenido.moveTo(2	* x33 - 100, anchoPagina - 250);
    	areaContenido.lineTo(3	* x33 - 200, anchoPagina - 250);
    	areaContenido.stroke();
    	// Costado derecho
    	areaContenido.moveTo(3	* x33 - 200, anchoPagina - 100);
    	areaContenido.lineTo(3	* x33 - 200, anchoPagina - 250);
    	areaContenido.stroke(); 
    	
    	// pone texto en la primera area rectangular
    	
    	float coordenadaY	=	anchoPagina - 110;
    	float anchoFont		=	tamanoFont;
    	float xInicial		=	110;
    	float xFinal		=	x33;
    	
    	SimpleDateFormat	formatoFecha	=	new SimpleDateFormat("dd.MM.yyyy");
    	Date				fecha			=	new Date();
    	
    	String textoLargo = "Durante siglos, Marte ha causado fascinación y misterio en el ser humano, "
    			+ "pero gracias al avance de la ciencia y la tecnología, estamos cerca de resolver "
    			+ "sus secretos. ¿Podremos algún día habitar el \"planeta rojo\"?";
    	
    	if(textoLargo.length() > 254) {
    		textoLargo	=	textoLargo.substring(0, 254); // el maximo de texto es 255
    	}
    	int numeroCaracteres = 30;
    	int inicioCaracteres = numeroCaracteres - 30;
    	while(numeroCaracteres < 254) {
        	areaContenido.beginText();
        	areaContenido.newLineAtOffset(xInicial, coordenadaY);
        	areaContenido.showText(textoLargo.substring(inicioCaracteres, numeroCaracteres));
        	areaContenido.endText();
        	numeroCaracteres += 30;
        	coordenadaY	-= 10;
    	}
    	
    	areaContenido.beginText();
    	areaContenido.newLineAtOffset(xInicial, coordenadaY);
    	areaContenido.showText("Texto inicial primer rectangulo");
    	areaContenido.endText();
    	
    	coordenadaY	-=	10;
    	
    	areaContenido.beginText();
    	areaContenido.newLineAtOffset(xInicial, coordenadaY);
    	areaContenido.showText("Texto secundario primer rectangulo");
    	areaContenido.endText();
    	
    	
    	
    	
    	areaContenido.close();
    	doc.save("/Volumes/JMHD/_JediFiles/tmp/test_22.pdf");
    	
    	System.out.println("Doc hecho !");
    	
    	
    }

}
