package org.chaos.demos.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author Chaos
 *         21/05/2017
 */

@Entity
public class Bill {

    public Bill(boolean isIncome, String category, float price, long timestamp, String note) {
        this.isIncome = isIncome;
        this.category = category;
        this.price = price;
        this.timestamp = timestamp;
        this.note = note;
    }

    @Generated(hash = 1399599325)
    public Bill() {
    }

    @Generated(hash = 1652883341)
    public Bill(Long id, boolean isIncome, String category, float price, long timestamp, String note) {
        this.id = id;
        this.isIncome = isIncome;
        this.category = category;
        this.price = price;
        this.timestamp = timestamp;
        this.note = note;
    }

    @Id(autoincrement = true)
    private Long id;
    private boolean isIncome;
    private String category;
    private float price;
    private long timestamp;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(boolean income) {
        isIncome = income;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Bill bill = (Bill) o;

        return id.longValue() == bill.id.longValue();
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isIncome ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", isIncome=" + isIncome +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                ", note='" + note + '\'' +
                '}';
    }
}
