package com.hcsc.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class generatePdfUsingItextPdf {

	public static String reportsAndStatesPdfExport(String reportType) throws ParseException, FileNotFoundException {

		// List<Map<String, Object>> reportListData,

		List<Map<String, Object>> reportListData = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listofSummeryData = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listOfSessionData = new ArrayList<Map<String, Object>>();

		Map<String, Object> sessionTableData = new HashMap<String, Object>();
		Map<String, Object> summerTableData = new HashMap<String, Object>();

		// sessionTable data
		sessionTableData.put("duration", "00:08:00");
		sessionTableData.put("stationId", "30001");
		sessionTableData.put("portId", "1");
		sessionTableData.put("siteId", "317");
		sessionTableData.put("siteName", "Site name");
		sessionTableData.put("location", "USA");
		sessionTableData.put("SessionId", "69423");
		sessionTableData.put("StartTime", "2020-05-22 21:07:01 IST");
		sessionTableData.put("EndTime", "2020-05-22 21:15:01 IS");
		sessionTableData.put("UserName", "veerababu H");
		sessionTableData.put("PdfprocessingFee", "0");
		sessionTableData.put("Spent", "2.5000");
		sessionTableData.put("kwhHours", "3.83");
		sessionTableData.put("TimePerHr", "8.0");
		sessionTableData.put("MasterList", "1");
		sessionTableData.put("UserId", "215");
		sessionTableData.put("GroupId", "5");
		sessionTableData.put("GroupName", "hah");
		sessionTableData.put("chargerType", "AC");
		sessionTableData.put("evseType", "LEVEL 2");
		sessionTableData.put("Cost", "0.5000");
		sessionTableData.put("connectedTime", "0");
		sessionTableData.put("totalDurationForPdf", "00:08:00");
		sessionTableData.put("totalProcessingFee", "0");

		listOfSessionData.add(sessionTableData);

		// Charging Summery Table data;
		summerTableData.put("stationId", "30001");
		summerTableData.put("portId", "1");
		summerTableData.put("siteId", "317");
		summerTableData.put("siteName", "Site name");
		summerTableData.put("location", "USA");
		summerTableData.put("totalrevenue", "2.5");
		summerTableData.put("totalkwh", "3.83");
		summerTableData.put("tottranscount", "1");
		summerTableData.put("totalCost", "12");
		listofSummeryData.add(summerTableData);

		HashMap<String, Object> finalMapData = new HashMap<String, Object>();

		finalMapData.put("ChargingSummerData", listofSummeryData);
		finalMapData.put("ChargingSessionTable", listOfSessionData);

		reportListData.add(finalMapData);

		System.out.println("finaldataMap>>>" + reportListData);

		PdfPTable chargingSummerTable = null;
		PdfPTable chargingSessionTable = null;

		// String PDF_File_LOC = "C:/reportsAndStatesPdf";
		String logPath = "C:\\Users\\vamshi\\Downloads\\evgatewayLogo.jpg";

		Font tbl_headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, new BaseColor(67, 67, 67));
		Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(255, 255, 255));
		Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);

		PdfPCell headingCell = new PdfPCell();
		headingCell.setBackgroundColor(new BaseColor(68, 68, 68));

		Document document = new Document();
		try {

			if (reportListData != null) {
				Map<String, Object> map = (Map<String, Object>) reportListData.get(0);
				List<Map<String, Object>> chargingSummerListTbl = (List<Map<String, Object>>) map
						.get("ChargingSummerData");
				List<Map<String, Object>> chargingSessionListTbl = (List<Map<String, Object>>) map
						.get("ChargingSessionTable");

				if (chargingSummerListTbl != null && chargingSummerListTbl.size() > 0) {
					// File folderObj = new File(PDF_File_LOC);

					// folderObj.mkdirs();
					// File file = new File(folderObj.getAbsolutePath() + "/" +
					// "Report_Analytics.pdf");
					PdfWriter.getInstance(document, new FileOutputStream("reportsAndState.pdf"));

					document.open();
					if (reportType.equalsIgnoreCase("sites")) {
						float chargingSummerTblColWidths[] = { 10, 10, 25, 25, 10, 10, 10 };
						chargingSummerTable = new PdfPTable(chargingSummerTblColWidths);
						chargingSummerTable.setHeaderRows(1);

						float chargingSessionTblColWidths[] = { 5, 10, 10, 5, 5, 15, 15, 10, 10 };
						chargingSessionTable = new PdfPTable(chargingSessionTblColWidths);
						chargingSessionTable.setHeaderRows(1);

						chargingSummerTable.setWidthPercentage(100);
						chargingSummerTable.setSpacingBefore(10f);
						chargingSessionTable.setWidthPercentage(100);
						chargingSessionTable.setSpacingBefore(10f);

						// Summary Table headings
						String chargingSummaryTblHeading[] = { "StationID", "Site ID", "Site Name", "Location",
								"Transactions", "Revenue\n($)", "Energy Usage\n(kWh)" };
						chargingSummerTable = buildPDFTableHeadings(chargingSummerTable, headingCell,
								chargingSummaryTblHeading, headingFont);

						// Summary Table content
						String chargingSummaryTblContentColumn[] = { "StationId", "siteId", "siteName", "location",
								"tottranscount", "totalrevenue", "totalkwh" };
						chargingSummerTable = buildPDFTableContent(chargingSummerTable, chargingSummerListTbl,
								chargingSummaryTblContentColumn, contentFont);

						// Session Table headings
						String chargingSessionTblHeading[] = { "Site ID", "Site Name", "Location", "Station ID",
								"Session ID", "Start Time", "Duration\n(HH:MM:SS)", "Revenue\n($)",
								"Energy Usage\n(kWh)" };
						chargingSessionTable = buildPDFTableHeadings(chargingSessionTable, headingCell,
								chargingSessionTblHeading, headingFont);

						// Session Table content
						String chargingSessionTblContentColumn[] = { "siteId", "siteName", "location", "stationId",
								"SessionId", "StartTime", "duration", "Spent", "kwhHours" };
						chargingSessionTable = buildPDFTableContent(chargingSessionTable, chargingSessionListTbl,
								chargingSessionTblContentColumn, contentFont);

					} 

					Image fullImage = null;

					fullImage = Image.getInstance(logPath);
					fullImage.scaleAbsolute(500f, 750f);
					document.add(fullImage);

					document.newPage();

					Font paragraphFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);

					float[] pointColumnWidths = { 10f };
					PdfPTable table = new PdfPTable(pointColumnWidths); // 1 columns.
					table.setWidthPercentage(100f);
					PdfPCell GeneratedBy = new PdfPCell(new Paragraph(
							"Report Generated By                                       "
									+ "                       :                            " + "EVGSupport",
							paragraphFont));
					PdfPCell RevenueEarnedBy = new PdfPCell(new Paragraph("Total Revenue Earned           "
							+ "                                                 :                             "
							+ "$402.59", paragraphFont));
					PdfPCell TransactionsCount = new PdfPCell(new Paragraph("Total Transactions                  "
							+ "                                               :                              " + "28",
							paragraphFont));
					PdfPCell ChargingTimeUtilization = new PdfPCell(new Paragraph(
							"Total Charging Time utilized (HH:MM:SS)"
									+ "                         :                             " + "36:04:00",
							paragraphFont));
					PdfPCell EnergyConsumption = new PdfPCell(new Paragraph("Total Energy (Kilowatt) Consumption"
							+ "                                 :                               " + "170.39(kWh)",
							paragraphFont));
					PdfPCell DateGeneratedBy = new PdfPCell(new Paragraph("Date and time of the Report being generated"
							+ "                       :                                " + "5/20/2020, 5:39:02 PM",
							paragraphFont));

					GeneratedBy.setBorderColor(new BaseColor(201, 193, 193));
					RevenueEarnedBy.setBorderColor(new BaseColor(201, 193, 193));
					TransactionsCount.setBorderColor(new BaseColor(201, 193, 193));
					ChargingTimeUtilization.setBorderColor(new BaseColor(201, 193, 193));
					EnergyConsumption.setBorderColor(new BaseColor(201, 193, 193));
					DateGeneratedBy.setBorderColor(new BaseColor(201, 193, 193));

					GeneratedBy.setFixedHeight(20);
					table.addCell(GeneratedBy);
					RevenueEarnedBy.setFixedHeight(20);
					table.addCell(RevenueEarnedBy);
					TransactionsCount.setFixedHeight(20);
					table.addCell(TransactionsCount);
					ChargingTimeUtilization.setFixedHeight(20);
					table.addCell(ChargingTimeUtilization);
					EnergyConsumption.setFixedHeight(20);
					table.addCell(EnergyConsumption);
					DateGeneratedBy.setFixedHeight(20);
					table.addCell(DateGeneratedBy);
					document.add(table);

					// document.add(HeadingBox);

					if (chargingSummerTable != null) {

						chargingSummerTable.setSpacingAfter(30f);
						Paragraph paragraph = new Paragraph("CHARGING SESSIONS SUMMERY", tbl_headingFont);
						paragraph.setSpacingBefore(30f);
						document.add(paragraph);
						document.add(chargingSummerTable);
					}
					if (chargingSummerTable != null) {
						chargingSessionTable.setSpacingAfter(30f);
						document.add(new Paragraph("CHARGING SESSIONS DETAILS", tbl_headingFont));
						document.add(chargingSessionTable);
					}
					document.close();

				}
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

		return "sucess";
	}

	public static PdfPTable buildPDFTableContent(PdfPTable pdfPtable, List<Map<String, Object>> listTableContent,
			String[] listTblContentColumn, Font contentFont) {
		try {
			PdfPCell cell = null;
			if (listTableContent.size() > 0) {
				for (int i = 0; i < listTableContent.size(); i++) {
					Map<String, Object> map = listTableContent.get(i);

					for (int col = 0; col < listTblContentColumn.length; col++) {
						if (map.containsKey(listTblContentColumn[col])) {
							if (map.get(listTblContentColumn[col]) == null) {
								cell = new PdfPCell(new Phrase(" "));
							} else {
								cell = new PdfPCell(
										(new Phrase(map.get(listTblContentColumn[col]).toString(), contentFont)));
							}
							cell.setBorderColor(new BaseColor(201, 193, 193));
							pdfPtable.addCell(cell);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdfPtable;
	}

	public static PdfPTable buildPDFTableHeadings(PdfPTable pdfPtable, PdfPCell cell, String[] tableHeading,
			Font headingFont) {
		for (int i = 0; i < tableHeading.length; i++) {
			cell.setPhrase(new Phrase(tableHeading[i], headingFont));
			cell.setBorderColor(new BaseColor(201, 193, 193));
			pdfPtable.addCell(cell);
		}
		return pdfPtable;
	}

	@SuppressWarnings("unused")
	private static String buildPDFTableContent() throws DocumentException {

		Font tbl_headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, new BaseColor(136, 34, 33));
		Font paragraphFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);
		Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, (float) 5.1, Font.BOLD, new BaseColor(255, 255, 255));
		Font contentFont = new Font(Font.FontFamily.TIMES_ROMAN, (float) 5.1);
		PdfPCell headingCell = new PdfPCell();
		headingCell.setBackgroundColor(new BaseColor(136, 34, 33));
		float chargingSummerTblColWidths[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		Document document = new Document();
		Paragraph paragraph = new Paragraph("EvGateway Charging Activity Report", tbl_headingFont);
		paragraph.setSpacingBefore(30f);

		PdfPTable chargingSummerTable = new PdfPTable(chargingSummerTblColWidths);
		chargingSummerTable.setHeaderRows(1);
		chargingSummerTable.setWidthPercentage(100);
		chargingSummerTable.setSpacingBefore(10f);

		List<Map<String, Object>> listTableContent = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> mapData = new HashMap<String, Object>();
		HashMap<String, Object> mapData1 = new HashMap<String, Object>();
		mapData.put("sessionId", 23456);
		mapData.put("User Name", 23456);
		mapData.put("Station ID", 23456);
		mapData.put("Station Name", 23456);

		mapData.put("Start Time", 23456);
		mapData.put("End Time", 23456);
		mapData.put("Duration", 23456);

		mapData.put("Inactivity", 23456);
		mapData.put("Energy", 23456);
		mapData.put("Reason", 23456);
		mapData.put("Cost", 23456);

		mapData.put("Transaction", 23456);
		mapData.put("Processing", 23456);
		mapData.put("Inactivity", 23456);
		mapData.put("Revenue", 23456);
		listTableContent.add(mapData);
		try {
			// File folderObj = new File("C:/AutomatedMailPDF");
			// folderObj.mkdirs();
			// File file = new File(folderObj.getAbsolutePath() + "/"+
			// "Report_Analytics.pdf");
			// PdfWriter.getInstance(document, new FileOutputStream(file));
			PdfWriter.getInstance(document, new FileOutputStream("sample3.pdf"));

			// Summary Table headings
			String chargingSummaryTblHeading[] = { "Session ID", "User Name", "Station ID", "Station Name",
					"Start Time", "End Time", "Duration\n(HH:MM:SS)", "Inactivity \n Time", "Energy \n Usage(kWh)",
					"Reason For \n Termination", "Cost", "Transaction \n Fee", "Processing \n Fee",
					"Inactivity \n Cost", "Revenue ($)" };
			String chargingSummaryTblcontent[] = { "Session ID", "User Name", "Station ID", "Station Name",
					"Start Time", "End Time", "Duration", "Inactivity", "Energy", "Reason", "Cost", "Transaction",
					"Processing", "Inactivity", "Revenue" };

			PdfPCell cell = null;
			if (listTableContent.size() > 0) {

				for (int i = 0; i < chargingSummaryTblHeading.length; i++) {
					headingCell.setPhrase(new Phrase(chargingSummaryTblHeading[i], headingFont));
					headingCell.setBorderColor(new BaseColor(201, 193, 193));
					chargingSummerTable.addCell(headingCell);
				}

				for (int i = 0; i < listTableContent.size(); i++) {
					Map<String, Object> map = listTableContent.get(i);

					for (int col = 0; col < chargingSummaryTblcontent.length; col++) {
						if (map.containsKey(chargingSummaryTblcontent[col])) {
							if (map.get(chargingSummaryTblcontent[col]) == null) {
								cell = new PdfPCell(new Phrase(" "));
								System.out.println(
										"NullConditiondata>>>" + map.get(chargingSummaryTblcontent[col]).toString());
							} else {
								System.out.println("data>>>" + map.get(chargingSummaryTblcontent[col]).toString());
								cell = new PdfPCell(
										(new Phrase(map.get(chargingSummaryTblcontent[col]).toString(), contentFont)));
							}
							cell.setBorderColor(new BaseColor(201, 193, 193));
							chargingSummerTable.addCell(cell);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("chargingsummerData>>>" + document.isOpen());
		document.open();
		if (document.isOpen()) {
			document.add(paragraph);
			Paragraph spacing = new Paragraph();
			spacing.setSpacingBefore(2f);
			document.add(spacing);
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph("Report Generated By                                       "
					+ "                       :                            " + "EVGSupport", paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph("Total Revenue Earned           "
					+ "                                                 :                             " + "$402.59",
					paragraphFont));
			spacing.setSpacingBefore(4f);
			document.add(spacing);
			document.add(new Paragraph(
					"Earnings From Credit Card                "
							+ "                                    :                             " + "$0.00",
					paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph("EvGateway $ Earned                "
					+ "                                             :                              " + "$402.59",
					paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph(
					"Total Transactions                  "
							+ "                                               :                              " + "28",
					paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph("Total Charging Time utilized (HH:MM:SS)"
					+ "                         :                             " + "36:04:00", paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph(
					"Total Energy (Kilowatt) Consumption"
							+ "                                 :                               " + "170.39(kWh)",
					paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph(
					"Date and time of the Report being generated"
							+ "                       :                                " + "5/20/2020, 5:39:02 PM",
					paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(new Paragraph("Processing Fee(%)"
					+ "                                                                :                                "
					+ "0", paragraphFont));
			spacing.setSpacingBefore(5f);
			document.add(spacing);
			document.add(chargingSummerTable);
			document.close();
		} else {
			System.out.println("documentClosded");
		}

		return "sucess";
	}

	public static void main(String[] args) throws FileNotFoundException, DocumentException, ParseException {

		reportsAndStatesPdfExport("stations");
	}
}
