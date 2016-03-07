package md.mi.model.json.request;

import md.mi.model.base.ModelBase;

import javax.validation.constraints.NotNull;

/**
 * Created by ael on 3/7/16.
 */
public class TransactionRequest extends ModelBase {

    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private int amount;
    @NotNull
    private String type;

    public TransactionRequest() {
        super();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
