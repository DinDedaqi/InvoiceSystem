import database.InvoiceDataBase;
import model.Invoice;
import service.InvoiceService;
import view.InvoiceView;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    private static InvoiceService invoiceService = null;
    private static InvoiceView invoiceView = null;


    public static void main(String[] args) {
        InvoiceDataBase dataBase = new InvoiceDataBase();
        invoiceService = InvoiceService.getInvoiceService(dataBase);

        invoiceView = new InvoiceView();
        useDatabase();
    }

    private static void useDatabase() {
        String order = JOptionPane.showInputDialog(null, "What do you want to do? (INSERT, DELETE, UPDATE, QUIT)");
        switch (order.toUpperCase()){
            case "INSERT":
                insertInvoice();
                invoiceView.repaint();
                useDatabase();
                break;
            case "DELETE":
                deleteInvoice();
                invoiceView.repaint();
                useDatabase();
                break;
            case "UPDATE":
                updateInvoice();
                invoiceView.repaint();
                useDatabase();
                break;
            case "QUIT":
                break;
            default:
                useDatabase();
        }
    }

    private static void updateInvoice() {
        int updatedNumber = getNumber("Enter the number of the invoice you want to update");
        Invoice invoice = null;
        try {
            invoice = invoiceService.findByNumber(updatedNumber);
            invoice.getNumber();
        } catch (Exception e) {
            String shouldContinue = JOptionPane.showInputDialog("You have entered wrong input. Do you want to CONTINUE?");
            if(shouldContinue.toUpperCase().equals("CONTINUE")) {
                updateInvoice();
            } else return;
        }

        boolean updateCustomer = shouldUpdate("paying customer");
        if(updateCustomer) {
            invoice.setPayingCustomer(JOptionPane.showInputDialog("What is the paying customer you want to update to?"));
        }
        boolean updateName = shouldUpdate("name");
        if(updateName) {
            invoice.setName(JOptionPane.showInputDialog("What is the name you want to update to"));
        }
        boolean updatePrice = shouldUpdate("price");
        if(updatePrice) {
            invoice.setPrice(getNumber("What is the price you want to update to?"));
        }
        boolean updateDate = shouldUpdate("date");
        if(updateDate) {
            invoice.setPayedDate(getDate());
        }
        invoiceService.updateInvoice(invoice);
    }

    private static boolean shouldUpdate(String object) {
        return JOptionPane
                .showConfirmDialog(null, "Do you want to update " + object) == JOptionPane.YES_OPTION;
    }

    private static void deleteInvoice() {
        String order = JOptionPane.showInputDialog(null, "How do you want to delete? (ID, NUMBER, QUIT)");
        switch (order.toUpperCase()) {
            case "ID":
                invoiceService.removeElement(JOptionPane.showInputDialog("Enter the id of the invoice you want to delete."));
                break;
            case "NUMBER":
                int updatedNumber = getNumber("Enter the number that you want to delete");
                Invoice invoice = null;
                try {
                    invoice = invoiceService.findByNumber(updatedNumber);
                    invoice.getNumber();
                } catch (Exception e) {
                    String shouldContinue = JOptionPane.showInputDialog("You have entered wrong input. Do you want to CONTINUE?");
                    if(shouldContinue.toUpperCase().equals("CONTINUE")) {
                        deleteInvoice();
                    } else return;
                }
                invoiceService.removeElement(updatedNumber);
                break;
            case "QUIT":
                useDatabase();
                break;
            default:
                deleteInvoice();
        }
    }

    private static void insertInvoice() {
        String name = JOptionPane.showInputDialog(null, "The name of the invoice is: ");
        String customer = JOptionPane.showInputDialog(null, "The customer paying the invoice is: ");
        int price = getNumber("The price of the invoice is: ");
        Date date = getDate();
        invoiceService.insertInvoice(new Invoice(name, customer, price, date));
    }

    public static Date getDate() {
        Date date;
        String format = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateAsString = JOptionPane.showInputDialog(null, "The payed date of the invoice in the form " + format + " is: ");
        try {
            date = simpleDateFormat.parse(dateAsString);
        } catch (Exception e) {
            date = getDate();
        }
        return date;
    }

    public static int getNumber(String message) {
        int d;
        try {
            d = Integer.parseInt(JOptionPane.showInputDialog(null, message));
        } catch (NumberFormatException | NullPointerException nfe) {
            d = getNumber(message);
        }
        return d;
    }
}
