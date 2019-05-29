package service;

import database.InvoiceDataBase;
import model.Invoice;

public class InvoiceService {
    private InvoiceDataBase dataBase;
    private ArrayService arrayService;
    private static InvoiceService service = null;

    private InvoiceService(InvoiceDataBase dataBase) {
        this.arrayService = ArrayService.getArrayService();
        this.dataBase = dataBase;
    }

    public static InvoiceService getInvoiceService(InvoiceDataBase dataBase) {
        if (service == null) {
            service = new InvoiceService(dataBase);
        }
        return service;
    }

    public static InvoiceService getInvoiceService() {
        return service;
    }

    public boolean insertInvoice(Invoice invoice) {
        int maxNumber = arrayService.getMaxElement(mapInvoicesToTheirNumbers(dataBase.getInvoices()));
        invoice.setNumber(maxNumber + 1);
        invoice.setId(generateUniqueId());

        Invoice[] invoices = arrayService.pushElement(dataBase.getInvoices(), invoice);

        if (invoices == null) {
            return false;
        } else {
            dataBase.setInvoices(invoices);
            return true;
        }
    }

    public boolean updateInvoice(Invoice invoice) {
        return arrayService.updateElement(dataBase.getInvoices(), invoice);
    }

    public boolean updateInvoice(Invoice invoice, String invoiceId) {
        return arrayService.updateElement(dataBase.getInvoices(), invoice, invoiceId);
    }

    public boolean updateInvoice(Invoice invoice, int number) {
        return arrayService.updateElement(dataBase.getInvoices(), invoice, number);
    }

    public boolean removeElement(int number) {
        return arrayService.removeElement(dataBase.getInvoices(), number);
    }

    public boolean removeElement(Invoice invoice) {
        return arrayService.removeElement(dataBase.getInvoices(), invoice);
    }

    public boolean removeElement(String invoiceId) {
        return arrayService.removeElement(dataBase.getInvoices(), invoiceId);
    }

    public Invoice[] findAll() {
        return dataBase.getInvoices();
    }

    public Invoice findByNumber(int number) {
        return arrayService.findByNumber(dataBase.getInvoices(), number);
    }

    private boolean searchForId(String id) {
        Invoice[] invoices = dataBase.getInvoices();
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if (invoices[i] != null && invoices[i].getId() != null && invoices[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String generateUniqueId() {
        String theNewAlphabet = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890qwertyuiopasdfghjklzxcvbnm";
        String id = "";
        for (int i = 0; i < 40; i++) {
            int index = (int) (theNewAlphabet.length() * Math.random());
            id = id + theNewAlphabet.charAt(index);
        }
        if (searchForId(id)) {
            id = generateUniqueId();
        }
        return id;
    }

    private int[] mapInvoicesToTheirNumbers(Invoice[] invoices) {
        int length = invoices == null ? 0: invoices.length;
        int[] invoiceNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            if (invoices[i] == null) {
                invoiceNumbers[i] = 0;
            } else {
                invoiceNumbers[i] = invoices[i].getNumber();
            }
        }
        return invoiceNumbers;
    }
}
