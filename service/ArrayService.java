package service;

import database.InvoiceDataBase;
import model.Invoice;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayService {

    private static ArrayService service = null;

    private ArrayService() {

    }

    public static ArrayService getArrayService() {
        if(service == null) {
            service = new ArrayService();
        }
        return service;
    }

    public Invoice[] pushElement(Invoice[] invoices, Invoice invoice) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        int index = 0;
        Invoice[] newInvoices = null;
        for (int i = 0; i < length; i++) {
            if(invoices[i] == null) {
                index = i;
                success = true;
                break;
            }
        }
        if(success) {
            invoices[index] = invoice;
            return sortArrayByNumber(invoices);
        } else {
            newInvoices = new Invoice[length + 1];
            for(int i = 0; i < length; i++) {
                newInvoices[i] = invoices[i];
            }
            newInvoices[length] = invoice;
            return sortArrayByNumber(newInvoices);
        }
//        System.out.println(newInvoices.length);
    }

    public boolean updateElement(Invoice[] invoices, Invoice invoice) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoice.getId().equals(invoices[i].getId()) || invoice == invoices[i]) {
                invoices[i] = invoice;
                success = true;
            }
        }
        return success;
    }

    public boolean updateElement(Invoice[] invoices, Invoice invoice, String invoiceId) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoices[i].getId().equals(invoiceId)) {
                invoices[i] = invoice;
                success = true;
                break;
            }
        }
        return success;
    }

    public boolean updateElement(Invoice[] invoices, Invoice invoice, int number) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoices[i].getNumber() == number) {
                invoices[i] = invoice;
                success = true;
                break;
            }
        }
        return success;
    }



    public boolean removeElement(Invoice[] invoices, int number) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoices[i].getNumber() == number) {
                invoices[i] = null;
                success = true;
                break;
            }
        }
        return success;
    }

    public boolean removeElement(Invoice[] invoices, Invoice invoice) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoices[i] == invoice || invoices[i].getId().equals(invoice.getId())) {
                invoices[i] = null;
                success = true;
                break;
            }
        }
        return success;
    }

    public boolean removeElement(Invoice[] invoices, String invoiceId) {
        boolean success = false;
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoices[i].getId().equals(invoiceId)) {
                invoices[i] = null;
                success = true;
                break;
            }
        }
        return success;
    }

    public Invoice findByNumber(Invoice[] invoices, int number) {
        int length = invoices == null ? 0: invoices.length;
        for (int i = 0; i < length; i++) {
            if(invoices[i] != null && invoices[i].getNumber() == number) {
                return invoices[i];
            }
        }
        return null;
    }

    public int getMaxElement(int[] elements) {
        int length = elements == null ? 0 : elements.length;
        int max = 0;
        for(int i = 0; i < length; i++) {
            if(elements[i] > max) {
                max = elements[i];
            }
        }
        return max;
    }

    private Invoice[] sortArrayByNumber(Invoice[] invoices) {
        for(int i = 0; i < invoices.length; i++) {
            if(invoices[i] == null) {
                invoices[i] = new Invoice();
                invoices[i].setNumber(0);
            }
        }
        Arrays.sort(invoices, Comparator.comparing(Invoice::getNumber));
        for(int i = 0; i < invoices.length; i++) {
            if(invoices[i].getNumber() == 0) {
                invoices[i] = null;
            }
        }
        return invoices;
    }
}
