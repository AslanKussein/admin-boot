package kz.crtr.emaket.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.pdf.qrcode.ByteMatrix;
import net.sf.jasperreports.engine.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static kz.crtr.emaket.utils.Constant.DEF_ENCODING_UTF8;


/**
 * @author Bekzhan
 */
public class FileManager {

    public static byte[] getPdfByJrxmlByte(InputStream input, JDocumentParameter jdp) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            JasperPrint jasperPrint;

            if (jdp.getBeanColDataSource() != null) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, jdp.getParametersMap(), jdp.getBeanColDataSource());
            } else {
                jasperPrint = JasperFillManager.fillReport(jasperReport, jdp.getParametersMap());
            }

            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, os);
                return os.toByteArray();
            } finally {
                input.close();
            }
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[] pixels = new int[width * height];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pixels[j * width + i] = matrix.get(j, i) ? Color.BLACK.getRGB() : Color.WHITE.getRGB();
            }
        }
        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }

    public static BufferedImage getMatrixByString(String code) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, DEF_ENCODING_UTF8);
//        hints.put(EncodeHintType.CHARACTER_SET, "windows-1251");
        hints.put(EncodeHintType.MARGIN, 4); // Default 4
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, 1, 1, hints);
        BufferedImage image = matrixToImage(matrix);
        try {
            return image;
        } finally {
            matrix.clear();
            hints.clear();
        }
    }

    public static String blobToString(Blob blob) throws IOException, SQLException {
        if (blob != null) {
            int offset = -1;
            int chunkSize = 1024;
            long blobLength = blob.length();
            if (chunkSize > blobLength) {
                chunkSize = (int) blobLength;
            }
            char buffer[] = new char[chunkSize];
            StringBuilder stringBuffer = new StringBuilder();

            try (Reader reader = new InputStreamReader(blob.getBinaryStream(), StandardCharsets.UTF_8)) {
                while ((offset = reader.read(buffer)) != -1) {
                    stringBuffer.append(buffer, 0, offset);
                }
            } finally {
                blob.free();
            }

            return stringBuffer.toString();
        }
        return null;
    }
}