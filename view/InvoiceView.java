package view;

import model.Invoice;
import service.InvoiceService;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InvoiceView extends JPanel {

    private InvoiceService invoiceService = null;

    public InvoiceView() {
        setBackground(Color.WHITE);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 400);
        frame.add(this);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        invoiceService = InvoiceService.getInvoiceService();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        Invoice[] invoices = invoiceService.findAll();

        int length = invoices == null ? 0: invoices.length;
        int count = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for(int i = 0; i < length; i++){
            if(invoices[i] != null) {
                count++;
                Invoice invoice = invoices[i];
                g.drawString(count + ") Invoice " + invoice.getNumber() + ": " + invoice.getName()
                                + " for " + invoice.getPrice() + " by " + invoice.getPayingCustomer() +
                                " on " + simpleDateFormat.format(invoice.getPayedDate()),20, 20*(count+1));
            }
        }
    }
}
