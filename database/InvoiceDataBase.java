package database;

import model.Invoice;

public class InvoiceDataBase {
    private Invoice[] invoices;

    public InvoiceDataBase() {
    }

    public InvoiceDataBase(Invoice[] invoices) {
        this.invoices = invoices;
    }

    public Invoice[] getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoice[] invoices) {
        this.invoices = invoices;
    }

}
