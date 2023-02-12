package com.hcsc.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hcsc.entity.EdTriggerEntity;
import com.hcsc.entity.EligibilityEntity;
import com.hcsc.entity.UserRegistration;
import com.hcsc.pojo.CoResponse;
import com.hcsc.repo.DcCaseRepo;
import com.hcsc.repo.EdTriggerRepo;
import com.hcsc.repo.EligibilityRepo;
import com.hcsc.repo.UserRegistrationRepo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

@Service
public class CoServiceImpl implements CoService {

	@Autowired
	private EligibilityRepo eligibilityRepo;

	@Autowired
	private EdTriggerRepo edTriggerRepo;

	@Autowired
	private UserRegistrationRepo userRegistrationRepo;

	@Autowired
	private DcCaseRepo dcCaseRepo;

	@Autowired
	private JavaMailSender javaMailSender;

	UserRegistration userDetails = null;
	EligibilityEntity edEligibleData = null;
	ByteArrayOutputStream outputStream = null;

	@Override
	public CoResponse sendPdfAndMail() {

		List<EdTriggerEntity> edTriData = edTriggerRepo.findByStatus("pending");
		CoResponse response = new CoResponse();

		if (!edTriData.isEmpty()) {
			response.setRecordsProcessed(Long.valueOf(edTriData.size()));
			edTriData.stream().forEach(ed -> {
				try {
					outputStream = new ByteArrayOutputStream();

					edEligibleData = eligibilityRepo.findByCaseNum(ed.getCaseNum());

					if (edEligibleData != null) {
						userDetails = userRegistrationRepo.findByAppId(dcCaseRepo.findByCaseNum(edEligibleData.getCaseNum()).getAppId());

					generatePdfInstance(edEligibleData, userDetails, outputStream);
					sendMail(edEligibleData, userDetails, outputStream, response);
					}
				} catch (DocumentException | MessagingException e) {
					response.setFailureCount(response.getFailureCount() + 1);
					e.printStackTrace();
				}
				if (response.isEmailSend()) {
					response.setSucceCount(response.getSucceCount() + 1);
					ed.setStatus("Success");
					ed.setPdf(outputStream.toByteArray());
					edTriggerRepo.save(ed);

				}
			});

		}

		return response;
	}

	private void sendMail(EligibilityEntity edEligibleData2, UserRegistration user, ByteArrayOutputStream outputStream,
			CoResponse response) throws MessagingException {

		MimeMessage mailMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);

		messageHelper.setTo(user.getEmail());
		messageHelper.setFrom("veerababu.testmail@gmail.com");
		messageHelper.setSubject("RI");

		DataSource dataSource = new ByteArrayDataSource(outputStream.toByteArray(), "application/pdf");

		MimeBodyPart pdfBodyPart = new MimeBodyPart();
		pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		pdfBodyPart.setFileName("test.pdf");

		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(pdfBodyPart);
		mailMessage.setContent(mimeMultipart);
		javaMailSender.send(mailMessage);

		response.setEmailSend(true);

	}

	private void generatePdfInstance(EligibilityEntity elge, UserRegistration userDetails,
			ByteArrayOutputStream outputStream) throws DocumentException {
		Document document = new Document();

		PdfWriter.getInstance(document, outputStream);

		document.open();
		Paragraph heading = new Paragraph(
				elge.getStatus().equalsIgnoreCase("Approved") ? "Approve Notice" : "Denied Notice");
		heading.setAlignment(Element.ALIGN_CENTER);
		document.add(heading);
		document.add(Chunk.NEWLINE);
		DottedLineSeparator lineSeparator = new DottedLineSeparator();
		lineSeparator.setLineColor(new BaseColor(20, 30, 20));
		Paragraph line = new Paragraph();
		line.add(lineSeparator);
		document.add(line);
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph("Case Num : \t" + elge.getCaseNum()));
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph("Name : " + userDetails.getFname()));
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph("Plan Name:  " + elge.getPlanName()));
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph("Plan Start Date: " + elge.getStartDate()));
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph("Plan End Date : " + elge.getEndDate()));
		document.add(new Paragraph(Chunk.NEWLINE));
		if (elge.getStatus().equalsIgnoreCase("Approved")) {
			document.add(new Paragraph("Benifit Amount : " + elge.getBeniftAmout()));
		}
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(line);
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph("DHS ofc Address  RI New Jersy "));
		document.add(new Paragraph("Contact Number  8548548785"));
		document.add(new Paragraph("Web Site Url"));
		document.close();
	}

}
