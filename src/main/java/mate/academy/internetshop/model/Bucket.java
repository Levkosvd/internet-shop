package mate.academy.internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private long id;
    private Order order;
    private List<Item> bucketItems;

    public Bucket() {
        bucketItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getBucketItems() {
        return bucketItems;
    }

    public void setBucketItems(List<Item> bucketItems) {
        this.bucketItems = bucketItems;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "bucketId=" + id +
                ", order=" + order +
                ", bucketItems=" + bucketItems +
                '}';
    }
}
